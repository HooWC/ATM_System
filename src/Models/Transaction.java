package Models;

import java.sql.Date;

public class Transaction {

	private int TransactionID;
	private int BankAccountID;
	private String TransactionType;// Online
	private Date Date;
	private String Description;// Eduvo sdn bhd 
	private long Credit;// 别人转账给我 bank account no
	private long Debit;// 我转账给别人 bank account no
	private double Balance;
	
	public Transaction() {
		
	}
	
	public long getCredit() {
		return Credit;
	}

	public void setCredit(long credit) {
		Credit = credit;
	}

	public long getDebit() {
		return Debit;
	}

	public void setDebit(long debit) {
		Debit = debit;
	}

	public Transaction(int trid,int bankaccid,String trtype, Date date, String description,long credit,long debit, double balance) {
		
		this.TransactionID = trid;
		this.BankAccountID = bankaccid;
		this.TransactionType = trtype;
		this.Date = date;
		this.Description = description;
		this.Credit = credit;
		this.Debit = debit;
		this.Balance = balance;
		
	}
	
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public int getBankAccountID() {
		return BankAccountID;
	}
	public void setBankAccountID(int bankAccountID) {
		BankAccountID = bankAccountID;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	
}
