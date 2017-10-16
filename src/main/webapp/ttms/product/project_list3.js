$(document).ready(function(){
	doGetObjects();
})
/*获取项目信息*/
function doGetObjects(){
	//var url="project/doGetObjects.do";
	/*$.ajax({
		url:url,
		type:"get",
		dataType:"json",
		success:function(result){
		}
	});*/
	var url="project/doGetPageObjects.do"
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;//默认取第一页的数据
	var params={"pageCurrent":pageCurrent};
	//发起异步请求获取服务端数据
	$.getJSON(url,params,function(result){//callback method
		//console.log(result);//json对象
		//将数据显示在table的tbody位置
		setTableBodyRows(result.list);//map中的key对应的值
		//设置分页信息(函数定义在了page.js文件中)
		setPagination(result.pageObject);
	});
}
/*将数据填充在table对象的body中*/
function setTableBodyRows(result){//扩展作业
	 debugger
	 //1.获得tbody对象
	 var tBody=$("#tbodyId");
	 tBody.empty();
	 //2.迭代数据集result
	 //for(var i=0;i<result.length;i++){}
	 for(var i in result){
	 //2.1构建一个tr对象
	 var tr=$("<tr></tr>");
	 //2.2构建每行td对象(一行有多个)
	 //var td0=$("<td></td>");
	 //....
	 //2.3在td对象内容填充具体数据
	 //td0.append(result[id].id);
	 //....
	 var tds="<td><input type='checkbox' name='checkId' value='"+result[i].id+"'></td>"+
	         "<td>"+result[i].code+"</td>"+
	         "<td>"+result[i].name+"</td>"+
	         "<td>"+result[i].beginDate+"</td>"+
	         "<td>"+result[i].endDate+"</td>"+
	         "<td>"+(result[i].valid?"有效":"无效")+"</td>"+
	         "<td><input type='button'  class='btn btn-default' value='修改'/></td>";
	 //2.4将td添加到tr对象中(一行要放多个)
	 tr.append(tds);
	 //2.5将tr追加到tbody中
	 tBody.append(tr);
	 }
}



