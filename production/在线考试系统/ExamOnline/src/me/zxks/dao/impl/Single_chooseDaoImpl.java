package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ISingle_chooseDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.QType;
import me.zxks.entity.Single_choose;

public class Single_chooseDaoImpl extends DBOper implements ISingle_chooseDao{

	@Override
	//添加单项选择题记录
	public int add(Single_choose choose) throws SQLException {
		String sql = "insert into single_choose(choice_content,choiceA,choiceB," +
				"choiceC,choiceD,choice_answer,subjectId,chapter) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,choose.getChoice_content());
		pt.setString(2,choose.getChoiceA());
		pt.setString(3,choose.getChoiceB());
		pt.setString(4,choose.getChoiceC());
		pt.setString(5,choose.getChoiceD());
		pt.setString(6,choose.getChoice_answer());
		pt.setString(7,choose.getSubjectId());
		pt.setString(8,choose.getChapter());
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	@Override
	//根据科目Id查询对应的单项选择题
	public ResultSet Show(Single_choose choose) throws SQLException {
		String sql = "select * from single_choose where subjectId=? ";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, choose.getSubjectId()); 
		return this.executeQuery(sql, pt);
	}
	@Override  //叶俊浩
	//查询出特定行数的记录作为新出试卷的试卷内容
	public ResultSet select(QType qtype, int n) {
		String sql = "select choice_id from single_choose where chapter=? and " +
				"subjectId=? and paper_name is NULL or paper_name='' order by rand() limit "+ n;
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, qtype.getChapter());
			pt.setString(2, qtype.getSubjectId());
		} catch (SQLException e) {
			System.out.println("查询出错！异常如下：");
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}
	@Override  //叶俊浩
	//将查询出来的 特定行数的记录的paper_name改为新出的试卷名
	public int updatePaper_name(String paper_name,int n) {
		String sql = "update single_choose set paper_name=? where choice_id=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, paper_name);
			pt.setInt(2,n);
		} catch (SQLException e) {
			System.out.println("更新出错！异常如下：");
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	//删除试题记录
	public int delete(Single_choose choose) {
		String sql = "delete from single_choose where choice_content=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, choose.getChoice_content());
		} catch (SQLException e) {
			System.out.println("删除出错！异常如下：");
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	
	
}
