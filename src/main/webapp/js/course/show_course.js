$(function(){
	getPages();
	$("#inputSearch").on('change',function(){//模糊查询时重新加载课程列表、分页器，默认第一页
		getAllCourse(1);
		getPages();
	});
})
/*加载课程列表，带上查询字段，当没有输入字段时，为空，也可查出所有记录；带上页码，默认会是第一页，在点击分页器中页码时，
会传入页码并调用getAllCourse()重新加载列表；加载列表，即通过body append内容至页面
*/
function getAllCourse(num){
	var url = PROJECTPATH+"/course/selectAll.do";
	var searchTitle = $("#inputSearch").val();
	var params = {"num":num,"keyword": searchTitle}; 
	$.get(url, 
			params,
			function(jsonObj){
		if(jsonObj.status==0){
			var list = jsonObj.data;
			$("#tbody").empty();//防止数据不刷新，先empty一下
			$.each(list, function(index, item){
				var start = getMyDate(item.startTime);
				var end = getMyDate(item.endTime);
				$("#tbody").append('<tr>'+
						'	<td class="center">'+item.id+'</td>'+
						'	<td class="center" width="450px">'+item.title+'</td>'+
						'	<td style="text-align:center;" class="center">'+start+' - '+end+'</td>'+
						'	<td style="text-align:center;" class="center">'+(item.status==0?'未上架': (item.status==1?'已上架':'已下架') )+'</td>'+
						'	<td >'+
						'		<a class="btn btn-danger" href="info_course.html?id='+item.id+'"><span>查看</span></a>'+
						'		<a class="btn btn-success" href="modify_course.html?id='+item.id+'"><span>修改</span></a>'+
						'		<a class="btn btn-info" href="javascript:updateStatus('+item.id+',1);"><span>上架</span></a>'+
						'		<a class="btn btn-warning" href="javascript:updateStatus('+item.id+',2);"><span>下架</span></a>'+
						'	</td>'+
						'</tr>');
			});
		}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
			processError(jsonObj);
		}
	}, 'json');
}

/*
 * 获取分页器，即获取记录数，再实例化jqPaginator，根据记录数及设置的一页显示数分配页码，初始化分页器；
 * 当点击分页中的数字时，触发回调函数，会将页码数字传入getAllCourse()中，根据此时的查询字段和页码去sql中获取记录返回
 * 传入controller后，会根据页码去计算limit中的两个字段。
 */
var currentNum;
function getPages(){
	var searchTitle = $("#inputSearch").val();
	$.post(PROJECTPATH+"/course/countAll.do",
			{"keyword":searchTitle},
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
					        	getAllCourse(num);
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
	$.post(PROJECTPATH+"/course/updateStatus.do",
			{"courseId":id,"status":status},
			function(jsonObj){
				if(jsonObj.status==0){
					getAllCourse(currentNum);
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
			
	);
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