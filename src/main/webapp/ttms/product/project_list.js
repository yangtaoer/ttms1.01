$(document).ready(function(){
	//注册事件
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	.on("click",".btn-add",doLoadEditPage);
	$("#tbodyId").on("click",".btn-default",doLoadEditPage);
	doGetObjects();
})


function doLoadEditPage() {
	var url="project/editUI.do";
	//$(".context").load(url);  异步加载页面到节点
	//模态框中异步加载显示编辑页面
	//本项目中模态框的定义,在index.jsp中,默认隐藏
	var title;
	if($(this).hasClass("btn-add")) {
		title="添加项目";
	}
	if($(this).hasClass("btn-success")) {
		var idValue = $(this).parent().parent().data("id");
		$("#modal-dialog").data("idKey",idValue);
		title = "修改项目----id="+idValue;
		
	}
	$("#modal-dialog .modal-body").load(url,function(){//回调函数
		//bootstrap在jquery基础上写的,show表示显示,hide表示隐藏
		$("#modal-dialog").modal("show");
		$(".modal-title").html(title);
	});
}


function doValidById(){
	
	//1.设置valid的值
	
	var valid;
	
	//1.1获得点击的button对象，根据点击的对象的不同设置不同的valid值            $(this)即点击的对象.
	if($(this).hasClass("btn-valid")){
		valid = 1;	
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
		tr.data("id",result[i].id);
		//2.2构建每个tr的td对象（多个）
		//2.3在td对象内容填充具体数据
		var tds = "<td><input type='checkbox' name='checkId' value='"+result[i].id+"'></td>"+
		"<td>"+result[i].code+"</td>"+
		"<td>"+result[i].name+"</td>"+
		"<td>"+result[i].beginDate+"</td>"+
		"<td>"+result[i].endDate+"</td>"+
		"<td>"+(result[i].valid?"有效":"无效")+"</td>"+
		"<td><button type='button' class='btn btn-default btn-success'>修改</button></td>";
		//2.4将td添加到tr对象中
		tr.append(tds);
		//2.5将tr追加到tbody中
		tBody.append(tr);
		
	}
	
}