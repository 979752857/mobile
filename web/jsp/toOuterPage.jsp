<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<script src="${pageContext.request.contextPath}/static/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/common/util.js"></script>
<body>
<form id="toPageForm"></form>
<script>
  $(function () {
      var Request = GetRequest();
      var url = Request['url'];
      delete Request['url'];
      var method = Request['method'];
      delete Request['method'];
      var form = $('#toPageForm');
      form.attr('action',"http://"+url);
      form.attr('method',method);
      form.html("");
      //再次修改input内容
      for (var item in Request) {
          form.append("<input type='hidden' name='"+item+"' value='"+Request[item]+"'/>");
      }
      form.submit();
  });
</script>
</body>
</html>
