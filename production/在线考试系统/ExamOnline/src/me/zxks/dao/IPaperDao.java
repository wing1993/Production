package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.QType;

public interface IPaperDao {
	/**
	 * 
	 * 找出选择题表中所有指定试卷的题目
	 */
	public ResultSet choose(QType qType) throws SQLException;

	/**
	 * 找出填空题表中所有指定试卷的题目
	 */
	public ResultSet comple(QType qType) throws SQLException;

	/**
	 * 找出判断题表中所有指定试卷的题目
	 */
	public ResultSet tfng(QType qType) throws SQLException;

	/**
	 * 找出主观题表中所有指定试卷的题目
	 */
	public ResultSet subjective(QType qType) throws SQLException;
}
