package me.zxks.entity;

/**
 * 考试科目实体类
 */
public class Subject {
	private String sbujectId;  //科目id
	private String subjectName; //科目名称
	private int State;
	public String getSbujectId() {
		return sbujectId;
	}
	public void setSbujectId(String sbujectId) {
		this.sbujectId = sbujectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	
}
