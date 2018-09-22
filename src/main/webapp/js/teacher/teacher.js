/*
 * 教师列表的js
 * 教师列表的加载显示，模糊查询，分页器，添加、修改等按钮链接
 */

$(function(){
	getPages();
	$("#inputSearch").on('change',function(){//模糊查询时重新加载课程列表、分页器，默认第一页
		getTeachers(1);
		getPages();
	});
})

function getTeachers(num){
	var searchName = $("#inputSearch").val();
	var params = {"num":num,"keyword": searchName}; 
	$.get("teacher/teachers.do",
			params,
			function(jsonObj){
				if(jsonObj.status==0){
					var teachers = jsonObj.data;
					$("#tbody").empty();//防止数据不刷新，先empty一下
					$.each(teachers,function(i,item){
						$("#tbody").append(	'<tr>'+
												'<td class="center">'+item.id+'</td>'+
												'<td class="center">'+item.name+'</td>'+
												'<td class="center">'+item.email+'</td>'+
												'<td class="center">'+(item.status==0?'离职':'在职')+'</td>'+
												'<td>'+
												'	<a class="btn btn-success" href="modify_teacher.html?id='+item.id+'"><span>修改</span></a>'+
												'	<a class="btn btn-info" href="javascript:updateStatus('+item.id+',1)"><span>入职</span></a>'+
												'	<a class="btn btn-danger" href="javascript:updateStatus('+item.id+',0)"><span>离职</span></a>'+
												'</td>'+
											'</tr>');
					})
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}


var currentNum;
function getPages(){
	var searchName = $("#inputSearch").val();
	$.post(PROJECTPATH+"/teacher/countAll.do",
			{"keyword":searchName},
			function(jsonObj){
				if(jsonObj.status==0){
					var courseCounts = jsonObj.data;
					 $.jqPaginator('#paginator', {
					        totalCounts: courseCounts,
					        pageSize:5,
					        visiblePages: 5,
					        currentPage: 1,
					        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
					        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
					        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
					        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
					        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
					        onPageChange: function (num, type) { 
					        	//当点击分页器中的页码li时，将会执行该函数，并且把num页码回调回来
					        	currentNum = num;
					        	getTeachers(num);
					        }
					    });
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}

//上架下架更新课程状态
function updateStatus(id,status){
	$.post(PROJECTPATH+"/teacher/updateStatus.do",
			{"teacherId":id,"status":status},
			function(jsonObj){
				if(jsonObj.status==0){
					getTeachers(currentNum);
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
			
	);
}