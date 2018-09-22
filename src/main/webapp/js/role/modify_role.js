/* 修改角色_功能的js，
 * 1.roleid 回显  role .name  role - role_function中对应的function
 * 2.提交请求， 修改role.name 以及role 对应的function
 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];
$(function(){
	getRole();
	getFunctions();
	$("#btnSubmit").click(function(){
		$.ajax({url:PROJECTPATH+"/role/update.do",
				type:"post",
				data:$("#formUpdateRole").serialize()+"&id="+id,
				success : function(jsonObj){
					if(jsonObj.status==0){
						location = "index.html";
					}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
						processError(jsonObj);
					}
				}
		});
	})
})
function getRole(){
	$.post(
			PROJECTPATH+"/role/getRole.do",
			{"roleId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var role = jsonObj.data;
					$("#inputName").val(role.name);
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
	);
}
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
				getRoleFunction();
			}
	
	);
}
function getRoleFunction(){
	$.post(
			PROJECTPATH+"/role/getRoleFunction.do",
			{"roleId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var rolefunctions = jsonObj.data;
						var opts = $("#selectFunction").children();
						$.each(opts,function(i,opt){
							$.each(rolefunctions,function(j,rolefunction){
								if(opt.value == rolefunction.functionId)
									opt.selected = true;
							})
						});
					$("#selectFunction").trigger("liszt:updated");
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			})
}