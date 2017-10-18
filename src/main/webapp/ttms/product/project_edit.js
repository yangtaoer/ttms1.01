$(document).ready(function(){
	$("#modal-dialog")
	.on("click",".ok",doSaveOrUpdate);
	//在模态框隐藏后移除ok上注册的事件,防止数据多次提交
	$("#modal-dialog").on('hidden.bs.modal' ,function(e){
		clear();
	});
	var id = $("#modal-dialog").data("idKey");
	if(id!=null) {

		doFindObjectById(id);
	}
});


/**
 * 根据id 执行查询操作
 */
function doFindObjectById(id) {
	
	var url = "project/doFindObjectById.do";
	var params = {"id":id};
	$.getJSON(url,params,function(result){
		if(result.state==1) {

			doInitFormData(result.data);
		} else if(result.state==0) {
			alert(result.message);
		}
	});
}

//初始化表单数据
function doInitFormData(data) {
	$("#nameId").val(data.name);
	$("#codeId").val(data.code);
	$("#beginDateId").val(data.beginDate);
	$("#endDateId").val(data.endDate);
	$("#noteId").html(data.note);
	$("#editFormId input[name='valid']").each(function(){
		if($(this).val()==data.valid) {
			$(this).prop("checked",true);//给input框赋值checked
		}
	});
}

function clear(){//移除绑定事件,并且移除绑定的id值
	$("#modal-dialog").off("click",".ok").removeData("idKey");
	
}

/*添加或修改数据*/
function doSaveOrUpdate() {
	//1.获得表单数据

	var params  = getEditFormData();

	//2.异步提交表单数据
	var insertUrl = "project/doSaveObject.do"
	var updateUrl = "project/doUpdateObject.do"	
		//根据id判断是添加还是修改数据,并且添加id的值到json数据发送到处理器
	var id = $("#modal-dialog").data("idKey");
	var url = id?updateUrl:insertUrl;
	if(id)params.id=id;
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

//获取表单数据
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