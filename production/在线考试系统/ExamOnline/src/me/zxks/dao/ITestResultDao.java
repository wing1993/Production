package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ITestResultDao {
	public ResultSet className(String subjectId) throws SQLException;
}
