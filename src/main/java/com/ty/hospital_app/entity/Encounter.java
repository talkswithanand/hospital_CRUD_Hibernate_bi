package com.ty.hospital_app.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Encounter {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "enc")
	@SequenceGenerator(name = "enc", initialValue = 300)
	private int encounterId;
	
	@ManyToOne
	@JoinColumn(name = "my_branch_id")
	private Branch branch;
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	private String doctorName;
	private String diagnosis;
	
	@CreationTimestamp
	private LocalDate admitDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	private int noOfTests;
	
	
	@OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL)
	private List<MedOrders> medOrders;
	
	@ManyToOne
	@JoinColumn(name = "my_person_id")
	private Person person;

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public int getNoOfTests() {
		return noOfTests;
	}

	public void setNoOfTests(int noOfTests) {
		this.noOfTests = noOfTests;
	}

	public List<MedOrders> getMedOrders() {
		return medOrders;
	}

	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "Encounter [encounterId=" + encounterId + ", doctorName=" + doctorName + ", diagnosis=" + diagnosis
				+ ", admitDate=" + admitDate + ", updatedDate=" + updatedDate + ", noOfTests=" + noOfTests + "]";
	}

}
