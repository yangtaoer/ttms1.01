var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];//定义table表头及每列元素信息

$(function(){
	$("#formHead")
	.on("click",".btn-delete",doDeleteObject)
	.on("click",".btn-add",doLoadEditPage)	
	doGetObjects();
});
function doLoadEditPage() {//加载添加分类页面
	var url = "type/editUI.do";
	$(".content").load(url,function(){
		$("#pageTitle").html("添加分类");
	});	
}
function doDeleteObject() {
	//1.获取id值
	var id = getSeletedId();
	//2.删除id值删除分类信息
	var url = "type/doDeleteObject.do";
	$.getJSON(url,{"id":id},function(result){
		alert(result.message);
		doGetObjects();
	});
}
function getSeletedId(){//用于获取id
	//1.获取选中的id
	var selections = $("#typeTable").bootstrapTreeTable("getSelections");
	console.log(selections)
	if(selections.length==0) {
		alert("请先选择元素!");
		return; 
	}
	return selections[0].id;
}
function doGetObjects() {
	var tableId="typeTable";//不需要加#因为已经封装
	var url="type/doFindObjects.do";
	//TreeTable在table.js中定义
	var table = new TreeTable(tableId, url, columns);
	//初始化table对象,底层会发送异步请求获取数据并更新页面
	table.init();
	
}

