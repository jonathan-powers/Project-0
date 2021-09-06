package com.banking.Javalin;

import com.banking.Database.DAOimpl;
import com.banking.models.Account;

import io.javalin.http.Handler;

public class PatchController {
	
	public static Handler DepOrWith = ctx -> {

		//TODO get Account data 
		Account account = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.splat(1)));
		
		//TODO read body and run deposit or withdraw
		if (true) {
			Account.deposit(account, 0);
		} else if (true) {
			Account.withdraw(account, 0);
		} else {
			//TODO return an exception?/400 code?
		}
		
		DAOimpl.instance().updateAccount(Driver.connection, account);

		ctx.json(account);
	};
	
	public static Handler Transfer = ctx -> {

		//TODO get Account data 
		Account account1 = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.splat(1)));
		Account account2 = DAOimpl.instance().getAccount(Driver.connection, Integer.parseInt(ctx.splat(2)));
		
		//TODO read body and transfer
		Account.transfer(account1, account2, 0);
		
		DAOimpl.instance().updateAccount(Driver.connection, account1);
		DAOimpl.instance().updateAccount(Driver.connection, account2);

		ctx.json(account1);
	};

}
