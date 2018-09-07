package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Page<Message> findByReceiver_UserIdAndHasRead(Long receiverId, Boolean hasRead, Pageable pageable);

}
