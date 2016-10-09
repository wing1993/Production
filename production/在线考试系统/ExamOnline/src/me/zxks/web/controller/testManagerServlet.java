package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.CompletionDaoImpl;
import me.zxks.dao.impl.Single_chooseDaoImpl;
import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.dao.impl.Subjective_itemDaoImpl;
import me.zxks.dao.impl.TfngDaoImpl;
import me.zxks.entity.Completion;
import me.zxks.entity.Single_choose;
import me.zxks.entity.Subjective_item;
import me.zxks.entity.Tfng;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;
import me.zxks.web.formbean.PropertyChange;

/**
 * 完成题库管理员"试题管理"的功能
 */

@WebServlet("/testManagerServlet")
public class testManagerServlet extends BaseServlet {
	
	
	BackParameterService BPService=new BackParameterService();
	SubjectDaoImpl subjectDao = new SubjectDaoImpl();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public testManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //ajax添加单项选择题记录
    public String ajaxAddSingle_choose(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	Single_choose choose=new Single_choose();
    	Single_chooseDaoImpl chooseDao=new Single_chooseDaoImpl();
    	//将前台数据封装到选择题实体类Single_choose
    	choose.setChoice_content(request.getParameter("choice_content"));
    	choose.setChoiceA(request.getParameter("choiceA"));
    	choose.setChoiceB(request.getParameter("choiceB"));
    	choose.setChoiceC(request.getParameter("choiceC"));
    	choose.setChoiceD(request.getParameter("choiceD"));
    	choose.setChoice_answer(request.getParameter("choice_answer"));
    	choose.setChapter(request.getParameter("chapter"));		
    	//根据传递过来的subjectName 获取subjectId
    	String subjectName=request.getParameter("subjectName");
    	try{
    		subjectDao.getConn();
    		choose.setSubjectId(subjectDao.find(subjectName));
    		//添加单项选择试题
			chooseDao.getConn();// 连接数据库
			if(chooseDao.add(choose)==1){
				response.getWriter().print("试题添加成功！");
			}else {response.getWriter().print("试题添加失败！");}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			chooseDao.closeAll();
		}
		return null;
    }
    //ajax添加填空题记录
    public String ajaxAddCompletion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	Completion com=new Completion();
    	CompletionDaoImpl completionDao=new CompletionDaoImpl();
    	//将前台数据封装到填空题实体类Completion
    	com.setCompletion_content(request.getParameter("completion_content"));
    	com.setCompletion_answer(request.getParameter("completion_answer"));
    	com.setChapter(request.getParameter("chapter"));		
    	//根据传递过来的subjectName 获取subjectId
    	String subjectName=request.getParameter("subjectName");
    	try{
    		subjectDao.getConn();
    		com.setSubjectId(subjectDao.find(subjectName));
    		//添加填空题
			completionDao.getConn();// 连接数据库
			if(completionDao.add(com)==1){
				response.getWriter().print("试题添加成功！");
			}else {response.getWriter().print("试题添加失败！");}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			completionDao.closeAll();
		}
		return null;
    }
    //ajax添加判断题
    public String ajaxAddTfng(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	Tfng tfng=new Tfng();
    	TfngDaoImpl completionDao=new TfngDaoImpl();
    	PropertyChange tf=new PropertyChange();//将正确T、错误F分别转换为1,0存储
    	//将前台数据封装到判断题实体类Tfng
    	tfng.setTfng_content(request.getParameter("tfng_content"));
    	tfng.setTfng_answer(tf.TFChangeInt(request.getParameter("tfng_answer")));
    	tfng.setChapter(request.getParameter("chapter"));		
    	//根据传递过来的subjectName 获取subjectId
    	String subjectName=request.getParameter("subjectName");
    	try{
    		subjectDao.getConn();
    		tfng.setSubjectId(subjectDao.find(subjectName));
    		//添加判断题
			completionDao.getConn();// 连接数据库
			if(completionDao.add(tfng)==1){
				response.getWriter().print("试题添加成功！");
			}else {response.getWriter().print("试题添加失败！");}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			completionDao.closeAll();
		}
		return null;
    }
    //ajac添加主观题
    public String ajaxAddItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	Subjective_item item=new Subjective_item();
    	Subjective_itemDaoImpl itemDao = new Subjective_itemDaoImpl();
    	//将前台数据封装到主观题实体类Subjective_item
    	item.setItem_content(request.getParameter("item_content"));
    	item.setItem_answer(request.getParameter("item_answer"));
    	item.setChapter(request.getParameter("chapter"));		
    	//根据传递过来的subjectName 获取subjectId
    	String subjectName=request.getParameter("subjectName");
    	try{
    		subjectDao.getConn();
    		item.setSubjectId(subjectDao.find(subjectName));
    		//添加主观题
			itemDao.getConn();// 连接数据库
			if(itemDao.add(item)==1){
				response.getWriter().print("试题添加成功！");
			}else {response.getWriter().print("试题添加失败！");}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			itemDao.closeAll();
		}
		return null;
    }
    //显示单项选择题
	public void ajaxShowChoose(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		Single_chooseDaoImpl chooseDao=new Single_chooseDaoImpl();
    	try{
    		//将SubjectName转换为subjectId 并放到choose对象中
        	subjectDao.getConn();
        	Single_choose choose=new Single_choose();
        	choose.setSubjectId(subjectDao.find(request.getParameter("subjectName")));
        	//通过科目Id查询出对应的选择题
    		chooseDao.getConn();
    		ResultSet rs=chooseDao.Show(choose);
    		BPService.Parameter2(response, rs);
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			chooseDao.closeAll();
		}
    }
	
	//显示填空题
	public void ajaxShowCompletion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
	    try{
	    	//将SubjectName转换为subjectId 并放到com对象中
	       	subjectDao.getConn();
	       	Completion com=new Completion();
	       	com.setSubjectId(subjectDao.find(request.getParameter("subjectName")));
	       	//通过科目Id查询出对应的填空题
	   		CompletionDaoImpl completionDao=new CompletionDaoImpl();
	   		completionDao.getConn();
	    	ResultSet rs=completionDao.Show(com);
	    	BPService.Parameter2(response, rs);
	   	} catch (Exception e) {
			e.printStackTrace();
		}
		/*try{
	    	//将SubjectName转换为subjectId 并放到com对象中
	       	subjectDao.getConn();
	       	String subjectId=subjectDao.find(request.getParameter("subjectName"));
	       	//通过科目Id查询出对应的填空题
	   		CompletionDaoImpl completionDao=new CompletionDaoImpl();
	   		completionDao.getConn();
	   		ArrayList<Completion> list = new ArrayList<Completion>();
	   		list=completionDao.getAll(subjectId);
	   		response.getWriter().print(list);
	   	} catch (Exception e) {
			e.printStackTrace();
		}*/
    }
	//显示判断题
	public void ajaxShowTfng(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
	    try{
	    	//将SubjectName转换为subjectId 并放到tfng对象中
	       	subjectDao.getConn();
	       	Tfng tfng=new Tfng();
	       	tfng.setSubjectId(subjectDao.find(request.getParameter("subjectName")));
	       	//通过科目Id查询出对应的判断题
	   		TfngDaoImpl tfngDao=new TfngDaoImpl();
	   		tfngDao.getConn();
	    	ResultSet rs=tfngDao.Show(tfng);
	    	BPService.Parameter2(response, rs);
	   	} catch (Exception e) {
			e.printStackTrace();
		}
    }
	//显示主观题
	public void ajaxShowItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
	    try{
	    	//将SubjectName转换为subjectId 并放到item对象中
	       	subjectDao.getConn();
	       	Subjective_item item=new Subjective_item();
	       	item.setSubjectId(subjectDao.find(request.getParameter("subjectName")));
	       	//通过科目Id查询出对应的主观题
	   		Subjective_itemDaoImpl tfngDao=new Subjective_itemDaoImpl();
	   		tfngDao.getConn();
	    	ResultSet rs=tfngDao.Show(item);
	    	BPService.Parameter2(response, rs);
	   	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //删除单项选择题
	public void delete_choose(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try{
        	Single_choose choose=new Single_choose();
        	choose.setChoice_content(request.getParameter("choice_content"));
    		Single_chooseDaoImpl chooseDao=new Single_chooseDaoImpl();
    		chooseDao.getConn();
    		chooseDao.delete(choose);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
	//删除填空题
	public void delete_completion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		try{
        	Completion com=new Completion();
        	com.setCompletion_content(request.getParameter("completion_content"));
        	CompletionDaoImpl completionDao=new CompletionDaoImpl();
        	completionDao.getConn();
        	completionDao.delete(com);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除判断题
	public void delete_tfng(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		try{
        	Tfng tfng=new Tfng();
        	tfng.setTfng_content(request.getParameter("tfng_content"));
        	TfngDaoImpl TfngDao=new TfngDaoImpl();
        	TfngDao.getConn();
        	TfngDao.delete(tfng);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除主观题
	public void delete_item(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		try{
        	Subjective_item item=new Subjective_item();
        	item.setItem_content(request.getParameter("item_content"));
        	Subjective_itemDaoImpl ItemDao=new Subjective_itemDaoImpl();
        	ItemDao.getConn();
        	ItemDao.delete(item);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//	根据题目内容获取Id
	public void getQuestionId(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String qType=request.getParameter("qType");
		String content=request.getParameter("content");
		System.out.println(qType+"bb"+content);
		if(qType.equals("填空题")){
			CompletionDaoImpl completionDao=new CompletionDaoImpl();
			System.out.println(completionDao.getId(content));
			response.getWriter().print(completionDao.getId(content));
		}
		if(qType.equals("判断题")){
			TfngDaoImpl tfngDao=new TfngDaoImpl();
			response.getWriter().print(tfngDao.getId(content));
		}if(qType.equals("主观题")){
			Subjective_itemDaoImpl itemDao=new Subjective_itemDaoImpl();
			response.getWriter().print(itemDao.getId(content));
		}
	}
   //	保存题目的修改信息
	public void saveQuestion(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String qType=request.getParameter("qType");
		String content=request.getParameter("content");
		String answer=request.getParameter("answer");
		System.out.println(qType+" "+content+"  "+answer);
		int qTypeId=Integer.parseInt(request.getParameter("qTypeId"));
		if(qType.equals("填空题")){
			CompletionDaoImpl completionDao=new CompletionDaoImpl();
			System.out.println(completionDao.update(qTypeId,content,answer));
			response.getWriter().print(completionDao.update(qTypeId,content,answer));
		}
		if(qType.equals("判断题")){
			int answer1=new PropertyChange().TFChangeInt(answer);
			TfngDaoImpl tfngDao=new TfngDaoImpl();
			response.getWriter().print(tfngDao.update(qTypeId,content,answer1));
		}if(qType.equals("主观题")){
			Subjective_itemDaoImpl itemDao=new Subjective_itemDaoImpl();
			response.getWriter().print(itemDao.update(qTypeId,content,answer));
		}
	}
}
