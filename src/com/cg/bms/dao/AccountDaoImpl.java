package com.cg.bms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.bms.exception.BMSException;
import com.cg.bms.model.Account;
import com.cg.bms.utility.JdbcUtility;

public class AccountDaoImpl implements IAccountDao {

	static Logger logger = Logger.getLogger(AccountDaoImpl.class);
	Connection connection = null;
	PreparedStatement statement = null;

	@Override
	public boolean createAccount(Account account) throws BMSException {

		boolean flag = false;
		connection = JdbcUtility.getConnection();
		logger.info("connection established");

		try {
			statement = connection.prepareStatement(QueryConstants.insertAccount);
			logger.info("statement created");

			statement.setString(1, account.getType());
			statement.setString(2, account.getCustomerName());
			statement.setDouble(3, account.getBalance());

			java.util.Date date1 = account.getDate();

			Date date = new Date(date1.getTime());

			statement.setDate(4, date);

			int result = statement.executeUpdate();

			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			logger.error("Statement object not created");
			throw new BMSException("statement not created with some problem");
		}finally {
			JdbcUtility.closeConnection();
		}

		return flag;
	}

	@Override
	public int getMaxId() throws BMSException {
		int id = 0 ;
		ResultSet resultSet = null;
		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QueryConstants.selectMaxId);
			resultSet = statement.executeQuery();

			resultSet.next();
			id= resultSet.getInt(1);
		} catch (SQLException e) {

			throw new BMSException("Unable to create the statement");

		} finally{
			
			try{
			resultSet.close();
			}catch (SQLException e) {
				throw new BMSException("problem while closing resultset");
			}
			
		}
		try{
			statement.close();
		}
		catch(SQLException e) {
			throw new BMSException("problem while closing statement");
		}
		
		JdbcUtility.closeConnection();

		return id;
	}

}
