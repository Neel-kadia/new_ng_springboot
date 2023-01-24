package com.example.CustomerRegistration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CustomerRegistration.model.Customer;
import com.example.CustomerRegistration.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// get all Customers
	@GetMapping("")
	public List < Customer > getAllCustomers() {
		return customerService.findbyAllOrderByIdDesc();
	}

	// create customer method
	@PostMapping("")
	public Object createCustomer(@RequestBody Customer customer) {
		boolean emailResult = customerService.existsEmail(customer.getEmail());
		boolean mobileResult = customerService.existsMobile(customer.getMobile());
		String errormsg = "";
		if (customer.getId() == 0) {
			if (mobileResult == true) {
				errormsg = "Mobile already exists.";
				return errormsg;
			}
			if (emailResult == true) {
				errormsg = "Email already exists.";
				return errormsg;
			}
		}
		return customerService.saveCustomer(customer);
	}

	// get customer by id method
	@GetMapping("/{id}")
	public ResponseEntity < Customer > getCustomerById(@PathVariable int id) {
		Customer customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(customer);
	}

	// update customer method
	@PutMapping("/{id}")
	public Object updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
		Customer cust = customerService.getCustomerById(customer.getId());
		boolean emailResult = customerService.existsEmail(customer.getEmail());
		boolean mobileResult = customerService.existsMobile(customer.getMobile());
		String errormsg = "";
		if (mobileResult == true && !(customer.getMobile().equals(cust.getMobile()))) {
			errormsg = "Mobile already exists.";
			return errormsg;
		}
		if (emailResult == true && !(customer.getEmail().equals(cust.getEmail()))) {
			errormsg = "Email already exists.";
			return errormsg;
		}
		Customer updateCustomer = customerService.saveCustomer(customer);
		return ResponseEntity.ok(updateCustomer);
	}

	// delete customer method
	@DeleteMapping("/{id}")
	public ResponseEntity < Map < String, Boolean >> deleteCustomer(@PathVariable int id) {
		customerService.deleteCustomerById(id);
		Map < String, Boolean > response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}