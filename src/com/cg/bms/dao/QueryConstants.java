package com.cg.bms.dao;

public interface QueryConstants {

	public static final String insertAccount = "insert into account_master1 values(account_sequence.nextval, ?,?,?,?)";
	
	public static final String selectMaxId = "select max(account_no) from account_master1";
	
}
