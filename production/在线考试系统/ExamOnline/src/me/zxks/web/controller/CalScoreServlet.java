package me.zxks.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.ScoreDaoImpl;
import me.zxks.entity.Student;

/**
 * Servlet implementation class CalScoreServlet
 */
@WebServlet("/CalScoreServlet")
public class CalScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalScoreServlet() {
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
	 * 
	 * 循环数组计算每种题型的总分
	 */
	public int addScore(int[] arrs) {
		int sum = 0;
		for (int i = 0; i < arrs.length; i++) {
			sum += arrs[i];
		}
		return sum;
	}

	/**
	 * 计算题目的总分
	 */
	public int Sum(String[] strs) {
		int sum = 0;
		return addScore(StringToInt(strs));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stuInfo");
		String paper_name = (String) session.getAttribute("paper_name");// 获取考试科目
		String subjectId = (String) session.getAttribute("subjectId");// 获取考试科目
		String item_content[] = request.getParameterValues("item_content");// 获取主观题题目内容
		String item_ans[] = request.getParameterValues("item_answer");// 获取学生所答主观题答案
		int full_score[] = StringToInt(request.getParameterValues("item_score"));// 获取主观题的分数

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String test_time = sdf.format(new Date());// 获取当前日期
		int choose_sum = 0;
		int comple_sum = 0;
		int tfng_sum = 0;
		choose_sum = Sum(request.getParameterValues("choose_score"));// 计算单项选择的分数
		comple_sum = Sum(request.getParameterValues("comple_score"));// 计算填空题的分数
		tfng_sum = Sum(request.getParameterValues("tfng_score"));// 计算判断题的分数
		int score = choose_sum + comple_sum + tfng_sum;// 计算总分
		ScoreDaoImpl scoreDao = new ScoreDaoImpl();
		try {
			scoreDao.getConn();
			int result = scoreDao.addScore(student.getStuNo(), subjectId,
					score, test_time, choose_sum, tfng_sum, comple_sum,
					paper_name);
			int item_num = 0;
			for (int i = 0; i < item_content.length; i++) {
				item_num += scoreDao.save_item(student.getStuNo(), paper_name,
						item_content[i], item_ans[i], test_time, full_score[i]);// 将主观题答案存入数据库
			}
			if (result == 1 && item_num == item_content.length) {
				out.print("<script charset='utf-8'>alert('提交成功');window.close();</script>");

			} else {
				out.print("<script charset='utf-8'>alert('提交失败');</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scoreDao.closeAll();
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
