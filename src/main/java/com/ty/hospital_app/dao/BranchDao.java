package com.ty.hospital_app.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hospital_app.entity.Branch;
import com.ty.hospital_app.entity.Encounter;
import com.ty.hospital_app.entity.Hospital;

public class BranchDao {
	private static Scanner sc = new Scanner(System.in);

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	public static Branch createBranchObject() {
		Branch branch = new Branch();

		System.out.println("Enter branch name");
		branch.setBranchName(sc.next());
		System.out.println("Enter branch speciality");
		branch.setBranchSpeciality(sc.next());
		System.out.println("Enter ratings");
		branch.setRatings(sc.nextInt());
		System.out.println("Enter no Of doctors");
		branch.setNoOfDoctors(sc.nextInt());

		// set address
		branch.setAddress(AddressDao.createAddressObject());
		return branch;
	}

	public static Branch saveBranch(int hospitalId) {
		Branch branch = null;

		entityTransaction.begin();

		Hospital hospital = entityManager.find(Hospital.class, hospitalId);
		if(hospital != null) {
			branch = createBranchObject();
			branch.setHospital(hospital);
		}
		entityManager.persist(branch);
		
		entityTransaction.commit();

		// sc.close();
		return branch;
	}

	public static Branch findBranch(int branchId) {
		Branch branch = entityManager.find(Branch.class, branchId);
		if (branch != null)
			System.out.println(branch);
		return branch;
	}

	public static Branch updateBranch(int branchId) {
		Branch branch = entityManager.find(Branch.class, branchId);

		System.out.println("ENTER\n1. UPDATE BRANCH NAME\n2. UPDATE BRANCH SPECIALITY\n"
				+ "3. UPDATE RATINGS\n4. UPDATE NO. OF DOCTORS");
		switch (sc.nextInt()) {
		case 1: {
			System.out.println("Enter branch name");
			branch.setBranchName(sc.next());
		}
			break;
		case 2: {
			System.out.println("Enter branch speciality");
			branch.setBranchSpeciality(sc.next());
		}
			break;
		case 3: {
			System.out.println("Enter ratings");
			branch.setRatings(sc.nextInt());
		}
			break;
		case 4: {
			System.out.println("Enter no of doctors");
			branch.setNoOfDoctors(sc.nextInt());
		}
			break;
		}

		System.out.println(branch);
		return branch;
	}

	public static Branch deleteBranch(int branchId) {
		Branch branch = entityManager.find(Branch.class, branchId);

		entityTransaction.begin();
		entityManager.remove(branch);
		entityTransaction.commit();

		return entityManager.find(Branch.class, branchId);
	}

	public static void displayBranches() {
		Query query = entityManager.createQuery("Select b from Branch b");
		List<Branch> branches = query.getResultList();
		for (Branch b : branches) {
			System.out.println("{Branch id: " + b.getBranchId() + " Branch name: " + b.getBranchName() + " }");
		}
	}

}
