package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.constant.ContractBackTestHead;
import com.nju.edu.cn.constant.TradeHead;
import com.nju.edu.cn.dao.*;
import com.nju.edu.cn.entity.*;
import com.nju.edu.cn.model.ContractBackTestBean;
import com.nju.edu.cn.model.ContractBackTestParamsBean;
import com.nju.edu.cn.model.FuturesUpdatingBean;
import com.nju.edu.cn.model.SpotGoodsUpdatingBean;
import com.nju.edu.cn.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shea on 2018/9/8.
 */
@Service
public class FileServiceImpl implements FileService {
    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private int count = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    private int count7 = 0;
    @Autowired
    private SpotGoodsRepository spotGoodsRepository;
    @Autowired
    private FuturesRepository futuresRepository;
    @Autowired
    private SpotGoodsUpdatingRepository spotGoodsUpdatingRepository;
    @Autowired
    private FuturesUpdatingRepository futuresUpdatingRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractBackTestParamsRepository contractBackTestParamsRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private ContractBackTestRepository contractBackTestRepository;

    private SpotGoodsUpdatingDao spotGoodsUpdatingDao = new SpotGoodsUpdatingDaoImpl();
    private FuturesUpdatingDao futuresUpdatingDao = new FuturesUpdatingDaoImpl();
    private ContractBackTestDao contractBackTestDao = new ContractBackTestDaoImpl();

