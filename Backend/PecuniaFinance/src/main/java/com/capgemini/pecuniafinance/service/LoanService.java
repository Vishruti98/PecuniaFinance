package com.capgemini.pecuniafinance.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.capgemini.pecuniafinance.model.Loan;

@Component
public interface LoanService {

	public String loanDisbursal(Loan loan);
	public List<Loan> getLoanHistory (long account_id);
	public Loan addLoanRequest(Loan loan);
		
}
