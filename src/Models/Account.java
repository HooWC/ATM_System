package Models;

public class Account {

	private int AccountID;
	private int CustomerID;
	private String Username;
	private String Password;
	private String UserStatus;
	private int LoginLimit;
	private String Verifyimage;
	private String BankType;
	
	public Account() {
		
	}
	
	public Account(int accountid,int customerid,String username,String password,String userstatus,int loginlimit,String verifyimage,String banktype) {
		this.AccountID = accountid;
		this.CustomerID = customerid;
		this.Username = username;
		this.Password = password;
		this.UserStatus = userstatus;
		this.LoginLimit = loginlimit;
		this.Verifyimage = verifyimage;
		this.BankType = banktype;
	}
	
	
	public int getAccountID() {
		return AccountID;
	}
	public void setAccountID(int accountID) {
		AccountID = accountID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
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

	public String getUserStatus() {
		return UserStatus;
	}

	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}

	public int getLoginLimit() {
		return LoginLimit;
	}
	public void setLoginLimit(int loginLimit) {
		LoginLimit = loginLimit;
	}
	public String getVerifyimage() {
		return Verifyimage;
	}
	public void setVerifyimage(String verifyimage) {
		Verifyimage = verifyimage;
	}
	public String getBankType() {
		return BankType;
	}
	public void setBankType(String bankType) {
		BankType = bankType;
	}
	
}

enum EnumStatus{
	Active,Blocked
}
