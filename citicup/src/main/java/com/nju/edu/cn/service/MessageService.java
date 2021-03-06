package com.nju.edu.cn.service;

import com.nju.edu.cn.model.MessageModel;

import java.util.List;

/**
 * Created by shea on 2018/9/6.
 */
public interface MessageService {

    List<MessageModel> getHasReadList(Long userId, Integer page, Integer pageNum);

    List<MessageModel> getHasNotReadList(Long userId, Integer page, Integer pageNum);

    void readMessage(Long userId, List<Long> messageIdList);

    List<MessageModel> getList(Long userId, Integer page, Integer pageNum, Boolean isSystemMessage);

    Integer getTotalNum(Long userId, Boolean isSystemMessage);
}
