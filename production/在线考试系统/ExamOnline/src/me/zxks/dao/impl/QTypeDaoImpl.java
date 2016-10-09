package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IQTypeDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.QType;

public class QTypeDaoImpl extends DBOper implements IQTypeDao {

	@Override
	// 查找试卷信息
	public ResultSet queryPaper(QType qType) throws SQLException {
		String sql = "select DISTINCT qTime,qTest_time,paper_name,tName from question_type,teacher "
				+ "where question_type.tNo=teacher.tNo and subjectId=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, qType.getSubjectId());
		return pt.executeQuery();
	}

	/**
	 * 
	 * 查找成绩表中是否有学生的成绩，返回值为true则说明改试卷已考
	 */
	public boolean queryState(String stuNo, String subjectid)
			throws SQLException {
		String sql = "select * from score where stuNo=? and subjectId=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, stuNo);
		pt.setString(2, subjectid);
		ResultSet rs = pt.executeQuery();
		if (rs.next())
			return true;
		else
			return false;
	}
	/**
	 * 添加试卷记录//叶俊浩
	 */
	public int add(QType qtype) {
		String sql="insert into question_type(subjectId,chapter,qType,qNum," +
				"qScore,qTest_time,qTime,paper_name,tNo) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1,qtype.getSubjectId());
			pt.setString(2,qtype.getChapter());
			pt.setString(3,qtype.getqType());
			pt.setInt(4,qtype.getqNum());
			pt.setInt(5,qtype.getqScore());
			pt.setInt(6,qtype.getqTime());
			pt.setString(7,qtype.getqTest_time());
			pt.setString(8,qtype.getPaper_name());
			pt.setString(9,qtype.gettNo() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	/**
	 * 根据tNo查找试卷信息  yjh
	 */
	public ResultSet showPaper(QType qtype){
		String sql="select  DISTINCT paper_name,qTime,qTest_time from question_type where tNo=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, qtype.gettNo());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	/**
	 * 根据paper_name来修改试卷内容question_type  yjh
	 */
	public int changePaper(QType qtype) {
		String sql="update question_type set qTime=?,qTest_time=? where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		System.out.println(qtype.getqTest_time()+"   "+qtype.getqTime()+"  "+qtype.getPaper_name());
		try {
			pt.setString(1,qtype.getqTest_time());
			pt.setInt(2,qtype.getqTime());
			pt.setString(3,qtype.getPaper_name() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	
	/**
	 * 删除各个题型的paper_name  删除各题型  yjh
	 */
	public void delete(QType qtype,String sql) {
		PreparedStatement pt=this.getStatement(sql);
		try{
			pt.setString (1,qtype.getPaper_name());
			this.executeUpdate(sql, pt);
		}catch(SQLException e)
		{
			System.out.println("删除出错！异常如下：");
			e.printStackTrace();
		} 
	}
}
