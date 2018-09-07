package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface CollectRepository extends JpaRepository<Collect,Long> {
    Page<Collect> findByUser_UserIdAndDeleted(Long userId,Boolean deleted,Pageable pageable);

    Collect findByContract_ContractIdAndUser_UserId(Long contractId,Long userId);
}
