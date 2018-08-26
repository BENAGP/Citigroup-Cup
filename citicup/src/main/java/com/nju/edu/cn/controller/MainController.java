package com.nju.edu.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by shea on 2018/2/12.
 */
@Controller
public class MainController {

    @GetMapping("/home")
    public String member(){
        System.out.println("call 爆吧 index");
        return "index";
    }

}
