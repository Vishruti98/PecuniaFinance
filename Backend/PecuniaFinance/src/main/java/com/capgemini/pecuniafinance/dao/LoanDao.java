package com.capgemini.pecuniafinance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Loan;

@Repository
public interface LoanDao extends JpaRepository<Loan, Integer>{

}
