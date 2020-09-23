package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Loan;

@Repository
public interface LoanDao extends JpaRepository<Loan, Integer>{

}
