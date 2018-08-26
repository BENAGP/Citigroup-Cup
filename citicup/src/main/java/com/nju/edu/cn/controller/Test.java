package com.nju.edu.cn.controller;

import com.alibaba.fastjson.JSON;
import com.nju.edu.cn.util.UuidHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by shea on 2018/7/29.
 */
@RestController
@RequestMapping(value = "/api/gene", produces = "application/json;charset=UTF-8")
public class Test {
    @GetMapping("/uuid")
    public @ResponseBody String uuid(){
        String res = UUID.randomUUID().toString() ;
        System.out.println(res);

        return JSON.toJSONString(res);
    }
}
