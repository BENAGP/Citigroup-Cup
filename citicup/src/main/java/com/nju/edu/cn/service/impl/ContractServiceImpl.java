package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.dao.*;
import com.nju.edu.cn.entity.*;
import com.nju.edu.cn.model.CommentModel;
import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import com.nju.edu.cn.model.ContractTradeSearch;
import com.nju.edu.cn.service.ContractService;
import com.nju.edu.cn.constant.FuturesType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by shea on 2018/9/7.
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CollectRepository collectRepository;
    @Autowired
    private ContractBackTestRepository contractBackTestRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private ContractRepository contractRepository;

    //比较器，回测数据按更新时间排序
    private Comparator<ContractBackTest> comparator = new Comparator<ContractBackTest>() {
        @Override
        public int compare(ContractBackTest o1, ContractBackTest o2) {
            return (int)(o1.getCreateTime().getTime()-o2.getCreateTime().getTime());
        }
    };

    @Override
    public List<ContractTradeModel> getCollectList(Long userId, Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        //首先找到用户偏好风险等级
        User user = userRepository.findByUserId(userId);
        int riskLevel = user.getPreferRiskLevel();
        //然后找到用户收藏表
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(page,pageNum,sort);
        List<Collect> collects = collectRepository.findByUser_UserIdAndDeleted(userId,false,pageRequest).getContent();
        Long now = System.currentTimeMillis();
        //遍历收藏表，生成ContractTradeModel
        collects.forEach(collect -> {
            //取出收藏的合约ID
            Contract contract = collect.getContract();
            Long contractId = contract.getContractId();
            Date ddl = contract.getNearbyFutures().getLastTradingDate();
            //合约ID+风险等级+购买用户ID=>一次购买（每有一次购买，就增加一条回测数据线）
            //这里应该是新增合约的时候默认增加的回测数据多条数据线中的一条
            Trade trade = tradeRepository.findByContract_ContractIdAndRiskLevelAndUser_UserId(contractId,riskLevel,null);
            ContractTradeModel contractTradeModel = new ContractTradeModel();
            //找出与该此购买对应的一条回测数据线
            List<ContractBackTest> contractBackTests = trade.getContractBackTests();
            Collections.sort(contractBackTests,comparator);//按更新时间排序
            BeanUtils.copyProperties(contractTradeModel,trade);
            List<Float> yields = new ArrayList<>();// 收益率纵轴
            List<Date> updateTimes = new ArrayList<>();//时间横轴
            contractBackTests.forEach(contractBackTest -> {
                yields.add(contractBackTest.getYield());
                updateTimes.add(contractBackTest.getCreateTime());
            });
            contractTradeModel.updateTimes = updateTimes;
            contractTradeModel.yields = yields;
            contractTradeModel.ddl = ddl;
            contractTradeModel.contractId = contractId;
            contractTradeModel.isEnd = (ddl.getTime()<now);
            contractTradeModels.add(contractTradeModel);
        });
        return contractTradeModels;
    }

    @Override
    public void collect(Long userId, Long contractId) {
        Collect collect = new Collect();
        Contract contract = contractRepository.findByContractId(contractId);
        User user = userRepository.findByUserId(userId);
        collect.setContract(contract);
        collect.setUser(user);
        collect.setCreateTime(new Date(System.currentTimeMillis()));
        collectRepository.save(collect);
    }

    @Override
    public void cancelCollect(Long userId, Long contractId) {
        Collect collect = collectRepository.findByContract_ContractIdAndUser_UserId(contractId,userId);
        collect.setDeleted(true);
        collectRepository.save(collect);
    }

    @Override
    public List<ContractTradeModel> getList(Long userId, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        //首先找到用户偏好风险等级
        User user = userRepository.findByUserId(userId);
        int riskLevel = user.getPreferRiskLevel();
        //整理筛选条件
        contractTradeSearch.checkNullValue();
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(page,pageNum,sort);
        List<Trade> trades = null;
        if(contractTradeSearch.type== FuturesType.ALL){
            trades = tradeRepository.findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
                    userId,riskLevel,new Date(System.currentTimeMillis()),contractTradeSearch.yieldR,contractTradeSearch.yieldL,contractTradeSearch.maxDrawdownR,contractTradeSearch.maxDrawdownL,
                    contractTradeSearch.winRateR,contractTradeSearch.winRateL,contractTradeSearch.profitRossRatioR,contractTradeSearch.profitLossRatioL,contractTradeSearch.marketCapitalCapacityR,contractTradeSearch.marketCapitalCapacityL,pageRequest
            ).getContent();
        }else {
            trades = tradeRepository.findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_TypeAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
                    userId,riskLevel,contractTradeSearch.type,new Date(System.currentTimeMillis()),contractTradeSearch.yieldR,contractTradeSearch.yieldL,contractTradeSearch.maxDrawdownR,contractTradeSearch.maxDrawdownL,
                    contractTradeSearch.winRateR,contractTradeSearch.winRateL,contractTradeSearch.profitRossRatioR,contractTradeSearch.profitLossRatioL,contractTradeSearch.marketCapitalCapacityR,contractTradeSearch.marketCapitalCapacityL,pageRequest
            ).getContent();
        }
        trades.forEach(trade -> {
            ContractTradeModel contractTradeModel = new ContractTradeModel();
            //找出与该此购买对应的一条回测数据线
            List<ContractBackTest> contractBackTests = trade.getContractBackTests();
            Collections.sort(contractBackTests,comparator);//按更新时间排序
            BeanUtils.copyProperties(contractTradeModel,trade);
            List<Float> yields = new ArrayList<>();//收益率纵轴
            List<Date> updateTimes = new ArrayList<>();// 时间横轴
            contractBackTests.forEach(contractBackTest -> {
                yields.add(contractBackTest.getYield());
                updateTimes.add(contractBackTest.getCreateTime());
            });
            contractTradeModel.updateTimes = updateTimes;
            contractTradeModel.yields = yields;
            contractTradeModels.add(contractTradeModel);
        });

        return contractTradeModels;
    }

    @Override
    public void buy(Long userId, Long contractId, Float investment, Integer riskLevel) {
        Contract contract = contractRepository.findByContractId(contractId);
        User user = userRepository.findByUserId(userId);
        Trade trade = new Trade();
        trade.setContract(contract);
        trade.setUser(user);
        trade.setRiskLevel(riskLevel);
        trade.setInvestment(investment);
        trade.setCreateTime(new Date(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }

    @Override
    public ContractTradeDetail getDetail(Long userId, Long tradeId) {
        Trade trade = tradeRepository.findTopByTradeId(tradeId);
        return getDetail(trade);
    }

    /**
     * 在详情页切换风险等级的时候调用此方法
     * @param userId
     * @param contractId
     * @param riskLevel
     * @return
     */
    @Override
    public ContractTradeDetail getDetail(Long userId, Long contractId, Integer riskLevel) {
        Trade trade = tradeRepository.findByContract_ContractIdAndRiskLevelAndUser_UserId(contractId,riskLevel,null);
        return getDetail(trade);
    }

    private ContractTradeDetail getDetail(Trade trade){
        ContractTradeDetail contractTradeDetail = new ContractTradeDetail();
        //找出与该此购买对应的一条回测数据线
        List<ContractBackTest> contractBackTests = trade.getContractBackTests();
        Collections.sort(contractBackTests,comparator);//按更新时间排序
        BeanUtils.copyProperties(contractTradeDetail,trade);
        List<Float> yields = new ArrayList<>();//收益率纵轴
        List<Date> updateTimes = new ArrayList<>();// 时间横轴
        contractBackTests.forEach(contractBackTest -> {
            yields.add(contractBackTest.getYield());
            updateTimes.add(contractBackTest.getCreateTime());
        });
        contractTradeDetail.updateTimes = updateTimes;
        contractTradeDetail.yields = yields;
        //todo 添加其ta收益率
        Contract contract = trade.getContract();
        List<ContractBackTestParams> contractBackTestParams = contract.getContractBackTestParams();
        Comparator<ContractBackTestParams> contractBackTestParamsComparator = new Comparator<ContractBackTestParams>() {
            @Override
            public int compare(ContractBackTestParams o1, ContractBackTestParams o2) {
                return (int)(o1.getCreateTime().getTime()-o2.getCreateTime().getTime());
            }
        };
        Collections.sort(contractBackTestParams,contractBackTestParamsComparator);//按更新时间排序
        contractTradeDetail.liquidity = contractBackTestParams.get(contractBackTestParams.size()-1).getLiquidity();
        //todo 是重新查还是直接get?
        List<Comment> comments = contract.getComments();
        List<CommentModel> commentModels = comments.stream().map(comment -> {
            CommentModel commentModel = new CommentModel();
            BeanUtils.copyProperties(commentModel,comment);
            return commentModel;
        }).limit(10).collect(Collectors.toList());
        contractTradeDetail.comments = commentModels;
        return contractTradeDetail;
    }

    @Override
    public void redeem(Long userId, Long tradeId) {
        Trade trade = tradeRepository.findTopByTradeId(tradeId);
        trade.setDeleted(true);
        trade.setDeleteTime(new Date(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }
}
