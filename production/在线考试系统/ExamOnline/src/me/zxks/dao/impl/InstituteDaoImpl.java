package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IInstituteDao;
import me.zxks.dbOperate.DBOper;

public class InstituteDaoImpl extends DBOper implements IInstituteDao{

	@Override
	public ResultSet find() throws SQLException {
		String sql = "select DISTINCT InstituteName from institute";
		PreparedStatement pt = this.getStatement(sql);
		return pt.executeQuery();
	}
	/**
	 * 根据instituteName返回instituteId
	 * @param instituteName
	 * @return
	 */
	public String getInstituteId(String instituteName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		this.getConn();
		String sql="select distinct instituteId from institute where instituteName=?";
		PreparedStatement pt = this.getStatement(sql);
		String str=null;
		try {
			pt.setString(1,instituteName);
			ResultSet rs=this.executeQuery(sql, pt);
			if(rs.next()){
				str=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		return str;
	}
	
	
	
}
