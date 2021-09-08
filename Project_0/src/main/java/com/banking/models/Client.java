package com.banking.models;

import java.util.HashSet;
import java.util.Set;

public class Client {
	private String name;
	
	private int clientId;
	
	private static Set<Client> clients = new HashSet<Client>();
	
	
	public Client(String name, int clientId) {
		this.name = name;
		this.clientId = clientId;
	}
	
	public Client() {
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

	
	public static Set<Client> getClients() {
		return clients;
	}

	public static void setClients(Set<Client> clients) {
		Client.clients = clients;
	}

	@Override
	public String toString() {
		return name;
	}

}
