package me.zxks.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

public interface IBackService {
	/**
	 * 提供服务    将数据库查询的内容传递到前台
	 */
	public String Parameter(HttpServletResponse response,ArrayList<String> list)
			throws IOException;
}
