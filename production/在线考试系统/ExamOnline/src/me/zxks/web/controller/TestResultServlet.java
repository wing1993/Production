package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.dao.impl.TestResultDaoImpl;
import me.zxks.entity.Score;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class TestResultServlet
 */
//@WebServlet("/TestResultServlet")
public class TestResultServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService BPService = new BackParameterService();

	
	 public TestResultServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	/**
	 * 查找班级信息
	 */
	public void searchClass(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		System.out.println("1212121212");
		String subjectName = request.getParameter("subjectName");
		SubjectDaoImpl subdImpl = new SubjectDaoImpl();
		TestResultDaoImpl tr = new TestResultDaoImpl();
		ArrayList<String> list = new ArrayList<String>();
		try {
			subdImpl.getConn();
			tr.getConn();
			String subjectId = subdImpl.find(subjectName);
			ResultSet rs = tr.className(subjectId);
			while (rs.next()) {
				list.add(rs.getString(1) + rs.getString(2));
			}
			BPService.Parameter(response, list);// 返回班级名称
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			subdImpl.closeAll();
		}
	}

	/**
	 * 查找班级试卷信息
	 */
	public void searchPaper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String grade = request.getParameter("grade");
		String className = request.getParameter("className");

		ArrayList<Score> list = new ArrayList<Score>();
		TestResultDaoImpl tr = new TestResultDaoImpl();
		try {
			tr.getConn();
			ResultSet rs = tr.paperResult(grade, className);
			while (rs.next()) {
				Score score = new Score();
				score.setPaper_name(rs.getString(1));
				score.setTest_time(rs.getString(2));

				ResultSet rs_num = tr
						.testNum(rs.getString(1), grade, className);
				if (rs_num.next()) {
					score.setTest_num(rs_num.getString(4));// 设置考试人数
					score.setAvg(rs_num.getFloat(1));// 计算班级平均分
					score.setMax(rs_num.getInt(2));// 计算最高分
					score.setMin(rs_num.getInt(3));// 计算最低分
				}
				ResultSet rsu6 = tr
						.Under_six(rs.getString(1), grade, className);
				ResultSet rs6 = tr.Six(rs.getString(1), grade, className);
				ResultSet rs7 = tr.Seven(rs.getString(1), grade, className);
				ResultSet rs8 = tr.Eight(rs.getString(1), grade, className);
				ResultSet rs9 = tr.Nine(rs.getString(1), grade, className);
				ResultSet rs10 = tr.Ten(rs.getString(1), grade, className);
				if (rsu6.next()) {
					score.setUnder_six(rsu6.getInt(1));
				} else {
					score.setUnder_six(0);
				}
				// 计算分数在60-69之间的人数
				if (rs6.next()) {
					score.setSix(rs6.getInt(1));
				} else {
					score.setSix(0);
				}
				// 计算分数在70-79之间的人数
				if (rs7.next()) {
					score.setSeven(rs7.getInt(1));
				} else {
					score.setSeven(0);
				}
				// 计算分数在80-89之间的人数
				if (rs8.next()) {
					score.setEight(rs8.getInt(1));
				} else {
					score.setEight(0);
				}
				// 计算分数在90-99之间的人数
				if (rs9.next()) {
					score.setNine(rs9.getInt(1));
				} else {
					score.setNine(0);
				}
				// 计算分数大于100的人数
				if (rs10.next()) {
					score.setTen(rs10.getInt(1));
				} else {
					score.setTen(0);
				}
				list.add(score);
			}
			response.getWriter().print(JSONArray.fromObject(list));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tr.closeAll();
		}
	}

	/**
	 * 查找班级
	 */
	public void find_class(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String paper_name = request.getParameter("paper_name");
		TestResultDaoImpl tDaoImpl = new TestResultDaoImpl();
		ArrayList<String> list = new ArrayList<String>();
		try {
			tDaoImpl.getConn();
			ResultSet rs = tDaoImpl.findClass(paper_name);
			while (rs.next()) {
				list.add(rs.getString(1) + rs.getString(2));
			}
			BPService.Parameter(response, list);// 返回班级名称
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tDaoImpl.closeAll();
		}
	}

	/**
	 * 获取学生主观题的内容和答案
	 */
	public void get_item_content(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String grade = request.getParameter("grade");
		String className = request.getParameter("className");
		String paper_name = request.getParameter("paper_name");

		ArrayList<Score> score_list = new ArrayList<Score>();
		TestResultDaoImpl tr = new TestResultDaoImpl();
		try {
			tr.getConn();
			ResultSet rs = tr.find_itemInfo(grade, className, paper_name);
			while (rs.next()) {
				Score score = new Score();
				score.setStuNo(rs.getString(1));
				score.setStuName(rs.getString(2));
				score.setTest_time(rs.getString(3));
				score.setPaper_name(paper_name);
				/*
				 * score.setItem_content(rs.getString(4));
				 * score.setItem_answer(rs.getString(5));
				 * score.setItem_full_score(rs.getInt(6));
				 */
				score_list.add(score);
			}
			response.getWriter().print(JSONArray.fromObject(score_list));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tr.closeAll();
		}
	}
}
