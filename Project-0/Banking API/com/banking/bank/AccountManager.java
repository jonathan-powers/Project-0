package com.banking.bank;

import java.util.HashSet;
import java.util.Set;

// class probably going to be removed when refactoring for working with DB

public class AccountManager {
	
	private static Set<Client> clients = new HashSet<Client>();

	public static Set<Client> getClients() {
		return clients;
	}

	public static void setClients(Set<Client> clients) {
		AccountManager.clients = clients;
	}
	

	


}
