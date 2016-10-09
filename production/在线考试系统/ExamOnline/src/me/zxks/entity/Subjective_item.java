package me.zxks.entity;

public class Subjective_item {
	private String item_content; //主观题内容
	private String item_answer;	 //答案
	private String subjectId;	 //所属科目
	private String chapter;		 //所属章节
	private String paper_name;	   //试卷名称
	public String getPaper_name() {
		return paper_name;
	}
	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	public String getItem_answer() {
		return item_answer;
	}
	public void setItem_answer(String item_answer) {
		this.item_answer = item_answer;
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
