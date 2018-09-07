package com.nju.edu.cn.dao;

import com.nju.edu.cn.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by shea on 2018/9/7.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
    Contract findByContractId(Long contractId);
}
