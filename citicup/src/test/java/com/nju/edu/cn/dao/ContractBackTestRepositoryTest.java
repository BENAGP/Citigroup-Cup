package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.ContractBackTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ContractBackTestRepositoryTest{

    @Test
    public void testFindByTrade_User_UserIdAndTrade_RiskLevelAndTrade_Contract_ContractIdAndCreateTimeAfterAndCreateTimeBefore(){}

    @Test
    public void testFindByTrade_User_UserIdAndTrade_RiskLevelAndTrade_Contract_ContractIdOrderByCreateTime(){}
}
