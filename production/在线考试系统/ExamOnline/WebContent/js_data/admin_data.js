
pagepc=parseInt(1);  //设置当前页(初始化) 学生   全局的页数
pages_total=parseInt(1);
pagepc1=parseInt(1);//教师分页
pages_total1=parseInt(1);

$('document').ready(function(){ 
	ajaxGetInstitute();//填充学院的下拉框
});
function getClass(obj,str){
	ajaxGetClass(obj,str);
}
function ajaxGetClass(obj,str){
	//根据学院获取该学院下的所有班级
    var instituteValue = $(obj).val();
    $.post('OptionServlet',{method:'ajaxClassOption',instituteName:instituteValue},
 		   function(list){
		   var array = eval('(' +list+ ')');
		   $(str).html("");
		   for(var i=0;i<array.length;i++){
            var opt = '<option value='+array[i]+'>'+array[i]+'</option>';
            $(str).append(opt);
            }
		   if(str=="#class"){showStudent();}
    }); 
}
function ajaxGetInstitute(){
    //获取学院下拉框内容
   $.ajax({
	    cache: false,
		async: false,
		type: "POST",
		data: {method: "ajaxInstituteOption"},
		url: "OptionServlet",
		 success: function (list) {
             var obj = eval('(' +list+ ')');
             for(var i=0;i<obj.length;i++){
                var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
                 /*$('#institute').append(opt);
                 $("#instituteT").append(opt);
                 $("#instituteI").append(opt);
                 $("#insert_tinstitute").append(opt);*/
                $(".institute1").append(opt);
                 if(obj[i]!=$('.institute2').val())
                	 $('.institute2').append(opt);
         }
         },
         error: function (XMLHttpRequest, textStatus, errorThrown) {
             alert("ajax学院信息获取失败-"+errorThrown);
         }
	});
}

//根据学院获取该学院下的所有班级
function change(institute){
	var instituteValue = $(institute).val();
    $.post('OptionServlet',{method:'ajaxClassOption',instituteName:instituteValue},
 		   function(list){   
		   var obj = eval('(' +list+ ')');
		   $(institute).parent().next().find("select").html("");
		   for(var i=0;i<obj.length;i++){
            var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
            if(obj[i]!=$(institute).parent().next().find("select").val())
            	$(institute).parent().next().find("select").append(opt);
		    }	  
	   });
}
/**
 * 学生信息分页
 */
//获取数据输出
var student="student";
function InputStudent(array){
	var obj = eval('(' +array+ ')');
	$("#stuInfo tr:not(:first)").empty();
	for(var i=0;i<obj.length;i++){
		var j=0;
		if(obj[i].stuGender==1) { stuGender1="男";stuGender2="女";}
		else{ j=1; stuGender1="女";stuGender2="男";}
		var opt="<tr><td class='stuNo'>"+obj[i].stuNo+"</td><td><input type='text' class='hide-text' value="+obj[i].stuName+"></td>" +
				"<td><select class='gender'>" +
				"<option value='"+obj[i].stuGender+"'>"+stuGender1+"</option><option value='"+j+"'>"+
				stuGender2+"</option></select></td><td><select class='institute2' onclick='change(this)'>" +
				"<option>"+obj[i].instituteName+"</option></select></td><td><select class='class2'>" +
				"<option>"+obj[i].grade+obj[i].className+"</option></select></td><td><button class='valid' " +
				"onclick='edit_stu(this)'>修改</button>&nbsp;<button class='valid' onclick='delete_stu(this)'>" +
				"删除</button>&nbsp;<button class='invalid' onclick='save_change(this,student)'>保存</button></td></tr>";
		$("#stuInfo").append(opt);
	}
    $.ajax({
	    cache: false,
		async: false,
		type: "POST",
		data: {method: "ajaxInstituteOption"},
		url: "OptionServlet",
		 success: function (list) {
          var obj = eval('(' +list+ ')');
          for(var i=0;i<obj.length;i++){
             var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
              if(obj[i]!=$('.institute2').val())
             	 $('.institute2').append(opt);
          }
      }
	 });
    $(".tblist2 select").addClass("hide-select");
	$(".tblist2 select").attr("disabled",true);
	$(".tblist2 .hide-text").attr("disabled",true);
}

