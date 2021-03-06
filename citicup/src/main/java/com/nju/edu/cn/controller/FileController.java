package com.nju.edu.cn.controller;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.dao.ContractRepository;
import com.nju.edu.cn.dao.TradeRepository;
import com.nju.edu.cn.entity.Contract;
import com.nju.edu.cn.entity.Trade;
import com.nju.edu.cn.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by shea on 2018/9/8.
 */
@RestController
@RequestMapping(value = "/api/file", produces = "application/json;charset=UTF-8")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;
    @Autowired
    private ContractRepository contractRepository;
    

    @GetMapping("/initFuturesUpdating")
    public void initFuturesUpdating() {
        logger.info("initFuturesUpdating");
//        fileService.initFuturesUpdating("/Users/shea/Downloads/futures_updating.csv");
//        fileService.initFuturesUpdating("/Users/shea/Downloads/updating.csv");
    }

    @GetMapping("/updateFuturesUpdating")
    public void updateFuturesUpdating() {
        logger.info("updateFuturesUpdating");
//        fileService.updateFuturesUpdating("/Users/shea/Downloads/rate33.csv",33l);
//        fileService.initFuturesUpdating("/Users/shea/Downloads/updating.csv");
    }

    @GetMapping("/insertFuturesUpdating")
    public void insertFuturesUpdating() {
        logger.info("insertFuturesUpdating");

//        fileService.insertFuturesUpdating("/Users/shea/file/f297_300_303.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f333_336_339.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/futures_updating.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f67_68_69.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f97_98_99.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f124_125_126.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f55_56_57.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f58-61.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f62-64.csv");
//        fileService.insertFuturesUpdating("/Users/shea/file/f65_66.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/54.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/51.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/48.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/45.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/42.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/30.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/24.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/36.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/39.csv");
//        fileService.insertFuturesUpdating("/Users/shea/Downloads/21.csv");
//        fileService.initFuturesUpdating("/Users/shea/Downloads/updating.csv");
    }

    @GetMapping("/initParams")
    public void initParams() {
        logger.info("initParams");
//        fileService.initParams("/Users/shea/Downloads/params.csv");
//        fileService.initParams("/Users/shea/file/(125,126)contract_back_test_params.csv");
//        fileService.initParams("/Users/shea/file/contract_back_test_params(1).csv");
//        fileService.initParams("/Users/shea/file/contract_back_test_params.csv");
//        fileService.initParams("/Users/shea/Downloads/params2.csv");
    }

    @GetMapping("/initTrade")
    public void initTrade() {
        logger.info("initTrade");
//        fileService.initTrade("/Users/shea/Downloads/trade_xu.csv");
//        fileService.initContractBackTest("/Users/shea/Downloads/contract_back_test_xu.csv");
//        fileService.initTrade("/Users/shea/Downloads/trade_li.csv");
//        fileService.initContractBackTest("/Users/shea/Downloads/contract_back_test_li.csv");
    }

    @GetMapping("/resetYield")
    public void resetYield() {
        logger.info("resetYield");
//        fileService.resetYield();
    }

    @GetMapping("/initSpotUpdating")
    public void initSpotUpdating() {
        logger.info("initFuturesUpdating");
//        fileService.initFuturesUpdating("/Users/shea/Downloads/futures_updating.csv");
//        fileService.initFuturesUpdating("/Users/shea/Downloads/updating.csv");
//        fileService.initSpotUpdating("/Users/shea/Downloads/spot_upadting.csv");
    }

    @GetMapping("/insertContractBackTest")
    public void insertContractBackTest(){
//        fileService.initContractBackTest("/Users/shea/file/(125,126)contract_back_test.csv");
//        fileService.initContractBackTest("/Users/shea/file/contract_back_test(1).csv");
    }

    @GetMapping("/test")
    public void test(){
//        List<Contract> contracts = contractRepository.findAll();
//        contracts.forEach(contract -> {
//            contract.setType(contract.getNearbyFutures().getType());
//        });
//        contractRepository.saveAll(contracts);
//        List<Trade> trades = tradeRepository.findAll();
//        trades.forEach(trade -> {
//            trade.setType(trade.getContract().getNearbyFutures().getType());
//        });
//        tradeRepository.saveAll(trades);
    }
}
