// 转到注册页
function toRegist(){
	location.href = "toRegist";
}

// 登录
function login(){
	var user_name = $.trim($("#user_name").val());
	var password = $.trim($("#password").val());
	$.ajax({
		type: "post",
		//url: "${pageContext.request.contextPath }/admin/manageActivity!checkCoordinateInCity",
		url:"login",
		data: {"user_name":user_name,"password":password},
		dataType: "json",
		beforeSend: function(XMLHttpRequest){
			 //loading();
			},
		success: function(data, textStatus){
			if (!data) {
				alert("用户名或密码有误！");
			} else {
				// TODO 登录成功，转到首页
				console.log("login success");
				location.href = "index";
			}
			//unblock();
			/*var code=data.code;
			if(code==0){
				var form = document.forms['form1'];
				form.submit();
			}else{
				messageDialog(data.message,unblock);
			}*/
		},
		error: function(){
			//unblock();
		}

	});
}

// 注册
function regist(){
	$.ajax({
		type: "post",
		url:"regist",
		data: {"userName":$("input[name=userName]").val(),"password":$("input[name=password]").val(),
			"realName":$("input[name=realName]").val(),"telno":$("input[name=telno]").val(),
			"email":$("input[name=email]").val()},
		dataType: "json",
		beforeSend: function(XMLHttpRequest){
			 //loading();
			},
		success: function(data, textStatus){
			if (!data) {
				$("#regist_msg").html("<font color='red'>注册失败</font>");
			} else {
				$("#regist_msg").html("<a style='font-size: 20px;color:blue' href='toLogin'>注册成功，去登陆</a>");
			}
		},
		error: function(){
			//unblock();
		}

	});
}