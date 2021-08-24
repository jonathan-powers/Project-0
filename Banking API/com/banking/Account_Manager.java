package com.banking;

import java.util.HashSet;
import java.util.Set;

import com.banking.bank.*;

public class Account_Manager {
	
	private Set<Client> clients = new HashSet<Client>();

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	public void deposit(Client client, Account account, double amount) {
		//check if client owns account
	}
	
	public void withdraw(Client client, Account account, double amount) {
		//check if client owns account
	}
	
	//method for client transferring between their own accounts
	public void transfer(Client client, Account fromAccount, Account toAccount, double amount) {
		//check if client owns account
	}
	
	//method for transferring between clients
	public void transfer(Client fromClient, Client toClient, Account fromAccount, Account toAccount, double amount) {
		//check if clients owns accounts
	}

}
