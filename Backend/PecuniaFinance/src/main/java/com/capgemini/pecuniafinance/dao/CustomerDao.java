package com.capgemini.pecuniafinance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
}
