package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.CommentModel;
import com.nju.edu.cn.service.CommentService;
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

/**
 * Created by shea on 2018/9/2.
 */
@Api(value = "CommentController", description = "评论接口")
@RestController()
@RequestMapping(value = "/api/comment", produces = "application/json;charset=UTF-8")
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;
    /**
     * 获得合约评论
     * @param contractId 合约ID
     * @param userId 用户ID
     * @param page 第几页
     * @param pageNum 每页项数
     * @return 评论列表
     */
    @ApiOperation(value="getList", notes="获得合约评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "page", value = "第几页", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "每页项数", required = true ,dataType = "string")
    })
    @PostMapping("/getList")
    public @ResponseBody List<CommentModel> getComments(Long contractId,Long userId,Integer page,Integer pageNum){
        logger.info("contractId:{},userId:{},page:{},pageNum:{}",contractId,userId,page,pageNum);
        return commentService.getComments(contractId,userId,page,pageNum);
    }

    /**
     * 获得合约评论
     * @param contractId 合约ID
     * @param userId 用户ID
     * @param commentId 回复的评论ID
     * @param content 回复内容
     * @return
     */
    @ApiOperation(value="reply", notes="获得合约评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "commentId", value = "回复的评论ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "content", value = "回复内容", required = true ,dataType = "string"),
    })
    @PostMapping("/reply")
    public void reply(Long contractId,Long userId,Long commentId,String content){
        logger.info("contractId:{},userId:{},commentId:{},content:{}",contractId,userId,commentId,content);
        commentService.reply(contractId,userId,commentId,content);
    }

    /**
     * 发表评论
     * @param contractId 合约ID
     * @param userId 用户ID
     * @param content 回复内容
     * @return
     */
    @ApiOperation(value="postComment", notes="发表评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "contractId", value = "合约ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "content", value = "回复内容", required = true ,dataType = "string"),
    })
    @PostMapping("/postComment")
    public void postComment(Long contractId,Long userId,String content){
        logger.info("contractId:{},userId:{},content:{}",contractId,userId,content);
        commentService.postComment(contractId,userId,content);
    }
}
