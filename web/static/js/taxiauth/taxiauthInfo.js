$(function () {
    $('.select2').select2();
    $('#licenceIssueDate').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    })
    //认证按钮改变事件
    $('input[type=radio][name=certificationState]').change(function () {
        checkSmsContent($(this).val());
    });
    checkAllInput();
    $("#idCardNo").focus(function () {
        checkCardID($("#idCardNo").val());
    });
    $("#taxiCarNo").focus(function () {
        isVehicleNumber();
    });
    $("#driverPermitNo").focus(function () {
        checkDriverInfoExist(null, null, $("#driverPermitNo").val());
    });
    $("#idCardNo").blur(function () {
        checkCardID($("#idCardNo").val());
    });
    $("#taxiCarNo").blur(function () {
        isVehicleNumber();
    });
    $("#driverPermitNo").blur(function () {
        checkDriverInfoExist(null, null, $("#driverPermitNo").val());
    });
    $("#city").change(function () {
        districtInit($("#city").val(), "", "");
        if($("#city").val() == '37'){
            $("#model-div").show();
        }else{
            $("#model-div").hide();
        }
    });
    $("#district").change(function () {
        taxiCompanyInit($("#district").val(), "");
    });
    $("#taxiCompanyId").change(function () {
        checkDriverInfoExist(null, null, $("#driverPermitNo").val());
    });
    $("#getLicenseDate").change(function () {
        checkLicenseDate();
    });
    checkCardID($("#idCardNo").val());
    setTimeout(isVehicleNumber, 1500);
    getAuthHis();
    checkLicenseDate();
    checkSmsContent($('input[type=radio][name=certificationState]').val());
});

/**
 * 驳回理由添加驳回内容
 * @param obj
 */
function checkRefuseVal(obj){
    var reason = $(obj).val();
    if("" == reason){
        return;
    }
    var content = $("#refuseContent").val();
    if(content.length > "驳回理由：".length){
        content += ",\n"+reason;
    }else{
        content = "驳回理由：\n"+reason;
    }
    $("#refuseContent").val(content);
}

function getAuthHis() {
    var arrcol=["createTime","content","smsContent","remark","op_name"];
    var param = {"userId" : userId};
    initDataTableCommon("record", all_scope_path+"/taxiDriverAuth/getAuthHis", arrcol, param);
}

/**
 * 校验准驾证、身份证、车牌号是否重复
 */
function checkDriverInfoExist(idCardNo, taxiCarNo, driverPermitNo) {
    var taxiCompanyId = $("#taxiCompanyId").val();
    if(driverPermitNo && !taxiCompanyId){
        showInputAlert("driverPermitNo", "没有获取到出租车公司信息", "error");
        return;
    }
    var userId = $("input[name=userId]").val();
    $.ajax({
        "type" : 'GET',
        "url" : all_scope_path+'/taxiDriverAuth/checkDriverInfoExist',
        "dataType" : "json",
        "data" : {
            idCardNo : idCardNo,
            taxiCarNo : taxiCarNo,
            driverPermitNo : driverPermitNo,
            taxiCompanyId : taxiCompanyId,
            userId : userId
        },
        "success" : function(data) {
            if(data && data.length > 0){
                var html = data.length+"位车主已认证</br>已认证车主：";
                for(var i in data){
                    html += data[i]+"&nbsp;&nbsp;";
                }
                if(idCardNo){
                    showInputAlert("idCardNo", html, "error");
                }
                if(taxiCarNo){
                    showInputAlert("taxiCarNo", html, "error");
                }
                if(driverPermitNo){
                    showInputAlert("driverPermitNo", html, "error");
                }
            }else{
                if(idCardNo){
                    showInputAlert("idCardNo", "通过", "success");
                }
                if(taxiCarNo){
                    showInputAlert("taxiCarNo", "通过", "success");
                }
                if(driverPermitNo){
                    showInputAlert("driverPermitNo", "通过", "success");
                }
            }
        }
    });
}

