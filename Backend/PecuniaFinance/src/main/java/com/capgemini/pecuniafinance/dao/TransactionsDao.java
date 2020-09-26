package com.capgemini.pecuniafinance.dao;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Transactions;

@Repository
public interface TransactionsDao extends JpaRepository<Transactions, Integer>{
	
	@Query( value = "select amount from Account where account_Id = :accountId", nativeQuery=true)
	double getBalance(@Param("accountId") long accountid);
	
	@Query(value=" select * from Transactions where account_Id=:accountId  and transaction_date>=:startDate and transaction_date<:endDate",nativeQuery=true)
	List<Transactions> accountSummary(@Param("accountId")long accountId, @Param("startDate")Date startDate,@Param("endDate")Date endDate);
	

}
