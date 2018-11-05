<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8" />
		<title>手机号信息</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<jsp:include page="header.jsp" flush="true"/>
	</head>
	<body>
		<div class="container-fluid content">
			<div class="row">
				<div class="main sidebar-minified">
					<div class="page-header">
						<div class="pull-left">
							<ol class="breadcrumb visible-sm visible-md visible-lg">								
								<li><a href="#"><i class="icon fa fa-home"></i>手机号管理</a></li>
							</ol>						
						</div>
						<div class="pull-right">
							<h2>手机号管理</h2>
						</div>					
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span>手机号管理</h6>
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-6">
											<div class="bk-margin-bottom-10">
												<button id="addToTable" class="btn btn-info" onclick="addemployee();">添加 <i class="fa fa-plus"></i></button>
											</div>
										</div>
									</div>
									<table class="table table-bordered table-striped mb-none" id="datatable-editable">
										<thead>
											<tr>
											    <th>手机号</th>
												<th>链接</th>
												<th>价格</th>
												<th>状态</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="propertyManager">

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>					   
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</body>
</html>