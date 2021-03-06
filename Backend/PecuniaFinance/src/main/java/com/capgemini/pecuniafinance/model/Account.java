package com.capgemini.pecuniafinance.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity 
public class Account {

	@Id
	@SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 67582300, allocationSize = 100)
	@GeneratedValue(generator = "mySeqGen")
	@Column(name="account_id")
	private long accountId;
	
	@Column(name="branch",nullable=false)
	private String branch;
	
	@Column(name="account_type",nullable=false)
	private String accountType;
	
	@Column(name="amount")
	private double amount;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;
	
	
	
	public Account(long accountId, String branch, String accountType, double amount, Customer customer) {
		super();
		this.accountId = accountId;
		this.branch = branch;
		this.accountType = accountType;
		this.amount = amount;
		this.customer = customer;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
