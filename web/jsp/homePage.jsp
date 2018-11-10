<%@ page import="org.springframework.util.CollectionUtils" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tendy.model.MenuModel" %>
<%@ page import="com.tendy.utils.StringUtils" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>手机号信息管理系统 | 首页</title>
  <%@include file="/jsp/common/common.jsp"%>
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/bower_components/Ionicons/css/ionicons.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/menu.js"></script>
</head>
<%
  String name = String.valueOf(request.getSession().getAttribute("name"));
  String businessName = String.valueOf(request.getSession().getAttribute("businessName"));
  String endTime = String.valueOf(request.getSession().getAttribute("endTime"));
%>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="javascript:void(0);" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>手机号</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>手机号</b>管理</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
		  <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${pageContext.request.contextPath }/static/adminlte/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">${businessName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${pageContext.request.contextPath }/static/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                <p>
                  ${name}
                  <small>到期时间：${endTime}</small>
                </p>
              </li>
              <!-- Menu Body
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">信息</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">手册</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">配置</a>
                  </div>
                </div>
              </li>-->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a target="right" href="${pageContext.request.contextPath }/page/toEditUser" class="btn btn-default btn-flat">修改个人信息</a>
                </div>
                <div class="pull-right">
                  <a href="${pageContext.request.contextPath }/page/logout" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${pageContext.request.contextPath }/static/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${businessName}</p>
          <a href="#"><i class="fa fa-circle text-success"></i>${name}</a>
        </div>
      </div>
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" id="search-menu" class="form-control" oninput="searchMenuUtil('search-menu')" placeholder="菜单查询...">
          <span class="input-group-btn">
			<button type="button" name="sea rch" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
			</button>
          </span>
        </div>
      </form>
      <ul class="sidebar-menu" id="main-menu-list" data-widget="tree">
        <li class="header">主菜单</li>
        <%
          List<MenuModel> menuList = (List<MenuModel>) request.getAttribute("menuList");
          if(!CollectionUtils.isEmpty(menuList)){
              for(MenuModel menu : menuList){
                showCurMenuLevel(menu, out, request.getContextPath());
              }
          }
        %>
      </ul>
      <ul class="sidebar-menu" id="main-menu-list-search" style="display: none;" data-widget="tree">

      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
	<iframe class="content-wrapper" name="right" src="${pageContext.request.contextPath }/page/toEditUser" style="width:100%;margin:0px" ></iframe>
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2018-2099 <a href="#">手机号管理系统</a>.</strong> 版权所有
  </footer>
</div>
<!-- ./wrapper -->
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath }/static/adminlte/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/static/adminlte/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/static/adminlte/dist/js/demo.js"></script>
</body>
</html>
<%!
  /**
   * 递归遍历Menu实体，输出html
   * @param menuModel
   * @param out
   * @return
   * @throws IOException
   */
  public MenuModel showCurMenuLevel(MenuModel menuModel, JspWriter out, String address) throws IOException {
    if(menuModel.getIsfunction() == 1){
        return menuModel;
    }
    if(CollectionUtils.isEmpty(menuModel.getList())){
      out.println("<li><a href=\""+ address+menuModel.getMenuUrl() +"\" target=\"right\"><i class=\"fa "+ menuModel.getMenuIconUrl() +"\"></i> "+ menuModel.getMenuName() +"</a></li>");
    }else{
      if(StringUtils.isNotBlank(menuModel.getMenuUrl())){
        out.println("<li><a href=\""+ address+menuModel.getMenuUrl() +"\" target=\"right\"><i class=\"fa "+ menuModel.getMenuIconUrl() +"\"></i> "+ menuModel.getMenuName() +"</a></li>");
        return menuModel;
      }else{
        out.println("<li class=\"treeview\">");
        out.println("<a href=\"#\">");
        out.println("<span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span>");
        out.println("<i class=\"fa "+ menuModel.getMenuIconUrl() +"\"></i> <span>"+ menuModel.getMenuName() +"</span>");
      }
      out.println("</a>");
      if(!CollectionUtils.isEmpty(menuModel.getList())){
        out.println("<ul class=\"treeview-menu\">");
        for(MenuModel model : menuModel.getList()){
          showCurMenuLevel(model, out, address);
        }
        out.println("</ul>");
      }
      out.println("</li>");
    }
    return menuModel;
  }

%>