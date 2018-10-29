package com.nju.edu.cn.service.impl;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.dao.*;
import com.nju.edu.cn.entity.*;
import com.nju.edu.cn.model.*;
import com.nju.edu.cn.service.ContractService;
import com.nju.edu.cn.constant.FuturesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
    @Autowired
    private ContractBackTestParamsRepository contractBackTestParamsRepository;
    @Autowired
    private FuturesUpdatingRepository futuresUpdatingRepository;
    private ContractBackTestDao contractBackTestDao = new ContractBackTestDaoImpl();

    private static Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //比较器，回测数据按更新时间排序
    private Comparator<ContractBackTest> comparator = new Comparator<ContractBackTest>() {
        @Override
        public int compare(ContractBackTest o1, ContractBackTest o2) {
            return (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
        }
    };

    @Override
    public List<ContractTradeModel> getCollectList(Long userId, Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        //首先找到用户偏好风险等级
        User user = userRepository.findByUserId(userId);
        int riskLevel = user.getPreferRiskLevel();
        //然后找到用户收藏表
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(page, pageNum, sort);
        List<Collect> collects = collectRepository.findByUserIdAndDeleted(userId, false, pageRequest).getContent();
        Long now = System.currentTimeMillis();
        //遍历收藏表，生成ContractTradeModel
        collects.forEach(collect -> {
            //取出收藏的合约ID
            Long contractId = collect.getContractId();
            Contract contract = contractRepository.findByContractId(contractId);
            Date ddl = contract.getNearbyFutures().getLastTradingDate();
            //合约ID+风险等级+购买用户ID=>一次购买（每有一次购买，就增加一条回测数据线）
            //这里应该是新增合约的时候默认增加的回测数据多条数据线中的一条
            Trade trade = tradeRepository.findByContract_ContractIdAndRiskLevelAndUser_UserId(contractId, riskLevel, null);
            ContractTradeModel contractTradeModel = new ContractTradeModel();
            //找出与该此购买对应的一条回测数据线
            List<ContractBackTest> contractBackTests = trade.getContractBackTests();
//            Collections.sort(contractBackTests,comparator);//按更新时间排序
//            BeanUtils.copyProperties(trade,contractTradeModel);
            copyProperties(trade, contractTradeModel,FuturesType.ALL);
            List<Double> yields = new ArrayList<>();// 收益率纵轴
            List<Date> updateTimes = new ArrayList<>();//时间横轴
            List<String> formatDates = new ArrayList<>();//时间横轴
            contractBackTests.forEach(contractBackTest -> {
                yields.add(contractBackTest.getYield());
                updateTimes.add(contractBackTest.getCreateTime());
                formatDates.add(simpleDateFormat.format(contractBackTest.getCreateTime()));
            });
            contractTradeModel.updateTimes = updateTimes;
            contractTradeModel.formatDates = formatDates;
            contractTradeModel.yields = yields;
            contractTradeModel.computeYield();
            contractTradeModel.ddl = ddl;
            contractTradeModel.isEnd = (ddl.getTime() < now);
            contractTradeModels.add(contractTradeModel);
        });
        return contractTradeModels;
    }

    @Override
    public void collect(Long userId, Long contractId) {
        Collect collect = new Collect();
        collect.setContractId(contractId);
        collect.setUserId(userId);
        collect.setCreateTime(new Date(System.currentTimeMillis()));
        collect.setDeleted(false);
        collectRepository.save(collect);
    }

    @Override
    public void cancelCollect(Long userId, Long contractId) {
        Collect collect = collectRepository.findByContractIdAndUserId(contractId, userId);
        collect.setDeleted(true);
        collectRepository.save(collect);
    }

    @Override
    public List<ContractTradeModel> getMyTradeList(Long userId, Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        PageRequest pageRequest = new PageRequest(page, pageNum, sort);
//        List<Trade> trades = tradeRepository.findByUser_UserIdAndDeletedIsFalse(userId, pageRequest).getContent();
        List<Trade> trades = tradeRepository.findTop4ByRiskLevel(3);
        trades.forEach(trade -> {
            ContractTradeModel contractTradeModel = new ContractTradeModel();
            //找出与该此购买对应的一条回测数据线
            List<ContractBackTest> contractBackTests = trade.getContractBackTests();
//            Collections.sort(contractBackTests,comparator);//按更新时间排序
//            BeanUtils.copyProperties(trade,contractTradeModel);
            copyProperties(trade, contractTradeModel,FuturesType.ALL);
            List<Double> yields = new ArrayList<>();// 收益率纵轴
            List<Date> updateTimes = new ArrayList<>();//时间横轴
            List<String> formatDates = new ArrayList<>();//时间横轴
            List<Double> positions = new ArrayList<>();//资金占用纵轴
            List<Integer> nearbyFuturesPositionOperations = new ArrayList<>();
            List<Integer> backFuturesPositionOperations = new ArrayList<>();
            for(int i=0;i<contractBackTests.size();i++){
                ContractBackTest contractBackTest = contractBackTests.get(i);
                yields.add(contractBackTest.getYield());
                updateTimes.add(contractBackTest.getCreateTime());
                formatDates.add(simpleDateFormat.format(contractBackTest.getCreateTime()));
                positions.add(contractBackTest.getPosition());
                if(i>0){
                    ContractBackTest pre = contractBackTests.get(i-1);
                    nearbyFuturesPositionOperations.add(contractBackTest.getNearbyFuturesPosition()-pre.getNearbyFuturesPosition());
                    backFuturesPositionOperations.add(contractBackTest.getBackFuturesPosition()-pre.getBackFuturesPosition());
                }else {
                    nearbyFuturesPositionOperations.add(contractBackTest.getNearbyFuturesPosition());
                    backFuturesPositionOperations.add(contractBackTest.getBackFuturesPosition());
                }
            }
            contractTradeModel.updateTimes = updateTimes;
            contractTradeModel.formatDates = formatDates;
            contractTradeModel.yields = yields;
            contractTradeModel.computeYield();
            contractTradeModel.positions = positions;
            //历史调仓
            contractTradeModel.nearbyFuturesPositionOperations = nearbyFuturesPositionOperations;
            contractTradeModel.backFuturesPositionOperations = backFuturesPositionOperations;
            //当前持仓
            Contract contract = trade.getContract();
            Futures nearbyFutures = contract.getNearbyFutures();
            Futures backFutures = contract.getBackFutures();
            contractTradeModel.nearbyFuturesName = nearbyFutures.getName();
            contractTradeModel.backFuturesName = backFutures.getName();
            ContractBackTest contractBackTest = contractBackTests.get(contractBackTests.size()-1);
            contractTradeModel.position = contractBackTest.getPosition();
            contractTradeModel.nearbyFuturesPosition = contractBackTest.getNearbyFuturesPosition();
            contractTradeModel.backFuturesPosition = contractBackTest.getBackFuturesPosition();
            contractTradeModel.todayProfitLoss = contractBackTest.getTodayProfitLoss();
            FuturesUpdating nearbyFuturesUpdating = futuresUpdatingRepository.findTopByFutures_FuturesIdOrderByUpdateTimeDesc(nearbyFutures.getFuturesId());
            FuturesUpdating backFuturesUpdating = futuresUpdatingRepository.findTopByFutures_FuturesIdOrderByUpdateTimeDesc(backFutures.getFuturesId());
            contractTradeModel.nearbyFuturesPrice = nearbyFuturesUpdating.getPrice();
            contractTradeModel.backFuturesPrice = backFuturesUpdating.getPrice();
            contractTradeModels.add(contractTradeModel);
        });

        return contractTradeModels;
    }

    @Override
    public List<ContractTradeModel> getList(Long userId, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum) {
        User user = userRepository.findByUserId(userId);
        int riskLevel = user.getPreferRiskLevel();
        return contractBackTestDao.getList(riskLevel,contractTradeSearch,page,pageNum);
    }

//    @Override
//    public List<ContractTradeModel> getList(Long userId, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum) {
//        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
//        //首先找到用户偏好风险等级
//        User user = userRepository.findByUserId(userId);
//        logger.info(JSON.toJSONString(user));
//        int riskLevel = user.getPreferRiskLevel();
//        //整理筛选条件
//        if (contractTradeSearch == null) contractTradeSearch = new ContractTradeSearch();
//        contractTradeSearch.checkNullValue();
//        final int type = contractTradeSearch.type;
//        if(type==FuturesType.CHEMICAL){
//            riskLevel=6;
//            page=1;
//        }
//        else if(type==FuturesType.FARM_PRODUCE){
//            riskLevel=3;
//            page=1;
//        }
//        Sort sort = new Sort(Sort.Direction.ASC, "profitLossRatio");
////        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        PageRequest pageRequest = new PageRequest(page, pageNum, sort);
//        List<Trade> trades = null;
//        System.out.println("=====================phrase0==========================");
//        if (contractTradeSearch.type != FuturesType.FIANACE) {
//            trades = tradeRepository.findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
//                    null, riskLevel, new Date(System.currentTimeMillis()), contractTradeSearch.yieldR, contractTradeSearch.yieldL, contractTradeSearch.maxDrawdownR, contractTradeSearch.maxDrawdownL,
//                    contractTradeSearch.winRateR, contractTradeSearch.winRateL, contractTradeSearch.profitRossRatioR, contractTradeSearch.profitLossRatioL, contractTradeSearch.marketCapitalCapacityR, contractTradeSearch.marketCapitalCapacityL, pageRequest
//            ).getContent();
////            trades = tradeRepository.findByUser_UserIdAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
////                    null,new Date(System.currentTimeMillis()),contractTradeSearch.yieldR,contractTradeSearch.yieldL,contractTradeSearch.maxDrawdownR,contractTradeSearch.maxDrawdownL,
////                    contractTradeSearch.winRateR,contractTradeSearch.winRateL,contractTradeSearch.profitRossRatioR,contractTradeSearch.profitLossRatioL,contractTradeSearch.marketCapitalCapacityR,contractTradeSearch.marketCapitalCapacityL,pageRequest
////            ).getContent();
//
//        } else {
//            trades = tradeRepository.findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_TypeAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
//                    null, riskLevel, contractTradeSearch.type, new Date(System.currentTimeMillis()), contractTradeSearch.yieldR, contractTradeSearch.yieldL, contractTradeSearch.maxDrawdownR, contractTradeSearch.maxDrawdownL,
//                    contractTradeSearch.winRateR, contractTradeSearch.winRateL, contractTradeSearch.profitRossRatioR, contractTradeSearch.profitLossRatioL, contractTradeSearch.marketCapitalCapacityR, contractTradeSearch.marketCapitalCapacityL, pageRequest
//            ).getContent();
//        }
//        System.out.println("=====================phrase1==========================");
////        trades.set(0,trades.get(5));
////        trades.set(1,trades.get(4));
//        trades.forEach(trade -> {
//            ContractTradeModel contractTradeModel = new ContractTradeModel();
//            //找出与该此购买对应的一条回测数据线
//            System.out.println("=====================phrase1-0==========================");
//            List<ContractBackTest> contractBackTests = trade.getContractBackTests();
//            System.out.println("=====================phrase1-1==========================");
////            Collections.sort(contractBackTests,comparator);//按更新时间排序
////            BeanUtils.copyProperties(trade,contractTradeModel);
//            copyProperties(trade, contractTradeModel,type);
//            List<Double> yields = new ArrayList<>();//收益率纵轴
//            List<Date> updateTimes = new ArrayList<>();// 时间横轴
//            List<String> formatDates = new ArrayList<>();// 时间横轴
//            contractBackTests.forEach(contractBackTest -> {
//                yields.add(contractBackTest.getYield());
//                updateTimes.add(contractBackTest.getCreateTime());
//                formatDates.add(simpleDateFormat.format(contractBackTest.getCreateTime()));
//            });
//            contractTradeModel.updateTimes = updateTimes;
//            contractTradeModel.formatDates = formatDates;
//            contractTradeModel.yields = yields;
//            contractTradeModel.computeYield();
//            contractTradeModels.add(contractTradeModel);
//        });
//        System.out.println("=====================phrase2==========================");
//        //
//        String[] farmProduce = {"A1805-1803","CS1805-1803","RI803-801","RM805-803","A1805-1803","CS1805-1803"};
//        String[] chemical = {"RU1807-1806","L1807-1806","V1807-1806","PP1807-1806","RU1807-1806","L1807-1806"};
//        for(int i=0;i<6;i++){
//            if(type==FuturesType.FARM_PRODUCE){
//                contractTradeModels.get(i).contractName = farmProduce[i];
//            }else if(type==FuturesType.CHEMICAL){
//                contractTradeModels.get(i).contractName = chemical[i];
//            }
//
//        }
//        contractTradeModels.set(0,contractTradeModels.get(5));
//        contractTradeModels.set(1,contractTradeModels.get(4));
//
//        return contractTradeModels;
//    }

    @Override
    public Map<Long, Boolean> isCollected(Long userId, List<Long> contractIds) {
        Map<Long, Boolean> res = new TreeMap<>();
        contractIds.forEach(contractId -> {
            res.put(contractId, false);
        });
        List<Collect> collects = collectRepository.findByUserIdAndDeleted(userId, false);
        collects.forEach(collect -> {
            Long contractId = collect.getContractId();
            if (contractIds.contains(contractId)) {
                res.replace(contractId, true);
            }
        });

        return res;
    }

    private void copyProperties(Trade trade, ContractTradeModel contractTradeModel,Integer type) {
        contractTradeModel.riskLevel = trade.getRiskLevel();
        contractTradeModel.investment = trade.getInvestment();
        contractTradeModel.marketCapitalCapacity = trade.getMarketCapitalCapacity();
        contractTradeModel.maxDrawdown = trade.getMaxDrawdown();
        contractTradeModel.profitLossRatio = trade.getProfitLossRatio();
        contractTradeModel.tradeId = trade.getTradeId();
        contractTradeModel.winRate = trade.getWinRate();
        contractTradeModel.createTime = trade.getCreateTime();
        Contract contract = trade.getContract();
        contractTradeModel.contractId = contract.getContractId();
        contractTradeModel.contractName = contract.getName();

    }

    @Override
    public void buy(Long userId, Long contractId, Double investment, Integer riskLevel) {
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

    /**
     * 详情页初始化的时候调用此方法
     *
     * @param userId
     * @param tradeId
     * @return
     */
    @Override
    public ContractTradeDetail getDetail(Long userId, Long tradeId) {
        Trade trade = tradeRepository.findByTradeId(tradeId);
        return getDetail(trade);
    }

    @Override
    public double getLiquidity(Long userId, Long contractId) {
        List<ContractBackTestParams> contractBackTestParams = contractBackTestParamsRepository.findByContract_ContractId(contractId);
        Comparator<ContractBackTestParams> contractBackTestParamsComparator = new Comparator<ContractBackTestParams>() {
            @Override
            public int compare(ContractBackTestParams o1, ContractBackTestParams o2) {
                return (int) (o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
            }
        };
        Collections.sort(contractBackTestParams, contractBackTestParamsComparator);//按更新时间排序
        return contractBackTestParams.get(0).getLiquidity();
    }

    @Override
    public HistoryMarket getHistoryMarket(Long userId, Long contractId) {
        HistoryMarket historyMarket = new HistoryMarket();
        historyMarket.contractId = contractId;
        Contract contract = contractRepository.findByContractId(contractId);
        Futures nearbyFutures = contract.getNearbyFutures();
        Futures backFutures = contract.getBackFutures();
        historyMarket.nearbyFuturesId = nearbyFutures.getFuturesId();
        historyMarket.nearbyFuturesName = nearbyFutures.getName();
        historyMarket.backFuturesName = backFutures.getName();
        historyMarket.backFuturesId = backFutures.getFuturesId();
        Comparator<FuturesUpdating> comparator = new Comparator<FuturesUpdating>() {
            @Override
            public int compare(FuturesUpdating o1, FuturesUpdating o2) {
                return (int) (o1.getUpdateTime().getTime() - o2.getUpdateTime().getTime());
            }
        };
        List<FuturesUpdating> nearbyFuturesUpdating = futuresUpdatingRepository.findByFutures_FuturesId(nearbyFutures.getFuturesId());
        List<FuturesUpdating> backFuturesUpdating = futuresUpdatingRepository.findByFutures_FuturesId(backFutures.getFuturesId());
//        Collections.sort(nearbyFuturesUpdating, comparator);
//        Collections.sort(backFuturesUpdating, comparator);
        List<Date> updateTimes = new ArrayList<>();
        List<String> formatDates = new ArrayList<>();
        List<Float> nearbyPrices = new ArrayList<>();
        List<Float> backPrices = new ArrayList<>();

        List<Integer> nearbyTradings = new ArrayList<>();
        List<Integer> backTradings = new ArrayList<>();

        nearbyFuturesUpdating.forEach(futuresUpdating -> {
            updateTimes.add(futuresUpdating.getUpdateTime());
            formatDates.add(simpleDateFormat.format(futuresUpdating.getUpdateTime()));
            nearbyPrices.add(futuresUpdating.getPrice());
            nearbyTradings.add(futuresUpdating.getTrading());
        });
        backFuturesUpdating.forEach(futuresUpdating -> {
            backPrices.add(futuresUpdating.getPrice());
            backTradings.add(futuresUpdating.getTrading());
        });
        historyMarket.updateTimes = updateTimes;
        historyMarket.formatDates = formatDates;
        historyMarket.nearbyPrices = nearbyPrices;
        historyMarket.nearbyTradings = nearbyTradings;
        historyMarket.backPrices = backPrices;
        historyMarket.backTradings = backTradings;

        return historyMarket;
    }

    /**
     * 在详情页切换风险等级的时候调用此方法
     *
     * @param userId
     * @param contractId
     * @param riskLevel
     * @return
     */
    @Override
    public ContractTradeDetail getDetail(Long userId, Long contractId, Integer riskLevel) {
        Trade trade = tradeRepository.findByContract_ContractIdAndRiskLevelAndUser_UserId(contractId, riskLevel, null);
        return getDetail(trade);
    }

    private ContractTradeDetail getDetail(Trade trade) {
        Long now = System.currentTimeMillis();
        Contract contract = trade.getContract();
        Date ddl = contract.getNearbyFutures().getLastTradingDate();
        ContractTradeDetail contractTradeDetail = new ContractTradeDetail();
        //找出与该此购买对应的一条回测数据线
        List<ContractBackTest> contractBackTests = trade.getContractBackTests();
//        Collections.sort(contractBackTests,comparator);//按更新时间排序
//        BeanUtils.copyProperties(trade,contractTradeDetail);
        copyProperties(trade, contractTradeDetail);
        List<Double> yields = new ArrayList<>();//收益率纵轴
        List<Date> updateTimes = new ArrayList<>();// 时间横轴
        List<String> formatDates = new ArrayList<>();// 时间横轴
        contractBackTests.forEach(contractBackTest -> {
            yields.add(contractBackTest.getYield());
            updateTimes.add(contractBackTest.getCreateTime());
            formatDates.add(simpleDateFormat.format(contractBackTest.getCreateTime()));
        });
        contractTradeDetail.updateTimes = updateTimes;
        contractTradeDetail.formatDates = formatDates;
        contractTradeDetail.yields = yields;
        contractTradeDetail.computeYield();
        contractTradeDetail.ddl = simpleDateFormat.format(ddl);
        contractTradeDetail.isEnd = (ddl.getTime() < now);
//        //重新查
//        List<Comment> comments = contract.getComments();
//        List<CommentModel> commentModels = comments.stream().map(comment -> {
//            CommentModel commentModel = new CommentModel();
//            BeanUtils.copyProperties(comment,commentModel);
//            return commentModel;
//        }).limit(10).collect(Collectors.toList());
//        contractTradeDetail.comments = commentModels;
        return contractTradeDetail;
    }

    private void copyProperties(Trade trade, ContractTradeDetail contractTradeDetail) {
        contractTradeDetail.riskLevel = trade.getRiskLevel();
        contractTradeDetail.investment = trade.getInvestment();
        contractTradeDetail.marketCapitalCapacity = trade.getMarketCapitalCapacity();
        contractTradeDetail.maxDrawdown = trade.getMaxDrawdown();
        contractTradeDetail.profitLossRatio = trade.getProfitLossRatio();
        contractTradeDetail.tradeId = trade.getTradeId();
        contractTradeDetail.winRate = trade.getWinRate();
        contractTradeDetail.createTime = trade.getCreateTime();
        Contract contract = trade.getContract();
        contractTradeDetail.contractId = contract.getContractId();
        contractTradeDetail.contractName = contract.getName();
    }


    @Override
    public void redeem(Long userId, Long tradeId) {
        Trade trade = tradeRepository.findByTradeId(tradeId);
        trade.setDeleted(true);
        trade.setDeleteTime(new Date(System.currentTimeMillis()));
        tradeRepository.save(trade);
    }
}
