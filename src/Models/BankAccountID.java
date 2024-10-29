package Models;

public class BankAccountID {

	private int BankAccountID;
	private int AccountID;
	private String AccountType;
	private long BankAccNo;
	private long CardNumber;
	private int PinNumber;
	private double Balance;
	private double CardLimit;//30000
	private int BankAcStatus; // 0 1
	
	public BankAccountID() {
		
	}
	
	public BankAccountID(int bid,int aic,String atype,long bankaccno,long cardnumber, int pin, double balance, double cardlimit, int bankstatus) {
		this.BankAccountID = bid;
		this.AccountID = aic;
		this.AccountType = atype;
		this.BankAccNo = bankaccno;
		this.CardNumber = cardnumber;
		this.PinNumber = pin;
		this.Balance = balance;
		this.CardLimit = cardlimit;
		this.BankAcStatus = bankstatus;
	}
	
	public int getBankAccountID() {
		return BankAccountID;
	}
	public void setBankAccountID(int bankAccountID) {
		BankAccountID = bankAccountID;
	}
	public int getAccountID() {
		return AccountID;
	}
	public void setAccountID(int accountID) {
		AccountID = accountID;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public long getBankAccNo() {
		return BankAccNo;
	}
	public void setBankAccNo(long bankAccNo) {
		BankAccNo = bankAccNo;
	}
	public long getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(long cardNumber) {
		CardNumber = cardNumber;
	}
	public int getPinNumber() {
		return PinNumber;
	}
	public void setPinNumber(int pinNumber) {
		PinNumber = pinNumber;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public double getCardLimit() {
		return CardLimit;
	}
	public void setCardLimit(double cardLimit) {
		CardLimit = cardLimit;
	}
	public int getBankAcStatus() {
		return BankAcStatus;
	}
	public void setBankAcStatus(int bankAcStatus) {
		BankAcStatus = bankAcStatus;
	}
	
}

enum EnumBankAcStatus{
	Active,Blocked
}
