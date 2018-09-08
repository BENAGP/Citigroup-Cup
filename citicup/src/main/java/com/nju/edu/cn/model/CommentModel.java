package com.nju.edu.cn.model;

import com.nju.edu.cn.entity.Comment;
import com.nju.edu.cn.entity.Contract;
import com.nju.edu.cn.entity.User;

import java.util.Date;

/**
 * Created by shea on 2018/9/6.
 */
public class CommentModel{
    public Long commentId;

    /**
     * 评论者ID
     */
    public Long userId;

    /**
     * 昵称
     */
    public String userNickname;

    /**
     * 头像地址
     */
    public String userAvatar;

    /**
     * 被评论的合约ID
     */
    public Long contractId;

    /**
     * 此条评论内容
     */
    public String content;

    /**
     * 被回复的评论ID
     */
    public Long fatherCommentId;

    /**
     * 被回复的评论人
     */
    public Long fatherCommentUserId;

    /**
     * 被回复的评论内容
     */
    public String fatherCommentContent;

    /**
     * 被回复的评论人头像URL
     */
    public String fatherCommentUserAvatar;

    /**
     * 被回复的评论人昵称
     */
    public String fatherUserNickname;

    /**
     * 此条评论创建时间
     */
    public Date createTime;

    public CommentModel() {
    }



}
