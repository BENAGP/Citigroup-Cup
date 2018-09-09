package com.nju.edu.cn.service;

import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import com.nju.edu.cn.model.ContractTradeSearch;

import java.util.List;

/**
 * Created by shea on 2018/9/6.
 */
public interface ContractService {
    public List<ContractTradeModel> getCollectList(Long userId, Integer page, Integer pageNum);

    public void collect(Long userId,Long contractId);

    public void cancelCollect(Long userId,Long contractId);

    public List<ContractTradeModel> getList(Long userId, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum);

    public void buy(Long userId,Long contractId,Double investment,Integer riskLevel);

    public ContractTradeDetail getDetail(Long userId, Long tradeId);

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
