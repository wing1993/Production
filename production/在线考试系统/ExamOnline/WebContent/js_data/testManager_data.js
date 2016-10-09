 pagepc=parseInt(1);  //设置当前页(初始化)   全局的页数
 pages_total=parseInt(1);
 var change_str;//定义全局变量记录题型
/**
 * 我要出题 提交题目
 */
function click_addQuestion(){  
	if($("#question1").val()==""){
		alert("请输入题目内容！");
		return;
	}
	if(change_str=="单项选择"){
		if($("#choice1A").val()==""){
			alert("请输入A选项内容！");
			return;
		}
		if($("#choice1B").val()==""){
			alert("请输入B选项内容！");
			return;
		}
	}
	
	if(change_str!="单项选择"&&$("#answer1").val()==""){
		alert("请输入答案！");
		return;
	}
	//获取input中name="type"的用户数据    此为被选中的题目类型单选框
	var typeValue=$('input[name="type"]:checked').val();
	if(typeValue=="单项选择")
		Addsingle_choose();        //添加单项选择题
	else if(typeValue=="填空题")
		Addcompletion();			//添加填空题
	else if(typeValue=="判断题")
		Addtfng();					//添加判断题
	else if(typeValue=="主观题")
		Addsubjective_item();		//添加主观题
}
//添加单项选择题
function Addsingle_choose(){
	 var subjectValue = $("#subject1").val();
	 var chapterValue = $("#chapter1").val();
	 var questionValue = $("#question1").val();
	 var choiceAValue = $("#choice1A").val();
	 var choiceBValue = $("#choice1B").val();
	 var choiceCValue = $("#choice1C").val();
	 var choiceDValue = $("#choice1D").val();
	 var answerValue=$("#answer1").val();
	 $.post('testManagerServlet',{method:'ajaxAddSingle_choose',
		 choice_content:questionValue,choiceA:choiceAValue,choiceB:choiceBValue,
		 choiceC:choiceCValue,choiceD:choiceDValue,choice_answer:answerValue,
		 subjectName:subjectValue,chapter:chapterValue},
		 function(str){  //返回结果
			 //清空文本框内容
			 alert(str);
			 $("#question1").val('');
			 $("#choice1A").val('');
			 $("#choice1B").val('');
			 $("#choice1C").val('');
			 $("#choice1D").val('');
	});  	
}
//添加填空题
function Addcompletion(){
	 var subjectValue = $("#subject1").val();
	 var chapterValue = $("#chapter1").val();
	 var questionValue = $("#question1").val();
	 var answerValue=$("#answer1").val();
	 $.post('testManagerServlet',{method:'ajaxAddCompletion',
		 completion_content:questionValue,completion_answer:answerValue,
		 subjectName:subjectValue,chapter:chapterValue},
		 function(str){ //返回结果
			 alert(str);
			 //清空文本框内容
			 $("#question1").val('');
			 $("#answer1").val('');
	 });
}
//添加判断题
function Addtfng(){
	 var subjectValue = $("#subject1").val();
	 var chapterValue = $("#chapter1").val();
	 var questionValue = $("#question1").val();
	 var answerValue=$("#answer1").val();
	 $.post('testManagerServlet',{method:'ajaxAddTfng',
		 tfng_content:questionValue,tfng_answer:answerValue,
		 subjectName:subjectValue,chapter:chapterValue},
		 function(str){ //返回结果
			 alert(str);
			 //清空文本框内容
			 $("#question1").val('');
	 });
}
//添加主观题
function Addsubjective_item(){
	 var subjectValue = $("#subject1").val();
	 var chapterValue = $("#chapter1").val();
	 var questionValue = $("#question1").val();
	 var answerValue=$("#answer1").val();
	 $.post('testManagerServlet',{method:'ajaxAddItem',
		 item_content:questionValue,item_answer:answerValue,
		 subjectName:subjectValue,chapter:chapterValue},
		 function(str){ //返回结果
			 alert(str);
			 //清空文本框内容
			 $("#question1").val('');
	 });
}
//删除试题
function delete_question(obj){
	if($("#qType").val()=="单项选择")
	    if(confirm("确定删除该选择题吗？")){
		    var choice_contentValue=$(obj).parent().prev().prev().prev().text();
		    $(obj).parent().parent().remove();
		    $.post("testManagerServlet",{method:'delete_choose',choice_content:choice_contentValue});	
	    }
	if($("#qType").val()=="填空题")
		 if(confirm("确定删除该填空题吗？")){
			    var completion_contentValue=$(obj).parent().prev().prev().text();
			    $(obj).parent().parent().remove();
			    $.post("testManagerServlet",{method:'delete_completion',completion_content:completion_contentValue});	
		 }
	if($("#qType").val()=="判断题")
	    if(confirm("确定删除该判断题吗？")){
		    var tfng_contentValue=$(obj).parent().prev().prev().text();
		    $(obj).parent().parent().remove();
		    $.post("testManagerServlet",{method:'delete_tfng',tfng_content:tfng_contentValue});	
	    }
	if($("#qType").val()=="主观题")
		 if(confirm("确定删除该主观题吗？")){
			    var item_contentValue=$(obj).parent().prev().prev().text();
			    $(obj).parent().parent().remove();
			    $.post("testManagerServlet",{method:'delete_item',item_content:item_contentValue});	
		 }
}

