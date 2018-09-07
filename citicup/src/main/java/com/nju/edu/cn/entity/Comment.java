package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 一条评论，可以是单纯的评论，也可以是针对某一条评论的回复
 */
@Entity
@Table(name = "`comment`")
public class Comment {
    @Id
    @Column(name = "`comment_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    /**
     * 评论者ID
     */
    @ManyToOne()
    @JoinColumn(name = "`user_key`")
    private User user;

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
    @ManyToOne()
    @JoinColumn(name = "`contract_key`")
    private Contract contract;

    @Column(name = "`contract_id`")
    private Long contractId;

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
     * 被回复的评论人
     */
    @Column(name = "`father_comment_user_id`")
    private Long fatherCommentUserId;

    /**
     * 被回复的评论内容
     */
    @Column(name = "`father_comment_content`")
    private String fatherCommentContent;

    /**
     * 被回复的评论人头像URL
     */
    @Column(name = "`father_comment_user_avatar`")
    private String fatherCommentUserAvatar;

    /**
     * 被回复的评论人昵称
     */
    @Column(name = "`father_user_nickname`")
    private String fatherUserNickname;

    /**
     * 此条评论创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    @OneToOne(mappedBy = "comment")
    @JoinColumn(name = "`message_key`")
    private Message message;

    @Column(name = "`message_id`")
    private Long messageId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.setUserId(user.getUserId());
        this.setUserNickname(user.getNickname());
        this.setUserAvatar(user.getAvatar());
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
        this.setContractId(contract.getContractId());
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

    public void setFatherComment(Comment fatherComment){
        if(fatherComment!=null){
            this.setFatherCommentContent(fatherComment.getContent());
            this.setFatherCommentId(fatherComment.getCommentId());
            this.setFatherCommentUserAvatar(fatherComment.getUserAvatar());
            this.setFatherCommentUserId(fatherComment.getUserId());
            this.setFatherUserNickname(fatherComment.getUserNickname());
        }
    }

    public Long getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Long fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public Long getFatherCommentUserId() {
        return fatherCommentUserId;
    }

    public void setFatherCommentUserId(Long fatherCommentUserId) {
        this.fatherCommentUserId = fatherCommentUserId;
    }

    public String getFatherCommentContent() {
        return fatherCommentContent;
    }

    public void setFatherCommentContent(String fatherCommentContent) {
        this.fatherCommentContent = fatherCommentContent;
    }

    public String getFatherCommentUserAvatar() {
        return fatherCommentUserAvatar;
    }

    public void setFatherCommentUserAvatar(String fatherCommentUserAvatar) {
        this.fatherCommentUserAvatar = fatherCommentUserAvatar;
    }

    public String getFatherUserNickname() {
        return fatherUserNickname;
    }

    public void setFatherUserNickname(String fatherUserNickname) {
        this.fatherUserNickname = fatherUserNickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
