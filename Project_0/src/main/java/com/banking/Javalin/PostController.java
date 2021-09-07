package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;


public class PostController {
	
	/**
	 * Handler for creating new clients
	 */
	public static Handler NewClient = ctx -> {
		Client client = new Client();

		// get client data from body
		client = ctx.bodyAsClass(client.getClass());

		//checks to see if all fields were changed from default
		if (client.getClientId() == 0 || client.getName() == null) {
			ctx.status(400);
			ctx.result("Client not created");
			
		} else {
			Client.getClients().add(client);

			DAOimpl.instance().addClient(Driver.connection, client);

			ctx.status(201);
			ctx.json(client);
		}
	};

	/**
	 * Handler for creating new accounts
	 */
	public static Handler NewAccount = ctx -> {
		Account account = new Account();

		// get account data from body
		account = ctx.bodyAsClass(account.getClass());

		//checks to see if all fields were changed from default 
		if (account.getClientId() == 0 || account.getNickName() == null 
				|| account.getAccountType() == null || account.getAccountId() == 0 ||
				
				//checks to see if account properly matches client from path
				DAOimpl.instance().getClient(Driver.connection, 
						Integer.parseInt(ctx.pathParam(":client"))).getClientId() != account.getClientId()) {
			
			ctx.status(400);
			ctx.result("Account not created");
			
		} else {
			Account.getAccounts().add(account);

			DAOimpl.instance().addAccount(Driver.connection, account);

			ctx.status(201);
			ctx.json(account);
		}
	};

}
