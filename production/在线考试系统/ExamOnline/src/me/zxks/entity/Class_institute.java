package me.zxks.entity;

/**
 * 记录学生的学院，班级信息
 * 
 * @author Administrator
 *
 */
public class Class_institute {
	private String instituteName; // 学院名称
	private String className; // 班级名称
	private String grade; // 年级

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
