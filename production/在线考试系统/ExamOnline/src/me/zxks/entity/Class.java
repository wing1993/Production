package me.zxks.entity;

/**
 * 班级实体类Class
 */
public class Class {
	private	String classNo;  	//班级编号
	private String className;   //班级名称
	private String instituteId; //所属学院
	private String grade;       //年级
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
