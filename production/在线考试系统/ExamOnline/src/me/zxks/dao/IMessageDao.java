package me.zxks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Message;

public interface IMessageDao {
	public ResultSet showMessage() throws SQLException;
	/**
	 * 添加消息  yjh
	 */
	public int addMessage(Message message);
	/**
	 * 根据发布消息的教师Id查询消息
	 */
	public ResultSet showMessage(Message message);
	/**
	 * 根据发布消息message_title删除消息
	 */
	public int deleteMessage(Message message);
	public ResultSet getMessageId(Message message) throws SQLException ;
	int updateMessage(Message message);
}
