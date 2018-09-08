package com.nju.edu.cn.entity;

import javax.persistence.*;

/**
 * Created by shea on 2018/9/8.
 * 现货表
 */
@Entity
@Table(name = "`spot_goods`")
public class SpotGoods {
    @Id
    @Column(name = "`spot_goods_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotGoodsId;
    /**
     * 现货品种（IF/IH）
     */
    @Column(name = "`variety`")
    private Integer variety;


    /**
     * 现货类型(金融／农产品)
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 现货名称（IF1706）
     */
    @Column(name = "`name`")
    private String name;

    public Long getSpotGoodsId() {
        return spotGoodsId;
    }

    public void setSpotGoodsId(Long spotGoodsId) {
        this.spotGoodsId = spotGoodsId;
    }

    public Integer getVariety() {
        return variety;
    }

    public void setVariety(Integer variety) {
        this.variety = variety;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
