<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8" />
		<title>主页 | 手机号查询</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<jsp:include page="header.jsp" flush="true"/>
	</head>
	<body>
		<!-- Start: Header -->
		<div class="navbar" role="navigation">
			<div class="container-fluid container-nav">
				<!-- Navbar Action -->
				<ul class="nav navbar-nav navbar-actions navbar-left">
					
				</ul>
				<!-- Navbar Left -->
				<div class="navbar-left">
					
				</div>
				<!-- Navbar Right -->
				<div class="navbar-right">							
					<!-- Userbox -->
					<div class="userbox">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<figure class="profile-picture hidden-xs">
								<img src="${pageContext.request.contextPath}/static/assets/img/avatar.jpg" class="img-circle" alt="" />
							</figure>
							<div class="profile-info">
								<span class="name">${user.name}</span>
								<span class="role"><i class="fa fa-circle bk-fg-success"></i>${user.permission}</span>
							</div>
							<i class="fa custom-caret"></i>
						</a>
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">								
								</li>	
								<li>
									<a href="${pageContext.request.contextPath}/logout"><i class="fa fa-power-off"></i> 注销</a>
								</li>
							</ul>
						</div>						
					</div>
					<!-- End Userbox -->
				</div>
				<!-- End Navbar Right -->
			</div>		
		</div>
		<!-- End: Header -->
		
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">
			
				<!-- Sidebar -->
				<div class="sidebar">
					<div class="sidebar-collapse">
						<!-- Sidebar Header Logo-->
						<div class="sidebar-header">
							<img src="${pageContext.request.contextPath}/static/assets/img/logo.png" class="img-responsive" alt="" />
						</div>
						<!-- Sidebar Menu-->
						<div class="sidebar-menu">						
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-sidebar">
									<div class="panel-body text-center">								
										<div class="flag">
											<img src="${pageContext.request.contextPath}/static/assets/img/flags/china.jpg" class="img-flags" alt="" />
										</div>
									</div>
									<li class="active">
										<a href="${pageContext.request.contextPath}/notice"  target="myframe">
											<i class="fa fa-comment" aria-hidden="true"></i><span>系统公告</span>
										</a>
									</li>
									<li class="nav-parent nav-expanded opened">
										<a>
											<i class="fa fa-bars" aria-hidden="true"></i><span>信息管理</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="${pageContext.request.contextPath}/page/toOpTable" target="myframe" ><span class="text"> 手机号管理</span></a></li>
											<li><a href="${pageContext.request.contextPath}/page/toPhoneListDetail" target="myframe" ><span class="text"> 手机号管理</span></a></li>
											<li><a href="${pageContext.request.contextPath}/checkpile" target="myframe" ><span class="text"> 操作记录</span></a></li>
										</ul>
									</li>
									<li class="nav-parent nav-expanded opened">
										<a>
											<i class="fa fa-user" aria-hidden="true"></i><span>个人信息管理</span>
										</a>
										<ul class="nav nav-children">
											<li><a href="${pageContext.request.contextPath}/manager/userinfo" target="myframe" ><span class="text"> 修改个人信息</span></a></li>
											<li><a href="${pageContext.request.contextPath}/manager/changepw" target="myframe" ><span class="text"> 修改密码</span></a></li>
										</ul>
									</li>
									<%--<li class="active">
										<a href="${pageContext.request.contextPath}/showcc"  target="myframe">
											<i class="fa fa-star" aria-hidden="true"></i><span>简介</span>
										</a>
									</li>--%>
								</ul>
							</nav>
						</div>
						<!-- End Sidebar Menu-->
					</div>
					<!-- Sidebar Footer-->
					<div class="sidebar-footer">					
						<div class="small-chart-visits">
						</div>
						<ul class="sidebar-terms bk-margin-top-10">
						</ul>					
					</div>
					<!-- End Sidebar Footer-->
				</div>
				<!-- End Sidebar -->
						
				<!-- Main Page -->
					<iframe name="myframe" height="1000px" width="100%" src="${pageContext.request.contextPath}/showcc" frameborder="0"></iframe>
				<!-- End Main Page -->
			</div>
		</div><!--/container-->
		<div class="clearfix"></div>
		<!-- Pages JS -->
		<script src="${pageContext.request.contextPath}/static/assets/js/pages/index.js"></script>
		
	</body>
	
</html>