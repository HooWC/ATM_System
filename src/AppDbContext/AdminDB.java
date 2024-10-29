package AppDbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Admin;
import Text_Color.Color;

public class AdminDB {

	static Color color = new Color();
	private String tableName = "admin";
	private PreparedStatement stmt;
	
	public List<Admin> getAllAdmin(){
		String query = "SELECT * FROM " + tableName;
		List<Admin> listofadmin = new ArrayList<>();
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				int AdminID = rs.getInt("AdminID");
				String FullName = rs.getString("FullName");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				listofadmin.add(new Admin(AdminID,FullName,Username,Password));
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return listofadmin;
	}
	
	public void CreateAdmin(Admin admin) {
		
		String insertQuert = "INSERT INTO "+tableName+
				" Values (NULL,?,?,?)";
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(insertQuert);
			stmt.setString(1, admin.getFullName());
			stmt.setString(2, admin.getUsername());
			stmt.setString(3, admin.getPassword());
			stmt.executeUpdate();//DB.SaveChange
			System.out.println(color.TEXT_RED+"\t\t Added Successful"+color.TEXT_RESET);
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	public Admin getAdmin(String username,String password) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Username=? AND Password=?";
		Admin admin = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,username);
			stmt.setString(2,password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{				
				int AdminID = rs.getInt("AdminID");
				String FullName = rs.getString("FullName");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				admin = new Admin(AdminID,FullName,Username,Password);		
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return admin;
	}
	
	public Admin getAdmin_id(int id) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE AdminID=?";
		Admin admin = null;
		DBConnection.createConnection();
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{				
				String FullName = rs.getString("FullName");
				String Username = rs.getString("Username");
				String Password = rs.getString("Password");
				admin = new Admin(id,FullName,Username,Password);		
			}
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return admin;
	}
	
	public void updateAdmin(Admin admin) {
		String UpdateQuert = "Update "+tableName+" SET FullName=?,Username=?,Password=? Where AdminID=?";
		DBConnection.createConnection();
		try {
			stmt = DBConnection.conn.prepareStatement(UpdateQuert);
			stmt.setString(1, admin.getFullName());
			stmt.setString(2, admin.getUsername());
			stmt.setString(3, admin.getPassword());
			stmt.setInt(4, admin.getAdminID());
			stmt.executeUpdate();
			System.out.println(color.TEXT_RED+"\t\t Update Successful"+color.TEXT_RESET);
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void deleteAdmin(int id) {
		String DeleteQuery = "DELETE FROM "+tableName+" WHERE AdminID=?";
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
	
	public boolean CheckAdmin_Username(String username) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Username=?";
		DBConnection.createConnection();
		boolean admin = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				admin = true;	
			else
				admin = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return admin;
	}
	
	public boolean CheckAdmin_Password(String password) {
		String SelectQuery = "SELECT * FROM "+tableName+" WHERE Password=?";
		DBConnection.createConnection();
		boolean admin = false;
		
		try 
		{
			stmt = DBConnection.conn.prepareStatement(SelectQuery);
			stmt.setString(1,password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())		
				admin = true;	
			else
				admin = false;
		}
		catch(SQLException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
		return admin;
	}
	
	
	
}
