package com.capgemini.pecuniafinance.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.capgemini.pecuniafinance.error.InvalidValidationException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private long customerId;
	
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="contact",unique=true,nullable=false)
	private long contact;
	
	@Column(name="aadhaar",unique=true,nullable=false)
	private long aadhaar;
	
	@Column(name="pan",unique=true,nullable=false)
	private String pan;
	
	@Column(name="dob",nullable=false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dob;
	
	@Column(name="gender",nullable=false)
	private String gender;
	
	@OneToOne(cascade = CascadeType.MERGE, mappedBy = "customer")
	@JoinColumn(name = "account_id")
	@JsonManagedReference
	private Account account;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(long customerId, String name,String password, long contact, long aadhaar, String pan, Date dob,
			String gender, Account account) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.password = password;
		this.contact = contact;
		this.aadhaar = aadhaar;
		this.pan = pan;
		this.dob = dob;
		this.gender = gender;
		this.account = account;
	}

	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		try {
			if(String.valueOf(contact).length()==10)
				this.contact = contact;
			else 
				throw new InvalidValidationException("Invalid Validation");
		}
		catch(Exception e) {
			throw new InvalidValidationException("Invalid Validation");
		}
	}
		
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(long aadhaar) {
		try {
			if(String.valueOf(aadhaar).length()==12)
				this.aadhaar = aadhaar;
			else 
				throw new InvalidValidationException("Invalid Validation");
		}
		catch(Exception e) {
			throw new InvalidValidationException("Invalid Validation");
		}
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		try {
			if(String.valueOf(pan).length()==10)
				this.pan = pan;
			else 
				throw new InvalidValidationException("Invalid Validation");
		}
		catch(Exception e) {
			throw new InvalidValidationException("Invalid Validation");
		}
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
