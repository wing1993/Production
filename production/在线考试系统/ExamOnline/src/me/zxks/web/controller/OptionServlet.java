package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;

import me.zxks.dao.impl.ClassDaoImpl;
import me.zxks.dao.impl.InstituteDaoImpl;
import me.zxks.dao.impl.PaperDaoImpl;
import me.zxks.dao.impl.SubjectDaoImpl;

/**
 * Servlet implementation class OptionServlet
 */
@WebServlet("/OptionServlet")
public class OptionServlet extends BaseServlet {  //继承BaseServlet
	
	
	BackParameterService BPService=new BackParameterService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 取得学院信息到下拉列表	
	 * @param request
	 * @param response
	 * @return
	 */
    public String ajaxInstituteOption(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InstituteDaoImpl instituteDao=new InstituteDaoImpl();
		try{
			instituteDao.getConn();// 连接数据库
			ResultSet rs = instituteDao.find();
			ArrayList<String> list=new ArrayList<String>();
			for(;rs.next();){
				list.add(rs.getString(1));
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			instituteDao.closeAll();
		}
    	return null;
    	
    }
    
    /**
     * 获取班级下拉框内容
     * @param request
     * @param response
     * @return
     */
    public String ajaxClassOption(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
    	String instituteName=request.getParameter("instituteName");
		ClassDaoImpl classDao=new ClassDaoImpl();
		try{
			classDao.getConn();// 连接数据库
			ResultSet rs = classDao.find(instituteName);
			ArrayList<String> list=new ArrayList<String>();
			for(;rs.next();){
				list.add(rs.getString(2)+rs.getString(1));
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			classDao.closeAll();
		}
		return null;
    }
    
    /**
     * 获取科目下拉框内容
     * @param request
     * @param response
     * @return
     */
    public String ajaxSubjectOption(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubjectDaoImpl subjectDao=new SubjectDaoImpl();
		try{
			subjectDao.getConn();// 连接数据库
			ResultSet rs = subjectDao.find();
			ArrayList<String> list=new ArrayList<String>();
			for(;rs.next();){
				
				list.add(rs.getString(1));
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
		}
		return null;
    }
    /**
     * 获取试卷下拉框内容
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void ajaxPapernameOption(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PaperDaoImpl paperDao=new PaperDaoImpl();
		try{
			ResultSet rs = paperDao.find_papername();
			ArrayList<String> list=new ArrayList<String>();
			for(;rs.next();){
				list.add(rs.getString(1));
			}
			BPService.Parameter(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			paperDao.closeAll();
		}
    }
    
}
