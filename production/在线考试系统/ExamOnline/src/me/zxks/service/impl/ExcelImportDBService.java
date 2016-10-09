package me.zxks.service.impl;

import java.io.IOException; 
import java.io.InputStream;   
import java.sql.SQLException;

import me.zxks.dao.impl.ClassDaoImpl;
import me.zxks.dao.impl.CompletionDaoImpl;
import me.zxks.dao.impl.StudentDaoImpl;
import me.zxks.dao.impl.Subjective_itemDaoImpl;
import me.zxks.dao.impl.TfngDaoImpl;
import me.zxks.entity.Completion;
import me.zxks.entity.Student;
import me.zxks.entity.Subjective_item;
import me.zxks.entity.Tfng;
import me.zxks.web.formbean.PropertyChange;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  

public class ExcelImportDBService {
	int  result=0;//导入结果
	/**
	 * 通过文件流读取excel表格中数据
	 * @param inputStream
	 * @param ImportType
	 * @return 返回执行结果
	 */
	public int  read(InputStream inputStream,String ImportType) throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{  
		//初始化整个Excel   
		HSSFWorkbook webwork=new HSSFWorkbook(inputStream);       
		//打印获取表格（共有多少个工作表）   
		System.out.println(webwork.getNumberOfSheets()); 

        //循环工作表   
		for (int i = 0; i < webwork.getNumberOfSheets(); i++) 
		{    
			HSSFSheet sheet=webwork.getSheetAt(i);    
			//通过具体的工作表，获取表中有多少行    
			System.out.println("共有"+sheet.getLastRowNum()+"行");      
			//循环sheet表中有数据库的每一行       忽略第一行 
			for (int j = 1; j <= sheet.getLastRowNum(); j++) { 
				HSSFRow row=sheet.getRow(j);  
				//即使当前行空数据  未循环结束之前都继续 
				if(row.getCell(0)==null){      
					continue;    
				}  
				System.out.println("共有"+row.getLastCellNum()+"列");   
				//循环该sheet中数据的每一行     将每一列数据导入数据库      
				if(ImportType.equals("Student")){    //假如是学生表的导入
					StudentDaoImpl studentDao=new StudentDaoImpl();
					Student student=new Student();
					ClassDaoImpl classDao=new ClassDaoImpl();
					//性别  男-->1   女-->0
					PropertyChange pc=new PropertyChange();
					student.setStuNo(row.getCell(0).toString());
					//学生学号不重复则插入
					if(studentDao.check(student)){
						student.setStuName(row.getCell(1).toString());
						student.setStuGender(pc.GenderChangeInt(row.getCell(2).toString()));
						//获取第4列的单元格数据 年级  转换为int类型
						HSSFCell cell = row.getCell(3);
						int grade1=(int)(cell.getNumericCellValue());
						String grade2=String.valueOf(grade1);
					    String className=row.getCell(4).toString();
					    //根据班级名称   年级   获取班级编号
						student.setClassNo(classDao.select(className,grade2));
						System.out.println("查出classNo为"+student.getClassNo());
						//将student插入数据库
						result = studentDao.insert(student);
					}
				}
				else if(ImportType.equals("Completion")){    //填空题数据
					Completion completion = new Completion ();
					CompletionDaoImpl completionDao=new CompletionDaoImpl();
					completion.setCompletion_content(row.getCell(0).toString());
					completion.setCompletion_answer(row.getCell(1).toString());
					completion.setSubjectId(row.getCell(2).toString());
					completion.setChapter(row.getCell(3).toString());     
					result=completionDao.add(completion);
				}
				else if(ImportType.equals("Tfng")){    //判断题数据
					Tfng tfng=new Tfng();
					TfngDaoImpl tfngDao=new TfngDaoImpl();
					tfng.setTfng_content(row.getCell(0).toString());
					tfng.setTfng_answer(Integer.parseInt(row.getCell(1).toString()));
					tfng.setSubjectId(row.getCell(2).toString());
					tfng.setChapter(row.getCell(3).toString());  
					result=tfngDao.add(tfng);
				}
				else if(ImportType.equals("Subjective_item")){   //主观题数据
					Subjective_item item=new Subjective_item();
					Subjective_itemDaoImpl tfngDao=new Subjective_itemDaoImpl();
					item.setItem_content(row.getCell(0).toString());
					item.setItem_answer(row.getCell(1).toString());
					item.setSubjectId(row.getCell(2).toString());
					item.setChapter(row.getCell(3).toString());  
					result=tfngDao.add(item);
				}
			}		
		}	
		inputStream.close();//关闭文件流
		return result;
	}
}
