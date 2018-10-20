package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.FuturesUpdating;
import com.nju.edu.cn.model.FuturesUpdatingBean;
import com.nju.edu.cn.util.SqlConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shea on 2018/10/19.
 */
public class FuturesUpdatingDaoImpl implements FuturesUpdatingDao {
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

    public List<FuturesUpdatingBean> selectByFuturesId(Long futuresId){
        List<FuturesUpdatingBean> res = new ArrayList<>();
        ResultSet resultSet = null;//存储结果
        try {

            connection = SqlConnectUtil.getSqlConnect();
            String selectSql = "select futures_updating_id from `futures_updating` WHERE futures_id="+futuresId;
            System.out.println(selectSql);
            statement = connection.prepareStatement(selectSql);
            resultSet=statement.executeQuery();
            System.out.println(selectSql);
            while (resultSet.next()){
                FuturesUpdatingBean futuresUpdatingBean = new FuturesUpdatingBean();
                futuresUpdatingBean.setFuturesUpdatingId(resultSet.getLong("futures_updating_id"));
//                futuresUpdatingBean.setInterestRate(resultSet.getDouble("interest_rate"));
//                futuresUpdatingBean.setPrice(resultSet.getFloat("price"));
//                futuresUpdatingBean.setTrading(resultSet.getInt("trading"));
//                futuresUpdatingBean.setTimeStr(resultSet.getString("update_time"));
//                futuresUpdatingBean.setFuturesId(resultSet.getLong("futures_id"));
                res.add(futuresUpdatingBean);
            }
        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            freeConnect();
        }
        return res;
    }

    @Override
    public void updateList(List<FuturesUpdatingBean> futuresUpdatingBeans) {
//                    int length = futuresUpdatingBeans.size();
//            int perPacket = length/6;
//            FuturesUpdatingBean futuresUpdatingBean;
////            int length = 2;
//            int i=0,k=0;
//            for(int j=0;j<7&&i<length;j++,i++){
//                for(k=0;i<length-1&&k<perPacket-1;i++,k++){
//                    futuresUpdatingBean = futuresUpdatingBeans.get(i);
//                    System.out.print(i+" ");
//                }
//                futuresUpdatingBean = futuresUpdatingBeans.get(i);
//                System.out.println(i);
//                System.out.println("finish");
//            }
//        System.out.println(futuresUpdatingBeans.size());
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = futuresUpdatingBeans.size();
            int perPacket = length/6;
//            System.out.println(futuresUpdatingBeans.size());
            FuturesUpdatingBean futuresUpdatingBean;
//            int length = 2;
            int i=0,k=0;
            for(int j=0;j<7&&i<length;j++,i++){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `futures_updating` (`futures_updating_id`,`interest_rate`)  VALUES ");
                for(k=0;i<length-1&&k<perPacket-1;i++,k++){
                    futuresUpdatingBean = futuresUpdatingBeans.get(i);
                    stringBuilder.append("("+futuresUpdatingBean.getFuturesUpdatingId()+","+futuresUpdatingBean.getInterestRate()+"),");
                }
                futuresUpdatingBean = futuresUpdatingBeans.get(i);
                stringBuilder.append("("+futuresUpdatingBean.getFuturesUpdatingId()+","+futuresUpdatingBean.getInterestRate()+") on duplicate key update interest_rate=values(interest_rate) ");
                String sql = stringBuilder.toString();
                System.out.println("before");
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
    public void insertList(List<FuturesUpdatingBean> futuresUpdatingBeans) {
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = futuresUpdatingBeans.size();
            int perPacket = length/6;
//            System.out.println(futuresUpdatingBeans.size());
            FuturesUpdatingBean futuresUpdatingBean;
//            int length = 2;
            int i=0,k=0;
            for(int j=0;j<7&&i<length;j++,i++){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("insert INTO `futures_updating` (`interest_rate`,`price`,`trading`,`update_time`,`futures_id`)  VALUES ");
                for(k=0;i<length-1&&k<perPacket-1;i++,k++){
                    futuresUpdatingBean = futuresUpdatingBeans.get(i);
                    stringBuilder.append("("+futuresUpdatingBean.getInterestRate()+","+futuresUpdatingBean.getPrice()+","+futuresUpdatingBean.getTrading()+","+futuresUpdatingBean.getTimeStr()+","+futuresUpdatingBean.getFuturesId()+"),");
                }
                futuresUpdatingBean = futuresUpdatingBeans.get(i);
                stringBuilder.append("("+futuresUpdatingBean.getInterestRate()+","+futuresUpdatingBean.getPrice()+","+futuresUpdatingBean.getTrading()+","+futuresUpdatingBean.getTimeStr()+","+futuresUpdatingBean.getFuturesId()+")");
                String sql = stringBuilder.toString();
                System.out.println("before");
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
