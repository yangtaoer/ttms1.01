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
	$("#typeLayer").on("click",".btn-cancle",doHideZtree).on("click",".layui-layer-close",doHideZtree);
	$("#btn-return").click(function(){
		doBack();
	});
})
function doHideZtree() {
	//隐藏树
	$("#typeLayer").css("display","none");
}
function loadZtreeNodes() {
	//显示z-tree窗口
	$("#typeLayer").css("display","block");
}
function doBack(){
	var url = "type/listUI.do?s="+Math.random();
	$(".content").load(url);
}