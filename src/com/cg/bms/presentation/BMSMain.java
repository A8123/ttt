package com.cg.bms.presentation;

import java.security.Provider.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;
import com.cg.bms.service.AccountServiceImpl;
import com.cg.bms.service.IAccountService;

public class BMSMain {

	static Logger logger = Logger.getLogger(BMSMain.class);
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		IAccountService service = new AccountServiceImpl();
		
		
		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("log4j configured..");
		
		
		

		
		System.out.println("____________________________________");
		System.out.println("------Welcome to Bangalore Bank------- ");
		System.out.println("____________________________________");

		System.out.println("1. create account");
		System.out.println("2. get account info ");
		System.out.println("3. display all accounts");
		System.out.println("4. Exit");

		System.out.println("select your choice ");
		int option = 0;
		
		try {
			option = scanner.nextInt();
			logger.info("selected option is" +option);			
		} catch (InputMismatchException e) {
			System.err.println("Enter digits only [1-4]");
			logger.error("error");
			System.exit(0);
		}

		switch (option) {
		case 1:
				Account account = openAccount();
				logger.debug("data in the account class is" +account);
	
			try {
				boolean result= service.validateAccount(account);
				logger.debug("Validation of account is"+result );
				
				if(result){
					boolean resultFlag = service.createAccount(account); 
					if(resultFlag){
						int id = service.getMaxId();
						
						System.out.println("Account created with id" +id);
					}
					else {
						System.err.println("account not created");
					}
					
				}
				
			} catch (BMSException e) {
				System.err.println(e.getMessage());
				
			}
			break;

		case 2:

			break;
		case 3:

			break;
		case 4:

			break;

		default:
			System.out.println(" invalid input");
			logger.info("invalid option selected");
			break;

		}
	}
	
	public static Account openAccount(){
		
		scanner.nextLine();
		System.out.println("Enter Account type to create  ");
		String accountType = scanner.nextLine();
		
		System.out.println("Enter customer name");
		String customerName = scanner.nextLine();
		
		System.out.println("Enter min deposit");
		double amount = 0;
		try{
		amount=scanner.nextDouble();
		}catch (InputMismatchException e){
			System.err.println("Given input is not correct, try again");
			logger.error("entered amount is not correct");
			System.exit(0);
		}
		
		
		Date date = new Date();
		
	/*	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = format.format(date);
		
		try {
			Date date3 = format.parse(newDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}*/
		
		Account account = new Account(accountType, customerName, amount, date);
		logger.info("account object created" +amount);
		return account;
	}

}