    @Override
    public void initFuturesUpdating(String path) {
        if(count==2)return;
        count++;
        logger.info("--------------"+count);
        File csv = new File(path);  // CSV文件路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            String[] strings;
            int interestRate = 0;
            int price = 1;
            int trading = 2;
            int futuresId = 3;
            int updateTime = 4;
            List<SpotGoodsUpdating> spotGoodsUpdatings = new ArrayList<>();
            List<FuturesUpdating> futuresUpdatings = new ArrayList<>();
            List<SpotGoods> spotGoods = spotGoodsRepository.findAll();
            List<Futures> futuresList = futuresRepository.findAll();
            Map<Long,Futures> map = new TreeMap<>();
            futuresList.forEach(futures -> {
                map.put(futures.getFuturesId(),futures);
            });
            boolean isNaN = false;
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                strings = line.split(",");
                if(strings[futuresId].equals("1")||strings[futuresId].equals("10")){
                    for (int i=1;i<5;i++) {
                        if(strings[i].equals("NaN")){
                            isNaN = true;
                        }
                    }
                    if(isNaN){
                        isNaN = false;
                        continue;
                    }
                    SpotGoodsUpdating spotGoodsUpdating = new SpotGoodsUpdating();
                    spotGoodsUpdating.setPrice(Float.valueOf(strings[price]));
                    spotGoodsUpdating.setUpdateTime(sdf.parse(strings[updateTime].substring(1,19)));
                    spotGoodsUpdating.setTrading(Integer.valueOf(strings[trading]));
                    if(strings[futuresId].equals("1"))spotGoodsUpdating.setSpotGoods(spotGoods.get(0));
                    else if(strings[futuresId].equals("10"))spotGoodsUpdating.setSpotGoods(spotGoods.get(1));
                    spotGoodsUpdatings.add(spotGoodsUpdating);
                }else {
                    for (int i=0;i<5;i++) {
                        if(strings[i].equals("NaN")){
                            isNaN = true;
                        }
                    }
                    if(isNaN){
                        isNaN = false;
                        continue;
                    }
                    FuturesUpdating futuresUpdating = new FuturesUpdating();
                    futuresUpdating.setPrice(Float.valueOf(strings[price]));
                    futuresUpdating.setUpdateTime(sdf.parse(strings[updateTime].substring(1,19)));
                    futuresUpdating.setTrading(Integer.valueOf(strings[trading]));
                    futuresUpdating.setInterestRate(Double.valueOf(strings[interestRate]));
                    futuresUpdating.setFutures(map.get(Long.valueOf(strings[futuresId])));
                    futuresUpdatings.add(futuresUpdating);
                }

            }
            futuresUpdatingRepository.saveAll(futuresUpdatings);
            spotGoodsUpdatingRepository.saveAll(spotGoodsUpdatings);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFuturesUpdating(String path,Long futuresId){
        if(count7==1)return;
        count7++;
        logger.info("--------------"+count7);
        File csv = new File(path);  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            br.readLine();
            String[] strings;
            int interestRate = 0;
            int updateTime = 3;
            List<FuturesUpdatingBean> futuresUpdatingBeans = futuresUpdatingDao.selectByFuturesId(futuresId);
            int count = 0;
            System.out.println("found");
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                FuturesUpdatingBean futuresUpdatingBean = futuresUpdatingBeans.get(count);
                strings = line.split(",");
                futuresUpdatingBean.setInterestRate(strings[interestRate].equals("NaN")?null:Double.valueOf(strings[interestRate]));
//                futuresUpdatingBean.setFuturesId(27l);
//                futuresUpdatingBean.setTimeStr(strings[updateTime]);
                count++;
            }
            futuresUpdatingDao.updateList(futuresUpdatingBeans);


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertFuturesUpdating(String path) {
        if(count7==4)return;
        count7++;
        logger.info("--------------"+count7);
        File csv = new File(path);  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            br.readLine();
            String[] strings;
            int price = 0;
            int trading = 1;
            int interestRate = 2;
            int updateTime = 3;
            int futuresId = 4;
            List<FuturesUpdatingBean> futuresUpdatingBeans = new ArrayList<>();
            String trade;
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                FuturesUpdatingBean futuresUpdatingBean = new FuturesUpdatingBean();
                strings = line.split(",");
                futuresUpdatingBean.setInterestRate(strings[interestRate].equals("NaN")?null:Double.valueOf(strings[interestRate]));
                futuresUpdatingBean.setPrice(strings[price].equals("NaN")?null:Float.valueOf(strings[price]));
                futuresUpdatingBean.setTimeStr(strings[updateTime]);
                trade = strings[trading];
                if(trade.contains(".")){
//                    System.out.println(trade);
                    trade = trade.substring(0,trade.indexOf("."));
//                    System.out.println(trade);
                }
                futuresUpdatingBean.setTrading(trade.equals("NaN")?null:Integer.valueOf(trade));
                futuresUpdatingBean.setFuturesId(Long.valueOf(strings[futuresId]));
                futuresUpdatingBeans.add(futuresUpdatingBean);
            }
            futuresUpdatingDao.insertList(futuresUpdatingBeans);


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initSpotUpdating(String path) {
        if(count6==1)return;
        count6++;
        logger.info("--------------"+count6);
        File csv = new File(path);  // CSV文件路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            String[] strings;
            int updating_time = 0;
            int price = 1;
            int trading = 2;
            int spot_goods_id = 3;
            List<SpotGoodsUpdatingBean> spotGoodsUpdatings = new ArrayList<>();
            List<SpotGoods> spotGoods = spotGoodsRepository.findAll();
            Map<Long,SpotGoods> map = new TreeMap<>();
            spotGoods.forEach(spotGood -> {
                map.put(spotGood.getSpotGoodsId(),spotGood);
            });
            boolean isNaN = false;
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                strings = line.split(",");
                SpotGoodsUpdatingBean spotGoodsUpdating = new SpotGoodsUpdatingBean();
                spotGoodsUpdating.setPrice(strings[price].equals("NaN")?null:Float.valueOf(strings[price]));
                spotGoodsUpdating.setTimeStr(strings[updating_time]);
//                spotGoodsUpdating.setUpdateTime(sdf.parse(strings[updating_time].substring(1,19)));
                String trade = strings[trading];
                if(trade.contains(".")){
//                    System.out.println(trade);
                    trade = trade.substring(0,trade.indexOf("."));
//                    System.out.println(trade);
                }
                spotGoodsUpdating.setTrading(trade.equals("NaN")?null:Integer.valueOf(trade));
                spotGoodsUpdating.setSpotGoods(map.get(Long.valueOf(strings[spot_goods_id])));
                spotGoodsUpdatings.add(spotGoodsUpdating);

            }
            spotGoodsUpdatingDao.insertAll(spotGoodsUpdatings);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initParams(String path) {
        if(count2==1)return;
        count2++;
        logger.info("--------------"+count2);
        File csv = new File(path);  // CSV文件路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            String[] strings;
            //alpha,beta,gam,delta,interest_rate_diff,liquidity,contract_id,create_time
            int alpha = 0;
            int beta = 1;
            int gam = 2;
            int delta = 3;
            int interest_rate_diff = 4;
            int liquidity = 5;
            int contract_id = 6,create_time=7;
            List<ContractBackTestParamsBean> contractBackTestParamsList = new ArrayList<>();
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                strings = line.split(",");
                ContractBackTestParamsBean contractBackTestParams = new ContractBackTestParamsBean();
                contractBackTestParams.setAlpha(strings[alpha].equals("NaN")?null:Double.valueOf(strings[alpha]));
                contractBackTestParams.setBeta(strings[beta].equals("NaN")?null:Double.valueOf(strings[beta]));
                contractBackTestParams.setContractId(strings[contract_id].equals("NaN")?null:Long.valueOf(strings[contract_id]));
                contractBackTestParams.setCreateTimeStr(strings[create_time]);
                contractBackTestParams.setDelta(strings[delta].equals("NaN")?null:Double.valueOf(strings[delta]));
                contractBackTestParams.setInterestRateDiff(strings[interest_rate_diff].equals("NaN")?null:Double.valueOf(strings[interest_rate_diff]));
                contractBackTestParams.setGam(strings[gam].equals("NaN")?null:Double.valueOf(strings[gam]));
                contractBackTestParams.setLiquidity(strings[liquidity].equals("NaN")?null:Float.valueOf(strings[liquidity]));
                contractBackTestParamsList.add(contractBackTestParams);

            }
            contractBackTestDao.insertParamsList(contractBackTestParamsList);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initTrade(String path) {
        if(count3==2)return;
        count3++;
        logger.info("--------------"+count3);
        File csv = new File(path);  // CSV文件路径
        TradeHead tradeHead = new TradeHead(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            String[] strings;
            List<Trade> trades = new ArrayList<>();
            List<Contract> contracts = contractRepository.findAll();
            Map<Long,Contract> map = new TreeMap<>();
            contracts.forEach(contract -> {
                map.put(contract.getContractId(),contract);
            });
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                if(line.contains("NaN"))continue;
//                logger.info(line);
                strings = line.split(",");
                Trade trade = new Trade();
                logger.info(strings[tradeHead.getCreate_time()]);
                trade.setCreateTime(sdf.parse(strings[tradeHead.getCreate_time()].substring(1,19)));
                trade.setRiskLevel(Integer.valueOf(strings[tradeHead.getRisklevel()].trim()));
                trade.setContract(map.get(Long.valueOf(strings[tradeHead.getContractid()].trim())));
                trade.setInvestment(100000d);
                trade.setMaxDrawdown(Double.valueOf(strings[tradeHead.getMax_drawdown()].trim()));
                trade.setProfitLossRatio(Double.valueOf(strings[tradeHead.getProfit_loss_ratio()].trim()));
                trade.setYield(Double.valueOf(strings[tradeHead.getYield()].trim()));
                trade.setWinRate(Double.valueOf(strings[tradeHead.getWin_rate()].trim()));
                trade.setMarketCapitalCapacity(Double.valueOf(strings[tradeHead.getMarket_capial_capacity()].trim()));
                trade.setDeleted(false);
                trades.add(trade);

            }
            tradeRepository.saveAll(trades);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initContractBackTest(String path) {
        if(count4==1)return;
        count4++;
        logger.info("--------------"+count4);
        File csv = new File(path);  // CSV文件路径
        ContractBackTestHead head = new ContractBackTestHead(path);
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        try {
            String[] data;
            List<ContractBackTestBean> contractBackTests = new ArrayList<>();
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                data = line.split(",");
//                logger.info(data[head.getDo_adjust()].trim().equals("1")+"");
                ContractBackTestBean contractBackTest = new ContractBackTestBean();
                contractBackTest.setYield(data[head.getYield()].trim().equals("NaN")?null:Double.valueOf(data[head.getYield()].trim()));
                contractBackTest.setMaxDrawdown(data[head.getMax_drawdown()].trim().equals("NaN")?null:Double.valueOf(data[head.getMax_drawdown()].trim()));
                contractBackTest.setWinRate(data[head.getWin_rate()].trim().equals("NaN")?null:Double.valueOf(data[head.getWin_rate()].trim()));
                contractBackTest.setProfitLossRatio(data[head.getProfit_loss_ratio()].trim().equals("NaN")?null:Double.valueOf(data[head.getProfit_loss_ratio()].trim()));
                contractBackTest.setPosition(data[head.getPosition()].trim().equals("NaN")?null:Double.valueOf(data[head.getPosition()].trim()));
                contractBackTest.setTodayProfitLoss(data[head.getToday_profitloss()].trim().equals("NaN")?null:Double.valueOf(data[head.getToday_profitloss()].trim()));
                contractBackTest.setAverageTradingPrice(data[head.getAverage_trading_price()].trim().equals("NaN")?null:Double.valueOf(data[head.getAverage_trading_price()].trim()));
                contractBackTest.setDoAdjustWarehouse(data[head.getDo_adjust()].trim().equals("1"));
                contractBackTest.setTradeId(Long.valueOf(data[head.getTradeid()].trim()));//data[head.getTradeid()].trim()=101,tradeid=109,
                contractBackTest.setNearbyFuturesPosition(data[head.getNearbyposition()].trim().equals("NaN")?null:Integer.valueOf(data[head.getNearbyposition()].trim()));
                contractBackTest.setBackFuturesPosition(data[head.getBackposition()].trim().equals("NaN")?null:Integer.valueOf(data[head.getBackposition()].trim()));
                contractBackTest.setMarketCapitalCapacity(data[head.getMarket_capial_capacity()].trim().equals("NaN")?null:Double.valueOf(data[head.getMarket_capial_capacity()].trim()));
                contractBackTest.setCreateTimeStr(data[head.getCreattime()]);
                contractBackTests.add(contractBackTest);
            }

            logger.info("size:{}",contractBackTests.size());
            contractBackTestDao.insertList(contractBackTests);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void resetYield() {
        if(count5==1)return;
        count5++;
        List<Contract> contracts = contractRepository.findAll();
        contracts.forEach(contract -> {
//            double sum = nearbyUpdating.getPrice()+backUpdating.getPrice();
            List<Trade> trades = tradeRepository.findByContract_ContractId(contract.getContractId());
            trades.forEach(trade -> {
                List<ContractBackTest> contractBackTests = contractBackTestRepository.findByTrade_TradeId(trade.getTradeId());
                if(contractBackTests.size()==0){
                    logger.info(trade.getTradeId()+"");
                }else {
                    ContractBackTest first = null;
                    for (ContractBackTest test:contractBackTests) {
                        if(test.getPosition()!=0){
                            first = test;
                            break;
                        }
                    }
                    double capital = first.getAverageTradingPrice()*2/first.getPosition()*0.16;
                    contractBackTests.forEach(contractBackTest -> {
                        contractBackTest.setYield(contractBackTest.getYield()/capital);
                    });
                    trade.setYield(contractBackTests.get(contractBackTests.size()-1).getYield());
                }
                contractBackTestRepository.saveAll(contractBackTests);
            });
            tradeRepository.saveAll(trades);
        });
    }
}
