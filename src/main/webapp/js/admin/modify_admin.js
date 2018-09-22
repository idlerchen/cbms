/*修改业务，页面携带ID跳转过来，获取到iD去异步请求该id的具体信息，渲染至修改页面中去
 * 由于页面中有下拉框需要优先ajax加载，将数据渲染放在这个ajax处理函数中
 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];
$(function(){
	$.post(PROJECTPATH+"/admin/getAdminById.do",
			{"adminId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var admin = jsonObj.data;
					$("#username").val(admin.username);
					$("input[value='"+admin.status+"']").closest("span").addClass("checked");
					$("#email").val(admin.email);
					$("#name").val(admin.name);
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
})

//提交的点击事件
	$("#btnSubmit").click(function(){
		var da = $("#formUpdateAdmin").serialize();
		$.ajax({
				url:PROJECTPATH+"/admin/update.do",
				type:"post",
				data:$("#formUpdateAdmin").serialize()+"&id="+id,
				success : function(jsonObj){
					if(jsonObj.status==0){ //修改成功 跳转页面
						location="user.html";
					}
				}
		});
	})
	//取消的点击事件
	$("#btnCancle").click(function(){
		location = "user.html";
	})
	
