function submitUpload(){
	var option = {
			url:path+"/upload/uploadPic.do",//上传的url
			dataType:"text",//回调值的数据类型
			success:function(responseText){
				//{"realPath":"http://...", "relativePath":"/upload/.."}
				var jsonObj = $.parseJSON(responseText);
				$("#imgsImgSrc").attr("src", jsonObj.realPath);
				$("#imgs").val(jsonObj.relativePath);
				$("#lastRealPath").val(jsonObj.realPath);
			},
			error:function(){
				alert("系统错误");
			}
	}
	//使用ajax方式提交表单，上传文件
	$("#form111").ajaxSubmit(option);
	
}

$(function(){
	
	$("#form111").submit(function(){
		var isSubmit = true;
		//校验必填字段
		$(this).find("[reg2]").each(function(){
			//获得输入的值
			var val = $(this).val();
			val = $.trim(val);
			//获得正则
			var reg = $(this).attr("reg2");
			//获得提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var regExp = new RegExp(reg);
			if(!regExp.test(val)){
				$(this).next("span").html("<font color='red'>"+tip+"</font>");
				isSubmit = false;
				//在jQuery中跳出循环要使用return false;
				return false;
			}else{
					$(this).next("span").html("");
			}
			
		});
		
		$(this).find("[reg1]").each(function(){
			//获得输入的值
			var val = $(this).val();
			val = $.trim(val);
			//获得正则
			var reg = $(this).attr("reg1");
			//获得提示信息
			var tip = $(this).attr("tip");
			//创建正则表达式的对象
			var regExp = new RegExp(reg);
			if(val !=null && val != "" && !regExp.test(val)){
				$(this).next("span").html("<font color='red'>"+tip+"</font>");
				isSubmit = false;
				//在jQuery中跳出循环要使用return false;
				return false;
			}else{
				$(this).next("span").html("");
			}
			
		})
		//防止表单二次提交
		if(isSubmit){
			tipShow("#refundLoadDiv");
		}
		return isSubmit;
	});
	
	
	$("#form111").find("[reg2]").blur(function(){
		//获得输入的值
		var val = $(this).val();
		val = $.trim(val);
		//获得正则
		var reg = $(this).attr("reg2");
		//获得提示信息
		var tip = $(this).attr("tip");
		//创建正则表达式的对象
		var regExp = new RegExp(reg);
		if(!regExp.test(val)){
			$(this).next("span").html("<font color='red'>"+tip+"</font>");
		}else{
				$(this).next("span").html("");
		}
		
	});
	
	$("#form111").find("[reg1]").blur(function(){
		//获得输入的值
		var val = $(this).val();
		val = $.trim(val);
		//获得正则
		var reg = $(this).attr("reg1");
		//获得提示信息
		var tip = $(this).attr("tip");
		//创建正则表达式的对象
		var regExp = new RegExp(reg);
		if(val !=null && val != "" && !regExp.test(val)){
			$(this).next("span").html("<font color='red'>"+tip+"</font>");
		}else{
			$(this).next("span").html("");
		}
		
	})
})