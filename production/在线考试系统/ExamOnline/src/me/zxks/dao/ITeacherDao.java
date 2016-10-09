package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Teacher;

public interface ITeacherDao {
	
	/**
     * 添加教师记录
     *//*
	public int addTeadent(Teacher teacher) throws SQLException;
	
	/**
     * 检查用户名是否重复
     */
	public boolean check(Teacher teacher)throws Exception;
	
	/**
     * 验证登录信息
     */
	public ResultSet teacherLogin(Teacher teacher)throws SQLException;
	
	/**
     * 修改教师资料
     *//*
	public int editTeaInfo(Teacher teacher)throws SQLException;*/
}
