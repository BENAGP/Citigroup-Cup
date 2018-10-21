package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Page<Message> findByReceiver_UserIdAndHasRead(Long receiverId, Boolean hasRead, Pageable pageable);

    Page<Message> findByReceiver_UserIdAndFatherCommentIdNull(Long receiverId, Pageable pageable);

    Page<Message> findByReceiver_UserIdAndFatherCommentIdNotNull(Long receiverId, Pageable pageable);

    List<Message> findByMessageIdIn(List<Long> messageIdList);

    Integer countByReceiver_UserIdAndFatherCommentIdNull(Long userId);

    Integer countByReceiver_UserIdAndFatherCommentIdNotNull(Long userId);

}
