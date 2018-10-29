package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.Trade;

/**
 * Created by shea on 2018/10/28.
 */
public class TradeBean extends Trade {
    private Long contractId;
    private Long userId;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
