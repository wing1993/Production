<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>excel����</title>
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
         alert("����Ϊ��");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("��ȷ�ϵ���λ���Ƿ���ȷ"+fileExcel)==true)
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
         alert("����Ϊ��");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("��ȷ�ϵ���λ���Ƿ���ȷ"+fileExcel)==true)
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
         alert("����Ϊ��");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("��ȷ�ϵ���λ���Ƿ���ȷ"+fileExcel)==true)
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
         alert("����Ϊ��");
         file_up.focus();
         file_up.select();
	     return false;
    }
	file_up.select();    
	file_up.blur();     
	var fileExcel=document.selection.createRange().text; 
	if(confirm("��ȷ�ϵ���λ���Ƿ���ȷ"+fileExcel)==true)
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
					<td class="td_title1">����ǰλ�ã� &gt;&gt;���ݱ��� &gt;&gt; excel����</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF" height="50"><br>
				   <table border="0" align="center" width="700">
				   <tr>
				       <td>
                      	<form action="excelclientFromAction_clientFromExcel.action"
							method="post" id="upload1">
							����˿�Excel�����ݿ�<input type="file" id="client" title="��ѡ����λ��" /> <input
								type="button" value="ȷ�ϵ���" onclick="aaa()" />
								</form>
					    </td>
					</tr>
					<tr>
				       <td>
                      	<form action="exceladminFromAction_adminFromExcel.action"
							method="post" id="upload2">
							���빤��ԱExcel�����ݿ�<input type="file" id="admin" title="��ѡ����λ��" /> <input
								type="button" value="ȷ�ϵ���" onclick="return adminExcel()" />
								</form>
					    </td>
					</tr>
					
					<tr>
				       <td>
                      	<form action="excelmedFromAction_medFromExcel.action"
							method="post" id="upload3">
							����ҽҩExcel�����ݿ�<input type="file" id="med" title="��ѡ����λ��" /> <input
								type="button" value="ȷ�ϵ���" onclick="return medExcel()" />
								</form>
					    </td>
					</tr>
					
					<tr>
				       <td>
                      	<form action="excelsellFromAction_sellFromExcel.action"
							method="post" id="upload4">
							���붩��Excel�����ݿ�<input type="file" id="sell" title="��ѡ����λ��" /> 
							<input	type="button" value="ȷ�ϵ���" onclick="return sellExcel()" />
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
