package me.zxks.entity;

public class Message {
	private int message_id;// 消息id
	private String message_title;// 消息标题
	private String message_content;// 消息内容
	private String message_time;// 发布时间
	private String message_authorId;// 发布人id
	private String message_authorName;// 发布人姓名

	public String getMessage_authorName() {
		return message_authorName;
	}

	public void setMessage_authorName(String message_authorName) {
		this.message_authorName = message_authorName;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_authorId() {
		return message_authorId;
	}

	public void setMessage_authorId(String message_authorId) {
		this.message_authorId = message_authorId;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

}
