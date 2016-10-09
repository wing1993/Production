package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.QType;

public interface IQTypeDao {
	// 查询试卷名称
	public ResultSet queryPaper(QType qType) throws SQLException;
	/**
	 * 根据tNo查找试卷信息  yjh
	 */
	public ResultSet showPaper(QType qtype);
}
