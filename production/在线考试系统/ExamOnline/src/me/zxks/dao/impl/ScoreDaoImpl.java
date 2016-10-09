package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IScoreDao;
import me.zxks.dbOperate.DBOper;

public class ScoreDaoImpl extends DBOper implements IScoreDao {

	@Override
	/**
	 * 统计分数
	 */
	public int addScore(String stuNo, String subjectId, int score,
			String test_time, int choose_score, int tfng_score,
			int comple_score, String paper_name) throws SQLException {
		String sql = "insert into score(stuNo,subjectId,score,test_time,q_choose,q_tfng,q_completion,paper_name) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		System.out.println(stuNo);
		pt.setString(1, stuNo);
		pt.setString(2, subjectId);
		pt.setInt(3, score);
		pt.setString(4, test_time);
		pt.setInt(5, choose_score);
		pt.setInt(6, tfng_score);
		pt.setInt(7, comple_score);
		pt.setString(8, paper_name);
		return this.executeUpdate(sql, pt);
	}

	/**
	 * 找出试卷名称和分数
	 */
	public ResultSet paper_score(String stuNo, String subjectId)
			throws SQLException {
		String sql = "select paper_name,score from score where stuNo=? and subjectId=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, stuNo);
		pt.setString(2, subjectId);
		return pt.executeQuery();
	}

	/**
	 * 计算平均分
	 */
	public ResultSet avgSet(String paper_name) throws SQLException {
		String sql = "select avg(score) from score where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, paper_name);
		return pt.executeQuery();
	}

	/**
	 * 计算班级排名
	 */
	public ResultSet myRank(String stuNo, String paper_name)
			throws SQLException {
		String sql = "select rank from (select stuNo,paper_name,(select COUNT(paper_name)+1 from "
				+ "score where score > s.score) rank from score s where paper_name=? order by rank) a "
				+ "where stuNo=? and paper_name=?";// 查找视图中的内容
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, paper_name);
		pt.setString(2, stuNo);
		pt.setString(3, paper_name);
		return pt.executeQuery();
	}

	/**
	 * 将主观题题目内容和学生答案存到item_score表中
	 */
	public int save_item(String stuNo, String paper_name, String item_content,
			String item_ans, String test_time, int item_score)
			throws SQLException {
		String sql = "insert into item_score(stuNo,paper_name,item_content,item_answer,test_time,full_score) "
				+ "values(?,?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, stuNo);
		pt.setString(2, paper_name);
		pt.setString(3, item_content);
		pt.setString(4, item_ans);
		pt.setString(5, test_time);
		pt.setInt(6, item_score);
		return this.executeUpdate(sql, pt);
	}
	/**
	 * 将学生考试成绩作废   分数置零
	 * @param paper_name
	 * @param stuNo
	 * @throws SQLException 
	 */
	public int updateStuScore(String paper_name, String stuNo) throws SQLException {
		String sql = "update score set score=0 where stuNo=? and paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, stuNo);
		pt.setString(2, paper_name);
		return this.executeUpdate(sql, pt);
		
	}
}
