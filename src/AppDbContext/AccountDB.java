package AppDbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.Admin;
import Text_Color.Color;

public class AccountDB {

	static Color color = new Color();
	private String tableName = "account";
	private PreparedStatement stmt;
	
	public List<Account> getAllAccount(){
		String query = "SELECT * FROM " + tableName;
		List<Account> listofaccount = new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				int AccountID = rs.getInt("AccountID");
				int CustomerID = rs.getInt("CustomerID");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				String UserStatus = rs.getString("UserStatus");
				int LoginLimit = rs.getInt("LoginLimit");
				String Verifyimage = rs.getString("Verifyimage");
				String BankType = rs.getString("BankType");
				listofaccount.add(new Account(AccountID,CustomerID,Username,Password,UserStatus,LoginLimit,Verifyimage,BankType));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofaccount;
	}
	
	public void CreateAccount(Account acc) {
		
		String insertQuert = "INSERT INTO "+tableName+
				" Values (NULL,?,?,?,?,?,?,?)";
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(insertQuert);
			
			stmt.setInt(1, acc.getCustomerID());
			stmt.setString(2, acc.getUsername());
			stmt.setString(3, acc.getPassword());
			stmt.setString(4, acc.getUserStatus());
			stmt.setInt(5, acc.getLoginLimit());
			stmt.setString(6, acc.getVerifyimage());
			stmt.setString(7, acc.getBankType());

			stmt.executeUpdate();//DB.SaveChange
			System.out.println(color.TEXT_RED+"\t\t Added Successful"+color.TEXT_RESET);
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public Account getAccount(String username,String password) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Username=? AND Password=?";
		Account acc = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,username);
			stmt.setString(2,password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{			
				int AccountID = rs.getInt("AccountID");
				int CustomerID = rs.getInt("CustomerID");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				String UserStatus = rs.getString("UserStatus");
				int LoginLimit = rs.getInt("LoginLimit");
				String Verifyimage = rs.getString("Verifyimage");
				String BankType = rs.getString("BankType");
				acc = new Account(AccountID,CustomerID,Username,Password,UserStatus,LoginLimit,Verifyimage,BankType);
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return acc;
	}
	
	public void updateAccount(Account acc,int num) {
		String UpdateQuert = "Update "+tableName+" SET Username=?,Password=?,UserStatus=?,LoginLimit=?,Verifyimage=?,BankType=? Where AccountID=?";
		DBConnection.createConnection();
		try {
			stmt = DBConnection.conn.prepareStatement(UpdateQuert);
			stmt.setString(1, acc.getUsername());
			stmt.setString(2, acc.getPassword());
			stmt.setString(3, acc.getUserStatus());
			stmt.setInt(4, acc.getLoginLimit());
			stmt.setString(5, acc.getVerifyimage());
			stmt.setString(6, acc.getBankType());
			stmt.setInt(7, acc.getAccountID());
			
			stmt.executeUpdate();
			if(num == 0) {
				System.out.println(color.TEXT_RED+"\t\t Update Successful"+color.TEXT_RESET);
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deleteAccound(int id) {
		String DeleteQuery = "DELETE FROM "+tableName+" WHERE AccountID=?";
		DBConnection.createConnection();
		
		try {
			stmt = DBConnection.conn.prepareStatement(DeleteQuery);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println(color.TEXT_RED+"\t\t Delete Successful."+color.TEXT_RESET);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public boolean CheckAccount_Username(String username) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Username=?";
		DBConnection.createConnection();
		boolean account = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				account = true;	
			else
				account = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return account;
	}
	
	public boolean CheckAccount_Password(String password) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Password=?";
		DBConnection.createConnection();
		boolean account = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				account = true;	
			else
				account = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return account;
	}
	
	public int getAccount_Number(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE CustomerID=?";
		int num = 0;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{			
				num += 1;
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return num;
	}
	
	public List<Account> getAccount_Detail(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE CustomerID=?";
		List<Account> listofaccount = new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{			
				int AccountID = rs.getInt("AccountID");
				int CustomerID = rs.getInt("CustomerID");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				String UserStatus = rs.getString("UserStatus");
				int LoginLimit = rs.getInt("LoginLimit");
				String Verifyimage = rs.getString("Verifyimage");
				String BankType = rs.getString("BankType");
				listofaccount.add(new Account(AccountID,CustomerID,Username,Password,UserStatus,LoginLimit,Verifyimage,BankType));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofaccount;
	}
	
	public Account getAccount_ByID(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE AccountID=?";
		Account acc = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{			
				int AccountID = rs.getInt("AccountID");
				int CustomerID = rs.getInt("CustomerID");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				String UserStatus = rs.getString("UserStatus");
				int LoginLimit = rs.getInt("LoginLimit");
				String Verifyimage = rs.getString("Verifyimage");
				String BankType = rs.getString("BankType");
				acc = new Account(AccountID,CustomerID,Username,Password,UserStatus,LoginLimit,Verifyimage,BankType);
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return acc;
	}
	
	public Account CheckAccount_Username_Login(String username) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Username=?";
		DBConnection.createConnection();
		Account account = null;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int AccountID = rs.getInt("AccountID");
				int CustomerID = rs.getInt("CustomerID");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				String UserStatus = rs.getString("UserStatus");
				int LoginLimit = rs.getInt("LoginLimit");
				String Verifyimage = rs.getString("Verifyimage");
				String BankType = rs.getString("BankType");
				account = new Account(AccountID,CustomerID,Username,Password,UserStatus,LoginLimit,Verifyimage,BankType);
			}		
					
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return account;
	}
	
	public void getAllAccount_Limit_Bank(){
		List<Account> listofaccount = getAllAccount();

		for(Account list : listofaccount) {
			if(list.getLoginLimit() != 3) {
				list.setLoginLimit(3);
				updateAccount(list,1);
			}
		}
		
	}
	
	
	
}
