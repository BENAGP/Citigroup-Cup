package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.FuturesUpdating;

/**
 * Created by shea on 2018/10/19.
 */
public class FuturesUpdatingBean extends FuturesUpdating {
    private String timeStr;

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Long futuresId;

    public Long getFuturesId() {
        return futuresId;
    }

    public void setFuturesId(Long futuresId) {
        this.futuresId = futuresId;
    }

}
