package com.nju.edu.cn.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju.edu.cn.model.UserModel;
import com.nju.edu.cn.service.UserService;
import com.nju.edu.cn.util.GetAccounts;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by shea on 2018/9/1.
 */
@Api(value = "UserController", description = "用户接口")
@RestController()
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    /**
     * 用户注册
     * @param email 邮箱
     * @param psw 密码
     * @return
     */
    @ApiOperation(value="register", notes="用户注册。检查是否帐号重复，前端验证邮箱格式是否规范，密码限制10个字符")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "psw", value = "密码", required = true ,dataType = "string")
    })
    @PostMapping("/register")
    public @ResponseBody
    UserModel register(String email,String psw){
        logger.info("email:{},psw:{}",email,psw);
        return userService.register(email,psw);
    }

    /**
     * 用户登陆
     * @param email 邮箱
     * @param psw 密码
     * @return
     */
    @ApiOperation(value="login", notes="用户登陆。检查帐号密码是否正确。前端根据返回的UserModel.isCompleted判断信息是否完整，不完整提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "psw", value = "密码", required = true ,dataType = "string")
    })
    @PostMapping("/login")
    public @ResponseBody
    UserModel login(String email, String psw, HttpServletRequest request){
        logger.info("email:{},psw:{}",email,psw);
        return userService.login(email,psw);
    }

    /**
     * 获得加密参数
     * @return
     */
    @ApiOperation(value="getEncryptParams", notes="获得加密参数")
    @PostMapping("/getEncryptParams")
    public @ResponseBody
    JSONObject getEncryptParams(HttpServletRequest request){
        ServletContext context = request.getServletContext();
        if(context.getAttribute("access_token")==null||context.getAttribute("event_id")==null){
            try {
                GetAccounts.getBizToken(context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("biz_token",context.getAttribute("biz_token"));
        jsonObject.put("modulus",context.getAttribute("modulus"));
        jsonObject.put("exponent",context.getAttribute("exponent"));
        jsonObject.put("event_id",context.getAttribute("event_id"));
        return jsonObject;
    }


    /**
     * 用户完善上传头像
     * @param userId 用户ID
     * @param file 头像图片文件
     * @return 头像URL
     */
    @ApiOperation(value="postAvatar", notes="用户完善／修改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "file", value = "头像图片文件", required = true ,dataType = "string"),
    })
    @PostMapping("/postAvatar")
    public @ResponseBody String postAvatar(Long userId, MultipartFile file){
        logger.info("userId:{},file:{}",userId,file);
        return userService.postAvatar(userId,file);
    }

    /**
     * 用户完善个人信息,登陆后检查用户信息是否未完善
     * @param userId 用户ID
     * @param email 邮箱
     * @param nickname 昵称
     * @param preferRiskLevel 偏好风险等级
     * @param avatar 头像URL
     * @return
     */
    @ApiOperation(value="perfectInfo", notes="用户完善／修改个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "email", value = "邮箱，置灰不可修改", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "preferRiskLevel", value = "偏好风险等级", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "avatar", value = "头像URL", required = true ,dataType = "string")
    })
    @PostMapping("/perfectInfo")
    public @ResponseBody
    UserModel perfectInfo(Long userId, String email, String nickname, Integer preferRiskLevel,String avatar){
        logger.info("userId:{},email:{},nickname:{},preferRiskLevel:{},avatar:{}",userId,email,nickname,preferRiskLevel,avatar);
        return userService.perfectInfo(userId,email,nickname,preferRiskLevel,avatar);
    }


    /**
     * 用户获得基本信息
     * @param userId 用户ID
     * @return 用户基本信息
     */
    @ApiOperation(value="getInfo", notes="用户获得基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
    })
    @PostMapping("/getInfo")
    public @ResponseBody
    UserModel getInfo(Long userId){
        logger.info("userId:{}",userId);
        return userService.getInfo(userId);
    }

    /**
     * 修改密码
     * @param userId 用户ID
     * @param originPsw 原密码
     * @param newPsw 新密码
     */
    @ApiOperation(value="changePsw", notes="修改密码，判断原密码与新密码是否相同，密码限制10个字符")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "originPsw", value = "原密码", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "newPsw", value = "新密码", required = true ,dataType = "string"),
    })
    @PostMapping("/changePsw")
    public void changePsw(Long userId,String originPsw,String newPsw){
        logger.info("userId:{},originPsw:{},newPsw:{}",userId,originPsw,newPsw);
        userService.changePsw(userId,originPsw,newPsw);
    }

}
