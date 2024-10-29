package Models;

public class Admin {

	private int AdminID;
	private String FullName;
	private String Username;
	private String Password;
	
	public Admin() {
		
	}
	
	public Admin(int AdminID,String FullName, String Username,String Password) {
		this.AdminID = AdminID;
		this.FullName = FullName;
		this.Username = Username;
		this.Password = Password;
	}
	
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
