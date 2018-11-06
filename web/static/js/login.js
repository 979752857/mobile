// 登录
function login(){
	var user_name = $.trim($("#user_name").val());
	var password = $.trim($("#password").val());
	$.ajax({
		type: "post",
		url: all_scope_path+"/login/checkLogin",
		data: {"name":user_name,"password":password},
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
				location.href = "/page/toHomePage";
			}
		},
		error: function(){
			//unblock();
		}
	});
}