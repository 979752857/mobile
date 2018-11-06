$(function () {

    $('.select2').select2();
    //initParam();
    var cityId = $("input[name='cityId']").val();
    districtInit(cityId, "", "");
    //点击实时生效
    $.each($(".sear_cond .option"),function(i,obj){
        $(obj).click(function(){
            initParam();
            var param = $(this).data('param').split("-")[0];
            if(param == 'cityId'){
                var cityId = $("input[name='cityId']").val();
                districtInit(cityId, "", "");
            }
        });
    });
    $("#searchBut").click(function(){
        var phone = $("input[name='phone']").val();
        if(phone){
            initPhoneBaseStringReload(all_scope_path+'/taxiDriverAuth/getTaxiDriverByPhone', phone);
        }else{
            initParam();
        }
    });
    $("#form-district").change(function () {
        taxiCompanyInit($("#form-district").val(), "");
    });
    $("#form-company").change(function () {
        initParam();
    });
});

function initParam() {
    var cityId = $("input[name='cityId']").val();
    var status = $("input[name='status']").val();
    var reviewState = $("input[name='reviewState']").val();
    var beginTime = $("input[name='beginTime']").val();
    var endTime = $("input[name='endTime']").val();
    var companyId = $("#form-company").val();
    initBaseStringReload(all_scope_path+'/taxiDriverAuth/getTaxiDriverList', cityId, status, reviewState, beginTime, endTime, companyId);
}

/**
 * 初始化userTable表
 * @param url
 */
function initBaseStringReload(url, cityId, status, reviewState, beginTime, endTime, companyId){
    var conditionUrl = "&cityId="+cityId+"&status="+status+"&reviewState="+reviewState+"&beginTime="+beginTime+"&endTime="+endTime+"&companyId="+companyId;
    var arrcol = getCloumJson(conditionUrl);
    var param = {"cityId":cityId, "status":status, "reviewState":reviewState, "beginTime":beginTime, "endTime":endTime, "companyId":companyId};
    if(checkPrivilege("63-querylist")){
        initDataTable("baseStringTable", url, arrcol, param);
    }else{
        console.log("没有查看出租车认证列表权限");
    }
}

/**
 * 通过手机号查询用户
 * @param url
 */
function initPhoneBaseStringReload(url, phone){
    var arrcol = getCloumJson();
    var param = {"phone":phone};
    initDataTable("baseStringTable", url, arrcol, param);
}

