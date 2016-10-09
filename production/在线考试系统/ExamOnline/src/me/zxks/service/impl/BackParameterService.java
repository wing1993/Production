package me.zxks.service.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

//import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import me.zxks.entity.Message;
import me.zxks.entity.QType;
import me.zxks.entity.Single_choose;
import me.zxks.service.IBackService;


public class BackParameterService implements IBackService {
	/**
	 * 将数据库查询的字符串(比如说结果集只有一个列属性)作为JSON数组数据传递到前台
	 */
	 public String Parameter(HttpServletResponse response,ArrayList<String> list)
				throws IOException { 
		  response.getWriter().print(JSONArray.fromObject(list));
		  response.getWriter().flush();
		  response.getWriter().close();
		  return null;
	 }
	 /**
		 * 
		 * (wing)
		 */
		public String Parameter(HttpServletResponse response, int r)
				throws IOException {
			response.getWriter().print(r);
			response.getWriter().flush();
			response.getWriter().close();
			return null;
		}

		/**
		 * 函数重载(wing)
		 */
		public void Parameter(HttpServletResponse response, ArrayList<Message> list)
				throws IOException {
			response.getWriter().print(JSONArray.fromObject(list));
			response.getWriter().flush();
			response.getWriter().close();

		}
	 /**
	  * 将数据库查询的内容(包含多个列属性)作为JSONObject对象数据传递到前台
	  */
	/*public void Parameter1(HttpServletResponse response,
			ArrayList<Single_choose> list) throws IOException {
		response.getWriter().print(JSONObject.fromObject(list));
		
		response.getWriter().flush();
		response.getWriter().close();
	}*/
		
		public void Parameter1(HttpServletResponse response, ArrayList<QType> list)
				throws IOException {
			response.getWriter().print(JSONArray.fromObject(list));
			response.getWriter().flush();
			response.getWriter().close();
		}	
	 /**
	  * ResultSet为多行多列   将每一行作为一个JSONObject对象数据传递到前台
	  */
	public void Parameter2(HttpServletResponse response,
			ResultSet rs) throws IOException, SQLException {
	   // json数组
	   JSONArray array = new JSONArray();
	   //得到结果集的结构（列数、列名等）
	   ResultSetMetaData metaData = rs.getMetaData();
	   // 获取列数
	   int columnCount = metaData.getColumnCount();	
	   // 遍历ResultSet中的每条数据
	   while (rs.next()) {
	        JSONObject jsonObj = new JSONObject();
	        // 遍历每一列
	        for (int i = 1; i <= columnCount; i++) {
	            String columnName =metaData.getColumnLabel(i);//列名
	            String value = rs.getString(columnName);      //列值
	            jsonObj.put(columnName,value);
	        } 
	        array.add(jsonObj); 
	    }
		response.getWriter().print(array);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
