package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.ContractBackTestParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/8.
 */
@Repository
public interface ContractBackTestParamsRepository extends JpaRepository<ContractBackTestParams,Long> {
}
