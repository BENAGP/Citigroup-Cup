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
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`user_id`")
    private User user;

    /**
     * 被购买的合约
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`contract_id`")
    private Contract contract;

    /**
     * 投入资金
     * todo 是否应放到trade表里
     */
    @Column(name = "`investment`")
    private Float investment;

    /**
     * 风险等级
     * todo 是否应放到trade表里
     */
    @Column(name = "`risk_level`")
    private Integer riskLevel;

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
    @OneToMany(mappedBy = "trade",cascade = {CascadeType.PERSIST})
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

    public Float getInvestment() {
        return investment;
    }

    public void setInvestment(Float investment) {
        this.investment = investment;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }
}
