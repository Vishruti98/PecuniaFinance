package com.capgemini.pecuniafinance.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecuniafinance.dao.AccountDao;
import com.capgemini.pecuniafinance.dao.LoanDao;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Loan;

@Service("loanServiceImpl")
public class LoanServiceImpl implements LoanService{

	@Autowired
	LoanDao loanDao;
	@Autowired
	AccountDao accountDao;
	
	Loan loan;
	
	/* Method:addLoanRequest
	 * Description: When addLoanRequest method is called, after assigning LoanStatus and RateOfInterest the Loan object is persisted in the database
	 * @param Loan: loan
	 * @return Loan: a Loan object is returned to notify that a new Loan Request is added
 	*/
	@Override
	 public Loan addLoanRequest( Loan loan) {
			
			Optional <Account> findById = accountDao.findById(loan.getAccount().getAccountId());
			if(findById.isPresent())
			{   loan.setLoanStatus("Pending");
			    if(loan.getLoanType().equalsIgnoreCase("Education Loan"))
			    	loan.setRateOfInterest(11.67);
			    else if(loan.getLoanType().equalsIgnoreCase("Personal Loan"))
			    	loan.setRateOfInterest(10.75);
			    else if(loan.getLoanType().equalsIgnoreCase("Medical Loan"))
			    	loan.setRateOfInterest(13.2);
			    else
			    	loan.setRateOfInterest(12.99);
				Loan savedLoan = loanDao.save(loan);
				return savedLoan;
			}
			return null;
		}
	 
	/* Method:loanDisbursal
	 * Description: When loanDisbursal method is called, the loan request is checked against the following parameters-
	 *              1. Credit Score should be greater than or equal to 670.
	 *              2. Loan Amount should be between 1000 and 2500000.
	 *              3. Loan Tenure should be between 12 and 240 months.
	 * @param  Loan: loan
	 * @return String: a string is returned to notify that Loan Request is accepted or rejected
	*/
	@Override
	public String loanDisbursal(Loan loan) {
		Optional <Loan> findById = loanDao.findById(loan.getLoanId());
		if(findById.isPresent()) {
		if(loan.getCreditScore()>=670 && (loan.getLoanAmount()>=1000 && loan.getLoanAmount()<=2500000) && (loan.getTenure()>=12 && loan.getTenure()<=240)) {
			
			loanDao.updateStatus(loan.getLoanId(), "Accepted");
			
			double balance=loanDao.getAccountBalance(loan.getAccount().getAccountId());

			loanDao.updateBalance(loan.getAccount().getAccountId(), balance+loan.getLoanAmount());
			
			return "Accepted";
		}
		    loanDao.updateStatus(loan.getLoanId(), "Rejected");
		
		    return "Rejected";
		}
		return null;
	}

	/* Method:getLoanHistory
	 * Description: When it is mapped with client request, getAllCustomer method is called at Service Layer
	 * @param  Long: account_id
	 * @return List<Loan>: a list of all loan request w.r.t a particular account_id are returned
	*/
	@Override
	public List<Loan> getLoanHistory(long account_id) {
		Optional <Account> findById = accountDao.findById(account_id);
		if(findById.isPresent()) {
		return loanDao.getLoanHistory(account_id);
		}
		return null;
		
	}
	

}