//考生信息查询修改       显示学生信息
function change_class(){
	showStudent();
}
//先查询出总的页数再默认显示第一页的内容
function showStudent(){
	$.post("DisplayByPagesServlet",{method:"getPagesTotal_Student",
		instituteName:$("#institute").val(),className:$("#class").val()},
		function(num){
			pages_total=parseInt(num);
			$.post("DoAdminServlet",{method:"showStudent",instituteName:$("#institute").val(),
				className:$("#class").val(),page:1},function(array){
			InputStudent(array);
		});
		varpage(pages_total);
	});
}
//按数字键  实现页数跳转
function showStudentByPages(obj){
	pagepc=$(obj).val();     //赋值页数
	$.post("DoAdminServlet",{method:"showStudent",page:$(obj).val(),
		instituteName:$("#institute").val(),className:$("#class").val()},
		function(array){  
			InputStudent(array);
	});
		varpage(pages_total);    //调用页数列表的改变函数
}
//按下一页 实现搜索内容跳转
function next(){
	if(pagepc<pages_total){    //如果当前页数最大了，不能下一页
		$.post("DoAdminServlet",{method:"showStudent",page:parseInt(pagepc)+parseInt(1),
			instituteName:$("#institute").val(),className:$("#class").val()},
			function(array){  
				InputStudent(array);
		});
	pagepc=parseInt(pagepc)+parseInt(1);   //按下一页，相当的页数增加
	varpage(pages_total);
	}else{alert("这已经是最后一页！");}
}
//按上一页 实现搜索内容跳转
function last(){
	if(pagepc>1){    //如果当前页数为第一页，不能上一页
		$.post("DoAdminServlet",{method:"showStudent",page:parseInt(pagepc)-parseInt(1),
			instituteName:$("#institute").val(),className:$("#class").val()},
			function(array){  
				InputStudent(array);
		});
	pagepc=parseInt(pagepc)-parseInt(1);   //按上一页，相当的页数减少
	varpage(pages_total);
	}else{alert("当前页是第一页！");}
}
//查询特定学号姓名的学生
function findStudent(){
	$.post("DoAdminServlet",{method:"findStudent",stuNo:$("#stuNo").val(),stuName:$("#stuName").val()},
			function (array){
				InputStudent(array);
	});
	$(".page_no_stu1").remove();
	$("#page_no_stu").append("<button class='page_no_stu1' onclick=''>1</button>");
	$("#next").before("<span class='page_no_stu1'>第<span id='current_page'>1</span>/<span id='total_count'>1</span>页 </span>");
}

//------- 页数显示的变化------------
function varpage(pages_total){
	$(".page_no_stu1").remove();
	$("#next").before("<span class='page_no_stu1'>第<span id='current_page'>"+pagepc+"</span>/<span id='total_count'>"+pages_total+"</span>页 </span>");	
	for(var p=1;p<=parseInt(pages_total);p++){
		if(p>=1 && p<=parseInt(pages_total)){
				$("#page_no_stu").append("<button class='page_no_stu1'  onclick='showStudentByPages(this)' value="+p+">"+p+"</button>");
		}
	}
}
/**
 * 教师信息分页
 */
var teacher="teacher";

