package Components;

import java.sql.Date;
import java.text.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import AppDbContext.*;
import Function.Func;
import Models.*;
import Text_Color.BackgroundColor;
import Text_Color.Color;

public class Com_Admin {

	static BackgroundColor backgrondcolor = new BackgroundColor();
	static Color color = new Color();
	static Func func = new Func();
	static AdminDB ad = new AdminDB();
	static CustomerDB cd = new CustomerDB();
	static Admin admin_acc = new Admin();
	static AccountDB accDB = new AccountDB();
	static BankAccountIDDB bankDB = new BankAccountIDDB();
	
	public static void Admin_Home_Console() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			System.out.println("\t      WELCOME ADMIN PAGE");
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println("\t 1. Create New Admin Account");
			System.out.println("\t 2. Login");
			System.out.println("\t 0. Back");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
			case "1":
				Admin_Create_Console(sc);
				break;
			case "2":
				Admin_Login_Console(sc);
				break;
			case "0":
				return;
			default:
				System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
				sc.nextLine();
			}			
		}
	}
	
	public static void Admin_Create_Console(Scanner sc) {
		func.Clear();

		System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
		System.out.println("\t       CREATE ADMIN PAGE");
		System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
		System.out.println();
		
		System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
		System.out.println("\t * Full name length must be 3-16.");
		System.out.println("\t * Admin id and Password length must be 6.");
		
		System.out.println();
		System.out.print("\t FULL NAME : " + color.TEXT_YELLOW);
		String admin_fullname = sc.nextLine();
		
		if(admin_fullname.length() >= 3 && admin_fullname.length() <= 16) {
			System.out.print(color.TEXT_RESET+"\t\t ADMIN ID  : " + color.TEXT_YELLOW);
			String admin_id = sc.nextLine();
			boolean admin_id_check = ad.CheckAdmin_Username(admin_id);
			if(admin_id.length() == 6 && admin_id_check == false) {
				System.out.print(color.TEXT_RESET+"\t\t PASSWORD  : "+ color.TEXT_YELLOW);
				String admin_password = sc.nextLine();
				boolean admin_password_check = ad.CheckAdmin_Password(admin_password);
				if(admin_password.length() == 6 && admin_password_check == false) {
					
					Admin new_admin = new Admin();
					new_admin.setFullName(admin_fullname);
					new_admin.setUsername(admin_id);
					new_admin.setPassword(admin_password);
					
					ad.CreateAdmin(new_admin);
					
					
				}else 
					System.out.println(color.TEXT_RED+"\t\t Password Length Must Be 6 or This password already exists."+color.TEXT_RESET);
			}else 
				System.out.println(color.TEXT_RED+"\t\t Admin Id Length Must Be 6 or This id already exists."+color.TEXT_RESET);
		}else 
			System.out.println(color.TEXT_RED+"\t\t Full Name Length Must Be 3-16."+color.TEXT_RESET);
		
		sc.nextLine();
	}
	
	public static void Admin_Login_Console(Scanner sc) {

		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			System.out.println("\t       ADMIN LOGIN PAGE");
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.print("\t ADMIN ID : " + color.TEXT_YELLOW);
			String admin_id = sc.nextLine();
			System.out.print(color.TEXT_RESET+"\t\t PASSWORD : "+ color.TEXT_YELLOW);
			String admin_password = sc.nextLine();
			admin_acc = ad.getAdmin(admin_id, admin_password);
			if(admin_acc != null) {
				Admin_Menu_Console(sc);
				return;
			}else {
				System.out.println(color.TEXT_RED+"\t\t Login failed, please try again."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			
		}
		
	}
	
	public static void Admin_Menu_Console(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			System.out.println("                                      WELCOME                                      ");
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println("\t 1. Create New Customer / Account          6. Manage Account");
			System.out.println("\t 2. Show All Customers List                7. Add Customer New Bank Account");
			System.out.println("\t 3. Show All Customers Account Detail      8. Manage Bank Account");
			System.out.println("\t 4. Manage Customers Information           0. Back");
			System.out.println("\t 5. Add Customer New Account");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Admin_Create_Customer_Console(sc);
					break;
				case "2":
					func.Clear();
					Admin_Show_All_Customers_Console();
					sc.nextLine();
					break;
				case "3":
					Check_Customer_Detail(sc);
					break;
				case "4":
					Admin_Manage_Customer_Console(sc);
					break;
				case "5":
					Admin_Create_Account_Customer_Console(sc);
					break;
				case "6":
					Manage_Account(sc);
					break;
				case "7":
					Add_Bank_Account(sc);
					break;
				case "8":
					Manage_Bank_Account(sc);
					break;
				case "0":
					admin_acc = null;
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}
		}
	}
	
	public static void Check_Customer_Detail(Scanner sc) {
		
		while(true) {
			
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                            CHECKING CUSTOMER                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_RED+"                               (1) STEP ONE                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to view account or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Customer cus = cd.getCustomer(id);
			if(cus != null) {
				Check_Customer_Account_Detail(sc,cus);
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
		}
		
	}
	
	public static void Check_Customer_Account_Detail(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                              MANAGE ACCOUNT                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_WHITE+"                               (2) STEP TWO                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Print_Account_Detail(cus);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a account id to manage or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Account acc = accDB.getAccount_ByID(id);
			if(acc != null && acc.getCustomerID() == cus.getCustomerID()) {
				Check_Customer_Bank_Account_Detail(sc,acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}

	public static void Check_Customer_Bank_Account_Detail(Scanner sc,Account acc) {
	
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                            (3) STEP THREE                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Print_Bank_Account_Detail(acc);
			System.out.println();
			System.out.println(color.TEXT_RED+"Please click any key to return "+color.TEXT_RESET);
			sc.nextLine();
			return;

		}
		
	}
	
	public static void Admin_Create_Customer_Console(Scanner sc) {

		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             CREATE CUSTOMER                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                              NEW CUSTOMER                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
			System.out.println("\t * IC : Must enter 12 digits.");
			System.out.println("\t * AGE :  Must be over 18 years old or under 60 years old.");
			System.out.println("\t * BIRTH DATE : need to follow format (year-month-day).");
			System.out.println("\t * GENDER : just can enter number (0. Male / 1. Female / 2. Others).");
			
			System.out.println();
			System.out.print("\t FULL NAME : " + color.TEXT_YELLOW);
			String user_fullname = sc.nextLine();
			
			if(user_fullname.equals("")) {
				System.out.println(color.TEXT_RED+"\t\t Full name cannot be empty."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t IC NUMBER : " + color.TEXT_YELLOW);
			String user_ic = sc.nextLine();
			
			boolean b = cd.CheckCustomer_IC(user_ic);
			if(b == true || user_ic.length() != 12) {
				System.out.println(color.TEXT_RED+"\t\t This ic already exists or IC number wrong."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t AGE : " + color.TEXT_YELLOW);
			String user_age = sc.nextLine();
			
			try {
				Integer.parseInt(user_age);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Age input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int age = Integer.valueOf(user_age);
			
			if(age < 18 || age > 90) {
				System.out.println(color.TEXT_RED+"\t\t Age input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BIRTH DATE  : " + color.TEXT_YELLOW);
			String user_birthdate = sc.nextLine();
			Date date = null;

	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            sdf.setLenient(false);
	            sdf.parse(user_birthdate); 
	            int pos1 = user_birthdate.indexOf("-");
	            int pos2 = user_birthdate.indexOf("-",pos1+1);
	            String y = user_birthdate.substring(0,pos1);
	            String m = user_birthdate.substring(pos1+1,pos2);
	            String d = user_birthdate.substring(pos2+1);
	            date = Date.valueOf(LocalDate.of(Integer.valueOf(y),Integer.valueOf(m),Integer.valueOf(d)));
	        } catch (ParseException e) {
	            System.out.println(color.TEXT_RED+"\t\t Birth Date Format Wrong..."+color.TEXT_RESET);
	            sc.nextLine();
				return;
	        }
			
			System.out.print(color.TEXT_RESET+"\t\t GENDER  : " + color.TEXT_YELLOW);
			String user_gender = sc.nextLine();
			
			try {
				Integer.parseInt(user_gender);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Gender input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int gender = Integer.valueOf(user_gender);
			
			if(gender != 0)
				if(gender != 1)
					if(gender != 2){
						System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Male / 1. Female / 2. Others)."+color.TEXT_RESET);
						sc.nextLine();
						return;
					}
			
			System.out.print(color.TEXT_RESET+"\t\t EMAIL : " + color.TEXT_YELLOW);
			String user_email = sc.nextLine();
			
			boolean email_bool = cd.CheckCustomer_Email(user_email);
			if(!user_email.contains("@gmail.com")) {
				System.out.println(color.TEXT_RED+"\t\t Email Format Wrong."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(email_bool == true) {
				System.out.println(color.TEXT_RED+"\t\t This email already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET);
					
			Customer cus = new Customer();
			cus.setFullName(user_fullname);
			cus.setIC(user_ic);
			cus.setAge(age);
			cus.setBirthDate(date);
			cus.setGender(gender);
			cus.setEmail(user_email);
			cus.setAdminID(admin_acc.getAdminID());
			
			cd.CreateCustomer(cus);
			sc.nextLine();
			
			return;
				
		}
	}
		
	public static void Admin_Show_All_Customers_Console() {
		
		List<Customer> list = cd.getAllCustomer();
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("FullName");
		t.addCell("IC");
		t.addCell("Age");
		t.addCell("BirthDate");
		t.addCell("Gender");
		t.addCell("Email");
		t.addCell("Number Of Accounts");
		t.addCell("Admin Create");
		
		for(Customer cus : list) {
			
			String gender = "";
			if(cus.getGender() == 0)
				gender = "Male";
			else if(cus.getGender() == 1)
				gender = "Female";
			else if(cus.getGender() == 2)
				gender = "Others";
			
			int count = accDB.getAccount_Number(cus.getCustomerID());
			
			Admin admin = ad.getAdmin_id(cus.getAdminID());
			
			t.addCell(String.valueOf(cus.getCustomerID()),numberStyle);
			t.addCell(cus.getFullName(),numberStyle);
			t.addCell(cus.getIC(),numberStyle);
			t.addCell(String.valueOf(cus.getAge()),numberStyle);
			t.addCell(String.valueOf(cus.getBirthDate()),numberStyle);
			t.addCell(gender,numberStyle);
			t.addCell(cus.getEmail(),numberStyle);
			t.addCell(String.valueOf(count),numberStyle);
			t.addCell(admin.getFullName(),numberStyle);
		}
		
		System.out.println(t.render());
		
	}
	
	public static void Admin_Manage_Customer_Console(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             MANAGE CUSTOMER                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_RED+"                               (1) STEP ONE                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to manage or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Customer cus = cd.getCustomer(id);
			if(cus != null) {
				Admin_Manage_Customer_Edit_COnsole(sc,cus);
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
		}
		
	}
	
	public static void Admin_Manage_Customer_Edit_COnsole(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             MANAGE CUSTOMER                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_WHITE+"                               (2) STEP TWO                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_Customers_Console(cus);
			System.out.println();
			System.out.println();
			System.out.println("\t 1. Edit");
			System.out.println("\t 2. Delete");
			System.out.println("\t 0. Back");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Admin_Edit_Customer_Console(sc,cus);
					return;
				case "2":
					Admin_Delete_Customer_Console(sc,cus);
					return;
				case "0":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}
			
			
		}
		
	}
	
	public static void Admin_Delete_Customer_Console(Scanner sc,Customer cus){
		
		func.Clear();
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                          ");
		System.out.println(color.TEXT_WHITE+"                         CONFIRM DELETE CUSTOMER                          "+color.TEXT_PURPLE);
		System.out.println("                                                                          "+color.TEXT_RESET);
		 
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t 1. CONFIRM"+color.TEXT_RESET);
		System.out.print(" / ");
		System.out.print(color.TEXT_CYAN+"2. CANCEL"+color.TEXT_RESET);
		System.out.println();
		System.out.println();
		System.out.print("\t Select Your Option: ");

		String opt = sc.nextLine();
		switch(opt) {
			case "1":
				cd.deleteCustomer(cus.getCustomerID());
				sc.nextLine();
				return;
			case "2":
				return;
			default:
				System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
				sc.nextLine();
				return;
			
		}
		
	}
	
	public static void Admin_Show_Customers_Console(Customer cus) {
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("FullName");
		t.addCell("IC");
		t.addCell("Age");
		t.addCell("BirthDate");
		t.addCell("Gender");
		t.addCell("Email");
		t.addCell("Number Of Accounts");
		t.addCell("Admin Create");
		
		String gender = "";
		if(cus.getGender() == 0)
			gender = "Male";
		else if(cus.getGender() == 1)
			gender = "Female";
		else if(cus.getGender() == 2)
			gender = "Others";
		
		int count = accDB.getAccount_Number(cus.getCustomerID());
		
		Admin admin = ad.getAdmin_id(cus.getAdminID());
		
		t.addCell(String.valueOf(cus.getCustomerID()),numberStyle);
		t.addCell(cus.getFullName(),numberStyle);
		t.addCell(cus.getIC(),numberStyle);
		t.addCell(String.valueOf(cus.getAge()),numberStyle);
		t.addCell(String.valueOf(cus.getBirthDate()),numberStyle);
		t.addCell(gender,numberStyle);
		t.addCell(cus.getEmail(),numberStyle);
		t.addCell(String.valueOf(count),numberStyle);
		t.addCell(admin.getFullName(),numberStyle);
		
		System.out.println(t.render());
		
	}
	
	public static void Admin_Edit_Customer_Console(Scanner sc,Customer c) {
		
		while(true) {
			
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             MANAGE CUSTOMER                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                              STEP THREE                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_Customers_Console(c);
			
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
			System.out.println("\t * IC : Must enter 12 digits.");
			System.out.println("\t * AGE :  Must be over 18 years old or under 60 years old.");
			System.out.println("\t * BIRTH DATE : need to follow format (year-month-day).");
			System.out.println("\t * GENDER : just can enter number (0. Male / 1. Female / 2. Others).");
			
			System.out.println();
			System.out.print("\t FULL NAME : " + color.TEXT_YELLOW);
			String user_fullname = sc.nextLine();
			
			if(user_fullname.equals("")) {
				System.out.println(color.TEXT_RED+"\t\t Full name cannot be empty."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t IC NUMBER : " + color.TEXT_YELLOW);
			String user_ic = sc.nextLine();
			
			boolean b = cd.CheckCustomer_IC(user_ic);
			if(b == true || user_ic.length() != 12) {
				System.out.println(color.TEXT_RED+"\t\t This ic already exists or IC number wrong."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t AGE : " + color.TEXT_YELLOW);
			String user_age = sc.nextLine();
			
			try {
				Integer.parseInt(user_age);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Age input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int age = Integer.valueOf(user_age);
			
			if(age < 18 || age > 90) {
				System.out.println(color.TEXT_RED+"\t\t Age input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BIRTH DATE  : " + color.TEXT_YELLOW);
			String user_birthdate = sc.nextLine();
			Date date = null;

	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            sdf.setLenient(false);
	            sdf.parse(user_birthdate); 
	            int pos1 = user_birthdate.indexOf("-");
	            int pos2 = user_birthdate.indexOf("-",pos1+1);
	            String y = user_birthdate.substring(0,pos1);
	            String m = user_birthdate.substring(pos1+1,pos2);
	            String d = user_birthdate.substring(pos2+1);
	            date = Date.valueOf(LocalDate.of(Integer.valueOf(y),Integer.valueOf(m),Integer.valueOf(d)));
	        } catch (ParseException e) {
	            System.out.println(color.TEXT_RED+"\t\t Birth Date Format Wrong..."+color.TEXT_RESET);
	            sc.nextLine();
				return;
	        }
			
			System.out.print(color.TEXT_RESET+"\t\t GENDER  : " + color.TEXT_YELLOW);
			String user_gender = sc.nextLine();
			
			try {
				Integer.parseInt(user_gender);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Gender input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int gender = Integer.valueOf(user_gender);
			
			if(gender != 0)
				if(gender != 1)
					if(gender != 2){
						System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Male / 1. Female / 2. Others)."+color.TEXT_RESET);
						sc.nextLine();
						return;
					}
			
			System.out.print(color.TEXT_RESET+"\t\t EMAIL : " + color.TEXT_YELLOW);
			String user_email = sc.nextLine();
			
			boolean email_bool = cd.CheckCustomer_Email(user_email);
			if(!user_email.contains("@gmail.com")) {
				System.out.println(color.TEXT_RED+"\t\t Email Format Wrong."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(email_bool == true) {
				System.out.println(color.TEXT_RED+"\t\t This email already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET);
					
			Customer cus = new Customer();
			cus.setCustomerID(c.getCustomerID());
			cus.setFullName(user_fullname);
			cus.setFullName(user_fullname);
			cus.setIC(user_ic);
			cus.setAge(age);
			cus.setBirthDate(date);
			cus.setGender(gender);
			cus.setEmail(user_email);
			cus.setAdminID(c.getAdminID());
			
			cd.updateCurtomer(cus);
			sc.nextLine();
			return;
			
			
		}
		
	}
	
	public static void Admin_Create_Account_Customer_Console(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             CREATE ACCOUNT                              ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                              NEW ACCOUNT                                "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to add account or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;
			
			Customer cus = cd.getCustomer(id);
			if(cus != null) {
				Add_Account(sc,cus);
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
		}
		
	}
	
	public static void Add_Account(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                             CREATE ACCOUNT                              ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                              NEW ACCOUNT                                "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_Customers_Console(cus);
			
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
			System.out.println("\t * USER NAME : Must enter 6 digits.");
			System.out.println("\t * PASSWORD : Must enter 6 digits.");
			System.out.println("\t * VERTFYIMAGE : Must enter 12 digits.");
			System.out.println("\t * BANK TYPE : just can enter number (0. public bank / 1. mybank).");
			
			System.out.println();
			System.out.print("\t USER NAME : " + color.TEXT_YELLOW);
			String user_name = sc.nextLine();
			
			boolean b_name = accDB.CheckAccount_Username(user_name);
			
			if(user_name.equals("") || user_name.length() != 6) {
				System.out.println(color.TEXT_RED+"\t\t User name cannot be empty or Must enter 6 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(b_name == true) {
				System.out.println(color.TEXT_RED+"\t\t This username already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t PASSWORD : " + color.TEXT_YELLOW);
			String user_password = sc.nextLine();
			
			boolean b_password = accDB.CheckAccount_Password(user_password);
			
			if(user_password.equals("") || user_password.length() != 6) {
				System.out.println(color.TEXT_RED+"\t\t Password cannot be empty or Must enter 6 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(b_password == true) {
				System.out.println(color.TEXT_RED+"\t\t This password already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t VERTFYIMAGE : " + color.TEXT_YELLOW);
			String user_verify = sc.nextLine();
			
			if(user_verify.equals("") || user_verify.length() != 12) {
				System.out.println(color.TEXT_RED+"\t\t Verify cannot be empty or Must enter 12 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BANK TYPE : " + color.TEXT_RESET);
			String user_banktype = sc.nextLine();

			
			try {
				Integer.parseInt(user_banktype);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Bank Type input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int banktype = Integer.valueOf(user_banktype);
			
			if(banktype != 0)
				if(banktype != 1) {
					System.out.println(color.TEXT_RED+"\t\t Just can enter (0. public bank / 1. mybank)."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			
			
			if(banktype == 0)
				user_banktype = "Public Bank";
			else if(banktype == 1)
				user_banktype = "MyBank";
			
			Account acc = new Account();
			acc.setCustomerID(cus.getCustomerID());
			acc.setUsername(user_name);
			acc.setPassword(user_password);
			acc.setUserStatus("Active");
			acc.setLoginLimit(3);
			acc.setVerifyimage(user_verify);
			acc.setBankType(user_banktype);
			
			accDB.CreateAccount(acc);
			sc.nextLine();
			return;
			
		}
	}
	
	public static void Admin_Show_All_Customers_Account_Console() {
		
		List<Customer> list = cd.getAllCustomer();
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("FullName");
		t.addCell("IC");
		t.addCell("Age");
		t.addCell("BirthDate");
		t.addCell("Gender");
		t.addCell("Email");
		t.addCell("Number Of Accounts");
		t.addCell("Admin Create");
		
		for(Customer cus : list) {
			
			String gender = "";
			if(cus.getGender() == 0)
				gender = "Male";
			else if(cus.getGender() == 1)
				gender = "Female";
			else if(cus.getGender() == 2)
				gender = "Others";
			
			int count = accDB.getAccount_Number(cus.getCustomerID());
			
			Admin admin = ad.getAdmin_id(cus.getAdminID());
			
			if(count != 0) {
				t.addCell(String.valueOf(cus.getCustomerID()),numberStyle);
				t.addCell(cus.getFullName(),numberStyle);
				t.addCell(cus.getIC(),numberStyle);
				t.addCell(String.valueOf(cus.getAge()),numberStyle);
				t.addCell(String.valueOf(cus.getBirthDate()),numberStyle);
				t.addCell(gender,numberStyle);
				t.addCell(cus.getEmail(),numberStyle);
				t.addCell(String.valueOf(count),numberStyle);
				t.addCell(admin.getFullName(),numberStyle);
			}
			
			
		}
		
		System.out.println(t.render());
		
	}
		
	public static void Manage_Account(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                              MANAGE ACCOUNT                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_RED+"                               (1) STEP ONE                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Account_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to view account details or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Customer cus = cd.getCustomer(id);
			
			if(cus != null) {
				int count = accDB.getAccount_Number(cus.getCustomerID());
				if(count != 0) 
					Check_Account_Detail(sc,cus);
				else {
					System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
					sc.nextLine();
					continue;
				}
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
	}
	
	public static void Check_Account_Detail(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                              MANAGE ACCOUNT                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_WHITE+"                               (2) STEP TWO                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Print_Account_Detail(cus);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a account id to manage or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Account acc = accDB.getAccount_ByID(id);
			if(acc != null && acc.getCustomerID() == cus.getCustomerID()) {
				Edit_Delete_Acount(sc,acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}
	
	public static void Print_Account_Detail(Customer cus) {
		
		List<Account> list = accDB.getAccount_Detail(cus.getCustomerID());
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("Customer Name");
		t.addCell("Username");
		t.addCell("Password");
		t.addCell("UserStatus");
		t.addCell("LoginLimit");
		t.addCell("VerifyImage");
		t.addCell("BankType");
		t.addCell("Bank Account");
		
		if(list.size() == 0) {
			
			
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			
			System.out.println(t.render());
			
		}else {

			for(var acc : list) {
				
				Customer customers = cd.getCustomer(acc.getCustomerID());

				List<BankAccountID> num = bankDB.getBankAccountID_Account_List(acc.getAccountID());
				
				t.addCell(String.valueOf(acc.getAccountID()),numberStyle);
				t.addCell(String.valueOf(customers.getFullName()),numberStyle);
				t.addCell(acc.getUsername(),numberStyle);
				t.addCell(acc.getPassword(),numberStyle);
				t.addCell(acc.getUserStatus(),numberStyle);
				t.addCell(String.valueOf(acc.getLoginLimit()),numberStyle);
				t.addCell(acc.getVerifyimage(),numberStyle);
				t.addCell(acc.getBankType());
				t.addCell(String.valueOf(num.size()),numberStyle);
			}
			
			System.out.println(t.render());
			
		}
		
		
		
	}
	
	public static void Edit_Delete_Acount(Scanner sc,Account acc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                              MANAGE ACCOUNT                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_WHITE+"                              (3) STEP THREE                                "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			System.out.println("\t 1. Edit");
			System.out.println("\t 2. Delete");
			System.out.println("\t 0. Back");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Edit_Account(sc,acc);
					return;
				case "2":
					Delete_Account(sc,acc);
					return;
				case "0":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}
		}
	}
	
	public static void Delete_Account(Scanner sc,Account acc) {
		//delete account
		//delete bank account
		
		func.Clear();
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                          ");
		System.out.println(color.TEXT_WHITE+"                          CONFIRM DELETE ACCOUNT                          "+color.TEXT_PURPLE);
		System.out.println("                                                                          "+color.TEXT_RESET);
		 
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t 1. CONFIRM"+color.TEXT_RESET);
		System.out.print(" / ");
		System.out.print(color.TEXT_CYAN+"2. CANCEL"+color.TEXT_RESET);
		System.out.println();
		System.out.println();
		System.out.print("\t Select Your Option: ");

		String opt = sc.nextLine();
		switch(opt) {
			case "1":
				accDB.deleteAccound(acc.getAccountID());
				List<BankAccountID> list = bankDB.getBankAccountID_Account_List(acc.getAccountID());
				bankDB.deleteBankAccountID_list(list);
				sc.nextLine();
				return;
			case "2":
				return;
			default:
				System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
				sc.nextLine();
				return;
			
		}
		
	}
	
	public static void Edit_Account(Scanner sc,Account acc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                              MANAGE ACCOUNT                             ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                            ");
			System.out.println(color.TEXT_WHITE+"                               (4) STEP FOUR                                "+color.TEXT_PURPLE);
			System.out.println("                                                                            "+color.TEXT_RESET);
			
			System.out.println();
			Print_Account_One(acc);
			
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
			System.out.println("\t * USER NAME : Must enter 6 digits.");
			System.out.println("\t * PASSWORD : Must enter 6 digits.");
			System.out.println("\t * VERTFYIMAGE : Must enter 12 digits.");
			System.out.println("\t * BANK TYPE : just can enter number (0. public bank / 1. mybank).");
			System.out.println("\t * UserStatus : just can enter number (0. Active / 1. Blocked).");
			System.out.println("\t * LOGIN LIMIT : just can enter number 0 ~ 3.");
			
			System.out.println();
			System.out.print("\t USER NAME : " + color.TEXT_YELLOW);
			String user_name = sc.nextLine();
			
			boolean b_name = accDB.CheckAccount_Username(user_name);
			
			if(user_name.equals("") || user_name.length() != 6) {
				System.out.println(color.TEXT_RED+"\t\t User name cannot be empty or Must enter 6 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(b_name == true && user_name != acc.getUsername()) {
				System.out.println(color.TEXT_RED+"\t\t This username already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t PASSWORD : " + color.TEXT_YELLOW);
			String user_password = sc.nextLine();
			
			boolean b_password = accDB.CheckAccount_Password(user_password);
			
			if(user_password.equals("") || user_password.length() != 6) {
				System.out.println(color.TEXT_RED+"\t\t Password cannot be empty or Must enter 6 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else if(b_password == true && user_password != acc.getPassword()) {
				System.out.println(color.TEXT_RED+"\t\t This password already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t VERTFYIMAGE : " + color.TEXT_YELLOW);
			String user_verify = sc.nextLine();
			
			if(user_verify.equals("") || user_verify.length() != 12) {
				System.out.println(color.TEXT_RED+"\t\t Verify cannot be empty or Must enter 12 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BANK TYPE : " + color.TEXT_RESET);
			String user_banktype = sc.nextLine();

			
			try {
				Integer.parseInt(user_banktype);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Gender input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int banktype = Integer.valueOf(user_banktype);
			
			if(banktype != 0)
				if(banktype != 1) {
					System.out.println(color.TEXT_RED+"\t\t Just can enter (0. public bank / 1. mybank)."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			
			System.out.print(color.TEXT_RESET+"\t\t UserStatus : " + color.TEXT_RESET);
			String user_UserStatus = sc.nextLine();

			try {
				Integer.parseInt(user_banktype);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t UserStatus input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int status = Integer.valueOf(user_banktype);
			
			if(status != 0)
				if(status != 1) {
					System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Active / 1. Blocked)."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			
			System.out.print(color.TEXT_RESET+"\t\t Login Limit : " + color.TEXT_RESET);
			String user_limit = sc.nextLine();

			try {
				Integer.parseInt(user_limit);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Login Limit input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int limit = Integer.valueOf(user_limit);
			
			if(limit != 0)
				if(limit != 1) 
					if(limit != 2)
						if(limit != 3) {
							{
								System.out.println(color.TEXT_RED+"\t\t Just can enter 0 ~ 3 number."+color.TEXT_RESET);
								sc.nextLine();
								return;
							}
						}
			
			
			if(banktype == 0)
				user_banktype = "Public Bank";
			else if(banktype == 1)
				user_banktype = "MyBank";
			
			if(status == 0)
				user_UserStatus = "Active";
			else if(status == 1)
				user_UserStatus = "Blocked";
			
			Account acc1 = new Account();
			acc1.setAccountID(acc.getAccountID());
			acc1.setCustomerID(acc.getCustomerID());
			acc1.setUsername(user_name);
			acc1.setPassword(user_password);
			acc1.setUserStatus(user_UserStatus);
			acc1.setLoginLimit(limit);
			acc.setVerifyimage(user_verify);
			acc.setBankType(user_banktype);
			
			accDB.updateAccount(acc,0);
			sc.nextLine();
			return;
		}
		
	}
	
	public static void Print_Account_One(Account acc) {
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("Customer Name");
		t.addCell("Username");
		t.addCell("Password");
		t.addCell("UserStatus");
		t.addCell("LoginLimit");
		t.addCell("VerifyImage");
		t.addCell("BankType");
		t.addCell("Bank Account");
		
		Customer customers = cd.getCustomer(acc.getCustomerID());
		
		List<BankAccountID> num = bankDB.getBankAccountID_Account_List(acc.getAccountID());
		
		t.addCell(String.valueOf(acc.getAccountID()),numberStyle);
		t.addCell(String.valueOf(customers.getFullName()),numberStyle);
		t.addCell(acc.getUsername(),numberStyle);
		t.addCell(acc.getPassword(),numberStyle);
		t.addCell(acc.getUserStatus(),numberStyle);
		t.addCell(String.valueOf(acc.getLoginLimit()),numberStyle);
		t.addCell(acc.getVerifyimage(),numberStyle);
		t.addCell(acc.getBankType());
		t.addCell(String.valueOf(num.size()),numberStyle);
		
		System.out.println(t.render());
		
		
	}
	
	public static void Add_Bank_Account(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CREATE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                            NEW BANK ACCOUNT                             "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Account_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to view account detail or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;
			
			Customer cus = cd.getCustomer(id);
			
			if(cus != null) {
				int count = accDB.getAccount_Number(cus.getCustomerID());
				if(count != 0) 
					BankAccount_Acount_Detail(sc,cus);
				else {
					System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
					sc.nextLine();
					continue;
				}
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}
	
	public static void BankAccount_Acount_Detail(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CREATE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                              (2) STEP TWO                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Print_Account_Detail(cus);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a account id to add bank account or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Account acc = accDB.getAccount_ByID(id);
			if(acc != null && acc.getCustomerID() == cus.getCustomerID()) {
				Add_Bank_Account(sc,acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}
	
	public static void Add_Bank_Account(Scanner sc,Account acc) {
		
		
		func.Clear();
		System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
		System.out.println("                          CREATE BANK ACCOUNT                            ");
		System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
		
		System.out.println();
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
		System.out.println(color.TEXT_WHITE+"                            (3) STEP THREE                               "+color.TEXT_PURPLE);
		System.out.println("                                                                         "+color.TEXT_RESET);
		
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
		System.out.println("\t * ACCOUNT TYPE : just can enter number (0. Savings/Current Account / 1. Credit Card).");
		System.out.println("\t * BANK ACCOUNT NUMBER : Must enter 7 digits.");
		System.out.println("\t * CARD NUMBER : Must enter 16 digits.");
		System.out.println("\t * PIN NUMBER : Must enter 6 digits.");
		
		System.out.println();
		System.out.print("\t ACCOUNT TYPE : " + color.TEXT_YELLOW);
		String acc_type = sc.nextLine();
		
		try {
			Integer.parseInt(acc_type);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t Account type input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		int acctype = Integer.valueOf(acc_type);
		
		if(acctype != 0)
			if(acctype != 1) {
				System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Savings/Current Account / 1. Credit Card)."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
		
		System.out.print(color.TEXT_RESET+"\t\t BANK ACCOUNT NUMBER : " + color.TEXT_YELLOW);
		String acc_number = sc.nextLine();
		
		if(acc_number.length() != 7) {
			System.out.println(color.TEXT_RED+"\t\t Bank Account Number must enter 7 digits."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		try {
			Long.parseLong(acc_number);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t Bank Account Number input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		long bank_num = Long.parseLong(acc_number);
		
		boolean b1 = bankDB.CheckBankAccountID_BankAccNo(bank_num);
		if(b1 == true) {
			System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		
		System.out.print(color.TEXT_RESET+"\t\t CARD NUMBER : " + color.TEXT_YELLOW);
		String card_num = sc.nextLine();
		
		if(card_num.length() != 16) {
			System.out.println(color.TEXT_RED+"\t\t Card Number must enter 7 digits."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		try {
			Long.parseLong(card_num);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t Card Number input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		long card_num_type = Long.parseLong(card_num);
		
		boolean b2 = bankDB.CheckBankAccountID_CardNumber(card_num_type);
		if(b2 == true) {
			System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		System.out.print(color.TEXT_RESET+"\t\t PIN NUMBER : " + color.TEXT_YELLOW);
		String pin_num = sc.nextLine();
		
		if(pin_num.length() != 6) {
			System.out.println(color.TEXT_RED+"\t\t Pin Number must enter 6 digits."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		try {
			Integer.parseInt(pin_num);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t Pin Number input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		int pin = Integer.parseInt(pin_num);
		
		boolean b3 = bankDB.CheckBankAccountID_PinNumber(pin);
		if(b3 == true) {
			System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		System.out.println(color.TEXT_RESET);
		double limit = 0;
		if(acctype == 0) {
			acc_type = "Savings/Current Account";
			limit = 50000.00;
		}
		else if(acctype == 1) {
			acc_type = "Credit Card";
			limit = 10000.00;
		}
			
		
		BankAccountID bank = new BankAccountID();
		bank.setAccountID(acc.getAccountID());
		bank.setAccountType(acc_type);
		bank.setBankAccNo(bank_num);
		bank.setCardNumber(card_num_type);
		bank.setPinNumber(pin);
		bank.setBalance(20);
		bank.setCardLimit(limit);
		bank.setBankAcStatus(0);
		
		bankDB.CreateBankAccountID(bank);
		sc.nextLine();
		return;
		
		
	}
	
	public static void Manage_Bank_Account(Scanner sc) {
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_RED+"                               (1) STEP ONE                              "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Admin_Show_All_Customers_Account_Console();
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a customer id to view account detail or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;
			
			Customer cus = cd.getCustomer(id);
			
			if(cus != null) {
				int count = accDB.getAccount_Number(cus.getCustomerID());
				if(count != 0) 
					Manage_BankAccount_Acount_Detail(sc,cus);
				else {
					System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
					sc.nextLine();
					continue;
				}
			}else {
				System.out.println(color.TEXT_RED+"This customer id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
	}
	
	public static void Manage_BankAccount_Acount_Detail(Scanner sc,Customer cus) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                              (2) STEP TWO                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Print_Account_Detail(cus);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a account id to view bank account or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			Account acc = accDB.getAccount_ByID(id);
			if(acc != null && acc.getCustomerID() == cus.getCustomerID()) {
				Select_BankAccount_Acount_Detail(sc,acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}
	
	public static void Select_BankAccount_Acount_Detail(Scanner sc,Account acc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                            (3) STEP THREE                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Print_Bank_Account_Detail(acc);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"Please select a account id to view bank account or enter 0 to back: "+color.TEXT_RESET);
			String cus_id = sc.nextLine();
			
			try {
				Integer.parseInt(cus_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(cus_id);
			if(id == 0)
				return;

			BankAccountID bank_acc = bankDB.getBankAccountID(id);
			if(bank_acc != null && bank_acc.getAccountID() == acc.getAccountID()) {
				Edit_Delete_Bank_Account(sc,bank_acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
		}
		
	}
	
	public static void Edit_Delete_Bank_Account(Scanner sc , BankAccountID bank) {
		
		while(true){
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                            (4) STEP FOUR                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			System.out.println("\t 1. Edit");
			System.out.println("\t 2. Delete");
			System.out.println("\t 0. Back");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Edit_Bank_Account(sc,bank);
					return;
				case "2":
					Delete_Bank_Account(sc,bank);
					return;
				case "0":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}
			
		}
		
	}
	
	public static void Edit_Bank_Account(Scanner sc,BankAccountID bank) {
		
		while(true){
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          MANAGE BANK ACCOUNT                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                         ");
			System.out.println(color.TEXT_WHITE+"                            (5) STEP FIVE                               "+color.TEXT_PURPLE);
			System.out.println("                                                                         "+color.TEXT_RESET);
			
			System.out.println();
			Print_Bank_Account_Detail_One(bank);
			System.out.println();
					
			System.out.println(color.TEXT_RED+"\t\t Note:"+color.TEXT_RESET);
			System.out.println("\t * ACCOUNT TYPE : just can enter number (0. Savings/Current Account / 1. Credit Card).");
			System.out.println("\t * BANK ACCOUNT NUMBER : Must enter 7 digits.");
			System.out.println("\t * CARD NUMBER : Must enter 16 digits.");
			System.out.println("\t * PIN NUMBER : Must enter 6 digits.");
			System.out.println("\t * BALANCE : Amount must be above RM 20.00.");
			System.out.println("\t * BANK ACCOUNT STATUS : just can enter number (0. Active / 1. Blocked).");
			
			System.out.println();
			System.out.print("\t ACCOUNT TYPE : " + color.TEXT_YELLOW);
			String acc_type = sc.nextLine();
			
			try {
				Integer.parseInt(acc_type);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Account type input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int acctype = Integer.valueOf(acc_type);
			
			if(acctype != 0)
				if(acctype != 1) {
					System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Savings/Current Account / 1. Credit Card)."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			
			System.out.print(color.TEXT_RESET+"\t\t BANK ACCOUNT NUMBER : " + color.TEXT_YELLOW);
			String acc_number = sc.nextLine();
			
			if(acc_number.length() != 7) {
				System.out.println(color.TEXT_RED+"\t\t Bank Account Number must enter 7 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			try {
				Long.parseLong(acc_number);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Bank Account Number input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			long bank_num = Long.parseLong(acc_number);
			
			boolean b1 = bankDB.CheckBankAccountID_BankAccNo(bank_num);
			if(b1 == true && bank_num != bank.getBankAccNo()) {
				System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			
			System.out.print(color.TEXT_RESET+"\t\t CARD NUMBER : " + color.TEXT_YELLOW);
			String card_num = sc.nextLine();
			
			if(card_num.length() != 16) {
				System.out.println(color.TEXT_RED+"\t\t Card Number must enter 7 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			try {
				Long.parseLong(card_num);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Card Number input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			long card_num_type = Long.parseLong(card_num);
			
			boolean b2 = bankDB.CheckBankAccountID_CardNumber(card_num_type);
			if(b2 == true && card_num_type != bank.getCardNumber()) {
				System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t PIN NUMBER : " + color.TEXT_YELLOW);
			String pin_num = sc.nextLine();
			
			if(pin_num.length() != 6) {
				System.out.println(color.TEXT_RED+"\t\t Pin Number must enter 6 digits."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			try {
				Integer.parseInt(pin_num);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Pin Number input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int pin = Integer.parseInt(pin_num);
			
			boolean b3 = bankDB.CheckBankAccountID_PinNumber(pin);
			if(b3 == true && pin != bank.getPinNumber()) {
				System.out.println(color.TEXT_RED+"\t\t This number already exists."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BALANCE : " + color.TEXT_YELLOW);
			String balance = sc.nextLine();
			
			try {
				Double.parseDouble(balance);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Balance input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			double balance_d = Double.parseDouble(balance);
			if(balance_d < 20) {
				System.out.println(color.TEXT_RED+"\t\t Amount must be above RM 20.00."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.print(color.TEXT_RESET+"\t\t BANK ACCOUNT STATUS : " + color.TEXT_YELLOW);
			String bastatus = sc.nextLine();
			
			try {
				Integer.parseInt(bastatus);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Balance input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int bankstatus = Integer.parseInt(bastatus);
			if(bankstatus != 0)
				if(bankstatus != 1) {
					System.out.println(color.TEXT_RED+"\t\t Just can enter (0. Active / 1. Blocked)."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			
			
			System.out.println(color.TEXT_RESET);
			double limit = 0;
			if(acctype == 0) {
				acc_type = "Savings/Current Account";
				limit = 50000.00;
			}
			else if(acctype == 1) {
				acc_type = "Credit Card";
				limit = 10000.00;
			}
				
			
			BankAccountID bank1 = new BankAccountID();
			bank1.setBankAccountID(bank.getBankAccountID());
			bank1.setAccountID(bank.getAccountID());
			bank1.setAccountType(acc_type);
			bank1.setBankAccNo(bank_num);
			bank1.setCardNumber(card_num_type);
			bank1.setPinNumber(pin);
			bank1.setBalance(balance_d);
			bank1.setCardLimit(bank.getCardLimit());
			bank1.setBankAcStatus(bankstatus);
			
			bankDB.updateBankAccountID(bank1);
			sc.nextLine();
			return;
		}
		
	}
	
	public static void Delete_Bank_Account(Scanner sc,BankAccountID bank) {
		
		func.Clear();
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                          ");
		System.out.println(color.TEXT_WHITE+"                       CONFIRM DELETE BANK ACCOUNT                        "+color.TEXT_PURPLE);
		System.out.println("                                                                          "+color.TEXT_RESET);
		 
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t 1. CONFIRM"+color.TEXT_RESET);
		System.out.print(" / ");
		System.out.print(color.TEXT_CYAN+"2. CANCEL"+color.TEXT_RESET);
		System.out.println();
		System.out.println();
		System.out.print("\t Select Your Option: ");

		String opt = sc.nextLine();
		switch(opt) {
			case "1":
				bankDB.deleteBankAccountID(bank.getBankAccountID());
				sc.nextLine();
				return;
			case "2":
				return;
			default:
				System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
				sc.nextLine();
				return;
			
		}
		
	}
	
	public static void Print_Bank_Account_Detail(Account acc) {
		
		List<BankAccountID> list = bankDB.getBankAccountID_Account_List(acc.getAccountID());
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("AccountID");
		t.addCell("AccountType");
		t.addCell("Bank Account No");
		t.addCell("CardNumber");
		t.addCell("Pin");
		t.addCell("Balance");
		t.addCell("Remaining CardLimit");
		t.addCell("Bank Account Status");
		
		if(list.size() == 0) {
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
			t.addCell("NULL",numberStyle);
		}else {
			for(var b : list) {

				String status = null;
				if(b.getBankAcStatus() == 0)
					status = "Active";
				else if(b.getBankAcStatus() == 1)
					status = "Blocked";

				String balance = String.format("%.2f",b.getBalance());
				String cardlimit = String.format("%.2f",b.getCardLimit());
				
				t.addCell(String.valueOf(b.getBankAccountID()),numberStyle);
				t.addCell(String.valueOf(b.getAccountID()),numberStyle);
				t.addCell(b.getAccountType(),numberStyle);
				t.addCell(String.valueOf(b.getBankAccNo()),numberStyle);
				t.addCell(String.valueOf(b.getCardNumber()),numberStyle);
				t.addCell(String.valueOf(b.getPinNumber()),numberStyle);
				t.addCell("RM "+balance,numberStyle);
				t.addCell("RM "+cardlimit,numberStyle);
				t.addCell(status,numberStyle);
			}
		}
		
		
		
		System.out.println(t.render());
		
		
	}
	
    public static void Print_Bank_Account_Detail_One(BankAccountID b) {

		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(9,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("AccountID");
		t.addCell("AccountType");
		t.addCell("Bank Account No");
		t.addCell("CardNumber");
		t.addCell("Pin");
		t.addCell("Balance");
		t.addCell("Remaining CardLimit");
		t.addCell("Bank Account Status");
		
		String status = null;
		if(b.getBankAcStatus() == 0)
			status = "Active";
		else if(b.getBankAcStatus() == 1)
			status = "Blocked";
		
		t.addCell(String.valueOf(b.getBankAccountID()),numberStyle);
		t.addCell(String.valueOf(b.getAccountID()),numberStyle);
		t.addCell(b.getAccountType(),numberStyle);
		t.addCell(String.valueOf(b.getBankAccNo()),numberStyle);
		t.addCell(String.valueOf(b.getCardNumber()),numberStyle);
		t.addCell(String.valueOf(b.getPinNumber()),numberStyle);
		t.addCell("RM "+String.valueOf(b.getBalance()),numberStyle);
		t.addCell("RM "+String.valueOf(b.getCardLimit()));
		t.addCell(status,numberStyle);
		
		System.out.println(t.render());
		
		
	}
	
	
	
	
	
	
}
