$(document).ready(function(){
	//注册事件
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	
	doGetObjects();
})

function doValidById(){
	
	//1.设置valid的值
	
	var valid;
	
	//1.1获得点击的button对象，根据点击的对象的不同设置不同的valid值            $(this)即点击的对象.
	if($(this).hasClass("btn-valid")){
		valid = 5;	
	}
	
	if($(this).hasClass("btn-invalid")){
		valid = 0;
	}
	
	//2.获得选中的checkbox的id值
	var ids="";
	$("#tbodyId input[name='checkId']")
	.each(function(){//each函数用于迭代一个数组
		if($(this).prop("checked")){//判断该input对象是否是选中的
			if(ids==""){
				ids+=$(this).val();
			}else {
				ids+=","+$(this).val();
			}
			
			
		}
		
		
	});
	console.log("valid="+valid);
	console.log("ids="+ids);
	if(ids==""){
		alert("请至少选择一个！");
		return;
	}	
	
    //3.发起异步请求，更新数据
	var url="project/doValidById.do";
	var params = {
			"valid":valid,
			"ids":ids		
	}

	$.post(url,params,function(result){
		//重新执行查询操作--判断是否更新成功
		if(result.state==1) {
		    alert(result.message);	
		    doGetObjects();
		}else if(result.state==0){
			alert(result.message);
		}
	})


}





function doQueryObjects(){
	//1.初始化当前页面数据
	$("#pageId").data("pageCurrent",1);
	//2根据条件查询数据
	doGetObjects();
}




/*获取项目信息*/
function doGetObjects(){
	var url = "project/doGetPageObjects.do"
		var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent) pageCurrent=1;//默认取第一页的数据
	var params={"pageCurrent":pageCurrent};
	params.name = $("#searchNameId").val();
	params.valid = $("#searchValidId").val();
	console.log(params);
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
function setTableBodyRows(result){

	//1.获得tbody对象
	var tBody = $('#tbodyId');
	 tBody.empty();
	//2.迭代数据集，result
	
	//for(var i=0;i<result.length;i++){	}
	for(var i in result){
		//2.1构建一个tr对象
		var tr = $("<tr></tr>");
		//2.2构建每个tr的td对象（多个）
		//2.3在td对象内容填充具体数据
		var tds = "<td><input type='checkbox' name='checkId' value='"+result[i].id+"'></td>"+
		"<td>"+result[i].code+"</td>"+
		"<td>"+result[i].name+"</td>"+
		"<td>"+result[i].beginDate+"</td>"+
		"<td>"+result[i].endDate+"</td>"+
		"<td>"+(result[i].valid?"有效":"无效")+"</td>"+
		"<td><button type='button' class='btn btn-success'>修改</button></td>";
		//2.4将td添加到tr对象中
		tr.append(tds);
		//2.5将tr追加到tbody中
		tBody.append(tr);
		
	}
	
}