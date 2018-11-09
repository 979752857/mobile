$(function () {
    $('.select2').select2();
    getPhoneInfo();
});

function getPhoneInfo() {
    var Request = GetRequest();
    var phone = Request['phone'];
    var url = all_scope_path+'/phoneInfo/phoneInfo';
    $.ajax({
        "type" : 'GET',
        "url" : url,
        "dataType" : "json",
        "data" : {
            phone : phone
        },
        "success" : function(data) {
            if(data.code == 0){
                var userAccountPhone = data.userAccountPhone;
                $("#phone").val(userAccountPhone.phone);
                $("#url").val(userAccountPhone.url);
                $("#phoneId").val(userAccountPhone.id);
                $("#price").val(userAccountPhone.price);
                $("input[name='status'][value="+userAccountPhone.status+"]").attr("checked",true);
            }else{
                Ewin.error({message: data.message});
            }
        }
    });
}

/**
 * 更新认证数据
 */
function updateTaxiDriver() {
    var url = all_scope_path+'/phoneInfo/updatePhone';
    var id = $("input[name=phoneId]").val();
    var phone = $("input[name=phone]").val();
    var phoneUrl = $("input[name=url]").val();
    var price = $("input[name=price]").val();
    if(phone == null || phone == ""){
        Ewin.warning({message: "手机号有误请重试"});
        return;
    }
    if(price == null || price == ""){
        price = 0;
    }
    var status = $("input[name='status']:checked").val();
    if(status == null || status == ""){
        Ewin.warning({message: "请选择状态"});
        return;
    }
    $.ajax({
        "type" : 'GET',
        "url" : url,
        "dataType" : "json",
        "data" : {
            id : id,
            phone : phone,
            price : price,
            url : phoneUrl,
            status : status
        },
        "success" : function(data) {
            if(data.code == 0){
                Ewin.success({message: "操作成功"});
            }else{
                Ewin.error({message: data.message});
            }
        }
    });
}