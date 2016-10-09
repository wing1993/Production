package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.ClassDaoImpl;
import me.zxks.dao.impl.ScoreDaoImpl;
import me.zxks.dao.impl.StudentDaoImpl;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;

/**
 * Servlet implementation class DoInvigilatorServlet
 */
@WebServlet("/DoInvigilatorServlet")
public class DoInvigilatorServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BackParameterService bpService=new  BackParameterService();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoInvigilatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //获取当前学生在线
    public void getOnlineTotal(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException, NumberFormatException, SQLException {
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	response.getWriter().print(studentDao.getOnlineTotal());
    }
    //显示在线学生
    public void ShowStudentState(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException, SQLException {
    	StudentDaoImpl studentDao = new StudentDaoImpl();
    	String str=request.getParameter("className");
		String classNo=null;
    	if(str.isEmpty()){
	    	ResultSet rs=studentDao.getOnlineStu();
	    	bpService.Parameter2(response, rs);
	    }else{
	    	String className=str.substring(2);
	    	String grade=str.substring(0,2);
	    	ClassDaoImpl classDao=new ClassDaoImpl();
	    	try {
				classNo=classDao.select(className, grade);
			} catch( Exception e) {
				e.printStackTrace();
			}
	    	ResultSet rs=studentDao.getOnlineStu(classNo);
	    	bpService.Parameter2(response, rs);
	    }
    }
    //将考试成绩作废
    public void cancelStuScore(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	String paper_name=request.getParameter("paper_name");
    	String stuNo=request.getParameter("stuNo");
    	ScoreDaoImpl scoreDao=new ScoreDaoImpl();
    	scoreDao.getConn();
    	if(scoreDao.updateStuScore(paper_name,stuNo)==1) {
    		String result="该学生试卷成绩已作废！";
    		scoreDao.closeAll();
    		response.getWriter().print(result);
    	}else{
    		String result="该学生试卷成绩作废失败！";
    		response.getWriter().print(result);
    		scoreDao.closeAll();
    	}
    	
    }
}
