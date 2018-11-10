$(function () {
    getUserInfo();
});
function editUser(){
    var name = $("#name").html();
    var password = $("#password").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var remark = $("#remark").val();
	$.ajax({
		type: "post",
		url: all_scope_path + "/userInfo/updateInfo",
		data: {"name":name, "phone":phone, "password":$("input[name=password]").val(),
			"address":address, "remark":remark},
		dataType: "json",
		beforeSend: function(XMLHttpRequest){
			 //loading();
			},
		success: function(data, textStatus){
			if (data.code == 0) {
                Ewin.success({message:"修改成功"});
            } else {
                if(!data){
                    Ewin.error({message:"修改失败"});
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

function getUserInfo(){
    $.ajax({
        type: "POST",
        url: all_scope_path + "/userInfo/userInfo",
        data: {},
        dataType: "json",
        beforeSend: function(XMLHttpRequest){
            //loading();
        },
        success: function(data){
            if (data.code == 0) {
				$("#name").html(data.name);
				$("#city").html(data.city);
				$("#phone").val(data.phone);
				$("#address").val(data.address);
				$("#remark").val(data.remark);
            } else {
            	if(!data){
                    Ewin.error({message:"请重试"});
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