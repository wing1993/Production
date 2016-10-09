package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import me.zxks.dao.impl.StudentDaoImpl;
import me.zxks.dao.impl.TeacherDaoImpl;
import me.zxks.entity.Student;
import me.zxks.entity.Teacher;

/**
 * 处理登录的Servlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		//获取验证码
		String str=(String) request.getSession().getAttribute("vCode");
		// 获取提交的信息
		String name = request.getParameter("username").trim();
		String password = request.getParameter("userpassword");
		String role = request.getParameter("role");
		String vCode=request.getParameter("check_core");
		
		
		/*ServletContext ctx=this.getServletContext();
		synchronized(this){//线程同步
			Integer userCount=(Integer)ctx.getAttribute("userCount");
			int temp=0;
			if(userCount==null){
				//如果userCount=null，说明为第一次访问
				userCount=new Integer(1);
			}
			else{
				//否则，说明不是第一次访问，在线人数加1
				temp=userCount.intValue()+1;
				userCount=new Integer(temp);
			}
			//把新的访问人数写入ServletContext对象中
			ctx.setAttribute("userCount",userCount);
		}*/
		
		
		if(vCode.equalsIgnoreCase(str)){
			if(role.equals("考生"))
			{
				// 将信息封装到Student对象中
				Student student = new Student();
				student.setStuNo(name);
				student.setStuPwd(password);
				// 调用studentDao，完成数据库操作
				StudentDaoImpl studentDao = new StudentDaoImpl();
				try {
					studentDao.getConn();// 连接数据库
					ResultSet rs = studentDao.studentLogin(student);
					if (rs.next()) {	
						student.setStuName(rs.getString(2));
						student.setStuGender(rs.getInt(3));
						student.setClassNo(rs.getString(4));
						student.setStuPwd(rs.getString(5));
						request.setAttribute("stu", student);
						request.getSession().setAttribute("username",name);
						session.setAttribute("stuInfo", student);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						String loginTime=df.format(new Date());// new Date()为获取当前系统时间
						
						//考虑用户不点“退出”或者异常退出
						if(studentDao.check_online(student)){
							studentDao.insert(student,loginTime);//保存登录信息
						}else{
							studentDao.update_online(student,loginTime);//修改登录信息
							studentDao.closeAll();
						}
						
						request.getRequestDispatcher("StuLoginUIServlet")
							.forward(request,response);
					} else {
						response.getWriter().print("<script>alert('用户名或密码输入错误!');window.location.href='login.jsp';</script>");
						
						//response.sendRedirect("login.jsp");
						return;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					studentDao.closeAll();
				}
			}else if(name.equals("root")&&password.equals("123")&&role.equals("管理员")){
				request.getSession().setAttribute("username",name);
				request.getRequestDispatcher("AdminLoginUIServlet")
					.forward(request,response);
				return;
			}else {
				// 将信息封装到Teacher对象中
				Teacher teacher = new Teacher();
				teacher.settNo(name);
				teacher.settPwd(password);
				TeacherDaoImpl teacherDao = new TeacherDaoImpl();
				try {
					teacherDao.getConn();// 连接数据库
					ResultSet rs = teacherDao.teacherLogin(teacher);
					if (rs.next()) {
						teacher.settName(rs.getString(2));
						teacher.settGender(rs.getInt(3));
					teacher.settRole(rs.getString(4));
					teacher.setInstituteId(rs.getString(5));
					teacher.settPwd(rs.getString(6));
					request.setAttribute("tea", teacher);
					session.setAttribute("tInfo",teacher);
					request.getSession().setAttribute("username",name);
						request.getSession().setAttribute("username",name);
						if(role.equals("监考员")){
							request.getRequestDispatcher("/WEB-INF/pages/Invigilator.jsp")
							.forward(request,response);
							return;
						}else if(role.equals("题库管理员")){
							request.getRequestDispatcher("TeaLoginUIServlet")
							.forward(request,response);
							return;
						}
					} else {
						response.getWriter().print("<script>alert('用户名或密码输入错误!');window.location.href='login.jsp';</script>");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					teacherDao.closeAll();
				}
			}
		}else{
			response.getWriter().print("<script>alert('验证码输入错误!');window.location.href='login.jsp';</script>");
			return;
		}
		/*if(role.equals("考生"))
		{
			// 将信息封装到Student对象中
			Student student = new Student();
			student.setStuNo(name);
			student.setStuPwd(password);
			// 调用studentDao，完成数据库操作
			StudentDaoImpl studentDao = new StudentDaoImpl();
			try {
				studentDao.getConn();// 连接数据库
				ResultSet rs = studentDao.studentLogin(student);
				if (rs.next()) {
					student.setStuName(rs.getString(2));
					student.setStuGender(rs.getInt(3));
					student.setClassNo(rs.getString(4));
					student.setStuPwd(rs.getString(5));
					request.setAttribute("stu", student);
					request.getSession().setAttribute("username",name);
					request.getRequestDispatcher("StuLoginUIServlet")
						.forward(request,response);
		            return;
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！", "错误提示",
							JOptionPane.ERROR_MESSAGE);
					//request.setCharacterEncoding("utf-8");
					//response.setContentType("text/html;charset=utf-8");
					request.getRequestDispatcher("login.html").forward(request,response);
					//out.print("<script>alert('用户名或密码错误!');</script>");
					//out.print("用户名或密码错误！<br/><a href='login.html'>返回</a>");
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(name.equals("root")&&password.equals("123")&&role.equals("管理员")){
			request.getSession().setAttribute("username",name);
			request.getRequestDispatcher("AdminLoginUIServlet")
				.forward(request,response);
		}else {
			// 将信息封装到Teacher对象中
			Teacher teacher = new Teacher();
			teacher.settNo(name);
			teacher.settPwd(password);
			TeacherDaoImpl teacherDao = new TeacherDaoImpl();
			try {
				teacherDao.getConn();// 连接数据库
				ResultSet rs = teacherDao.teacherLogin(teacher);
				if (rs.next()) {
					teacher.settName(rs.getString(2));
					teacher.settGender(rs.getInt(3));
					teacher.settRole(rs.getString(4));
					teacher.setInstituteId(rs.getString(5));
					teacher.settPwd(rs.getString(6));
					request.setAttribute("tea", teacher);
					request.getSession().setAttribute("username",name);
					//response.setContentType("text/html;charset=utf-8");
					if(rs.getString(4).equals("监考员")){
						request.getRequestDispatcher("/WEB-INF/pages/监考员.jsp")
						.forward(request,response);
					}else if(rs.getString(4).equals("题库管理员")){
						request.getRequestDispatcher("TeaLoginUIServlet")
						.forward(request,response);
					}
		            return;
					
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！", "错误", 
							JOptionPane.ERROR_MESSAGE);
					request.getRequestDispatcher("login.jsp")
					.forward(request,response);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
