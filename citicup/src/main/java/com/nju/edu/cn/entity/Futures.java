package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/1.
 * 期货表
 */
@Entity
@Table(name = "`futures`")
public class Futures {
    @Id
    @Column(name = "`futures_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long futuresId;

    /**
     * 最后交易日（截止日）
     */
    @Column(name = "`last_trading_date`")
    private Date lastTradingDate;

    /**
     * 期货类型
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 期货每分钟更新的数据
     */
    @OneToMany(mappedBy = "futures",cascade = {CascadeType.PERSIST})
    private List<FuturesUpdating> futuresUpdatings;

    public Long getFuturesId() {
        return futuresId;
    }

    public void setFuturesId(Long futuresId) {
        this.futuresId = futuresId;
    }

    public Date getLastTradingDate() {
        return lastTradingDate;
    }

    public void setLastTradingDate(Date lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<FuturesUpdating> getFuturesUpdatings() {
        return futuresUpdatings;
    }

    public void setFuturesUpdatings(List<FuturesUpdating> futuresUpdatings) {
        this.futuresUpdatings = futuresUpdatings;
    }
}
