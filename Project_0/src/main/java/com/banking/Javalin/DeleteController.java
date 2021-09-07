package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class DeleteController {

	/**
	 * Handler to delete Client
	 */
	public static Handler DeleteClient = ctx -> {

		// get Client data
		Client client = DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")));

		// Check to see if client exists
		if (client.getClientId() == 0) {
			ctx.status(404);
			ctx.result("Client not Found");
		} else {
			// Deletes client from DB
			DAOimpl.instance().deleteClient(Driver.connection, client);

			ctx.status(205);
			ctx.json(client);
		}
	};
	/**
	 * Handler to delete account
	 */
	public static Handler DeleteAccount = ctx -> {

		// get Account data
		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.pathParam(":account")));

		// Checks to see if client and account exist then checks if the account matches
		// the client
		if (account.getClientId() == 0 || account.getAccountId() == 0
				|| DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")))
						.getClientId() != account.getClientId()) {
			ctx.status(404);
			ctx.result("Client or Account not Found");
		} else {
			// deletes account from DB
			DAOimpl.instance().deleteAccount(Driver.connection, account);

			ctx.status(205);
			ctx.json(account);
		}
	};

}
