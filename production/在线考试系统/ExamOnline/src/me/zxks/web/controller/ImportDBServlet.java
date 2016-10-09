package me.zxks.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.zxks.service.impl.ExcelImportDBService;
import me.zxks.servlet.BaseServlet;

/**
 * Servlet implementation class ImportDBServlet
 */
@WebServlet("/ImportDBServlet")
public class ImportDBServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void ExcelImportDB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	try {
	    	//获取文件路径
	    	String filePath=request.getParameter("filePath");
    		//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
            String filename = filePath.substring(filePath.lastIndexOf("\\")+1);
            filePath="D:\\"+filename;
	    	//获取要文件要导入的表的类型   如Student表  
	    	String ImportType=request.getParameter("ImportType");
	    	//文件系统File
	    	File file=new File(filePath);
	    	//打开一个到file的连接
	    	FileInputStream fis=new FileInputStream(file);
	    	ExcelImportDBService eService=new ExcelImportDBService();
	    	int result;
			result = eService.read(fis,ImportType);
			if(result==1){
				response.getWriter().print("导入成功！");
			}else{
				response.getWriter().print("导入失败！");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
}
