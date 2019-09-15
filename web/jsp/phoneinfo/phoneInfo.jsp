<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>手机号管理</title>
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
            <div class="row">
                <ul class="nav nav-tabs">
                    <li><a href="${pageContext.request.contextPath }/page/toPhoneListDetail">手机号管理</a></li>
                    <li class="active">
                        <a href="javascript:void(0)"><i class="fa fa-plus"></i>&nbsp;<c:choose><c:when test="${phone != null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose></a>
                    </li>
                </ul>
            </div>
            <form class="form-horizontal" method="post">
                <input type="hidden" id="phoneId" name="phoneId" value="" >
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-3">
                                <c:choose><c:when test="${phone != null}">
                                    <input type="number" class="form-control notnull" id="phone" name="phone" value="" maxlength="11" required="required" disabled>
                                </c:when><c:otherwise>
                                    <input type="number" class="form-control notnull" id="phone" name="phone" value="" maxlength="11" required="required">
                                </c:otherwise></c:choose>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">链接</label>
                            <div class="col-sm-3">
                                <input type="text" class="form-control" id="url" name="url" value="" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">价格</label>
                            <div class="col-sm-5">
                                <input type="number" class="form-control" id="price" name="price" value="" maxlength="18" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">运营商</label>
                            <div class="col-sm-9">
                                <input type="radio" name="type" checked="checked" value="Mobile">移动
                                <input type="radio" name="type" value="Unicom" style="margin-left:50px;">联通
                                <input type="radio" name="type" value="Telecom" style="margin-left:50px;">电信
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-9">
                                <input type="radio" name="status" checked="checked" value="private">私有
                                <input type="radio" name="status" value="locked" style="margin-left:50px;">作废
                            </div>
                        </div>
                        <div class="box-footer" style="">
                            <div class="text-center">
                                <button type="button" class="btn btn-primary" onclick = "updatePhone()">
                                    <i class="fa fa-save">&nbsp;保存</i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="${pageContext.request.contextPath }/static/js/phoneinfo/phoneInfo.js"></script>
</html>
