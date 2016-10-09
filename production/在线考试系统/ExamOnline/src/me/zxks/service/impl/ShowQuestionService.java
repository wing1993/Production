package me.zxks.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.impl.DisplayByPages;
import me.zxks.dbOperate.DBOper;

public class ShowQuestionService extends DBOper{
	public ResultSet show(String subjectId,String qType,int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{

		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		ResultSet rs = null;
		if(qType.equals("单项选择")){
    		String sql= "select * from single_choose where subjectId=? order by choice_id limit ?,?";
			 rs=dbPages.selectByPage(sql,subjectId,page);
		} 
		else if(qType.equals("填空题")){
    		String sql= "select * from completion where subjectId=? order by completion_id limit ?,?";
			rs=dbPages.selectByPage(sql,subjectId,page);
    	}
		else if(qType.equals("判断题")){
    		String sql= "select * from tfng where subjectId=? order by tfng_id limit ?,?";
			 rs=dbPages.selectByPage(sql,subjectId,page);
    	}
		else if(qType.equals("主观题")){
    		String sql= "select * from subjective_item where subjectId=? order by item_id limit ?,?";
			 rs=dbPages.selectByPage(sql,subjectId,page);
    	}
		
		return rs;
	}
	/*public ResultSet showTeacher(String instituteName,int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		String sql="select tNo,tName,tGender,tRole,instituteName from institute,teacher" +
				" where institute.instituteId=teacher.instituteId order by tNo limit ?,?";
		ResultSet rs=dbPages.selectPageTeacher(sql,page);
		return rs;
	}*/
	public ResultSet showTeacher(String instituteName, int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		System.out.println(instituteName+"  1221 ");
		System.out.println(instituteName!=null+"判断是否为空");
		if(instituteName!=null){
			String sql="select tNo,tName,tGender,tRole,instituteName from institute,teacher where instituteName=? " +
				" and institute.instituteId=teacher.instituteId order by tNo limit ?,?";
			ResultSet rs=dbPages.selectPageTeacher(sql,instituteName,page);
			return rs;
		}else{
			System.out.println("jinlai l");
			String sql="select tNo,tName,tGender,tRole,instituteName from institute,teacher where  " +
					"  institute.instituteId=teacher.instituteId order by tNo limit ?,?";
			ResultSet rs=dbPages.selectPageTeacher(sql,page);
			return rs;
		}
	}
	public ResultSet showStudentPaper(String paper_name,String classNo, int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		String sql="select a.stuNo,stuName,stuGender,test_time,paper_name,score from " +
				"(select * from student where student.classNo=?) a LEFT OUTER JOIN score on " +
				"a.stuNo=score.stuNo and paper_name=?  ,class where a.classNo=class.classNo  order by stuNo limit ?,?";
		ResultSet rs=dbPages.selectPagePaper(sql,paper_name,classNo,page);
		return rs;
	}
	public int getCount(String subjectId,String qType) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		int num=0;
		if(qType.equals("单项选择")){
			String sql="select count(*) from single_choose where subjectId=?";
			 num=dbPages.getdbCount(sql,subjectId);
		} 
		else if(qType.equals("填空题")){
    		String sql= "select count(*) from completion where subjectId=?";
			num=dbPages.getdbCount(sql,subjectId);
    	}
		else if(qType.equals("判断题")){
    		String sql= "select count(*) from tfng where subjectId=?";
			 num=dbPages.getdbCount(sql,subjectId);
    	}
		else if(qType.equals("主观题")){
    		String sql= "select count(*) from subjective_item where subjectId=?";
			 num=dbPages.getdbCount(sql,subjectId);
    	}
		return num;
	}
	public int getCount_stu(String instituteName,String className,String grade)throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		int num=0;
		String sql = "select count(*) from student,institute,class" +
				"  where student.classNo=class.classNo and class.instituteId=institute.instituteId and" +
				"  instituteName=? and className=? and grade=?";
		num=dbPages.getStuCount(sql, instituteName,className,grade);
		return num;
	}
	public int getCount_tea(String instituteName)throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		DisplayByPages dbPages=new DisplayByPages();
		dbPages.getConn();
		int num=0;
		System.out.println(instituteName+"选择的学院");
		String sql = "select count(*) from teacher,institute where teacher.instituteId=institute.instituteId and instituteName=?";
		num=dbPages.getTeaCount(sql,instituteName);
		return num;
	}
	
	
}
