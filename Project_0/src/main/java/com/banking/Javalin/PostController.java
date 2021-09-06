package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;
import com.banking.models.Client;

import io.javalin.http.Handler;

public class PostController {
	public static Handler NewClient = ctx -> {
		Client client = new Client();
		
		//TODO get Client data from body
		client.setClientId(0);
		client.setName("");
		Client.getClients().add(client);
		
		DAOimpl.instance().addClient(Driver.connection,client);
		ctx.json(client);
	};
	
	public static Handler NewAccount = ctx -> {
		Account account = new Account();
		
		//TODO get account data from body
		account.setAccountId(0);
		account.setNickName("");
		account.setAccountType("");
		account.setBalance(0);
		account.setClientId(0);
		Account.getAccounts().add(account);
		
		DAOimpl.instance().addAccount(Driver.connection,account);
		ctx.json(account);
	};

}
