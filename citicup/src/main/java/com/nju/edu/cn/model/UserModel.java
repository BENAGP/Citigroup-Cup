package com.nju.edu.cn.model;

/**
 * Created by shea on 2018/9/1.
 */
public class UserModel {

    public Long userId;

    /**
     * 帐号（邮箱）
     */
    public String email;

    /**
     * 昵称
     */
    public String nickname;

    /**
     * 偏好风险等级
     */
    public Integer preferRiskLevel;

    /**
     * 头像地址
     */
    public String avatar;

    /**
     * 信息是否完整
     */
    public Boolean isCompleted;

    public UserModel() {
    }

    public void setIsCompleted(){
        if(this.avatar==null||this.preferRiskLevel==null||this.nickname==null){
            this.isCompleted = false;
        }else {
            this.isCompleted = true;
        }
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPreferRiskLevel(Integer preferRiskLevel) {
        this.preferRiskLevel = preferRiskLevel;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
