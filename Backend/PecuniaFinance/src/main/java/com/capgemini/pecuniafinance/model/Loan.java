package com.capgemini.pecuniafinance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="loan_id")
	private long loanId;
	
	@Column(name="loan_amount")
	private double loanAmount;
	
	@Column(name="tenure")
	private int tenure;
	
	@Column(name="credit_score")
	private int creditScore;
	
	@Column(name="rate_of_interest")
	private double rateOfInterest;
	
	@Column(name="loan_status")
	private String loanStatus;
	
	@Column(name="loan_type")
	private String loanType;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="account_id")
    private Account account;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(long loanId, double loanAmount, int tenure, int creditScore, double rateOfInterest, String loanStatus,
			String loanType, Account account) {
		super();
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.creditScore = creditScore;
		this.rateOfInterest = rateOfInterest;
		this.loanStatus = loanStatus;
		this.loanType = loanType;
		this.account = account;
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
