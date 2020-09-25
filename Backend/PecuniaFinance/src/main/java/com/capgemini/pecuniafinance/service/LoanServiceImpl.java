package com.capgemini.pecuniafinance.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecuniafinance.dao.LoanDao;
import com.capgemini.pecuniafinance.model.Loan;

@Service("loanServiceImpl")
public class LoanServiceImpl implements LoanService{

	@Autowired
	LoanDao loanDao;
	
	
	Loan loan;
	
	//Creates and adds a new Loan Request
	@Override
	 public Loan addLoanRequest( Loan loan) {
			
			Optional <Loan> findById = loanDao.findById(loan.getLoanId());
			if(!findById.isPresent())
			{   loan.setLoanStatus("Pending");
			    if(loan.getLoanType()=="Holiday Loan")
			    	loan.setRateOfInterest(11.67);
			    else if(loan.getLoanType()=="Personal Loan")
			    	loan.setRateOfInterest(10.75);
			    else if(loan.getLoanType()=="Medical Loan")
			    	loan.setRateOfInterest(13.2);
			    else
			    	loan.setRateOfInterest(12.99);
				Loan savedLoan = loanDao.save(loan);
				return savedLoan;
			}
			return null;
		}
	 
	//Checks whether loan should be disbursed or not and updates the account balance accordingly
	@Override
	public String loanDisbursal(Loan loan) {
		
		if(loan.getCreditScore()>=670 && (loan.getLoanAmount()>=1000 && loan.getLoanAmount()<=2500000) && (loan.getTenure()>=12 && loan.getTenure()<=240)) {
			
			loanDao.updateStatus(loan.getLoanId(), "Accepted");

			loanDao.updateBalance(loan.getAccount().getAccountId(), loan.getAccount().getAmount()+loan.getLoanAmount());
			
			return "Accepted";
		}
		    loanDao.updateStatus(loan.getLoanId(), "Rejected");
		
		    return "Rejected";
	}

  //Fetches loan history for an Account Id
	@Override
	public List<Loan> getLoanHistory(long account_id) {
		
		return loanDao.getLoanHistory(account_id);
		
	}
	

}
