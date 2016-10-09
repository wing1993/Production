package me.zxks.entity;

/**
 * 教师实体类
 */
public class Teacher {
	
	private String tNo;         //教师编号
	private String tName;       //姓名
	private int tGender;        //性别
	private String tRole;       //角色
	private String instituteId; //所属学院编号
	private String tPwd;        //登录密码
	public String gettNo() {
		return tNo;
	}
	public void settNo(String tNo) {
		this.tNo = tNo;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public int gettGender() {
		return tGender;
	}
	public void settGender(int tGender) {
		this.tGender = tGender;
	}
	public String gettRole() {
		return tRole;
	}
	public void settRole(String tRole) {
		this.tRole = tRole;
	}
	public String getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	public String gettPwd() {
		return tPwd;
	}
	public void settPwd(String tPwd) {
		this.tPwd = tPwd;
	}
	
}
