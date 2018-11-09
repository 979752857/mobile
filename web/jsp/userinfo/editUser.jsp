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
						<div class="form-group">
							<label  class="col-sm-2 control-label"><i class="fa fa-user"></i> 运营商账户</label>

							<div class="col-sm-9">
								<label class="col-sm-2 control-label">${tSysUser.userName }</label>
							</div>
						</div>

						<input type="hidden" name="email" value="${tSysUser.email }"/>
						<div class="form-group">
							<label class="col-sm-2 control-label"><i class="fa fa-envelope"></i> 城市</label>

							<div class="col-sm-9">
								<label class="col-sm-2 control-label">${tSysUser.email }</label>
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
								<input type="text" class="form-control" id="phone" name="phone" value="${tSysUser.telno }" placeholder="手机号" >
							</div>
						</div>

						<div class="form-group">
							<label for="address" class="col-sm-2 control-label"><i class="fa fa-info"></i> 地址</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="address" name="address" value="${tSysUser.realName }" placeholder="地址">
							</div>
						</div>

						<div class="form-group">
							<label for="remark" class="col-sm-2 control-label"><i class="fa fa-bookmark"></i> 备注</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="remark" name="remark" placeholder="备注">${tSysUser.remark }</textarea>
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
