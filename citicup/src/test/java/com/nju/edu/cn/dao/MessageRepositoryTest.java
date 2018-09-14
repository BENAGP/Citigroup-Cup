package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Message;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SpringBootTest
public class MessageRepositoryTest {

    @Test
    public void testFindByReceiver_UserIdAndHasRead(){}

}
