/*修改业务，页面携带ID跳转过来，获取到iD去异步请求该id的具体信息，渲染至修改页面中去
 * 由于页面中有下拉框需要优先ajax加载，将数据渲染放在这个ajax处理函数中
 */
var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1
var id = (href.split("?")[1]).split("=")[1];
$(function(){
	//加载课程列表 和 讲师列表 
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
				getLesson();
			},
	'json');
	getLesson();
})

function getLesson(){
	$.post(PROJECTPATH+"/lesson/getLessonById.do",
			{"id":id},
			function(jsonObj){
				if(jsonObj.status==0){
					var lesson = jsonObj.data;
					$("#teacherName").val(lesson.teacherId);
					$("#teacherName").trigger("liszt:updated");
					$("#courseName").val(lesson.courseId);
					$("#courseName").trigger("liszt:updated");
					$("#className").val(lesson.title);
					$("#dateStart").val(getMyDate(lesson.startTime));
					$("#dateEnd").val(getMyDate(lesson.endTime));
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}

//提交的点击事件
	$("#btnSubmit").click(function(){
		$.ajax({
				url:"lesson/update.do",
				type:"post",
				data:$("#formUpdateLesson").serialize()+"&id="+id, // "id=asdasd&s=000&name=1233"
				success : function(jsonObj){
					if(jsonObj.status==0){ //修改成功 跳转页面
						location="class.html";
					}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
						processError(jsonObj);
					}
				}
		});
	})
	//取消的点击事件
	$("#btnCancle").click(function(){
		location = "class.html";
	})
	
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