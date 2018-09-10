package com.nju.edu.cn.model;

import com.nju.edu.cn.constant.Constant;
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
    public Double yearYield;

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

    public String contractName;

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
     * 资金占用纵轴
     */
    public List<Double> fundOccupation;

    /**
     * 合约被购买的时间
     */
    public Date createTime;

//    /**
//     * 此合约是否被收藏
//     */
//    public Boolean isCollected;

    /**
     * 合约交易截止日
     * 收藏列表提醒用户到期 取消收藏
     */
    public Date ddl;

    /**
     * 是否过期
     */
    public Boolean isEnd;

    public ContractTradeModel() {
    }

    public void setMaxDrawdown(Double maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public void setProfitLossRatio(Double profitLossRatio) {
        this.profitLossRatio = profitLossRatio;
    }

    public void setMarketCapitalCapacity(Double marketCapitalCapacity) {
        this.marketCapitalCapacity = marketCapitalCapacity;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public void setUpdateTimes(List<Date> updateTimes) {
        this.updateTimes = updateTimes;
    }

    public void setYields(List<Double> yields) {
        this.yields = yields;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setIsEnd(Boolean isEnd) {
        isEnd = isEnd;
    }

//    public void setIsCollected(Boolean isCollected) {
//        this.isCollected = isCollected;
//    }

    public void computeYield(){
        int minutesIn1Week = Constant.MINUTES_PER_WEEK;
        int last = yields.size()-1;
        int aWeekAgo = last-minutesIn1Week;
        if(aWeekAgo>=0){
            Double near1WeekYield = yields.get(last)-yields.get(aWeekAgo);
            this.yearYield = near1WeekYield*Constant.WEEKS_PER_YEER;
        }

    }
}
