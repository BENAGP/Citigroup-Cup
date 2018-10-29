package com.nju.edu.cn.dao;

import com.nju.edu.cn.constant.FuturesType;
import com.nju.edu.cn.entity.Trade;
import com.nju.edu.cn.model.*;
import com.nju.edu.cn.util.SqlConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/10/25.
 */
public class ContractBackTestDaoImpl implements ContractBackTestDao {
    static Connection connection = null;//数据库连接
    static PreparedStatement statement = null;//句柄
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ResultSet resultSet = null;

    static {
        connection = SqlConnectUtil.getSqlConnect();
    }

    private void freeConnect() {       //释放连接
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
            int perPacket = length / 10;
//            System.out.println(futuresUpdatingBeans.size());
            ContractBackTestBean contractBackTestBean;
//            int length = 2;
            int i = 0, k = 0;
            for (int j = 0; j < 11 && i < length; j++, i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `contract_back_test` (yield,max_drawdown,win_rate,profit_loss_ratio,position," +
                        "today_profit_loss,average_trading_price,do_adjust_warehouse,trade_id,back_futures_position," +
                        "nearby_futures_position,market_capital_capacity,create_time)  VALUES ");
                for (k = 0; i < length - 1 && k < perPacket - 1; i++, k++) {
                    contractBackTestBean = contractBackTests.get(i);
                    stringBuilder.append("(" + contractBackTestBean.getYield() + "," + contractBackTestBean.getMaxDrawdown() + "," + contractBackTestBean.getWinRate() + "," + contractBackTestBean.getProfitLossRatio() + "," + contractBackTestBean.getPosition() + "," +
                            contractBackTestBean.getTodayProfitLoss() + "," + contractBackTestBean.getAverageTradingPrice() + "," + contractBackTestBean.getDoAdjustWarehouse() + "," + contractBackTestBean.getTradeId() + "," + contractBackTestBean.getBackFuturesPosition() + "," +
                            contractBackTestBean.getNearbyFuturesPosition() + "," + contractBackTestBean.getMarketCapitalCapacity() + "," + contractBackTestBean.getCreateTimeStr() + "),");
                }
                contractBackTestBean = contractBackTests.get(i);
                stringBuilder.append("(" + contractBackTestBean.getYield() + "," + contractBackTestBean.getMaxDrawdown() + "," + contractBackTestBean.getWinRate() + "," + contractBackTestBean.getProfitLossRatio() + "," + contractBackTestBean.getPosition() + "," +
                        contractBackTestBean.getTodayProfitLoss() + "," + contractBackTestBean.getAverageTradingPrice() + "," + contractBackTestBean.getDoAdjustWarehouse() + "," + contractBackTestBean.getTradeId() + "," + contractBackTestBean.getBackFuturesPosition() + "," +
                        contractBackTestBean.getNearbyFuturesPosition() + "," + contractBackTestBean.getMarketCapitalCapacity() + "," + contractBackTestBean.getCreateTimeStr() + ")");
                String sql = stringBuilder.toString();
                System.out.println("before");
//                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
                System.out.println("finish");
            }
            System.out.println("over");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeConnect();
        }
    }

    @Override
    public void insertParamsList(List<ContractBackTestParamsBean> contractBackTestParamsBeans) {
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = contractBackTestParamsBeans.size();
            int perPacket = length / 2;
//            System.out.println(futuresUpdatingBeans.size());
            ContractBackTestParamsBean contractBackTestParamsBean;
//            int length = 2;
            int i = 0, k = 0;
            for (int j = 0; j < 3 && i < length; j++, i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `contract_back_test_params` (alpha,beta,gam,delta,interest_rate_diff,liquidity,contract_id,create_time)  VALUES ");
                for (k = 0; i < length - 1 && k < perPacket - 1; i++, k++) {
                    contractBackTestParamsBean = contractBackTestParamsBeans.get(i);
                    stringBuilder.append("(" + contractBackTestParamsBean.getAlpha() + "," + contractBackTestParamsBean.getBeta() + "," +
                            contractBackTestParamsBean.getGam() + "," + contractBackTestParamsBean.getDelta() + "," + contractBackTestParamsBean.getInterestRateDiff() + "," +
                            contractBackTestParamsBean.getLiquidity() + "," + contractBackTestParamsBean.getContractId() + "," + contractBackTestParamsBean.getCreateTimeStr() + "),");
                }
                contractBackTestParamsBean = contractBackTestParamsBeans.get(i);
                stringBuilder.append("(" + contractBackTestParamsBean.getAlpha() + "," + contractBackTestParamsBean.getBeta() + "," +
                        contractBackTestParamsBean.getGam() + "," + contractBackTestParamsBean.getDelta() + "," + contractBackTestParamsBean.getInterestRateDiff() + "," +
                        contractBackTestParamsBean.getLiquidity() + "," + contractBackTestParamsBean.getContractId() + "," + contractBackTestParamsBean.getCreateTimeStr() + ")");
                String sql = stringBuilder.toString();
                System.out.println("before");
//                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
                System.out.println("finish");
            }
            System.out.println("over");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            freeConnect();
        }
    }

    @Override
    public List<ContractTradeModel> getList(Integer riskLevel, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum) {

        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        try {
            connection = SqlConnectUtil.getSqlConnect();
            String selectSql = "select t.trade_id as trade_id,t.market_capital_capacity as market_capital_capacity," +
                    "t.max_drawdown as max_drawdown,t.win_rate as win_rate,t.profit_loss_ratio as profit_loss_ratio," +
                    "t.contract_id as contract_id, c.`name` as contract_name " +
                    "from trade t left join contract c on t.contract_id=c.contract_id " +
                    "where t.deleted=0 and t.risk_level=" + riskLevel;
            if (contractTradeSearch.type != null && contractTradeSearch.type != FuturesType.ALL) {
                selectSql = selectSql + " and c.`type`=" + contractTradeSearch.type;
            }
            if (contractTradeSearch.marketCapitalCapacityR != null && Double.isFinite(contractTradeSearch.marketCapitalCapacityR)) {
                selectSql = selectSql + " and t.market_capital_capacity<=" + contractTradeSearch.marketCapitalCapacityR;
            }
            if (contractTradeSearch.marketCapitalCapacityL != null && Double.isFinite(contractTradeSearch.marketCapitalCapacityL)) {
                selectSql = selectSql + " and t.market_capital_capacity>=" + contractTradeSearch.marketCapitalCapacityL;
            }
            if (contractTradeSearch.profitRossRatioR != null && Double.isFinite(contractTradeSearch.profitRossRatioR)) {
                selectSql = selectSql + " and t.profit_loss_ratio<=" + contractTradeSearch.profitRossRatioR;
            }
            if (contractTradeSearch.profitLossRatioL != null && Double.isFinite(contractTradeSearch.profitLossRatioL)) {
                selectSql = selectSql + " and t.profit_loss_ratio>=" + contractTradeSearch.profitLossRatioL;
            }
            if (contractTradeSearch.winRateR != null && Double.isFinite(contractTradeSearch.winRateR)) {
                selectSql = selectSql + " and t.win_rate<=" + contractTradeSearch.winRateR;
            }
            if (contractTradeSearch.winRateL != null && Double.isFinite(contractTradeSearch.winRateL)) {
                selectSql = selectSql + " and t.win_rate>=" + contractTradeSearch.winRateL;
            }
            if (contractTradeSearch.maxDrawdownR != null && Double.isFinite(contractTradeSearch.maxDrawdownR)) {
                selectSql = selectSql + " and t.max_drawdown<=" + contractTradeSearch.maxDrawdownR;
            }
            if (contractTradeSearch.maxDrawdownL != null && Double.isFinite(contractTradeSearch.maxDrawdownL)) {
                selectSql = selectSql + " and t.max_drawdown>=" + contractTradeSearch.maxDrawdownL;
            }
            if (contractTradeSearch.yieldR != null && Double.isFinite(contractTradeSearch.yieldR)) {
                selectSql = selectSql + " and t.yield<=" + contractTradeSearch.yieldR;
            }
            if (contractTradeSearch.yieldL != null && Double.isFinite(contractTradeSearch.yieldL)) {
                selectSql = selectSql + " and t.yield>=" + contractTradeSearch.yieldL;
            }
            selectSql = selectSql + " limit "+6+";";
//            selectSql = selectSql + " limit "+pageNum+";";
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            ResultSet innerResult = null;
            while (resultSet.next()) {
                ContractTradeModel contractTradeModel = new ContractTradeModel();
                List<Double> yields = new ArrayList<>();//收益率纵轴
                List<Date> updateTimes = new ArrayList<>();// 时间横轴
                List<String> formatDates = new ArrayList<>();// 时间横轴
                setContractTradeModel(contractTradeModel,resultSet);
                contractTradeModel.setRiskLevel(riskLevel);
                selectSql = "select yield,create_time from contract_back_test where trade_id=" + contractTradeModel.getTradeId();
                statement = connection.prepareStatement(selectSql);
                innerResult = statement.executeQuery();
                while (innerResult.next()){
                    yields.add(innerResult.getDouble("yield"));
                    updateTimes.add(innerResult.getDate("create_time"));
                    formatDates.add(simpleDateFormat.format(innerResult.getDate("create_time")));
                }
                contractTradeModel.updateTimes = updateTimes;
                contractTradeModel.formatDates = formatDates;
                contractTradeModel.yields = yields;
                contractTradeModel.computeYield();
                contractTradeModels.add(contractTradeModel);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            freeConnect();
        }
        return contractTradeModels;
    }

    private void setContractTradeModel(ContractTradeModel contractTradeModel,ResultSet resultSet){
        try {
            contractTradeModel.setTradeId(resultSet.getLong("trade_id"));
            contractTradeModel.setContractId(resultSet.getLong("contract_id"));
            contractTradeModel.setMarketCapitalCapacity(resultSet.getDouble("market_capital_capacity"));
            contractTradeModel.setMaxDrawdown(resultSet.getDouble("max_drawdown"));
            contractTradeModel.setWinRate(resultSet.getDouble("win_rate"));
            contractTradeModel.setProfitLossRatio(resultSet.getDouble("profit_loss_ratio"));
            contractTradeModel.setContractName(resultSet.getString("contract_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
