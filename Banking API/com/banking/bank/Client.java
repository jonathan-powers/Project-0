package com.banking.bank;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private String name;
	
	private int clientId;
	
	private Set<Account> accounts = new HashSet<Account>();
	
	//constructor for new clients
	public Client(String name) {
		this.name = name;
		
		//refactor ID when working with DB
		this.clientId = AccountManager.getClients().size();
		
		HashSet<Account> accounts = new HashSet<>();
		Account checking = new Account(accounts.size(), "checking");
		accounts.add(checking);
		
		Account saving = new Account(accounts.size(), "saving");
		accounts.add(saving);
		
		//adds to DB?
	}
	
	//constructor for existing clients
	public Client(String name, int clientId, Set<Account> accounts) {
		this.name = name;
		this.clientId = clientId;
		this.accounts = accounts;
	}
	
	public Account newAccount( String nickName, String accountType) {
		Account account = new Account( accounts.size(), nickName, accountType);
		
		accounts.add(account);
		//add account to DB?
		
		return account;
	}
	
	public static void deposit(Account account, double amount) {
		//check if client owns account
		
		double prevAmount = account.getBalance();
		
		account.setBalance(prevAmount + amount);
	}
	
	public static void withdraw(Account account, double amount) {
		//check if client owns account
		
		double prevAmount = account.getBalance();
		
		//check if account has enough money
		account.setBalance(prevAmount - amount);
	}
	
	//method for client transferring between their own accounts
	public static void transfer(Account fromAccount, Account toAccount, double amount) {
		//check if client owns accounts occurs in withdraw and deposit
		
		withdraw(fromAccount, amount);
		
		deposit(toAccount, amount);
	}
	
	//method for transferring between clients
	// don't want to load toClient and their account on application for privacy
	//refactor method when working with DB
	public static void transfer(Client fromClient, Client toClient, Account fromAccount, Account toAccount, double amount) {
		//check if clients owns accounts
		
		withdraw(fromAccount, amount);
		
		//want to adjust amount server-side
		deposit(toAccount, amount);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	
	@Override
	public String toString() {
		return name;
	}

}
