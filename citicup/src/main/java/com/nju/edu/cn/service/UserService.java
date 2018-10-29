package com.nju.edu.cn.service;

import com.nju.edu.cn.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shea on 2018/9/6.
 */
public interface UserService {

    public UserModel register(String email,String psw);

    public UserModel login(String username);

    public UserModel firstLogin(String username,String nickname);

    public Boolean isFirstLogin(String username);

    public String postAvatar(Long userId, MultipartFile multipartFile);

    public UserModel perfectInfo(Long userId, String email, String nickname, Integer preferRiskLevel,String avatar);

    public UserModel getInfo(Long userId);

    public UserModel changePsw(Long userId,String originPsw,String newPsw);
}
