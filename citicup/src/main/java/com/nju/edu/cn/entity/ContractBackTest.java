package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 正在回测的合约表
 */
@Entity
@Table(name = "`contract_back_test`")
public class ContractBackTest {
    @Id
    @Column(name = "`contract_trade_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractTradeId;

    /**
     * 收益率
     */
    @Column(name = "`yield`")
    private Float yield;

    /**
     * 最大回撤
     */
    @Column(name = "`max_drawdown`")
    private Float maxDrawdown;

    /**
     * 胜率
     */
    @Column(name = "`win_rate`")
    private Float winRate;

    /**
     * 盈亏比
     */
    @Column(name = "`profit_loss_ratio`")
    private Float profitLossRatio;

    /**
     * 流动性
     */
    @Column(name = "`liquidity`")
    private Float liquidity;

    /**
     * 市场最大承载资金
     */
    @Column(name = "`market_capital_capacity`")
    private Float marketCapitalCapacity;

    /**
     * 仓位
     */
    @Column(name = "`position`")
    private Float position;

    /**
     * 当日盈亏
     */
    @Column(name = "`today_profit_loss`")
    private Float todayProfitLoss;

    /**
     * 成交均价
     */
    @Column(name = "`average_trading_price`")
    private Float averageTradingPrice;

    /**
     * 是否调仓
     */
    @Column(name = "`do_adjust_warehouse`")
    private Boolean doAdjustWarehouse;

    /**
     * 近月期货持有数量
     */
    @Column(name = "`nearby_futures_position`")
    private Integer nearbyFuturesPosition;

    /**
     * 远月期货持有数量
     */
    @Column(name = "`back_futures_position`")
    private Integer backFuturesPosition;

    //稳定分布的四个形状参数（alpha、beta、gam、delta）
    @Column(name = "`alpha`")
    private Float alpha;
    @Column(name = "`beta`")
    private Float beta;
    @Column(name = "`gam`")
    private Float gam;
    @Column(name = "`delta`")
    private Float delta;

    /**
     * 对数利率之差
     */
    @Column(name = "`interest_rate_diff`")
    private Float interestRateDiff;

    /**
     * 更新时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 合约
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`trade_id`")
    private Trade trade;

    public Long getContractTradeId() {
        return contractTradeId;
    }

    public void setContractTradeId(Long contractTradeId) {
        this.contractTradeId = contractTradeId;
    }

    public Float getYield() {
        return yield;
    }

    public void setYield(Float yield) {
        this.yield = yield;
    }

    public Float getMaxDrawdown() {
        return maxDrawdown;
    }

    public void setMaxDrawdown(Float maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public Float getWinRate() {
        return winRate;
    }

    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }

    public Float getProfitLossRatio() {
        return profitLossRatio;
    }

    public void setProfitLossRatio(Float profitLossRatio) {
        this.profitLossRatio = profitLossRatio;
    }

    public Float getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Float liquidity) {
        this.liquidity = liquidity;
    }

    public Float getMarketCapitalCapacity() {
        return marketCapitalCapacity;
    }

    public void setMarketCapitalCapacity(Float marketCapitalCapacity) {
        this.marketCapitalCapacity = marketCapitalCapacity;
    }

    public Float getPosition() {
        return position;
    }

    public void setPosition(Float position) {
        this.position = position;
    }

    public Float getTodayProfitLoss() {
        return todayProfitLoss;
    }

    public void setTodayProfitLoss(Float todayProfitLoss) {
        this.todayProfitLoss = todayProfitLoss;
    }

    public Float getAverageTradingPrice() {
        return averageTradingPrice;
    }

    public void setAverageTradingPrice(Float averageTradingPrice) {
        this.averageTradingPrice = averageTradingPrice;
    }

    public Boolean getDoAdjustWarehouse() {
        return doAdjustWarehouse;
    }

    public void setDoAdjustWarehouse(Boolean doAdjustWarehouse) {
        this.doAdjustWarehouse = doAdjustWarehouse;
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

    public Float getAlpha() {
        return alpha;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }

    public Float getBeta() {
        return beta;
    }

    public void setBeta(Float beta) {
        this.beta = beta;
    }

    public Float getGam() {
        return gam;
    }

    public void setGam(Float gam) {
        this.gam = gam;
    }

    public Float getDelta() {
        return delta;
    }

    public void setDelta(Float delta) {
        this.delta = delta;
    }

    public Float getInterestRateDiff() {
        return interestRateDiff;
    }

    public void setInterestRateDiff(Float interestRateDiff) {
        this.interestRateDiff = interestRateDiff;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }
}
