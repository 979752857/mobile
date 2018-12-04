$(function () {
    getUserInfo();
});
function editUser(){
    var name = $("#name").html();
    var password = $("#password").val();
    var phone = $("#phone").val();
    var busName = $("#busName").val();
    var address = $("#address").val();
    var remark = $("#remark").val();
	$.ajax({
		type: "post",
		url: all_scope_path + "/userInfo/updateInfo",
		data: {"name":name, "phone":phone, "password":password, "busName":busName,
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
                $("#timeout").html(data.endTime+"到期");
                if(data.warnLevel == 2){
                    $("#timeout").addClass("text-danger");
                    $("#pay").addClass("btn-danger");
                }else if(data.warnLevel == 1){
                    $("#timeout").addClass("text-warning");
                    $("#pay").addClass("btn-warning");
                }else{
                    $("#pay").addClass("btn-primary");
                }
				$("#city").html(data.city);
				$("#phone").val(data.phone);
				$("#address").val(data.address);
				$("#remark").val(data.remark);
				$("#busName").val(data.businessName);
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

function showAlipay() {
    var showId = "alipay-code";
    var name = $("#name").html();
    var html = "<div id=\""+showId+"\" style='width:450;margin:0 auto;'>" +
        "<p class='text-red' style='font-size:15px;'>请添加管理员支付宝好友，进行续费操作，转账前请将您的账号:<span class='text-blue'>" + name + "</span>发送给管理员！</p>" +
        "<img style='width: 300px;' src=\""+all_scope_path+"/static/image/alipay.jpg\">" +
        "</div>";
    Ewin.normal({message:html});
}