package com.nju.edu.cn.controller;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        fileService.insertFuturesUpdating("/Users/shea/Downloads/30.csv");
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
        fileService.initSpotUpdating("/Users/shea/Downloads/spot_upadting.csv");
    }
}
