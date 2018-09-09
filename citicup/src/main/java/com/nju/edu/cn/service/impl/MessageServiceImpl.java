package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.dao.MessageRepository;
import com.nju.edu.cn.entity.Message;
import com.nju.edu.cn.model.MessageModel;
import com.nju.edu.cn.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;


    @Override
    public List<MessageModel> getHasReadList(Long userId, Integer page, Integer pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(page,pageNum,sort);
        Page<Message> messagePage = messageRepository.findByReceiver_UserIdAndHasRead(userId,true,pageRequest);
        List<Message> messages = messagePage.getContent();
        List<MessageModel> messageModels = new ArrayList<>();
        messages.forEach(message -> {
            MessageModel messageModel = new MessageModel();
            BeanUtils.copyProperties(message,messageModel);
            messageModels.add(messageModel);
        });
        return messageModels;
    }

    @Override
    public List<MessageModel> getHasNotReadList(Long userId, Integer page, Integer pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(page,pageNum,sort);
        Page<Message> messagePage = messageRepository.findByReceiver_UserIdAndHasRead(userId,false,pageRequest);
        List<Message> messages = messagePage.getContent();
        List<MessageModel> messageModels = new ArrayList<>();
        messages.forEach(message -> {
            MessageModel messageModel = new MessageModel();
            BeanUtils.copyProperties(message,messageModel);
            messageModels.add(messageModel);
            message.setHasRead(true);
        });
        messageRepository.saveAll(messages);
        return messageModels;
    }

    @Override
    public void readMessage(Long userId, String messages) {

    }
}
