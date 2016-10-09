package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface IInstituteDao {
	/**
     * 查询学院信息
     */
	public ResultSet find()throws SQLException;
}