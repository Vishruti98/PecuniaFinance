package com.capgemini.pecuniafinance.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.pecuniafinance.error.RecordNotFoundException;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Loan;
import com.capgemini.pecuniafinance.service.LoanService;
@RestController
@RequestMapping("/pecuniafinance")
@CrossOrigin("http://localhost:4200")
public class LoanController {

	@Autowired
	private LoanService loanService;
	String status;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
	
	/* Method:addLoanRequest
	 * Type: PostMapping
	 * Description: When /addLoanRequest is mapped with client request, addLoanRequest method is called at Service Layer
	 * @param Loan: loan, Long:account_id
	 * @return Loan: a Loan object is returned to notify that a new Loan Request is added
 	*/
	@PostMapping("/addLoanRequest/{id}")
	public Loan addLoanRequest(@RequestBody Loan loan,@PathVariable("id") long account_id){
		Loan loanRequest = new Loan();
		Account account = new Account();
		account.setAccountId(account_id);
		loan.setAccount(account);
		
		try {
			loanRequest = loanService.addLoanRequest(loan);

			if (loanRequest == null)
				throw new RecordNotFoundException("Account Id does not exist.");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Account Id does not exist.");

		}
		return loanRequest;

	}
	
	/* Method:loanDisbursal
	 * Type: PutMapping
	 * Description: When /loanDisbursal is mapped with client request, loanDisbursal method is called at Service Layer
	 * @param  Loan: loan
	 * @return String: a string is returned to notify that Loan Request is accepted or rejected
	*/
	@PutMapping("/loanDisbursal")
	public String loanDisbursal(@RequestBody Loan loan) {
		

		try {
			status = loanService.loanDisbursal(loan);

			if (status == null)
				throw new RecordNotFoundException("Loan Id does not exist.");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Loan Id does not exist.");

		}
		return status;

	}
	
	/* Method:showLoanHistory
	 * Type: GetMapping
	 * Description: When it is mapped with client request, getAllCustomer method is called at Service Layer
	 * @param  Long: account_id
	 * @return List<Loan>: a list of all loan request w.r.t a particular account_id are returned
	*/
	@GetMapping("/showLoanHistory/{id}")
	public List<Loan> showLoanHistory(@PathVariable("id") long account_id){
		List <Loan> loanlist = new ArrayList<Loan>();

		try {
			loanlist = loanService.getLoanHistory(account_id);

			if (loanlist == null)
				throw new RecordNotFoundException("Account Id does not exist.");

		} catch (Exception e) {

			LOGGER.info(e.getMessage(), HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Account Id does not exist.");

		}
		return loanlist;
	}
	
	
}
