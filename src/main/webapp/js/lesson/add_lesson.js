
/* 添加讲师页面中所需要的js脚本 */
$(function(){
	//页面加载后，加载需要的课程名字和讲师名字
	$.post(
			"lesson/coursesAndTeachers.do",
			function(jsonObj){
				if(jsonObj.status==0){
					var courses=jsonObj.data.courses;
					var teachers=jsonObj.data.teachers;
					$("#courseName").empty();
					$.each(courses,function(i,item){
						$("#courseName").append('<option value="'+item.id+'">'+item.title+'</option>');
					});
					$("#courseName").trigger("liszt:updated");
					$("#teacherName").empty();
					$.each(teachers,function(i,item){
						$("#teacherName").append('<option value="'+item.id+'">'+item.name+'</option>');
					})
					$("#teacherName").trigger("liszt:updated");
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
	//页面加载完毕后为按钮添加监听
	$("#btnSubmit").click(function(){//两个下拉框的选值会自动选中
		$.ajax({
			url:PROJECTPATH+"/lesson/addLesson.do",
			type:'post',
			data:$("#formAddLesson").serialize(), // "id=asdasd&s=000&name=1233"
			success:function(jsonObj){  
				if(jsonObj.status==0){ //成功
					window.location=PROJECTPATH+"/class.html";
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
		})
	});
	
	$("#btnCancel").click(function(){
		window.location=PROJECTPATH+"/class.html";
	});
	
});

