<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>导入手机号</title>
    <%@include file="/jsp/common/common.jsp"%>
    <%@include file="/jsp/common/common_table.jsp"%>
</head>
<body class="hold-transition">
    <div class="wrapper">
        <div class="box">
            <div class="box-header">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="javascript:void(0)">导入手机号</a></li>
                </ul>
            </div>
            <div class="box-body">
                <form class="form-horizontal" method="post">
                    <div class="row form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-2">导入文档：</label>
                                <div class="col-md-3">
                                    <input type="file" id="excelFile" name="excelFile" onchange="checkFile()"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">运营商：</label>
                                <div class="col-sm-9">
                                    <input type="radio" name="type" checked="checked" value="Mobile">移动
                                    <input type="radio" name="type" value="Unicom" style="margin-left:50px;">联通
                                    <input type="radio" name="type" value="Telecom" style="margin-left:50px;">电信
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3">
                                    <button type="button" class="btn btn-info" id="impBtn" style="width: 83px; height: 34px;" onclick="readExcel()">导入</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div style="width: 420px; font-size: 12px;padding-left: 20px;padding-top:5px;padding-bottom:10px;float: left;">
                        导入说明：<br/>
                        1、仅支持excel文件（后缀为xls,xlsx）。请将手机号写在第一列，价格写在第二列，无需表头，<span class="text-red">每次上传不能超过1000行</span><br />
                        2、范例文档如下格式：
                        <table border="1px">
                            <tr align="center" style="font-weight: bold">
                                <td>13701254131</td><td>1000</td>
                            </tr>
                            <tr align="center" style="font-weight: bold">
                                <td>13701254131</td><td>1000</td>
                            </tr>
                            <tr align="center" style="font-weight: bold">
                                <td>13701254131</td><td>1000</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath }/static/js/phoneinfo/bussinessImport.js"></script>
</body>
</html>
