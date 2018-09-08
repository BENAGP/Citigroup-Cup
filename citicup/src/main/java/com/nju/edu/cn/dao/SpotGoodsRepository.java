package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.SpotGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/8.
 */
@Repository
public interface SpotGoodsRepository extends JpaRepository<SpotGoods,Long> {
    SpotGoods findBySpotGoodsId(Long sportGoodsId);
}
