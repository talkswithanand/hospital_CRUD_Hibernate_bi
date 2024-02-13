package com.ty.hospital_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class MedOrders {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "med")
	@SequenceGenerator(name = "med", initialValue = 400)
	private int medOrdersId;
	
	private String invoice;
	private int bill;
	private String paymentMode;
	
	@CreationTimestamp
	private LocalDateTime orderDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedOrderDate;
	
	@ManyToOne
	@JoinColumn(name = "my_encounter_id")
	private Encounter encounter;
	
	@ManyToMany(mappedBy = "medOrders", cascade = CascadeType.ALL)
	private List<Item> items;

	public int getMedOrdersId() {
		return medOrdersId;
	}

	public void setMedOrdersId(int medOrdersId) {
		this.medOrdersId = medOrdersId;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "MedOrders [medOrdersId=" + medOrdersId + ", invoice=" + invoice + ", bill=" + bill + ", paymentMode="
				+ paymentMode + ", orderDate=" + orderDate + ", updatedOrderDate=" + updatedOrderDate + "]";
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

}
