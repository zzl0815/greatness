$(function(){
	$.ajax({
		url:"../first/queryfriend.do",
		type:"get",
		dataType:"json",
		success:function(result){
			var html="";
			var id=$("#userId").text();
			for(var i=0;i<result.length;i++){
				if(id==result[i].sender.id&&result[i].sender.isAgree==0){
					html+="<a href='../friend/updateIsAgreeById.do?Id="+result[i].id+"'>加为好友</a>"; 
				}else if(result[i].isAgree==1){
					html+="<a href='../first/chat.html'>聊天</a>"; 
					//拼接成一个form表单,提交,跳转页面即可
				}
				else{
					html+="等待验证"; 
				}
			}
			$("#friendsList").append(html);
		}
	})
})



