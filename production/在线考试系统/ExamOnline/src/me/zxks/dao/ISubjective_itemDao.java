package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.QType;
import me.zxks.entity.Subjective_item;

public interface ISubjective_itemDao {
	/**
     * 添加主观题记录
     */
	public int add(Subjective_item item) throws SQLException;
	/**
     * 显示主观题记录
     */
	public ResultSet Show(Subjective_item item) throws SQLException;
	/**
     * 添加试卷时  选题    n代表试卷中主观题的数目  叶俊浩
     */
	public ResultSet select(QType qtype,int n);
	/**
     * 添加试卷时  选题    选中的题目update   paper_name 叶俊浩
     */
	public int updatePaper_name(String paper_name,int n);
}
