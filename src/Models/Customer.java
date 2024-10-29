package Models;

import java.sql.Date;

public class Customer {

	private int CustomerID;
	private String FullName;
	private String IC;
	private int Age;
	private Date BirthDate;
	private int Gender;
	private String Email;
	private int AdminID; 
	

	public Customer() {
		
	}

	public Customer(int customersid , String fullname,String ic,int age,Date birthdate,int Gender, String email,int AdminID) {
		this.CustomerID = customersid;
		this.FullName = fullname;
		this.IC = ic;
		this.Age = age;
		this.BirthDate = birthdate;
		this.Gender = Gender;
		this.Email = email;
		this.AdminID = AdminID;
	}
	
	
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getIC() {
		return IC;
	}
	public void setIC(String iC) {
		IC = iC;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public Date getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getAdminID() {
		return AdminID;
	}

	public void setAdminID(int adminID) {
		AdminID = adminID;
	}
	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}
	
}

enum EnumGender{
	Male,Female,Others
}