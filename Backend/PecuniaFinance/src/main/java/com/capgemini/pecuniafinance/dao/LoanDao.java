package com.capgemini.pecuniafinance.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Loan;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long>{

	//Retrieves Loan History for a given account number
	@Query( value = "select * from loan where account_id = :accountid ", nativeQuery=true)
	List<Loan> getLoanHistory(@Param("accountid") long account_id);
	
	//Modifies the Loan Status when the loan is accepted or rejected
	@Query(value="update loan set loan_status = :status where loan_id = :loan_id  ",nativeQuery=true)
	@Modifying
	@Transactional
	int updateStatus(@Param("loan_id") long loan_id, @Param("status") String status);
	
	//Modifies the Account Balance when the loan is disbursed
	@Query(value="update account set amount = :balance where account_id = :accountid  ",nativeQuery=true)
	@Modifying
	@Transactional
	int updateBalance(@Param("accountid") long account_id, @Param("balance") double amount);
	
	
}
