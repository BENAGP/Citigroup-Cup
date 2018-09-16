package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/3.
 * 合约交易表
 */
@Entity
@Table(name = "`trade`")
public class Trade {
    @Id
    @Column(name = "`trade_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    /**
     * 购买合约的用户
     */
    @ManyToOne()
    @JoinColumn(name = "`user_id`")
    private User user;

    /**
     * 被购买的合约
     */
    @ManyToOne()
    @JoinColumn(name = "`contract_id`")
    private Contract contract;

    /**
     * 投入资金
     * 是否应放到trade表里
     */
    @Column(name = "`investment`")
    private Double investment;

    /**
     * 风险等级，假如有十个风险等级，那么一个合约初始化的时候，backtest增加十条线，分别对应十个风险等级。然后每增加一个trade，backtest增加一条线
     * 是否应放到trade表里
     */
    @Column(name = "`risk_level`")
    private Integer riskLevel;

    /**
     * 累计收益率
     */
    @Column(name = "`yield`")
    private Double yield;

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
     * 合约被购买的时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 合约被赎回的时间
     */
    @Column(name = "`delete_time`")
    private Date deleteTime;

    /**
     * 合约有没有被赎回
     */
    @Column(name = "`deleted`")
    private Boolean deleted;

    /**
     * 此条交易每分钟更新的回测数据。如果合约被赎回，回测数据不再更新
     */
    @OneToMany(mappedBy = "trade")
    private List<ContractBackTest> contractBackTests;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<ContractBackTest> getContractBackTests() {
        return contractBackTests;
    }

    public void setContractBackTests(List<ContractBackTest> contractBackTests) {
        this.contractBackTests = contractBackTests;
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
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

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }
}
