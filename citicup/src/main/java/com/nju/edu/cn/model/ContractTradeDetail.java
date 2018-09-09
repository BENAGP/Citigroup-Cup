package com.nju.edu.cn.model;

import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/2.
 */
public class ContractTradeDetail {

    /**
     * 收益率
     */
    public Double yield;

    public Double near3WeekYield;

    public Double near1WeekYield;

    public Double near12WeekYield;

    public Double near6WeekYield;

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
     * 流动性
     */
    public Float liquidity;

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
     * 合约的前十条评论
     */
    public List<CommentModel> comments;

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

    public ContractTradeDetail() {
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public void setNear3WeekYield(Double near3WeekYield) {
        this.near3WeekYield = near3WeekYield;
    }

    public void setNear1WeekYield(Double near1WeekYield) {
        this.near1WeekYield = near1WeekYield;
    }

    public void setNear12WeekYield(Double near12WeekYield) {
        this.near12WeekYield = near12WeekYield;
    }

    public void setNear6WeekYield(Double near6WeekYield) {
        this.near6WeekYield = near6WeekYield;
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

    public void setLiquidity(Float liquidity) {
        this.liquidity = liquidity;
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

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public void setIsEnd(Boolean isEnd) {
        isEnd = isEnd;
    }
}
