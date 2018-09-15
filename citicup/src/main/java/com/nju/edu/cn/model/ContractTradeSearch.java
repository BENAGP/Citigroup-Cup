package com.nju.edu.cn.model;

import com.nju.edu.cn.constant.FuturesType;

/**
 * Created by shea on 2018/9/2.
 * 合约列表筛选条件
 */
public class ContractTradeSearch {
    /**
     * 收益率
     */
    public Double yieldL;

    /**
     * 最大回撤
     */
    public Double maxDrawdownL;

    /**
     * 胜率
     */
    public Double winRateL;

    /**
     * 盈亏比
     */
    public Double profitLossRatioL;

    /**
     * 市场最大承载资金
     */
    public Double marketCapitalCapacityL;

    /**
     * 收益率
     */
    public Double yieldR;

    /**
     * 最大回撤
     */
    public Double maxDrawdownR;

    /**
     * 胜率
     */
    public Double winRateR;

    /**
     * 盈亏比
     */
    public Double profitRossRatioR;

    /**
     * 市场最大承载资金
     */
    public Double marketCapitalCapacityR;

    /**
     * 期货类型
     */
    public Integer type;

    public ContractTradeSearch() {
    }

    public void setYieldL(Double yieldL) {
        this.yieldL = yieldL;
    }

    public void setMaxDrawdownL(Double maxDrawdownL) {
        this.maxDrawdownL = maxDrawdownL;
    }

    public void setWinRateL(Double winRateL) {
        this.winRateL = winRateL;
    }

    public void setProfitLossRatioL(Double profitLossRatioL) {
        this.profitLossRatioL = profitLossRatioL;
    }

    public void setMarketCapitalCapacityL(Double marketCapitalCapacityL) {
        this.marketCapitalCapacityL = marketCapitalCapacityL;
    }

    public void setYieldR(Double yieldR) {
        this.yieldR = yieldR;
    }

    public void setMaxDrawdownR(Double maxDrawdownR) {
        this.maxDrawdownR = maxDrawdownR;
    }

    public void setWinRateR(Double winRateR) {
        this.winRateR = winRateR;
    }

    public void setProfitRossRatioR(Double profitRossRatioR) {
        this.profitRossRatioR = profitRossRatioR;
    }

    public void setMarketCapitalCapacityR(Double marketCapitalCapacityR) {
        this.marketCapitalCapacityR = marketCapitalCapacityR;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void checkNullValue(){
        Double minF = Double.MIN_VALUE;
        Double maxF = Double.MAX_VALUE;
//        yieldL = yieldL==null?minF:yieldL;
//        yieldR = yieldR==null?maxF:yieldR;
//        maxDrawdownL = maxDrawdownL==null?minF:maxDrawdownL;
//        maxDrawdownR = maxDrawdownR==null?maxF:maxDrawdownR;
//        winRateL = winRateL==null?minF:winRateL;
//        winRateR = winRateR==null?maxF:winRateR;
//        profitLossRatioL = profitLossRatioL==null?minF:profitLossRatioL;
//        profitRossRatioR = profitRossRatioR==null?maxF:profitRossRatioR;
//        marketCapitalCapacityL = marketCapitalCapacityL==null?minF:marketCapitalCapacityL;
//        marketCapitalCapacityR = marketCapitalCapacityR==null?maxF:marketCapitalCapacityR;
        yieldL = minF;
        yieldR = maxF;
        maxDrawdownL = minF;
        maxDrawdownR = maxF;
        winRateL = minF;
        winRateR = maxF;
        profitLossRatioL = minF;
        profitRossRatioR = maxF;
        marketCapitalCapacityL = minF;
        marketCapitalCapacityR = maxF;
        type = type==null? FuturesType.ALL:type;
    }

}
