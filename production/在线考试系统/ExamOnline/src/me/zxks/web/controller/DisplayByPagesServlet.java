package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.ClassDaoImpl;
import me.zxks.dao.impl.DisplayByPages;
import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.service.impl.BackParameterService;
import me.zxks.service.impl.ShowQuestionService;
import me.zxks.servlet.BaseServlet;

/**
 * Servlet implementation class DisplayByPagesServlet
 */
@WebServlet("/DisplayByPagesServlet")
public class DisplayByPagesServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	BackParameterService BPService=new BackParameterService();
	DisplayByPages dbPages=new DisplayByPages();

    public DisplayByPagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void showQuestion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	String subjectName=request.getParameter("subjectName");
    	String qType=request.getParameter("qType");
    	int page=Integer.parseInt(request.getParameter("page"));
    	SubjectDaoImpl subjectDao=new SubjectDaoImpl();
		ShowQuestionService SQService=new ShowQuestionService();
    	try {
	    	subjectDao.getConn();
			String subjectId=subjectDao.find(subjectName);//根据科目名称获取id
			SQService.getConn();
			ResultSet rs=SQService.show(subjectId, qType, page);//根据qType查询试题
			BPService.Parameter2(response, rs);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		subjectDao.closeAll();
			SQService.closeAll();
    	}
    }
    public void showTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    	String instituteName=request.getParameter("instituteName");
    	int page=Integer.parseInt(request.getParameter("page"));
    	BackParameterService BPService=new BackParameterService();
    	ShowQuestionService SQService=new ShowQuestionService();
		SQService.getConn();
		ResultSet rs=SQService.showTeacher(instituteName,page);//根据qType查询试题
		BPService.Parameter2(response, rs);
		SQService.closeAll();
    } 
    public void showStudentPaper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    	String paper_name=request.getParameter("paper_name");
    	if("".equals(paper_name)){return ;}
    	else{
    		//根据班级名获取班级编号
        	String str=request.getParameter("className");
        	ClassDaoImpl classDao=new ClassDaoImpl();
        	String classNo=classDao.select(str.substring(2), str.substring(0, 2));
        	
			int page=Integer.parseInt(request.getParameter("page"));
			BackParameterService BPService=new BackParameterService();
			ShowQuestionService SQService=new ShowQuestionService();
			SQService.getConn();
			ResultSet rs=SQService.showStudentPaper(paper_name,classNo,page);
			BPService.Parameter2(response, rs);
			SQService.closeAll();
    	}
    }
    public void getPages_total(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	String subjectName=request.getParameter("subjectName");
    	String qType=request.getParameter("qType");
    	SubjectDaoImpl subjectDao=new SubjectDaoImpl();
		ShowQuestionService SQService=new ShowQuestionService();
    	try{
			subjectDao.getConn();
			String subjectId=subjectDao.find(subjectName);//根据科目名称获取id
			SQService.getConn();
			int num=SQService.getCount(subjectId,qType);
			System.out.println("页数为"+num);
			response.getWriter().print(num);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		subjectDao.closeAll();
    		SQService.closeAll();
    	}
    	
    }
    //获得要查询的学生  所需要的页数
    public void getPagesTotal_Student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	System.out.println("查询学生页数");
    	String instituteName=request.getParameter("instituteName");
    	String str=request.getParameter("className");
    	String className=str.substring(2);
    	String grade=str.substring(0, 2);
		ShowQuestionService SQService=new ShowQuestionService();
    	try{
    		int num=SQService.getCount_stu(instituteName,className,grade);
    		response.getWriter().print(num);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		SQService.closeAll();
    	}
    }
    //获得要查询的教师 所需要的页数
    public void getPagesTotal_Teachar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		ShowQuestionService SQService=new ShowQuestionService();
    	String instituteName=request.getParameter("instituteName");
    	System.out.println(instituteName+" djfhasdfh ");
    	try{
    		int num=SQService.getCount_tea(instituteName);
			System.out.println("教师页数为"+num);
    		response.getWriter().print(num);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		SQService.closeAll();
    	}
    }
   /* //监考员   分页显示  考生试卷作废总的页数
    public void getPagesTotal_Paper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	ShowQuestionService SQService=new ShowQuestionService();//分页的业务逻辑
    	String str=request.getParameter("className");
    	String instituteName=request.getParameter("instituteName");
    	String className=str.substring(2);
    	String grade=str.substring(0, 2);
    	ClassDaoImpl classDao=new ClassDaoImpl();
    	String classNo=classDao.select(str.substring(2), str.substring(0, 2));
    	try{
    		int num=SQService.getCount_stu(instituteName,className,grade);
			System.out.println("监考员考生作废页数为"+num);
    		response.getWriter().print(num);
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		SQService.closeAll();
    	}
    }*/
}
