package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.MessageDaoImpl;
import me.zxks.entity.Message;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;

/**
 * Servlet implementation class ScanMessageServlet
 */
@WebServlet("/ScanMessageServlet")
public class ScanMessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService BPService = new BackParameterService();

	// 查询数据库消息
	public void showMessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MessageDaoImpl mDaoImpl = new MessageDaoImpl();
		try {
			mDaoImpl.getConn();
			ResultSet rs = mDaoImpl.showMessage();

			// 建立Message类型的ArrayList
			ArrayList<Message> list = new ArrayList<Message>();
			for (; rs.next();) {
				Message message = new Message();
				// message.setMessage_id(rs.getInt(1));
				message.setMessage_title(rs.getString(2));
				message.setMessage_content(rs.getString(3));
				message.setMessage_time(rs.getString(4));
				list.add(message);
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			mDaoImpl.closeAll();
		}
	}

	/**
	 * 查看消息内容
	 */
	public void showDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");// 获取js传递过来的数据
		Message message = new Message();
		message.setMessage_title(title);
		MessageDaoImpl mDaoImpl = new MessageDaoImpl();
		try {
			mDaoImpl.getConn();
			ResultSet rs = mDaoImpl.showDetail(message);
			ArrayList<Message> list = new ArrayList<Message>();
			for (; rs.next();) {
				message.setMessage_content(rs.getString(1));
				message.setMessage_time(rs.getString(2));
				message.setMessage_authorName(rs.getString(3));
				list.add(message);
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			mDaoImpl.closeAll();
		}
	}
}
