package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISubjectDao {
	/**
     * 查询科目信息
     */
	public ResultSet find()throws SQLException;
	/**
     * 根据科目名称返回科目Id
     */
	public String find(String subjectName)throws SQLException;
}
