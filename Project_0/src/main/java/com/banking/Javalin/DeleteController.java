package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class DeleteController {
	
	public static Handler DeleteClient = ctx -> {

		// TODO get Client data from body
		Client client = DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.splat(0)));
		DAOimpl.instance().deleteClient(Driver.connection, client);

		ctx.json(client);
	};
	
	public static Handler DeleteAccount = ctx -> {

		// TODO get Account data from body and
		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.splat(1)));
		DAOimpl.instance().deleteAccount(Driver.connection, account);

		ctx.json(account);
	};

}
