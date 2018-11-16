package com.nju.edu.cn.dao;

import com.nju.edu.cn.model.FuturesUpdatingBean;

import java.util.List;

/**
 * Created by shea on 2018/10/19.
 */
public interface FuturesUpdatingDao {
    List<FuturesUpdatingBean> selectByFuturesId(Long futuresId);
    void updateList(List<FuturesUpdatingBean> futuresUpdatingBeans);
    void insertList(List<FuturesUpdatingBean> futuresUpdatingBeans);
}