/**
 * 我要出卷
 */
function click_addPaper(){  
	var paper_nameValue=$("#paper_title").val();
	var subjectValue=$("#subject4").val();
	var chapterValue=$("#chapter2").val();
	var qTimeValue=$("#qTime").val();
	var qTest_timeValue=$('input[name="test_time"]').val();
	var type = document.getElementsByName("question_type");
	var qNum1 = document.getElementsByName("question_num");
	var qScore = document.getElementsByName("weighting");
	var typeValue="";
	var qNumValue="";
	var qScoreValue="";
	for(var i in type) 
		if(type[i].checked) {
			typeValue+=(type[i].value+","); 
			qNumValue+=(qNum1[i].value+","); 
			qScoreValue+=(qScore[i].value+","); 
		}
	 $.post('PaperManagerServlet',{method:'ajaxAddPaper',
		 paper_name:paper_nameValue,subjectName:subjectValue,
		 chapter:chapterValue,qTest_time:qTest_timeValue,qTime:qTimeValue,
		 qType:typeValue,qNum:qNumValue,qScore:qScoreValue},
		 function(str){ 
			 alert(str);
			 //清空文本框内容
			 //$("#question_num1").val("");
			 document.getElementById("question_num1").value="";
			 document.getElementById("question_num2").value="";
			 document.getElementById("question_num3").value="";
			 document.getElementById("question_num4").value="";
			 document.getElementById('paper_title').value="";
			 document.getElementById('test_time').value="";
			 document.getElementById('weighting1').value="";
			 document.getElementById('weighting2').value="";
			 document.getElementById("weighting3").value="";
			 document.getElementById("weighting3").value="";
			 //将checkbox的勾选状态去掉
			 $("[name=question_type]:checkbox").prop("checked", false);
	 });
}
/**
 * 试卷修改
 */
//显示已有试卷
function ShowPaper(){
	$("#edit_paper_tb tr:not(:first)").empty();
	$.post('PaperManagerServlet',{method:'showPaper'},function(arry){
		var obj=eval('('+arry+')');
		for(var i=0;obj.length;i++){
			var opt="<tr class='showpaper1'><td>"+obj[i].paper_name+"</td><td>"+
			    obj[i].qTime+"</td><td>"+obj[i].qTest_time+"</td><td><a href='#'"+ 
		    	"onclick='edit_paper(this)'>修改</a>&nbsp;<a href='#' onclick='delete_paper(this)'>删除</a></td>";
			$('#edit_paper_tb').append(opt);
		}
	});
}
//修改试卷  修改db内容
function changePaper(){
	$.post('PaperManagerServlet',{method:'changePaper',paper_name:$("#po-paper-name").text(),
		qTime:$("#po-test-date").val(),qTest_time:$("#po-test-time").val()},
		function(str){
			//alert(str);
		});
}


