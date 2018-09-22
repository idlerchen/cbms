/*
 * 处理登录请求
 */
$(function(){
	$("#btnSubmit").click(function(){
		var url = "admin/login.do";
		var params = $("#formLogin").serialize();
		$.post(url,params,function(jsonObj){
			if(jsonObj.status==0){
				//location = "index.html";
				var indexUrl = jsonObj.data;
				location = indexUrl;
			}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
				processError(jsonObj);
			}
		},'json');
	})
})
