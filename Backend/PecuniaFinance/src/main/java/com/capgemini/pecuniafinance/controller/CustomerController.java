package com.capgemini.pecuniafinance.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecuniafinance.error.RecordNotFoundException;
import com.capgemini.pecuniafinance.model.Account;
import com.capgemini.pecuniafinance.model.Customer;
import com.capgemini.pecuniafinance.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	@Autowired 
	private CustomerService customerService;

		/* Method:addCustomer
		 * Type: PostMapping
		 * Description: When /addCustomer is mapped with client request, addUser method is called at Service Layer
		 * @param SignUp: signup
		 * @return Customer: a Customer object is returned to notify that a new Customer Account is added
	 	*/
		@PostMapping("/addCustomer")
		public Customer addUser(@Valid @RequestBody SignUp signup) {
			logger.trace("Add User method accessed at controller");
			Customer user = new Customer();
			user = customerService.addUser(signup.customer, signup.account);
			return user;

		}

		/* Method:updateCustomer
		 * Type: PutMapping
		 * Description: When /updateCustomer is mapped with client request, updateUser method is called at Service Layer
		 * @param Customer: customer
		 * @return Customer: a Customer object is returned to notify that Customer Details are updated
		*/		
		@PutMapping("/updateCustomer")
		@ExceptionHandler(RecordNotFoundException.class)
		public Customer updateCustomer(@RequestBody Customer customer) {
			logger.trace("Update customer details method accessed at controller");
			Customer user = new Customer();

		try {
				user = customerService.updateUser(customer);

				if (user == null)
				throw new RecordNotFoundException("Record Not Found");
		} 
		catch (Exception e) {
				logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
		} 
			return user;
		}
		
		/* Method:removeUserById
		 * Type: DeleteMapping
		 * Description: When it is mapped with client request, removeUserById method is called at Service Layer
		 * @param long: customer_id
		 * @param long: account_id
		 * @return String: a message is returned to notify that Customer Account at given id is deleted or not
		*/
		@DeleteMapping("/deleteCustomer/{customer_id}/{account_id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public String removeUserById(@PathVariable("customer_id") long customer_id, @PathVariable("account_id") long account_id) {
			logger.trace("Delete customer account method accessed at controller");
			String msg = null;

			try {
				msg = customerService.removeUserById(customer_id, account_id);

				if (!msg.equals("User Removed"))
					throw new RecordNotFoundException("Record Not Found");

			} catch (Exception e) {
				logger.info(e.getMessage(), HttpStatus.NOT_FOUND);

			}
			return msg;
		}
		
		/* Method:getAllCustomer
		 * Type: GetMapping
		 * Description: When it is mapped with client request, getAllCustomer method is called at Service Layer
		 * @return List<Customer>: a list of all the customers is returned
		*/
		@GetMapping("/getAllCustomer")
		public List<Customer> getAllCustomer(){
			logger.trace("Get all customers method accessed at controller");
			return customerService.getAllCustomer();
		}
		
		/* Method:getCustomerById
		 * Type: GetMapping
		 * Description: When it is mapped with client request, getCustomerById method is called at Service Layer
		 * @param long: customer_id
		 * @return Optional<Customer>: Customer details are returned of the given customer id 
		*/
		@GetMapping("/getCustomerById/{customer_id}")
		@ExceptionHandler(RecordNotFoundException.class)
		public Optional<Customer> getCustomerById(@PathVariable("customer_id")long customerId){
			logger.trace("Get customer by id method accessed at controller");
			Optional<Customer> user = Optional.of(new Customer());
			try {
				user=customerService.getCustomerById(customerId);
				System.out.println(user);
					if(!(user.isPresent()))
					throw new RecordNotFoundException("Record Not Found");
			}
			catch (Exception e) {
				logger.info(e.getMessage(), HttpStatus.NOT_FOUND);
			} 
				return user;
		}
		
		
}
class SignUp{
	public Customer customer;
	public Account account;
}
