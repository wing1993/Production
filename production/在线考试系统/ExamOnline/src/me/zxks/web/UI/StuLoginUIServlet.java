package me.zxks.web.UI;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.Class_instituteDaoImpl;
import me.zxks.entity.Class_institute;
import me.zxks.entity.Student;
import me.zxks.service.impl.BackParameterService;

/**
 * StuLoginUIServlet负责为学生输出登录成功界面
 * 当用户访问StuLoginUIServlet时，就跳转到WEB-INF/pages目录下的Student.jsp页面
 */
@WebServlet("/StuLoginUIServlet")
public class StuLoginUIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService BPService = new BackParameterService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StuLoginUIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stuInfo");
		Class_institute cInstitute = new Class_institute();
		// 调用Class_instituteDaoImpl完成对学院班级信息的查找
		Class_instituteDaoImpl class_instituteDaoImpl = new Class_instituteDaoImpl();
		try {
			class_instituteDaoImpl.getConn();// 连接数据库
			ResultSet rs = class_instituteDaoImpl.class_institute(student);
			if (rs.next()) {
				cInstitute.setInstituteName(rs.getString(1));
				cInstitute.setClassName(rs.getString(2));
				cInstitute.setGrade(rs.getString(3));
				HttpSession session1 = request.getSession();
				session1.setAttribute("classInfo", cInstitute);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.getRequestDispatcher("/WEB-INF/pages/student.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
