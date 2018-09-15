package com.nju.edu.cn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nju.edu.cn.entity.ContractBackTestParams;
import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import com.nju.edu.cn.model.ContractTradeSearch;
import com.nju.edu.cn.model.HistoryMarket;
import com.nju.edu.cn.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shea on 2018/9/2.
 */
@Api(value = "ContractController", description = "合约接口")
@RestController()
@RequestMapping(value = "/api/contract", produces = "application/json;charset=UTF-8")
public class ContractController {
    @Autowired
    private ContractService contractService;

    private static Logger logger = LoggerFactory.getLogger(ContractController.class);
    /**
     * 获得用户收藏的合约列表
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     */
    @ApiOperation(value="getCollectList", notes="获得用户收藏的合约列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getCollectList")
    public @ResponseBody List<ContractTradeModel> getCollectList(Long userId,Integer page,Integer pageNum){
        logger.info("userId:{},page:{},pageNum:{}",userId,page,pageNum);
        return contractService.getCollectList(userId,page,pageNum);
    }

    /**
     * 收藏合约
     * @param userId 用户ID
     * @param contractId 收藏的合约ID
     */
    @ApiOperation(value="collect", notes="收藏合约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "收藏的合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/collect")
    public void collect(Long userId,Long contractId){
        logger.info("userId:{},contractId:{}",userId,contractId);
        contractService.collect(userId,contractId);
    }

    /**
     * 取消收藏合约
     * @param userId 用户ID
     * @param contractId 取消收藏的合约ID
     */
    @ApiOperation(value="cancelCollect", notes="取消收藏合约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "取消收藏的合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/cancelCollect")
    public void cancelCollect(Long userId,Long contractId){
        logger.info("userId:{},contractId:{}",userId,contractId);
        contractService.cancelCollect(userId,contractId);
    }

    /**
     * 获得我的交易列表
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     * @return
     */
    @ApiOperation(value="getMyTradeList", notes="获得我的交易列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getMyTradeList")
    public @ResponseBody List<ContractTradeModel> getMyTradeList(Long userId,Integer page,Integer pageNum){
        logger.info("userId:{},page:{},pageNum:{}",userId,page,pageNum);

        return contractService.getMyTradeList(userId,page,pageNum);
    }

    /**
     * 获得合约列表
     * @param userId 用户ID
     * @param contractTradeSearch 筛选条件ContractTradeSearch,转成JSON字符串
     * @param page 第几页
     * @param pageNum 每页项数
     * @return
     */
    @ApiOperation(value="getList", notes="获得合约列表,如果用户没有登陆，userId传null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractTradeSearch", value = "筛选条件ContractTradeSearch,转成JSON字符串,页面初始化用户尚未选筛选条件的时候可以不传，后端会用默认的", required = false ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getList")
    public @ResponseBody List<ContractTradeModel> getList(Long userId,String contractTradeSearch,Integer page,Integer pageNum){
        logger.info("userId:{},contractTradeSearch:{},page:{},pageNum:{}",userId,contractTradeSearch,page,pageNum);
        //ContractTradeSearch contractTradeSearch1 = (ContractTradeSearch)JSONObject.toBean(contractTradeSearch,ContractTradeSearch.class);
        ContractTradeSearch contractTradeSearch1 = JSON.parseObject(contractTradeSearch,ContractTradeSearch.class);
        return contractService.getList(userId,contractTradeSearch1,page,pageNum);
    }

    /**
     * 获得合约列表
     * @param userId 用户ID
     * @param contractIdList contractId列表,转成JSON字符串
     * @return 这些合约是否别该用户收藏
     */
    @ApiOperation(value="isCollected", notes="获得合约列表之后，取消合约列表里的contractID，组成一个list，返回这些合约是否别该用户收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractIdList", value = "contractId列表,转成JSON字符串", required = true ,dataType = "string"),
    })
    @PostMapping("/isCollected")
    public @ResponseBody
    Map<Long,Boolean> isCollected(Long userId,String contractIdList){
        logger.info("userId:{},contractIdList:{}",userId,contractIdList);
        JSONArray jsonArray = JSONArray.parseArray(contractIdList);
        List<Long> contractIds = jsonArray.toJavaList(Long.class);
        return contractService.isCollected(userId,contractIds);
    }

    /**
     * 购买合约
     * @param userId 用户ID
     * @param contractId 购买的合约ID
     * @param investment 购买的金额
     * @param riskLevel 选择的风险等级
     */
    @ApiOperation(value="buy", notes="购买合约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "购买的合约ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "investment", value = "购买的金额", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "riskLevel", value = "选择的风险等级", required = true ,dataType = "string"),
    })
    @PostMapping("/buy")
    public void buy(Long userId,Long contractId,Double investment,Integer riskLevel){
        logger.info("userId:{},contractId:{},investment:{},riskLevel:{}",userId,contractId,investment,riskLevel);
        contractService.buy(userId,contractId,investment,riskLevel);
    }

    /**
     * 获得合约详情，详情页初始化的时候调用此方法
     * @param userId 用户ID
     * @param tradeId 购买的合约交易ID
     * @return
     */
    @ApiOperation(value="getDetail", notes="获得合约详情，详情页初始化的时候调用此方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "tradeId", value = "购买的合约交易ID", required = true ,dataType = "string"),
    })
    @PostMapping("/getDetail")
    public @ResponseBody
    ContractTradeDetail getDetail(Long userId,Long tradeId){
        logger.info("userId:{},tradeId:{}",userId,tradeId);
        return contractService.getDetail(userId,tradeId);
    }

    /**
     * 获得合约流动性，详情页初始化的时候调用此方法
     * @param userId 用户ID
     * @param contractId 合约ID
     * @return
     */
    @ApiOperation(value="getLiquidity", notes="获得合约流动性，详情页初始化的时候调用此方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/getLiquidity")
    public double getLiquidity(Long userId,Long contractId){
        logger.info("userId:{},contractId:{}",userId,contractId);
        return contractService.getLiquidity(userId,contractId);
    }

    /**
     * 获得历史行情，详情页初始化的时候调用此方法
     * @param userId 用户ID
     * @param contractId 合约ID
     * @return
     */
    @ApiOperation(value="getHistoryMarket", notes="获得历史行情，详情页初始化的时候调用此方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/getHistoryMarket")
    public @ResponseBody
    HistoryMarket getHistoryMarket(Long userId, Long contractId){
        logger.info("userId:{},contractId:{}",userId,contractId);
        return contractService.getHistoryMarket(userId,contractId);
    }



    /**
     * 获得合约详情
     * @param userId 用户ID
     * @param contractId 合约ID
     * @param riskLevel 选择的风险等级
     * @return
     */
    @ApiOperation(value="getDetailByRiskLevel", notes="在详情页切换风险等级的时候调用此方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "riskLevel", value = "选择的风险等级", required = true ,dataType = "string"),
    })
    @PostMapping("/getDetailByRiskLevel")
    public @ResponseBody
    ContractTradeDetail getDetailByRiskLevel(Long userId,Long contractId,Integer riskLevel){
        logger.info("userId:{},contractId:{},riskLevel:{}",userId,contractId,riskLevel);
        return contractService.getDetail(userId,contractId,riskLevel);
    }

    /**
     * 赎回合约
     * @param userId 用户ID
     * @param tradeId 赎回的合约ID
     */
    @ApiOperation(value="redeem", notes="赎回合约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "tradeId", value = "赎回的合约交易ID", required = true ,dataType = "string"),
    })
    @PostMapping("/redeem")
    public void redeem(Long userId,Long tradeId){
        logger.info("userId:{},tradeId:{}",userId,tradeId);
        contractService.redeem(userId,tradeId);
    }
}