/**
 * 发布消息
 */
function click_postMessage(){
	var Message_titleValue=$("#message_title").val();
	var Message_contentValue=$("#message_content").val();
	$.post('PaperManagerServlet',{method:'addMessage',
		message_title:Message_titleValue,message_content:Message_contentValue},
		function(str){
			alert(str);
			//清空文本框
			$("#message_title").val("");
			$("#message_content").val("");
			ShowMessage();
		});
}
//重置消息
function click_resetMessage(){
	$("#message_title").val("");
	$("#message_content").val("");
}

// 按上一页的变化
function last(){
	if(pagepc>1){            //if页数大于一，则可以有上一页
	now = new Date();
	$.post("${pageContext.request.contextPath}/phone/find_findListPage?code="+now.getTime(),
				{"page.pc":parseInt(pagepc)-parseInt(1),"phs":$(".phs").val(),"pb":$(".pb").val(),"ps":$(".phss").val(),
			"pa":$(".pa").val(),"priceBegin":$(".pbegin").val(),"priceEnd":$(".pend").val(),"findSolo":$(".fSolo").val()},function(data){  
					var str=eval("("+data+")");
					$("#showlist").html("");
					$.each(str, function (index, p) {
	 					$("#showlist").append("<div  style='width: 217px; height:320px;  float: left; border:2px solid #FFFFFF;' onmouseover='this.style.borderColor='	#E0E0E0''  onmouseout='this.style.borderColor='#FFFFFF''><a target='_bank' href='./phone/phone_getPhoneInfo?phone.pid="+p.pid+"'><img src='../"+p.image+"' alt='...' class='img-rounded' style='width: 200px;'></a><div style='padding: 10px;'><font color='red' size='4pt'> <span class='glyphicon glyphicon-yen' aria-hidden='true'></span> "+p.price+".0</font></div> <a target='_bank' href='./phone/phone_getPhoneInfo?phone.pid="+p.pid+"'><span>"+p.pname+"</span></a></div>");
	 				});
		});
		pagepc=pagepc-1;   //页数减一
	varpage();
	}
}
//精确页数的跳转
$(".reNumber").click(function(){
	var s=$(".pageNumber").val();   //获取输入框的页数text
	if(parseInt(s)<=$(".pr").val()&&parseInt(s)>=1)  //比较页数将text转换为int
	{   
	now = new Date();
	$.post("${pageContext.request.contextPath}/phone/find_findListPage?code="+now.getTime(),
				{"page.pc":$(".pageNumber").val(),"phs":$(".phs").val(),"pb":$(".pb").val(),"ps":$(".phss").val(),
			"pa":$(".pa").val(),"priceBegin":$(".pbegin").val(),"priceEnd":$(".pend").val(),"findSolo":$(".fSolo").val()},function(data){  
					var str=eval("("+data+")");
					$("#showlist").html("");
					$.each(str, function (index, p) {
	 					$("#showlist").append("<div  style='width: 217px; height:320px;  float: left; border:2px solid #FFFFFF;' onmouseover='this.style.borderColor='	#E0E0E0''  onmouseout='this.style.borderColor='#FFFFFF''><a target='_bank' href='./phone/phone_getPhoneInfo?phone.pid="+p.pid+"'><img src='../"+p.image+"' alt='...' class='img-rounded' style='width: 200px;'></a><div style='padding: 10px;'><font color='red' size='4pt'> <span class='glyphicon glyphicon-yen' aria-hidden='true'></span> "+p.price+".0</font></div> <a target='_bank' href='./phone/phone_getPhoneInfo?phone.pid="+p.pid+"'><span>"+p.pname+"</span></a></div>");
	 				});
		});
		pagepc=parseInt(s);
	varpage();
	}
});


