package com.capgemini.pecuniafinance.service;

import com.capgemini.pecuniafinance.model.Transactions;

public interface AccountService {
	public String creditUsingSlip(Transactions transaction);
	public String creditUsingCheque(Transactions transaction);
	public String debitUsingSlip(Transactions transaction);
	public String debitUsingCheque(Transactions transaction);
}
