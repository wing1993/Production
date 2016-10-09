package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.PaperDaoImpl;
import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.entity.Completion;
import me.zxks.entity.QType;
import me.zxks.entity.Single_choose;
import me.zxks.entity.Subjective_item;
import me.zxks.entity.Tfng;

/**
 * Servlet implementation class ShowPaperServlet
 */
@WebServlet("/ShowPaperServlet")
public class ShowPaperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String paper_name = java.net.URLDecoder.decode(
				request.getParameter("paper_name"), "UTF-8");// 获取试卷名称
		String test_time = request.getParameter("test_time");// 获取考试时长
		String subjectName = java.net.URLDecoder.decode(
				request.getParameter("subjectName"), "UTF-8");// 获取科目名称
		SubjectDaoImpl sDaoImpl = new SubjectDaoImpl();

		QType qType = new QType();
		qType.setPaper_name(paper_name);
		qType.setqTest_time(test_time);
		HttpSession session = request.getSession();
		session.setAttribute("qType", qType);
		session.setAttribute("paper_name", paper_name);
		PaperDaoImpl pDaoImpl = new PaperDaoImpl();

		ArrayList<Single_choose> single_chooses = new ArrayList<Single_choose>();
		ArrayList<Completion> completions = new ArrayList<Completion>();
		ArrayList<Subjective_item> subjective_items = new ArrayList<Subjective_item>();
		ArrayList<Tfng> tfngs = new ArrayList<Tfng>();

		// HttpSession session2 = request.getSession();
		try {
			pDaoImpl.getConn();
			sDaoImpl.getConn();
			String subjectId = sDaoImpl.find(subjectName);// 根据科目名称查找id
			session.setAttribute("subjectId", subjectId);// 将科目id存在session中
			ResultSet rs_qtype = pDaoImpl.find_type(qType.getPaper_name());
			ArrayList<QType> qTypes = new ArrayList<QType>();
			for (; rs_qtype.next();) {
				QType qType1 = new QType();
				qType1.setqType(rs_qtype.getString(1));
				qType1.setqNum(rs_qtype.getInt(2));
				qType1.setqScore(rs_qtype.getInt(3));
				qTypes.add(qType1);
			}
			session.setAttribute("qTypes", qTypes);

			ResultSet rs_choose = pDaoImpl.choose(qType);
			while (rs_choose.next()) {
				Single_choose single_choose = new Single_choose();
				single_choose.setPaper_name(paper_name);// 设置paper_name
				single_choose.setChoice_content(rs_choose.getString(2));
				single_choose.setChoiceA(rs_choose.getString(3));
				single_choose.setChoiceB(rs_choose.getString(4));
				single_choose.setChoiceC(rs_choose.getString(5));
				single_choose.setChoiceD(rs_choose.getString(6));
				single_choose.setChoice_answer(rs_choose.getString(7));
				single_choose.setSubjectId(rs_choose.getString(8));
				single_choose.setPaper_name(rs_choose.getString(11));
				single_chooses.add(single_choose);
			}
			session.setAttribute("single_chooses", single_chooses);// 设置选择题session

			ResultSet rs_comple = pDaoImpl.comple(qType);
			while (rs_comple.next()) {
				Completion completion = new Completion();
				completion.setPaper_name(paper_name);
				completion.setCompletion_content(rs_comple.getString(2));
				completion.setCompletion_answer(rs_comple.getString(3));
				completions.add(completion);
			}
			session.setAttribute("completions", completions);// 设置填空题session

			ResultSet rs_subjective = pDaoImpl.subjective(qType);
			while (rs_subjective.next()) {
				Subjective_item subjective_item = new Subjective_item();
				subjective_item.setPaper_name(paper_name);
				subjective_item.setItem_content(rs_subjective.getString(2));
				subjective_items.add(subjective_item);
			}
			session.setAttribute("subjective_items", subjective_items);// 设置主观题session

			ResultSet rs_tfng = pDaoImpl.tfng(qType);
			while (rs_tfng.next()) {
				Tfng tfng = new Tfng();
				tfng.setPaper_name(paper_name);
				tfng.setTfng_content(rs_tfng.getString(2));
				tfng.setTfng_answer(rs_tfng.getInt(3));
				tfngs.add(tfng);
			}
			session.setAttribute("tfngs", tfngs);// 设置判断题session

		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletContext ctx = this.getServletContext();
		synchronized (this) {
			Integer userCount = (Integer) ctx.getAttribute("userCount");
			int temp = 0;
			if (userCount == null) {
				// 如果为空，说明窗口还未打开
				request.getRequestDispatcher("/WEB-INF/pages/testOnline.jsp")
						.forward(request, response);
				System.out.println(userCount);
			} else {
				temp = userCount.intValue() + 1;
				userCount = new Integer(temp);
				response.getWriter().print(
						"<script>alert('考试窗口已打开！');history.back();</script>");
				System.out.println("考试窗口已打开！");
			}
			ctx.setAttribute("userCount", userCount);
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
