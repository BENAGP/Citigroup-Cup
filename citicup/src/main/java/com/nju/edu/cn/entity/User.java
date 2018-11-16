package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by shea on 2018/9/1.
 * 用户表
 */
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "`user_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 账户
     */
    @Column(name = "username")
    private String username;

    /**
     * 帐号（邮箱）
     */
    @Column(name = "`email`")
    private String email;

    /**
     * 昵称
     */
    @Column(name = "`nickname`")
    private String nickname;

    /**
     * 偏好风险等级
     */
    @Column(name = "`prefer_risk_level`")
    private Integer preferRiskLevel;

    /**
     * 头像地址
     */
    @Column(name = "`avatar`")
    private String avatar;

    /**
     * 登录密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 用户的交易，进一步应该筛出deleted=false的
     */
//    @OneToMany(mappedBy = "user")
//    private List<Trade> trades;

    /**
     * 用户的消息
     */
//    @OneToMany(mappedBy = "receiver")
//    private List<Message> messages;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPreferRiskLevel() {
        return preferRiskLevel;
    }

    public void setPreferRiskLevel(Integer preferRiskLevel) {
        this.preferRiskLevel = preferRiskLevel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public List<Trade> getTrades() {
//        return trades;
//    }
//
//    public void setTrades(List<Trade> trades) {
//        this.trades = trades;
//    }
//
//    public List<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }
}
