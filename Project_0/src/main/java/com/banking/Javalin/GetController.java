package com.banking.Javalin;

import java.util.HashSet;
import java.util.Set;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class GetController {

	/**
	 * Handler to return a set of all clients
	 */
	public static Handler fetchAllClients = ctx -> {

		Set<Client> Clients = DAOimpl.instance().getClients(Driver.connection);

		ctx.status(200);
		ctx.json(Clients);
	};

	/**
	 * Handler to return a set of all accounts belonging to a client specified by
	 * the path
	 */
	public static Handler fetchAllAccounts = ctx -> {

		Set<Account> Accounts = DAOimpl.instance().getAccounts(Driver.connection,
				DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client"))));

		// checks if there are query params in path

		try {
			double amountLessThan = Double.parseDouble(ctx.queryParam("amountLessThan"));
			double amountGreaterThan = Double.parseDouble(ctx.queryParam("amountGreaterThan"));
			
			//Creates set of accounts to remove
			Set<Account> Raccounts = new HashSet<Account>();
			
			//Checks if Account needs to be removed
			for (Account account : Accounts) {
				if (account.getBalance() < amountGreaterThan || account.getBalance() > amountLessThan) {
					Raccounts.add(account);
					//if accounts needs to be removed add it to Set
				}
			}
			//Removes Account from result
			for(Account account : Raccounts) {
				Accounts.remove(account);
			}
		} catch (Exception NullPointerException) {

		}

		// check to see if client exists
		if (DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")))
				.getClientId() == 0) {
			ctx.status(404);
			ctx.result("Client not Found");
		} else {
			ctx.status(200);
			ctx.json(Accounts);
		}

	};

	/**
	 * Handler to return a specific client specified by the path
	 */
	public static Handler fetchClient = ctx -> {

		Client client = DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")));

		// check to see if client exists
		if (client.getClientId() == 0) {
			ctx.status(404);
			ctx.result("Client not Found");
		} else {
			ctx.status(200);
			ctx.json(client);
		}
	};

	/**
	 * Handler to return a specific account specified by the path
	 */
	public static Handler fetchAccount = ctx -> {

		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.pathParam(":account")));

		// checks to see if account or client exist
		if (account.getClientId() == 0 || account.getAccountId() == 0
				|| DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")))
						.getClientId() != account.getClientId()) {
			ctx.status(404);
			ctx.result("Client or Account not Found");
		} else {
			ctx.status(200);
			ctx.json(account);
		}
	};
}
