package com.ty.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app.entity.Item;

public class ItemDao {

	private static Scanner sc = new Scanner(System.in);

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static Item createItemObject() {
		Item item = new Item();

		System.out.println("Enter item name");
		item.setName(sc.next());
		System.out.println("Enter price of item");
		item.setPrice(sc.nextInt());

		return item;
	}

//	public static Item saveItem() {
//		Item item = createItemObject();
//
//		entityTransaction.begin();
//
//		entityManager.persist(item);
//
//		entityTransaction.commit();
//
//		return item;
//	}

}
