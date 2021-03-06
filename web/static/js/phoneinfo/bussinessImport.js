//重新加载页面
function getParamData(){

    // initBaseStringReload(all_scope_path+'/phoneInfo/phoneInfo',source,code);
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
    var type = $("input[name='type']:checked").val();
    if(type == null || type == ""){
        Ewin.warning({message: "请选择号码运营商"});
        return;
    }
    $("#impBtn").text("导入中...");
    $("#impBtn").attr("disabled",true);
    $.ajaxFileUpload({
        url: all_scope_path + "/phoneInfo/busReadExcel",
        type: "post",
        data: {type:type},
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
                    var html = "以下手机号添加失败：<br/>";
                    for(var i=0;i <failList.length;i++){
                        html+="第"+failList[i].sort+"条："+failList[i].title+"("+failList[i].failReason+")<br/>";
                    }
                    Ewin.warning({message:html}).hide(function (e) {
                        getParamData();
                    });
                }
            } else {
                Ewin.unblock();
                if(data.message != ''){
                    Ewin.error({message: data.message});
                }else{
                    Ewin.error({message: "批量添加手机号失败!"});
                }
            }
            $("#impBtn").text("导入");
            $("#impBtn").attr("disabled", false);
        },
        error: function (data, status, e) {
            Ewin.unblock();
            Ewin.error({message: "批量添加手机号失败!"});
            $("#impBtn").text("导入");
            $("#impBtn").attr("disabled", false);
        }
    });

}
