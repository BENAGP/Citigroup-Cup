package com.nju.edu.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shea on 2018/9/1.
 * 收藏表
 */
@Entity
@Table(name = "`collect`")
public class Collect {
    @Id
    @Column(name = "`collect_id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collectId;

//    /**
//     * 收藏的合约ID
//     */
//    @ManyToOne(cascade = {CascadeType.PERSIST})
//    @JoinColumn(name = "`contract_id`")
//    private Contract contract;
//
//    /**
//     * 收藏者
//     */
//    @ManyToOne(cascade = {CascadeType.PERSIST})
//    @JoinColumn(name = "`user_id`")
//    private User user;

    @Column(name = "`contract_id`")
    private Long contractId;

    @Column(name = "`user_id`")
    private Long userId;

    /**
     * 是否取消收藏
     */
    @Column(name = "`deleted`")
    private Boolean deleted;

    /**
     * 收藏时间
     */
    @Column(name = "`create_time`")
    private Date createTime;


    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

//    public Contract getContract() {
//        return contract;
//    }
//
//    public void setContract(Contract contract) {
//        this.contract = contract;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
