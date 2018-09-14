package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.FuturesUpdating;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootTest
public class FuturesUpdatingRepositoryTest {

    @Test
    public void testFindByFutures_FuturesId(){}


    @Test
    public void testFindTopByFutures_FuturesIdOrderByUpdateTimeDesc(){}
}
