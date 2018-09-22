
/* 添加admin页面中所需要的js脚本 */
$(function(){
	//给两次密码加监听
	$("#repassword").change(function(){
		var pwd = $("#password").val();
		var repwd = $("#repassword").val();
		if(pwd!=repwd){
			$("#msg").html("密码不一致");
			$("#btnSubmit").attr("disabled", true);
		}else{
			$("#msg").html("");
			$("#btnSubmit").attr("disabled", false);
			
		}
	})
	//页面加载完毕后为按钮添加监听
	$("#btnSubmit").click(function(){//两个下拉框的选值会自动选中
		var da = $("#formAddAdmin").serialize();
		$.ajax({
			url:PROJECTPATH+"/admin/add.do",
			type:'post',
			data:$("#formAddAdmin").serialize(), // "id=asdasd&s=000&name=1233"
			success:function(jsonObj){  
				if(jsonObj.status==0){ //成功
					window.location=PROJECTPATH+"/user.html";
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
		})
	});
	
	$("#btnCancel").click(function(){
		window.location=PROJECTPATH+"/user.html";
	});
	
});

