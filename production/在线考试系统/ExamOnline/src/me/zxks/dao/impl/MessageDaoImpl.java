package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.IMessageDao;
import me.zxks.dbOperate.DBOper;
import me.zxks.entity.Message;

public class MessageDaoImpl extends DBOper implements IMessageDao {
	// 显示所有的消息
	public ResultSet showMessage() throws SQLException {
		String sql = "select * from message";
		PreparedStatement pt = this.getStatement(sql);
		return pt.executeQuery();
	}

	// 显示消息的内容
	public ResultSet showDetail(Message message) throws SQLException {
		String sql = "select DISTINCT message_content,message_time,tName from message,"
				+ "teacher where message_authorId=tNo and message_title=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, message.getMessage_title());
		return pt.executeQuery();
	}

	@Override  //叶俊浩
	public int addMessage(Message message) {
		String sql="insert into message(message_title,message_content," +
				"message_time,message_authorId) values(?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1,message.getMessage_title());
			pt.setString(2,message.getMessage_content());
			pt.setString(3,message.getMessage_time());
			pt.setString(4,message.getMessage_authorId());
		} catch (SQLException e) {
			System.out.println("插入Message数据出错，异常如下：");
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	@Override 
	public ResultSet getMessageId(Message message) throws SQLException {
		String sql = "select DISTINCT message_id,message_content from message"
				+ " where message_title=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, message.getMessage_title());
		return pt.executeQuery();
	}
	@Override
	public int updateMessage(Message message) {
		String sql = "update message set message_title=?,message_content=? " +
				"where message_id=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, message.getMessage_title());
			pt.setString(2, message.getMessage_content());
			pt.setInt(3, message.getMessage_id());
		} catch (SQLException e) {
			System.out.println("删除消息出错！异常如下：");
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
	@Override 
	public ResultSet showMessage(Message message) {
		String sql = "select DISTINCT * from message where message_authorId=? ";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, message.getMessage_authorId());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return this.executeQuery(sql, pt);
	}

	@Override
	public int deleteMessage(Message message) {
		String sql = "delete from message where message_title=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, message.getMessage_title());
		} catch (SQLException e) {
			System.out.println("删除消息出错！异常如下：");
			e.printStackTrace();
		}
		int result = this.executeUpdate(sql, pt);
		return result;
	}
}