function getCloumJson(conditionUrl) {
    var arrcol = [
        {
            "sWidth":"8%",
            "sTitle" : "用户ID",
            "mData" : "userId"
        }, {
            "sWidth":"8%",
            "sTitle" : "城市",
            "mData" : "cityName"
        }, {
            "sWidth":"15%",
            "sTitle" : "用户信息",
            "mRender" : function test(data, type, full) {
                var resultHtml = '<a href="'+all_scope_path+'/taxiDriverHomepage/getTaxiDriverHomepage?taxiDriverId='+full.userId+'">'+full.firstName+full.lastName+"</br>"+full.accountId;
                if(full.isBlack == 'true'){
                    resultHtml += "</br><span class='text-red'>【黑名单司机】</span>";
                }
                if(full.isBan == 'true'){
                    resultHtml += "</br><span class='text-red'>【封禁司机】</span>";
                }
                resultHtml += '</a>';
                return resultHtml;
            }
        }, {
            "sWidth":"20%",
            "sTitle" : "车辆信息",
            "mRender" : function test(data, type, full) {
                var resultHtml = full.companyName+"</br>"+full.taxiCarNo;
                return resultHtml;
            }
        }, {
            "sWidth":"15%",
            "sTitle" : "准驾证号",
            "mData" : "driverPermitNo"
        }, {
            "sTitle" : "驾驶证",
            "mData" : "licenseImg",
            "mRender" : function test(data, type, full) {
                var resultHtml = "";
                if(data){
                    resultHtml += '<a href="javascript:void(0);" onclick="Ewin.carousel('+ JSON.convertQuot({title:"图片",width:500,height:400,srcArr:full.taxiPicPath+data})+')"><img width="70px" height="45px" title="点击查看大图" src="'+full.littleTaxiPicPath+data+'"/></a>';
                }else{
                    resultHtml += '未上传';
                }
                return resultHtml;
            }
        }, {
            "sTitle" : "行驶证",
            "mData" : "drivingLicenseImg",
            "mRender" : function test(data, type, full) {
                var resultHtml = "";
                if(data){
                    resultHtml += '<a href="javascript:void(0);" onclick="Ewin.carousel('+ JSON.convertQuot({title:"图片",width:500,height:400,srcArr:full.taxiPicPath+data})+')"><img width="70px" height="45px" title="点击查看大图" src="'+full.littleTaxiPicPath+data+'"/></a>';
                }else{
                    resultHtml += '未上传';
                }
                return resultHtml;
            }
        }, {
            "sTitle" : "准驾证",
            "mData" : "driverPermitImg",
            "mRender" : function test(data, type, full) {
                var resultHtml = "";
                if(data){
                    resultHtml += '<a href="javascript:void(0);" onclick="Ewin.carousel('+ JSON.convertQuot({title:"图片",width:500,height:400,srcArr:full.taxiPicPath+data})+')"><img width="70px" height="45px" title="点击查看大图" src="'+full.littleTaxiPicPath+data+'"/></a>';
                }else{
                    resultHtml += '未上传';
                }
                return resultHtml;
            }
        }, {
            "sWidth":"20%",
            "sTitle" : "操作",
            "mData" : "userId",
            "mRender" : function test(data, type, full) {
                var resultHtml = '';
                if(full.certificationState == 1){
                    resultHtml += '<span class="text-orange">审核中</span>&nbsp;&nbsp;';
                }else if(full.certificationState == 2){
                    resultHtml += '<span class="text-red">审核驳回</span>&nbsp;&nbsp;';
                }else if(full.certificationState == 3){
                    resultHtml += '<span class="text-green">审核通过</span>&nbsp;&nbsp;';
                }else if(full.certificationState == 4){
                    resultHtml += '<span class="text-blue">更新资料</span>&nbsp;&nbsp;';
                }
                if(full.certificationTime){
                    resultHtml += "</br>最近提交时间：" + full.certificationTime;
                }
                if(full.sysOpName){
                    resultHtml += "<br/>认证操作人：" + full.sysOpName;
                }
                resultHtml += '</br>'
                if(full.certificationOpTime){
                    resultHtml += "审核时间：";
                    resultHtml += full.certificationOpTime+'</br>';
                }
                resultHtml += '<a class="btn btn-primary btn-xs" href="'+all_scope_path+'/taxiDriverAuth/showAuthPage?userId='+full.userId;
                if(conditionUrl){
                    resultHtml += conditionUrl;
                }
                resultHtml += '"><i class="fa fa-search"></i> 审核</a>';
                return resultHtml;
            }
        }];
    return arrcol;
}

function districtInit(cityId, districtId, companyId) {
    $("#form-district").html("<option value=''>不限</option>");
    if(!cityId || cityId == ""){
        $("#form-company").html("<option value=''>不限</option>");
        return;
    }
    $.ajax({
        "type" : 'GET',
        "url" : all_scope_path+'/taxiDriverAuth/getDistrictList',
        "dataType" : "json",
        "data" : {
            cityId : cityId
        },
        "success" : function(data) {
            console.info(data);
            for(var i in data){
                $("#form-district").append("<option value='"+data[i].id+"'>"+data[i].districtName+"</option>");
            }
            $("#form-district").val(districtId);
            taxiCompanyInit(districtId, companyId);
        }
    });
}

function taxiCompanyInit(districtId, companyId) {
    $("#form-company").html("<option value=''>不限</option>");
    if(!districtId || districtId == ""){
        return;
    }
    $.ajax({
        "type" : 'GET',
        "url" : all_scope_path+'/taxiDriverAuth/getTaxiCompanyList',
        "dataType" : "json",
        "data" : {
            districtId : districtId
        },
        "success" : function(data) {
            console.info(data);
            for(var i in data){
                $("#form-company").append("<option value='"+data[i].id+"'>"+data[i].companyName+"</option>");
            }
            $("#form-company").val(companyId);
        }
    });
}