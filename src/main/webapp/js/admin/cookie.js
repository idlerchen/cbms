/*
 *给所有页面设置登录状态，以及登出 
 */

$(function(){
	var username = $.cookie("username");
	if(!username){
		location="login.html";
	}
	$(".dropdown-toggle").html('<i class="halflings-icon white user"></i>'+username+'<span class="caret"></span>');
	$(".dropdown-menu li:last").attr("href","admin/logout.do");
})
