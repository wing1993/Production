package me.zxks.entity;

public class Score {
	private String stuNo;
	private String stuName;
	private String subjectId;
	private float score;
	private String test_time;// 考试的日期
	private int q_choose;// 选择题分数
	private int q_tfng;// 判断题分数
	private int q_completion;// 填空题分数
	private int q_item;// 主观题
	private String test_num;// 考试人数
	private String paper_name;// 试卷名称
	private float avg;// 平均分
	private int max;// 最高分
	private int min;// 最低分
	private int under_six;// 60分以下的人数
	private int six;// 60到70之间的人
	private int seven;
	private int eight;
	private int nine;
	private int ten;
	private String item_id;
	private String item_content;
	private String item_full_answer;
	private String item_answer;
	private int item_full_score;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getItem_full_answer() {
		return item_full_answer;
	}

	public void setItem_full_answer(String item_full_answer) {
		this.item_full_answer = item_full_answer;
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

	public int getItem_full_score() {
		return item_full_score;
	}

	public void setItem_full_score(int item_score) {
		this.item_full_score = item_score;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getUnder_six() {
		return under_six;
	}

	public void setUnder_six(int under_six) {
		this.under_six = under_six;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getSeven() {
		return seven;
	}

	public void setSeven(int seven) {
		this.seven = seven;
	}

	public int getEight() {
		return eight;
	}

	public void setEight(int eight) {
		this.eight = eight;
	}

	public int getNine() {
		return nine;
	}

	public void setNine(int nine) {
		this.nine = nine;
	}

	public int getTen() {
		return ten;
	}

	public void setTen(int ten) {
		this.ten = ten;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

	public String getTest_num() {
		return test_num;
	}

	public void setTest_num(String test_num) {
		this.test_num = test_num;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getTest_time() {
		return test_time;
	}

	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}

	public int getQ_choose() {
		return q_choose;
	}

	public void setQ_choose(int q_choose) {
		this.q_choose = q_choose;
	}

	public int getQ_tfng() {
		return q_tfng;
	}

	public void setQ_tfng(int q_tfng) {
		this.q_tfng = q_tfng;
	}

	public int getQ_completion() {
		return q_completion;
	}

	public void setQ_completion(int q_completion) {
		this.q_completion = q_completion;
	}

	public int getQ_item() {
		return q_item;
	}

	public void setQ_item(int q_item) {
		this.q_item = q_item;
	}
}
