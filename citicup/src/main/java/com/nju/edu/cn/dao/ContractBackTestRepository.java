package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.ContractBackTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface ContractBackTestRepository extends JpaRepository<ContractBackTest,Long>{
    List<ContractBackTest> findByTrade_TradeId(Long tradeId);

    List<ContractBackTest> findByTrade_User_UserIdAndTrade_RiskLevelAndTrade_Contract_ContractIdAndCreateTimeAfterAndCreateTimeBefore(Long userId, Integer riskLevel, Long contractId, Date left,Date right);
    List<ContractBackTest> findByTrade_User_UserIdAndTrade_RiskLevelAndTrade_Contract_ContractIdOrderByCreateTime(Long userId, Integer riskLevel, Long contractId);
}
