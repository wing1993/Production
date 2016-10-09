package me.zxks.dbOperate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import me.zxks.web.formbean.DBPrametes;

public class DBOper {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBPrametes dbPrametes=new DBPrametes(); //调用DBPrametes类
	ArrayList<String> list=new ArrayList<String>(); //作为存储连接数据库的参数的数组
	
	public Connection getConn()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, SQLException {
		
		dbPrametes.getDBPrametes();  //获取数据库连接的参数
		list=dbPrametes.outputDBPrameter();//返回数据库连接的参数
		
		// JDBC驱动
		String driver = "com.mysql.jdbc.Driver";
		// 连接字符串
	    String url = "jdbc:mysql://" +  list.get(0) + ":3306/" +  list.get(1) + "?user=" +  list.get(2)
				+ "&password=" +  list.get(3) + "&useUnicode=true&characterEncoding=gbk";
		// 加载JDBC驱动
		Class.forName(driver).newInstance();
		// 获取连接
		conn = DriverManager.getConnection(url);
		return conn;// 返回连接
	}

	// 返回数据库命令执行对象
	public PreparedStatement getStatement(String sql) {
		try {
			// 根据获取的数据库连接对象创建数据库 命令执行对象
			this.pstmt = conn.prepareStatement(sql);
		} catch (Exception e) {
			System.err.println("获取数据库命令执 行对象错误：" + e.getMessage());
		}
		// 返回数据库命令执行对象
		return this.pstmt;
	}

	public ResultSet executeQuery(String sql, PreparedStatement pt) {
		try {
			this.rs = null;
			// 执行查询语句，返回结果集
			rs = pt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int executeUpdate(String sql, PreparedStatement pt) {
		try {
			// 执行更新，返回受影响的记录数
			return pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet executeQuery(String sql,String[] params){
		try{
			//获取PreparedStatement对象
			pstmt=conn.prepareStatement(sql);
			//设置PreparedStatement对象的参数
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setString(i+1,params[i]);
				}
			}
			//指向查询，返回结果集
			rs=pstmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public int executeUpdate(String sql,String[] params){
		int n=0;
		try{
			//获取PreparedStatement对象
			pstmt=conn.prepareStatement(sql);
			//设置PreparedStatement对象的参数
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setString(i+1,params[i]);
				}
			}
			//执行更新  返回受影响的记录数
			n=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return n;
	}
	
	public void closeAll() {
		// 关闭ResultSet
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭PreparedStatement
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 关闭Connection
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
