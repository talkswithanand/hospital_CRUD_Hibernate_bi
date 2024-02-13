package com.ty.hospital_app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "per")
	@SequenceGenerator(name = "per", initialValue = 500)
	private int personId;
	private String visitedDoctor;
	private String name;
	private int age;
	private int noOfVisits;
	
	@OneToMany(mappedBy = "person")
	private List<Encounter> encounters;
	
	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getVisitedDoctor() {
		return visitedDoctor;
	}
	public void setVisitedDoctor(String visitedDoctor) {
		this.visitedDoctor = visitedDoctor;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", visitedDoctor=" + visitedDoctor + ", name=" + name + ", age=" + age
				+ ", noOfVisits=" + noOfVisits + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getNoOfVisits() {
		return noOfVisits;
	}
	public void setNoOfVisits(int noOfVisits) {
		this.noOfVisits = noOfVisits;
	}

	
	
}
