package com.banking.Javalin;

import java.sql.Connection;

import com.banking.Database.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {
	static Connection connection = ConnectionFactory.getConnection();
	
	public static void main(String[] args) {
		
		//creates local host to receive requests from Postman
		Javalin app = Javalin.create().start(7000);
		
		app.get("/hello", ctx -> ctx.html("Hello, Javalin!"));

		app.get("/clients", GetController.fetchAllClients);

		app.get("/clients/:client", GetController.fetchClient);

		app.get("/clients/:client/accounts", GetController.fetchAllAccounts);

		app.get("/clients/:client/accounts/:account", GetController.fetchAccount);

		app.post("/clients", PostController.NewClient);

		app.post("/clients/:client/accounts", PostController.NewAccount);

		app.put("/clients/:client", PutController.UpdateClient);

		app.put("/clients/:client/accounts/:account", PutController.UpdateAccount);

		app.patch("/clients/:client/accounts/:account", PatchController.DepOrWith);

		app.patch("/clients/:client/accounts/:account/transfer/:transfer", PatchController.Transfer);

		app.delete("/clients/:client", DeleteController.DeleteClient);

		app.delete("/clients/:client/accounts/:account", DeleteController.DeleteAccount);

		app.get("/exception", ctx -> {
			throw new Exception("test");
		});

		app.exception(Exception.class, (e, ctx) -> {
			System.out.println("exception: " + e + " - " + ctx);
			ctx.status(500);
		});

		app.error(500, "html", ctx -> {
			ctx.result("500 Internal Server Error!!!");
		});

	}
}