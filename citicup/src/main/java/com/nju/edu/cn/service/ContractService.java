package com.nju.edu.cn.service;

import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import com.nju.edu.cn.model.ContractTradeSearch;
import com.nju.edu.cn.model.HistoryMarket;

import java.util.List;
import java.util.Map;

/**
 * Created by shea on 2018/9/6.
 */
public interface ContractService {
    public List<ContractTradeModel> getCollectList(Long userId, Integer page, Integer pageNum);

    public void collect(Long userId,Long contractId);

    public void cancelCollect(Long userId,Long contractId);

    public List<ContractTradeModel> getMyTradeList(Long userId,Integer page,Integer pageNum);

    public List<ContractTradeModel> getList(Long userId, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum);

    public Map<Long,Boolean> isCollected(Long userId,List<Long> contractIds);

    public void buy(Long userId,Long contractId,Double investment,Integer riskLevel);

    public ContractTradeDetail getDetail(Long userId, Long tradeId);

    public double getLiquidity(Long userId,Long contractId);

    public HistoryMarket getHistoryMarket(Long userId, Long contractId);

    /**
     * 在详情页切换风险等级的时候调用此方法
     * @param userId
     * @param contractId
     * @param riskLevel
     * @return
     */
    public ContractTradeDetail getDetail(Long userId,Long contractId,Integer riskLevel);

    public void redeem(Long userId,Long tradeId);
}
