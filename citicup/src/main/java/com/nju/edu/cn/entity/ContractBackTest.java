package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 正在回测的合约表,每增加一条trade记录，ContractBackTest新增一条线
 */
@Entity
@Table(name = "`contract_back_test`")
public class ContractBackTest {
    @Id
    @Column(name = "`contract_trade_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractTradeId;

    /**
     * 累计收益率
     */
    @Column(name = "`yield`")
    private Double yield;

    /**
     * 仓位
     */
    @Column(name = "`position`")
    private Double position;

    /**
     * 当日盈亏
     */
    @Column(name = "`today_profit_loss`")
    private Double todayProfitLoss;

    /**
     * 成交均价
     */
    @Column(name = "`average_trading_price`")
    private Double averageTradingPrice;

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

    /**
     * 最大回撤
     */
    @Column(name = "`max_drawdown`")
    private Double maxDrawdown;

    /**
     * 胜率
     */
    @Column(name = "`win_rate`")
    private Double winRate;

    /**
     * 盈亏比
     */
    @Column(name = "`profit_loss_ratio`")
    private Double profitLossRatio;

    /**
     * 市场最大承载资金
     */
    @Column(name = "`market_capital_capacity`")
    private Double marketCapitalCapacity;

    /**
     * 更新时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 交易
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

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }

    public Double getTodayProfitLoss() {
        return todayProfitLoss;
    }

    public void setTodayProfitLoss(Double todayProfitLoss) {
        this.todayProfitLoss = todayProfitLoss;
    }

    public Double getAverageTradingPrice() {
        return averageTradingPrice;
    }

    public void setAverageTradingPrice(Double averageTradingPrice) {
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

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
