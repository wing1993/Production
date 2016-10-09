$(document).ready(function(){
	
	//发布消息
	$("#list4_title").click(function(){
		$("#location1").html($(this).text());
		$("#location2").html("");
	});
	//点击一个功能按钮时隐藏其他div
	//出题
	$("#manualAdd").click(function(){
		$("#buildTest").show();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").hide();
		
	});

	//我的试题
	$("#mytest").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").show();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").hide();
	});
	
	//导入试题
	$("#importTest").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").show();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").hide();
	});

	//试卷修改
	$("#editPaper").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").show();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").hide();
		ShowPaper();//yjh
	});
	//我要出卷
	$("#setPaper").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").show();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").hide();
		$("#buildPaper input[type='checkbox']").nextAll().attr("disabled",true);
	});
	//主观题评分
	$("#sub_eval").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").show();
		$("#sum_up").hide();
		$("#releaseNews").hide();
	});
	//考试情况同意统计
	$("#statistic").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").show();
		$("#releaseNews").hide();
	});

	//发布消息
	$("#list4_title").click(function(){
		$("#buildTest").hide();
		$("#manage_mytest").hide();
		$("#importFile").hide();
		$("#buildPaper").hide();
		$("#edit_paper").hide();
		$("#grading").hide();
		$("#sum_up").hide();
		$("#releaseNews").show();
	});

	//当题目类型变化时，相应的题目答案输入框也跟着变化
	$("#type input").change(function(){
		if($(this).val()=="判断题"){
			$(".choice").hide();
			$(".answer").children('td').remove();
			$(".answer").append("<td>答案：</td><td><select id='answer1'><option value='T'>T</option><option value='F'>F</option></select></td>");
			
		}
		else if ($(this).val()=="填空题") {
			$(".choice").hide();
			$(".answer").children('td').remove();
			$(".answer").append("<td>答案：</td><td><textarea type='textarea' id='answer1'></textarea></td>");
		}
		else if($(this).val()=="单项选择"){
			$(".choice").show();
			$(".answer").children('td').remove();
			$(".answer").append("<td>答案：</td><td><select id='answer1'><option>A</option><option value='B'>B</option><option>C</option><option>D</option></select></td>");
			//alert($("#answer").val());
		}
		else{
			$(".choice").hide();
			$(".answer").children('td').remove();
			$(".answer").append("<td>参考答案：</td><td><textarea type='textarea' id='answer1'></textarea></td>");
		}
		change_str=$(this).val();
	});

	//当下拉框的内容改变时显示不同的table
	$("#qType").change(function(){
		if($(this).val()=="单项选择"){
			$("#single_choice").show();
			$("#completion").hide();
			$("#tfng").hide();
			$("#subjective").hide();
			ShowSingle_choose();
		}	
		else if ($(this).val()=="填空题") {
			$("#completion").show();
			$("#single_choice").hide();
			$("#tfng").hide();
			$("#subjective").hide();
			ShowCompletion();
		}
		else if($(this).val()=="判断题"){
			$("#completion").hide();
			$("#single_choice").hide();
			$("#tfng").show();
			$("#subjective").hide();
			ShowTfng();
		}
		else if($(this).val()=="主观题"){
			$("#completion").hide();
			$("#single_choice").hide();
			$("#tfng").hide();
			$("#subjective").show();
			ShowSubjective_item();
		}
	});
	//显示题目时当科目类型改变时
	$('#subject2').change(function(){
		var qTypeValue=$('#qType').val();
		if(qTypeValue=="单项选择") ShowSingle_choose();
		else if(qTypeValue=="判断题") ShowTfng();
		else if(qTypeValue=="填空题") ShowCompletion();
		else if(qTypeValue=="主观题") ShowSubjective_item();
	});
	//设置当复选框选中时，数量和数目为可选状态
	$("#buildPaper input[type='checkbox']").change(function(){
		if($(this).is(':checked')){
			$(this).nextAll().attr("disabled",false);
		}else{
			$(this).nextAll().attr("disabled",true);
		}
	});

	//关闭修改消息框
	$(".close").click(function(){
		//if(confirm("要保存修改的内容吗")){
			
		//}else{}
		$(".background").fadeOut(100);
		$(".popover").slideUp(200);

	});
});

//弹出新的窗口进行主观题评分
function forword(){
	window.open("item_answer.jsp","","toolbar=no,width=800,height=500,top=150,left=300,status=no,scrollbars=yes,resizable=yes,menuber=no");
}

//显示统计结果页面
function showGraphic(){
	window.open("analyse_result.jsp","","toolbar=no,width=800,height=500,top=150,left=300,status=no,scrollbars=yes,resizable=yes,menuber=no");
}

//限制输入框只能输入数字
function keypress(){
	var keyCode = event.keyCode;    
    if ((keyCode >= 48 && keyCode <= 57))    //键值48－57在键值表中只是对应大键盘的0－9
    {    
        event.returnValue = true;    
    } else {    
          event.returnValue = false;    
   }    
}
//修改试卷wing
function edit_paper(obj){
	$(".background").fadeIn(100);
	$(".po-edit-paper").slideDown(200);
	var paper_name=$(obj).parent().prev().prev().prev().text();
	var date=$(obj).parent().prev().prev().text();
	date=date.replace(/-/g,"-");
	var str=$(obj).parent().prev().text();
	//var time=str.substr(0,str.indexOf('分钟'));//提取字符串
	$("#po-paper-name").text(paper_name);
	$("#po-test-date").val(date);
	$("#po-test-time").val(str);
}

