package com.nju.edu.cn.dao;

import com.nju.edu.cn.model.ContractBackTestBean;
import com.nju.edu.cn.model.ContractBackTestParamsBean;
import com.nju.edu.cn.model.FuturesUpdatingBean;
import com.nju.edu.cn.util.SqlConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shea on 2018/10/25.
 */
public class ContractBackTestDaoImpl implements ContractBackTestDao {
    static Connection connection=null;//数据库连接
    static PreparedStatement statement=null;//句柄
    static {
        connection = SqlConnectUtil.getSqlConnect();
    }

    private void freeConnect(){       //释放连接
        try {
            SqlConnectUtil.close(connection, statement);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void insertList(List<ContractBackTestBean> contractBackTests) {
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = contractBackTests.size();
            int perPacket = length/10;
//            System.out.println(futuresUpdatingBeans.size());
            ContractBackTestBean contractBackTestBean;
//            int length = 2;
            int i=0,k=0;
            for(int j=0;j<11&&i<length;j++,i++){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `contract_back_test` (yield,max_drawdown,win_rate,profit_loss_ratio,position," +
                        "today_profit_loss,average_trading_price,do_adjust_warehouse,trade_id,back_futures_position," +
                        "nearby_futures_position,market_capital_capacity,create_time)  VALUES ");
                for(k=0;i<length-1&&k<perPacket-1;i++,k++){
                    contractBackTestBean = contractBackTests.get(i);
                    stringBuilder.append("("+contractBackTestBean.getYield()+","+contractBackTestBean.getMaxDrawdown()+","+contractBackTestBean.getWinRate()+","+contractBackTestBean.getProfitLossRatio()+","+contractBackTestBean.getPosition()+","+
                            contractBackTestBean.getTodayProfitLoss()+","+contractBackTestBean.getAverageTradingPrice()+","+contractBackTestBean.getDoAdjustWarehouse()+","+contractBackTestBean.getTradeId()+","+contractBackTestBean.getBackFuturesPosition()+","+
                            contractBackTestBean.getNearbyFuturesPosition()+","+contractBackTestBean.getMarketCapitalCapacity()+","+contractBackTestBean.getCreateTimeStr()+"),");
                }
                contractBackTestBean = contractBackTests.get(i);
                stringBuilder.append("("+contractBackTestBean.getYield()+","+contractBackTestBean.getMaxDrawdown()+","+contractBackTestBean.getWinRate()+","+contractBackTestBean.getProfitLossRatio()+","+contractBackTestBean.getPosition()+","+
                        contractBackTestBean.getTodayProfitLoss()+","+contractBackTestBean.getAverageTradingPrice()+","+contractBackTestBean.getDoAdjustWarehouse()+","+contractBackTestBean.getTradeId()+","+contractBackTestBean.getBackFuturesPosition()+","+
                        contractBackTestBean.getNearbyFuturesPosition()+","+contractBackTestBean.getMarketCapitalCapacity()+","+contractBackTestBean.getCreateTimeStr()+")");
                String sql = stringBuilder.toString();
                System.out.println("before");
//                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
                System.out.println("finish");
            }
            System.out.println("over");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            freeConnect();
        }
    }

    @Override
    public void insertParamsList(List<ContractBackTestParamsBean> contractBackTestParamsBeans) {
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = contractBackTestParamsBeans.size();
            int perPacket = length/2;
//            System.out.println(futuresUpdatingBeans.size());
            ContractBackTestParamsBean contractBackTestParamsBean;
//            int length = 2;
            int i=0,k=0;
            for(int j=0;j<3&&i<length;j++,i++){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `contract_back_test_params` (alpha,beta,gam,delta,interest_rate_diff,liquidity,contract_id,create_time)  VALUES ");
                for(k=0;i<length-1&&k<perPacket-1;i++,k++){
                    contractBackTestParamsBean = contractBackTestParamsBeans.get(i);
                    stringBuilder.append("("+contractBackTestParamsBean.getAlpha()+","+contractBackTestParamsBean.getBeta()+","+
                            contractBackTestParamsBean.getGam()+","+contractBackTestParamsBean.getDelta()+","+ contractBackTestParamsBean.getInterestRateDiff()+","+
                            contractBackTestParamsBean.getLiquidity()+","+contractBackTestParamsBean.getContractId()+","+contractBackTestParamsBean.getCreateTimeStr()+"),");
                }
                contractBackTestParamsBean = contractBackTestParamsBeans.get(i);
                stringBuilder.append("("+contractBackTestParamsBean.getAlpha()+","+contractBackTestParamsBean.getBeta()+","+
                        contractBackTestParamsBean.getGam()+","+contractBackTestParamsBean.getDelta()+","+ contractBackTestParamsBean.getInterestRateDiff()+","+
                        contractBackTestParamsBean.getLiquidity()+","+contractBackTestParamsBean.getContractId()+","+contractBackTestParamsBean.getCreateTimeStr()+")");
                String sql = stringBuilder.toString();
                System.out.println("before");
//                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
                System.out.println("finish");
            }
            System.out.println("over");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            freeConnect();
        }
    }
}
