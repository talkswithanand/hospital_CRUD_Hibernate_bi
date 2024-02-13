package com.ty.hospital_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "it")
	@SequenceGenerator(name = "it", initialValue = 600)
	private int itemId;
	
	private String name;
	private int price;
	
	@CreationTimestamp
	private LocalDateTime manufactureDate;
	private String expiryDate = LocalDateTime.now().plusYears(2).toString();
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "my_item_id"), inverseJoinColumns = @JoinColumn(name = "my_medOrders_id"))
	private List<MedOrders> medOrders;
	
	public List<MedOrders> getMedOrders() {
		return medOrders;
	}
	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", price=" + price + ", manufactureDate=" + manufactureDate
				+ ", expiryDate=" + expiryDate + "]";
	}

	
}	
