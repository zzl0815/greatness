$(function(){
 	$.ajax({
		url:"../title/querytitleType.do",
		type:"POST",
		dataType:"json",
		success:function(data){
			var html="";
			for(var i=0; i<data.length ; i++){
				html+="<option value="+data[i].id+">"+data[i].typeName+"</option>"
				}
			$("#selecttitleType").append(html);
			}
		});  
})	
	function addTitleType(me){
		$(".hidden").show();
		$(me).hide();
	}
	function realaddTitleType(){
		var typeName=$("#addtitleName").val();
		$.ajax({
			url:"../title/savetitleType.do",
			type:"POST",
			data:{
				typeName:typeName
			},
			success:function(result){
				alert("添加成功");
				$(".hidden").hide();
				var html ="<option value="+result+" >"+typeName+"</option>";
				$("#selecttitleType").append(html);
			}
		})
	}
