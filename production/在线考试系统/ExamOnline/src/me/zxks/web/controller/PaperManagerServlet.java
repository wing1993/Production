package me.zxks.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.dao.impl.MessageDaoImpl;
import me.zxks.dao.impl.QTypeDaoImpl;
import me.zxks.dao.impl.SubjectDaoImpl;
import me.zxks.db.service.PaperService;
import me.zxks.entity.Message;
import me.zxks.entity.QType;
import me.zxks.service.impl.BackParameterService;
import me.zxks.servlet.BaseServlet;

/**
 *  完成题库管理员"试卷管理"的功能
 */
@WebServlet("/PaperManagerServlet")
public class PaperManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	QType qtype=new QType();
	QTypeDaoImpl qTypeDao=new QTypeDaoImpl();   
	BackParameterService BPService=new BackParameterService();
	SubjectDaoImpl subjectDao = new SubjectDaoImpl();
	PaperService paper = new PaperService();
	Message message=new Message();
	MessageDaoImpl messageDao=new MessageDaoImpl();
    /**
     * @see BaseServlet#BaseServlet()
     */
    public PaperManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    //ajax添加试卷
    public String ajaxAddPaper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	//将前台数据封装到选择题实体类Single_choose
    	qtype.setPaper_name(request.getParameter("paper_name"));
    	qtype.setChapter(request.getParameter("chapter"));
    	qtype.setqTime(Integer.parseInt(request.getParameter("qTest_time")));
    	qtype.setqTest_time(request.getParameter("qTime"));
    	qtype.settNo((String) request.getSession().getAttribute("username"));
    	//根据传递过来的subjectName 获取subjectId
    	String subjectName=request.getParameter("subjectName");
    	String qType=request.getParameter("qType");
    	String qNum=request.getParameter("qNum");
    	String qScore=request.getParameter("qScore");
    	String [] qType1=new String[4];
    	qType1=qType.split(",");     //将String类型分割为String数组
    	String [] qNum1=qNum.split(",");
    	String [] qScore1=qScore.split(",");
    	int count=0;//标记入库记录条数
    	try{
    		subjectDao.getConn();
    		qtype.setSubjectId(subjectDao.find(subjectName));
    		//添加试卷   试卷名不能一样。。。
        	for(int i=0;i<qType1.length;i++) {
        		qTypeDao.getConn();// 连接数据库
        		qtype.setqType(qType1[i]);
        		qtype.setqNum(Integer.parseInt(qNum1[i]));
        		qtype.setqScore(Integer.parseInt(qScore1[i]));
        		if(qTypeDao.add(qtype)==1){
        			count+=1;
        		}
        		paper.AddPaper(qtype);//修改题目的paper_name
        	}
        	//插入的记录条数要等于题目类型的数量
    		if(count==qType1.length) response.getWriter().printf("提交成功！");
    		else response.getWriter().printf("提交失败！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			subjectDao.closeAll();
			qTypeDao.closeAll();
		}
    	return null;
    }
    //发布消息
    public String addMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	
    	message.setMessage_title(request.getParameter("message_title"));
    	message.setMessage_content(request.getParameter("message_content"));
    	message.setMessage_authorId((String) request.getSession().getAttribute("username")); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    	message.setMessage_time(dateFormat.format(new Date()));//获取当前日期
    	//MessageDaoImpl messageDao=new MessageDaoImpl();
    	try {
			messageDao.getConn();
	    	if(messageDao.addMessage(message)==1){
	    		response.getWriter().print("消息发布成功！");
			}else {response.getWriter().print("消息发布失败！");}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			messageDao.closeAll();
		}
    	return null;
    }
    //显示试卷信息
    public void showPaper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try{
    		//通过session  获取发布消息的人的authorId
    		qtype.settNo((String) request.getSession().getAttribute("username"));
    		//根据tNo获取消息内容
        	qTypeDao.getConn();
        	ResultSet rs=qTypeDao.showPaper(qtype);
    		BPService.Parameter2(response, rs);
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			qTypeDao.closeAll();
		}
    }
    //试卷信息修改 修改试卷时间跟考试时长 
    public void changePaper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try{
    		qtype.setPaper_name(request.getParameter("paper_name"));
    		qtype.setqTime(Integer.parseInt(request.getParameter("qTest_time")));
    		qtype.setqTest_time(request.getParameter("qTime"));
    		qTypeDao.getConn();
    		if(qTypeDao.changePaper(qtype)>=1){
    			response.getWriter().print("修改成功！");}
    		else {response.getWriter().print("修改失败");}
    	}catch (Exception e){
    			e.printStackTrace();
		}finally{
			qTypeDao.closeAll();
		}
    }
    //试卷信息删除之后修改对应的题型的paper_name
    public void deletePaper(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try{
    		qtype.setPaper_name(request.getParameter("paper_name"));
    		qTypeDao.getConn();
    		String sql="delete from question_type where paper_name=?";
    		qTypeDao.delete(qtype, sql);
    		paper.DeletePaper(qtype);//修改对应题型的paper_name
    	}catch (Exception e){
    		e.printStackTrace();
		}finally{
    		qTypeDao.closeAll();
		}
    }
    //显示所有消息
    public void showMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try{
    		//通过session  获取发布消息的人的authorId
    		message.setMessage_authorId((String) request.getSession().getAttribute("username"));
        	//根据message_authorId获取消息内容
    		//MessageDaoImpl messageDao=new MessageDaoImpl();
        	messageDao.getConn();
        	ResultSet rs=messageDao.showMessage(message);
    		BPService.Parameter2(response, rs);
    	} catch (Exception e) {
			e.printStackTrace();
		}finally{
			messageDao.closeAll();
		}
    }
    //修改消息时  弹出来的框中根据消息标题显示内容
    public void getMessageContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	message.setMessage_title(request.getParameter("message_title"));
    	try {
			messageDao.getConn();
			ResultSet rs=messageDao.getMessageId(message);
			ArrayList<String> list=new ArrayList<String>();
			//只有一条记录
			while(rs.next()){
				list.add(rs.getString(1));//返回消息id
				list.add(rs.getString(2));//返回消息内容
			}
			BPService.Parameter(response,list );
		} catch (Exception e) {
			System.out.println("查找已发布的消息出错！异常如下：");
			e.printStackTrace();
		}finally{
			messageDao.closeAll();
		}
    }
    //根据message_title删除消息
    public void deleteMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	message.setMessage_title(request.getParameter("message_title"));
    	try{
    		messageDao.getConn();
    		if(messageDao.deleteMessage(message)==1)
    			response.getWriter().print("消息删除成功！");
    		else response.getWriter().print("消息删除失败！");
    	}catch(Exception e){
    		System.out.println("删除消息出错！异常如下：");
    		e.printStackTrace();
    	}finally{
			messageDao.closeAll();
		}
    }
    //修改消息
    public void updateMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	message.setMessage_title(request.getParameter("message_title"));
    	message.setMessage_id(Integer.parseInt(request.getParameter("message_id")));
    	message.setMessage_content(request.getParameter("message_content"));
    	System.out.println("id====="+Integer.parseInt(request.getParameter("message_id")));
    	try{
    		messageDao.getConn();
    		if(messageDao.updateMessage(message)==1)
    			response.getWriter().print("消息保存成功！");
    		else response.getWriter().print("消息保存失败！");
    	}catch(Exception e){
    		System.out.println("删除消息出错！异常如下：");
    		e.printStackTrace();
    	}finally{
			messageDao.closeAll();
		}
    }
}
