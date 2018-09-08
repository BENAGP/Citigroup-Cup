package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Trade findTopByTradeId(Long tradeId);

    Trade findByContract_ContractIdAndRiskLevelAndUser_UserId(Long contractId, Integer riskLevel, Long userId);

    Page<Trade> findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_TypeAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndLiquidityLessThanEqualAndLiquidityGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
            Long userId, Integer riskLevel,Integer type,Date today, Float yieldR, Float yieldL, Float maxDrawdownR, Float maxDrawdownL, Float winRateR, Float winRateL,
            Float profitLossRatioR, Float profitLossRatioL, Float liquidityR, Float liquidityL, Float marketCapitalCapacityR, Float marketCapitalCapacityL, Pageable pageable);

    Page<Trade> findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndLiquidityLessThanEqualAndLiquidityGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
            Long userId, Integer riskLevel,Date today, Float yieldR, Float yieldL, Float maxDrawdownR, Float maxDrawdownL, Float winRateR, Float winRateL,
            Float profitLossRatioR, Float profitLossRatioL, Float liquidityR, Float liquidityL, Float marketCapitalCapacityR, Float marketCapitalCapacityL, Pageable pageable);
}
