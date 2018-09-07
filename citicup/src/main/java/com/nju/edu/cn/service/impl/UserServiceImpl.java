package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.dao.UserRepository;
import com.nju.edu.cn.entity.User;
import com.nju.edu.cn.exception.InvalidRequestException;
import com.nju.edu.cn.model.UserModel;
import com.nju.edu.cn.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by shea on 2018/9/7.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void register(String email, String psw) {
        User exist = userRepository.findByEmail(email);
        if(exist!=null)throw new InvalidRequestException("邮箱已被注册");
        User user = new User();
        user.setEmail(email);
        user.setPassword(psw);
        userRepository.save(user);

    }

    @Override
    public UserModel login(String email, String psw) {
        User user = userRepository.findByEmail(email);
        if(user==null||!user.getPassword().equals(psw))throw new InvalidRequestException("用户名或密码错误");
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel,user);
        userModel.setIsCompleted();
        return userModel;
    }

    @Override
    public UserModel perfectInfo(Long userId, String email, String nickname, Integer preferRiskLevel, String avatar) {
        User user = userRepository.findByUserId(userId);
        if(user==null)throw new InvalidRequestException("用户名错误");
        user.setNickname(nickname);
        user.setAvatar(avatar);
        user.setPreferRiskLevel(preferRiskLevel);
        userRepository.save(user);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel,user);
        userModel.setIsCompleted();
        return userModel;
    }

    @Override
    public UserModel getInfo(Long userId) {
        User user = userRepository.findByUserId(userId);
        if(user==null)throw new InvalidRequestException("用户名不存在");
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel,user);
        userModel.setIsCompleted();
        return userModel;
    }

    @Override
    public UserModel changePsw(Long userId, String originPsw, String newPsw) {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new InvalidRequestException("用户不存在");
        if (!user.getPassword().equals(originPsw)) throw new InvalidRequestException("原密码错误");
        user.setPassword(newPsw);
        userRepository.save(user);
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userModel, user);
        userModel.setIsCompleted();
        return userModel;
    }
}