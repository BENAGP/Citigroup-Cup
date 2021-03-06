package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findByContract_ContractId(Long contractId, Pageable pageable);

    Comment findByCommentId(Long commentId);

    Integer countByContractId(Long contractId);
}
