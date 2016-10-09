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
import me.zxks.dao.impl.InstituteDaoImpl;
import me.zxks.dao.impl.StudentDaoImpl;
import me.zxks.dao.impl.TeacherDaoImpl;
import me.zxks.entity.Student;
import me.zxks.entity.Teacher;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;
import me.zxks.web.formbean.PropertyChange;

/**
 * Servlet implementation class DoAdminServlet
 */
@WebServlet("/DoAdminServlet")
public class DoAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //显示学生信息
    public void showStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    	
    	String instituteName=request.getParameter("instituteName");
    	String str=request.getParameter("className");
    	String className=str.substring(2);
    	String grade=str.substring(0,2);
    	int page=Integer.parseInt(request.getParameter("page"));
    	BackParameterService BPService=new BackParameterService();
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	studentDao.getConn();
    	//只选择班级信息    13科技2班   只取  科技2班
    	ResultSet rs=studentDao.show(instituteName,className,grade,page);
		BPService.Parameter2(response, rs);
		studentDao.closeAll();
    }
    //根据学号或姓名查询学生信息
    public void findStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    	
    	String stuNo=request.getParameter("stuNo");
    	String stuName=request.getParameter("stuName");
    	BackParameterService BPService=new BackParameterService();
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	studentDao.getConn();
    	ResultSet rs=studentDao.showBystuN(stuNo,stuName);
		BPService.Parameter2(response, rs);
		studentDao.closeAll();
    }
    //根据编号或姓名查询教师信息
    public void findTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    	
    	String tNo=request.getParameter("tNo");
    	String tName=request.getParameter("tName");
    	BackParameterService BPService=new BackParameterService();
    	TeacherDaoImpl teaDao=new TeacherDaoImpl();
    	teaDao.getConn();
    	ResultSet rs=teaDao.showByteaN(tNo,tName);
		BPService.Parameter2(response, rs);
		teaDao.closeAll();
    }
    //删除学生信息
    public void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	String stuNo=request.getParameter("stuNo");
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	studentDao.getConn();
    	int result=studentDao.delete(stuNo);
    	response.getWriter().print(result);
    	studentDao.closeAll();
    }
    //保存学生信息
    public void saveStudent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	Student student=new Student();
    	student.setStuNo(request.getParameter("stuNo"));
    	student.setStuName(request.getParameter("stuName"));
    	//将性别男,女转换为1,0
    	student.setStuGender(Integer.parseInt(request.getParameter("stuGender")));
    	String str=request.getParameter("className");
    	//将年级班别  拆分成grade 跟 className 属性 并获得classNo
    	student.setGrade(str.substring(0,2));
    	ClassDaoImpl classDao=new ClassDaoImpl();
    	classDao.getConn();
    	student.setClassNo(classDao.select(str.substring(2),str.substring(0,2)));
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	studentDao.getConn();
    	System.out.println(student.getStuName()+" "+student.getStuGender()+" "+student.getClassNo());
    	int result=studentDao.save(student);
    	response.getWriter().print(result);
    	System.out.println("完成saveStudent");
    	studentDao.closeAll();
    	classDao.closeAll();
    }
    public void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	Student student=new Student();
    	student.setStuNo(request.getParameter("stuNo"));
    	StudentDaoImpl studentDao=new StudentDaoImpl();
    	//判断插入的学生编号是否已存在记录
    	if(studentDao.check(student)){
	    	student.setStuName(request.getParameter("stuName"));
	    	student.setStuGender(new PropertyChange().GenderChangeInt(request.getParameter("stuGender")));
	    	String str=request.getParameter("className");
	    	ClassDaoImpl classDao=new ClassDaoImpl();
	    	student.setClassNo(classDao.select(str.substring(2),str.substring(0,2)));
	    	System.out.println(student.getStuNo()+"  "+student.getStuName()+"  "+student.getStuGender()+" "+student.getClassNo());
	    	if(studentDao.insert(student)==1){
	    		response.getWriter().print("学生信息插入成功！");
	    	}else{
	    		response.getWriter().print("学生信息插入失败！");
	    	}
	    	classDao.closeAll();
    	}else{
    		response.getWriter().print("该学号已存在！");
    	}
    	studentDao.closeAll();
    }
    public void insertTeacher(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	Teacher teacher=new Teacher();
    	teacher.settNo(request.getParameter("tNo"));
    	TeacherDaoImpl teacherDao=new TeacherDaoImpl();
    	//判断插入的记录是否已存在
    	if(teacherDao.check(teacher)){
    		teacher.settName(request.getParameter("tName"));
    		teacher.settRole(request.getParameter("tRole"));
    		teacher.settGender(Integer.parseInt(request.getParameter("tGender")));
    		teacher.setInstituteId(new InstituteDaoImpl().getInstituteId(request.getParameter("instituteName")));
    		if(teacherDao.insert(teacher)){
    			response.getWriter().print("教师信息插入成功！");
    		}else{
    			response.getWriter().print("教师信息插入失败！");
    		}
    	}else{
    		response.getWriter().print("该教师编号已存在");
    	}
    }
}