//显示单项选择题
function ShowSingle_choose(){
	/*var subjectValue=$("#subject2").val();
	$.post('testManagerServlet',{method:'ajaxShowChoose',subjectName:subjectValue},
	   	function(arry){
         	var obj = eval('('+arry+')');
         	$("#question_content").html("");
         	for(var i=0;obj.length;i++){
		    var opt = "<tr><td>"+obj[i].choice_content+"</td><td>"+obj[i].choiceA+
		        obj[i].choiceB+obj[i].choiceC+obj[i].choiceD+"</td><td>"+
		        obj[i].choice_answer+"</td><td><a href='#' class='edit' onclick='edit_question(this)'>修改</a>"+
		    	"<a href='#' class='delete' onclick='delete_question(this)'>删除</a></td></tr>";
            $('#question_content').append(opt);}
	    });*/
	//var pages_total=0;
	$.post("DisplayByPagesServlet",{method:"getPages_total",
		subjectName:$("#subject2").val(),qType:$("#qType").val()},
		function(num){
			pages_total=parseInt(num);
			 pagepc=parseInt(1); 
			//alert("返回页数"+pages_total);
			$.post("DisplayByPagesServlet",{method:"showQuestion",page:1,
				subjectName:$("#subject2").val(),qType:$("#qType").val()},
				function(data){  
					showbypages(data);
			});
			varpage(pages_total);    //调用页数列表的改变函数
		}
	);		
}


//显示填空题
function ShowCompletion(){
	/*var subjectValue=$("#subject2").val();
	$.post('testManagerServlet',{method:'ajaxShowCompletion',subjectName:subjectValue},
	   	function(arry){
		    var obj = eval('('+arry+')');
         	$("#question_content").html("");
         	for(var i=0;obj.length;i++){
		    var opt = "<tr><td>"+obj[i].completion_content+"</td><td>"+obj[i].completion_answer+
		        "</td><td><a href='#' class='edit' onclick='edit_question(this)'>修改</a>" +
		        "<a href='#'class='delete' onclick='delete_question(this)'>删除</a></td></tr> ";
            $('#question_content').append(opt);
            }
	 });*/
	//var pages_total=0;
	$.post("DisplayByPagesServlet",{method:"getPages_total",
		subjectName:$("#subject2").val(),qType:$("#qType").val()},
		function(num){
			pages_total=num;
			 pagepc=parseInt(1); 
			//alert("返回页数"+pages_total);
			$.post("DisplayByPagesServlet",{method:"showQuestion",page:1,
				subjectName:$("#subject2").val(),qType:$("#qType").val()},
				function(data){  
					showbypages(data);
			});
			varpage(pages_total);    //调用页数列表的改变函数
		}
	);		
}
//显示判断题
function ShowTfng(){
	/*var subjectValue=$("#subject2").val();
	$.post('testManagerServlet',{method:'ajaxShowTfng',subjectName:subjectValue},
	   	function(arry){
         	var obj = eval('('+arry+')');
         	$("#question_content").html("");
     		var tfng_answer;
         	for(var i=0;obj.length;i++){
         		if(parseInt(obj[i].tfng_answer)==0) { tfng_answer="F";}
         		else { tfng_answer="T";}
		    var opt = "<tr><td>"+obj[i].tfng_content+"</td><td>"+tfng_answer+
		        "</td><td><a href='#' class='edit' onclick='edit_question(this)'>修改</a>" +
		        "<a href='#' class='delete' onclick='delete_question(this)'>删除</a></td></tr>";
            $('#question_content').append(opt);
            }
     });*/
	//var pages_total=0;
	$.post("DisplayByPagesServlet",{method:"getPages_total",
		subjectName:$("#subject2").val(),qType:$("#qType").val()},
		function(num){
			pages_total=parseInt(num);
			 pagepc=parseInt(1); 
			//alert("返回页数"+pages_total);
			$.post("DisplayByPagesServlet",{method:"showQuestion",page:1,
				subjectName:$("#subject2").val(),qType:$("#qType").val()},
				function(data){  
					showbypages(data);
			});
			varpage(pages_total);    //调用页数列表的改变函数
		}
	);		
}

