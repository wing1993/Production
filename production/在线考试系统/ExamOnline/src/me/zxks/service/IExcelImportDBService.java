package me.zxks.service;

import java.io.IOException;
import java.io.InputStream;

public interface IExcelImportDBService {
	/**
	 * 根据已建立的文件流跟表类型  读取Excel的内容到数据库
	 * @param inputStream
	 * @param ImportType
	 * @return
	 * @throws IOException
	 */
	public String  read(InputStream inputStream,String ImportType) throws IOException;
}