/**
 * 校验驾龄
 */
function checkLicenseDate(){
    var date = parseDate($("#getLicenseDate").val());
    var str = getDateTimeDiff(date, new Date());
    if(str.Years<3){
        showInputAlert("getLicenseDate", str.PubTime, "error");
    }else{
        showInputAlert("getLicenseDate", str.PubTime, "success");
    }
}

/**
 * 校验短信内容
 */
function checkSmsContent(value) {
    if (value == '3') {
        $("#refuse-div").hide();
        $("#smsContent").val(auth_success);
    }else if(value == '2'){
        $("#refuse-div").show();
        $("#smsContent").val(auth_fail);
    }
}

function cityInit(cityId, districtId, companyId) {
    $("#city").html("<option value=''>请选择</option>");
    $.ajax({
        "type" : 'GET',
        "url" : all_scope_path+'/taxiDriverAuth/getCityList',
        "dataType" : "json",
        "success" : function(data) {
            console.info(data);
            for(var i in data){
                $("#city").append("<option value='"+data[i].id+"'>"+data[i].cityName+"</option>");
            }
            $("#city").val(cityId);
            districtInit(cityId, districtId, companyId);
        }
    });
}

function districtInit(cityId, districtId, companyId) {
    $("#district").html("<option value=''>请选择</option>");
    if(!cityId || cityId == ""){
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
                $("#district").append("<option value='"+data[i].id+"'>"+data[i].districtName+"</option>");
            }
            $("#district").val(districtId);
            taxiCompanyInit(districtId, companyId);
        }
    });
}

function taxiCompanyInit(districtId, companyId) {
    $("#taxiCompanyId").html("<option value=''>请选择</option>");
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
                $("#taxiCompanyId").append("<option value='"+data[i].id+"'>"+data[i].companyName+"</option>");
            }
            $("#taxiCompanyId").val(companyId);
            checkDriverInfoExist(null, null, $("#driverPermitNo").val());
        }
    });
}

function licenseImgCallback(data) {
    var allFileUrl = data.data.allFileUrl;
    var fileUrl = data.data.fileUrl;
    $("#licenseImg").val(fileUrl);
    $("#licenseImgUrl").attr("src", allFileUrl);
    updateTaxiDriverPhoto({licenseImg:$("input[name='licenseImg']").val()});
}

function drivingLicenseImgCallback(data) {
    var allFileUrl = data.data.allFileUrl;
    var fileUrl = data.data.fileUrl;
    $("#drivingLicenseImg").val(fileUrl);
    $("#drivingLicenseImgUrl").attr("src", allFileUrl);
    updateTaxiDriverPhoto({drivingLicenseImg:$("input[name='drivingLicenseImg']").val()});
}

function driverPermitImgCallback(data) {
    var allFileUrl = data.data.allFileUrl;
    var fileUrl = data.data.fileUrl;
    $("#driverPermitImg").val(fileUrl);
    $("#driverPermitImgUrl").attr("src", allFileUrl);
    updateTaxiDriverPhoto({driverPermitImg:$("input[name='driverPermitImg']").val()});
}

function carPhotoCallback(data) {
    var allFileUrl = data.data.allFileUrl;
    var fileUrl = data.data.fileUrl;
    $("#carPhoto").val(fileUrl);
    $("#carPhotoUrl").attr("src", allFileUrl);
    updateTaxiDriverPhoto({carPhoto:$("input[name='carPhoto']").val()});
}

function changePhoto() {
    var objCheck = $("input[type='checkbox'][name='changePhoto']:checked");
    if(objCheck.length != 2){
        Ewin.warning({message: "请选择两个照片进行交换"});
        return;
    }
    changePhotoValue($(objCheck[0]).val(), $(objCheck[1]).val());
}

/**
 * 图片交换
 * @param data
 */
