package Components;

import java.util.Scanner;

import AppDbContext.*;
import Function.Func;
import Text_Color.Color;

public class Menu {

	static Color color = new Color();
	static Func func = new Func();
	static AccountDB accDB = new AccountDB();
	
	public static void Home() {
		
		//Admin  
		//1. 创建Customer    
		//2. 删除Customer
		//3. 查看CUstomer
		//4. 修改Customer
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			System.out.println("\t    WELCOME TO EDUVO BANK");
			System.out.println(color.TEXT_RED+"==============================================="+color.TEXT_RESET);
			System.out.println();
			System.out.println("\t 1. Admin");
			System.out.println("\t 2. Login");
			System.out.println("\t 3. Fund Transfer");
			System.out.println("\t 0. Exit");
			System.out.println();
			System.out.print(color.TEXT_CYAN+"\t\t Option: "+color.TEXT_RESET);
			String opt = sc.nextLine();
			switch(opt) {
				case "1":
					Com_Admin admin = new Com_Admin();
					admin.Admin_Home_Console();
					func.Clear();
					break;
				case "2":
					Com_Customer customer = new Com_Customer();
					customer.Customer_Home_Console();
					func.Clear();
					break;
				case "3":
					break;
				case "0":
					accDB.getAllAccount_Limit_Bank();
					System.exit(0);
				default:
					System.out.println(color.TEXT_RED+"\t\t Wrong Option..."+color.TEXT_RESET);
					sc.nextLine();
					func.Clear();
			}
		}
		
	}

}
