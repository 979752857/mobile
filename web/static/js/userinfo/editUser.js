function editUser(){
	$.ajax({
		type: "post",
		url: all_scope_path + "/editUser",
		data: {"userName":$("input[name=userName]").val(), "password":$("input[name=password]").val(),
			"realName":$("input[name=realName]").val(), "telno":$("input[name=telno]").val(),
			"email":$("input[name=email]").val(), "logoPath":$("input[name=logoPath]").val(),
			"userId":$("input[name=userId]").val()},
		dataType: "json",
		beforeSend: function(XMLHttpRequest){
			 //loading();
			},
		success: function(data, textStatus){
			if (!data) {
				Ewin.error({message:"修改失败"});
			} else {
				Ewin.success({message:"修改成功"});
			}
		},
		error: function(){
			//unblock();
		}

	});
}