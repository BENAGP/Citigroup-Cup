package com.nju.edu.cn.dao;

import com.nju.edu.cn.constant.FuturesType;
import com.nju.edu.cn.entity.Trade;
import com.nju.edu.cn.model.*;
import com.nju.edu.cn.util.SqlConnectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shea on 2018/10/25.
 */
public class ContractBackTestDaoImpl implements ContractBackTestDao {
    static Connection connection = null;//数据库连接
    static PreparedStatement statement = null;//句柄
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Logger logger = LoggerFactory.getLogger(ContractBackTestDaoImpl.class);
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
    public List<ContractTradeModel> getList(Long userId,Integer riskLevel, ContractTradeSearch contractTradeSearch, Integer page, Integer pageNum) {

        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        connection = SqlConnectUtil.getSqlConnect();
        try {
            List<Long> contractIds = getCollectList(userId);
            String selectSql = "select t.trade_id as trade_id,t.market_capital_capacity as market_capital_capacity," +
                    "t.max_drawdown as max_drawdown,t.win_rate as win_rate,t.profit_loss_ratio as profit_loss_ratio," +
                    "t.contract_id as contract_id, c.`name` as contract_name,t.risk_level as risk_level " +
                    "from trade t left join contract c on t.contract_id=c.contract_id " +
                    "where t.deleted=FALSE and t.risk_level=" + riskLevel;
            if (contractTradeSearch.type != null && contractTradeSearch.type != FuturesType.ALL) {
                selectSql = selectSql + " and c.`type`=" + contractTradeSearch.type;
            }
//            if(userId == null){
//                selectSql = selectSql + " and user_id is null";
//            }else {
//                selectSql = selectSql + " and user_id = "+userId;
//            }
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
            selectSql = selectSql + " limit 6;";//(pageNumber-1)*pageSize,pageSize
//            selectSql = selectSql + " limit "+pageNum+";";
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ContractTradeModel contractTradeModel = new ContractTradeModel();
                setContractTradeModel(contractTradeModel,resultSet);
                getYields(contractTradeModel);
                contractTradeModel.isCollected = contractIds.indexOf(resultSet.getLong("contract_id"))>=0;
                contractTradeModels.add(contractTradeModel);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            freeConnect();
        }
        return contractTradeModels;
    }

