package com.prms.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Address {
	
	@Id
	@Column(name = "a_id")
	private long addressId;
	
	@Column(name = "p_id")
	private long patientId;
	
	@Column(name = "address")
	private String address;

    public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

    public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Address(long addressId, long patientId, String address) {
		this.addressId = addressId;
		this.patientId = patientId;
		this.address = address;
	}
}
	