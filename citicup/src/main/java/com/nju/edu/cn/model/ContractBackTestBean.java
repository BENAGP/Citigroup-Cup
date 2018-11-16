package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.ContractBackTest;

/**
 * Created by shea on 2018/10/25.
 */
public class ContractBackTestBean extends ContractBackTest {
    private String createTimeStr;

    private Long tradeId;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
