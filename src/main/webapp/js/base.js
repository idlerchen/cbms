/* 每张页面通用的JS脚本 */
var PROJECTPATH='/cbms';

/*异常处理*/
function processError(result){
	if(result.status==1){ //处理失败
		alert(result.msg);
	}
	else if(result.status==2){//没有登录用户
		location = "login.html";
	}
	else if(result.status==3){//没有权限操作
		alert(result.msg);
	}
}

