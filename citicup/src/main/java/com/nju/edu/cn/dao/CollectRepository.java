package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface CollectRepository extends JpaRepository<Collect,Long> {
    Page<Collect> findByUserIdAndDeleted(Long userId,Boolean deleted,Pageable pageable);

    Collect findByContractIdAndUserId(Long contractId,Long userId);

    List<Collect> findByUserIdAndDeleted(Long userId,Boolean deleted);
}
