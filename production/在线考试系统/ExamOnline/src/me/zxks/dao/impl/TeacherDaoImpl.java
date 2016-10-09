package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ITeacherDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.Teacher;

public class TeacherDaoImpl extends DBOper implements ITeacherDao{
	/**
	 * 插入教师信息
	 * @param student
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public boolean insert(Teacher teacher) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.getConn();
		String sql = "insert into teacher(tNo,tName,tGender,tRole,instituteId,tPwd) values(?,?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, teacher.gettNo());
		pt.setString(2, teacher.gettName());
		pt.setInt(3, teacher.gettGender());
		pt.setString(4, teacher.gettRole());
		pt.setString(5,teacher.getInstituteId());
		pt.setString(6, teacher.gettNo());
		boolean result = (this.executeUpdate(sql, pt)==1);
		this.closeAll();
		return result;
	}
    // 检测用户名是否重复
	public boolean  check(Teacher teacher) throws Exception {
		this.getConn();
		String sql = "select * from teacher where tNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, teacher.gettNo());
		ResultSet rs= pt.executeQuery();
		if(rs.next()){
			return false;
		}else{
			return true;
		}
		
	}
	// 验证登录信息
	public ResultSet teacherLogin(Teacher teacher) throws SQLException {
		String sql = "select * from teacher where tNo=? and tPwd=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, teacher.gettNo());
		pt.setString(2, teacher.gettPwd());

		return this.executeQuery(sql, pt);

	}

	public ResultSet showByteaN(String tNo, String tName) throws SQLException {
		if(!tNo.equals("")){
			String sql="select tNo,tName,tGender,tRole,instituteName from institute,teacher" +
					" where institute.instituteId=teacher.instituteId and tNo=?";
			PreparedStatement pt = this.getStatement(sql);
			pt.setString(1, tNo);
			return this.executeQuery(sql, pt);
		}
		else{
			String sql="select tNo,tName,tGender,tRole,instituteName from institute,teacher" +
					" where institute.instituteId=teacher.instituteId and tName=?";
			PreparedStatement pt = this.getStatement(sql);
			pt.setString(1, tName);
			return this.executeQuery(sql, pt);
		}
	}

	
}
