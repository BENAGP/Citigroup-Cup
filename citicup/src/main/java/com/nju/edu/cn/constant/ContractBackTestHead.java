package com.nju.edu.cn.constant;

/**
 * Created by shea on 2018/9/10.
 */
public class ContractBackTestHead {
    //yield,max_drawdown,win_rate,profit_loss_ratio,position,
    // today_profit_loss,average_trading_price,do_adjust_warehouse,trade_id,back_futures_position,
    // nearby_futures_position,market_capital_capacity,creat_time
    private int yield = 0;
    private int max_drawdown = 1;
    private int win_rate = 2;
    private int profit_loss_ratio = 3;
    private int position = 4;
    private int today_profitloss = 5;
    private int average_trading_price = 6;
    private int do_adjust = 7;
    private int tradeid = 8;
    private int backposition = 9;
    private int nearbyposition = 10;
    private int market_capial_capacity = 11;
    private int creattime = 12;
//    private int fileBeginT = 101;
//    private int tableBeginT = 109;
//    private int gap = 109-101;

    public ContractBackTestHead(String path) {
        if(path.split("/")[path.split("/").length-1].equals("contract_back_test_xu.csv")){
            yield = 3;
            max_drawdown = 4;
            win_rate = 5;
            market_capial_capacity = 6;
            profit_loss_ratio = 7;
            position = 8;
            today_profitloss = 9;
            average_trading_price = 12;
            do_adjust = 13;
            nearbyposition = 14;
            backposition = 15;
            creattime = 16;
            tradeid = 18;
//            fileBeginT = 1;
//            tableBeginT = 1;
//            gap = 0;
        }
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

    public int getPosition() {
        return position;
    }

    public int getToday_profitloss() {
        return today_profitloss;
    }

    public int getAverage_trading_price() {
        return average_trading_price;
    }

    public int getDo_adjust() {
        return do_adjust;
    }

    public int getTradeid() {
        return tradeid;
    }

    public int getNearbyposition() {
        return nearbyposition;
    }

    public int getBackposition() {
        return backposition;
    }

    public int getMarket_capial_capacity() {
        return market_capial_capacity;
    }

    public int getCreattime() {
        return creattime;
    }

//    public int getGap() {
//        return gap;
//    }
}
