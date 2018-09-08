package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/8.
 * 每分钟更新的现货表
 */
@Entity
@Table(name = "`spot_goods_updating`")
public class SpotGoodsUpdating {
    @Id
    @Column(name = "`spot_goods_updating_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotGoodsUpdatingId;

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
     * 现货ID
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`spot_goods_id`")
    private SpotGoods spotGoods;

    public Long getSpotGoodsUpdatingId() {
        return spotGoodsUpdatingId;
    }

    public void setSpotGoodsUpdatingId(Long spotGoodsUpdatingId) {
        this.spotGoodsUpdatingId = spotGoodsUpdatingId;
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

    public SpotGoods getSpotGoods() {
        return spotGoods;
    }

    public void setSpotGoods(SpotGoods spotGoods) {
        this.spotGoods = spotGoods;
    }
}
