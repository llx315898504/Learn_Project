$(function(){
	//获得上下架状态，只要是在页面上获得值都是String类型
	var showStatus = parseInt($("#showStatus").val());
	if(showStatus == 1){
		$("#label4").attr("class", "here");
	}else if(showStatus == 0){
		$("#label5").attr("class", "here");
	}else{
		$("#label6").attr("class", "here");
	}
	
	//获得隐藏域的值
	var pageNo = parseInt($("#currentPageNo").val());
	var totalPage = parseInt($("#totalPage").val());
	/**
	 * 判断翻页按钮展示
	 */
	if(pageNo == 1 && pageNo == totalPage){
		$("#firstPage").hide();
		$("#lastPage").hide();
		$("#previous").hide();
		$("#next").hide();
	}else if(pageNo == 1 && totalPage > pageNo){
		$("#firstPage").hide();
		$("#lastPage").show();
		$("#previous").hide();
		$("#next").show();
	}else if(pageNo > 1 && totalPage > pageNo){
		$("#firstPage").show();
		$("#lastPage").show();
		$("#previous").show();
		$("#next").show();
	}else if(pageNo == totalPage && totalPage > 1){
		$("#firstPage").show();
		$("#lastPage").hide();
		$("#previous").show();
		$("#next").hide();
	}
	
	/**
	 * 点击下一页
	 */
	$("#next").click(function(){
		pageNo++;
		$("#pageNo").val(pageNo);
		$("#form1").submit();
	});
	$("#previous").click(function(){
		pageNo--;
		$("#pageNo").val(pageNo);
		$("#form1").submit();
	});
	$("#firstPage").click(function(){
		$("#pageNo").val(1);
		$("#form1").submit();
	});
	$("#lastPage").click(function(){
		$("#pageNo").val(totalPage);
		$("#form1").submit();
	});
	
	$("#selectPage").change(function(){
		var myPage = $(this).val();
		$("#pageNo").val(myPage);
		$("#form1").submit();
	});
	
	$("#selectPage").val(pageNo);
	
	$("#addItemNoteConfirm").click(function(){
		var notes = $("#itemNote").val();
		$("#notes").val(notes);
		//提交表单
		$("#showForm").submit();
	});
	
});

function isShow(itemId, showStatus){
	//把itemId和showStatus给表单
	$("#itemId").val(itemId);
	$("#showStatus1").val(showStatus);
	$("#addItemNoteH2").html("商品上下架");
	$("#itemNote").val("");
	tipShow("#addItemNote");
}

function publish(itemId){
	tipShow("#refundLoadDiv");
	$.ajax({
		url:path+"/item/publishItem.do",
		type:"post",
		dataType:"text",
		data:{
			itemId:itemId
		},
		success:function(responseText){
			if(responseText == "success"){
				alert("发布成功");
			}else{
				alert("发布失败");
			}
			tipHide("#refundLoadDiv");
		}
	});
}