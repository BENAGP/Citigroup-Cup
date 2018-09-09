package com.nju.edu.cn.service.impl;

import com.nju.edu.cn.dao.CommentRepository;
import com.nju.edu.cn.dao.ContractRepository;
import com.nju.edu.cn.dao.MessageRepository;
import com.nju.edu.cn.dao.UserRepository;
import com.nju.edu.cn.entity.Comment;
import com.nju.edu.cn.entity.Contract;
import com.nju.edu.cn.entity.Message;
import com.nju.edu.cn.entity.User;
import com.nju.edu.cn.exception.InvalidRequestException;
import com.nju.edu.cn.model.CommentModel;
import com.nju.edu.cn.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<CommentModel> getComments(Long contractId, Long userId, Integer page, Integer pageNum) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest pageRequest = new PageRequest(page,pageNum,sort);
        Page<Comment> commentPage = commentRepository.findByContract_ContractId(contractId,pageRequest);
        List<Comment> comments = commentPage.getContent();
        List<CommentModel> commentModels = new ArrayList<>();
        comments.forEach(comment -> {
            CommentModel commentModel = new CommentModel();
            BeanUtils.copyProperties(comment,commentModel);
            commentModels.add(commentModel);
        });
        return commentModels;
    }

    @Override
    public void reply(Long contractId, Long userId, Long commentId, String content) {
        Comment fatherComment = commentRepository.findByCommentId(commentId);
        Contract contract = contractRepository.findByContractId(contractId);
        if(contract==null)throw new InvalidRequestException("合约不存在");
        User user = userRepository.findByUserId(userId);
        if(user==null)throw new InvalidRequestException("评论用户不存在");
        User fatherCommentUser = userRepository.findByUserId(fatherComment.getFatherCommentUserId());
        if(fatherCommentUser==null)throw new InvalidRequestException("被回复用户不存在");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setContract(contract);
        comment.setUser(user);
        comment.setFatherComment(fatherComment);
        comment.setCreateTime(new Date(System.currentTimeMillis()));
        comment = commentRepository.save(comment);
        //向被回复者发送消息
        Message message = new Message();
        message.setComment(comment);
        message.setReceiver(fatherCommentUser);
        messageRepository.save(message);

    }

    @Override
    public void postComment(Long contractId, Long userId, String content) {
        Contract contract = contractRepository.findByContractId(contractId);
        if(contract==null)throw new InvalidRequestException("合约不存在");
        User user = userRepository.findByUserId(userId);
        if(user==null)throw new InvalidRequestException("评论用户不存在");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setContract(contract);
        comment.setUser(user);
        comment.setCreateTime(new Date(System.currentTimeMillis()));
        commentRepository.save(comment);
    }
}
