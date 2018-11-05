<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Basic -->
    	<meta charset="UTF-8" />
		<title>登录 | 手机号查询</title>
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<style>footer{display: none;}</style>
		<jsp:include page="header.jsp" flush="true"/>
	</head>
	<body>
		<!-- Start: Content -->
		<div class="container-fluid content">
			<div class="row">
				<!-- Main Page -->
				<div id="content" class="col-sm-12 full">
					<div class="row">
						<div class="login-box">
							<div class="panel">
								<div class="panel-body">	
										
									<div id="message-container" class="header bk-margin-bottom-20 text-center">			
										<img src="${pageContext.request.contextPath}/static/assets/img/logo.png" class="img-responsive" alt="" />
										    <h4 id="errmsg">登录</h4>	
									</div>
									<form id="loginform" class="form-horizontal login" >
										<div class="bk-padding-left-20 bk-padding-right-20">
											<div class="form-group">
												<label>用户名</label>
												<div class="input-group input-group-icon">
													<input type="text" class="form-control bk-radius" id="username" name="username" placeholder="用户名"/>
													<span class="input-group-addon">
														<span class="icon">
															<i class="fa fa-user"></i>
														</span>
													</span>
												</div>
											</div>											
											<div class="form-group">
												<label>密码</label>
												<div class="input-group input-group-icon">
													<input type="password" class="form-control bk-radius" id="password" name="password" placeholder="密码"/>
													<span class="input-group-addon">
														<span class="icon">
															<i class="fa fa-key"></i>
														</span>
													</span>
												</div>
											</div>
											<div class="row bk-margin-top-20 bk-margin-bottom-10">
												<div class="col-sm-8">
													<div class="checkbox-custom checkbox-default">
														<!--<input id="RememberMe" name="rememberme" type="checkbox" />
														<label for="RememberMe">记住密码</label>-->
													</div>
												</div>
												<div class="col-sm-4 text-right">
													<button type="button" class="btn btn-primary hidden-xs" onclick="loginform()">登录</button>
												</div>
											</div>
											<div class="text-with-hr">
												<span>or</span>
											</div>											
											
											<p class="text-center">还没有账户？ <a href="#">快注册吧!</a></p>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>			
				</div>
				<!-- End Main Page -->
			</div>
		</div><!--/container-->

		<script>
            function loginform(){
                var name = $("#username").val();
                var password = $("#password").val();
                $.ajax({
                    type: "POST",
                    url:"${pageContext.request.contextPath}/login/checkLogin",
                    data:{name:name, password:password},// 要提交的表单
                    success: function(data) {
                        if(!data){
                            $('#errmsg').html("网络传输错误,请重试！");
                            return ;
						}
                        var result = eval('(' + data + ')');
						if(result.code == 0){
                            location.href = "${pageContext.request.contextPath}/page/toHomePage";
						}else{
                            $('#errmsg').html(result.message);
                            document.getElementById("password").value = "";
						}
                    },
                    error:function(e){
                        alert("网络传输错误！！");
                    }
                });
            }
		</script>
	</body>
	
</html>