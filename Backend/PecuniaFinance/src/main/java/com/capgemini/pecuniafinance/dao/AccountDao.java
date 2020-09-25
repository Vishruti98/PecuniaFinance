package com.capgemini.pecuniafinance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

	
}
