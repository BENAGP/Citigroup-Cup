package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.dao.*;
import com.nju.edu.cn.entity.*;
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
    public void initParams(String path) {
        if(count2==2)return;
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
            int liqulity = 0;
            int interestratediff = 1;
            int alpha = 2;
            int beta = 3;
            int gam = 4;
            int delta = 5;
            int contractid,createTime;
            if(path.split("/")[path.split("/").length-1].equals("params.csv")){
                contractid = 6;
                createTime = 7;
            }else {
                contractid = 7;
                createTime = 6;
            }
            List<ContractBackTestParams> contractBackTestParamsList = new ArrayList<>();
            List<Contract> contracts = contractRepository.findAll();
            Map<Long,Contract> map = new TreeMap<>();
            contracts.forEach(contract -> {
                map.put(contract.getContractId(),contract);
            });
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                strings = line.split(",");
                ContractBackTestParams contractBackTestParams = new ContractBackTestParams();
                contractBackTestParams.setAlpha(Double.valueOf(strings[alpha]));
                contractBackTestParams.setBeta(Double.valueOf(strings[beta]));
                contractBackTestParams.setContract(map.get(Long.valueOf(strings[contractid])));
                contractBackTestParams.setCreateTime(sdf.parse(strings[createTime].substring(1,19)));
                contractBackTestParams.setDelta(Double.valueOf(strings[delta]));
                contractBackTestParams.setInterestRateDiff(Double.valueOf(strings[interestratediff]));
                contractBackTestParams.setGam(Double.valueOf(strings[gam]));
                contractBackTestParams.setLiquidity(Float.valueOf(strings[liqulity]));
                contractBackTestParamsList.add(contractBackTestParams);

            }

            contractBackTestParamsRepository.saveAll(contractBackTestParamsList);

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initTrade(String path) {
        if(count3==1)return;
        count3++;
        logger.info("--------------"+count3);
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
            int contractid = 1;
            int risklevel = 2;
            int yield = 3;
            int max_drawdown = 4;
            int win_rate = 5;
            int profit_loss_ratio = 6,market_capial_capacity=7,create_time=8;
            List<Trade> trades = new ArrayList<>();
            List<Contract> contracts = contractRepository.findAll();
            Map<Long,Contract> map = new TreeMap<>();
            contracts.forEach(contract -> {
                map.put(contract.getContractId(),contract);
            });
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                strings = line.split(",");
                Trade trade = new Trade();
                logger.info(strings[create_time]);
                trade.setCreateTime(sdf.parse(strings[create_time].substring(1,19)));
                trade.setRiskLevel(Integer.valueOf(strings[risklevel]));
                trade.setContract(map.get(Long.valueOf(strings[contractid])));
                trade.setInvestment(100000d);
                trade.setMaxDrawdown(Double.valueOf(strings[max_drawdown]));
                trade.setProfitLossRatio(Double.valueOf(strings[profit_loss_ratio]));
                trade.setYield(Double.valueOf(strings[yield]));
                trade.setWinRate(Double.valueOf(strings[win_rate]));
                trade.setMarketCapitalCapacity(Double.valueOf(strings[market_capial_capacity]));
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
            String[] data;
            int yield = 0;
            int max_drawdown = 1;
            int win_rate = 2;
            int profit_loss_ratio = 3;
            int position = 4;
            int today_profitloss = 5;
            int average_trading_price = 6,do_adjust=7,tradeid=8,nearbyposition=9,backposition=10,market_capial_capacity=11,creattime=12;
            List<ContractBackTest> contractBackTests = new ArrayList<>();
            List<Trade> trades = tradeRepository.findAll();
            Map<Long,Trade> map = new TreeMap<>();
            trades.forEach(trade -> {
                map.put(trade.getTradeId()+100l,trade);
            });
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
//                logger.info(line);
                data = line.split(",");
                ContractBackTest contractBackTest = new ContractBackTest();
                contractBackTest.setYield(Double.valueOf(data[yield]));
                contractBackTest.setMaxDrawdown(Double.valueOf(data[max_drawdown]));
                contractBackTest.setWinRate(Double.valueOf(data[win_rate]));
                contractBackTest.setProfitLossRatio(Double.valueOf(profit_loss_ratio));
                contractBackTest.setPosition(Double.valueOf(data[position]));
                contractBackTest.setTodayProfitLoss(Double.valueOf(data[today_profitloss]));
                contractBackTest.setAverageTradingPrice(Double.valueOf(data[average_trading_price]));
                contractBackTest.setDoAdjustWarehouse(Boolean.valueOf(data[do_adjust]));
                contractBackTest.setTrade(map.get(Long.valueOf(data[tradeid])));
                contractBackTest.setNearbyFuturesPosition(Integer.valueOf(data[nearbyposition]));
                contractBackTest.setBackFuturesPosition(Integer.valueOf(data[backposition]));
                contractBackTest.setMarketCapitalCapacity(Double.valueOf(data[market_capial_capacity]));
                contractBackTest.setCreateTime(sdf.parse(data[creattime].substring(1,19)));
                contractBackTests.add(contractBackTest);
            }
            contractBackTestRepository.saveAll(contractBackTests);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
