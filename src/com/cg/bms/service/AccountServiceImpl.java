package com.cg.bms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.bms.dao.AccountDaoImpl;
import com.cg.bms.dao.IAccountDao;
import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;

public class AccountServiceImpl implements IAccountService {
	
	static Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	
	IAccountDao accountDao = new AccountDaoImpl();
	
	/*
	 *methodName	 - createAccount
	 *arguments		 -  Account Object
	 *return type	 - boolean
	 *Author		 -  Capgemini
	 *creationDate	 - 12/10/2018
	 * this method is used to insert he account data into database
	 */
	
	
	@Override
	public boolean createAccount(Account account) throws BMSException {
		
		logger.info("in account service class");
		
		return accountDao.createAccount(account);
	}
	

	@Override
	public boolean validateAccount(Account account) throws BMSException {
		boolean flag=false;
		
		logger.info("in validation account method");
		List<String> list = new ArrayList<String>();
		
		
		if(!isNameValid(account.getCustomerName())){
			list.add("\nCustomer name should contain min 5 char and max 20 char\n" );
		}
		
		if(!isBalanceValid(account.getBalance())){
			list.add("\nAccount balance should be greater than 1000\n");
		}
		
		if(!isDateValid(account.getDate())){
			list.add("\nDate should be in the format yyyy-mm-dd\n");
		}
		
		if(!list.isEmpty()){
			logger.debug("list is not empty");
			throw new BMSException(list+ "");
		} else
			{
			logger.debug("list is empty");
			flag = true;
			}
		logger.debug("result from account validation method is" +flag );
		return flag;
	}

	
	public boolean isNameValid(String name){
		
		logger.info("in name validation method");
		String nameRegEx = "[A-Za-z]{5,20}";
		
	Pattern pattern = 	Pattern.compile(nameRegEx);
	Matcher matcher = pattern.matcher(name);
	logger.debug("name validation"+matcher.matches());
	return matcher.matches();
	}
	
	public boolean isBalanceValid(double balance){
		
		logger.info("in balance validation metod");
		boolean balanceflag = false;
		
		if(balance>=1000){
			balanceflag=true;
		}
		logger.debug("balance validation"+balanceflag);
		return balanceflag;
	}
	
	public boolean isDateValid(Date date){
		
		logger.info("in date validation method");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String newDate = format.format(date);
		

		
		String dateRegEx= "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern pattern = Pattern.compile(dateRegEx);
		Matcher matcher = pattern.matcher(newDate);
		logger.debug("date validation"+matcher.matches());
		return matcher.matches();
		
	}


	
	public int getMaxId() throws BMSException {
		return accountDao.getMaxId();
		
	}	
}