//显示主观题
function ShowSubjective_item(){
	/*var subjectValue=$("#subject2").val();
	$.post('testManagerServlet',{method:'ajaxShowItem',subjectName:subjectValue},
	   	function(arry){
         	var obj = eval('('+arry+')');
         	$("#question_content").html("");
         	for(var i=0;obj.length;i++){
		    var opt = "<tr><td>"+obj[i].item_content+"</td><td>"+obj[i].item_answer+
		        "</td><td><a href='#' class='edit'  onclick='edit_question(this)'>修改</a>"+
		    	"<a href='#' class='delete'  onclick='delete_question(this)'>删除"+
		    	"</a></td></tr>";
            $('#question_content').append(opt);
            }
     });*/
	 //var pages_total=0;
	$.post("DisplayByPagesServlet",{method:"getPages_total",
		subjectName:$("#subject2").val(),qType:$("#qType").val()},
		function(num){
			pages_total=parseInt(num);
			 pagepc=parseInt(1); 
			//alert("返回页数"+pages_total);
			$.post("DisplayByPagesServlet",{method:"showQuestion",page:1,
				subjectName:$("#subject2").val(),qType:$("#qType").val()},
				function(data){  
					showbypages(data);
				});
			varpage(pages_total);    //调用页数列表的改变函数
		});	
   } 
//$('document').ready(function(){ 
/**
 * 分页显示
 */
