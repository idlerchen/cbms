var E = window.wangEditor
var editor = new E('#div1', '#div2') // 两个参数也可以传入 elem 对象，class 选择器，此前参数为tools栏和文本框栏
editor.customConfig.showLinkImg = false;
editor.customConfig.uploadImgServer = PROJECTPATH + "/course/upload.do";
editor.customConfig.uploadImgMaxLength = 5;
editor.customConfig.uploadFileName = 'file';
editor.customConfig.uploadImgHeaders = {
	'Accept': 'multipart/form-data'
};
editor.create()
/*修改业务，页面携带ID跳转过来，获取到iD去异步请求该id的具体信息，渲染至修改页面中去
 * 由于页面中有下拉框需要优先ajax加载，将数据渲染放在这个ajax处理函数中
 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];
$(function(){
	$.post(PROJECTPATH+"/teacher/getTeacherById.do",
			{"teacherId":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var teacher = jsonObj.data;
					$("#teacherName").val(teacher.name);
					$("input[value='"+teacher.gender+"']").closest("span").addClass("checked");
					$("#mail").val(teacher.email);
					$("#account").val(teacher.username);
					$("#photo").attr("src",teacher.photoUrl);
					editor.txt.html(teacher.description);
					
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
})

//提交的点击事件
	$("#btnSubmit").click(function(){
		var formdata = new FormData($("#formUpdateTeacher")[0]);
		formdata.append("id",id);
		formdata.append("description",editor.txt.html());
		$.ajax({
				url:"teacher/updateTeacher.do",
				type:"post",
				data:formdata,
				processData:false,
				contentType:false,
				success : function(jsonObj){
					if(jsonObj.status==0){ //修改成功 跳转页面
						location="teachers.html";
					}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
						processError(jsonObj);
					}
				}
		});
	})
	//取消的点击事件
	$("#btnCancle").click(function(){
		location = "teachers.html";
	})
	
