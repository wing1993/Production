package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.zxks.dao.ICompletionDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.Completion;
import me.zxks.entity.QType;

public class CompletionDaoImpl extends DBOper implements ICompletionDao{
	public ArrayList<Completion> getAll(String subjectId){
		String sql="select * from completion where subjectId=?";
			String params[] = {subjectId};
			ArrayList<Completion> list = new ArrayList<Completion>();
			ResultSet rs = this.executeQuery(sql, params);
			try {
				while(rs.next()){
					Completion com=new Completion();
					com.setCompletion_content(rs.getString(2));
					com.setCompletion_answer(rs.getString(3));
					com.setSubjectId(rs.getString(4));
					com.setChapter(rs.getString(5));
					com.setPaper_name(rs.getString(6));
					list.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}

	@Override
	//添加填空题记录
	public int add(Completion com) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.getConn();
		String sql="insert into completion(completion_content,completion_answer," +
				"subjectId,chapter) values(?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,com.getCompletion_content());
		pt.setString(2,com.getCompletion_answer());
		pt.setString(3,com.getSubjectId());
		pt.setString(4,com.getChapter());
		
		int result = this.executeUpdate(sql, pt);
		this.closeAll();
		return result;
	}

	@Override
	//查询填空题记录
	public ResultSet Show(Completion com) throws SQLException {
		String sql = "select * from completion where subjectId=? ";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, com.getSubjectId()); 
		return this.executeQuery(sql, pt);
	}

	@Override  //叶俊浩
	public ResultSet select(QType qtype, int n) {
		String sql = "select completion_id from completion where chapter=? and " +
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

	@Override  // 叶俊浩
	public int updatePaper_name(String paper_name, int n) {
		String sql = "update completion set paper_name=? where completion_id=?";
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

	public void delete(Completion com) {
		String sql = "delete from completion where completion_content=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, com.getCompletion_content());
		} catch (SQLException e) {
			System.out.println("删除出错！异常如下：");
			e.printStackTrace();
		}
		 this.executeUpdate(sql, pt);
	}

	public int  getId(String content) throws Exception {
		this.getConn();
		int completion_id=0;
		this.getConn();
		String sql="select completion_id from completion where completion_content=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, content);
		ResultSet rs=this.executeQuery(sql, pt);
		if(rs.next()){
			completion_id=Integer.parseInt(rs.getString(1));
		}
		this.closeAll();
		return completion_id;
	}

	public int  update(int qTypeId, String content, String answer) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		this.getConn();
		String sql = "update completion set completion_content=?,completion_answer=? where completion_id=?";
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
