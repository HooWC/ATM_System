package AppDbContext;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
import Text_Color.Color;

public class CustomerDB {

	static Color color = new Color();
	private String tableName = "customers";
	private PreparedStatement stmt;
	
	
	public List<Customer> getAllCustomer(){
		String query = "SELECT * FROM " + tableName;
		List<Customer> listofcustomer = new ArrayList<>();
		DBConnection.createConnection();
		Customer cus = new Customer();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				int CustomerID = rs.getInt("CustomerID");
				String FullName = rs.getString("FullName");
				String IC = rs.getString("IC");
				int Age = rs.getInt("Age");
				Date BirthDate = rs.getDate("BirthDate");
				int Gender = rs.getInt("Gender");
				String Email = rs.getString("Email");
				int AdminID = rs.getInt("AdminID");
				listofcustomer.add(new Customer(CustomerID,FullName,IC,Age,BirthDate,Gender,Email,AdminID));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofcustomer;
	}
	
	public void CreateCustomer(Customer cus) {
		
		String insertQuert = "INSERT INTO "+tableName+
				" Values (NULL,?,?,?,?,?,?,?)";
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(insertQuert);
			stmt.setString(1, cus.getFullName());
			stmt.setString(2, cus.getIC());
			stmt.setInt(3, cus.getAge());
			stmt.setDate(4, cus.getBirthDate());
			stmt.setInt(5, cus.getGender());
			stmt.setString(6, cus.getEmail());
			stmt.setInt(7, cus.getAdminID());
			
			stmt.executeUpdate();//DB.SaveChange
		
			System.out.println(color.TEXT_RED+"\t\t Added Successful"+color.TEXT_RESET);
			
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public Customer getCustomer(int id) {
		
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE CustomerID=?";
		Customer cus = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{				
				int CustomerID = rs.getInt("CustomerID");
				String FullName = rs.getString("FullName");
				String IC = rs.getString("IC");
				int Age = rs.getInt("Age");
				Date BirthDate = rs.getDate("BirthDate");
				int Gender = rs.getInt("Gender");
				String Email = rs.getString("Email");
				int AdminID = rs.getInt("AdminID");
				cus = new Customer(CustomerID,FullName,IC,Age,BirthDate,Gender,Email,AdminID);		
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return cus;
	}
	
	public void updateCurtomer(Customer cus) {
		String UpdateQuert = "Update "+tableName+" SET FullName=?,IC=?,Age=?,BirthDate=?,Gender=?,Email=?,AdminID=? Where CustomerID=?";
		DBConnection.createConnection();
		try {
			stmt = DBConnection.conn.prepareStatement(UpdateQuert);
			stmt.setString(1, cus.getFullName());
			stmt.setString(2, cus.getIC());
			stmt.setInt(3, cus.getAge());
			stmt.setDate(4, cus.getBirthDate());
			stmt.setInt(5, cus.getGender());
			stmt.setString(6, cus.getEmail());
			stmt.setInt(7, cus.getAdminID());
			stmt.setInt(8, cus.getCustomerID());
			stmt.executeUpdate();
			System.out.println(color.TEXT_RED+"\t\t Update Successful"+color.TEXT_RESET);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deleteCustomer(int id) {
		String DeleteQuery = "DELETE FROM "+tableName+" WHERE CustomerID=?";
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
	
	public boolean CheckCustomer_IC(String ic) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE IC=?";
		DBConnection.createConnection();
		boolean b = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,ic);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				b = true;	
			else
				b = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return b;
	}
	
	public boolean CheckCustomer_Email(String email) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Email=?";
		DBConnection.createConnection();
		boolean b = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				b = true;	
			else
				b = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return b;
	}
	
	
	

}