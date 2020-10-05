package com.capgemini.pecuniafinance.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecuniafinance.service.TransactionService;
import com.capgemini.pecuniafinance.error.AccountIdNotFoundException;
import com.capgemini.pecuniafinance.model.Transactions;
@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
	@Autowired
	private TransactionService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	/* Method:showBalance
	 * Type: RequestMapping
	 * Description: When /showBalance requests mapping, showBalance method is called at Service Layer
	 * @param long: accountId
	 * @return Balance: a balance value is returned to notify that this the account balance.
 	*/
	@RequestMapping("/showBalance/{id}")
	@ExceptionHandler(AccountIdNotFoundException.class)
	public double showBalance(@PathVariable("id") long accountid) {
		logger.trace("Show balance method accessed at controller");
		double balance = 0;
		try {
			
			balance = service.showBalance(accountid);
			
		} catch (Exception e) {

			logger.error(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new AccountIdNotFoundException("Account Not Found");

		}
		return balance;
	}
	
	/* Method:accountSummary
	 * Type: PostMapping
	 * Description: When /accountSummary is mapped, accountSummary method is called at Service Layer
	 * @param Account Id = account_Id
	 * @param Start Date = startDate
	 * @param End Date = endDate
	 * @return List<transactions>: a list of transactions is returned to notify that these are the transactions between the given dates
	*/	
	@GetMapping("/accountSummary/{accountId}/{startDate}/{endDate}")
	public ResponseEntity<List<Transactions>> accountSummary(@PathVariable long accountId, @PathVariable Date startDate, @PathVariable Date endDate) 
	{
		List<Transactions> list = service.accountSummary(accountId, startDate, endDate);
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Please enter Valid details")
	@ExceptionHandler({Exception.class})
	public void handleException() {
		
	}
	
	/* Method:accountValidation
	 * Type: GetMappping
	 * Description: When /accountValidation is mapped, accountValidation method is called at Service Layer
	 * @param Account Id = account_Id
	 * @return validates that account Id is available
	*/			
	@GetMapping("/accountValidation/{accountId}")
	public boolean accountValidation(@PathVariable("accountId") long accountId) {
		return service.accountValidation(accountId);
	}
 
}


