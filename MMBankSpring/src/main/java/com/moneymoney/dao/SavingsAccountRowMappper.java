package com.moneymoney.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.moneymoney.account.SavingsAccount;

public class SavingsAccountRowMappper implements RowMapper<SavingsAccount> {

	/*
	 * public Student mapRow1(ResultSet rs, int rowNum) throws SQLException {
	 * 
	 * Student student = new Student(); student.setId(rs.getInt("id"));
	 * student.setName(rs.getString("name")); student.setAge(rs.getInt("age"));
	 * return student;
	 */


	@Override
	public SavingsAccount mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		SavingsAccount savingsAccount = new SavingsAccount(resultSet.getInt("accountId"), resultSet.getString("account_hn"), resultSet.getDouble(3), resultSet.getBoolean("isSalary"));
		return savingsAccount;
	}
}