package me.zxks.entity;

/**
 * 填空题实体类
 */
public class Completion {
	private String completion_content;  //填空题内容
	private String completion_answer;   //填空题答案
	private String subjectId;           //所属科目
	private String chapter;             //所属章节
	private String paper_name;			//试卷名称
	public String getCompletion_content() {
		return completion_content;
	}
	public void setCompletion_content(String completion_content) {
		this.completion_content = completion_content;
	}
	public String getCompletion_answer() {
		return completion_answer;
	}
	public void setCompletion_answer(String completion_answer) {
		this.completion_answer = completion_answer;
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
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	
}
