package com.nju.edu.cn.controller;

import com.nju.edu.cn.model.APIContext;
import com.nju.edu.cn.service.FileService;
import com.nju.edu.cn.util.GetAccounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * Created by shea on 2018/2/12.
 */
@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/home")
    public String member(HttpServletRequest request){
        logger.info("home");
        return "/home/home";
    }


}
