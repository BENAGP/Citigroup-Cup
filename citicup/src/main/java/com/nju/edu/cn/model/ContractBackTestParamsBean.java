package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.ContractBackTestParams;

/**
 * Created by shea on 2018/10/25.
 */
public class ContractBackTestParamsBean extends ContractBackTestParams {
    private Long contractId;
    private String createTimeStr;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
