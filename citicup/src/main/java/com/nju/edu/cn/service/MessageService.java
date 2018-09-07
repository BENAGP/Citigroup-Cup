package com.nju.edu.cn.service;

import com.nju.edu.cn.model.MessageModel;

import java.util.List;

/**
 * Created by shea on 2018/9/6.
 */
public interface MessageService {

    public List<MessageModel> getHasReadList(Long userId,Integer page,Integer pageNum);

    public List<MessageModel> getHasNotReadList(Long userId,Integer page,Integer pageNum);

    public void readMessage(Long userId,String messages);
}
