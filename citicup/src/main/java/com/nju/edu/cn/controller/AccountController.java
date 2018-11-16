package com.nju.edu.cn.controller;

import com.nju.edu.cn.exception.InvalidRequestException;
import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.util.GetAccounts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by shea on 2018/10/23.
 */
@Api(value = "AccountController", description = "花旗账户接口")
@RestController()
@RequestMapping(value = "/api/account", produces = "application/json;charset=UTF-8")
public class AccountController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * 获得用户的账户信息
     * @return
     */
    @ApiOperation(value="getAccounts", notes="返回已授权您的应用程序的Citi客户持有的所有帐户的摘要。如果客户有多个但类似的帐户，例如两个储蓄帐户，则帐户将在数组accountGroupSummary中返回")
    @GetMapping("/getAccounts")
    public @ResponseBody
    String getAccounts(HttpServletRequest request){
        String realAccessToken = (String) request.getSession().getAttribute("real_access_token");
        logger.info("real_access_token:{} ",realAccessToken);
        String res = "";
        try {
            res = GetAccounts.getAccounts(realAccessToken,request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 获得用户的账户信息
     * @return
     */
    @ApiOperation(value="getPayeeCombine", notes="返回内部国内转移目标和源帐户的有效组合。该信息可用于为所选目的地准备有效的源列表，反之亦然。")
    @GetMapping("/getPayeeCombine")
    public @ResponseBody
    String getPayeeCombine(HttpServletRequest request){
        String realAccessToken = (String) request.getSession().getAttribute("real_access_token");
        logger.info("real_access_token:{} ",realAccessToken);
        String res = "";
        try {
            res = GetAccounts.getPayeeCombine(realAccessToken,request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获得用户的账户信息
     * @return
     */
    @ApiOperation(value="transferPreProgress", notes="创建新的内部国内转移并验证没有错误。应使用此资源的响应来构建预先确认页面，以便客户在确认之前查看交易。")
    @PostMapping("/transferPreProgress")
    public @ResponseBody
    String transferPreProgress(String body,HttpServletRequest request){
        String realAccessToken = (String) request.getSession().getAttribute("real_access_token");
        logger.info("real_access_token:{} ",realAccessToken);
        String res = "";
        try {
            res = GetAccounts.transferPreProgress(realAccessToken,body,request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 获得用户的账户信息
     * @return
     */
    @ApiOperation(value="transferConfirm", notes="确认国内转移。应在成功创建内部传输后调用它。如果成功，它将返回应显示和存储的确认号。")
    @PostMapping("/transferConfirm")
    public @ResponseBody
    String transferConfirm(String body,HttpServletRequest request){
        String realAccessToken = (String) request.getSession().getAttribute("real_access_token");
        logger.info("real_access_token:{} ",realAccessToken);
        String res = "";
        try {
            res = GetAccounts.transferConfirm(realAccessToken,body,request.getSession());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }





}
