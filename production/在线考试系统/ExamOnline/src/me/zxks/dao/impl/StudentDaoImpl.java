package me.zxks.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.entity.Student;
import me.zxks.dao.IStudentDao;
import me.zxks.dbOperate.DBOper;


public class StudentDaoImpl extends DBOper implements IStudentDao{
	
	int recordNum=8;// 分页   一页显示8条记录
	
	// 插入学生信息
	public int insert(Student student) throws SQLException {
		try {
			this.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "insert into student(stuNo,stuName,stuGender,classNo,stuPwd) values(?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		pt.setString(2, student.getStuName());
		pt.setInt(3, student.getStuGender());
		pt.setString(4, student.getClassNo());
		pt.setString(5,student.getStuNo());
		int result = this.executeUpdate(sql, pt);
		this.closeAll();
		return result;
	}

	// 检测用户名是否重复
	public boolean  check(Student student) throws SQLException {
		try {
			this.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select * from student where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		ResultSet rs= pt.executeQuery();
		if(rs.next()){
			return false;
		}else{
			return true;
		}
		
	}

	// 验证登录信息
	public ResultSet studentLogin(Student student) throws SQLException {
		String sql = "select * from student where stuNo=? and stuPwd=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		pt.setString(2, student.getStuPwd());

		return this.executeQuery(sql, pt);

	}

	// 修改学生信息
	/*public int editStuInfo(Student student) throws SQLException {
		String sql = "update student1 set name=?gender=?,faculty=?,major=? where number?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getName());
		pt.setInt(2, student.getGender());
		pt.setString(3, student.getFaculty());
		pt.setString(4, student.getMajor());
		pt.setString(5, student.getNumber());
		return this.executeUpdate(sql, pt);
	}*/
	/**
	 * 修改密码(wing)
	 */
	public int changeKey(Student student) throws SQLException {
		String sql = "update student set stuPwd=? where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuPwd());
		pt.setString(2, student.getStuNo());
		return this.executeUpdate(sql, pt);
	}
	/**
	 * 考生信息查询修改  根据学院班级 查询学生信息
	 * @param instituteName
	 * @param className
	 * @return
	 * @throws SQLException
	 */
	public ResultSet show(String instituteName,String className,String grade,int page) throws SQLException{
		String sql = "select stuNo,stuName,stuGender,instituteName,grade,className from student,institute,class" +
				"  where student.classNo=class.classNo and class.instituteId=institute.instituteId and" +
				"  instituteName=? and className=? and grade=? order by stuNo limit ?,?";
		
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, instituteName);
		pt.setString(2, className);
		pt.setString(3, grade);
		pt.setInt(4, (page-1)*recordNum);
		pt.setInt(5, recordNum);
		System.out.println("执行sql");
		return this.executeQuery(sql, pt);
	}
	/**
	 * 根据学号或姓名精确查找学生信息
	 * @param stuNo
	 * @param stuName
	 * @return
	 * @throws SQLException
	 */
	public ResultSet showBystuN(String stuNo,String stuName) throws SQLException{
		if(!stuNo.equals("")){
			String sql = "select stuNo,stuName,stuGender,instituteName,grade,className from student,institute,class" +
				"  where student.classNo=class.classNo and class.instituteId=institute.instituteId and" +
				"  stuNo=?";
			PreparedStatement pt = this.getStatement(sql);
			pt.setString(1, stuNo);
			return this.executeQuery(sql, pt);
		}
		else{
			String sql = "select stuNo,stuName,stuGender,instituteName,grade,className from student,institute,class" +
				"  where student.classNo=class.classNo and class.instituteId=institute.instituteId and" +
				"  stuName=?";
			PreparedStatement pt = this.getStatement(sql);
			pt.setString(1, stuName);
			return this.executeQuery(sql, pt);
		}
	}

	public int delete(String stuNo) {
		String sql = "delete from student where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, stuNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.executeUpdate(sql, pt);
		
	}
	public int save( Student student) {
		String sql = "update student set stuName=?,stuGender=?,classNo=? where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1,student.getStuName());
			pt.setInt(2, student.getStuGender());
			pt.setString(3, student.getClassNo());
			pt.setString(4,student.getStuNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.executeUpdate(sql, pt);
		
	}
	//统计在线学生
	public void insert(Student student, String loginTime) throws Exception{
		try {
			this.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "insert into login_stu(stuNo,stuName,stuGender,classNo,loginTime) values(?,?,?,?,?)";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		pt.setString(2, student.getStuName());
		pt.setInt(3, student.getStuGender());
		pt.setString(4, student.getClassNo());
		pt.setString(5,loginTime);
		int result = this.executeUpdate(sql, pt);
		this.closeAll();
	}
	public boolean  check_online(Student student) throws SQLException {
		try {
			this.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "select * from login_stu where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1, student.getStuNo());
		ResultSet rs= pt.executeQuery();
		if(rs.next()){
			return false;
		}else{
			return true;
		}
		
	}
	public int delete_online(String stuNo) throws Exception{
		this.getConn();
		String sql = "delete from login_stu where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, stuNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.executeUpdate(sql, pt);
		
	}

	public void update_online(Student student,String loginTime) throws Exception{
		this.getConn();
		String sql="update login_stu set loginTime=? where stuNo=?";
		PreparedStatement pt = this.getStatement(sql);
		try {
			pt.setString(1, loginTime);
			pt.setString(2,student.getStuNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.executeUpdate(sql, pt);
	}

	public int  getOnlineTotal() throws NumberFormatException, SQLException {
		try {
			this.getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int n=0;
		String sql = "select count(*) from login_stu ";
		PreparedStatement pt = this.getStatement(sql);
		ResultSet rs=pt.executeQuery();
		if(rs.next()) n=Integer.parseInt(rs.getString(1));
		return n;
		
	}

	public ResultSet getOnlineStu() throws SQLException {
		try {
			this .getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select * from login_stu ";
		PreparedStatement pt = this.getStatement(sql);
		ResultSet rs=this.executeQuery(sql, pt);
		return rs;
	}	
	public ResultSet getOnlineStu(String classNo) throws SQLException {
		try {
			this .getConn();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "select * from login_stu where classNo=? ";
		PreparedStatement pt = this.getStatement(sql);
		pt.setString(1,classNo);
		ResultSet rs=this.executeQuery(sql, pt);
		return rs;
	}	
}

