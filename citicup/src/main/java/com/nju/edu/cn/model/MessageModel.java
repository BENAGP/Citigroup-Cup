package com.nju.edu.cn.model;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by shea on 2018/9/6.
 */
public class MessageModel {
    public Long messageId;

    /**
     * 是否已读
     */
    public Boolean hasRead;

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
     * 被评论的合约名称
     */
    public String contractName;


    /**
     * 此条评论内容
     */
    public String content;

    /**
     * 被回复的评论ID
     */
    public Long fatherCommentId;

    /**
     * 被回复的评论内容
     */
    public String fatherCommentContent;

    /**
     * 此条评论创建时间
     */
    public Date createTime;

    public Long commentId;

    public MessageModel() {
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFatherCommentId(Long fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public void setFatherCommentContent(String fatherCommentContent) {
        this.fatherCommentContent = fatherCommentContent;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
