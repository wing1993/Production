package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IPaperDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.QType;

public class PaperDaoImpl extends DBOper implements IPaperDao {
	@Override
	public ResultSet choose(QType qType) throws SQLException {
		String sql = "select * from single_choose where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, qType.getPaper_name());
		return pt.executeQuery();
	}

	@Override
	public ResultSet comple(QType qType) throws SQLException {
		String sql = "select * from completion where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, qType.getPaper_name());
		return pt.executeQuery();
	}

	@Override
	public ResultSet subjective(QType qType) throws SQLException {
		String sql = "select * from subjective_item where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, qType.getPaper_name());
		return pt.executeQuery();
	}

	@Override
	public ResultSet tfng(QType qType) throws SQLException {
		String sql = "select * from tfng where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, qType.getPaper_name());
		return pt.executeQuery();
	}

	// 查找试卷有哪些题型,数目，分值
	public ResultSet find_type(String paper_name) throws SQLException {
		String sql = "select qType,qNum,qScore from question_type where paper_name=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, paper_name);
		return pt.executeQuery();
	}
	//查找试卷所有的试卷名
	public ResultSet find_papername() throws SQLException {
		try {
			this.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select DISTINCT paper_name from question_type";
		PreparedStatement pt = this.getStatement(sql);
		return pt.executeQuery();
	}

}
