package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.QType;
import me.zxks.entity.Single_choose;

public interface ISingle_chooseDao {
	/**
     * 添加单项选择题记录
     */
	public int add(Single_choose choose) throws SQLException;
	/**
     * 显示单项选择题记录
     */
	public ResultSet Show(Single_choose choose) throws SQLException;
	/**
     * 添加试卷时  选题    n代表试卷中单项选择的数目   叶俊浩
     */
	public ResultSet select(QType qtype,int n);
	/**
     * 添加试卷时  选题    选中的题目update   paper_name   叶俊浩
     */
	public int updatePaper_name(String paper_name,int n);
}
