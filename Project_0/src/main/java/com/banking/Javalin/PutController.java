package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class PutController {

	/**
	 * Handler for updating Client data
	 */
	public static Handler UpdateClient = ctx -> {

		//get Client data from body
		Client client =  new Client();
		client = ctx.bodyAsClass(client.getClass());
		
		client = DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")));

		if (client.getClientId() == 0) {
			ctx.status(404);
			ctx.result("Client not Found");
		} else {
			DAOimpl.instance().updateClient(Driver.connection, client);
			
			ctx.status(200);
			ctx.json(client);
		}
	};

	/**
	 * Handler for updating Account data
	 */
	public static Handler UpdateAccount = ctx -> {

		//get Account data from body
		Account account = new Account();
		account = ctx.bodyAsClass(account.getClass());
		
		
		DAOimpl.instance().updateAccount(Driver.connection, account);
		account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.pathParam(":account")));

		
		if (account.getClientId() == 0 || 
				DAOimpl.instance().getClient(Driver.connection, 
						Integer.parseInt(ctx.pathParam(":client"))).getClientId() != account.getClientId()) {
			ctx.status(404);
			ctx.result("Account or Client not found");
		} else {
			ctx.status(200);
			ctx.json(account);
		}
	};

}
