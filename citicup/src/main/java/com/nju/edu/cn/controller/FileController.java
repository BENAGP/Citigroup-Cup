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
    private FileService futuresService;

    @GetMapping("/initFuturesUpdating")
    public void initFuturesUpdating(){
        logger.info("initFuturesUpdating");
//        futuresService.initFuturesUpdating("/Users/shea/Downloads/futures_updating.csv");
//        futuresService.initFuturesUpdating("/Users/shea/Downloads/updating.csv");
    }

    @GetMapping("/initParams")
    public void initParams(){
        logger.info("initParams");
//        futuresService.initParams("/Users/shea/Downloads/params.csv");
//        futuresService.initParams("/Users/shea/Downloads/params2.csv");
    }

    @GetMapping("/initTrade")
    public void initTrade(){
        logger.info("initTrade");
//        futuresService.initTrade("/Users/shea/Downloads/trade.csv");
//        futuresService.initContractBackTest("/Users/shea/Downloads/contract_back_test.csv");
    }
}
