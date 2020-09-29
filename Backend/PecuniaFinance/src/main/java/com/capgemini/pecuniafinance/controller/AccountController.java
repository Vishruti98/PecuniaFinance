package com.capgemini.pecuniafinance.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.pecuniafinance.error.AccountIdNotFoundException;
import com.capgemini.pecuniafinance.model.Transactions;
import com.capgemini.pecuniafinance.service.AccountService;

@RestController
@RequestMapping("/account") 
public class AccountController {
	
	Logger logger=LoggerFactory.getLogger(AccountController.class);
	@Autowired
	AccountService accountService;
	
	/* Method:creditUsingSlip
	 * Type: PutMapping
	 * Description: When /creditSlip is mapped with client request, creditUsingSlip method is called at Service Layer
	 * @param Transaction: transaction
	 * @return message: a String object is returned to notify that credit is done successfully
 	*/
	@PutMapping("/creditSlip")
	public String creditUsingSlip(@RequestBody Transactions transaction){
		logger.trace("Credit is done using slip");
		String msg=null;
	try {
		msg = accountService.creditUsingSlip(transaction);
		System.out.println(msg);
			if (msg == "Details Incorrect.Please try again")
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
	catch (Exception e) {
			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
		return msg;
	}
	
	/* Method:debitUsingSlip
	 * Type: PutMapping
	 * Description: When /debitSlip is mapped with client request, debitUsingSlip method is called at Service Layer
	 * @param Transaction: transaction
	 * @return message: a String object is returned to notify that debit is done successfully
 	*/
	@PutMapping("/debitSlip")
	public String debitUsingSlip(@RequestBody Transactions transaction){
		logger.trace("Debit is done using slip");
		String msg=null;
	try {
		msg = accountService.debitUsingSlip(transaction);
		System.out.println(msg);
			if (msg == "Details Incorrect.Please try again")
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
	catch (Exception e) {
			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
		return msg;
	}
	
	/* Method:creditUsingCheque
	 * Type: PutMapping
	 * Description: When /creditCheque is mapped with client request, creditUsingCheque method is called at Service Layer
	 * @param Transaction: transaction
	 * @return message: a String object is returned to notify that credit is done successfully
 	*/
	@PutMapping("/creditCheque")
	public String creditUsingCheque(@RequestBody Transactions transaction){
		logger.trace("Credit is done using slip");
		String msg=null;
	try {
		msg = accountService.creditUsingCheque(transaction);
		System.out.println(msg);
			if (msg == "Details Incorrect.Please try again")
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
	catch (Exception e) {
			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
		return msg;
	}
	
	/* Method:debitUsingCheque
	 * Type: PutMapping
	 * Description: When /debitCheque is mapped with client request, debitUsingCheque method is called at Service Layer
	 * @param Transaction: transaction
	 * @return message: a String object is returned to notify that debit is done successfully
 	*/
	@PutMapping("/debitCheque")
	public String debitUsingCheque(@RequestBody Transactions transaction) {
		logger.trace("Debit is done using cheque");
		String msg=null;
	try {
		msg = accountService.debitUsingCheque(transaction);
		System.out.println(msg);
			if (msg == "Details Incorrect.Please try again")
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
	catch (Exception e) {
			logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new AccountIdNotFoundException("Details Incorrect.Please try again");
	} 
		return msg;
	}
	
}
