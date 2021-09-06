package com.banking.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import com.banking.models.Account;
import com.banking.models.Client;

public interface DAO {
	public Set<Client> getClients(Connection connection) throws SQLException;
	public Set<Account> getAccounts(Connection connection, Client client) throws SQLException;
	
	public Client getClient(Connection connection,int id) throws SQLException;
	public Account getAccount(Connection connection, int id) throws SQLException;
	
	public void updateClient(Connection connection, Client client) throws SQLException;
	public void updateAccount(Connection connection, Account account) throws SQLException;
	
	public void deleteClient(Connection connection, Client client) throws SQLException;
	public void deleteAccount(Connection connection, Account acount) throws SQLException;
	
	public void addClient(Connection connection, Client client) throws SQLException;
	public void addAccount(Connection connection, Account account) throws SQLException;
}
