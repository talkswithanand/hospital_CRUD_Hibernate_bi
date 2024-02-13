package com.ty.hospital_app.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.hospital_app.entity.Address;

public class AddressDao {
	private static Scanner sc = new Scanner(System.in);
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static Address createAddressObject() {
		Address address = new Address();

		System.out.println("Enter Address location");
		address.setAddressLocation(sc.next());
		System.out.println("Enter address pincode");
		address.setAdressPinCode(sc.nextInt());
		System.out.println("Enter address state");
		address.setAdressState(sc.next());
		System.out.println("Enter no of patients");
		address.setContact(sc.nextLong());

		return address;
	}

//	public static Address saveAddress() {
//		entityTransaction.begin();
//
//		Address address = createAddressObject();
//		entityManager.persist(address);
//
//		entityTransaction.commit();
//		
////		sc.close();
//		return address;
//	}
}
