package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.ContractBackTestParams;
import com.nju.edu.cn.model.*;

import java.util.List;

/**
 * Created by shea on 2018/10/25.
 */
public interface ContractBackTestDao {
    void insertList(List<ContractBackTestBean> contractBackTests);
    void insertParamsList(List<ContractBackTestParamsBean> contractBackTestParamsBeans);
    List<ContractTradeModel> getList(Long userId,Integer riskLevel, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum);
    ContractTradeDetail getDetail(Long userId, Long contractId, Integer riskLevel);
    ContractTradeDetail getDetail(Long userId, Long tradeId);
    HistoryMarket getHistoryMarket(Long userId, Long contractId);

}
