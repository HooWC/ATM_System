package Components;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import java.util.List;
import java.util.Random;
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

public class Com_Bank {

	static Account account = new Account();
	static Customer customer = new Customer();
	static BackgroundColor backgrondcolor = new BackgroundColor();
	static Color color = new Color();
	static Func func = new Func();
	static CustomerDB cd = new CustomerDB();
	static Admin admin_acc = new Admin();
	static AccountDB accDB = new AccountDB();
	static BankAccountIDDB bankDB = new BankAccountIDDB();
	static BankAccountID bank = new BankAccountID();
	static TransactionDB trDB = new TransactionDB();
	
	public static void Bank_Menu(BankAccountID bankaccount) {
		
		Scanner sc = new Scanner(System.in);
		
		account = accDB.getAccount_ByID(bankaccount.getAccountID());
		customer = cd.getCustomer(account.getCustomerID());
		bank = Update_Bank(bankaccount);
		
		while(true) {
			
			func.Clear();
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			System.out.println("                                      WELCOME                                      ");
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.print(color.TEXT_RED+"\t\t User Name : "+color.TEXT_RESET);
			System.out.println(color.TEXT_YELLOW+customer.getFullName()+color.TEXT_RESET);
			System.out.print(color.TEXT_RED+"\t\t Bank No   : " +color.TEXT_RESET);
			System.out.println(color.TEXT_YELLOW+bank.getBankAccNo()+color.TEXT_RESET);
			System.out.print(color.TEXT_RED+"\t\t Bank Type : " +color.TEXT_RESET);
			System.out.println(color.TEXT_YELLOW+account.getBankType()+color.TEXT_RESET);
			

			System.out.println();
			System.out.println("\t 1. Check Balance          6.  Third Party Transfer          11. Sign out");
			System.out.println("\t 2. Deposit Cash           7.  IBG and IBGT Transfer         12. Exit");
			System.out.println("\t 3. Withdraw Cash          8.  Monthly Statement");
			System.out.println("\t 4. Change PIN             9.  View Bank Transactions");
			System.out.println("\t 5. Change Password        10. My Profile");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Select_Check_Balance(sc);
					break;
				case "2":
					Deposit_Cash(sc);
					break;
				case "3":
					Withdraw_Cash(sc);
					break;
				case "4":
					Change_PIN(sc);
					break;
				case "5":
					Change_Password(sc);
					break;
				case "6":
					Third_Party_Transfer(sc);
					break;
				case "7":
				
					break;
				case "8":
			
					break;
				case "9":
					
					break;
				case "10":
					
					break;
				case "11":
					
					break;
				case "12":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}			
		}
	}
	
	public static void Select_Check_Balance(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			System.out.println("                                   CHECK BALANCE                                    ");
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println("\t 1. View current account balance");
			System.out.println("\t 2. View all current account balances");
			System.out.println("\t 0. Back");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option : "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Check_Balance(sc);
					break;
				case "2":
					Check_All_Balance(sc);
					break;
				case "0":
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
			}			
		}
		
	}
	
	public static void Check_Balance(Scanner sc) {
		
		func.Clear();
		System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
		System.out.println("                                   CHECK BALANCE                                    ");
		System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
		
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Dear "+color.TEXT_RESET + customer.getFullName() + "("+bank.getBankAccNo()+")");
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Your Available Balance: "+color.TEXT_RESET);
		System.out.println("\t ---------------------------------------");
		System.out.println(color.TEXT_YELLOW+"\t\t Ringgit Malaysia (RM)  : "+color.TEXT_RESET + String.format("%.2f",bank.getBalance()));
		System.out.println("\t ---------------------------------------");
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Press Any Key To Main Menu."+color.TEXT_RESET);
		sc.nextLine();
		return;
		
	}
	
	public static void Check_All_Balance(Scanner sc) {
	
		func.Clear();
		System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
		System.out.println("                                   CHECK BALANCE                                    ");
		System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
		
		System.out.println();
		
		List<BankAccountID> list = bankDB.getBankAccountID_Account_List(account.getAccountID());
		
		CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
		Table t = new Table(6,BorderStyle.CLASSIC,ShownBorders.ALL);
		t.addCell("ID");
		t.addCell("AccountType");
		t.addCell("Bank Account No");
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
			}
		}
		
		
		
		System.out.println(t.render());
		System.out.println();
		System.out.println(color.TEXT_RED+" Press Any Key To Main Menu."+color.TEXT_RESET);
		sc.nextLine();
		return;
	}
	
	public static void Deposit_Cash(Scanner sc) {
		//throws InterruptedException
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			System.out.println("                                   DEPOSIT CASH                                     ");
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(color.TEXT_YELLOW+"\t\t This ATM Only Accepts RM10 And Above"+color.TEXT_RESET);
			System.out.println();
			System.out.print("\t Insert The Deposit Amount: "+color.TEXT_YELLOW);
			String str = sc.nextLine();
			System.out.print(color.TEXT_RESET);
			
			try {
				Double.parseDouble(str);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Input error."+color.TEXT_RESET);
				sc.nextLine();
				continue;
			}
			
			double balance = Double.valueOf(str);
			if(balance < 10) {
			
				System.out.println(color.TEXT_RED+"\t\t Please Insert A Valid Amount."+color.TEXT_RESET);
				System.out.println(color.TEXT_RED+"\t\t This ATM Only Accepts Deposits Multiply Of RM10."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}else {
				
				if(balance % 10 == 0) {
					Loading();			
					try {
						Thread.sleep(3000);
					}catch(Exception ex) {
						System.out.println(ex.getMessage());
						return;
					}
					
					func.Clear();
					System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
					System.out.println("                     Summary Of Deposit                            ");
					System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
					
					System.out.println();
					System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                   "+color.TEXT_RESET);
					System.out.println();
					System.out.println(color.TEXT_CYAN+"\t\t Confirm Amount For Deposit: "+color.TEXT_RESET+"RM "+String.format("%.2f",balance));
					System.out.println();
					System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                   "+color.TEXT_RESET);
					System.out.println();
					System.out.print(color.TEXT_RED+"\t\t 1. CONFIRM"+color.TEXT_RESET);
					System.out.print(" / ");
					System.out.print(color.TEXT_RED+"2. CANCEL"+color.TEXT_RESET);
					System.out.print(" / ");
					System.out.print(color.TEXT_RED+"3. BACK TO MENU"+color.TEXT_RESET);
					System.out.println();
					
					System.out.print(color.TEXT_CYAN+"\t\t Select Your Option: "+color.TEXT_RESET);
					String opt = sc.nextLine();
					if(opt.equals("1")) {
						
						double new_balance = bank.getBalance() + balance;
						bank.setBalance(new_balance);
						bankDB.updateBankAccountID(bank);
						func.Clear();
						Loading();		
						
						try {
							Thread.sleep(3000);
						}catch(Exception ex) {
							System.out.println(ex.getMessage());
							return;
						}
						
						Right_Of_Balance(sc,balance,"d");
						func.email(balance, bank,account,"d");
						sc.nextLine();
						return;
						
					}else if(opt.equals("2")) {
						continue;
					}else if(opt.equals("3")) {
						func.Clear();
						return;
					}else {
						System.out.println();
						System.out.println(color.TEXT_RED+"\tPress Any Key To Retry.."+color.TEXT_RESET);
						sc.nextLine();
						continue;
					}

					
				}else {
					System.out.println(color.TEXT_RED+"\t\t Please Insert A Valid Amount."+color.TEXT_RESET);
					System.out.println(color.TEXT_RED+"\t\t This ATM Only Accepts Deposits Multiply Of RM10."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}	
			}			
		}	
	}
	
	public static BankAccountID Update_Bank(BankAccountID b) {
		return bankDB.getBankAccountID(b.getBankAccountID());
	}
	
	public static void Loading()  {
		
		func.Clear();
		System.out.print(backgrondcolor.ANSI_RED_BACKGROUND);
		System.out.println(" ================================================================ ");
		System.out.println("                          EDUVO BANK                              ");
		System.out.println("                                                                  ");
		System.out.println("              DELIVERING HOPE, HUMANITY &HAPPINESS                ");
		System.out.println(" ================================================================ ");
		System.out.print(color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Transaction In Progress... Please Wait A Moment..."+color.TEXT_RESET);
		
		
	}
	
	public static void Right_Of_Balance(Scanner sc,double balance,String opt) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		func.Clear();
		System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
		System.out.println("                     Deposit Confirmation                          ");
		System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
		System.out.println();
		System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                   "+color.TEXT_RESET);
		System.out.println();
		if(opt.equals("d"))
			System.out.println(color.TEXT_YELLOW+"\t\t Confirm Amount For Deposit: RM "+color.TEXT_RESET + String.format("%.2f",balance));
		else if(opt.equals("w"))
			System.out.println(color.TEXT_YELLOW+"\t\t Confirm Amount For Withdraw: RM "+color.TEXT_RESET + String.format("%.2f",balance));
		System.out.println(color.TEXT_YELLOW+"\t\t Date : " +color.TEXT_RESET+ dtf.format(now));
		System.out.println();
		System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                   "+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Your Amount Has Been Deposited Successfully..."+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Current Balance is: RM "+color.TEXT_RESET+String.format("%.2f",bank.getBalance()));
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Press Any Key To Main Menu."+color.TEXT_RESET);
		
	}
	
	public static void Withdraw_Cash(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			System.out.println("                                  WITHDRAW CASH                                     ");
			System.out.println(color.TEXT_RED+"===================================================================================="+color.TEXT_RESET);
			
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
			System.out.println();
			System.out.println("\t 1.Fast Cash RM 50        2.Fast Cash RM 100     ");
			System.out.println("\t 3.Fast Cash RM 200       4.Fast Cash RM 500     ");
			System.out.println("\t 5.Fast Cash RM 1000      6.Fast Cash RM 2000    ");
			System.out.println("\t 7.Fast Cash RM 5000      8.Other Amount         ");
			System.out.println(color.TEXT_RED+"\t\t 0. Return To Main Menu                          ");
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Select Your Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			
			try {
				Integer.parseInt(opt);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			int option = Integer.parseInt(opt);
			if(option == 0)
				return;
			
			switch (option)
			{
				case 1:
					Withdraw_Cash_Balance(sc,50);
					return;
				case 2:
					Withdraw_Cash_Balance(sc,100);
					return;
				case 3:
					Withdraw_Cash_Balance(sc,200);
					return;
				case 4:
					Withdraw_Cash_Balance(sc,500);
					return;
				case 5:
					Withdraw_Cash_Balance(sc,1000);
					return;
				case 6:
					Withdraw_Cash_Balance(sc,2000);
					return;
				case 7:
					Withdraw_Cash_Balance(sc,5000);
					return;
				case 8:
					Withdraw_Cash_Balance_Other(sc);
					return;
				default:
					System.out.println(color.TEXT_RED+"\t\t Input error."+color.TEXT_RESET);
					sc.nextLine();
					continue;

			}
		}	
	}
	
	public static void Withdraw_Cash_Balance(Scanner sc,double balance) {
		
		Loading();
		try {
			Thread.sleep(3000);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return;
		}
		
		func.Clear();
		
		double num = bank.getBalance();
		num -= balance;
		if(num < 20) {
			
			System.out.println("================================================================="+color.TEXT_RESET);
			System.out.println("                        Transaction Failed                       ");
			System.out.println("                       Insufficient Balance                      ");
			System.out.println("================================================================="+color.TEXT_RESET);
			System.out.println();
			System.out.println(color.TEXT_RED+"\t\t Minimum Balance Required In Account - RM20."+color.TEXT_RESET);
			System.out.println();
			sc.nextLine();
			return;
			
		}else {
			bank.setBalance(num);
			bankDB.updateBankAccountID(bank);
			Right_Of_Balance(sc,balance,"w");
			func.email(balance, bank, account, "w");
			sc.nextLine();
			return;
		}	
	}
	
	public static void Withdraw_Cash_Balance_Other(Scanner sc) {
		
		Loading();
		try {
			Thread.sleep(3000);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return;
		}
		
		func.Clear();
		
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		System.out.println("                     Other Amount Withdrawal                     ");
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Minimum Balance Required In Account - RM20          "+color.TEXT_RESET);
		System.out.println(color.TEXT_RED+"\t\t Account maximum balance requirement - RM10000          "+color.TEXT_RESET);
		System.out.println();
		System.out.print(color.TEXT_YELLOW+"\t\t Enter The Withdraw Amount: RM"+color.TEXT_RESET);
		String str = sc.nextLine();
		
		try {
			Double.parseDouble(str);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t Input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		double balance = Double.valueOf(str);
		if(balance < 1) {
			System.out.println(color.TEXT_RED+"\t\t Please Insert A Valid Amount."+color.TEXT_RESET);
			System.out.println(color.TEXT_RED+"\t\t This ATM Only Accepts Withdraw Higher Than RM1."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}else {
			
			if(balance > 10000) {
				System.out.println(color.TEXT_RED+"\t\t Please Insert A Valid Amount."+color.TEXT_RESET);
				System.out.println(color.TEXT_RED+"\t\t This ATM accepts cash withdrawal up to RM10000.00."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
				
			
			double num = bank.getBalance();
			num -= balance;
			if(num < 20) {
				func.Clear();
				System.out.println("================================================================="+color.TEXT_RESET);
				System.out.println("                        Transaction Failed                       ");
				System.out.println("                       Insufficient Balance                      ");
				System.out.println("================================================================="+color.TEXT_RESET);
				System.out.println();
				System.out.println(color.TEXT_RED+"\t\t Minimum Balance Required In Account - RM20."+color.TEXT_RESET);
				System.out.println();
				sc.nextLine();
				return;
			
			}else {
				func.Clear();
				bank.setBalance(num);
				bankDB.updateBankAccountID(bank);
				Right_Of_Balance(sc,balance,"w");
				func.email(balance, bank, account, "w");
				sc.nextLine();
				return;
			}
		}
	}
	
	public static void Change_PIN(Scanner sc) {
		
		func.Clear();
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		System.out.println("                          CHANGE PIN                             ");
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t Please Enter You Email: "+color.TEXT_RESET);
		String email = sc.nextLine();
		System.out.print(color.TEXT_CYAN+"\t\t Please Enter You PIN Number: "+color.TEXT_RESET);
		String pin = sc.nextLine();
		
		try {
			Integer.parseInt(pin);
		}catch(NumberFormatException ex) {
			System.out.println(color.TEXT_RED+"\t\t PIN Input error."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		int int_pin = Integer.parseInt(pin);
		
		if(bank.getPinNumber() == int_pin && email.equals(customer.getEmail())) {
			//change pin 
			//send email opt
			while(true) {
				func.Clear();
				System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
				System.out.println("                          CHANGE PIN                             ");
				System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
				System.out.println();
				
				System.out.print(color.TEXT_CYAN+"\t\t Please Enter You New PIN Number or enter 0 to bank: "+color.TEXT_RESET);
				String new_pin = sc.nextLine();
				
				try {
					Integer.parseInt(new_pin);
				}catch(NumberFormatException ex) {
					System.out.println(color.TEXT_RED+"\t\t PIN Input error."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
				
				if(new_pin.length() != 6) {
					System.out.println(color.TEXT_RED+"\t\t PIN number must be 6 digits."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}	
				
				int int_new_pin = Integer.parseInt(new_pin);
				if(int_new_pin == 0)
					return;
					
				boolean b = bankDB.CheckBankAccountID_PinNumber(int_new_pin);
				if(b == false) {
					
					//OTP
					func.Clear();
					String OPT = VerificationCode(8);
					func.email_OPT(OPT);
					java.util.Date date = new java.util.Date();
				    int s = date.getMinutes() + 2;
				    func.Clear();
					System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
					System.out.println("               PLEASE CHECK THE OTP OF YOUR EMAI                 ");
					System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
					System.out.println();
					System.out.print("\t\t OTP: ");
					String opt = sc.nextLine();
					
					Calendar cal = Calendar.getInstance();
					int mi = cal.get(Calendar.MINUTE);
					if(mi > s) {
						System.out.println(color.TEXT_RED+"\t\t Time expired, please try again."+color.TEXT_RESET);
						sc.nextLine();
						sc.nextLine();
						return;
					}
					
					if(opt.equals(OPT)) {
						
						bank.setPinNumber(int_new_pin);
						bankDB.updateBankAccountID(bank);
						func.Clear();
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDateTime now = LocalDateTime.now();
						
						System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
						System.out.println("                           Successful!                           ");
						System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
						
						System.out.println();
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_YELLOW+"\t\t You New PIN Number:  "+color.TEXT_RESET+int_new_pin);
						System.out.println(color.TEXT_YELLOW+"\t\t Date:  "+color.TEXT_RESET+dtf.format(now));
						System.out.println();
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_RED+"\t\t Press Any Key To Main Menu."+color.TEXT_RESET);
						sc.nextLine();
						return;	
						
					}else {
						System.out.println(color.TEXT_RED+"\t\t Wrong OTP,please try again."+color.TEXT_RESET);
						sc.nextLine();
						return;	
					}	
				}else {
					System.out.println(color.TEXT_RED+"\t\t This PIN number already exists."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			}	
		}else {
			System.out.println(color.TEXT_RED+"\t\t Error email or Error PIN."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
	}
		
	public static String VerificationCode(int n) {
		Random r = new Random();
		char[] word = new char[n];
		for (int i = 0; i < n; i++)
		{
			int ran = r.nextInt(3);
			if (ran == 0)
				word[i] = (char)r.nextInt(49, 58);
			else
				word[i] = (char)r.nextInt(48, 58);

		}
		String str = new String(word);
		return str;
	}
	
	public static void Change_Password(Scanner sc) {
		
		func.Clear();
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		System.out.println("                       CHANGE PASSWORD                           ");
		System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
		
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t Please Enter You User ID: "+color.TEXT_RESET);
		String user = sc.nextLine();
		System.out.print(color.TEXT_CYAN+"\t\t Please Enter You Password: "+color.TEXT_RESET);
		String password = sc.nextLine();
		
		if(account.getUsername().equals(user) && account.getPassword().equals(password)) {
			while(true) {
				func.Clear();
				System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
				System.out.println("                       CHANGE PASSWORD                           ");
				System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
				System.out.println();
				
				System.out.print(color.TEXT_CYAN+"\t Please Enter You New Password (6 digits) or enter 0 to bank: "+color.TEXT_RESET);
				String new_password = sc.nextLine();
				
				if(new_password.length() != 6) {
					System.out.println(color.TEXT_RED+"\t\t Password must be 6 digits."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}	
				
				
				if(new_password.equals("0"))
					return;
					
				boolean b = accDB.CheckAccount_Password(new_password);
				if(b == false) {
					
					//OTP
					func.Clear();
					String OPT = VerificationCode(8);
					func.email_OPT(OPT);
					java.util.Date date = new java.util.Date();
				    int s = date.getMinutes() + 2;
				    func.Clear();
					System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
					System.out.println("               PLEASE CHECK THE OTP OF YOUR EMAI                 ");
					System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
					System.out.println();
					System.out.print("\t\t OTP: ");
					String opt = sc.nextLine();
					
					Calendar cal = Calendar.getInstance();
					int mi = cal.get(Calendar.MINUTE);
					if(mi > s) {
						System.out.println(color.TEXT_RED+"\t\t Time expired, please try again."+color.TEXT_RESET);
						sc.nextLine();
						sc.nextLine();
						return;
					}
					
					if(opt.equals(OPT)) {
						
						account.setPassword(new_password);
						accDB.updateAccount(account, 1);
						func.Clear();
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDateTime now = LocalDateTime.now();
						
						System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
						System.out.println("                           Successful!                           ");
						System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
						
						System.out.println();
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_YELLOW+"\t\t You New Password is:  "+color.TEXT_RESET+new_password);
						System.out.println(color.TEXT_YELLOW+"\t\t Date:  "+color.TEXT_RESET+dtf.format(now));
						System.out.println();
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                                    "+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_RED+"\t\t Press Any Key To Main Menu."+color.TEXT_RESET);
						sc.nextLine();
						return;	
						
					}else {
						System.out.println(color.TEXT_RED+"\t\t Wrong OTP,please try again."+color.TEXT_RESET);
						sc.nextLine();
						return;	
					}	
				}else {
					System.out.println(color.TEXT_RED+"\t\t This password already exists."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			}	
		}else {
			System.out.println(color.TEXT_RED+"\t\t Error user id or Error password."+color.TEXT_RESET);
			sc.nextLine();
			return;
		}
		
		
	}
	
	public static void Third_Party_Transfer(Scanner sc) {
		
		while(true) {
			func.Clear();
			System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
			System.out.println("                        Fund Transfer                            ");
			System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
			
			System.out.println();
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+color.TEXT_BLACK+"                     (1) Destination Account                     "+color.TEXT_RESET);
			System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
			System.out.println();
			System.out.print(color.TEXT_YELLOW+"\t Please Enter Receiving Account Number or enter 0 to back: "+color.TEXT_RESET);
			String input_acc = sc.nextLine();
			
			if(input_acc.equals("0"))
				return;
			
			try {
				Long.parseLong(input_acc);
			}catch(NumberFormatException ex) {
				System.out.println(color.TEXT_RED+"\t\t Account Number Input error."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			
			long input_long_acc = Long.parseLong(input_acc);			
			BankAccountID b_acc = bankDB.getBankAccountID_ByAccountNUmber(input_long_acc);
			if(b_acc != null) {
				
				Account acc_check_banktype = accDB.getAccount_ByID(b_acc.getAccountID());
				if(account.getBankType().equals(acc_check_banktype.getBankType())) {
					Tr_StepTwo(acc_check_banktype);
					String opt = sc.nextLine();
					if(opt.equals("1")) {
						
						Loading();			
						try {
							Thread.sleep(3000);
						}catch(Exception ex) {
							System.out.println(ex.getMessage());
							return;
						}
						
						Customer c = cd.getCustomer(acc_check_banktype.getCustomerID());
						
						func.Clear();
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+color.TEXT_BLACK+"                  Please Insert Transfer Amount                  "+color.TEXT_RESET);
						System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_RED+"\t\t Receiving Account Details:"+color.TEXT_RESET);
						System.out.println();
						System.out.println(color.TEXT_YELLOW+"\t\t Full Name    : "+color.TEXT_RESET+c.getFullName());
						System.out.println(color.TEXT_YELLOW+"\t\t Currency     : Ringgit Malaysia (RM)"+color.TEXT_RESET);
						System.out.println();
						
						System.out.print(color.TEXT_CYAN+"\t\t Transfer Description: "+color.TEXT_RESET);
						String description = sc.nextLine();
						
						if(description.length() == 0) {
							System.out.println(color.TEXT_RED+"\t\t Transfer description cannot be empty."+color.TEXT_RESET);
							sc.nextLine();
							continue;
						}
						
						System.out.print(color.TEXT_CYAN+"\t\t You Will Transfer: RM "+color.TEXT_RESET);
						String tr_balance = sc.nextLine();
						
						try {
							Double.parseDouble(tr_balance);
						}catch(NumberFormatException ex) {
							System.out.println(color.TEXT_RED+"\t\t Transfer Balance Input error."+color.TEXT_RESET);
							sc.nextLine();
							return;
						}
						
						Double d = Double.parseDouble(tr_balance);
						
						if(d < 1) {
							System.out.println();
							System.out.println(color.TEXT_RED+"\t\t Please Insert A Valid Amount."+color.TEXT_RESET);
							System.out.println(color.TEXT_RED+"\t\t This ATM Only Accepts Withdraw Higher Than RM1."+color.TEXT_RESET);
							sc.nextLine();
							return;							
						}else {
							
							if(d > 10000) {
								System.out.println("The maximum transfer is only RM 10000.00");
								sc.nextLine();
								return;	
							}
							
							double myb = bank.getBalance();
							double limit = bank.getCardLimit();
							
							if(limit - d < 1) {
								System.out.println("There is still RM"+String.format("%.2f",limit)+" left in your limit for today and you can still transfer money.");
								sc.nextLine();
								return;	
							}else if(myb - d < 20) {
								func.Clear();
								System.out.println("================================================================="+color.TEXT_RESET);
								System.out.println("                        Transaction Failed                       ");
								System.out.println("                       Insufficient Balance                      ");
								System.out.println("================================================================="+color.TEXT_RESET);
								System.out.println();
								System.out.println(color.TEXT_RED+"\t\t Minimum Balance Required In Account - RM20."+color.TEXT_RESET);
								System.out.println();
								sc.nextLine();
								continue;
							}
							
							System.out.println();
							System.out.println(color.TEXT_WHITE+"\t\t -------------------------------------"+color.TEXT_RESET);
							System.out.println();
							System.out.print(color.TEXT_RED+"\t\t 1. CONFIRM"+color.TEXT_RESET);
							System.out.print(" / ");
							System.out.print(color.TEXT_RED+"2. CANCEL"+color.TEXT_RESET);
							System.out.print(" / ");
							System.out.println(color.TEXT_RED+"3. BACK TO MENU"+color.TEXT_RESET);
							System.out.println();
							System.out.print(color.TEXT_CYAN+"\t\t Select Your Option: "+color.TEXT_RESET);
							String opt2 = sc.nextLine();
							
							switch(opt2) {
							
								case "1":
									
									func.Clear();
									String OPT = VerificationCode(8);
									func.email_OPT(OPT);
									java.util.Date date_opt = new java.util.Date();
								    int s = date_opt.getMinutes() + 2;
								    func.Clear();
									System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
									System.out.println("               PLEASE CHECK THE OTP OF YOUR EMAI                 ");
									System.out.println(color.TEXT_RED+"================================================================="+color.TEXT_RESET);
									System.out.println();
									System.out.print("\t\t OTP: ");
									String opt_tr = sc.nextLine();
									
									Calendar cal = Calendar.getInstance();
									int mi = cal.get(Calendar.MINUTE);
									if(mi > s) {
										System.out.println(color.TEXT_RED+"\t\t Time expired, please try again."+color.TEXT_RESET);
										sc.nextLine();
										sc.nextLine();
										return;
									}
									
									if(opt_tr.equals(OPT)) {
										double nb = myb - d;
										double nc = limit - d;
										bank.setBalance(nb);
										bank.setCardLimit(nc);
										double tr_nb = b_acc.getBalance() + d;
										b_acc.setBalance(tr_nb);
										bankDB.updateBankAccountID(bank);
										bankDB.updateBankAccountID(b_acc);
										
										
										Date date = new Date(Calendar.getInstance().getTime().getTime());
										Transaction tr = new Transaction();
										tr.setBankAccountID(bank.getBankAccountID());
										tr.setTransactionType("Online");
										tr.setDate(date);
										tr.setDescription(description);
										tr.setCredit(b_acc.getBankAccNo());
										tr.setDebit(bank.getBankAccNo());
										tr.setBalance(d);
										
										trDB.CreateTransaction(tr);
										
										Loading();			
										try {
											Thread.sleep(3000);
										}catch(Exception ex) {
											System.out.println(ex.getMessage());
											return;
										}
										
										
										func.email_tr(bank.getBankAccNo(), b_acc.getBankAccNo(), account.getBankType(),d);
										Tr_Success();
										sc.nextLine();
										return;
									}else {
										System.out.println(color.TEXT_RED+"\t\t Wrong OTP,please try again."+color.TEXT_RESET);
										sc.nextLine();
										continue;	
									}

								case "2":
									continue;
								case "3":
									return;
								default:
									System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
									sc.nextLine();
									continue;
							
							}
						}
	
					}else if(opt.equals("2")) {
						continue;
					}else if(opt.equals("3")) {
						return;
					}else {
						System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
						sc.nextLine();
						continue;
					}
					
					
				}else {
					System.out.println(color.TEXT_RED+"\t\t This account does not exist."+color.TEXT_RESET);
					sc.nextLine();
					return;
				}
			}else {
				System.out.println(color.TEXT_RED+"\t\t This account does not exist."+color.TEXT_RESET);
				sc.nextLine();
				return;
			}
			

			
		}

		
		
	}
	
	public static void Tr_StepTwo(Account accd) {
		
		Customer c = cd.getCustomer(accd.getCustomerID());
		
		System.out.println();
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                 "+color.TEXT_RESET);
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+color.TEXT_WHITE+"                     (2) Confirm Account Details                 "+color.TEXT_RESET);
		System.out.println(backgrondcolor.ANSI_RED_BACKGROUND+"                                                                 "+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Please Confirm The Following Details:"+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Full Name    : "+color.TEXT_RESET + c.getFullName());
		System.out.println(color.TEXT_YELLOW+"\t\t Currency     : Ringgit Malaysia (RM)"+color.TEXT_RESET);
		System.out.println();
		System.out.print(color.TEXT_RED+"\t\t 1. CONFIRM"+color.TEXT_RESET);
		System.out.print(" / ");
		System.out.print(color.TEXT_RED+"2. CANCEL"+color.TEXT_RESET);
		System.out.print(" / ");
		System.out.println(color.TEXT_RED+"3. BACK TO MENU"+color.TEXT_RESET);
		System.out.println();
		System.out.print(color.TEXT_CYAN+"\t\t Select Your Option: "+color.TEXT_RESET);

		
	}
	
	public static void Tr_Success() {
		
		func.Clear();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
		System.out.println("                     Transaction Successful!                       ");
		System.out.println(color.TEXT_RED+" ================================================================= "+color.TEXT_RESET);
		System.out.println();
		System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_YELLOW+"\t\t Your Current Balance is: "+color.TEXT_RESET+"RM "+String.format("%.2f",bank.getBalance()));
		System.out.println(color.TEXT_YELLOW+"\t\t Date: "+color.TEXT_RESET+dtf.format(now));
		System.out.println();
		System.out.println(backgrondcolor.ANSI_WHITE_BACKGROUND+"                                                                 "+color.TEXT_RESET);
		System.out.println();
		System.out.println(color.TEXT_RED+"\t\t Press Any Key To Main Menu."+color.TEXT_RESET);

	}
	
	
	
	
}
