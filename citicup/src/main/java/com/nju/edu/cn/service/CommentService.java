package com.nju.edu.cn.service;

import com.nju.edu.cn.model.CommentModel;

import java.util.List;

/**
 * Created by shea on 2018/9/6.
 */
public interface CommentService {
    List<CommentModel> getComments(Long contractId, Long userId, Integer page, Integer pageNum);

    void reply(Long contractId, Long userId, Long commentId, String content);

    void postComment(Long contractId, Long userId, String content);

    Integer getPageNum(Long contractId);
}
