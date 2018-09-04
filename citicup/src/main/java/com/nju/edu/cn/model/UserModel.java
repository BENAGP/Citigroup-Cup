package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.User;

/**
 * Created by shea on 2018/9/1.
 */
public class UserModel extends User {
    /**
     * 信息是否完整
     */
    private Boolean isCompleted;

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
