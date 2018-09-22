var E = window.wangEditor
var editor = new E('#div1', '#div2'); // 两个参数也可以传入 elem 对象，class 选择器，此前参数为tools栏和文本框栏
//上传头像的设置，先将图片上传至服务器保存，再返回保存的路径到字段中，最后写入数据库
editor.customConfig.showLinkImg = false;
editor.customConfig.uploadImgServer = PROJECTPATH + "/course/upload.do";
editor.customConfig.uploadImgMaxLength = 5;
editor.customConfig.uploadFileName = 'file';
editor.customConfig.uploadImgHeaders = {
	'Accept': 'multipart/form-data'
};
editor.create();

/* 添加课程页面中所需要的js脚本 */
$(function(){
	//页面加载完毕后为按钮添加监听
	$("#btnSubmit").click(function(){
		//使用FormData对象构建表单数据集，用于向服务端传参
		var desc = editor.txt.html();
		alert(desc);
		$.ajax({
			url:PROJECTPATH+"/course/addCourse.do",
			type:'post',
			data:$("#formAddCourse").serialize()+"&description="+desc, // "id=asdasd&s=000&name=1233"
			success:function(jsonObj){  
				//jsonObj:  {status:0, msg:'', data:响应数据 }
				if(jsonObj.status==0){ //成功
					window.location=PROJECTPATH+"/course.html";
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
		})
	});
	$("#btnCancel").click(function(){
		window.location=PROJECTPATH+"/course.html";
	});
	
	//加载课程类别列表
	var url = PROJECTPATH+"/courseType/queryAll.do";
	$.get(url, function(jsonObj){
		if(jsonObj.status==0){
			$("#selCourseType").empty();
			var list = jsonObj.data;
			$.each(list, function(index, item){
				$("#selCourseType").append('<option value="'+item.id+'">'+item.name+'</option>');
			});
		}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
			processError(jsonObj);
		}
	}, 'json');
	
	
});

