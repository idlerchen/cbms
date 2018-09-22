/*
 * 从admin的主页跳转过来， 修改admin所拥有的角色(function)
 * 需要实现： 1.根据用户id加载用户信息 admin 2.加载角色列表，role3.根据用户id加载用户拥有的角色 admin_role ；并回显
 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];
$(function(){
	getAdmin();
	getRoles();
	$("#btnSubmit").click(function(){
		$.ajax({url:PROJECTPATH+"/admin/updateRoles.do",
				type:"post",
				data:$("#formUpdateRoles").serialize()+"&adminId="+id,
				success : function(jsonObj){
					if(jsonObj.status==0){
						location = "user.html";
					}
					else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
						processError(jsonObj);
					}
				}
		});
	})
	
})

function getAdmin(){
	$.post(PROJECTPATH+"/admin/getAdminById.do",
			{"adminId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var admin = jsonObj.data;
					$("#username").html(admin.username);
					$("#status").html(admin.status==1?'在职':'离职');
					$("#email").html(admin.email);
					$("#name").html(admin.name);
				}
				else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}

function getRoles(){
	$.post(PROJECTPATH+"/role/All.do",
			function(jsonObj){
				if(jsonObj.status==0){
					var roles = jsonObj.data;
					$("#selectRole").empty();
					$.each(roles,function(i,item){
						$("#selectRole").append(
								'<option value="'+item.id+'">'+item.name+'</option>'
						);
					});
					$("#selectRole").trigger("liszt:updated");
				}
				else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
				loadRoles();
			},
			'json');
}
function loadRoles(){
	$.post(PROJECTPATH+"/admin/getRoleByadminId.do",
			{"adminId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var adminRoles = jsonObj.data;
					var opts = $("#selectRole").children();
					$.each(opts,function(i,opt){
						$.each(adminRoles,function(j,role){
							if(opt.value == role.roleId)
								opt.selected = true;
						})
					});
					$("#selectRole").trigger("liszt:updated");
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}