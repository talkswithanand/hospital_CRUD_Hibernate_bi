package com.ty.hospital_app.contoller;

import com.ty.hospital_app.helper.HelperClass;

public class UserController {

	public static void main(String[] args) {
		System.out.println("Welcome to Hospital Management System!!!");
		boolean flag = true;
		while (flag) {
			int choice = HelperClass.initials();
			switch (choice) {
			case 1: {
				HelperClass.hospitalOperation();
			}
				break;
			case 2: {
				HelperClass.branchOperation();
			}
				break;
			case 3: {
				HelperClass.encounterOperation();
			}
				break;
			case 4: {
				HelperClass.medOrdersOperation();
			}
				break;
			case 5: {
				flag = false;
			}
				break;
			default:
				System.out.println("You have selected wrong option.");
			}
		}
	}
}
