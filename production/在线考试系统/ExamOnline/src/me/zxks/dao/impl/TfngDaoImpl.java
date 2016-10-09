package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ITfngDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.QType;
import me.zxks.entity.Tfng;

public class TfngDaoImpl extends DBOper implements ITfngDao{

	@Override
	public int add(Tfng tfng) throws SQLException {
		String sql="insert into tfng(tfng_content,tfng_answer," +
				"subjectId,chapter) values(?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,tfng.getTfng_content());
		pt.setInt(2,tfng.getTfng_answer());
		pt.setString(3,tfng.getSubjectId());
		pt.setString(4,tfng.getChapter());
		int result = this.executeUpdate(sql, pt);
		return result;
	}

	@Override
	public ResultSet Show(Tfng tfng) throws SQLException {
		String sql = "select * from tfng where subjectId=? ";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, tfng.getSubjectId()); 
		return this.executeQuery(sql, pt);
	}

	@Override
	public ResultSet select(QType qtype, int n) {
		String sql = "select tfng_id from tfng where chapter=? and " +
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

	@Override
	public int updatePaper_name(String paper_name, int n) {
		String sql = "update tfng set paper_name=? where tfng_id=?";
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

	public void delete(Tfng tfng) {
		String sql = "delete from tfng where tfng_content=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, tfng.getTfng_content());
		} catch (SQLException e) {
			System.out.println("删除出错！异常如下：");
			e.printStackTrace();
		}
		 this.executeUpdate(sql, pt);
	}

	public int  getId(String content) throws Exception {
		int tfng_id=0;
		this.getConn();
		String sql="select tfng_id from tfng where tfng_content=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, content);
		ResultSet rs=this.executeQuery(sql, pt);
		if(rs.next()){
			tfng_id=Integer.parseInt(rs.getString(1));
		}
		return tfng_id;
	}

	public int  update(int qTypeId, String content, int answer) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		this.getConn();
		String sql = "update tfng set tfng_content=?,tfng_answer=? where tfng_id=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, content);
			pt.setInt(2, answer);
			pt.setInt(3,qTypeId);
		} catch (SQLException e) {
			System.out.println("更新出错！异常如下：");
			e.printStackTrace();
		}
		
		int result = this.executeUpdate(sql, pt);
		this.closeAll();
		return result;
	}
	
}