    @Override
    public List<ContractTradeModel> getMyTradeList(Long userId, Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        String selectSql = "select t.trade_id as trade_id,t.market_capital_capacity as market_capital_capacity," +
                "t.max_drawdown as max_drawdown,t.win_rate as win_rate,t.profit_loss_ratio as profit_loss_ratio," +
                "t.contract_id as contract_id, c.`name` as contract_name,f1.last_trading_date as last_trading_date," +
                "t.risk_level as risk_level,f1.last_trading_date as last_trading_date,c.nearby_futures_id as nearby_futures_id," +
                "c.back_futures_id as back_futures_id,f1.name as nearby_futures_name,f2.name as back_futures_name " +
                "from trade t " +
                "left join contract c on c.contract_id=t.contract_id "+
                "left join futures f1 on f1.futures_id=c.nearby_futures_id " +
                "left join futures f2 on f2.futures_id=c.back_futures_id " +
                "where t.user_id="+20+" limit 3";
//                "where t.user_id="+userId;
        connection = SqlConnectUtil.getSqlConnect();
        try {
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            ResultSet innerResult = null;
            while (resultSet.next()){
                ContractTradeModel contractTradeModel = new ContractTradeModel();
                setContractTradeModel(contractTradeModel,resultSet);
                getYieldsAndPositions(contractTradeModel);
                contractTradeModel.nearbyFuturesName = resultSet.getString("nearby_futures_name");
                contractTradeModel.backFuturesName = resultSet.getString("back_futures_name");
                selectSql = "select price from futures_updating WHERE futures_id="+resultSet.getLong("nearby_futures_id")+" order by update_time desc limit 1;";
                statement = connection.prepareStatement(selectSql);
                innerResult = statement.executeQuery();
                if(innerResult.next()){
                    contractTradeModel.nearbyFuturesPrice = innerResult.getFloat("price");
                }
                selectSql = "select price from futures_updating WHERE futures_id="+resultSet.getLong("back_futures_id")+" order by update_time desc limit 1;";
                statement = connection.prepareStatement(selectSql);
                innerResult = statement.executeQuery();
                if(innerResult.next()){
                    contractTradeModel.backFuturesPrice = innerResult.getFloat("price");
                }
                contractTradeModels.add(contractTradeModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeConnect();
        }
        return contractTradeModels;
    }

    @Override
    public List<ContractTradeModel> getCollectList(Long userId, Integer riskLevel,Integer page, Integer pageNum) {
        List<ContractTradeModel> contractTradeModels = new ArrayList<>();
        String selectSql = "select t.trade_id as trade_id,t.market_capital_capacity as market_capital_capacity," +
                "t.max_drawdown as max_drawdown,t.win_rate as win_rate,t.profit_loss_ratio as profit_loss_ratio," +
                "t.contract_id as contract_id, c.`name` as contract_name,f1.last_trading_date as last_trading_date," +
                "t.risk_level as risk_level,f1.last_trading_date as last_trading_date,c.nearby_futures_id as nearby_futures_id," +
                "c.back_futures_id as back_futures_id,f1.name as nearby_futures_name,f2.name as back_futures_name " +
                "from collect ct " +
                "left join trade t where t.risk_level="+riskLevel+" and t.contract_id=ct.contract_id"+
                "left join contract c on c.contract_id=t.contract_id "+
                "left join futures f1 on f1.futures_id=c.nearby_futures_id " +
                "left join futures f2 on f2.futures_id=c.back_futures_id " +
                "where ct.user_id="+20+" limit 3";
//                "where ct.user_id="+userId+" limit 3";

        connection = SqlConnectUtil.getSqlConnect();
        try {
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                ContractTradeModel contractTradeModel = new ContractTradeModel();
                setContractTradeDetail(contractTradeModel,resultSet);
                getYields(contractTradeModel);
                contractTradeModels.add(contractTradeModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeConnect();
        }
        return contractTradeModels;
    }

    @Override
    public ContractTradeDetail getDetail(Long userId, Long contractId, Integer riskLevel) {
        String whereClause = "WHERE t.deleted=FALSE and t.risk_level=" + riskLevel+ " and t.contract_id="+contractId;
        return getDetail(userId,whereClause);
    }

    private ContractTradeDetail getDetail(Long userId,String whereClause){
        ContractTradeDetail contractTradeDetail = new ContractTradeDetail();
        HistoryMarket historyMarket = new HistoryMarket();
        connection = SqlConnectUtil.getSqlConnect();
        try {
            List<Long> contractIds = getCollectList(userId);
            String selectSql = "select t.trade_id as trade_id,t.market_capital_capacity as market_capital_capacity," +
                    "t.max_drawdown as max_drawdown,t.win_rate as win_rate,t.profit_loss_ratio as profit_loss_ratio," +
                    "t.contract_id as contract_id, c.`name` as contract_name,f1.last_trading_date as last_trading_date," +
                    "t.risk_level as risk_level,f1.last_trading_date as last_trading_date,c.nearby_futures_id as nearby_futures_id," +
                    "c.back_futures_id as back_futures_id,f1.name as nearby_futures_name,f2.name as back_futures_name " +
                    "from trade t " +
                    "left join contract c on c.contract_id=t.contract_id "+
                    "left join futures f1 on f1.futures_id=c.nearby_futures_id " +
                    "left join futures f2 on f2.futures_id=c.back_futures_id " +
                    whereClause+";";
//            if(userId == null){
//                selectSql = selectSql + " and user_id is null;";
//            }else {
//                selectSql = selectSql + " and user_id = "+userId+";";
//            }
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                setContractTradeDetail(contractTradeDetail,resultSet);
                logger.info("contract-trade-detail.ddl:{}",contractTradeDetail.ddl);
                getHistoryMarket(historyMarket,resultSet);
                getYields(contractTradeDetail);
                contractTradeDetail.historyMarket = historyMarket;
                contractTradeDetail.isCollected = contractIds.indexOf(resultSet.getLong("contract_id"))>=0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeConnect();
        }
        return contractTradeDetail;
    }

    @Override
    public ContractTradeDetail getDetail(Long userId, Long tradeId) {
        String whereClause = "WHERE t.deleted=FALSE and t.trade_id=" + tradeId;
        return getDetail(userId,whereClause);
    }

    @Override
    public HistoryMarket getHistoryMarket(Long userId, Long contractId) {
        HistoryMarket historyMarket = new HistoryMarket();
        String selectSql = "SELECT c.contract_id as contract_id,c.nearby_futures_id as nearby_futures_id,c.back_futures_id as back_futures_id," +
                "f1.name as nearby_futures_name,f2.name as back_futures_name " +
                "from contract c " +
                "left join futures f1 on f1.futures_id=c.nearby_futures_id " +
                "left join futures f2 on f2.futures_id=c.back_futures_id " +
                "where c.contract_id="+contractId+";";
        connection = SqlConnectUtil.getSqlConnect();
        try {
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                getHistoryMarket(historyMarket,resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeConnect();
        }

        return historyMarket;
    }

    private void getHistoryMarket(HistoryMarket historyMarket,ResultSet resultSet){
        ResultSet innerResult = null;
        List<Date> updateTimes = new ArrayList<>();
        List<String> formatDates = new ArrayList<>();
        List<Float> nearbyPrices = new ArrayList<>();
        List<Float> backPrices = new ArrayList<>();

        List<Integer> nearbyTradings = new ArrayList<>();
        List<Integer> backTradings = new ArrayList<>();

        try {
            historyMarket.contractId = resultSet.getLong("contract_id");
            historyMarket.nearbyFuturesId = resultSet.getLong("nearby_futures_id");
            historyMarket.nearbyFuturesName = resultSet.getString("nearby_futures_name");
            historyMarket.backFuturesId = resultSet.getLong("back_futures_id");
            historyMarket.backFuturesName = resultSet.getString("back_futures_name");
            String selectSql = "select update_time,price,trading from futures_updating where futures_id="+historyMarket.nearbyFuturesId;
            statement = connection.prepareStatement(selectSql);
            innerResult = statement.executeQuery();
            while (innerResult.next()){
                updateTimes.add(innerResult.getDate("update_time"));
                formatDates.add(simpleDateFormat.format(innerResult.getDate("update_time")));
                nearbyPrices.add(innerResult.getFloat("price"));
                nearbyTradings.add(innerResult.getInt("trading"));
            }
            selectSql = "select update_time,price,trading from futures_updating where futures_id="+historyMarket.backFuturesId;
            statement = connection.prepareStatement(selectSql);
            innerResult = statement.executeQuery();
            while (innerResult.next()){
                backPrices.add(innerResult.getFloat("price"));
                backTradings.add(innerResult.getInt("trading"));
            }
            historyMarket.updateTimes = updateTimes;
            historyMarket.formatDates = formatDates;
            historyMarket.nearbyPrices = nearbyPrices;
            historyMarket.nearbyTradings = nearbyTradings;
            historyMarket.backPrices = backPrices;
            historyMarket.backTradings = backTradings;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getYields(ContractTradeModel contractTradeModel){
        ResultSet innerResult = null;
        List<Double> yields = new ArrayList<>();//收益率纵轴
        List<Date> updateTimes = new ArrayList<>();// 时间横轴
        List<String> formatDates = new ArrayList<>();// 时间横轴
        String selectSql = "select yield,create_time from contract_back_test where trade_id=" + contractTradeModel.getTradeId();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getYieldsAndPositions(ContractTradeModel contractTradeModel){
        ResultSet innerResult = null;
        List<Double> yields = new ArrayList<>();//收益率纵轴
        List<Date> updateTimes = new ArrayList<>();// 时间横轴
        List<String> formatDates = new ArrayList<>();// 时间横轴
        List<Double> positions = new ArrayList<>();//资金占用纵轴
        List<Integer> nearbyFuturesPositionOperations = new ArrayList<>();
        List<Integer> backFuturesPositionOperations = new ArrayList<>();
        String selectSql = "select yield,create_time,`position`,nearby_futures_position,back_futures_position,today_profit_loss from contract_back_test where trade_id=" + contractTradeModel.getTradeId();
        try {
            statement = connection.prepareStatement(selectSql);
            innerResult = statement.executeQuery();
            int preNearbyPosition = 0;
            int preBackPosition = 0;
            while (innerResult.next()){
                yields.add(innerResult.getDouble("yield"));
                updateTimes.add(innerResult.getDate("create_time"));
                formatDates.add(simpleDateFormat.format(innerResult.getDate("create_time")));
                positions.add(innerResult.getDouble("position"));
                nearbyFuturesPositionOperations.add(innerResult.getInt("nearby_futures_position")-preNearbyPosition);
                backFuturesPositionOperations.add(innerResult.getInt("back_futures_position")-preBackPosition);
                preNearbyPosition = innerResult.getInt("nearby_futures_position");
                preBackPosition = innerResult.getInt("back_futures_position");
            }
            contractTradeModel.updateTimes = updateTimes;
            contractTradeModel.formatDates = formatDates;
            contractTradeModel.yields = yields;
            contractTradeModel.computeYield();
            contractTradeModel.positions = positions;
            //历史调仓
            contractTradeModel.nearbyFuturesPositionOperations = nearbyFuturesPositionOperations;
            contractTradeModel.backFuturesPositionOperations = backFuturesPositionOperations;
            //当前持仓
            contractTradeModel.position = positions.get(positions.size()-1);
            contractTradeModel.backFuturesPosition = preBackPosition;
            contractTradeModel.nearbyFuturesPosition = preNearbyPosition;
            innerResult.last();
            contractTradeModel.todayProfitLoss = innerResult.getDouble("today_profit_loss");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setContractTradeModel(ContractTradeModel contractTradeModel,ResultSet resultSet){
        try {
            contractTradeModel.setTradeId(resultSet.getLong("trade_id"));
            contractTradeModel.setRiskLevel(resultSet.getInt("risk_level"));
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

    private void setContractTradeDetail(ContractTradeModel contractTradeModel,ResultSet resultSet){
        setContractTradeModel(contractTradeModel,resultSet);
        try {
            Long now = System.currentTimeMillis();
            Date ddl = null;
            ddl = resultSet.getDate("last_trading_date");
            logger.info("ddl: {}",ddl);
            contractTradeModel.formatDDL = simpleDateFormat.format(ddl);
            contractTradeModel.isEnd = (ddl.getTime() < now);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Long> getCollectList(Long userId){
        String selectSql = "select contract_id from collect where deleted=FALSE user_id="+userId;
        ResultSet innerResult = null;
        List<Long> contractIds = new ArrayList<>();
        try {
            statement = connection.prepareStatement(selectSql);
            innerResult = statement.executeQuery();
            while (innerResult.next()){
                contractIds.add(innerResult.getLong("contract_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractIds;
    }


}
