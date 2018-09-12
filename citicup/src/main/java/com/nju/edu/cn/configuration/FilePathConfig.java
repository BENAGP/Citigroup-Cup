package com.nju.edu.cn.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by shea on 2018/2/19.
 */
@Configuration
public class FilePathConfig extends WebMvcConfigurerAdapter {
    public static final String PATH = "/Users/wuyoujie/Documents/GitHub/Citigroup-Cup/avatars/";

    //供客户端使用的url前缀
    public static final String URL = "http://localhost:8080/picture/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+PATH);
        super.addResourceHandlers(registry);
    }
}
