package com.capgemini.pecuniafinance.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecuniafinance.service.TransactionService;
import com.capgemini.pecuniafinance.error.AccountIdNotFound;
import com.capgemini.pecuniafinance.model.Transactions;
@RestController
@RequestMapping("/bank")
public class TransactionController {
	@Autowired
	private TransactionService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	/* Method:showBalance
	 * Type: RequestMapping
	 * Description: When /showBalance requests mapping, showBalance method is called at Service Layer
	 * @param long: accountid
	 * @return Balance: a balance value is returned to notify that this the account balance.
 	*/
	@RequestMapping("/showBalance/{id}")
	@ExceptionHandler(AccountIdNotFound.class)
	public double showBalance(@PathVariable("id") long accountid) {
		logger.trace("Show balance method accessed at controller");
		double balance = 0;
		try {
			balance = service.showBalance(accountid);

		} catch (Exception e) {

			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return balance;
	}
	
	/* Method:accountSummary
	 * Type: PostMapping
	 * Description: When /accountSummary is mapped, accountSummary method is called at Service Layer
	 * @param Passbook: passbook
	 * @return List<transactions>: a list of transactions is returned to notify that these are the transactions between the given dates
	*/	
	@PostMapping("/accountSummary")
	public ResponseEntity<List<Transactions>> accountSummary(@RequestBody Passbook passbook) 
	{
		logger.trace("Account summary method accessed at controller");
		List<Transactions> list = service.accountSummary(passbook.accountId, passbook.startDate,passbook.endDate);
		System.out.println(list);
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}
	

}

class Passbook{
	public long accountId;
	public Date startDate;
	public Date endDate;
	
}
