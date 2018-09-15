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

    public List<String> formatDates;

    /**
     * 收益率纵轴
     */
    public List<Double> yields;

    /**
     * 资金占用纵轴
     */
    public List<Double> positions;

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

    //=====================当前持仓=========================
    /**
     * 近月期货持有数量
     */
    public Integer nearbyFuturesPosition;
    /**
     * 远月期货持有数量
     */
    public Integer backFuturesPosition;
    /**
     * 当日盈亏
     */
    public Double todayProfitLoss;
    /**
     * 仓位
     */
    public Double position;
    /**
     * 近月期货市值
     */
    public Float nearbyFuturesPrice;
    /**
     * 远月期货市值
     */
    public Float backFuturesPrice;

    /**
     * 近月期货名称
     */
    public String nearbyFuturesName;
    /**
     * 远月期货名称
     */
    public String backFuturesName;

    //=================历史调仓================
    public List<Integer> nearbyFuturesPositionOperations;
    public List<Integer> backFuturesPositionOperations;


    public ContractTradeModel() {
    }

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
