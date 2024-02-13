package com.ty.hospital_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hosp")
	@SequenceGenerator(name = "hosp", initialValue = 100)
	private int branchId;
	private String branchName;
	private String branchSpeciality;
	private int ratings;
	private int noOfDoctors;

	@ManyToOne
	@JoinColumn(name = "my_hospital_id")
	private Hospital hospital;
	
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "my_address_id")
	private Address address;
	
	@OneToMany(mappedBy = "branch")
	private List<Encounter> encounters;

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchSpeciality() {
		return branchSpeciality;
	}

	public void setBranchSpeciality(String branchSpeciality) {
		this.branchSpeciality = branchSpeciality;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public int getNoOfDoctors() {
		return noOfDoctors;
	}

	public void setNoOfDoctors(int noOfDoctors) {
		this.noOfDoctors = noOfDoctors;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchSpeciality=" + branchSpeciality
				+ ", ratings=" + ratings + ", noOfDoctors=" + noOfDoctors + "]";
	}

	
}
