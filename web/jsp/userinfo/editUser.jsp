<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
	  <title>编辑用户信息</title>
	  <%@include file="/jsp/common/common.jsp"%>
	</head>
	<body class="hold-transition">
		<div class="col-md-9">
				<div class="box-body">
					<form class="form-horizontal" id="function-form" action="${pageContext.request.contextPath }/sysUser/addUser" method="post">
						<input type="hidden" name="userId" value="${tSysUser.userId }"/>
							<div class="form-group" style="margin-bottom: 50px;">
								<label  class="col-sm-2 control-label"><i class="fa fa-user"></i> 账户到期时间</label>
								<div class="col-sm-9">
									<label class="col-sm-2 control-label" id="timeout">${endTime}到期</label>
									<button id="pay" type="button" class="col-md-offset-1 col-sm-1 btn" onclick = "showAlipay()">
										<i class="fa fa-money">&nbsp;续费</i>
									</button>
								</div>
							</div>
						<c:choose><c:when test="${cid != null}">
							<div class="form-group">
								<label class="col-sm-2 control-label">下载二维码</label>
								<div class="col-sm-2">
									<a class="btn btn-success" href="${pageContext.request.contextPath }/static/qrcode/${cid}-1000X1000.png" download="${businessName}">
										<i class="fa fa-cloud-download">&nbsp;1000X1000</i>
									</a>
								</div>
								<div class="col-sm-2">
									<a class="btn btn-success" href="${pageContext.request.contextPath }/static/qrcode/${cid}-500X500.png" download="${businessName}">
										<i class="fa fa-cloud-download">&nbsp;500X500</i>
									</a>
								</div>
							</div>
						</c:when></c:choose>
						<div class="form-group">
							<label  class="col-sm-2 control-label"><i class="fa fa-user"></i> 账户</label>
							<div class="col-sm-9">
								<label class="col-sm-2 control-label" id="name"></label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="fa fa-envelope"></i> 城市</label>

							<div class="col-sm-9">
								<label class="col-sm-2 control-label" id="city"></label>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label"><i class="fa fa-user-secret"></i> 密码</label>

							<div class="col-sm-9">
								<input type="text" class="form-control" id="password" name="password" value="" placeholder="密码，修改的时候可以不填" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label"><i class="fa fa-mobile"></i> 手机号</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="phone" name="phone" value="" placeholder="手机号" >
							</div>
						</div>

						<div class="form-group">
							<label for="busName" class="col-sm-2 control-label"><i class="fa fa-mobile"></i> 运营商名</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="busName" name="busName" value="" placeholder="北京市XX移动运营商" >
							</div>
						</div>

						<div class="form-group">
							<label for="address" class="col-sm-2 control-label"><i class="fa fa-info"></i> 地址</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="address" name="address" value="" placeholder="地址">
							</div>
						</div>

						<div class="form-group">
							<label for="remark" class="col-sm-2 control-label"><i class="fa fa-bookmark"></i> 备注</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark" name="remark" placeholder="备注"></textarea>
							</div>
						</div>
						
	                    <div class="box-footer" style="">
							<div class="text-center">
 								<button type="button" class="btn btn-danger" data-btn-type="cancel">
 									<i class="fa fa-reply"  onclick="javascript:history.back(-1);" >&nbsp;返回</i>
 								</button>
								<button type="button" class="btn btn-primary" onclick = "editUser()">
									<i class="fa fa-save">&nbsp;保存</i>
								</button>
							</div>
						</div>
					</form>
				</div>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/userinfo/editUser.js"></script>
</html>
