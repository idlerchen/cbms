/*
 * role列表的js
 * role列表的加载显示，模糊查询，分页器，添加、修改等按钮链接
 */

$(function(){
	//access(jsonObj);
	getPages();
	$("#inputSearch").on('change',function(){//模糊查询时重新加载Role列表、分页器，默认第一页
		getRoles(1);
		getPages();
	});
})

function getRoles(num){
	var searchName = $("#inputSearch").val();
	var params = {"num":num,"keyword": searchName}; 
	$.get("role/roles.do",
			params,
			function(jsonObj){
				if(jsonObj.status==0){
					var roles = jsonObj.data;
					$("#tbody").empty();//防止数据不刷新，先empty一下
					$.each(roles,function(i,item){
						var createTime = getMyDate(item.createTime);
						$("#tbody").append(	'<tr>'+
											'	<td class="center">'+item.id+'</td>'+
											'	<td class="center">'+item.name+'</td>'+
											'	<td class="center">'+createTime+'</td>'+
											'	<td>'+
											'		<a class="btn btn-success" href="modify_role.html?id='+item.id+'"><span>修改</span></a>'+
											'		<a class="btn btn-danger" href="javascript:deleteRole('+item.id+')"><span>删除</span></a>'+
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
	$.post(PROJECTPATH+"/role/count.do",
			{"keyword":searchName},
			function(jsonObj){
				if(jsonObj.status==0){
					var counts = jsonObj.data;
					 $.jqPaginator('#paginator', {
					        totalCounts: counts,
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
					        	getRoles(num);
					        }
					    });
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			},
			'json');
}

//删除指定课时；
function deleteRole(id){
	var url=PROJECTPATH+"/role/delete.do";
	var params={"id":id};
	$.post(url,
			params,
			function(jsonObj){
				if(jsonObj.status==0){
					getRoles(currentNum);
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
    oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin)+':'+ getzf(oSen);//最后拼接时间    
    return oTime;  
};  
//补0操作  
function getzf(num){  
    if(parseInt(num) < 10){  
        num = '0'+num;  
    }  
    return num;  
}  