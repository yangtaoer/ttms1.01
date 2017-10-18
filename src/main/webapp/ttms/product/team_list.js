$(function(){
	doGetObjects();
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
});


function doQueryObjects(){ //设置当前页
	//1.初始化当前页面数据
	$("#pageId").data("pageCurrent",1);
	//2根据条件查询数据
	doGetObjects();
}



function doGetObjects(){
	var url = "team/doFindPageObjects.do?t="+Math.random();
	var params = {};
	params.name = $("#searchNameId").val();//设置搜索值
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params.pageCurrent=pageCurrent;//{pageCurrent:1}    params['page-Current']=page......;
	$.post(url,params,function(result){
		if(result.state==1) {
			setTableBodyRows(result.data.list);//设置表格信息
			setPagination(result.data.pageObject);//设置分页信息
		} else {
			alert(result.message);
		}
		
	});
}


function setTableBodyRows(result) {//调用者:doGetObjects 用于设置表格信息
	console.log("set");
	console.log(result);
	//1.获得tbody对象
	var tBody = $('#tbodyId');
	 tBody.empty();
	//2.迭代数据集，result
	//for(var i=0;i<result.length;i++){	}
	for(var i in result){
		//2.1构建一个tr对象
		var tr = $("<tr>.......</tr>");		
		//2.2构建每个tr的td对象（多个）
		//2.3在td对象内容填充具体数据
		var tds = "<td><input type='checkbox' name='checkId' value='"+result[i].id+"'></td>"+
		"<td>"+result[i].name+"</td>"+
		"<td>"+result[i].projectName+"</td>"+		
		"<td>"+(result[i].valid?"有效":"无效")+"</td>"+
		"<td><button type='button' class='btn btn-default btn-success'>修改</button></td>";
		//2.4将td添加到tr对象中
		tr.append(tds);
		//2.5将tr追加到tbody中
		tBody.append(tr);
		
	}
}