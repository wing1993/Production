package me.zxks.entity;


//试卷题型
public class QType {
	private String subjectId;// 科目id
	private String chapter;
	private String qType;// 题型
	private int qNum;// 每种题型的数量
	private int qScore;// 每一种题型一道题分数
	private int qTime;// 考试时间
	private String qTest_time;// 考试时长
	private String paper_name;// 试卷名称
	private String tNo;// 出题人id
	private String tName;// 出题人姓名

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

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public int getqNum() {
		return qNum;
	}

	public void setqNum(int qNum) {
		this.qNum = qNum;
	}

	public int getqScore() {
		return qScore;
	}

	public void setqScore(int qScore) {
		this.qScore = qScore;
	}

	public int getqTime() {
		return qTime;
	}

	public void setqTime(int qTime) {
		this.qTime = qTime;
	}

	public String getqTest_time() {
		return qTest_time;
	}

	public void setqTest_time(String qTest_time) {
		this.qTest_time = qTest_time;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

}
