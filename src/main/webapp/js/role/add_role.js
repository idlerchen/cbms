/*
add_role.js - add_role.html
1.function表的select展示，
2.发送请求 ，增加role表，同时增加role_function联系表
*/

$(function(){
	
	getFunctions();
	$("#btnSubmit").click(function(){
		$.ajax({
			url:PROJECTPATH+"/role/add.do",
			type:"post",
			data:$("#formAddRole").serialize(),
			success:function(jsonObj){
				if(jsonObj.status==0){
					location = "index.html";
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
		})
	})
})

function getFunctions(){
	$.post(
			PROJECTPATH+"/role/getFunction.do",
			function(jsonObj){
				if(jsonObj.status==0){
					var functions = jsonObj.data;
					$("#selectFunction").empty();
					$.each(functions,function(i,item){
						$("#selectFunction").append('<option value="'+item.id+'">'+item.name+'</option>')
					})
					$("#selectFunction").trigger("liszt:updated");
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
	
	);
}