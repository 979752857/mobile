$(function () {
    $('.select2').select2();
    initSelect();
    //点击实时生效
    $.each($(".sear_cond .option"),function(i,obj){
        $(obj).click(function(){

        });
    });
    $("#searchBut").click(function(){
        initParam();
    });
});

function initParam() {
    var keyword = $("input[name='phone']").val();
    var status = "";
    var tag = $("#tag").val();
    var notPhone = $("#notPhone").val();
    var position = $("#position").val();
    initBaseStringReload(all_scope_path+'/phoneInfo/phoneList', keyword, status, tag, notPhone, position);
}

/**
 * 初始化userTable表
 * @param url
 */
function initBaseStringReload(url, keyword, status, tag, notPhone, position){
    var arrcol = getCloumJson();
    var param = {"keyword":keyword, "status":status, "tag":tag, "notPhone":notPhone, "position":position};
    initDataTable("baseStringTable", url, arrcol, param);
}

function getCloumJson() {
    var arrcol = [
        {
            "sWidth":"8%",
            "sTitle" : "手机号",
            "mData" : "phone"
        }, {
            "sWidth":"15%",
            "sTitle" : "价格",
            "mData" : "price"
        }, {
            "sWidth":"20%",
            "sTitle" : "状态",
            "mData" : "status",
            "mRender" : function test(data, type, full) {
                var resultHtml = '';
                if(data == 'private'){
                    resultHtml = '可用';
                }else if(data == 'locked'){
                    resultHtml = '作废';
                }
                return resultHtml;
            }
        }, {
            "sWidth":"15%",
            "sTitle" : "日期",
            "mData" : "time"
        }, {
            "sWidth":"15%",
            "sTitle" : "链接",
            "mData" : "url",
            "mRender" : function test(data, type, full) {
                var resultHtml = '';
                if(data){
                    resultHtml += '<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="showQrcode(\''+full.city+'\',\''+data+'\',\''+full.phone+'\')"><i class="fa fa-search"></i> 查看二维码</a>';
                }
                return resultHtml;
            }
        }, {
            "sWidth":"20%",
            "sTitle" : "操作",
            "mData" : "phone",
            "mRender" : function test(data, type, full) {
                var resultHtml = '';
                resultHtml += '<a class="btn btn-primary btn-xs" href="'+all_scope_path+'/page/toPhoneInfo?phone='+data+'"><i class="fa fa-edit"></i> 修改</a>';
                if(full.status == 'private'){
                    resultHtml += '&nbsp;<a class="btn btn-danger btn-xs" href="javascript:void(0)" onclick="lockPhone(\''+data+'\', \'locked\')"><i class="fa fa-search"></i> 作废</a>';
                }else{
                    resultHtml += '&nbsp;<a class="btn btn-warning btn-xs" href="javascript:void(0)" onclick="lockPhone(\''+data+'\', \'private\')"><i class="fa fa-search"></i> 可用</a>';
                }
                return resultHtml;
            }
        }];
    return arrcol;
}

function showQrcode(city, data, phone) {
    var showId = "qrcode-"+phone;
    Ewin.normal({message:"<div id=\""+showId+"\" style='width:200;margin:0 auto;'></div>"});
    var qrcodeContent = getQrcodeByCity(city, data, phone);
    var qrcode = new QRCode(document.getElementById(showId), {
        width : 200,//设置宽高
        height : 200
    });
    qrcode.makeCode(qrcodeContent);
}

function lockPhone(phone, status){
    $.ajax({
        "type" : 'GET',
        "url" : all_scope_path+'/phoneInfo/lockedPhone',
        "dataType" : "json",
        "data" : {
            "phone" : phone,
            "status" : status
        },
        "success" : function(data) {
            console.log(data);
            if(data.code == 0){
                Ewin.success({message:"操作成功！"});
            }else{
                Ewin.error({message:data.message});
            }
        }
    });
}

function initSelect() {
    $("#tag").append("<option value='ABAC'>ABAC靓号</option>");//>ABAC靓号
    $("#tag").append("<option value='ABC'>3顺精品靓号</option>");//>3顺精品靓号
    $("#tag").append("<option value='AAA'>3A精品靓号</option>");//>3A精品靓号
    $("#tag").append("<option value='ABAB'>ABAB靓号</option>");//>ABAB靓号
    $("#tag").append("<option value='AABB'>AABB靓号</option>");//>AABB靓号
    $("#tag").append("<option value='ABCD'>4顺精品靓号</option>");//>4顺精品靓号
    $("#tag").append("<option value='AAAA'>4A精品靓号</option>");//>4A精品靓号
    $("#tag").append("<option value='ABACAD'>ABACAD靓号</option>");//>ABACAD靓号
    $("#tag").append("<option value='ABABAB'>ABABAB靓号</option>");//>ABABAB靓号
    $("#tag").append("<option value='ABCCBA'>3位回环靓号</option>");//>3位回环靓号
    $("#tag").append("<option value='AABBCC'>AABBCC靓号</option>");//>AABBCC靓号
    $("#tag").append("<option value='ABCABC'>ABCABC靓号</option>");//>ABCABC靓号
    $("#tag").append("<option value='AAABCCC'>AAABCCC靓号</option>");//>AAABCCC靓号
    $("#tag").append("<option value='ABCDE'>5顺精品靓号</option>");//>5顺精品靓号
    $("#tag").append("<option value='AAAAA'>5A精品靓号</option>");//>5A精品靓号
    $("#tag").append("<option value='ABCDEF'>6顺精品靓号</option>");//>6顺精品靓号
    $("#tag").append("<option value='AAAAAA'>6A精品靓号</option>");//>6A精品靓号

    $("#notPhone").append("<option value='0'>不含0</option>");//不含0
    $("#notPhone").append("<option value='1'>不含1</option>");//不含1
    $("#notPhone").append("<option value='2'>不含2</option>");//不含2
    $("#notPhone").append("<option value='3'>不含3</option>");//不含3
    $("#notPhone").append("<option value='4'>不含4</option>");//不含4
    $("#notPhone").append("<option value='5'>不含5</option>");//不含5
    $("#notPhone").append("<option value='6'>不含6</option>");//不含6
    $("#notPhone").append("<option value='7'>不含7</option>");//不含7
    $("#notPhone").append("<option value='8'>不含8</option>");//不含8
    $("#notPhone").append("<option value='9'>不含9</option>");//不含9

    $("#position").append("<option value='0'>中间</option>");
    $("#position").append("<option value='1'>末尾</option>");
}