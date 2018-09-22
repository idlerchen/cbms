/*发送请求，获取用户有的功能，选取显示的目录*/
//先将页面的角色目录置空
$(".main-menu").empty();
$(function(){
	getAccess();
})

function getAccess(){
	var url = PROJECTPATH+"/admin/getAccess.do";
	$.post(url,function(jsonObj){
		if(jsonObj.status==0){
			var functions = jsonObj.data;
			$.each(functions,function(i,item){
				if(item.id==1){
					$(".main-menu").append('<li>\
						<a href="index.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> 角色管理</span></a>\
					</li>');
				}else if(item.id==2){
					$(".main-menu").append('<li>\
							<a href="user.html"><i class="icon-edit"></i><span class="hidden-tablet"> 用户管理</span></a>\
							</li>');
				}else if(item.id==3){
					$(".main-menu").append('<li>\
							<a href="course.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> 直&nbsp;&nbsp;播&nbsp;&nbsp;课</span></a>\
							</li>');			
				}else if(item.id==4){
					$(".main-menu").append('<li>\
							<a href="teachers.html"><i class="icon-heart"></i><span class="hidden-tablet"> 教师管理</span></a>\
							</li>');
				}else if(item.id==5){
					$(".main-menu").append('<li>\
							<a href="class.html"><i class="icon-tag"></i><span class="hidden-tablet"> 课时管理</span></a>\
							</li>');
				}
			})
		}
	},
	'json'
	)
}