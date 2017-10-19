var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(document).ready(function(){
	$("#editTypeForm").on("click",".load-product-type",loadZtreeNodes);
	$("#typeLayer").on("click",".btn-cancle",doHideZtree)
	.on("click",".layui-layer-close",doHideZtree)
	.on("click",".btn-confirm",doSetType);
	$("#btn-return").click(function(){
		doBack();
	});
	$("#btn-save").click(function(){//保存分类
		doSaveObject();
	});
})
function doSaveObject() {
	//1.获取表单数据
	if(!$("#editTypeForm").valid())return;
	var params = getEditFormData();
	//2.保存数据
	var url = "type/doSaveObject.do";
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);
			doBack();
		} else {
			alert(result.message);
		}
	});
}
function getEditFormData(){//调用者: doSaveObject 用于获取表单数据
	var params = {
			name : $("#typeNameId").val(),
			parentId : $("#parentNameId").data("parentId"),
			sort : $("#typeSortId").val(),
			note : $("#typeNoteId").val()
	}
	return params;
}
function doSetType() {//设置上级分类信息
	//1.获得选中的数据信息
	var nodes = zTree.getSelectedNodes();
	//2.将数据信息填充在form表单中
	$("#parentNameId").val(nodes[0].name); 
	$("#parentNameId").data("parentId",nodes[0].id);	
	//3.隐藏zTree
	doHideZtree();
}
function doHideZtree() {
	//隐藏树
	$("#typeLayer").css("display","none");
}
function loadZtreeNodes() {
	//显示z-tree窗口
	$("#typeLayer").css("display","block");
	var url = "type/doFindZtreeNodes.do";
	$.getJSON(url,function(result){

		if(result.state==1) {
			zTree = $.fn.zTree.init($("#typeTree"),setting,result.data);//jquery的函数,初始化ztree的数据
		} else {
			alert(result.message);
		}
	});
}
function doBack(){
	var url = "type/listUI.do?s="+Math.random();
	$(".content").load(url);
}