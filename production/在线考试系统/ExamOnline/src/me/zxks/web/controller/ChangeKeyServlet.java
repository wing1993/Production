package me.zxks.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.StudentDaoImpl;
import me.zxks.entity.Student;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;

/**
 * Servlet implementation class ChangeKeyServlet
 */
@WebServlet("/ChangeKeyServlet")
public class ChangeKeyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService BPService = new BackParameterService();

	/**
	 * 修改密码
	 */
	public void Change(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session中的考生账号和密码
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stuInfo");

		// 读取js传递过来的参数
		String newPassword = request.getParameter("newPassword");

		// 设置新密码
		student.setStuPwd(newPassword);
		StudentDaoImpl stuDaoImpl = new StudentDaoImpl();
		int r;
		try {
			stuDaoImpl.getConn();
			if (stuDaoImpl.changeKey(student) == 1) {
				response.getWriter().print("密码修改成功");
			} else {
				response.getWriter().print("密码修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			stuDaoImpl.closeAll();
		}
	}
}
