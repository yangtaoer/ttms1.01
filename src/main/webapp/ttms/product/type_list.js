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
	doGetObjects();
});
function doGetObjects() {
	var tableId="typeTable";//不需要加#因为已经封装
	var url="type/doFindObjects.do";
	//TreeTable在table.js中定义
	var table = new TreeTable(tableId, url, columns);
	//初始化table对象,底层会发送异步请求获取数据并更新页面
	table.init();
	
}

