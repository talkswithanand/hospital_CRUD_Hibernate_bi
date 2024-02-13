package com.ty.hospital_app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospital_app.entity.Branch;
import com.ty.hospital_app.entity.Encounter;
import com.ty.hospital_app.entity.Person;

public class EncounterDao {

	private static Scanner sc = new Scanner(System.in);

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static Encounter createEncounterObject() {
		Encounter encounter = new Encounter();

		System.out.println("Enter diagnosis");
		encounter.setDiagnosis(sc.next());
		System.out.println("Enter doctor name");
		encounter.setDoctorName(sc.next());
		System.out.println("Enter no of tests");
		encounter.setNoOfTests(sc.nextInt());

		return encounter;
	}

	public static Encounter saveEncounter(int branchId, int personId) {
		Encounter encounter = createEncounterObject();
		
		// Find person & set person
		Person person = PersonDao.findPerson(personId);
		encounter.setPerson(person);
		
		//set branch to encounters
		Branch branch = entityManager.find(Branch.class, branchId);
		encounter.setBranch(branch);
		
		// set encounters to person
		List<Encounter> encounters = new ArrayList();
		encounters.add(encounter);
		person.setEncounters(encounters);
		
		entityTransaction.begin();

		entityManager.persist(encounter);

		entityTransaction.commit();

//		sc.close();
		return encounter;
	}

	public static Encounter findEncounter(int encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);
		if (encounter != null)
			System.out.println(encounter);
		return encounter;
	}

	public static Encounter updateEncounter(int encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);

		System.out.println("ENTER\n1. UPDATE DOCTOR NAME\n2. UPDATE DIAGNOSIS\n" + "3. UPDATE NO. OF TESTS");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter doctor name");
			encounter.setDoctorName(sc.next());
		}
			break;
		case 2: {
			System.out.println("Enter diagnosis");
			encounter.setDiagnosis(sc.next());
		}
			break;
		case 3: {
			System.out.println("Enter no of tests");
			encounter.setNoOfTests(sc.nextInt());
		}
			break;
		}
		System.out.println(encounter);
		return encounter;
	}

	public static Encounter deleteEncounter(int encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);

		entityTransaction.begin();
		entityManager.remove(encounter);
		entityTransaction.commit();

		return entityManager.find(Encounter.class, encounterId);
	}

	public static void displayEncounters() {
		Query query = entityManager.createQuery("Select e from Encounter e");
		List<Encounter> encounters = query.getResultList();
		for (Encounter e : encounters) {
			System.out.println("{Encounter id: " + e.getEncounterId() + " Doctor name: " + e.getDoctorName() + " }");
		}
	}

}
