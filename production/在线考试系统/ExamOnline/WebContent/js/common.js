//显示星期
$(document).ready(function(){
	var now=new Date();
	var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
	var y=now.getFullYear();
	var m=now.getMonth()+1;
	var d=now.getDate();
	var index=now.getDay();
	$("#date").html(y+"年"+m+"月"+d+"日"+show_day[index]);
});

$(document).ready(function(){
	//设置当前位置
	$("div.content li").click(function(){
		var str=$(this).parent().parent().attr("id");//获取元素的id
		var str1=$("#"+str+"_title").text();
		var location1=str1.substr(0,str1.indexOf('-'))
		$("#location1").html(location1);
		$("#location2").html(">>"+$(this).text());
	}); 
	
});

//二级菜单
function showmenu(id,sign){
	var list=$("#"+id);
	var sign=$("#"+sign);
	if(list.css("display")=='none'){
		list.css("display","block");
		sign.html("-");
		
	}else {
		list.css("display","none");
		sign.html("+");
	}
}

//获取科目下拉框的内容
$('document').ready(function(){ 
   $.ajax({
	    cache: false,
		async: false,
		type: "POST",
		data: {method: "ajaxSubjectOption"},
		url: "OptionServlet",
		 success: function (list) {
             var obj = eval('(' +list+ ')');
             for(var i=0;i<obj.length;i++){
                var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
                 $('.subject1').append(opt);
             }
         },
         error: function (XMLHttpRequest, textStatus, errorThrown) {
             alert("ajax科目信息获取失败-"+errorThrown);
         }
	});
});

