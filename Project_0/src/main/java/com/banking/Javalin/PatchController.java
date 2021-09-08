package com.banking.Javalin;

import java.util.HashMap;
import java.util.Map;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;

import io.javalin.http.Handler;

public class PatchController {

	/**
	 * Handler that handles Depositing money into or Withdrawing money from account
	 */
	public static Handler DepOrWith = ctx -> {

		// get Account data
		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.pathParam(":account")));
		
		//checks to see if client and account
		if (account.getClientId() == 0 || account.getAccountId() == 0
				
				//checks to see if account matches client 
				|| DAOimpl.instance().getClient(Driver.connection, 
						Integer.parseInt(ctx.pathParam(":client"))).getClientId() != account.getClientId()) {
			
			ctx.status(404);
			ctx.result("Client or Account not Found");

		} else {

			// read body for amount 
			Map<String, Integer> map = new HashMap<String, Integer>();
			map = ctx.bodyAsClass(map.getClass());
			
			//checks to see if depositing or withdrawing from account 
			if (map.containsKey("deposit")) {
				
				Account.deposit(account, map.get("deposit"));
				DAOimpl.instance().updateAccount(Driver.connection, account);

				ctx.status(200);
				ctx.json(account);

			} else if (map.containsKey("withdraw")) {
				
				//checks to see if account has enough moneys
				if (map.get("withdraw") > account.getBalance()) {
					ctx.status(422);
					ctx.result("Insuffcient Funds");

				} else {
					Account.withdraw(account, map.get("withdraw"));
					DAOimpl.instance().updateAccount(Driver.connection, account);

					ctx.status(200);
					ctx.json(account);
				}

			} else {
				ctx.status(400);
				ctx.result("Improper/Empty body format");
			}
		}

	};

	/**
	 * Handler for transferring money between two accounts
	 */
	public static Handler Transfer = ctx -> {

		// get Account data
		Account account1 = DAOimpl.instance().getAccount(Driver.connection,
				Integer.parseInt(ctx.pathParam(":account")));
		
		Account account2 = DAOimpl.instance().getAccount(Driver.connection,
				Integer.parseInt(ctx.pathParam(":transfer")));
		
		//checks to see if client and account exist
		if (account1.getClientId() == 0 || account1.getAccountId() == 0
				
				//checks to see if account1 matches client
				|| DAOimpl.instance().getClient(Driver.connection, Integer.parseInt(ctx.pathParam(":client")))
						.getClientId() != account1.getClientId()) {
			ctx.status(404);
			ctx.result("Client or Account not Found");
			
			//checks to see if client or account exist
		} else if (account2.getClientId() == 0 || account2.getAccountId() == 0) {
			ctx.status(404);
			ctx.result("Client or Account not Found");

		} else {

			// read body for amount to transfer
			Map<String, Integer> map = new HashMap<String, Integer>();
			map = ctx.bodyAsClass(map.getClass());

			//checks to see if info was in the body
			if (map.containsKey("amount")) {
				
				//checks to see if "from account" has enough money to transfer
				if (map.get("amount") > account1.getBalance()) {
					ctx.status(422);
					ctx.result("Insuffcient Funds");

				} else {

					Account.transfer(account1, account2, map.get("amount"));

					DAOimpl.instance().updateAccount(Driver.connection, account1);
					DAOimpl.instance().updateAccount(Driver.connection, account2);

					ctx.json(account1);
				}
			} else { 
				ctx.status(400);
				ctx.result("Improper/Empty body format");
			}
		}
	};

}
