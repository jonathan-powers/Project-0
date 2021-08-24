package com.banking.bank;

public class Account {
	
	private double balance;
	
	private String nickName;
	
	private String accountType;
	
	private Client client;
	
	private int accountId;
	
	public Account(Client client, int accountId, String nickName, String accountType, double balance) {
		this.client = client;
		this.accountId = accountId;
		this.nickName = nickName;
		this.accountType = accountType;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		return "Account [balance=" + balance + ", nickName=" + nickName + ", accountType=" + accountType + ", client="
				+ client + ", accountId=" + accountId + "]";
	}

}
