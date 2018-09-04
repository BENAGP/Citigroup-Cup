package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shea on 2018/9/1.
 */
@Api(value = "UserController", description = "用户接口")
@RestController()
@RequestMapping(value = "/api/user", produces = "application/json;charset=UTF-8")
public class UserController {
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
    public void register(String email,String psw){

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
    UserModel login(String email, String psw){
        return new UserModel();
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
        return new UserModel();
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
        return new UserModel();
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

    }

}
