<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/custom-tags" prefix="ct" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>出租车审核页</title>
        <%@include file="/jsp/common/common.jsp"%>
        <%@include file="/jsp/common/common_table.jsp"%>
    </head>
    <style>
        .form-row{
            border-top: thin solid #ddd;
            padding-top:10px;
        }
    </style>
    <body>
        <div class="box-body">
            <div class="row ml-top ml-bottom">
                <div class="col-md-4 col-md-offset-1">
                    <div class="col-md-3">
                        <a href="${pageContext.request.contextPath }/taxiDriverAuth/showPage?cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}" class="btn btn-default btn-sm"><i class="fa fa-mail-reply"></i> &nbsp;返&nbsp;回&nbsp;</a>
                    </div>
                    <div class="col-md-3">
                        <c:if test="${nextUserId != null}">
                            <a href="${pageContext.request.contextPath }/taxiDriverAuth/showAuthPage?userId=${nextUserId}&cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}" id="next2" class="btn btn-default btn-sm"><i class="fa fa-mail-forward"></i> &nbsp;下一页&nbsp;</a>
                        </c:if>
                    </div>
                </div>
                <div class="col-md-6 col-md-offset-1">
                    <div class="col-md-offset-2">
                        <h5 class="text-blue">司机手机号：${userUser.accountId}</h5>
                    </div>
                </div>
            </div>
            <form class="form-horizontal" method="post">
                <input type="hidden" name="userId" value="${taxiDriver.userId}">
                <input type="hidden" name="nextUserId" value="${nextUserId}">
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="row text-center ml-top">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-2 control-label">驾驶证</label>
                                <div class="col-sm-4">
                                    <input type="file" id="licenseImgInput" name="file" onchange="uploadFile('licenseImgInput',0,{},licenseImgCallback,null)" accept=".bmp,.jpg,.png" style="display: inline">
                                    <input type="hidden" name="licenseImg" id="licenseImg" value="${taxiDriver.licenseImg}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <c:choose>
                                <c:when test="${taxiDriver.licenseImg != null || taxiDriver.licenseImg != ''}">
                                    <img height="auto" style="max-height:450px;max-width:600px" id="licenseImgUrl" src="${bfs_pics_path}${taxiDriver.licenseImg}">
                                </c:when>
                                <c:otherwise>
                                    <span>未上传</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control notnull" id="firstName" name="firstName" value="${taxiDriver.firstName}" maxlength="8" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control notnull" id="lastName" name="lastName" value="${taxiDriver.lastName}" maxlength="8" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">身份证号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="idCardNo" name="idCardNo" value="${taxiDriver.idCardNo}" maxlength="18" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-9">
                                <c:choose>
                                    <c:when test="${taxiDriver.gender == 2 }">
                                        <input type="radio" name="gender" value="1">男
                                        <input type="radio" name="gender" value="2" checked="checked" style="margin-left:50px;">女
                                    </c:when>
                                    <c:otherwise>
                                        <input type="radio" name="gender" value="1" checked="checked">男
                                        <input type="radio" name="gender" value="2" style="margin-left:50px;">女
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">领证日期</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control notNull" id="getLicenseDate" name="getLicenseDate" value="<fmt:formatDate value="${taxiDriver.getLicenseDate}" pattern="yyyy-MM-dd"/>" required="required">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="row text-center ml-top">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-2 control-label">行驶证</label>
                                <div class="col-sm-4">
                                    <input type="file" id="drivingLicenseImgInput" name="file" onchange="uploadFile('drivingLicenseImgInput',0,{},drivingLicenseImgCallback,null)" accept=".bmp,.jpg,.png" style="display: inline">
                                    <input type="hidden" name="drivingLicenseImg" id="drivingLicenseImg" value="${taxiDriver.drivingLicenseImg}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <c:choose>
                                <c:when test="${taxiDriver.drivingLicenseImg != null || taxiDriver.drivingLicenseImg != ''}">
                                    <img height="auto" style="max-height:450px;max-width:600px" id="drivingLicenseImgUrl" src="${bfs_pics_path}${taxiDriver.drivingLicenseImg}">
                                </c:when>
                                <c:otherwise>
                                    <span>未上传</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group" style="margin-top: 80px;">
                            <label class="col-sm-2 control-label">地区</label>
                            <div class="col-sm-3">
                                <select class="form-control select2 sear_cond" id="city" style="float: left">
                                    <option value="" >请选择</option>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <select class="form-control select2" id="district" style="float: left">
                                    <option value="" >请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">公司</label>
                            <div class="col-sm-8">
                                <select  class="form-control select2 sear_cond" id="taxiCompanyId" name="taxiCompanyId"
                                style="float: left">
                                    <option value="" >请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">车牌号</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="taxiCarNo" name="taxiCarNo" value="${taxiDriver.taxiCarNo}" maxlength="10" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">注册日期</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control " id="taxiRegDate" name="taxiRegDate" value="<fmt:formatDate value="${taxiDriver.taxiRegDate}" pattern="yyyy-MM-dd"/>" required="required">
                            </div>
                        </div>
                        <div class="form-group" id="model-div" <c:if test="${taxiCompany.cityId != 37}">style="display: none"</c:if>>
                            <div class="row">
                                <label class="col-sm-2 control-label">车型</label>
                                <div class="col-sm-9">
                                    <input type="radio" name="taxiCategory" value="1" <c:if test="${taxiDriver.taxiCategory == 1}">checked="checked"</c:if> >高级车型
                                    <input type="radio" name="taxiCategory" value="0" <c:if test="${taxiDriver.taxiCategory == 0}">checked="checked"</c:if>  style="margin-left:50px;">低级车型
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-9 col-sm-offset-2">排气量在1.6升(含1.6升)以下，低级车型</label>
                                <label class="col-sm-9 col-sm-offset-2">排气量1.6升以上，高级车型</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">拥有人</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="owner" value="${taxiDriver.owner}" maxlength="18" required="required">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="row text-center ml-top">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-2 control-label">准驾证</label>
                                <div class="col-sm-4">
                                    <input type="file" id="driverPermitImgInput" name="file" onchange="uploadFile('driverPermitImgInput',0,{},driverPermitImgCallback,null)" accept=".bmp,.jpg,.png" style="display: inline">
                                    <input type="hidden" name="driverPermitImg" id="driverPermitImg" value="${taxiDriver.driverPermitImg}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <c:choose>
                                <c:when test="${taxiDriver.driverPermitImg != null || taxiDriver.driverPermitImg != ''}">
                                    <img height="auto" style="max-height:450px;max-width:600px" id="driverPermitImgUrl" src="${bfs_pics_path}${taxiDriver.driverPermitImg}">
                                </c:when>
                                <c:otherwise>
                                    <span>未上传</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="row ml-top">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">准驾证号</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="driverPermitNo" name="driverPermitNo" value="${taxiDriver.driverPermitNo}" maxlength="18" required="required">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row text-center ml-top">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-offset-2 control-label">人车合影</label>
                                <div class="col-sm-4">
                                    <input type="file" id="carPhotoInput" name="file" onchange="uploadFile('carPhotoInput',0,{},carPhotoCallback,null)" accept=".bmp,.jpg,.png" style="display: inline">
                                    <input type="hidden" name="carPhoto" onpropertychange="updateTaxiDriverPhoto({carPhoto:'carPhoto'});" id="carPhoto" value="${taxiDriver.carPhoto}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <c:choose>
                                <c:when test="${taxiDriver.carPhoto != null || taxiDriver.carPhoto != ''}">
                                    <img height="auto" style="max-height:450px;max-width:600px" id="carPhotoUrl" src="${bfs_pics_path}${taxiDriver.carPhoto}">
                                </c:when>
                                <c:otherwise>
                                    <span>未上传</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">审核结果</label>
                            <div class="col-sm-9">
                                <label><div class="iradio_minimal-blue checked">
                                    <input type="radio" name="certificationState" value="3" <c:if test="${taxiDriver.certificationState == 3}">checked="checked"</c:if> >通过
                                </div></label>
                                <label><div class="iradio_minimal-blue checked">
                                    <input type="radio" name="certificationState" value="2" <c:if test="${taxiDriver.certificationState == 2}">checked="checked"</c:if> >驳回
                                </div></label>
                            </div>
                        </div>
                        <div id="refuse-div" <c:if test="${taxiDriver.certificationState != 2}">style="display: none;"</c:if>>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驾驶证</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="driLicReason" onchange="checkRefuseVal(this)">
                                        <option value="" >请选择驳回理由</option>
                                        <option value="驾驶证不合格，请按示例图上传信息清晰完整的正本图片" >驾驶证不合格，请按示例图上传信息清晰完整的正本图片</option>
                                        <option value="驾驶证像素较低或太暗，无法识别，请注意上传后的正本图片要完整清晰">驾驶证像素较低或太暗，无法识别，请注意上传后的正本图片要完整清晰</option>
                                        <option value="驾驶证反光或太小，无法识别，请注意上传后的正本图片要完整清晰">驾驶证反光或太小，无法识别，请注意上传后的正本图片要完整清晰</option>
                                        <option value="驾驶证不能重复注册">驾驶证不能重复注册</option>
                                        <option value="驾驶证照片，未上传">驾驶证照片，未上传</option>
                                        <option value="驾驶证非原件">驾驶证非原件</option>
                                        <option value="驾驶证已过有效期，请及时更换证件">驾驶证已过有效期，请及时更换证件</option>
                                        <option value="驾驶证信息有遮挡或涂抹，请重新上传">驾驶证信息有遮挡或涂抹，请重新上传</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">行驶证</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="carLicReason" onchange="checkRefuseVal(this)">
                                        <option value="" >请选择驳回理由</option>
                                        <option value="行驶证不合格，请按示例图上传信息清晰完整的正本图片" >行驶证不合格，请按示例图上传信息清晰完整的正本图片</option>
                                        <option value="行驶证反光或太小，无法识别，请注意上传后的正本图片要完整清晰">行驶证反光或太小，无法识别，请注意上传后的正本图片要完整清晰</option>
                                        <option value="行驶证太暗或像素较低，无法识别，请上传清晰完整的正本图片">行驶证太暗或像素较低，无法识别，请上传清晰完整的正本图片</option>
                                        <option value="行驶证信息有遮挡或涂抹，请重新上传">行驶证信息有遮挡或涂抹，请重新上传</option>
                                        <option value="行驶证非原件">行驶证非原件</option>
                                        <option value="行驶证已重复注册，如需认证，请联系驳回其它号码。客服电话：4001630886">行驶证已重复注册，如需认证，请联系驳回其它号码。客服电话：4001630886</option>
                                        <option value="行驶证出租车车辆，请注册“嘀嗒出行”拼车版">行驶证出租车车辆，请注册“嘀嗒出行”拼车版</option>
                                        <option value="您所在的地区暂未开通该业务，现正在规划中，敬请留意！">您所在的地区暂未开通该业务，现正在规划中，敬请留意！</option>
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">人车照片</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="photoReason" onchange="checkRefuseVal(this)">
                                        <option value="" >请选择驳回理由</option>
                                        <option value="照片车牌号码与行驶证车牌号码不符，请重新上传" >照片车牌号码与行驶证车牌号码不符，请重新上传</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                            <label class="col-sm-2 control-label">准驾证</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="carPhoReason" onchange="checkRefuseVal(this)">
                                        <option value="" >请选择驳回理由</option>
                                        <option value="准驾证不合格，请按示例图上传信息清晰完整的正本图片" >准驾证不合格，请按示例图上传信息清晰完整的正本图片</option>
                                        <option value="准驾证已被注册，请上传本人准驾照。如有疑问，请联系客服：4001630886">准驾证已被注册，请上传本人准驾照。如有疑问，请联系客服：4001630886</option>
                                        <option value="非准驾证照片，请上传正确的准驾证（服务监督卡）照片。">非准驾证照片，请上传正确的准驾证（服务监督卡）照片。</option>
                                        <option value="准驾证像素较低或反光，无法识别，请注意上传后的正本图片要完整清晰">准驾证像素较低或反光，无法识别，请注意上传后的正本图片要完整清晰</option>
                                        <option value="准驾证信息与出租车公司信息不符">准驾证信息与出租车公司信息不符</option>
                                        <option value="准驾证信息与驾驶证信息不符">准驾证信息与驾驶证信息不符</option>
                                        <option value="准驾证不能重复注册">准驾证不能重复注册</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">综合驳回</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="totalReason" onchange="checkRefuseVal(this)">
                                        <option value="" >请选择驳回理由</option>
                                        <option value="驾驶证，行驶证，准驾照拍摄不合格，请按示例图上传信息清晰完整的正本图片" >驾驶证，行驶证，准驾照拍摄不合格，请按示例图上传信息清晰完整的正本图片</option>
                                        <option value="取消认证，欢迎再次申请认证" >取消认证，欢迎再次申请认证</option>
                                        <option value="更换手机号码，请用新号码使用认证" >更换手机号码，请用新号码使用认证</option>
                                        <option value="更换车辆，请上传新车行驶证和人车合照并同时更新相关信息" >更换车辆，请上传新车行驶证和人车合照并同时更新相关信息</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">驳回理由</label>
                                <div class="col-sm-9">
                                    <textarea id="refuseContent" class="form-control" name="rejectReason" placeholder="驳回理由" rows="5" ></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">短信</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="smsContent" name="smsContent" placeholder="短信" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">认证说明</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="certificationComment" value="${taxiDriver.certificationComment}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">交换图片</label>
                            <div class="col-sm-10">
                                <label class="col-sm-5"><input type="checkbox" name="changePhoto" value="licenseImg" >驾驶证</label>
                                <label class="col-sm-5"><input type="checkbox" name="changePhoto" value="drivingLicenseImg" >行驶证</label>
                                <label class="col-sm-5"><input type="checkbox" name="changePhoto" value="driverPermitImg" >准驾证</label>
                                <label class="col-sm-5"><input type="checkbox" name="changePhoto" value="carPhoto" >人车合影</label>
                                <div class="col-sm-5"><a id="change" href="javascript:changePhoto();" class="btn btn-default btn-xs"><i class="fa fa-refresh"></i> &nbsp;交换&nbsp;</a></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1 col-md-offset-1">
                        <a href="${pageContext.request.contextPath }/taxiDriverAuth/showPage?cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}" class="btn btn-default btn-sm"><i class="fa fa-mail-reply"></i> &nbsp;返&nbsp;回&nbsp;</a>
                    </div>
                    <div class="col-md-1">
                        <c:if test="${nextUserId != null}">
                            <a href="${pageContext.request.contextPath }/taxiDriverAuth/showAuthPage?userId=${nextUserId}&cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}" class="btn btn-default btn-sm"><i class="fa fa-mail-forward"></i> &nbsp;下一页&nbsp;</a>
                        </c:if>
                    </div>
                    <ct:privilegeControl funcCode="63-auth">
                        <div class="col-md-2 col-md-offset-2 text-center">
                            <button type="button" id="sub" class="btn btn-success btn-sm" onclick="updateTaxiDriver()"><i class="fa fa-check"></i> &nbsp;提&nbsp;交&nbsp;</button>
                            <c:if test="${taxiDriver.certificationState == 3 }">
                                <label class="col-md-offset-1"><input type="checkbox" name="sysCuser" <c:if test="${taxiDriver.sysCuser == 1 }">checked="checked"</c:if> value="1" >复审</label>
                            </c:if>
                        </div>
                    </ct:privilegeControl>
                </div>
            </form>
        </div>
        <div class="box-body">
            <div class="row form-row">
                <div class="col-md-2 col-md-offset-5 text-center">
                    <label class="control-label">审核记录</label>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                <table  class="table table-bordered table-hover" id="record">
                    <thead>
                        <th>提交时间</th>
                        <th>更改内容</th>
                        <th>发送短信</th>
                        <th>备注</th>
                        <th>操作人员</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath }/js/auth/taxiauth/taxiauthInfo.js?time=<%=new Date()%>"></script>
        <script src="${pageContext.request.contextPath }/js/common/img-rotate.js"></script>
        <script>
            $(function () {
                cityInit('${taxiCompany.cityId}','${taxiCompany.districtId}','${taxiCompany.id}');
            });
            var userId = ${taxiDriver.getUserId()};
            var auth_success = '${smsRule.auth_success}';
            var auth_fail = '${smsRule.auth_fail}';
            function toNextUser() {
                if(${nextUserId != null}){
                    window.location.href='${pageContext.request.contextPath }/taxiDriverAuth/showAuthPage?userId=${nextUserId}&cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}';
                }else{
                    window.location.href='${pageContext.request.contextPath }/taxiDriverAuth/showPage?cityId=${taxiDriverModel.cityId}&status=${taxiDriverModel.status}&reviewState=${taxiDriverModel.reviewState}&beginTime=${taxiDriverModel.beginTime}&endTime=${taxiDriverModel.endTime}&companyId=${taxiDriverModel.companyId}';
                }
            }
        </script>
    </body>
</html>
