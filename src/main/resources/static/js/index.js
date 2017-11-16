$(function(){
	$.ajax({
		url:"../index/queryTitle.do",
		data:{
			start:"0",
			length:"5",
		},
		dataType:"json",
		type:"GET",
		sync:false,
		success:function(result){
			var html="";
			for(var i=0;i<result.length;i++){
					html+="<div style='width:1000px; margin:0 auto;border:1px solid #0F0F0F';><a href='../index/findTitleById.do?id="+result[i].id+"'>";
				/*	html+="<div>Id:"+result[i].id+"</div></br>";*/
					html+="<div		style='text-align:center'>标题:"+result[i].title+"</div></br>";
					html+="<div style='color:#B8B8B8'>文章概要:"+result[i].titleAbstract+"</div></br>";
					/*html+="<div>内容:"+result[i].content+"</div></br>";*/
					html+="<div>创建时间:"+result[i].createDate+"</div></br>";
					html+="<div>阅读人数:"+result[i].readPerson+"</div></br>";
					html+="<div>所属类型:"+result[i].type.typeName+"</div></br>";
					html+="<div>博主昵称:"+result[i].type.user.nickname+"</div></br>";
					html+="</a></div> ";
			}
			$("#article").append(html);
		}
	})
	//用于提醒是否有人关注,类似于微信QQ等的好友加你提醒功能,该功能用reids完成
	$.ajax({
		url:"../index/Remindersfriends.do",
		data:{
		},
		dataType:"json",
		type:"GET",
		sync:false,
		success:function(data){
			var id = $("#userId").val();
			alert(data.length+"Data.");
			$("#RemindersPersonNumber").text(data.length);
			
			//如果是自己就给他按钮  如果不是就等待验证,
			//改变数据库的内容
			var html="";
			for(var i = 0; i <data.length;i++){
			}
		}
	})
})

function submitpay(){
		$("#submitpay").ajaxSubmit(function (data) {
			alert(data);
		});
	}