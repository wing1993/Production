package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface IClassDao {
	/**
     * 班级信息查询
     */
	public ResultSet find()throws SQLException;
	/**
     * 根据学院查询对应的班级信息
     */
	public ResultSet find(String instituteName) throws SQLException ;
}
