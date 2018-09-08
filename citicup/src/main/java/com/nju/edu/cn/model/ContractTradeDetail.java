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
    public Float yield;

    public Float near3WeekYield;

    public Float near1WeekYield;

    public Float near12WeekYield;

    public Float near6WeekYield;

    /**
     * 最大回撤
     */
    public Float maxDrawdown;

    /**
     * 胜率
     */
    public Float winRate;

    /**
     * 盈亏比
     */
    public Float profitLossRatio;

    /**
     * 流动性
     */
    public Float liquidity;

    /**
     * 市场最大承载资金
     */
    public Float marketCapitalCapacity;

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
    public Float investment;

    /**
     * 时间横轴
     */
    public List<Date> updateTimes;

    /**
     * 收益率纵轴
     */
    public List<Float> yields;

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

    public Float getYield() {
        return yield;
    }

    public Float getNear3WeekYield() {
        return near3WeekYield;
    }

    public Float getNear1WeekYield() {
        return near1WeekYield;
    }

    public Float getNear12WeekYield() {
        return near12WeekYield;
    }

    public Float getNear6WeekYield() {
        return near6WeekYield;
    }

    public Float getMaxDrawdown() {
        return maxDrawdown;
    }

    public Float getWinRate() {
        return winRate;
    }

    public Float getProfitLossRatio() {
        return profitLossRatio;
    }

    public Float getLiquidity() {
        return liquidity;
    }

    public Float getMarketCapitalCapacity() {
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

    public Float getInvestment() {
        return investment;
    }

    public List<Date> getUpdateTimes() {
        return updateTimes;
    }

    public List<Float> getYields() {
        return yields;
    }

    public Date getCreateTime() {
        return createTime;
    }


}
