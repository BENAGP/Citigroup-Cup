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

    public Double getYield() {
        return yield;
    }

    public Double getNear3WeekYield() {
        return near3WeekYield;
    }

    public Double getNear1WeekYield() {
        return near1WeekYield;
    }

    public Double getNear12WeekYield() {
        return near12WeekYield;
    }

    public Double getNear6WeekYield() {
        return near6WeekYield;
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

    public Float getLiquidity() {
        return liquidity;
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
