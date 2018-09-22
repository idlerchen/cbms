var href = window.location.href; //当前路径 :   http://xxxxx/xxxx?id=1&title=.. 获取id
var id = (href.split("?")[1]).split("=")[1];

$(function(){
	var url = PROJECTPATH+"/lesson/alessons.do";
	var params = {"courseId":id};
	$.post(url,params,
			function(jsonObj){
				if(jsonObj.status==0){
					var lessons = jsonObj.data;
					$("#courseTitle").text(lessons[0].courseTitle);
					$("#tbody").empty();
					$.each(lessons,function(i,item){
						var start = getMyDate(item.startTime);
						var end = getMyDate(item.endTime);
						$("#tbody").append(
								'<tr>'+
								'<td class="center">'+item.title+'</td>'+
								'<td class="center">'+item.teacherName+'</td>'+
								'<td class="center">'+start+'-'+end+'</td>'+
							'</tr>');
					})
				}else{ //异常处理 1-处理失败 2-没有登录用户 3-没有权限操作
					processError(jsonObj);
				}
			}
	);
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
        
function GetUrlByParamName(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var URL =  decodeURI(window.location.search);
    var r = URL.substr(1).match(reg);
    if(r!=null){
        //decodeURI() 函数可对 encodeURI() 函数编码过的 URI 进行解码
        return  decodeURI(r[2]);
    };
    return null;
};
