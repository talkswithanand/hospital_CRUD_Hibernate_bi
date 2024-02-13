package com.ty.hospital_app.dao;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospital_app.entity.Encounter;
import com.ty.hospital_app.entity.Item;
import com.ty.hospital_app.entity.MedOrders;

public class MedOrdersDao {

	private static Scanner sc = new Scanner(System.in);

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static MedOrders createMedOrdersObject() {
		MedOrders medOrder = new MedOrders();

		System.out.println("Enter invoice");
		medOrder.setInvoice(sc.next());
		System.out.println("Enter bill");
		medOrder.setBill(sc.nextInt());
		System.out.println("Enter payment mode");
		medOrder.setPaymentMode(sc.next());

		List<MedOrders> medOrders = new ArrayList<MedOrders>();
		medOrders.add(medOrder);

		List<Item> items = new ArrayList<Item>();
		// set items to medorder, medorders to item
		System.out.println("Enter no. of items to be added");
		int quantity = sc.nextInt();
		for (int i = 1; i <= quantity ; i++) {
			Item item = ItemDao.createItemObject();
			item.setMedOrders(medOrders);
			items.add(item);
		}

		medOrder.setItems(items);

		return medOrder;
	}

	public static MedOrders saveMedOrders(int encounterId) {
		MedOrders medOrder = createMedOrdersObject();

		Encounter encounter = entityManager.find(Encounter.class, encounterId);

		medOrder.setEncounter(encounter);

		List<MedOrders> medOrders = new ArrayList<MedOrders>();
		medOrders.add(medOrder);
		encounter.setMedOrders(medOrders);

		entityTransaction.begin();
		List<Item> items = medOrder.getItems();
		for(Item i: items)
			entityManager.persist(i);
		entityManager.persist(medOrder);

		entityTransaction.commit();

		return medOrder;
	}

	public static MedOrders findMedOrders(int medOrdersId) {
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrdersId);
		if (medOrder != null)
			System.out.println(medOrder);
		return medOrder;
	}

	public static MedOrders updateMedOrders(int medOrdersId) {
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrdersId);

		System.out.println("ENTER\n1. UPDATE INVOICE\n2. UPDATE BILL\n" + "3. UPDATE PAYMENT MODE");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter invoice");
			medOrder.setInvoice(sc.next());
		}
			break;
		case 2: {
			System.out.println("Enter bill");
			medOrder.setBill(sc.nextInt());
		}
			break;
		case 3: {
			System.out.println("Enter payment mode");
			medOrder.setPaymentMode(sc.next());
		}
			break;
		}
		System.out.println(medOrder);
		return medOrder;
	}

	public static MedOrders deleteMedOrders(int medOrdersId) {
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrdersId);
		List<Item> items = medOrder.getItems();
		
		entityTransaction.begin();
		for(Item i: items)
			entityManager.remove(i);
		entityManager.remove(medOrder);
		entityTransaction.commit();

		return entityManager.find(MedOrders.class, medOrdersId);
	}

	public static void displayMedOrders() {
		Query query = entityManager.createQuery("Select m from MedOrders m");
		List<MedOrders> medOrders = query.getResultList();
		for (MedOrders m : medOrders) {
			System.out.println("{MedOrder Id: " + m.getMedOrdersId() + " Invoice: " + m.getInvoice() + " }");
		}
	}
}
