package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByUserId(Long userId);

}
