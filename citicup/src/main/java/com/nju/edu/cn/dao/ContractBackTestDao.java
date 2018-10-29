package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.ContractBackTestParams;
import com.nju.edu.cn.model.ContractBackTestBean;
import com.nju.edu.cn.model.ContractBackTestParamsBean;
import com.nju.edu.cn.model.ContractTradeModel;
import com.nju.edu.cn.model.ContractTradeSearch;

import java.util.List;

/**
 * Created by shea on 2018/10/25.
 */
public interface ContractBackTestDao {
    void insertList(List<ContractBackTestBean> contractBackTests);
    void insertParamsList(List<ContractBackTestParamsBean> contractBackTestParamsBeans);
    List<ContractTradeModel> getList(Integer riskLevel, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum);

}
