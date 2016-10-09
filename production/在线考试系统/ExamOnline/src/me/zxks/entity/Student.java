package me.zxks.entity;

/**
 * 学生实体类
 */
public class Student {
	
	// 学生学号
    private String stuNo;
    // 学生姓名
    private String stuName;
    // 学生性别
    private int stuGender;
    // 学生班别
    private String classNo;
    // 密码
    private String stuPwd;
    
    private String className;
    private String grade;
    
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuGender() {
		return stuGender;
	}
	public void setStuGender(int stuGender) {
		this.stuGender = stuGender;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getStuPwd() {
		return stuPwd;
	}
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}

}