// 按下一页 实现搜索内容跳转
function next(){
	//alert("当前页"+pagepc);
	if(pagepc<pages_total){    //如果当前页数最大了，不能下一页
		$.post("DisplayByPagesServlet",{method:"showQuestion",page:parseInt(pagepc)+parseInt(1),
			subjectName:$("#subject2").val(),qType:$("#qType").val()},
			function(data){  
				showbypages(data);
		});
	pagepc=parseInt(pagepc)+parseInt(1);   //按下一页，相当的页数增加
	//alert("按下一页之后"+pagepc);
	varpage(pages_total);
	}else{
		alert("这已经是最后一页！");
	}
}	
//按上一页 实现搜索内容跳转
function last(){
	//alert("当前页"+pagepc);
	if(pagepc>1){    //如果当前页数最大了，不能下一页
		$.post("DisplayByPagesServlet",{method:"showQuestion",page:parseInt(pagepc)-parseInt(1),
			subjectName:$("#subject2").val(),qType:$("#qType").val()},
			function(data){  
				showbypages(data);
		});
		pagepc=parseInt(pagepc)-parseInt(1);   //按下一页，相当的页数增加
		//alert("按上一页之后"+pagepc);
	varpage(pages_total);
	}else{
		alert("这是第一页！");
	}
}	
//按数字键  实现页数跳转
function showQuestion(obj){
	//$(obj).addClass("click");
	pagepc=$(obj).text();     //赋值页数
	$.post("DisplayByPagesServlet",{method:"showQuestion",page:$(obj).text(),
		subjectName:$("#subject2").val(),qType:$("#qType").val()},
		function(data){  
			showbypages(data);
	});
		varpage(pages_total);    //调用页数列表的改变函数
}
//获取数据然后显示
function showbypages(data){
	
	var obj=eval("("+data+")");
	$("#single_choice tr:not(:first)").empty();
	if($("#qType").val()=="单项选择"){
		for(var i=0;obj.length;i++) {
			var opt="<tr><td><textarea class='content-ta' readOnly='readOnly'>"+obj[i].choice_content+"</textarea></td><td>" +
				"<textarea class='ans-ta' readOnly='readOnly'>"+obj[i].choiceA+"</textarea><textarea class='ans-ta' readOnly='readOnly'>"+
				obj[i].choiceB+"</textarea><textarea class='ans-ta' readOnly='readOnly'>"+obj[i].choiceC+"</textarea><textarea class='ans-ta' " +
				"readOnly='readOnly'>"+obj[i].choiceD+"</textarea></td><td><input type='text' class='choose-ans' value="+obj[i].choice_answer+
				" readOnly='readOnly'></td><td><button class='test-btn' onclick='edit_question(this)'>修改</button>&nbsp;"+
				"<button class='test-btn' onclick='delete_question(this)'>删除</button>&nbsp;" +
				"<button class='save' onclick='save_question(this)'>保存</button></td></tr>";
			$("#single_choice").append(opt);
		}
	}
	else if($("#qType").val()=="填空题"){
		$("#completion tr:not(:first)").empty();
		for(var i=0;obj.length;i++){
		    var opt = "<tr><td><textarea class='content-ta' readOnly='readOnly'>"+obj[i].completion_content+"</textarea></td><td>" +
		    	"<textarea class='ans-ta' readOnly='readOnly'>"+obj[i].completion_answer+"</textarea></td><td><button class='test-btn' " +
		    	"onclick='edit_question(this)'>修改</button>&nbsp;<button class='test-btn' onclick='delete_question(this)'>删除</button>&nbsp;" +
		        "<button class='save' onclick='save_question(this)'>保存</button></td></tr> ";
            $('#completion').append(opt);
        }
	}
	else if($("#qType").val()=="判断题"){
		$("#tfng tr:not(:first)").empty();
		for(var i=0;obj.length;i++){
     		if(parseInt(obj[i].tfng_answer)==0) { tfng_answer="F";}
     		else { tfng_answer="T";}
     	var opt = "<tr><td><textarea class='content-ta' readOnly='readOnly'>"+obj[i].tfng_content+"</textarea>" +
     		"</td><td><textarea class='ans-ta' readOnly='readOnly'>"+tfng_answer+"</textarea></td><td>" +
     		"<button class='test-btn' onclick='edit_question(this)'>修改</button>&nbsp;" +
	        "<button class='test-btn' onclick='delete_question(this)'>删除</button>&nbsp;" +
	        "<button class='save' onclick='save_question(this)'>保存</button></td></tr>";
        $('#tfng').append(opt);
        }
	}
	else if($("#qType").val()=="主观题"){
		$("#subjective tr:not(:first)").empty();
		for(var i=0;obj.length;i++){
		    var opt = "<tr><td><textarea class='sub-ta' readOnly='readOnly'>"+obj[i].item_content+"</textarea>" +
		    	"</td><td><textarea class='sub-ans-ta' readOnly='readOnly'>"+obj[i].item_answer+"</textarea>"+
		        "</td><td><button class='test-btn'  onclick='edit_question(this)'>修改</button>&nbsp;"+
		    	"<button class='test-btn'  onclick='delete_question(this)'>删除</button>&nbsp;" +
		    	"<button class='save' onclick='save_question(this)'>保存</button></td></tr>";
            $('#subjective').append(opt);
            }
	}
	$(".save").attr("disabled",true);
	$(".showMytest textarea").attr("disabled",true);
	$(".showMytest input").attr("disbaled",true);
}

	
//  ------- 页数显示的变化------------
function varpage(pages_total){
	$("#page_no button").remove();
	$("#now_all span").empty();
	$("#now_all").append("<span class='pages'>第<span>"+pagepc+"</span>/<span class='total_count'>"+pages_total+"</span>页 </span>");
	
	for(var p=pagepc-2;p<=pages_total;p++){
		if(p>=1 && p<=parseInt($(".total_count").text())){
				//$("#next").before("<span><a class='pages' onclick='showQuestion(this)'>"+p+"</a></span>");
				$("#page_no").append("<button class='pages'  onclick='showQuestion(this)'>"+p+"</button>");
		}
	}
}

/**
* 导入文件
*/
function ImportExcel(){
	var qTypeValue=null;
	if($("#Import_qType").val()=="请选择"){alert("请选择一种题型！");return;}
	if($("#Import_qType").val()=="单项选择"){ qTypeValue="Single_chose";}
	else if($("#Import_qType").val()=="判断题"){ qTypeValue="Tfng";}
	else if($("#Import_qType").val()=="填空题"){ qTypeValue="Completion";}
	else if($("#Import_qType").val()=="主观题"){ qTypeValue="Subjective_item";}
	$.post("ImportDBServlet",{method:"ExcelImportDB",filePath:$("#upfile").val(),
		ImportType:qTypeValue},function(str){
			alert(str);
		});
}

