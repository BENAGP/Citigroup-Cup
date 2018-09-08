package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/8.
 * 不随风险等级更新的回测数据
 */
@Entity
@Table(name = "`contract_back_test_params`")
public class ContractBackTestParams {
    @Id
    @Column(name = "`contract_back_test_params_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractBackTestParamsId;

    //稳定分布的四个形状参数（alpha、beta、gam、delta）
    @Column(name = "`alpha`")
    private Double alpha;
    @Column(name = "`beta`")
    private Double beta;
    @Column(name = "`gam`")
    private Double gam;
    @Column(name = "`delta`")
    private Double delta;

    /**
     * 对数利率之差
     */
    @Column(name = "`interest_rate_diff`")
    private Double interestRateDiff;

    /**
     * 更新时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 流动性
     */
    @Column(name = "`liquidity`")
    private Float liquidity;

    /**
     * 被评论的合约ID
     */
    @ManyToOne()
    @JoinColumn(name = "`contract_id`")
    private Contract contract;


    public Long getContractBackTestParamsId() {
        return contractBackTestParamsId;
    }

    public void setContractBackTestParamsId(Long contractBackTestParamsId) {
        this.contractBackTestParamsId = contractBackTestParamsId;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getGam() {
        return gam;
    }

    public void setGam(Double gam) {
        this.gam = gam;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Double getInterestRateDiff() {
        return interestRateDiff;
    }

    public void setInterestRateDiff(Double interestRateDiff) {
        this.interestRateDiff = interestRateDiff;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Float liquidity) {
        this.liquidity = liquidity;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
