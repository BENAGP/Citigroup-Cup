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

    public Double getYearYield() {
        return yearYield;
    }

    public void setYearYield(Double yearYield) {
        this.yearYield = yearYield;
    }

    public Double getMaxDrawdown() {
        return maxDrawdown;
    }

    public void setMaxDrawdown(Double maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public Double getWinRate() {
        return winRate;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public Double getProfitLossRatio() {
        return profitLossRatio;
    }

    public void setProfitLossRatio(Double profitLossRatio) {
        this.profitLossRatio = profitLossRatio;
    }

    public Double getMarketCapitalCapacity() {
        return marketCapitalCapacity;
    }

    public void setMarketCapitalCapacity(Double marketCapitalCapacity) {
        this.marketCapitalCapacity = marketCapitalCapacity;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public List<Date> getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(List<Date> updateTimes) {
        this.updateTimes = updateTimes;
    }

    public List<String> getFormatDates() {
        return formatDates;
    }

    public void setFormatDates(List<String> formatDates) {
        this.formatDates = formatDates;
    }

    public List<Double> getYields() {
        return yields;
    }

    public void setYields(List<Double> yields) {
        this.yields = yields;
    }

    public List<Double> getPositions() {
        return positions;
    }

    public void setPositions(List<Double> positions) {
        this.positions = positions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDdl() {
        return ddl;
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Integer getNearbyFuturesPosition() {
        return nearbyFuturesPosition;
    }

    public void setNearbyFuturesPosition(Integer nearbyFuturesPosition) {
        this.nearbyFuturesPosition = nearbyFuturesPosition;
    }

    public Integer getBackFuturesPosition() {
        return backFuturesPosition;
    }

    public void setBackFuturesPosition(Integer backFuturesPosition) {
        this.backFuturesPosition = backFuturesPosition;
    }

    public Double getTodayProfitLoss() {
        return todayProfitLoss;
    }

    public void setTodayProfitLoss(Double todayProfitLoss) {
        this.todayProfitLoss = todayProfitLoss;
    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    public Float getNearbyFuturesPrice() {
        return nearbyFuturesPrice;
    }

    public void setNearbyFuturesPrice(Float nearbyFuturesPrice) {
        this.nearbyFuturesPrice = nearbyFuturesPrice;
    }

    public Float getBackFuturesPrice() {
        return backFuturesPrice;
    }

    public void setBackFuturesPrice(Float backFuturesPrice) {
        this.backFuturesPrice = backFuturesPrice;
    }

    public String getNearbyFuturesName() {
        return nearbyFuturesName;
    }

    public void setNearbyFuturesName(String nearbyFuturesName) {
        this.nearbyFuturesName = nearbyFuturesName;
    }

    public String getBackFuturesName() {
        return backFuturesName;
    }

    public void setBackFuturesName(String backFuturesName) {
        this.backFuturesName = backFuturesName;
    }

    public List<Integer> getNearbyFuturesPositionOperations() {
        return nearbyFuturesPositionOperations;
    }

    public void setNearbyFuturesPositionOperations(List<Integer> nearbyFuturesPositionOperations) {
        this.nearbyFuturesPositionOperations = nearbyFuturesPositionOperations;
    }

    public List<Integer> getBackFuturesPositionOperations() {
        return backFuturesPositionOperations;
    }

    public void setBackFuturesPositionOperations(List<Integer> backFuturesPositionOperations) {
        this.backFuturesPositionOperations = backFuturesPositionOperations;
    }
}
