$(document).ready(function(){
	$("#modal-dialog")
	.on("click",".ok",doSaveOrUpdate);
	//在模态框隐藏后移除ok上注册的事件,防止数据多次提交
	$("#modal-dialog").on('hidden.bs.modal' ,function(e){
		clear();
	});
});

function clear(){//移除绑定事件
	$("#modal-dialog").off("click",".ok");
}

/*添加或修改数据*/
function doSaveOrUpdate() {
	//1.获得表单数据
	console.log(1111111);
	var params  =getEditFormData();
	console.log(params);
	//2.异步提交表单数据
	var url = "project/doSaveObject.do"
	$.post(url,params,function(result){
		if(result.state==1) {
			//关闭模态框
			$("#modal-dialog").modal("hide");
			alert(result.message);
			doGetObjects();
		} else {
			alert(result.message);
		}
	});
	
}

function getEditFormData(){
	var params = {
			name:$("#nameId").val(),
			code:$("#codeId").val(),
			beginDate:$("#beginDateId").val(),
			endDate:$("#endDateId").val(),
			valid:$("input[type='radio']:checked").val(),
			note:$("#noteId").val()
	}//Json对象
	return params;
}