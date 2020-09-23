package com.capgemini.pecuniafinance.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.pecuniafinance.model.Transactions;

@Repository
public interface TransactionsDao extends JpaRepository<Transactions, Integer>{

}
