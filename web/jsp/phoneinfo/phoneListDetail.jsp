<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>手机号管理</title>
    <%@include file="/jsp/common/common.jsp"%>
    <%@include file="/jsp/common/common_table.jsp"%>
</head>
<body class="hold-transition">
    <div class="wrapper">
        <div class="box">
            <div class="box-header">
                <div class="row">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="javascript:void(0)"> 手机号管理</a></li>
                        <li><a href="${pageContext.request.contextPath }/page/toPhoneInfo"><i class="fa fa-plus"></i> 添加</a></li>
                    </ul>
                </div>
                <br/>
                <div class="row">
                    <form id="searchForm"  method="get">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="sear_cond" style="float: left">
                                    <span class="title">类型：</span>
                                </div>
                                <div class="col-md-2" style="margin-left: 5px; padding: 0px">
                                    <select id="tag" class="form-control select2" style="width: 100%;">
                                        <option selected="selected" value="">不限</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-10">
                                <div class="sear_cond" style="float: left">
                                    <span class="title">特征：</span>
                                </div>
                                <div class="col-md-2" style="margin-left: 5px; padding: 0px">
                                    <select id="notPhone" class="form-control select2" style="width: 100%;">
                                        <option selected="selected" value="">不限</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2" style="margin-left: 20px;">
                                <input type="text" class="form-control" name="phone" placeholder="手机号" value="">
                            </div>
                            <button type="button" id="searchBut" class="btn btn-info bg-blue">
                                <i class="fa fa-search"></i> &nbsp;搜&nbsp;索&nbsp;
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="box-body" id="table-body">
            <table  class="table table-bordered table-hover" id="baseStringTable">
                <thead>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        </div>
    </div>
    <script>
        $(document).ready(function(){

        });
    </script>
    <script src="${pageContext.request.contextPath }/static/js/phoneinfo/phoneListDetail.js"></script>
</body>
</html>
