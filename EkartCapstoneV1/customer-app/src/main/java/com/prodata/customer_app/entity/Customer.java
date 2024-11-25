package com.prodata.customer_app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;

	@NotBlank(message = "Customer name cannot be blank")
	private String customerName;

	@Email(message = "invalid email format")
	@NotBlank(message = "Email cannot be blank")
	private String customerEmail;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //, mappedBy = "billingCustomer")
//	@JsonManagedReference
	@JoinColumn(name = "customerId")
	private List<CustomerAddress> customerBillingAddressList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //, mappedBy = "shippingCustomer")
//	@JsonManagedReference
	@JoinColumn(name = "customerId")
	private List<CustomerAddress> customerShippingAddressList = new ArrayList<>();

	public Customer() {

	}

	public Customer(@NotBlank(message = "Customer name cannot be blank") String customerName,
			@Email(message = "Invalid email format") @NotBlank(message = "Email cannot be blank") String customerEmail) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}

	public Customer(@NotBlank(message = "Customer name cannot be blank") String customerName,
			@Email(message = "Invalid email format") @NotBlank(message = "Email cannot be blank") String customerEmail,
//			@NotEmpty(message = "Billing address should not be empty") 
			List<CustomerAddress> customerBillingAddressList,
//			@NotEmpty(message = "shipping address should not be empty") 
			List<CustomerAddress> customerShippingAddressList) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerBillingAddressList = customerBillingAddressList;
		this.customerShippingAddressList = customerShippingAddressList;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + Objects.toString(customerId, "null") + ", customerName="
				+ Objects.toString(customerName, "null") + ", customerEmail=" + Objects.toString(customerEmail, "null")
				+ "]";
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<CustomerAddress> getCustomerBillingAddressList() {
		return customerBillingAddressList;
	}

	public void setCustomerBillingAddressList(List<CustomerAddress> customerBillingAddressList) {
		this.customerBillingAddressList = customerBillingAddressList;
	}

	public List<CustomerAddress> getCustomerShippingAddressList() {
		return customerShippingAddressList;
	}

	public void setCustomerShippingAddressList(List<CustomerAddress> customerShippingAddressList) {
		this.customerShippingAddressList = customerShippingAddressList;
	}

}
