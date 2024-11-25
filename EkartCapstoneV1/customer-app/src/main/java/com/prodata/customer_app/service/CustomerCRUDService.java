package com.prodata.customer_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodata.customer_app.entity.Customer;
import com.prodata.customer_app.entity.CustomerAddress;
import com.prodata.customer_app.exception.CustomerNotFoundException;
import com.prodata.customer_app.repository.CustomerAddressRepository;
import com.prodata.customer_app.repository.CustomerRepository;

@Service
public class CustomerCRUDService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerAddressRepository addressRepo;

	// Customer Crud

	@Transactional
	/*******
	 * Mark methods that change both the customer and their addresses
	 * with @Transactional to maintain data consistency and avoid partial updates.
	 ****/
	public Customer addCustomer(Customer customer) {
//		customer.getCustomerBillingAddressList().forEach(address -> address.setBillingCustomer(customer));
//		customer.getCustomerShippingAddressList().forEach(address -> address.setShippingCustomer(customer));
		return customerRepo.save(customer);
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerById(Long id) {
		return customerRepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id));
	}

	@Transactional
	public Customer updateCustomer(Long id, Customer latestCustomer) {
		Customer customer = getCustomerById(id);
		customer.setCustomerName(latestCustomer.getCustomerName());
		customer.setCustomerEmail(latestCustomer.getCustomerEmail());
		return customerRepo.save(customer);
	}

	@Transactional
	public void deleteCustomer(Long id) {
		Customer customer = getCustomerById(id);
		customerRepo.delete(customer);
	}

	// Billing address operation

	@Transactional
	public Customer addBillingAddress(Long customerId, CustomerAddress address) {
		Customer customer = getCustomerById(customerId);
//		address.setBillingCustomer(customer);
		customer.getCustomerBillingAddressList().add(address);
		return customerRepo.save(customer);
	}

	@Transactional
	public Customer updateBillingAddress(Long customerId, Long addressId, CustomerAddress updatedAddress) {
		Customer customer = getCustomerById(customerId);
		customer.getCustomerBillingAddressList().stream().filter(addr -> addr.getAddressId().equals(addressId)).findFirst()
				.ifPresent(oldAddr -> {
					oldAddr.setDoorNo(updatedAddress.getDoorNo());
					oldAddr.setStreetName(updatedAddress.getStreetName());
					oldAddr.setLayout(updatedAddress.getLayout());
					oldAddr.setCity(updatedAddress.getCity());
					oldAddr.setPincode(updatedAddress.getPincode());
				});
		return customerRepo.save(customer);
	}

	@Transactional
	public Customer deleteBillingAddress(Long customerId, Long addressId) {
		Customer customer = getCustomerById(customerId);
		customer.getCustomerBillingAddressList().removeIf(addr -> addr.getAddressId().equals(addressId));
		return customerRepo.save(customer);
	}

	// Shipping address operation

	@Transactional
	public Customer addShippingAddress(Long customerId, CustomerAddress address) {
		Customer customer = getCustomerById(customerId);
//		address.setShippingCustomer(customer);
		customer.getCustomerShippingAddressList().add(address);
		return customerRepo.save(customer);
	}

	@Transactional
	public Customer updateShippingAddress(Long customerId, Long addressId, CustomerAddress updatedAddress) {
		Customer customer = getCustomerById(customerId);
		customer.getCustomerShippingAddressList().stream().filter(addr -> addr.getAddressId().equals(addressId)).findFirst()
				.ifPresent(oldAddr -> {
					oldAddr.setDoorNo(updatedAddress.getDoorNo());
					oldAddr.setStreetName(updatedAddress.getStreetName());
					oldAddr.setLayout(updatedAddress.getLayout());
					oldAddr.setCity(updatedAddress.getCity());
					oldAddr.setPincode(updatedAddress.getPincode());
				});
		return customerRepo.save(customer);
	}

	@Transactional
	public Customer deleteShippingAddress(Long customerId, Long addressId) {
		Customer customer = getCustomerById(customerId);
		customer.getCustomerShippingAddressList().removeIf(addr -> addr.getAddressId().equals(addressId));
		return customerRepo.save(customer);
	}

}
