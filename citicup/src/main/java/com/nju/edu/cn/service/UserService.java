package com.nju.edu.cn.service;

import com.nju.edu.cn.model.UserModel;

/**
 * Created by shea on 2018/9/6.
 */
public interface UserService {

    public void register(String email,String psw);

    public UserModel login(String email,String psw);

    public UserModel perfectInfo(Long userId, String email, String nickname, Integer preferRiskLevel,String avatar);

    public UserModel getInfo(Long userId);

    public UserModel changePsw(Long userId,String originPsw,String newPsw);
}
