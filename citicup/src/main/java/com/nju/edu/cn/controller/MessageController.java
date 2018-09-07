package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.MessageModel;
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
 * Created by shea on 2018/9/6.
 */
@Api(value = "MessageController", description = "消息接口")
@RestController()
@RequestMapping(value = "/api/message", produces = "application/json;charset=UTF-8")
public class MessageController {
    /**
     * 获得用户已经阅读的消息列表
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     */
    @ApiOperation(value="getHasReadList", notes="获得用户已经阅读的消息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getHasReadList")
    public @ResponseBody
    List<MessageModel> getHasReadList(Long userId,Integer page,Integer pageNum){
        return new ArrayList<>();
    }

    /**
     * 获得用户未阅读的消息列表
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     */
    @ApiOperation(value="getHasNotReadList", notes="获得用户未阅读的消息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getHasNotReadList")
    public @ResponseBody
    List<MessageModel> getHasNotReadList(Long userId,Integer page,Integer pageNum){
        return new ArrayList<>();
    }

    /**
     * 将消息设为已读
     * @param userId 用户ID
     * @param messages 消息列表，转成JSON字符串
     */
    @ApiOperation(value="readMessage", notes="获得用户未阅读的消息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "messages", value = "消息列表，转成JSON字符串", required = true ,dataType = "string"),
    })
    @PostMapping("/readMessage")
    public void readMessage(Long userId,String messages){

    }

}