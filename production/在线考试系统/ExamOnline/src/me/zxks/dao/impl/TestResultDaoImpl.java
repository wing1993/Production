package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ITestResultDao;
import me.zxks.dbOperate.DBOper;

public class TestResultDaoImpl extends DBOper implements ITestResultDao {

	@Override
	/**
	 * 查找班级信息
	 */
	public ResultSet className(String subjectId) throws SQLException {
		String sql = "select DISTINCT grade,className from class,student "
				+ "where student.classNo=class.classNo and stuNo "
				+ "in(select stuNo from score where subjectId=?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, subjectId);
		return pt.executeQuery();
	}

	/**
	 * 查找试卷结果信息
	 */
	public ResultSet paperResult(String grade, String className)
			throws SQLException {
		String sql = "select DISTINCT paper_name,test_time from score where stuNo in"
				+ "(select stuNo from student where classNo in(select classNo "
				+ "from class where grade=? and className=?))";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, grade);
		pt.setString(2, className);
		return pt.executeQuery();
	}

	public ResultSet test_result(String paper_name, String grade,
			String className, String sql) throws SQLException {
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, paper_name);
		pt.setString(2, grade);
		pt.setString(3, className);
		return pt.executeQuery();
	}

	/**
	 * 查找考试人数、最高分、最低分和班级平均分
	 */
	public ResultSet testNum(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select avg(score),MAX(score),MIN(score),count(stuNo) from score where paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 低于60分
	 */
	public ResultSet Under_six(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score<60 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 
	 * 60-70
	 */
	public ResultSet Six(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score>=60 and score<=69 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 
	 * 70-79
	 */
	public ResultSet Seven(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score>=70 and score<=79 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 
	 * 80-89
	 */
	public ResultSet Eight(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score>=80 and score<=89 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 
	 * 90-99
	 */
	public ResultSet Nine(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score>=90 and score<=99 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 
	 * 大于等于100
	 */
	public ResultSet Ten(String paper_name, String grade, String className)
			throws SQLException {
		String sql = "select count(stuNo) from score where score>=100 and paper_name=? "
				+ "and stuNo in(select stuNo from student where classNo in(select classNo from "
				+ "class where grade=? and className=?))";
		return test_result(paper_name, grade, className, sql);
	}

	/**
	 * 主观题评分查找班级信息
	 */
	public ResultSet findClass(String paper_name) throws SQLException {
		String sql = "select DISTINCT grade,className from class,student where "
				+ "class.classNo=student.classNo and student.stuNo IN "
				+ "(select stuNo from score where paper_name=?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, paper_name);
		return pt.executeQuery();
	}

	/**
	 * 查找学生主观题的答案和内容
	 */
	public ResultSet find_itemInfo(String grade, String className,
			String paper_name) throws SQLException {
		String sql = "select DISTINCT a.stuNo,stuName,test_time "
				+ "from item_score a,student b,class where a.stuNo=b.stuNo and class.classNo=b.classNo "
				+ "and className=? and grade=? and a.paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, className);
		pt.setString(2, grade);
		pt.setString(3, paper_name);
		return pt.executeQuery();
	}

	/**
	 * 
	 * 查找主观题内容和答案
	 */
	public ResultSet find_itemInfo(String stuNo, String paper_name)
			throws SQLException {
		String sql = "select item_answerId,item_content,item_answer,full_score,(select item_answer "
				+ "from subjective_item where item_content=a.item_content) full_answer "
				+ "from item_score a where stuNo=? and paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, stuNo);
		pt.setString(2, paper_name);
		return pt.executeQuery();
	}

	/**
	 * 将主观题的分数更新到数据库
	 */
	public int Update_item_score(int item_id, int getScore) throws Exception {
		String sql = "update item_score set item_score=? where item_answerId=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setInt(1, getScore);
		pt.setInt(2, item_id);
		return this.executeUpdate(sql, pt);

	}

	/**
	 * 更新成绩表
	 */
	public int Update_score(String stuNo, int q_item) throws Exception {
		String sql = "update score set q_item=?,score=q_choose+q_completion+q_item+q_tfng where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setInt(1, q_item);
		pt.setString(2, stuNo);
		return this.executeUpdate(sql, pt);
	}
}
