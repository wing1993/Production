package me.zxks.web.formbean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;



public class DBPrametes {
	/** 
	* 从 DBParameter.properties文件获取连接数据库需要的参数
	*/ 
	private String server;
	private String db;
	private String user;
	private String pwd;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	//获取数据库连接的参数
	public void getDBPrametes() { 
		Properties prop = new Properties(); 
		InputStream fis =DBPrametes.class.getClassLoader().getResourceAsStream("DBParameter.properties");
		try { 
			prop.load(fis); 
			String server= prop.getProperty( "server" ).trim(); 
			String db= prop.getProperty( "db" ).trim();
			String user= prop.getProperty( "user" ).trim();
			String pwd= prop.getProperty( "pwd" ).trim();
			setServer(server);
			setDb(db);
			setUser(user);
			setPwd(pwd);
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
	
	//返回数据库连接的参数
	public ArrayList<String> outputDBPrameter(){
		ArrayList<String> list=new ArrayList<String>();
		list.add(getServer());
		list.add(getDb());
		list.add(getUser());
		list.add(getPwd());
		return (list);	
	}
}
