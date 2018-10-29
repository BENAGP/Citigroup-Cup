package com.nju.edu.cn.configuration;

import com.nju.edu.cn.controller.UserController;
import com.nju.edu.cn.exception.InvalidRequestException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by shea on 2018/10/28.
 */
public class AuthorizeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String realAccessToken = (String) request.getSession().getAttribute("real_access_token");
        if(realAccessToken==null){
            throw new InvalidRequestException("请先登录");
        }
    }

    @Override
    public void destroy() {

    }
}
