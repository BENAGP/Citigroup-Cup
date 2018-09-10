package com.nju.edu.cn.model;

import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/10.
 * 历史行情
 */
public class HistoryMarket {
    public Long contractId;
    public Long nearbyFuturesId;
    public Long backFuturesId;

    public List<Date> updateTimes;

    public List<Float> nearbyPrices;
    public List<Float> backPrices;

    public List<Integer> nearbyTradings;
    public List<Integer> backTradings;

    public HistoryMarket() {
    }
}
