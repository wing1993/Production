<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>excel导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
		
</head>

<script language="javascript">
function aaa(){
	$.post("ExcelImportDBService",{method:"goodsFromExcel"},function(str){alert(str);});
}
/* function clientExcel(){    
	var file_up=document.getElementById("client");
	if(file_up.value=="")
    {
         alert("不能为空");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("请确认导入位置是否正确"+fileExcel)==true)
	{
         document.forms[0].action="excelclientFromAction_clientFromExcel.action?fileExcel="+fileExcel;  
         document.forms[0].submit();   
	}
	else{
		return false;
	}
 } 
function adminExcel(){    
	var file_up=document.getElementById("admin");  
	if(file_up.value=="")
    {
         alert("不能为空");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("请确认导入位置是否正确"+fileExcel)==true)
	{
         document.forms[0].action="exceladminFromAction_adminFromExcel.action?fileExcel="+fileExcel;  
         document.forms[0].submit();   
	}
	else{
		return false;
	}
 } 
function medExcel(){    
	var file_up=document.getElementById("med");  
	if(file_up.value=="")
    {
         alert("不能为空");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("请确认导入位置是否正确"+fileExcel)==true)
	{
         document.forms[0].action="excelmedFromAction_medFromExcel.action?fileExcel="+fileExcel;  
         document.forms[0].submit();   
	}
	else{
		return false;
	}
 } 
function sellExcel(){    
	var file_up=document.getElementById("sell");   
	if(file_up.value=="")
    {
         alert("不能为空");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("请确认导入位置是否正确"+fileExcel)==true)
	{
         document.forms[0].action="excelsellFromAction_sellFromExcel.action?fileExcel="+fileExcel;  
         document.forms[0].submit();   
	}
	else{
		return false;
	}
 }  */
</script>
<%-- <% 
String cue = (String)request.getAttribute("cue"); 
%> 
<script language="javascript">
function cue(){ 
var cue = '<%=cue%>'; 
if(cue!="null"){ 
alert(cue); 
} 
} 
</script>--%>
<body >
	<div >
		
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				align="center">
				<tr>
					<td class="td_title1">·当前位置： &gt;&gt;数据报表 &gt;&gt; excel导入</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" height="50"><br>
				   <table border="0" align="center" width="700">
				   <tr>
				       <td>
                      	<form action="excelclientFromAction_clientFromExcel.action"
							method="post" id="upload1">
							导入顾客Excel到数据库<input type="file" id="client" title="请选择导入位置" /> <input
								type="button" value="确认导入" onclick="aaa()" />
								</form>
					    </td>
					</tr>
					<tr>
				       <td>
                      	<form action="exceladminFromAction_adminFromExcel.action"
							method="post" id="upload2">
							导入工作员Excel到数据库<input type="file" id="admin" title="请选择导入位置" /> <input
								type="button" value="确认导入" onclick="return adminExcel()" />
								</form>
					    </td>
					</tr>
					
					<tr>
				       <td>
                      	<form action="excelmedFromAction_medFromExcel.action"
							method="post" id="upload3">
							导入医药Excel到数据库<input type="file" id="med" title="请选择导入位置" /> <input
								type="button" value="确认导入" onclick="return medExcel()" />
								</form>
					    </td>
					</tr>
					
					<tr>
				       <td>
                      	<form action="excelsellFromAction_sellFromExcel.action"
							method="post" id="upload4">
							导入订单Excel到数据库<input type="file" id="sell" title="请选择导入位置" /> 
							<input	type="button" value="确认导入" onclick="return sellExcel()" />
								</form>
					    </td>
					</tr>
			     </table>
        
		
		
			</td>
			</tr>
			</table>
		</div>
</body>
</html>
