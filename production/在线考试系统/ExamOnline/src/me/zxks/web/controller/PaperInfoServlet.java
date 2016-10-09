package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zxks.dao.impl.QTypeDaoImpl;
import me.zxks.dao.impl.ScoreDaoImpl;
import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.entity.QType;
import me.zxks.entity.Student;
import me.zxks.entity.TestResult;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class PaperInfo
 */
@WebServlet("/PaperInfoServlet")
public class PaperInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService BPService = new BackParameterService();

	// private QType qType;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaperInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 显示试卷信息
	 */
	public void showPaper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
    	System.out.println(request.getSession().getAttribute("logintime"));
		String subjectName = request.getParameter("subject");
		SubjectDaoImpl sDaoImpl = new SubjectDaoImpl();
		QTypeDaoImpl qDaoImpl = new QTypeDaoImpl();
		QType qType = new QType();
		try {
			sDaoImpl.getConn();
			qDaoImpl.getConn();

			qType.setSubjectId(sDaoImpl.find(subjectName));// 将前台传回的subjectId转化为subjectNam
			ResultSet rs = qDaoImpl.queryPaper(qType);
			/*
			 * ArrayList<QType> list = new ArrayList<QType>(); if (rs.next()) {
			 * qType = new QType(); qType.setPaper_name(rs.getString(7));
			 * qType.setqTime(rs.getInt(6));// 考试时间
			 * qType.setqTest_time(rs.getString(5));// 考试时长
			 * qType.settName(rs.getString(8));// list.add(qType); }
			 */
			BPService.Parameter2(response, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sDaoImpl.closeAll();
			qDaoImpl.closeAll();
		}
	}

	/**
	 * 
	 * 考试状态
	 */
	public void testState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stuInfo");
		String stuNo = student.getStuNo();
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		System.out.println(student.getStuNo() + subject + time);
		String subjectid = null;// 科目id
		SubjectDaoImpl sDaoImpl = new SubjectDaoImpl();// 返回科目id的类
		QTypeDaoImpl qDaoImpl = new QTypeDaoImpl();
		try {
			sDaoImpl.getConn();
			qDaoImpl.getConn();
			subjectid = sDaoImpl.find(subject);// 查找科目id
			if (qDaoImpl.queryState(stuNo, subjectid))
				response.getWriter().print("已考");
			else {
				response.getWriter().print("未考");
			}
			System.out.println(qDaoImpl.queryState(stuNo, subjectid));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sDaoImpl.closeAll();
			qDaoImpl.closeAll();
		}
	}

	/**
	 * 显示考试成绩
	 */
	public void showScore(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		String subjectName = request.getParameter("subject");
		SubjectDaoImpl sDaoImpl = new SubjectDaoImpl();
		ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();
		HttpSession session = request.getSession();
		Student student = (Student) session.getAttribute("stuInfo");// 获取学生学号

		try {
			sDaoImpl.getConn();
			scoreDaoImpl.getConn();
			String subjectId = sDaoImpl.find(subjectName);// 获取科目id

			ArrayList<TestResult> tr = new ArrayList<TestResult>();
			ResultSet rs_paper_score = scoreDaoImpl.paper_score(
					student.getStuNo(), subjectId);
			while (rs_paper_score.next()) {
				TestResult tResult = new TestResult();
				tResult.setPaper_name(rs_paper_score.getString(1));// 获取试卷名称
				tResult.setMyScore(rs_paper_score.getInt(2));// 查找科目分数
				ResultSet rs_avg = scoreDaoImpl.avgSet(rs_paper_score
						.getString(1));// 查找平均分
				if (rs_avg.next()) {
					tResult.setAvg(rs_avg.getFloat(1));// 设置平均分
				} else {
					tResult.setAvg(0);
				}

				// 查找排名
				ResultSet rs_rank = scoreDaoImpl.myRank(student.getStuNo(),
						rs_paper_score.getString(1));
				if (rs_rank.next()) {
					tResult.setRank(rs_rank.getInt(1));// 获取排名
				} else {
					tResult.setRank(0);
				}
				tr.add(tResult);
			}
			response.getWriter().print(JSONArray.fromObject(tr));
			response.getWriter().flush();
			response.getWriter().close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sDaoImpl.closeAll();
			scoreDaoImpl.closeAll();
		}
	}
}
