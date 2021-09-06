package com.banking.Javalin;

import java.sql.Connection;

import com.banking.Database.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {
	static Connection connection = ConnectionFactory.getConnection();
	
	public static void main(String[] args) {
		

		Javalin app = Javalin.create().start(7000);
		app.get("/hello", ctx -> ctx.html("Hello, Javalin!"));

		app.get("/clients", GetController.fetchAllClients);

		app.get("/clients/*", GetController.fetchClient);

		app.get("/clients/*/accounts", GetController.fetchAllAccounts);

		app.get("/clients/*/accounts/*", GetController.fetchAccount);

		app.post("/clients", PostController.NewClient);

		app.post("/clients/*/accounts", PostController.NewAccount);

		app.put("/clients/*", PutController.UpdateClient);

		app.put("/clients/*/accounts/*", PutController.UpdateAccount);

		app.patch("/clients/*/accounts/*", PatchController.DepOrWith);

		app.patch("/clients/*/accounts/*/transfer/*", PatchController.Transfer);

		app.delete("/clients/*", DeleteController.DeleteClient);

		app.delete("/clients/*/accounts/*", DeleteController.DeleteAccount);

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