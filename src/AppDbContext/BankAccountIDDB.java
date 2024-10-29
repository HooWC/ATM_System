package AppDbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.BankAccountID;
import Text_Color.Color;

public class BankAccountIDDB {

	static Color color = new Color();
	private String tableName = "bankaccountid";
	private PreparedStatement stmt;
	
	public List<BankAccountID> getAllBankAccountID(){
		String query = "SELECT * FROM " + tableName;
		List<BankAccountID> listofbank= new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				int BankAccountID = rs.getInt("BankAccountID");
				int AccountID = rs.getInt("AccountID");
				String AccountType = rs.getString("AccountType");
				long BankAccNo = rs.getLong("BankAccNo");
				long CardNumber = rs.getInt("CardNumber");
				int PinNumber = rs.getInt("PinNumber");
				double Balance = rs.getDouble("Balance");
				double CardLimit = rs.getDouble("CardLimit");
				int BankAcStatuc = rs.getInt("BankAcStatuc");
				
				listofbank.add(new BankAccountID(BankAccountID,AccountID,AccountType,BankAccNo,CardNumber,PinNumber,Balance,CardLimit,BankAcStatuc));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofbank;
	}
	
	public void CreateBankAccountID(BankAccountID bank) {
		
		String insertQuert = "INSERT INTO "+tableName+
				" Values (NULL,?,?,?,?,?,?,?,?)";
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(insertQuert);
			
			stmt.setInt(1, bank.getAccountID());
			stmt.setString(2, bank.getAccountType());
			stmt.setLong(3, bank.getBankAccNo());
			stmt.setLong(4, bank.getCardNumber());
			stmt.setInt(5, bank.getPinNumber());
			stmt.setDouble(6, bank.getBalance());
			stmt.setDouble(7, bank.getCardLimit());
			stmt.setInt(8, bank.getBankAcStatus());

			stmt.executeUpdate();//DB.SaveChange
			System.out.println(color.TEXT_RED+"\t\t Added Successful"+color.TEXT_RESET);
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public BankAccountID getBankAccountID(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE BankAccountID=?";
		BankAccountID bank = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{			
				int BankAccountID = rs.getInt("BankAccountID");
				int AccountID = rs.getInt("AccountID");
				String AccountType = rs.getString("AccountType");
				long BankAccNo = rs.getLong("BankAccNo");
				long CardNumber = rs.getLong("CardNumber");
				int PinNumber = rs.getInt("PinNumber");
				double Balance = rs.getDouble("Balance");
				double CardLimit = rs.getDouble("CardLimit");
				int BankAcStatuc = rs.getInt("BankAcStatuc");
				bank = new BankAccountID(BankAccountID,AccountID,AccountType,BankAccNo,CardNumber,PinNumber,Balance,CardLimit,BankAcStatuc);
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return bank;
	}
	
	public void updateBankAccountID(BankAccountID bank) {
		String UpdateQuert = "Update "+tableName+" SET AccountID=?,AccountType=?,BankAccNo=?,CardNumber=?,PinNumber=?,Balance=?,CardLimit=?,BankAcStatuc=? Where BankAccountID=?";
		DBConnection.createConnection();
		try {
			stmt = DBConnection.conn.prepareStatement(UpdateQuert);
			
			stmt.setInt(1, bank.getAccountID());
			stmt.setString(2, bank.getAccountType());
			stmt.setLong(3, bank.getBankAccNo());
			stmt.setLong(4, bank.getCardNumber());
			stmt.setInt(5, bank.getPinNumber());
			stmt.setDouble(6, bank.getBalance());
			stmt.setDouble(7, bank.getCardLimit());
			stmt.setInt(8, bank.getBankAcStatus());
			stmt.setInt(9, bank.getBankAccountID());
			
			stmt.executeUpdate();
			System.out.println(color.TEXT_RED+"\t\t Update Successful"+color.TEXT_RESET);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deleteBankAccountID(int id) {
		String DeleteQuery = "DELETE FROM "+tableName+" WHERE BankAccountID=?";
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
	
	public void deleteBankAccountID_list(List<BankAccountID> bank) {
		String DeleteQuery = "DELETE FROM "+tableName+" WHERE BankAccountID=?";
		DBConnection.createConnection();
		
		for(var b : bank) {
			try {
				stmt = DBConnection.conn.prepareStatement(DeleteQuery);
				stmt.setInt(1, b.getBankAccountID());
				stmt.executeUpdate();		
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				return;
			}
		}
		
		System.out.println(color.TEXT_RED+"\t\t Delete Bank Account Successful."+color.TEXT_RESET);
		
	}
	
	public boolean CheckBankAccountID_BankAccNo(long BankAccNo) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE BankAccNo=?";
		DBConnection.createConnection();
		boolean bank = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setLong(1,BankAccNo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				bank = true;	
			else
				bank = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return bank;
	}
	
	public boolean CheckBankAccountID_CardNumber(long CardNumber) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE CardNumber=?";
		DBConnection.createConnection();
		boolean bank = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setLong(1,CardNumber);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				bank = true;	
			else
				bank = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return bank;
	}
	
	public boolean CheckBankAccountID_PinNumber(int pin) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE PinNumber=?";
		DBConnection.createConnection();
		boolean bank = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,pin);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				bank = true;	
			else
				bank = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return bank;
	}
	
	public int getBankAccountID_Account(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE AccountID=?";
		int b = 0;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{			
				b += 1;
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return b;
	}
	
	public List<BankAccountID> getBankAccountID_Account_List(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE AccountID=?";
		List<BankAccountID> listofbank= new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int BankAccountID = rs.getInt("BankAccountID");
				int AccountID = rs.getInt("AccountID");
				String AccountType = rs.getString("AccountType");
				long BankAccNo = rs.getLong("BankAccNo");
				long CardNumber = rs.getLong("CardNumber");
				int PinNumber = rs.getInt("PinNumber");
				double Balance = rs.getDouble("Balance");
				double CardLimit = rs.getDouble("CardLimit");
				int BankAcStatuc = rs.getInt("BankAcStatuc");
				listofbank.add(new BankAccountID(BankAccountID,AccountID,AccountType,BankAccNo,CardNumber,PinNumber,Balance,CardLimit,BankAcStatuc));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofbank;
	}
	
	public BankAccountID getBankAccountID_ByAccountNUmber(long AccountNumber) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE BankAccNo=?";
		BankAccountID bank = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setLong(1,AccountNumber);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{			
				int BankAccountID = rs.getInt("BankAccountID");
				int AccountID = rs.getInt("AccountID");
				String AccountType = rs.getString("AccountType");
				long BankAccNo = rs.getLong("BankAccNo");
				long CardNumber = rs.getLong("CardNumber");
				int PinNumber = rs.getInt("PinNumber");
				double Balance = rs.getDouble("Balance");
				double CardLimit = rs.getDouble("CardLimit");
				int BankAcStatuc = rs.getInt("BankAcStatuc");
				bank = new BankAccountID(BankAccountID,AccountID,AccountType,BankAccNo,CardNumber,PinNumber,Balance,CardLimit,BankAcStatuc);
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return bank;
	}
	
	
}
