package com.nju.edu.cn.constant;

/**
 * Created by shea on 2018/9/10.
 */
public class TradeHead {
    private int tradeid = 0;
    private int contractid = 1;
    private int risklevel = 2;
    private int yield = 3;
    private int max_drawdown = 4;
    private int win_rate = 5;
    private int profit_loss_ratio = 6;
    private int market_capial_capacity = 7;
    private int create_time = 8;


    public TradeHead(String path) {
        if(path.split("/")[path.split("/").length-1].equals("trade_xu.csv")){
             tradeid = 0;
             market_capial_capacity = 2;
             risklevel = 3;
             max_drawdown = 5;
             win_rate = 6;
             profit_loss_ratio = 7;
             yield = 8;
             create_time = 10;
             contractid = 11;

        }
    }

    public int getTradeid() {
        return tradeid;
    }

    public int getContractid() {
        return contractid;
    }

    public int getRisklevel() {
        return risklevel;
    }

    public int getYield() {
        return yield;
    }

    public int getMax_drawdown() {
        return max_drawdown;
    }

    public int getWin_rate() {
        return win_rate;
    }

    public int getProfit_loss_ratio() {
        return profit_loss_ratio;
    }

    public int getMarket_capial_capacity() {
        return market_capial_capacity;
    }

    public int getCreate_time() {
        return create_time;
    }

}
