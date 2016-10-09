package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IClassDao;
import me.zxks.dbOperate.DBOper;

public class ClassDaoImpl extends DBOper implements IClassDao{

	@Override
	//查询班级信息
	public ResultSet find() throws SQLException {
		String sql = "select DISTINCT className from class";
		PreparedStatement pt = this.getStatement(sql);
		return pt.executeQuery();
	}
	@Override
	//根据学院名称查询对应的班级信息  班级名称+年级
	public ResultSet find(String instituteName) throws SQLException {
		String sql = "select DISTINCT className,grade from institute,class where instituteName=? "+
				"and institute.instituteId=class.instituteId";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,instituteName);
		return pt.executeQuery();
	}
	//根据班级名称获得classNo
	public String select(String className,String grade) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		this.getConn();
		System.out.println(className+"  "+grade);
		String sql="select DISTINCT student.classNo from student,class where student.classNo=class.classNo" +
				" and className=? and grade=?";
		PreparedStatement pt=this.getStatement(sql);
		pt.setString(1, className);
		pt.setString(2, grade);
		ResultSet rs=this.executeQuery(sql,pt);
		String str=null;
		if(rs.next()){str=rs.getString(1);}
		System.out.println(str+"  classNo");
		this.closeAll();
		return str;
	}
}
