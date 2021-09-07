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
	
	
	public Account(int accountId, String accountType, int clientId, double balance) {
		this.accountId = accountId;
		this.nickName = accountType;
		this.accountType = accountType;
		this.clientId = clientId;
		this.balance = balance;
	}
	
	public Account(int accountId, String nickName, String accountType, double balance, int clientId) {
		this(accountId, accountType, clientId, balance);
		this.nickName= nickName;
	}
	
	public Account() {}
	
	/**
	 * static method to add money to account
	 * @param account to put money into
	 * @param amount of money to add to the account
	 */
	public static void deposit(Account account, double amount) {
		
		double prevAmount = account.getBalance();
		
		account.setBalance(prevAmount + amount);
	}
	
	/**
	 * static method to reduce the amount of money in a account
	 * @param account to take money from
	 * @param amount of money to take from the account
	 */
	public static void withdraw(Account account, double amount) {
		
		double prevAmount = account.getBalance();
		
		//check if account has enough money where method is called
		account.setBalance(prevAmount - amount);
	}
	
	/**
	 * method for transferring money between accounts
	 * @param fromAccount is the account money is taken from
	 * @param toAccount is the account money is put into
	 * @param amount taken from the first and put into the second
	 */
	public static void transfer(Account fromAccount, Account toAccount, double amount) {
		
		//check if account has enough money where method is called
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