function showTeacher(){
	$.post("DisplayByPagesServlet",{method:"getPagesTotal_Teachar",
		instituteName:$("#instituteT").val()},function(num){
		pages_total1=parseInt(num);
		$.post("DisplayByPagesServlet",{method:"showTeacher",page:1,
			instituteName:$("#instituteT").val()},function(array){
				InputTeacher(array);
			});
		varpageT(pages_total1);
	});
}
function InputTeacher(array){
	var obj = eval('(' +array+ ')');
	$("#teaInfo tr:not(:first)").empty();
	for(var i=0;i<obj.length;i++){
		var j=0;
		if(obj[i].tGender==1) { tGender1="男";tGender2="女";}
		else{ j=1; tGender1="女";tGender2="男";}
		var str="题库管理员";
		if(obj[i].tRole=="监考员"){tRole1="监考员";tRole2="题库管理员";}
		else{str="监考员";tRole2="监考员";tRole1="题库管理员";}
		var opt="<tr><td>"+obj[i].tNo+"</td><td>" +
				"<input type='text' value='"+obj[i].tName+"' class='hide-text'></td><td>" +
				"<select><option class='hide' value="+obj[i].tGender+">"+tGender1+"</option>" +
				"<option class='hide' value="+j+">"+tGender2+"</option></select></td>" +
				"<td><select class='hide-select'><option class='hide' value='"+obj[i].tRole+"'>"+tRole1+"</option><option class='hide' value='"+str+"'>"+tRole2+
				"</option></select></td>" +
				"<td><select class='institute2'><option>"+obj[i].instituteName+"</option></select></td>" +
				"<td><button class='valid' onclick='edit_stu(this)'>修改</button>&nbsp;<button class='valid' onclick='delete_t(this)'>" +
				"删除</button>&nbsp;<button class='invalid' onclick='save_change(this,teacher)'>保存</button></td></tr>";
		$("#teaInfo").append(opt);
	}
	$(".tblist2 select").addClass("hide-select");
	$(".tblist2 select").attr("disabled",true);
	$(".tblist2 .hide-text").attr("disabled",true);
}
//查询特定编号姓名的教师
function findTeacher(){
	$.post("DoAdminServlet",{method:"findTeacher",tNo:$("#tNo").val(),tName:$("#tName").val()},
			function (array){
				InputTeacher(array);
	});
	$(".page_no_stu1").remove();
	$("#page_no_tea").append("<button class='page_no_stu1' onclick=''>1</button>");
	$("#nextT").before("<span class='page_no_stu1'>第<span id='current_pageT'>1</span>/<span id='total_countT'>1</span>页 </span>");
}
//按数字键  实现页数跳转
function showTeacherByPages(obj){
	pagepc1=$(obj).val();     //赋值页数
	$.post("DisplayByPagesServlet",{method:"showTeacher",page:$(obj).val(),
		instituteName:$("#instituteT").val()},function(array){  
			InputTeacher(array);
	});
		varpageT(pages_total1);    //调用页数列表的改变函数
}
//按下一页 实现搜索内容跳转
function nextT(){
	if(pagepc1<pages_total1){    //如果当前页数最大了，不能下一页
		$.post("DisplayByPagesServlet",{method:"showTeacher",page:parseInt(pagepc1)+parseInt(1),
			instituteName:$("#instituteT").val()},function(array){  
				InputTeacher(array);
		});
	pagepc1=parseInt(pagepc1)+parseInt(1);   //按下一页，相当的页数增加
	varpageT(pages_total1);
	}else{alert("这已经是最后一页！");}
}
//按上一页 实现搜索内容跳转
function lastT(){
	if(pagepc1>1){    //如果当前页数为第一页，不能上一页
		$.post("DisplayByPagesServlet",{method:"showTeacher",page:parseInt(pagepc1)-parseInt(1),
			instituteName:$("#instituteT").val()},function(array){  
				InputStudent(array);
		});
	pagepc1=parseInt(pagepc1)-parseInt(1);   //按上一页，相当的页数减少
	varpageT(pages_total1);
	}else{alert("当前页是第一页！");}
}
//------- 页数显示的变化------------
function varpageT(pages_total1){
	$(".page_no_stu1").remove();
	$("#nextT").before("<span class='page_no_stu1'>第<span id='current_pageT'>"+pagepc1+"</span>/<span id='total_countT'>"+pages_total1+"</span>页 </span>");	
	for(var p=1;p<=parseInt(pages_total1);p++){
		if(p>=1 && p<=parseInt(pages_total1)){
				$("#page_no_tea").append("<button class='page_no_stu1'  onclick='showTeacherByPages(this)' value="+p+">"+p+"</button>");
		}
	}
}

/**
*	插入教师信息
*/
function insert_tea(){
	if($("#po-tNo").val()==""){
		alert("请输入教师编号！");
	}
	else if($("#po-tName").val()==""){
		alert("请输入姓名！");
	}
	else if($("#insert_tinstitute").val()==""){
		alert("请选择学院！");
	}
	else{
		/*alert($("#po-tNo").val());
		alert($("#po-tName").val());
		alert($("#insert_tgender").val());
		alert($(".tRole").val());
		alert($("#insert_tinstitute").val());*/
		$.post("DoAdminServlet",{method:"insertTeacher",tNo:$("#po-tNo").val(),
			tName:$("#po-tName").val(),tGender:$("#insert_tgender").val(),
			tRole:$(".tRole").val(),instituteName:$("#insert_tinstitute").val()},
			function(str){alert(str);});
		$(".background").fadeOut(100);
		$(".popover").slideUp(200);
	}
}
/**
* 导入文件
*/
function ImportExcel(){
	$.post("ImportDBServlet",{method:"ExcelImportDB",filePath:$("#upfile").val(),
		ImportType:"Student"},function(str){
			alert(str);
			$("#upfile").val('');
			});
}
