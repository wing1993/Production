package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.QType;
import me.zxks.entity.Tfng;


public interface ITfngDao {
	/**
     * 添加判断题记录
     */
	public int add(Tfng tfng) throws SQLException;
	/**
     * 显示填空题记录
     */
	public ResultSet Show(Tfng tfng) throws SQLException;
	/**
     * 添加试卷时  选题    n代表试卷中判断题的数目  叶俊浩
     */
	public ResultSet select(QType qtype,int n);
	/**
     * 添加试卷时  选题    选中的题目update   paper_name 叶俊浩
     */
	public int updatePaper_name(String paper_name,int n);
}
