package Components;

import java.util.List;
import java.util.Scanner;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import AppDbContext.AccountDB;
import AppDbContext.AdminDB;
import AppDbContext.BankAccountIDDB;
import AppDbContext.CustomerDB;
import Function.Func;
import Models.*;
import Text_Color.BackgroundColor;
import Text_Color.Color;

public class Com_Customer {

	static BackgroundColor backgrondcolor = new BackgroundColor();
	static Color color = new Color();
	static Func func = new Func();
	static AdminDB ad = new AdminDB();
	static CustomerDB cd = new CustomerDB();
	static Admin admin_acc = new Admin();
	static AccountDB accDB = new AccountDB();
	static BankAccountIDDB bankDB = new BankAccountIDDB();
	
	public static void Customer_Home_Console() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			func.Clear();
			
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CUSTOMER LOGIN PAGE                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.print("\t USER ID : " + color.TEXT_YELLOW);
			String admin_id = sc.nextLine();
			Account acc = accDB.CheckAccount_Username_Login(admin_id);
			if(acc != null) {
				Verication(sc,acc);
				return;
			}
			else {
				System.out.println(color.TEXT_RED+"\t\t Login failed, please try again."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
		}	
	}
	
	public static void Verication(Scanner sc,Account acc) {

		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CUSTOMER LOGIN PAGE                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                          ");
			System.out.println(color.TEXT_WHITE+"                             (2) STEP TWO                                 "+color.TEXT_PURPLE);
			System.out.println("                                                                          "+color.TEXT_RESET);
			
			System.out.println();
			System.out.print("\t\t");
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                           "+color.TEXT_RESET);		
			System.out.print("\t\t");
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+color.TEXT_BLACK+"                "+ acc.getVerifyimage() +"               "+color.TEXT_RESET);		
			System.out.print("\t\t");
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                           "+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t\t Is this your verification graph?"+color.TEXT_RESET);
			System.out.println();
			
			System.out.print(color.TEXT_CYAN+"\t\t\t 1. CONFIRM"+color.TEXT_RESET);
			System.out.print(" / ");
			System.out.print(color.TEXT_CYAN+"2. CANCEL"+color.TEXT_RESET);
			System.out.println();
			System.out.println();
			System.out.print("\t\t Select Your Option: "+ color.TEXT_YELLOW);

			String opt = sc.nextLine();
			System.out.print(color.TEXT_RESET);
			switch(opt) {
				case "1":
					Password_Login(sc,acc);
					return;
				case "2":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
					continue;
				
			}
		}
	}
	
	public static void Password_Login(Scanner sc,Account acc) {
		
		while(true) {
			
			func.Clear();
			
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CUSTOMER LOGIN PAGE                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                          ");
			System.out.println(color.TEXT_WHITE+"                          (3) STEP THREE                                  "+color.TEXT_PURPLE);
			System.out.println("                                                                          "+color.TEXT_RESET);
			
			System.out.println();
			
			if(acc.getLoginLimit() == 0) {
				System.out.println(color.TEXT_RED+"\t You have logged in incorrectly more than 3 times today, and cannot log in again today."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			System.out.println("\t USER ID  : " + color.TEXT_YELLOW+ acc.getUsername()+color.TEXT_RESET);
			System.out.print("\t PASSWORD : "+ color.TEXT_YELLOW);
			String password = sc.nextLine();
			System.out.print(color.TEXT_RESET);
			
			
			if(password.equals(acc.getPassword())) {
				Select_Bank_Account(sc,acc);
				return;
			}else {
				int count = acc.getLoginLimit() - 1;
				acc.setLoginLimit(count);
				accDB.updateAccount(acc,1);
				System.out.println(color.TEXT_RED+"\t\t Login failed, you have "+count+" more chances"+color.TEXT_RESET);
				sc.nextLine();
				return;
			}					
		}
		
	}
	
	public static void Select_Bank_Account(Scanner sc,Account acc) {
		
		while(true) {
			
			func.Clear();
			
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);
			System.out.println("                          CUSTOMER LOGIN PAGE                            ");
			System.out.println(color.TEXT_RED+"========================================================================="+color.TEXT_RESET);

			
			System.out.println();
			Print_Bank_Account_Detail(acc);
			System.out.println();
			
			System.out.print(color.TEXT_CYAN+"Please select which Bank Account to use or enter 0 to bank : "+color.TEXT_RESET);
			String bank_id = sc.nextLine();
			
			try {
				Integer.parseInt(bank_id);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			int id = Integer.valueOf(bank_id);
			if(id == 0)
				return;

			BankAccountID bank_acc = bankDB.getBankAccountID(id);
			if(bank_acc != null && bank_acc.getAccountID() == acc.getAccountID()) {
				Com_Bank bankclass = new Com_Bank();
				bankclass.Bank_Menu(bank_acc);
			}else {
				System.out.println(color.TEXT_RED+"This account id is does not exist."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}		
		}
		
		
	}
	
	public static void Print_Bank_Account_Detail(Account acc) {
		
		List<BankAccountID> list = bankDB.getBankAccountID_Account_List(acc.getAccountID());
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(7,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("AccountType");
		t.addCell("Bank Account No");
		t.addCell("Balance");
		t.addCell("Remaining CardLimit");
		t.addCell("Bank Account Status");
		t.addCell("Bank Type");
		
		if(list.size() == 0) {
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
				t.addCell(b.getAccountType(),numberStyle);
				t.addCell(String.valueOf(b.getBankAccNo()),numberStyle);
				t.addCell("RM "+balance,numberStyle);
				t.addCell("RM "+cardlimit,numberStyle);
				t.addCell(status,numberStyle);
				t.addCell(acc.getBankType(),numberStyle);
			}
		}
		
		
		
		System.out.println(t.render());
		
		
	}
	
	

	
}
