package com.prodata.customer_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodata.customer_app.entity.Customer;
import com.prodata.customer_app.entity.CustomerAddress;
import com.prodata.customer_app.service.CustomerCRUDService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class CustomerCRUDController {

	@Autowired
	private CustomerCRUDService customerService;

	// Customer Crud operation

//	@PostMapping(value="/add",
//		    consumes = {"application/json", "application/json;charset=UTF-8"})
	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		System.out.println("Received customer: " + customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> list = customerService.getAllCustomers();
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();// 204 No Content
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
		return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}

	// billing Address operation

	@PostMapping("/{customerId}/billing-addresses")
	public ResponseEntity<Customer> addBillingAddress(@PathVariable("customerId") Long customerId,
			@Valid @RequestBody CustomerAddress updatedAddress) {
		return ResponseEntity.ok(customerService.addBillingAddress(customerId, updatedAddress));
	}

	@PutMapping("/{customerId}/billing-addresses/{addressId}")
	public ResponseEntity<Customer> updateBillingAddress(@PathVariable Long customerId, @PathVariable Long addressId,
			@RequestBody CustomerAddress updatedAddress) {
		return ResponseEntity.ok(customerService.updateBillingAddress(customerId, addressId, updatedAddress));
	}

	@DeleteMapping("/{customerId}/billing-addresses/{addressId}")
	public ResponseEntity<Customer> deleteBillingAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
		return ResponseEntity.ok(customerService.deleteBillingAddress(customerId, addressId));
	}

	// ---- Shipping Address Operations ----

	@PostMapping("/{customerId}/shipping-addresses")
	public ResponseEntity<Customer> addShippingAddress(@PathVariable Long customerId,
			@Valid @RequestBody CustomerAddress address) {
		return ResponseEntity.ok(customerService.addShippingAddress(customerId, address));
	}

	@PutMapping("/{customerId}/shipping-addresses/{addressId}")
	public ResponseEntity<Customer> updateShippingAddress(@PathVariable Long customerId, @PathVariable Long addressId,
			@RequestBody CustomerAddress updatedAddress) {
		return ResponseEntity.ok(customerService.updateShippingAddress(customerId, addressId, updatedAddress));
	}

	@DeleteMapping("/{customerId}/shipping-addresses/{addressId}")
	public ResponseEntity<Customer> deleteShippingAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
		return ResponseEntity.ok(customerService.deleteShippingAddress(customerId, addressId));
	}

}
