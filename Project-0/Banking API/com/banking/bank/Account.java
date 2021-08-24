package com.banking.bank;

public class Account {
	
	private double balance;
	
	private String nickName;
	
	private String accountType;
	
	private int accountId;
	
	//Constructor for initial accounts
	public Account(int accountId, String accountType) {
		this.accountId = accountId;
		this.nickName = accountType;
		this.accountType = accountType;
		//add to DB?
	}
	
	//Constructor for new accounts
	public Account(int accountId, String nickName, String accountType) {
		this(accountId, accountType);
		this.nickName = nickName;
		//other constructor adds to DB?
	}
	
	//Constructor for existing accounts from DB
	public Account(int accountId, String nickName, String accountType, double balance) {
		this(accountId, nickName, accountType);
		this.balance = balance;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	@Override
	public String toString() {
		return  nickName;
	}
	
	

}
