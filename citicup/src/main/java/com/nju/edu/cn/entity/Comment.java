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
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`user_id`")
    private User user;

    /**
     * 被评论的合约ID
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`contract_id`")
    private Contract contract;

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
     * 此条评论创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    @OneToOne(mappedBy = "comment",cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`message_id`")
    private Message message;

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
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
}
