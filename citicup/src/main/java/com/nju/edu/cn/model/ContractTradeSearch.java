package com.nju.edu.cn.model;

import com.nju.edu.cn.util.FuturesType;

import javax.persistence.Column;

/**
 * Created by shea on 2018/9/2.
 * 合约列表筛选条件
 */
public class ContractTradeSearch {
    /**
     * 收益率
     */
    public Float yieldL;

    /**
     * 最大回撤
     */
    public Float maxDrawdownL;

    /**
     * 胜率
     */
    public Float winRateL;

    /**
     * 盈亏比
     */
    public Float profitLossRatioL;

    /**
     * 流动性
     */
    public Float liquidityL;

    /**
     * 市场最大承载资金
     */
    public Float marketCapitalCapacityL;

    /**
     * 收益率
     */
    public Float yieldR;

    /**
     * 最大回撤
     */
    public Float maxDrawdownR;

    /**
     * 胜率
     */
    public Float winRateR;

    /**
     * 盈亏比
     */
    public Float profitRossRatioR;

    /**
     * 流动性
     */
    public Float liquidityR;

    /**
     * 市场最大承载资金
     */
    public Float marketCapitalCapacityR;

    /**
     * 期货类型
     */
    public Integer type;

    public void checkNullValue(){
        Float minF = Float.MIN_VALUE;
        Float maxF = Float.MAX_VALUE;
        yieldL = yieldL==null?minF:yieldL;
        yieldR = yieldR==null?maxF:yieldR;
        maxDrawdownL = maxDrawdownL==null?minF:maxDrawdownL;
        maxDrawdownR = maxDrawdownR==null?maxF:maxDrawdownR;
        winRateL = winRateL==null?minF:winRateL;
        winRateR = winRateR==null?maxF:winRateR;
        profitLossRatioL = profitLossRatioL==null?minF:profitLossRatioL;
        profitRossRatioR = profitRossRatioR==null?maxF:profitRossRatioR;
        liquidityL = liquidityL==null?minF:liquidityL;
        liquidityR = liquidityR==null?maxF:liquidityR;
        marketCapitalCapacityL = marketCapitalCapacityL==null?minF:marketCapitalCapacityL;
        marketCapitalCapacityR = marketCapitalCapacityR==null?maxF:marketCapitalCapacityR;
        type = type==null? FuturesType.ALL:type;
    }

}
