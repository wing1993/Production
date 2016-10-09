package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.TestResultDaoImpl;
import me.zxks.entity.Score;

/**
 * Servlet implementation class ItemGradingServlet
 */
@WebServlet("/ItemGradingServlet")
public class ItemGradingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemGradingServlet() {
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
		String stuNo = java.net.URLDecoder.decode(
				request.getParameter("stuNo"), "UTF-8");// 获取学号
		String paper_name = java.net.URLDecoder.decode(
				request.getParameter("paper_name"), "utf-8");// 获取试卷名称

		TestResultDaoImpl tr = new TestResultDaoImpl();
		ArrayList<Score> list = new ArrayList<Score>();
		try {
			tr.getConn();
			ResultSet rs = tr.find_itemInfo(stuNo, paper_name);
			while (rs.next()) {
				Score score = new Score();
				score.setStuNo(stuNo);
				score.setItem_id(rs.getString(1));
				score.setItem_content(rs.getString(2));
				score.setItem_answer(rs.getString(3));
				score.setItem_full_score(rs.getInt(4));
				score.setItem_full_answer(rs.getString(5));
				list.add(score);
			}
			session.setAttribute("itemInfo", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("/WEB-INF/pages/item_answer.jsp").forward(
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
