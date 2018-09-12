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
    Trade findByTradeId(Long tradeId);

    Page<Trade> findByUser_UserIdAndDeletedIsFalse(Long userId,Pageable pageable);

    Trade findByContract_ContractIdAndRiskLevelAndUser_UserId(Long contractId, Integer riskLevel, Long userId);

    Page<Trade> findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_TypeAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
            Long userId, Integer riskLevel,Integer type,Date today, Double yieldR, Double yieldL, Double maxDrawdownR, Double maxDrawdownL, Double winRateR, Double winRateL,
            Double profitLossRatioR, Double profitLossRatioL, Double marketCapitalCapacityR, Double marketCapitalCapacityL, Pageable pageable);

    Page<Trade> findByUser_UserIdAndRiskLevelAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
            Long userId, Integer riskLevel,Date today, Double yieldR, Double yieldL, Double maxDrawdownR, Double maxDrawdownL, Double winRateR, Double winRateL,
            Double profitLossRatioR, Double profitLossRatioL, Double marketCapitalCapacityR, Double marketCapitalCapacityL, Pageable pageable);

    Page<Trade> findByUser_UserIdAndContract_NearbyFutures_LastTradingDateBeforeAndYieldLessThanEqualAndYieldGreaterThanEqualAndMaxDrawdownLessThanEqualAndMaxDrawdownGreaterThanEqualAndWinRateLessThanEqualAndWinRateGreaterThanEqualAndProfitLossRatioLessThanEqualAndProfitLossRatioGreaterThanEqualAndMarketCapitalCapacityLessThanEqualAndMarketCapitalCapacityGreaterThanEqual(
            Long userId,Date today, Double yieldR, Double yieldL, Double maxDrawdownR, Double maxDrawdownL, Double winRateR, Double winRateL,
            Double profitLossRatioR, Double profitLossRatioL, Double marketCapitalCapacityR, Double marketCapitalCapacityL, Pageable pageable);

    List<Trade> findTop4ByRiskLevel(Integer riskLevel);
}
