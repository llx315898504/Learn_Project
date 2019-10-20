$(function(){
	$(".bg_text input").blur(function(){
		//获得到离开焦点的值
		var val = $(this).val();
		val = $.trim(val);
		var inputName = $(this).attr("name");
		if(val == null || val == ""){
			if(inputName == "username"){
				$("#errorName").html("请输入用户名");
			}else if(inputName == "password"){
				$("#errorName").html("请输入密码");
			}else if(inputName == "captcha"){
				$("#errorName").html("请输入验证码");
			}
			$("#errorName").show(500);
		}else{
			$("#errorName").hide(500);
		}
	});
	
	var tip = $("#tip").val();
	if(tip == "cap_error"){
		$("#errorName").html("验证码错误");
		$("#errorName").show(500);
	}else if(tip == "userpass_error"){
		$("#errorName").html("用户名或者密码错误");
		$("#errorName").show(500);
	}
})

function changeImage(){
	//请求链接加上date参数，是为了让浏览器每次请求后端方法，不从缓存中取；(链接地址一样，浏览器会认为是同样的请求，会默认从缓存中取数据，导致刷新验证码部车不成功)
	var cPath  = path + "/user/getImage.do?date="+new Date(); 
	$("#capthcaImage").attr("src", cPath);
}