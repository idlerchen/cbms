/*
 * admin列表的js
 * admin列表的加载显示，模糊查询，分页器，添加、修改等按钮链接
 */

$(function(){
	getPages();
	$("#inputSearch").on('change',function(){//模糊查询时重新加载admin列表、分页器，默认第一页
		getAdmins(1);
		getPages();
	});
})

function getAdmins(num){
	var searchName = $("#inputSearch").val();
	var params = {"num":num,"keyword": searchName}; 
	$.get("admin/admins.do",
			params,
			function(jsonObj){
				if(jsonObj.status==0){
					var admins = jsonObj.data;
					$("#tbody").empty();//防止数据不刷新，先empty一下
					$.each(admins,function(i,item){
						$("#tbody").append(	'<tr>'+
											'	<td class="center">'+item.username+'</td>'+
											'	<td class="center">'+item.name+'</td>'+
											'	<td class="center">'+item.email+'</td>'+
											'	<td class="center">'+(item.status==1?'在职':'离职')+'</td>'+
											'	<td>'+
											'		<a class="btn btn-success" href="modify_user.html?id='+item.id+'"><span>修改</span></a>'+
											'		<a class="btn btn-info" href="fuction_user.html?id='+item.id+'"><span>功能</span></a>'+
											'		<a class="btn btn-danger" href="javascript:deleteAdmin('+item.id+')"><span>删除</span></a>'+
											'	</td>'+
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
	$.post(PROJECTPATH+"/admin/count.do",
			{"keyword":searchName},
			function(jsonObj){
				if(jsonObj.status==0){
					var Counts = jsonObj.data;
					 $.jqPaginator('#paginator', {
					        totalCounts: Counts,
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
					        	getAdmins(num);
					        }
					    });
				}
				else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}

//删除指定课时；
function deleteAdmin(id){
	var url="admin/delete.do";
	var params={"id":id};
	$.post(url,
			params,
			function(jsonObj){
				if(jsonObj.status==0){
					getAdmins(currentNum);
				}
				else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
	'json');
}