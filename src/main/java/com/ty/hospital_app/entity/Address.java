package com.ty.hospital_app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "add")
	@SequenceGenerator(name = "add", initialValue = 200)
	private int addressId;
	private String addressLocation;
	private String adressState;
	private int adressPinCode;
	private long contact;
	
	@OneToOne(mappedBy = "address")
	private Branch branch;
	
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddressLocation() {
		return addressLocation;
	}
	public void setAddressLocation(String addressLocation) {
		this.addressLocation = addressLocation;
	}
	public String getAdressState() {
		return adressState;
	}
	public void setAdressState(String adressState) {
		this.adressState = adressState;
	}
	public int getAdressPinCode() {
		return adressPinCode;
	}
	public void setAdressPinCode(int adressPinCode) {
		this.adressPinCode = adressPinCode;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressLocation=" + addressLocation + ", adressState="
				+ adressState + ", adressPinCode=" + adressPinCode + ", contact=" + contact + "]";
	}

	
}
