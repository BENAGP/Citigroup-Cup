package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 消息表
 */
@Entity
@Table(name = "`message`")
public class Message {
    @Id
    @Column(name = "`message_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    /**
     * 是否已读
     */
    @Column(name = "`has_read`")
    private Boolean hasRead;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`comment_id`")
    private Comment comment;

    /**
     * 评论者ID
     */
    @Column(name = "`user_id`")
    public Long userId;

    /**
     * 昵称
     */
    @Column(name = "`nickname`")
    private String userNickname;

    /**
     * 头像地址
     */
    @Column(name = "`avatar`")
    private String userAvatar;

    /**
     * 被评论的合约ID
     */
    @Column(name = "`contract_id`")
    private Long contractId;

    /**
     * 被评论的合约名称
     */
    @Column(name = "`contract_name`")
    private String contractName;

    /**
     * 此条评论内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 被回复的评论ID
     */
    @Column(name = "`father_comment_id`")
    private Long fatherCommentId;

    /**
     * 被回复的评论内容
     */
    @Column(name = "`father_comment_content`")
    private String fatherCommentContent;

    /**
     * 此条评论创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 消息接收者
     */
    @ManyToOne()
    @JoinColumn(name = "`receiver_id`")
    private User receiver;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        this.setContractId(comment.getContract().getContractId());
        this.setContractName(comment.getContract().getName());
        this.setContent(comment.getContent());
        this.setCreateTime(comment.getCreateTime());
        this.setFatherCommentId(comment.getFatherCommentId());
        this.setFatherCommentContent(comment.getFatherCommentContent());
        this.setUserAvatar(comment.getUserAvatar());
        this.setUserId(comment.getUserId());
        this.setUserNickname(comment.getUserNickname());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Long fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getFatherCommentContent() {
        return fatherCommentContent;
    }

    public void setFatherCommentContent(String fatherCommentContent) {
        this.fatherCommentContent = fatherCommentContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
}