function changePhotoValue(objName, objName1) {
    console.log("name:"+objName+"   name1:"+objName1);
    var value = $("#"+objName).val();
    var valueUrl = $("#"+objName+"Url").attr("src");
    $("#"+objName).val($("#"+objName1).val());
    $("#"+objName+"Url").attr("src", $("#"+objName1+"Url").attr("src"))
    $("#"+objName1).val(value);
    $("#"+objName1+"Url").attr("src", valueUrl);

    var jsonObj='{\"'+objName+'\":\"'+$("input[name="+objName+"]").val()+'\"}';
    var jsonObj1='{\"'+objName1+'\":\"'+$("input[name="+objName1+"]").val()+'\"}';
    //1、使用eval方法
    var objData=eval('(' + jsonObj + ')');
    var objData1=eval('(' + jsonObj1 + ')');
    updateTaxiDriverPhoto(objData);
    updateTaxiDriverPhoto(objData1);
}

/**
 * 更新照片路径
 */
function updateTaxiDriverPhoto(imgData) {
    var url = all_scope_path+'/taxiDriverAuth/updateTaxiDriverPhoto';
    var data = {};
    for (var attr in imgData) {
        data[attr] = imgData[attr];
    }
    data.userId = $("input[name=userId]").val();
    $.ajax({
        "type" : 'POST',
        "url" : url,
        "async": false,
        "dataType" : "json",
        "data" : data,
        "success" : function(data) {
            console.info(data.code+"****"+data.message);
        }
    });
}

/**
 * 更新认证数据
 */
function updateTaxiDriver() {
    var url = all_scope_path+'/taxiDriverAuth/updateTaxiDriver';
    var userId = $("input[name=userId]").val();
    var nextUserId = $("input[name=nextUserId]").val();
    var firstName = $("input[name=firstName]").val();
    if(firstName == null || firstName == ""){
        Ewin.warning({message: "请输入姓名"});
        return;
    }
    var lastName = $("input[name=lastName]").val();
    if(lastName == null || lastName == ""){
        Ewin.warning({message: "请输入姓名"});
        return;
    }
    var idCardNo = $("input[name=idCardNo]").val();
    if(idCardNo == null || idCardNo == ""){
        Ewin.warning({message: "请输入身份证号"});
        return;
    }
    var gender = $("input[name='gender']:checked").val();
    if(gender == null || gender == ""){
        Ewin.warning({message: "请选择性别"});
        return;
    }
    var getLicenseDate = $("input[name=getLicenseDate]").val();
    if(getLicenseDate == null || getLicenseDate == ""){
        Ewin.warning({message: "请输入领证日期"});
        return;
    }
    var licenseImg = $("input[name=licenseImg]").val();
    var taxiCompanyId = $("#taxiCompanyId").val();
    if(taxiCompanyId == null || taxiCompanyId == ""){
        Ewin.warning({message: "请选择出租车公司"});
        return;
    }
    var taxiCarNo = $("input[name=taxiCarNo]").val();
    if(taxiCarNo == null || taxiCarNo == ""){
        Ewin.warning({message: "请输入车牌号"});
        return;
    }
    var taxiRegDate = $("input[name=taxiRegDate]").val();
    if(taxiRegDate == null || taxiRegDate == ""){
        Ewin.warning({message: "请输入注册日期"});
        return;
    }
    var taxiCategory = $("input[name=taxiCategory]").val();
    var owner = $("input[name=owner]").val();
    if(owner == null || owner == ""){
        Ewin.warning({message: "请输入车辆拥有人"});
        return;
    }
    var driverPermitNo = $("input[name=driverPermitNo]").val();
    if(driverPermitNo == null || driverPermitNo == ""){
        Ewin.warning({message: "请输入准驾证号"});
        return;
    }
    var drivingLicenseImg = $("input[name=drivingLicenseImg]").val();
    if(drivingLicenseImg == null || drivingLicenseImg == ""){
        Ewin.warning({message: "请输入行驶证号"});
        return;
    }
    var driverPermitImg = $("input[name=driverPermitImg]").val();
    var carPhoto = $("input[name=carPhoto]").val();
    var certificationState = $("input[name='certificationState']:checked").val();
    if(certificationState == null || certificationState == ""){
        Ewin.warning({message: "请选择审核状态"});
        return;
    }
    var rejectReason = $("#refuseContent").val();
    var smsContent = $("#smsContent").val();
    if(smsContent == null || smsContent == ""){
        Ewin.warning({message: "请输入短信内容"});
        return;
    }
    var certificationComment = $("input[name=certificationComment]").val();
    var sysCuser = $("input[name='sysCuser']:checked").val();
    if(sysCuser == null || sysCuser == ""){
        sysCuser = 0;
    }
    var cityId = $("#city").val();
    $.ajax({
        "type" : 'POST',
        "url" : url,
        "dataType" : "json",
        "data" : {
            userId : $("input[name=userId]").val(),
            nextUserId : $("input[name=nextUserId]").val(),
            firstName : $("input[name=firstName]").val(),
            lastName : $("input[name=lastName]").val(),
            idCardNo : $("input[name=idCardNo]").val(),
            gender : gender,
            getLicenseDateStr : $("input[name=getLicenseDate]").val(),
            licenseImg : $("input[name=licenseImg]").val(),
            taxiCompanyId : $("#taxiCompanyId").val(),
            taxiCarNo : $("input[name=taxiCarNo]").val(),
            taxiRegDateStr : $("input[name=taxiRegDate]").val(),
            taxiCategory : $("input[name=taxiCategory]").val(),
            owner : $("input[name=owner]").val(),
            driverPermitNo : $("input[name=driverPermitNo]").val(),
            drivingLicenseImg : $("input[name=drivingLicenseImg]").val(),
            driverPermitImg : $("input[name=driverPermitImg]").val(),
            carPhoto : $("input[name=carPhoto]").val(),
            certificationState : certificationState,
            rejectReason : rejectReason,
            smsContent : $("#smsContent").val(),
            certificationComment : $("input[name=certificationComment]").val(),
            sysCuser : sysCuser,
            cityId : cityId
        },
        "success" : function(data) {
            if(data.code == 0){
                Ewin.success({message: "操作成功"});
                setTimeout(toNextUser,1000);
            }else{
                Ewin.error({message: data.message});
            }
        }
    });
}

