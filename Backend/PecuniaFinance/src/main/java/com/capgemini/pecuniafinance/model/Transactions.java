package com.capgemini.pecuniafinance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="transaction_id")
	private long transactionId;
	@Column(name="transaction_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date transactionDate;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_amount")
	private double transactionAmount;
	
	@Column(name="transaction_mode")
	private String transactionMode;
	
	@Column(name="cheque_number")
	private long chequeNumber;
	
	@Column(name="ifsc")
	private String ifsc;
	
	@Column(name="account_id")
	private long accountId;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(long transactionId, Date transactionDate, String transactionType, double transactionAmount,
			String transactionMode, long chequeNumber, String ifsc, long accountId) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionMode = transactionMode;
		this.chequeNumber = chequeNumber;
		this.ifsc = ifsc;
		this.accountId = accountId;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public long getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(long chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}
