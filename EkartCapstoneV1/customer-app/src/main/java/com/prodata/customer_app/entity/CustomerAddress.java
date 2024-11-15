package com.prodata.customer_app.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CustomerAddress")
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@NotBlank(message = "Door number cannot be blank")
	private String doorNo;

	@NotBlank(message = "Street name cannot be blank")
	private String streetName;

	@NotBlank(message = "Layout cannot be blank")
	private String layout;

	@NotBlank(message = "City cannot be blank")
	private String city;

//    @NotNull
//	@Positive
//	@Digits(integer = 6, fraction = 0, message = "Pincode must contain 6 digits")
	@Size(min = 5, max = 6, message = "Pincode must be 5 or 6 digits")
	private String pincode;

	public CustomerAddress() {
	}

	public CustomerAddress(@NotBlank(message = "Door number cannot be blank") String doorNo,
			@NotBlank(message = "Street name cannot be blank") String streetName,
			@NotBlank(message = "Layout cannot be blank") String layout,
			@NotBlank(message = "City cannot be blank") String city,
			@Size(min = 5, max = 6, message = "Pincode must be 5 or 6 digits") String pincode) {
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.layout = layout;
		this.city = city;
		this.pincode = pincode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, city, doorNo, layout, pincode, streetName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerAddress other = (CustomerAddress) obj;
		return addressId == other.addressId && Objects.equals(city, other.city) && Objects.equals(doorNo, other.doorNo)
				&& Objects.equals(layout, other.layout) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(streetName, other.streetName);
	}

	@Override
	public String toString() {
		return "CustomerAddress [addressId=" + Objects.toString(addressId, "null") + ", doorNo="
				+ Objects.toString(doorNo, "null") + ", streetName=" + Objects.toString(streetName, "null") + ", city="
				+ Objects.toString(city, "null") + ", layout=" + Objects.toString(layout, "null") + ", pincode="
				+ Objects.toString(pincode, "null") + "]";
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
