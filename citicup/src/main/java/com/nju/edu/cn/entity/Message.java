package com.nju.edu.cn.entity;

import javax.persistence.*;

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
     * 消息内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 消息接收者
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "`user_id`")
    private User user;

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
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
