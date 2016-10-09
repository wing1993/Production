package me.zxks.dao;

import java.sql.SQLException;

public interface IScoreDao {
	public int addScore(String stuNo, String subjectId, int score,
			String test_time, int choose_score, int tfng_score,
			int comple_score, String paper_name) throws SQLException;
}
