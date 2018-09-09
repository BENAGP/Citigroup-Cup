package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.ContractBackTest;
import com.nju.edu.cn.entity.Trade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/2.
 */
public class ContractTradeModel {
    /**
     * 收益率
     */
    public Double yield;

    /**
     * 最大回撤
     */
    public Double maxDrawdown;

    /**
     * 胜率
     */
    public Double winRate;

    /**
     * 盈亏比
     */
    public Double profitLossRatio;

    /**
     * 市场最大承载资金
     */
    public Double marketCapitalCapacity;

    /**
     * 合约ID
     */
    public Long contractId;

    public Long tradeId;

    /**
     * 风险等级
     */
    public Integer riskLevel;

    /**
     * 投入资金
     */
    public Double investment;

    /**
     * 时间横轴
     */
    public List<Date> updateTimes;

    /**
     * 收益率纵轴
     */
    public List<Double> yields;

    /**
     * 合约被购买的时间
     */
    public Date createTime;

    /**
     * 合约交易截止日
     * 收藏列表提醒用户到期 取消收藏
     */
    public Date ddl;

    /**
     * 是否过期
     */
    public Boolean isEnd;

    public Double getYield() {
        return yield;
    }

    public Double getMaxDrawdown() {
        return maxDrawdown;
    }

    public Double getWinRate() {
        return winRate;
    }

    public Double getProfitLossRatio() {
        return profitLossRatio;
    }

    public Double getMarketCapitalCapacity() {
        return marketCapitalCapacity;
    }

    public Long getContractId() {
        return contractId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public Double getInvestment() {
        return investment;
    }

    public List<Date> getUpdateTimes() {
        return updateTimes;
    }

    public List<Double> getYields() {
        return yields;
    }

    public Date getCreateTime() {
        return createTime;
    }


}
