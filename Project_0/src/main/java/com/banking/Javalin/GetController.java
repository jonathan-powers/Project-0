package com.banking.Javalin;

import java.util.Set;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class GetController {
	public static Handler fetchAllClients = ctx -> {

		Set<Client> Clients = DAOimpl.instance().getClients(Driver.connection);
		ctx.json(Clients);
	};

	public static Handler fetchAllAccounts = ctx -> {
		
		Set<Account> Accounts = DAOimpl.instance().getAccounts(
				Driver.connection, DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.splat(0))));
		ctx.json(Accounts);
 	};
 	
 	public static Handler fetchClient = ctx -> {
 		
 		Client client = DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.splat(0)));
 		ctx.json(client);
 	};
 	
 	public static Handler fetchAccount = ctx -> {
 		
 		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.splat(1)));
 		ctx.json(account);
 	};
}
