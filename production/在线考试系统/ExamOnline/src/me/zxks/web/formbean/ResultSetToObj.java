package me.zxks.web.formbean;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Single_choose;

/*
 * 将查询到的结果集每条记录放到Single_choose实体类
 */
public class ResultSetToObj {
	public Single_choose getChoosePrametes(ResultSet rs) throws SQLException{
		Single_choose choose=new Single_choose();
		choose.setChoice_content(rs.getString(2));
		choose.setChoiceA(rs.getString(2));
		choose.setChoiceB(rs.getString(3));
		choose.setChoiceC(rs.getString(4));
		choose.setChoiceD(rs.getString(5));
		choose.setChoice_answer(rs.getString(6));
		choose.setSubjectId(rs.getString(7));
		choose.setChapter(rs.getString(8));
		return choose;
	}
}
