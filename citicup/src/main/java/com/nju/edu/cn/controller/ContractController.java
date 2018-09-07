package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shea on 2018/9/2.
 */
@Api(value = "ContractController", description = "合约接口")
@RestController()
@RequestMapping(value = "/api/contract", produces = "application/json;charset=UTF-8")
public class ContractController {
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
        return new ArrayList<>();
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

    }

    /**
     * 获得合约列表
     * @param userId 用户ID
     * @param contractTradeSearch 筛选条件ContractTradeSearch,转成JSON字符串
     * @param page 第几页
     * @param pageNum 每页项数
     * @return
     */
    @ApiOperation(value="getList", notes="获得合约列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractTradeSearch", value = "筛选条件ContractTradeSearch,转成JSON字符串", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getList")
    public @ResponseBody List<ContractTradeModel> getList(Long userId,String contractTradeSearch,Integer page,Integer pageNum){
        return new ArrayList<>();
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
    public void buy(Long userId,Long contractId,Float investment,Integer riskLevel){

    }

    /**
     * 获得合约详情
     * @param userId 用户ID
     * @param contractId 合约ID
     * @return
     */
    @ApiOperation(value="getDetail", notes="获得合约详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "购买的合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/getDetail")
    public @ResponseBody
    ContractTradeDetail getDetail(Long userId,Long contractId){
        return new ContractTradeDetail();
    }

    /**
     * 赎回合约
     * @param userId 用户ID
     * @param contractId 赎回的合约ID
     */
    @ApiOperation(value="redeem", notes="赎回合约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "赎回的合约ID", required = true ,dataType = "string"),
    })
    @PostMapping("/redeem")
    public void redeem(Long userId,Long contractId){

    }
}
