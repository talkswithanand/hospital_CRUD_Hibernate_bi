package com.ty.hospital_app.helper;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ty.hospital_app.dao.BranchDao;
import com.ty.hospital_app.dao.EncounterDao;
import com.ty.hospital_app.dao.HospitalDao;
import com.ty.hospital_app.dao.MedOrdersDao;
import com.ty.hospital_app.dao.PersonDao;
import com.ty.hospital_app.entity.Branch;
import com.ty.hospital_app.entity.Encounter;
import com.ty.hospital_app.entity.MedOrders;
import com.ty.hospital_app.entity.Person;

public class HelperClass {

	private static Scanner sc = new Scanner(System.in);

	public static int initials() {
		System.out.println(
				"Enter\n1. Hospital Operations\n2. Branch Operations\n3. Encounter Operations\n4. MedOrders Operations\n5. Exit");
		int choice = 0;
		try {
			choice = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Numeric Input Expected!!");
			sc.nextLine();
			initials();
		}
		return choice;
	}

	public static void hospitalOperation() {
		// display hospital ID-NAME
		HospitalDao.displayHospitals();

		boolean flag = true;
		while (flag) {
			System.out.println("1. SAVE\n2. FIND\n3. UPDATE\n4. DELETE\n5. MAIN MENU");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				HospitalDao.saveHospital();
				System.out.println("Hospital Saved Successfully!!!");
			}
				break;
			case 2: {
				System.out.println("Enter Hospital Id");
				int hospitalId = sc.nextInt();
				if (HospitalDao.findHospital(hospitalId) == null)
					System.out.println("Hospital not found!!!");
			}
				break;
			case 3: {
				System.out.println("Enter Hospital Id");
				int hospitalId = sc.nextInt();
				if (HospitalDao.updateHospital(hospitalId) != null) {
					System.out.println("Hospital Updated Successfully!!!");
				}
			}
				break;
			case 4: {
				System.out.println("Enter Hospital Id");
				int hospitalId = sc.nextInt();
				if (HospitalDao.deleteHospital(hospitalId) == null) {
					System.out.println("Hospital Deleted Successfully!!!");
				}
			}
				break;
			case 5: {
				flag = false;
				break;
			}
			}
			// display updated hospital ID-NAME
			HospitalDao.displayHospitals();
		}
	}

	public static void branchOperation() {
		// display branch ID-NAME
		BranchDao.displayBranches();

		boolean flag = true;
		while (flag) {
			System.out.println("1. SAVE\n2. FIND\n3. UPDATE\n4. DELETE\n5. MAIN MENU");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				HospitalDao.displayHospitals();
				System.out.println("Enter hospital Id");
				int hospitalId = sc.nextInt();
				Branch branch = BranchDao.saveBranch(hospitalId);
				if (branch != null)
					System.out.println("Branch Saved Successfully!!!");
				else
					System.out.println("Hospital Id not correct!!");
			}
				break;
			case 2: {
				System.out.println("Enter Branch Id");
				int branchId = sc.nextInt();
				if (BranchDao.findBranch(branchId) == null)
					System.out.println("Branch not found!!!");
			}
				break;
			case 3: {
				System.out.println("Enter Branch Id");
				int branchId = sc.nextInt();
				if (BranchDao.updateBranch(branchId) != null) {
					System.out.println("Branch Updated Successfully!!!");
				}
			}
				break;
			case 4: {
				System.out.println("Enter Branch Id");
				int branchId = sc.nextInt();
				if (BranchDao.deleteBranch(branchId) == null) {
					System.out.println("Branch Deleted Successfully!!!");
				}
			}
				break;
			case 5:
				flag = false;
				break;
			}
			// display updated branch ID-NAME
			BranchDao.displayBranches();
		}
	}

	public static void encounterOperation() {
		// display encounter ID-DOCTOR
		EncounterDao.displayEncounters();

		boolean flag = true;
		while (flag) {
			System.out.println("1. SAVE\n2. FIND\n3. UPDATE\n4. DELETE\n5. MAIN MENU");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				BranchDao.displayBranches();
				System.out.println("Enter branch id");
				int branchId = sc.nextInt();
				Branch branch = BranchDao.findBranch(branchId);
				if (branch != null) {
					PersonDao.displayPersons();
					System.out.println("Enter person id");
					int personId = sc.nextInt();
					Person person = PersonDao.findPerson(personId);
					if (person == null) {
						System.out.println("First Register person!!");
						person = PersonDao.savePerson();
						personId = person.getPersonId();
					}
					EncounterDao.saveEncounter(branchId, personId);
					System.out.println("Encounter Saved Successfully!!!");
				} else {
					System.out.println("Branch does not exist!!");
				}
			}
				break;
			case 2: {
				System.out.println("Enter Encounter Id");
				int encounterId = sc.nextInt();
				if (EncounterDao.findEncounter(encounterId) == null)
					System.out.println("Encounter not found!!!");
			}
				break;
			case 3: {
				System.out.println("Enter Encounter Id");
				int encounterId = sc.nextInt();
				if (EncounterDao.updateEncounter(encounterId) != null) {
					System.out.println("Encounter Updated Successfully!!!");
				}
			}
				break;
			case 4: {
				System.out.println("Enter Encounter Id");
				int encounterId = sc.nextInt();
				if (EncounterDao.deleteEncounter(encounterId) == null) {
					System.out.println("Encounter Deleted Successfully!!!");
				}
			}
				break;
			case 5: {
				flag = false;
			}
				break;
			}
			// display updated encounter ID-DOCTOR
			EncounterDao.displayEncounters();

		}

	}

	public static void medOrdersOperation() {
		// display MedOrder ID-INVOICE
		MedOrdersDao.displayMedOrders();

		boolean flag = true;
		while (flag) {
			System.out.println("1. SAVE\n2. FIND\n3. UPDATE\n4. DELETE\n5. MAIN MENU");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				EncounterDao.displayEncounters();
				System.out.println("Enter encounter id");
				int encounterId = sc.nextInt();
				Encounter encounter = EncounterDao.findEncounter(encounterId);
				if (encounter != null) {

					MedOrders medorder = MedOrdersDao.saveMedOrders(encounterId);

				} else {
					System.out.println("Encounter does not exist!!");
				}
			}
				break;
			case 2: {
				System.out.println("Enter MedOrder Id");
				int medOrdersId = sc.nextInt();
				if (MedOrdersDao.findMedOrders(medOrdersId) == null)
					System.out.println("MedOrder not found!!!");
			}
				break;
			case 3: {
				System.out.println("Enter MedOrder Id");
				int medOrdersId = sc.nextInt();
				if (MedOrdersDao.updateMedOrders(medOrdersId) != null) {
					System.out.println("MedOrder Updated Successfully!!!");
				}
			}
				break;
			case 4: {
				System.out.println("Enter MedOrder Id");
				int medOrdersId = sc.nextInt();
				if (MedOrdersDao.deleteMedOrders(medOrdersId) == null) {
					System.out.println("MedOrder Deleted Successfully!!!");
				}
			}
				break;
			case 5:
				flag = false;
				break;
			}
			// display updated MedOrder ID-INVOICE
			MedOrdersDao.displayMedOrders();
		}
	}

}
