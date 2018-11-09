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
            <div class="box-header">
                <ul class="nav nav-tabs">
                    <li><a href="${pageContext.request.contextPath }/page/toPhoneListDetail">手机号管理</a></li>
                    <li class="active">
                        <a href="javascript:void(0)"><i class="fa fa-plus"></i>
                            <c:choose><c:when test="${id != null}">编辑</c:when><c:otherwise>添加</c:otherwise></c:choose>
                        </a>
                    </li>
                </ul>
            </div>
            <form class="form-horizontal" method="post">
                <div class="row form-row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-3">
                                <input type="number" class="form-control notnull" id="phone" name="phone" value="" maxlength="11" required="required">
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
                            <label class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-9">
                                <input type="radio" name="status" checked="checked" value="private">私有
                                <input type="radio" name="status" value="locked" style="margin-left:50px;">作废
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
