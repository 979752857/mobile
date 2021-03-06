$(function () {
    $("#searchBut").click(function(){
        getParamData();
    })
    getParamData();
});

//重新加载页面
function getParamData(){
    var source = $("input[name= source]").val();
    var code = $("input[name= code]").val();
    // initBaseStringReload(all_scope_path+'/phoneInfo/phoneInfo',source,code);
}

/**
 * 初始化userTable表
 * @param url
 * @param raffleActivityId
 */
function initBaseStringReload(url,source,code){
    var arrcol = [
        {
            "sTitle" : "券码",
            "mData" : "code"
        },
        {
            "sTitle" : "领取状态",
            "mData" : "status",
            "mRender" : function test(data) {
                if(data == '1'){
                    return "已领取"
                }else{
                    return "未领取"
                }
            }
        },
        {
            "sTitle" : "领取人",
            "mData" : "user"
        },
        {
            "sTitle" : "领取时间",
            "mRender" : function test(data,type,full) {
                if(full.status == '1'){
                    return moment(full.assign_time).format('YYYY-MM-DD hh:mm:ss');
                }
            }
        }
        ];
    var param = {
        source: source,
        code: code
    }
    var queryTable = initDataTable("baseStringTable", url, arrcol, param);
    queryTable.api().ajax.reload();
}

// 检查 Excel 文件
function checkFile() {
    var location = $('#excelFile').val();
    if(location==''){
        Ewin.error({message:"请选择xls格式Excel文件"});
        return false;
    }
    if (location.endsWith('xls') || location.endsWith('xlsx')) {
        return true;
    }else{
        Ewin.error({message:"请选择xls或xlsx格式Excel文件"});
        return false;
    }
}
// 读取Excel
function readExcel(){

    if(!checkFile()){
        return;
    }
    $("#impBtn").text("导入中...");
    $("#impBtn").attr("disabled",true);
    var busName = $("input[name= busName]").val();
    if(busName == null || busName == ""){
        Ewin.warning({message: "请输入运营商账号"});
        return;
    }
    var type = $("input[name='type']:checked").val();
    if(type == null || type == ""){
        Ewin.warning({message: "请选择号码运营商"});
        return;
    }
    $.ajaxFileUpload({
        url: all_scope_path + "/phoneInfo/readExcel",
        type: "post",
        data: {busName:busName, type:type},
        secureuri: false,
        fileElementId: "excelFile",
        dataType: 'json',
        beforeSend:function (XMLHttpRequest) {
            Ewin.loading();
        },
        success: function (data, status) {
            if (data.code == 0) {
                Ewin.unblock();
                var failList = data.failList;
                if(failList.length < 1){
                    Ewin.success({
                        message:"操作成功！"
                    }).hide(function (e) {
                        getParamData();
                    });
                }else{
                    var html = "以下第三方券码添加失败：<br/>";
                    for(var i=0;i <failList.length;i++){
                        html+="第"+failList[i].sort+"条："+failList[i].title+"("+failList[i].failReason+")<br/>";
                    }
                    Ewin.warning({message:html}).hide(function (e) {
                        getParamData();
                    });
                }
            } else {
                Ewin.unblock();
                Ewin.error({message: "批量添加第三方券码失败!"});
            }
            $("#impBtn").text("导入");
            $("#impBtn").attr("disabled",false);
        },
        error: function (data, status, e) {
            Ewin.unblock();
            Ewin.error({message: "批量添加第三方券码失败!"});
            $("#impBtn").text("导入");
            $("#impBtn").attr("disabled",false);
        }
    });

}
