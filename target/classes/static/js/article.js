function submitComment(){
	 $("#commenttitleId").attr("value",$("#titleid").text());	
	 var titleId = $("#commenttitleId").attr("value");
	 $("#submitComment").ajaxSubmit(function (data) {
		  history.go(0);
	  });
}
function IsApproal(id,isapproal,self){
	var flag = true;
	if(flag==false){
		alert("你已经点过了");
		return false;
	}
	$.ajax({
		url:"../index/updateIsApproal.do",
		type:"get",
		data:{
			Id:id,
			IsApproal:isapproal,
		},
		dataType:"json",
		success:function(result){
			$(self).removeAttr("value");
			$(self).val(result.isApproal);
			flag=false;
		},
	});
}

function NoApproal(id,noApproal,self){
	var flag = true;
	if(flag==false){
		alert("你已经点过了");
		return false;
	}
	$.ajax({
		url:"../index/updateNsApproal.do",
		type:"get",
		data:{
			Id:id,
			NoApproal:noApproal,
		},
		dataType:"json",
		success:function(result){
			$(self).removeAttr("value");
			//前端问题 无法显示变更后的value值
			$(self).val(result.noApproal);
			flag=false;
		},
	});
}


