package me.zxks.entity;

/**
 * 判断题实体类
 */
public class Tfng {
	private String tfng_content;	//判断题内容	
	private int tfng_answer;		//答案
	private String subjectId;		//所属科目
	private String chapter;			//所属章节
	private String paper_name;	   //试卷名称
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	public String getTfng_content() {
		return tfng_content;
	}
	public void setTfng_content(String tfng_content) {
		this.tfng_content = tfng_content;
	}
	public int getTfng_answer() {
		return tfng_answer;
	}
	public void setTfng_answer(int tfng_answer) {
		this.tfng_answer = tfng_answer;
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
}
