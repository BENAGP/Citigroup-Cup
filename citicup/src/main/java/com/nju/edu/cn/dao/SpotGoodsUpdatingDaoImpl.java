package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.SpotGoodsUpdating;
import com.nju.edu.cn.model.SpotGoodsUpdatingBean;
import com.nju.edu.cn.util.SqlConnectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shea on 2018/10/19.
 */
public class SpotGoodsUpdatingDaoImpl implements SpotGoodsUpdatingDao {
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
    public void insertAll(List<SpotGoodsUpdatingBean> spotGoodsUpdatings) {
//        int length = spotGoodsUpdatings.size();
//        int perPacket = length/6;
//        System.out.println(spotGoodsUpdatings.size());
//        SpotGoodsUpdatingBean spotGoodsUpdating;
//            int length = 2;
//        for(int j=0;j<6;j++) {
//            int i, k;
//            for (i = j * perPacket, k = 0; i < length - 1 && k < perPacket - 1; i++, k++) {
//                spotGoodsUpdating = spotGoodsUpdatings.get(i);
//            }
//            System.out.println(i);
//            System.out.println(k);
//            spotGoodsUpdating = spotGoodsUpdatings.get(i);
//        }
        try {
            connection = SqlConnectUtil.getSqlConnect();
//            String sql = "INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ";
            int length = spotGoodsUpdatings.size();
            int perPacket = length/6;
            System.out.println(spotGoodsUpdatings.size());
            SpotGoodsUpdatingBean spotGoodsUpdating;
//            int length = 2;
            for(int j=0;j<6;j++){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INSERT INTO `spot_goods_updating` (`price`,`trading`,`update_time`,`spot_goods_id`)  VALUES ");
                int i,k;
                for(i=j*perPacket,k=0;i<length-1&&k<perPacket-1;i++,k++){
                    spotGoodsUpdating = spotGoodsUpdatings.get(i);
                    stringBuilder.append("("+spotGoodsUpdating.getPrice()+","+spotGoodsUpdating.getTrading()+","+spotGoodsUpdating.getTimeStr()+","+spotGoodsUpdating.getSpotGoods().getSpotGoodsId()+"),");
                }
                spotGoodsUpdating = spotGoodsUpdatings.get(i);
                stringBuilder.append("("+spotGoodsUpdating.getPrice()+","+spotGoodsUpdating.getTrading()+","+spotGoodsUpdating.getTimeStr()+","+spotGoodsUpdating.getSpotGoods().getSpotGoodsId()+")");
                String sql = stringBuilder.toString();
                System.out.println(sql);
                statement = connection.prepareStatement(sql);
                statement.execute();
                System.out.println("finish");
            }
        }catch (SQLException e){

            e.printStackTrace();
        }finally {
            freeConnect();
        }
    }
}
