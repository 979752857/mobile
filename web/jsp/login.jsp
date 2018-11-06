<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>嘀嗒出行 | 登录首页</title>
  <%@include file="/jsp/common/common.jsp"%>
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/adminlte/bower_components/Ionicons/css/ionicons.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/adminlte/plugins/iCheck/square/blue.css">

  <script type="text/javascript" src="${pageContext.request.contextPath }/js/cms/login.js"></script>
</head>
<body class="hold-transition login-page" style="background:#d2d6de;background-image:url(${pageContext.request.contextPath }/image/login_img.jpg); color:#fff ">
<div class="login-box">
  <div class="login-logo" style="color: #fff;font-size: 40px">
    <b>嘀嗒出行</b>CMS
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">您好，欢迎来到cms管理后台</p>

    <form action="${pageContext.request.contextPath }/regist" method="post">
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
            <button type="button" onclick = "toRegist()" class="btn btn-block btn-danger">注 册</button>
        </div>
        <div class="col-xs-4">
          <p style="text-align:center">- OR -</p>
        </div>
        <div class="col-xs-4">
          <button type="button" onclick = "login()" class="btn btn-block btn-success">登 陆</button>
        </div>
      </div>
    </form>

  </div>
</div>
<!-- iCheck -->
<script src="${pageContext.request.contextPath }/adminlte/plugins/iCheck/icheck.min.js"></script>
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
