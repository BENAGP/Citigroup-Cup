package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.constant.ContractBackTestHead;
import com.nju.edu.cn.constant.TradeHead;
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
    private int count5 = 0;
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
        if(count4==2)return;
        count4++;
        logger.info("--------------"+count4);
        File csv = new File(path);  // CSV文件路径
        ContractBackTestHead head = new ContractBackTestHead(path);
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
            List<ContractBackTest> contractBackTests = new ArrayList<>();
            List<Trade> trades = tradeRepository.findAll();
            Map<Long,Trade> map = new TreeMap<>();
            trades.forEach(trade -> {
                map.put(trade.getTradeId()-head.getGap(),trade);
            });
            br.readLine();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                if(line.contains("NaN"))continue;
                data = line.split(",");
//                logger.info(data[head.getDo_adjust()].trim().equals("1")+"");
                ContractBackTest contractBackTest = new ContractBackTest();
                contractBackTest.setYield(Double.valueOf(data[head.getYield()].trim()));
                contractBackTest.setMaxDrawdown(Double.valueOf(data[head.getMax_drawdown()].trim()));
                contractBackTest.setWinRate(Double.valueOf(data[head.getWin_rate()].trim()));
                contractBackTest.setProfitLossRatio(Double.valueOf(data[head.getProfit_loss_ratio()].trim()));
                contractBackTest.setPosition(Double.valueOf(data[head.getPosition()].trim()));
                contractBackTest.setTodayProfitLoss(Double.valueOf(data[head.getToday_profitloss()].trim()));
                contractBackTest.setAverageTradingPrice(Double.valueOf(data[head.getAverage_trading_price()].trim()));
                contractBackTest.setDoAdjustWarehouse(data[head.getDo_adjust()].trim().equals("1"));
                contractBackTest.setTrade(map.get(Long.valueOf(data[head.getTradeid()].trim())));//data[head.getTradeid()].trim()=101,tradeid=109,
                contractBackTest.setNearbyFuturesPosition(Integer.valueOf(data[head.getNearbyposition()].trim()));
                contractBackTest.setBackFuturesPosition(Integer.valueOf(data[head.getBackposition()].trim()));
                contractBackTest.setMarketCapitalCapacity(Double.valueOf(data[head.getMarket_capial_capacity()].trim()));
                contractBackTest.setCreateTime(sdf.parse(data[head.getCreattime()].substring(1,19)));
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
