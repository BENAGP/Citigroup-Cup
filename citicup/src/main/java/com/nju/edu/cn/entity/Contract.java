package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by shea on 2018/9/1.
 * 合约表
 */
@Entity
@Table(name = "`contract`")
public class Contract {
    @Id
    @Column(name = "`contract_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    /**
     * 近月期货
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`nearby_futures_id`")
    private Futures nearbyFutures;

    /**
     * 远月期货
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`back_futures_id`")
    private Futures backFutures;

    /**
     * 合约名称（IF1706-IF1703）
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 合约的交易，进一步应该筛出deleted=false的
     */
    @OneToMany(mappedBy = "contract")
    private List<Trade> trades;

    /**
     * 合约的评论
     */
    @OneToMany(mappedBy = "contract")
    private List<Comment> comments;


    /**
     * 合约的评论
     */
    @OneToMany(mappedBy = "contract")
    private List<ContractBackTestParams> contractBackTestParams;


    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Futures getNearbyFutures() {
        return nearbyFutures;
    }

    public void setNearbyFutures(Futures nearbyFutures) {
        this.nearbyFutures = nearbyFutures;
    }

    public Futures getBackFutures() {
        return backFutures;
    }

    public void setBackFutures(Futures backFutures) {
        this.backFutures = backFutures;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContractBackTestParams> getContractBackTestParams() {
        return contractBackTestParams;
    }

    public void setContractBackTestParams(List<ContractBackTestParams> contractBackTestParams) {
        this.contractBackTestParams = contractBackTestParams;
    }
}
