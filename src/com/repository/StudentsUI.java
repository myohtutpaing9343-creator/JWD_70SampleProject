package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.modelview.UserBean;

public class StudentsUI {
	
	 static UserRespository userRepo;
	 Scanner scan=new Scanner (System.in);
	 
	 public void showMessage() {
		 System.out.println("------Welcome-----");
		 System.out.println("\n1. Create Account\n2.Login");
		System.out.println("Chosse Option :");
		int option=scan.nextInt();
		switch(option) {
		case 1: createUser();
		break;
		case 2: loginUser();
		break;
		default : System.out.println("Exit System");
		}
		
		
	}
	 public void loginUser() {
		System.out.println("Enter Id (USR_):");
		String id= scan.next();
		userRepo=new UserRespository();
		UserBean obj=userRepo.getbyUserId(id);
		if(obj==null) {
			System.out.println("No User Found!");
		}else {
			
			int i=userRepo. CheckInUser(obj.getId());
			if(i>0) {
				System.out.println("Welcome   :"+obj.getUser_name() );
			}else {
				System.out.println("Chill! You already Login....\nLogout(3)");
				int option=scan.nextInt();
				switch (option) {
				case 3: userRepo.checkoutUser(obj.getId());
				showMessage();
				break;
				default:
					System.out.println("");
				}
					
					
				
				
			}
		}
		
	}
	 public void createUser() {
		
	}
	
public void CreatStudents(){
	
	System.out.println("Enter Name");
	String name=scan.nextLine();
	
	System.out.println("EnterGender:");
	String gender=scan.nextLine();
	
	UserBean user=new UserBean(name, gender,getUserid());
	userRepo=new UserRespository();
	int userid=userRepo.createUser(user);
	if(userid>0) {
		String id=userRepo.getUserid(userid);
		System.out.println("Creat Successful");
		System.out.println("Your Id:"+id);
	}
	
	}

public static String getUserid() {
	int row=0;
	userRepo=new UserRespository();
	row=userRepo.getUserRowCount();
	
	if(row!=0) {
		return "USR"+(row+1);
	}else {
		return "USR1";
	}
}
}
