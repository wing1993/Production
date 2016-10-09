package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.ISubjectDao;
import me.zxks.dbOperate.DBOper;

public class SubjectDaoImpl extends DBOper implements ISubjectDao{

	@Override
	public ResultSet find() throws SQLException {
		String sql = "select DISTINCT subjectName from subject";
		PreparedStatement pt = this.getStatement(sql);
		return pt.executeQuery();
	}
    //根据subjectName返回subjectId
	@Override
	public String find(String subjectName) throws SQLException {
		String sql = "select subjectId from subject where subjectName=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,subjectName);
		ResultSet rs=pt.executeQuery();
		String str = null;
		if(rs.next())  
			str=rs.getString(1);
		return str;
	}

}
