package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/6.
 */
@Entity
@Table(name = "`basic_profit`")
public class BasicProfit {
    @Id
    @Column(name = "`basic_profit_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long basicProfitId;

    @Column(name = "`update_time`")
    Date updateTime;

    @Column(name = "`profit_rate`")
    Float profitRate;

    public Long getBasicProfitId() {
        return basicProfitId;
    }

    public void setBasicProfitId(Long basicProfitId) {
        this.basicProfitId = basicProfitId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Float getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Float profitRate) {
        this.profitRate = profitRate;
    }
}
