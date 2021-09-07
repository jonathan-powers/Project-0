package com.banking.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.banking.models.Account;
import com.banking.models.Client;

public class DAOimpl implements DAO {
	
	private static DAOimpl dao = null;
	
	private DAOimpl() {
		
	}
	
	public static DAOimpl instance() {
		if (dao == null) {
            dao = new DAOimpl();
        }
        return dao;
	}

	@Override
	public Set<Client> getClients(Connection connection) throws SQLException {
		String sql = "SELECT * FROM customers";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Client row = new Client();
			row.setClientId(rs.getInt("customer_id"));
			row.setName(rs.getString("name"));
			Client.getClients().add(row);
		}
		return Client.getClients();
	}

	@Override
	public Set<Account> getAccounts(Connection connection, Client client) throws SQLException {
		String sql = "SELECT * FROM accounts WHERE customer_id = " + client.getClientId();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		HashSet<Account> Accounts = new HashSet<Account>();
		
		while(rs.next()) {
			Account row = new Account();
			row.setAccountId(rs.getInt("account_id"));
			row.setAccountType(rs.getString("account_type"));
			row.setClientId(rs.getInt("customer_id"));
			row.setNickName(rs.getString("account_name"));
			row.setBalance(rs.getDouble("balance"));
			row.setClientId(rs.getInt("customer_id"));
			Account.getAccounts().add(row);
			Accounts.add(row);
		}
		return Accounts;
	}

	@Override
	public Client getClient(Connection connection, int id) throws SQLException {
		String sql = "SELECT * FROM customers WHERE customer_id = " + id;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		Client row = new Client();
		while(rs.next()) {
			row.setClientId(rs.getInt("customer_id"));
			row.setName(rs.getString("name"));
		}
		return row;
	}

	@Override
	public Account getAccount(Connection connection, int id) throws SQLException {
		String sql = "SELECT * FROM accounts WHERE account_id = " + id;
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		Account row = new Account();
		while(rs.next()) {
			row.setAccountId(rs.getInt("account_id"));
			row.setAccountType(rs.getString("account_type"));
			row.setClientId(rs.getInt("customer_id"));
			row.setNickName(rs.getString("account_name"));
			row.setBalance(rs.getDouble("balance"));
			row.setClientId(rs.getInt("customer_id"));
		}
		return row;
	}

	@Override
	public void updateClient(Connection connection, Client client) throws SQLException {
		String sql = "UPDATE customers SET name = \"" + client.getName() + "\" WHERE customer_id = " + client.getClientId();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
	}

	@Override
	public void updateAccount(Connection connection, Account account) throws SQLException {
		String sql1 = "UPDATE accounts SET account_name = \"" + account.getNickName() + "\" WHERE account_id = " + account.getAccountId();
		PreparedStatement pstmt1 = connection.prepareStatement(sql1);
		pstmt1.executeQuery();
		String sql2 = "UPDATE accounts SET balance = " + account.getBalance() + " WHERE account_id = " + account.getAccountId();
		PreparedStatement pstmt2 = connection.prepareStatement(sql2);
		pstmt2.executeQuery();
		
	}

	@Override
	public void deleteClient(Connection connection, Client client) throws SQLException {
		String sql1 = "DELETE FROM accounts WHERE customer_id = " + client.getClientId();
		PreparedStatement pstmt1 = connection.prepareStatement(sql1);
		pstmt1.executeQuery();
		
		//Need to delete accounts first because customers id is a foreign key
		
		String sql2 = "DELETE FROM customers WHERE customer_id = " + client.getClientId();
		PreparedStatement pstmt2 = connection.prepareStatement(sql2);
		pstmt2.executeQuery();
		
	}

	@Override
	public void deleteAccount(Connection connection, Account account) throws SQLException {
		String sql = "DELETE FROM accounts WHERE account_id = " + account.getAccountId();
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
	}

	@Override
	public void addClient(Connection connection, Client client) throws SQLException {
		String sql = "INSERT INTO customers (customer_id,name) VALUES "
				+ "(" + client.getClientId()+ ", \"" + client.getName() + "\")";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
	}

	@Override
	public void addAccount(Connection connection, Account account) throws SQLException {
		String sql = "INSERT INTO accounts (account_id, account_type, account_name, balance, customer_id)"
				+ " VALUES (" + account.getAccountId() + ", \"" + account.getAccountType() + "\", \"" + account.getNickName()
				+ "\", " + account.getBalance() + "," + account.getClientId() +")";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.executeQuery();
		
	}


}