//删除试卷wing+yjh
function delete_paper(obj){
	if(confirm("确定要删除该试卷吗？")){
		var paper_nameValue=$(obj).parent().prev().prev().prev().text();
		$(obj).parent().parent().remove();
		//删除题库中与paper_name对应的题型 
		$.post("PaperManagerServlet",{method:'deletePaper',paper_name:paper_nameValue});
	}else{}
}

//保存修改wing
function save_change(){
	if(parseInt($("#po-test-time").val())<=0){
		alert("输入的数字不能小于0！");
		$("#po-test-time").focus();
	}else{
		changePaper();//yjh
		ShowPaper();//yjh
		$(".background").fadeOut(100);
		$(".po-edit-paper").slideUp(200);
		
	}
	
}

//关闭修改试卷框wing
function close_paper(){
	
	//if(confirm("要保存修改的内容吗？")){
		$(".background").fadeOut(100);
		$(".po-edit-paper").slideUp(200);
	//}else{
	//	$(".background").fadeOut(100);
	//	$(".po-edit-paper").slideUp(200);
	//}
}
//删除消息
function deletemsg(obj){
	var title=$(obj).parent().prev().prev().text();
	if(confirm("确定要删除该消息吗？")){
		$(obj).parent().parent().remove();
		//删除数据库的内容
		$.post('PaperManagerServlet',{method:'deleteMessage',message_title:title},
				function(str){
		});
	}else{}
}

//修改消息内容
messageIdValue="";
function edit(obj){
	$(".background").fadeIn(100);
	$(".popover").slideDown(200);
	var title=$(obj).parent().prev().prev().text();//获取试卷名称
	$("#po-message-title").val(title);
	//消息内容来自数据库   获取消息内容并填充至文本框
	$.post('PaperManagerServlet',{method:'getMessageContent',message_title:title},
			function(list){
		var obj = eval('(' +list+ ')');
		//for(var i=0;obj.length;i++){
			$("#po-message-content").val(obj[1]);
			messageIdValue=obj[0];
		//}
	});
}
	//保存消息
	//$("#popover-btn").click(function(){
	function SaveMessage1(){
		//alert(messageIdValue);
		var title=$("#po-message-title").val();
		var content=$("#po-message-content").val();
		$.post('PaperManagerServlet',{method:'updateMessage',message_id:messageIdValue,
			message_title:title,message_content:content},
			function(str){
				//alert(str);
			});
		$(".background").fadeOut(100);
		$(".popover").slideUp(200);
		ShowMessage();
	}

	$(document).ready(function(){
		
		
	});
	var qTypeIdValue=0;
	//点击修改之后将保存控件设为可点击状态
	function edit_question(obj){
		$(obj).next().next().attr("disabled",false);
		$(obj).next().next().addClass("save-before");
		$(obj).parent().prev().prev().prev().find("textarea").attr("readOnly",false);//单项选择题目
		$(obj).parent().prev().prev().prev().find("textarea").addClass("ta-save-before");
		$(obj).parent().prev().prev().find("textarea").attr("readOnly",false);
		$(obj).parent().prev().prev().find("textarea").addClass("ans-ta-edit");
		$(obj).parent().prev().find("textarea").attr("readOnly",false);//填空题
		$(obj).parent().prev().find("textarea").addClass("ans-ta-edit");
		$(obj).parent().prev().find("input").attr("readOnly",false);//选择题答案
		$(obj).parent().prev().find("input").removeClass("choose-ans");
		$(obj).parent().prev().find("input").addClass("choose-ans-before");
		
		var qTypeValue=$("#qType").val();
	    var contentValue=$(obj).parent().prev().prev().text();
	    $.post("testManagerServlet",{method:'getQuestionId',content:contentValue,qType:qTypeValue},
	    		function(n){ qTypeIdValue=parseInt(n);});		
	}

	//保存修改
	function save_question(obj){
		$(obj).attr("disabled",true);

		$(obj).removeClass("save-before");
		$(obj).addClass("save");
		$(obj).parent().prev().prev().prev().find("textarea").attr("readOnly",true);//单项选择题目
		$(obj).parent().prev().prev().prev().find("textarea").removeClass("ta-save-before");
		$(obj).parent().prev().prev().prev().find("textarea").addClass("ta-save");
		$(obj).parent().prev().prev().find("textarea").attr("readOnly",true);
		$(obj).parent().prev().prev().find("textarea").removeClass("ans-ta-edit");
		$(obj).parent().prev().find("textarea").attr("readOnly",true);//填空题
		$(obj).parent().prev().find("textarea").removeClass("ans-ta-edit");
		$(obj).parent().prev().find("input").attr("readOnly",true);//选择题答案
		$(obj).parent().prev().find("input").removeClass("choose-ans-before");
		$(obj).parent().prev().find("input").addClass("choose-ans");
		
		var question_content=$(obj).parent().parent().eq(0).children().eq(0).children().val();
		var question_answer=$(obj).parent().parent().eq(0).children().eq(1).children().val();
		$.post("testManagerServlet",{method:"saveQuestion",content:question_content,answer:question_answer,
			qType:$("#qType").val(),qTypeId:qTypeIdValue},function(str){if(str==1)alert("修改成功");});
	}


