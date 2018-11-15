package com.cg.bms.service;

import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;

public interface IAccountService {

	public boolean validateAccount(Account account) throws BMSException;

	public boolean createAccount(Account account)throws BMSException;
	
	public int getMaxId() throws BMSException;
}
