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
		getCourse();
	}, 'json');
})

//提交的点击事件
	$("#btnSubmit").click(function(){
		var formdata = new FormData($("#updateFormData")[0]);
		formdata.append("id",id);
		formdata.append("description",editor.txt.html());
		$.ajax({
				url:"course/updateCourse.do",
				type:"post",
				data:formdata,
				processData:false,
				contentType:false,
				success : function(jsonObj){
					if(jsonObj.status==0){ //修改成功 跳转页面
						location="course.html";
					}
				}
		});
	})
	//取消的点击事件
	$("#btnCancle").click(function(){
		window.location = "course.html";
	})
	
//加载需要修改的课程信息，放在课程分类后面
function getCourse(){
	$.post(PROJECTPATH+"/course/getCourseById.do",
			{"id":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var course = jsonObj.data;
					$("#inputTitle").val(course.title);
					editor.txt.html(course.description);
					$("#selCourseType").val(course.typeId);
					$("#inputPrice").val(course.price);
					$("#inputTotal").val(course.total);
					$("#inputStartTime").val(getMyDate(course.startTime));
					$("#inputEndTime").val(getMyDate(course.endTime));
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}







//封装时间
        function getMyDate(str){  
            var oDate = new Date(str),  
            oYear = oDate.getFullYear(),  
            oMonth = oDate.getMonth()+1,  
            oDay = oDate.getDate(),  
            oHour = oDate.getHours(),  
            oMin = oDate.getMinutes(),  
            oSen = oDate.getSeconds(),  
            oTime = oYear +'/'+ getzf(oMonth) +'/'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin);//最后拼接时间  
            return oTime;  
        };  
        //补0操作  
        function getzf(num){  
            if(parseInt(num) < 10){  
                num = '0'+num;  
            }  
            return num;  
        }  