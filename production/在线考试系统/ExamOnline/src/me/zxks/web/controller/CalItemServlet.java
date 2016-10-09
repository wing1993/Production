package me.zxks.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.TestResultDaoImpl;

/**
 * Servlet implementation class CalItemServlet
 */
@WebServlet("/CalItemServlet")
public class CalItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 将String[]转化为int[]
	 */
	public int[] StringToInt(String[] arrs) {
		int[] ints = new int[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String stuNo = request.getParameter("stuNo");
		int getScore[] = StringToInt(request.getParameterValues("getScore"));// 获取教师给的分数
		int item_id[] = StringToInt(request.getParameterValues("item_id"));// 获取主观题答案id
		TestResultDaoImpl tr = new TestResultDaoImpl();
		try {
			tr.getConn();
			int k = 0;
			int j = 0;
			for (int i = 0; i < item_id.length; i++) {
				k += tr.Update_item_score(item_id[i], getScore[i]);
				j = tr.Update_score(stuNo, getScore[i]);
			}
			if (k == item_id.length && j >= 1) {
				response.getWriter().print(
						"<script>alert('评分成功！');window.close();</script>");
			} else {
				response.getWriter().print("<script>alert('评分失败！');</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tr.closeAll();
		}
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
