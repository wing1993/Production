package me.zxks.entity;

/**
 * 单项选择题实体类
 */
public class Single_choose {
	private String choice_content; //单项选择题内容
	private String choiceA;        //选项A
	private String choiceB;        //选项B
	private String choiceC;        //选项C
	private String choiceD;        //选项D
	private String choice_answer;  //答案
	private String subjectId;	   //所属科目
	private String chapter;        //所属章节
	private String paper_name;	   //试卷名称
	public String getChoice_content() {
		return choice_content;
	}
	public void setChoice_content(String choice_content) {
		this.choice_content = choice_content;
	}
	public String getChoiceA() {
		return choiceA;
	}
	public void setChoiceA(String choiceA) {
		this.choiceA = choiceA;
	}
	public String getChoiceB() {
		return choiceB;
	}
	public void setChoiceB(String choiceB) {
		this.choiceB = choiceB;
	}
	public String getChoiceC() {
		return choiceC;
	}
	public void setChoiceC(String choiceC) {
		this.choiceC = choiceC;
	}
	public String getChoiceD() {
		return choiceD;
	}
	public void setChoiceD(String choiceD) {
		this.choiceD = choiceD;
	}
	public String getChoice_answer() {
		return choice_answer;
	}
	public void setChoice_answer(String choice_answer) {
		this.choice_answer = choice_answer;
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
