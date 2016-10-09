package me.zxks.db.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.zxks.dao.impl.CompletionDaoImpl;
import me.zxks.dao.impl.QTypeDaoImpl;
import me.zxks.dao.impl.Single_chooseDaoImpl;
import me.zxks.dao.impl.Subjective_itemDaoImpl;
import me.zxks.dao.impl.TfngDaoImpl;
import me.zxks.entity.QType;

public class PaperService {
	public void AddPaper(QType qtype) throws SQLException, ClassNotFoundException,
	InstantiationException, IllegalAccessException{
		String paper_name=qtype.getPaper_name(); //获取试卷paper_name 
		if(qtype.getqType().equals("单项选择")){
			int qNum=qtype.getqNum();
			Single_chooseDaoImpl chooseDao =new Single_chooseDaoImpl();
			chooseDao.getConn();
			ResultSet rs=chooseDao.select(qtype,qNum);//查询等同数目的单项选择题
			while(rs.next()){
				//将题目的paper_name进行修改
				chooseDao.updatePaper_name(paper_name,Integer.parseInt(rs.getString(1)));
			}
		}
		else if(qtype.getqType().equals("判断题")){
			int qNum=qtype.getqNum();
			TfngDaoImpl tfngDao =new TfngDaoImpl();
			tfngDao.getConn();
			ResultSet rs=tfngDao.select(qtype,qNum);//查询等同数目的判断题
			while(rs.next()){
				//将题目的paper_name进行修改
				tfngDao.updatePaper_name(paper_name,Integer.parseInt(rs.getString(1)));
			}
		}
		else if(qtype.getqType().equals("填空题")){
			int qNum=qtype.getqNum();
			CompletionDaoImpl completionDao =new CompletionDaoImpl();
			completionDao.getConn();
			ResultSet rs=completionDao.select(qtype,qNum);//查询等同数目的判断题
			while(rs.next()){
				//将题目的paper_name进行修改
				completionDao.updatePaper_name(paper_name,Integer.parseInt(rs.getString(1)));
			}
		}
		else if(qtype.getqType().equals("主观题")){
			int qNum=qtype.getqNum();
			Subjective_itemDaoImpl itemDao =new Subjective_itemDaoImpl();
			itemDao.getConn();
			ResultSet rs=itemDao.select(qtype,qNum);//查询等同数目的判断题
			while(rs.next()){
				//将题目的paper_name进行修改
				itemDao.updatePaper_name(paper_name,Integer.parseInt(rs.getString(1)));
			}
		}
	}
	public void DeletePaper(QType qtype) throws SQLException, ClassNotFoundException,
	InstantiationException, IllegalAccessException{
		QTypeDaoImpl qTypeDao=new QTypeDaoImpl();
		qTypeDao.getConn();
		String sql1="update single_choose set paper_name = null where paper_name=?";
		String sql2="update completion set paper_name = null where paper_name=?";
		String sql3="update tfng set paper_name = null where paper_name=?";
		String sql4="update subjective_item set paper_name = null where paper_name=?";
		qTypeDao.delete(qtype,sql1);
		qTypeDao.delete(qtype,sql2);
		qTypeDao.delete(qtype,sql3);
		qTypeDao.delete(qtype,sql4);
	}
	
}
