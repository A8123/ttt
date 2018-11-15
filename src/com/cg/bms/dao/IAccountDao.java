package com.cg.bms.dao;

import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;

public interface IAccountDao {

	boolean createAccount(Account account) throws BMSException;
	
	public int getMaxId() throws BMSException;

}
