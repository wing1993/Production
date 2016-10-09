package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Student;

public interface IClass_instituteDao {
	/**
	 * 
	 * 查找学生所在的班级学院
	 */
	public ResultSet class_institute(Student student) throws SQLException;
}
