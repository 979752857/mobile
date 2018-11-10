// 登录
function login(){
	var user_name = $.trim($("#user_name").val());
	var password = $.trim($("#password").val());
	if(password.length < 6){
		Ewin.error({message:"密码至少6位以上"});
	}
	$.ajax({
		type: "post",
		url: all_scope_path+"/login/checkLogin",
		data: {"name":user_name,"password":password},
		dataType: "json",
		beforeSend: function(XMLHttpRequest){
			 //loading();
		},
		success: function(data, textStatus){
			if (data.code == 0) {
                // TODO 登录成功，转到首页
                console.log("login success");
                location.href = "/page/toHomePage";
			} else {
                if(!data){
                    Ewin.error({message:"登录失败请重试"});
                }else{
                    Ewin.error({message:data.message});
                }
			}
		},
		error: function(){
			//unblock();
		}
	});
}