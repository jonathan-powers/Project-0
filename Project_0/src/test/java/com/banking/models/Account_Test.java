package com.banking.models;

import org.junit.runner.RunWith;
import org.junit.*;
//import org.mockito.Mock;
//import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Account_Test {
	static Account account1 = new Account(1,"CHECKING",1,100);
	static Account account2 = new Account(2,"SAVINGS",1,100);
	
	@Before
	public void setUp() {
		account1 = new Account(1,"CHECKING",1,100);
		account2 = new Account(2,"SAVINGS",1,100);
	}
	
	@BeforeClass
	public static void reset() {
		account1.setBalance(100);
		account2.setBalance(100);
	}
	
	@Test
	public void test_Deposit() {
		double result = 150;
		Account.deposit(account1, 50);
		
		Assert.assertEquals(result, account1.getBalance(), 0.1);
	}
	
	@Test
	public void test_Withdraw() {
		double result = 50;
		Account.withdraw(account1, 50);
		
		Assert.assertEquals(result, account1.getBalance(), 0.1);		
	}
	
	@Test
	public void test_Transfer() {
		double result1 = 50;
		double result2 = 150;
		Account.transfer(account1, account2, 50);
		
		Assert.assertEquals(result1, account1.getBalance(), 0.1);
		Assert.assertEquals(result2, account2.getBalance(), 0.1);
		
	}
}
