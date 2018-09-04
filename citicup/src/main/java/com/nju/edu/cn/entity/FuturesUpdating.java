package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 每分钟更新的期货表
 */
@Entity
@Table(name = "`futures_updating`")
public class FuturesUpdating {
    @Id
    @Column(name = "`futures_updating_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long futuresUpdatingId;

    /**
     * 历史价格
     */
    @Column(name = "`price`")
    private Float price;

    /**
     * 成交量
     */
    @Column(name = "`trading`")
    private Integer trading;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 期货ID
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`futures_id`")
    private Futures futures;

    /**
     * 对数利率
     */
    @Column(name = "`interest_rate`")
    private Float interestRate;

    public Long getFuturesUpdatingId() {
        return futuresUpdatingId;
    }

    public void setFuturesUpdatingId(Long futuresUpdatingId) {
        this.futuresUpdatingId = futuresUpdatingId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getTrading() {
        return trading;
    }

    public void setTrading(Integer trading) {
        this.trading = trading;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Futures getFutures() {
        return futures;
    }

    public void setFutures(Futures futures) {
        this.futures = futures;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }
}
