<%@ page import="java.util.Date" %>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <!-- Tell the browser to be responsive to screen width -->
 <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

 <!-- Bootstrap 3.3.7 -->
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/adminlte/bower_components/bootstrap/dist/css/bootstrap.min.css">
 <!-- Font Awesome -->
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/adminlte/bower_components/font-awesome/css/font-awesome.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">
<!-- Theme style -->
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/adminlte/dist/css/AdminLTE.min.css">
 <!-- AdminLTE Skins. Choose a skin from the css/skins
      folder instead of downloading all of them to reduce the load. -->
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/adminlte/dist/css/skins/_all-skins.min.css">
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datetimepicker/dist/css/bootstrap-datetimepicker.min.css">

 <!-- jQuery 3 -->
 <script src="${pageContext.servletContext.contextPath }/static/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
 <!-- jQuery UI 1.11.4 -->
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/jquery-ui/jquery-ui.min.js"></script>
 <!-- Bootstrap 3.3.7 -->
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
 <!-- AdminLTE App -->
 <script src="${pageContext.request.contextPath }/static/adminlte/dist/js/adminlte.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/js/common/common.js?time=<%=new Date().getTime()%>"></script>
 <script src="${pageContext.request.contextPath }/static/js/common/ajaxfileupload.js"></script>
 <script src="${pageContext.request.contextPath }/static/js/common/dateUtil.js?v=<%=new Date()%>"></script>

 <!-- FastClick -->
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/fastclick/lib/fastclick.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/min/moment-with-locales.min.js"></script>
<script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datetimepicker/dist/js/bootstrap-datetimepicker.min.js"></script>
 
 <script>
 	var all_scope_path = "${pageContext.servletContext.contextPath }";
 </script>


 <!-- 页面有icon图标时引用 -->
 <!--
 <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/static/adminlte/bower_components/Ionicons/css/ionicons.min.css">
 -->
 <!------------------------->

 <!-- 下拉选择框引用   注意：select2.min.css 要在 AdminLTE.min.css 前面加载  js代码放最下面  css放前面-->
 <!--
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script>
 $(function () {
 //Initialize Select2 Elements
 $('.select2').select2()
 });
 </script>
 -->
 <!------------------------->


 <!-- 日期时间输入框  -->
 <!--
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datetimepicker/dist/css/bootstrap-datetimepicker.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">

 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/min/moment-with-locales.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datetimepicker/dist/js/bootstrap-datetimepicker.min.js"></script>
 $('#end-time').datetimepicker({
 format: 'YYYY-MM-DD HH:mm:ss',
 locale: moment.locale('zh-cn')
 })
 -->
 <!------------------------->


 <!-- 日期输入框  -->
 <!--
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">

 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
 $('#datepicker').datepicker({
 autoclose: true
 })
 -->
 <!------------------------->


 <!-- 范围时间输入框  -->
 <!--
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">

 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/min/moment.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/locale/zh-cn.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
 //Date range picker
 $('#reservation').daterangepicker()
 -->
 <!------------------------->


 <!-- 范围时间加上时分输入输入框  -->
 <!--
 //Date range picker with time picker
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">

 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/min/moment.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/locale/zh-cn.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
 $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'YYYY/DD/MM h:mm A' })
 -->
 <!------------------------->


 <!-- 选择近几天的输入框  -->
 <!--
 //Date range picker with time picker
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/css/select2.min.css">

 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/select2/dist/js/select2.full.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/min/moment.min.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/moment/locale/zh-cn.js"></script>
 <script src="${pageContext.request.contextPath }/static/adminlte/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
 $('#daterange-btn').daterangepicker(
 {
 ranges: {
 '今天': [moment(), moment()],
 '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
 '近一周': [moment().subtract(6, 'days'), moment()],
 '近一月': [moment().subtract(29, 'days'), moment()],
 '本月': [moment().startOf('month'), moment().endOf('month')],
 '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
 },
 startDate: moment().subtract(29, 'days'),
 endDate: moment()
 },
 function (start, end) {
 $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
 }
 )
 -->
 <!------------------------->


 <!-- 单选框含模糊查询  -->
 <!--
 <div class="form-group">
  <label>Minimal</label>
  <select class="form-control select2" style="width: 100%;">
   <option selected="selected">Alabama</option>
   <option>Alaska</option>
   <option>California</option>
   <option>Delaware</option>
   <option>Tennessee</option>
   <option>Texas</option>
   <option>Washington</option>
  </select>
 </div>


 $('.select2').select2()
 -->
 <!------------------------->


 <!-- 多选框  -->
 <!--
 <div class="form-group">
  <label>Multiple</label>
  <select class="form-control select2" multiple="multiple" data-placeholder="Select a State"
  style="width: 100%;">
   <option>Alabama</option>
   <option>Alaska</option>
   <option>California</option>
   <option>Delaware</option>
   <option>Tennessee</option>
   <option>Texas</option>
   <option>Washington</option>
  </select>
 </div>

 $('.select2').select2()
 -->
 <!------------------------->