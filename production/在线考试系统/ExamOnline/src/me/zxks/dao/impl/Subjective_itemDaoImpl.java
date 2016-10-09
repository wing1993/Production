package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ISubjective_itemDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.QType;
import me.zxks.entity.Subjective_item;

public class Subjective_itemDaoImpl extends DBOper implements ISubjective_itemDao{

	@Override
	public int add(Subjective_item item) throws SQLException {
		String sql="insert into subjective_item(item_content,item_answer," +
				"subjectId,chapter) values(?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,item.getItem_content());
		pt.setString(2,item.getItem_answer());
		pt.setString(3,item.getSubjectId());
		pt.setString(4,item.getChapter());
		int result = this.executeUpdate(sql, pt);
		return result;
	}

	@Override
	public ResultSet Show(Subjective_item item) throws SQLException {
		String sql = "select * from subjective_item where subjectId=? ";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, item.getSubjectId()); 
		return this.executeQuery(sql, pt);
		
	}

	@Override //叶俊浩
	public ResultSet select(QType qtype, int n) {
		String sql = "select item_id from subjective_item where chapter=? and " +
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
	public int updatePaper_name(String paper_name, int n) {
		String sql = "update subjective_item set paper_name=? where item_id=?";
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

	public void delete(Subjective_item item) {
		String sql = "delete from subjective_item where item_content=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, item.getItem_content());
		} catch (SQLException e) {
			System.out.println("删除出错！异常如下：");
			e.printStackTrace();
		}
		 this.executeUpdate(sql, pt);
	}
	public int  getId(String content) throws Exception {
		this.getConn();
		int item_id=0;
		this.getConn();
		String sql="select subjective_item from tfng where item_content=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, content);
		ResultSet rs=this.executeQuery(sql, pt);
		if(rs.next()){
			item_id=Integer.parseInt(rs.getString(1));
		}
		this.closeAll();
		return item_id;
	}

	public int  update(int qTypeId, String content, String answer) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		this.getConn();
		String sql = "update subjective_item set item_content=?,item_answer=? where item_id=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, content);
			pt.setString(2, answer);
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
