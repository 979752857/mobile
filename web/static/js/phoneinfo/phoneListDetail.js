$(function () {
    $('.select2').select2();
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
    var status = "private";
    initBaseStringReload(all_scope_path+'/phoneInfo/phoneList', keyword, status);
}

/**
 * 初始化userTable表
 * @param url
 */
function initBaseStringReload(url, keyword, status){
    var arrcol = getCloumJson();
    var param = {"keyword":keyword, "status":status};
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
            "sTitle" : "链接",
            "mData" : "url"
        }, {
            "sWidth":"15%",
            "sTitle" : "价格",
            "mData" : "price"
        }, {
            "sWidth":"20%",
            "sTitle" : "状态",
            "mData" : "status"
        }, {
            "sWidth":"15%",
            "sTitle" : "日期",
            "mData" : "time"
        }, {
            "sWidth":"20%",
            "sTitle" : "操作",
            "mData" : "phone",
            "mRender" : function test(data, type, full) {
                var resultHtml = '';
                resultHtml += '<a class="btn btn-primary btn-xs" href="#"><i class="fa fa-search"></i> 审核</a>';
                return resultHtml;
            }
        }];
    return arrcol;
}