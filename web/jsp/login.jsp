<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>登录首页</title>
  <%@include file="/jsp/common/common.jsp"%>
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/Ionicons/css/ionicons.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/iCheck/square/blue.css">

  <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/login.js"></script>
  <style>
    .login-page{
      position:fixed;
      top: 0;
      left: 0;
      width:100%;
      height:100%;
      min-width: 1000px;
      z-index:-10;
      zoom: 1;
      background-color: #d2d6de;
      background-repeat: no-repeat;
      background-size: cover;
      -webkit-background-size: cover;
      -o-background-size: cover;
      background-position: center 0;
    }
  </style>
</head>
<body class="hold-transition login-page" style="background-image:url(${pageContext.request.contextPath }/static/image/login_img.jpg); color:#fff; ">
<div class="login-box">
  <div class="login-logo" style="color: #fff;font-size: 40px">
    <b>手机号信息</b>CMS
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">您好，欢迎来到cms管理后台</p>
    <form action="#" method="post">
      <div class="input-group" style="margin: 10px 0px">
        <span class="input-group-addon"><i class="fa fa-fw fa-user"></i></span>
        <input type="text" id="user_name" class="form-control" placeholder="Username">
      </div>
      <div class="input-group" style="margin: 10px 0px">
        <span class="input-group-addon"><i class="fa fa-fw fa-lock"></i></span>
        <input type="password" id="password" class="form-control" placeholder="Password">
      </div>
      <div class="row">
        <div class="col-xs-4">
            <%--<button type="button" onclick = "" class="btn btn-block btn-danger">注 册</button>--%>
        </div>
        <div class="col-xs-4">
          <%--<p style="text-align:center">- OR -</p>--%>
        </div>
        <div class="col-xs-4">
          <button type="button" onclick = "login()" class="btn btn-block btn-success">登 陆</button>
        </div>
      </div>
    </form>

  </div>
</div>
<!-- iCheck -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
    $(document).keyup(function (e) {//捕获文档对象的按键弹起事件
        if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了
            //此处编写用户敲回车后的代码
            login();
        }
    });
  });
</script>
</body>
</html>
