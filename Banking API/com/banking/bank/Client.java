package com.banking.bank;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private String name;
	
	private int clientId;
	
	private Set<Account> accounts = new HashSet<Account>();
	
	public Client(String name, int clientId, Set<Account> accounts) {
		this.name = name;
		this.clientId = clientId;
		this.accounts = accounts;
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
		return "Client [name=" + name + ", clientId=" + clientId + ", accounts=" + accounts + "]";
	}

}
