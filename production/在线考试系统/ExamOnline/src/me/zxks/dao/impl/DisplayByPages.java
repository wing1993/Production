package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dbOperate.DBOper;

public class DisplayByPages extends DBOper{
	int recordNum=5;//试题  每页显示的数据数
	int recordNum1=8;//学生  每页显示的记录条数
	/**
	 * 分页选择試題记录
	 */
	public ResultSet selectByPage(String sql,String subjectId, int n) {
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, subjectId);
			pt.setInt(2, (n-1)*recordNum);
			pt.setInt(3, recordNum);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	/**
	 * 教师记录分页
	 */
	public ResultSet selectPageTeacher(String sql, String instituteName ,int n) {
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, instituteName);
			pt.setInt(2, (n-1)*recordNum1);
			pt.setInt(3, recordNum1);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	public ResultSet selectPageTeacher(String sql,int n) {
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setInt(1, (n-1)*recordNum1);
			pt.setInt(2, recordNum1);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	public ResultSet selectPagePaper(String sql, String paper_name,String classNo, int n) {
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1,classNo );
			pt.setString(2,paper_name);
			pt.setInt(3, (n-1)*recordNum1);
			pt.setInt(4, recordNum1);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	/**
	 * 计算题的总数算出页数
	 * @throws SQLException 
	 */
	public int getdbCount(String sql,String subjectId) throws SQLException{
		PreparedStatement pt=this.getStatement(sql);
		try {
			pt.setString(1, subjectId);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		ResultSet rs=this.executeQuery(sql, pt);
		int num=0;
		if(rs.next()){
			num=Integer.parseInt(rs.getString(1));
		}
		System.out.println("计算的页数为"+num/recordNum);
		if(num%recordNum==0)
		    return num/recordNum;
		else
			return num/recordNum+1;
	}
	public int getStuCount(String sql, String instituteName, String className,String grade) throws NumberFormatException, SQLException {
		PreparedStatement pt=this.getStatement(sql);
		try {
			pt.setString(1, instituteName);
			pt.setString(2,className);
			pt.setString(3, grade);
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		ResultSet rs=this.executeQuery(sql, pt);
		int num=0;
		if(rs.next()){
			num=Integer.parseInt(rs.getString(1));
		}
		System.out.println("计算的页数为"+num/recordNum1);
		if(num%recordNum1==0)
		    return num/recordNum1;
		else
			return num/recordNum1+1;
	}
	public int getTeaCount(String sql,String instituteName) throws NumberFormatException, SQLException {
		PreparedStatement pt=this.getStatement(sql);
		pt.setString(1,instituteName);
		ResultSet rs=this.executeQuery(sql, pt);
		int num=0;
		if(rs.next()){
			num=Integer.parseInt(rs.getString(1));
		}
		System.out.println("记录条数"+num);
		System.out.println("计算的页数为"+num/recordNum1);
		if(num%recordNum1==0)
		    return num/recordNum1;
		else
			return num/recordNum1+1;
	}
	
	
	
}
