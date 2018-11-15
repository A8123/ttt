package com.cg.bms.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bms.dao.AccountDaoImpl;
import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;

public class AccountDaoImplTest {
	AccountDaoImpl accountDao = null;

	@Before
	public void setUp() throws Exception {

		accountDao = new AccountDaoImpl();

	}

	@After
	public void tearDown() throws Exception {
		accountDao = null;

	}

	@Test
	public void testcreateAccount() throws SQLException {

		Account account = new Account("salaried", "shanti", 23000, new Date(
				2018, 10, 13));

		boolean result = false;
		try {
			
			
			result = accountDao.createAccount(account);
		} catch (BMSException e) {

			e.printStackTrace();
		}
		assertEquals(true, result);
	}


}
