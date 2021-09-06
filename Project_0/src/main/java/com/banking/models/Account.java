package com.banking.models;

import java.util.HashSet;
import java.util.Set;

public class Account {
	
	private int clientId;
	
	private double balance;
	
	private String nickName;
	
	private String accountType;
	
	private int accountId;
	
	private static Set<Account> accounts = new HashSet<>();
	
	//Constructor for initial accounts
	public Account(int accountId, String accountType, int clientId) {
		this.accountId = accountId;
		this.nickName = accountType;
		this.accountType = accountType;
		this.clientId = clientId;
		this.balance = 0;
	}
	
	//Constructor for new accounts
	public Account(int accountId, String nickName, String accountType, int clientId) {
		this(accountId, accountType, clientId);
		this.nickName = nickName;
	}
	
	//Constructor for existing accounts from DB
	public Account(int accountId, String nickName, String accountType, double balance, int clientId) {
		this(accountId, nickName, accountType, clientId);
		this.balance = balance;
	}
	
	public Account() {
	}

	public static void deposit(Account account, double amount) {
		
		double prevAmount = account.getBalance();
		
		account.setBalance(prevAmount + amount);
	}
	
	public static void withdraw(Account account, double amount) {
		
		double prevAmount = account.getBalance();
		
		//check if account has enough money
		account.setBalance(prevAmount - amount);
	}
	
	//method for clients transferring between accounts
	public static void transfer(Account fromAccount, Account toAccount, double amount) {
		
		withdraw(fromAccount, amount);
		
		deposit(toAccount, amount);
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public static Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		Account.accounts = accounts;
	}
}
