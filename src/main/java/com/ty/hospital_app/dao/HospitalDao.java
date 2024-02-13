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
import com.ty.hospital_app.entity.Hospital;

public class HospitalDao {
	private static Scanner sc = new Scanner(System.in);

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static Hospital createHospitalObject() {
		Hospital hospital = new Hospital();

		System.out.println("Enter hospital name");
		hospital.setHospitalName(sc.next());
		System.out.println("Enter hospital speciality");
		hospital.setHospitalSpeciality(sc.next());
		System.out.println("Enter no Of Beds available");
		hospital.setNoOfBeds(sc.nextInt());
		System.out.println("Enter no of patients");
		hospital.setNoOfPatients(sc.nextInt());

		// set hospital to branch & branch to hospital
		Branch branch = BranchDao.createBranchObject();
		branch.setHospital(hospital);

		List<Branch> branches = new ArrayList<Branch>();
		branches.add(branch);
		hospital.setBranches(branches);

		return hospital;
	}

	public static Hospital saveHospital() {
		Hospital hospital = createHospitalObject();

		entityTransaction.begin();

		entityManager.persist(hospital);
		// BranchDao.entityTransaction.commit();
		entityTransaction.commit();

		// sc.close();
		return hospital;
	}

	public static Hospital findHospital(int hospitalId) {
		Hospital hospital = entityManager.find(Hospital.class, hospitalId);
		if (hospital != null)
			System.out.println(hospital);
		return hospital;
	}

	public static Hospital updateHospital(int hospitalId) {
		Hospital hospital = entityManager.find(Hospital.class, hospitalId);

		System.out.println("ENTER\n1. UPDATE HOSPITAL NAME\n2. UPDATE HOSPITAL SPECIALITY\n"
				+ "3. UPDATE NO. OF BEDS\n4. UPDATE NO. OF PATIENTS");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter hospital name");
			hospital.setHospitalName(sc.next());
		}
			break;
		case 2: {
			System.out.println("Enter hospital speciality");
			hospital.setHospitalSpeciality(sc.next());
		}
			break;
		case 3: {
			System.out.println("Enter no Of Beds available");
			hospital.setNoOfBeds(sc.nextInt());
		}
			break;
		case 4: {
			System.out.println("Enter no of patients");
			hospital.setNoOfPatients(sc.nextInt());
		}
			break;
		}

		System.out.println(hospital);
		return hospital;
	}

	public static Hospital deleteHospital(int hospitalId) {
		Hospital hospital = entityManager.find(Hospital.class, hospitalId);

		entityTransaction.begin();
		entityManager.remove(hospital);
		entityTransaction.commit();

		return entityManager.find(Hospital.class, hospitalId);
	}

	public static void displayHospitals() {
		Query query = entityManager.createQuery("Select h from Hospital h");
		List<Hospital> hospitals = query.getResultList();
		for (Hospital h : hospitals) {
			System.out.println("{Hospital id: " + h.getHospitalId() + " Hospital name: " + h.getHospitalName() + " }");
		}
	}

}
