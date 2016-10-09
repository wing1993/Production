package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Completion;
import me.zxks.entity.QType;

public interface ICompletionDao {
	/**
     * 添加填空题记录
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
     */
	public int add(Completion com) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException;
	/**
     * 显示填空题记录
     */
	public ResultSet Show(Completion com) throws SQLException;
	/**
     * 添加试卷时  选题    n代表试卷中填空题的数目  叶俊浩
     */
	public ResultSet select(QType qtype,int n);
	/**
     * 添加试卷时  选题    选中的题目update   paper_name 叶俊浩
     */
	public int updatePaper_name(String paper_name,int n);
}
