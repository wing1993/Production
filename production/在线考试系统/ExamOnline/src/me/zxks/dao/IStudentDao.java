package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Student;

public interface IStudentDao {
	
	/**
     * 添加学生记录
     */
	public int insert(Student student) throws SQLException;
	
	/**
     * 检查用户名是否重复
     */
	public boolean check(Student student)throws SQLException;
	
	/**
     * 验证登录信息
     */
	public ResultSet studentLogin(Student student)throws SQLException;
	
	/**
     * 修改学生资料
     *//*
	public int editStuInfo(Student student)throws SQLException;*/
}