package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.SpotGoodsUpdating;
import com.nju.edu.cn.model.SpotGoodsUpdatingBean;

import java.util.List;

/**
 * Created by shea on 2018/10/19.
 */
public interface SpotGoodsUpdatingDao {
    void insertAll(List<SpotGoodsUpdatingBean> spotGoodsUpdatings);
}
