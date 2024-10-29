package AppDbContext;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Models.Transaction;
import Text_Color.Color;

public class TransactionDB {

	static Color color = new Color();
	private String tableName = "transaction";
	private PreparedStatement stmt;
	
	public List<Transaction> getAllTransaction(){
		String query = "SELECT * FROM " + tableName;
		List<Transaction> listoftr= new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				int TransactionID = rs.getInt("TransactionID");
				int BankAccountID = rs.getInt("BankAccountID");
				String TransactionType = rs.getString("TransactionType");
				Date Date = rs.getDate("Date");
				String Description = rs.getString("Description");
				long Credit = rs.getLong("Credit");
				long Debit = rs.getLong("Debit");
				double Balance = rs.getDouble("Balance");
				
				listoftr.add(new Transaction(TransactionID,BankAccountID,TransactionType,Date,Description,Credit,Debit,Balance));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listoftr;
	}
	
	public void CreateTransaction(Transaction tr) {
		
		String insertQuert = "INSERT INTO "+tableName+
				" Values (NULL,?,?,?,?,?,?,?)";
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(insertQuert);
			
			stmt.setInt(1, tr.getBankAccountID());
			stmt.setString(2, tr.getTransactionType());
			stmt.setDate(3, tr.getDate());
			stmt.setString(4, tr.getDescription());
			stmt.setLong(5, tr.getCredit());
			stmt.setLong(6, tr.getDebit());
			stmt.setDouble(7, tr.getBalance());

			stmt.executeUpdate();//DB.SaveChange
			System.out.println(color.TEXT_RED+"\t\t Added Successful"+color.TEXT_RESET);
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public Transaction getTransaction(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE TransactionID=?";
		Transaction tr = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{			
				int TransactionID = rs.getInt("TransactionID");
				int BankAccountID = rs.getInt("BankAccountID");
				String TransactionType = rs.getString("TransactionType");
				Date Date = rs.getDate("Date");
				String Description = rs.getString("Description");
				long Credit = rs.getLong("Credit");
				long Debit = rs.getLong("Debit");
				double Balance = rs.getDouble("Balance");
				tr = new Transaction(TransactionID,BankAccountID,TransactionType,Date,Description,Credit,Debit,Balance);
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return tr;
	}
	
	public void updateTransaction(Transaction tr) {
		String UpdateQuert = "Update "+tableName+" SET TransactionType=?,Date=?,Description=?,Credit=?,Debit=?,Balance=? Where TransactionID=?";
		DBConnection.createConnection();
		try {
			stmt = DBConnection.conn.prepareStatement(UpdateQuert);
			
			
			stmt.setString(1, tr.getTransactionType());
			stmt.setDate(2, tr.getDate());
			stmt.setString(3, tr.getDescription());
			stmt.setLong(4, tr.getCredit());
			stmt.setLong(5, tr.getDebit());
			stmt.setDouble(6, tr.getBalance());
			stmt.setInt(7, tr.getTransactionID());
			
			stmt.executeUpdate();
			System.out.println(color.TEXT_RED+"\t\t Update Successful"+color.TEXT_RESET);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deleteTransaction(int id) {
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
	
	
	
}