function checkCardID(sId){
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",
        22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",
        36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",
        46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",
        62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",
        91:"国外"}
    var iSum=0 ;
    if(!/^\d{17}(\d|x)$/i.test(sId)){
        showInputAlert("idCardNo", "身份证长度或格式错误", "error");
        return ;
    }
    sId=sId.replace(/x$/i,"a");
    if(aCity[parseInt(sId.substr(0,2))]==null){
        showInputAlert("idCardNo", "身份证地区非法", "error");
        return ;
    }
    var sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    var d=new Date(sBirthday.replace(/-/g,"/")) ;
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
        showInputAlert("idCardNo", "身份证上的出生日期非法", "error");
        return ;
    }
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
    if(iSum%11!=1){
        showInputAlert("idCardNo", "身份证号非法", "error");
        return ;
    }
    checkDriverInfoExist($("#idCardNo").val(), null, null);
}

//车牌号验证方法
function isVehicleNumber() {
    var vehicleNumber = $("#taxiCarNo").val();
    var flag = false;
    var xreg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF]$)|([DF][A-HJ-NP-Z0-9][0-9]{4}$))/;
    var creg=/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1}$/;
    if(vehicleNumber.length == 7){
        flag = creg.test(vehicleNumber);
    } else if(vehicleNumber.length == 8){
        flag = xreg.test(vehicleNumber);
    }else{
        showInputAlert("taxiCarNo", "车牌号非法", "error");
    }
    if(flag){
        checkDriverInfoExist(null, $("#taxiCarNo").val(), null);
    }else{
        showInputAlert("taxiCarNo", "车牌号非法", "error");
    }
}