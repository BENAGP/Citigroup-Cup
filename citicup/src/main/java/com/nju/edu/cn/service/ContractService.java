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

    public void buy(Long userId,Long contractId,Float investment,Integer riskLevel);

    public ContractTradeDetail getDetail(Long userId, Long contractId);

    public void redeem(Long userId,Long contractId);
}
