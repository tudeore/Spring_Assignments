package com.moneymoney.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.moneymoney.account.SavingsAccount;

public class SavingsAccountRowMappper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		SavingsAccount savingsAccount = new SavingsAccount(resultSet.getInt("accountId"),
				resultSet.getString("account_hn"), resultSet.getDouble(3), resultSet.getBoolean("isSalary"));
		return savingsAccount;
	}
}