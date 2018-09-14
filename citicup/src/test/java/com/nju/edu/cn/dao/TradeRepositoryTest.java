package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Trade;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TradeRepositoryTest {

    @Test
    public void testFindByTradeId(){}


    @Test
    public void testFindByUser_UserIdAndDeletedIsFalse(){}


    @Test
    public void testFindByContract_ContractIdAndRiskLevelAndUser_UserId(){}
    @Test
    public void testFindByUser_UserIdAndRiskLevelAndContract_NearbyFutures_TypeAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(){}

    @Test
    public void testFindByUser_UserIdAndRiskLevelAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(){}

    @Test
    public void testFindByUser_UserIdAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(){}

    @Test
    public void testFindTop4ByRiskLevel(){}
}
