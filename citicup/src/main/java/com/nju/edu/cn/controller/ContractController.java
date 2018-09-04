package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.ContractTradeDetail;
import com.nju.edu.cn.model.ContractTradeModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shea on 2018/9/2.
 */
@RestController()
@RequestMapping(value = "/api/contract", produces = "application/json;charset=UTF-8")
public class ContractController {
    /**
     * 获得用户收藏的合约列表
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     */
    @PostMapping("/getCollectList")
    public @ResponseBody List<ContractTradeModel> getCollectList(Long userId,Integer page,Integer pageNum){
        return new ArrayList<>();
    }

    /**
     * 收藏合约
     * @param userId 用户ID
     * @param contractId 收藏的合约ID
     */
    @PostMapping("/collect")
    public void collect(Long userId,Long contractId){

    }

    /**
     * 取消收藏合约
     * @param userId 用户ID
     * @param contractId 取消收藏的合约ID
     */
    @PostMapping("/cancelCollect")
    public void cancelCollect(Long userId,Long contractId){

    }

    /**
     * 获得合约列表
     * @param userId 用户ID
     * @param contractTradeSearch 筛选条件ContractTradeSearch,专程JSON字符串
     * @param page 第几页
     * @param pageNum 每页项数
     * @return
     */
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
    @PostMapping("/buy")
    public void buy(Long userId,Long contractId,Float investment,Integer riskLevel){

    }

    /**
     * 获得合约详情
     * @param userId 用户ID
     * @param contractId 合约ID
     * @return
     */
    @PostMapping("/getDetail")
    public @ResponseBody
    ContractTradeDetail getDetail(Long userId,Long contractId){
        return new ContractTradeDetail();
    }
}
