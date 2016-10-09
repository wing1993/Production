package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IClass_instituteDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.Student;

public class Class_instituteDaoImpl extends DBOper implements
		IClass_instituteDao {
	/**
	 * 根据学号查找学生所在班级和学院
	 */
	public ResultSet class_institute(Student student) throws SQLException {
		String sql = "select instituteName,className,grade from institute,"
				+ "class,student where student.classNo=class.classNo "
				+ "and class.instituteId=institute.instituteId and student.stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		return pt.executeQuery();
	}
}
