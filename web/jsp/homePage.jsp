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
  <title>嘀嗒出行管理系统 | 用户管理</title>
  <%@include file="/jsp/common/common.jsp"%>
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/adminlte/bower_components/Ionicons/css/ionicons.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/cms/menu.js"></script>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="${pageContext.request.contextPath }/adminlte/index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>嘀嗒</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>嘀嗒</b>出行</span>
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
			<li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <div class="input-group input-group-sm margin" id="search-div-width" style="width:250px">
                <div class="input-group-btn">
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span id="searche-type">搜索</span>
                    <span class="fa fa-caret-down"></span></button>
                  <ul class="dropdown-menu">
                    <li><a href="javascript:changeSearchType('手机号搜用户', 1)">手机号搜用户</a></li>
                    <li><a href="javascript:changeSearchType('用户ID搜用户', 2)">用户ID搜用户</a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:changeSearchType('手机号搜出租车司机', 3)">手机号搜出租车司机</a></li>
                    <li><a href="javascript:changeSearchType('用户ID搜出租车司机', 4)">用户ID搜出租车司机</a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:changeSearchType('搜工单', 5)">搜工单</a></li>
                  </ul>
                </div>
                <!-- /btn-group -->
                <input type="text" class="form-control" id="search-user-input">
                <span class="input-group-btn">
                  <button type="button" name="sea rch" id="search-user" onclick="searchUser()" class="btn btn-flat"><i class="fa fa-search"></i>
                  </button>
                </span>
              </div>
          </li>
		  <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${pageContext.request.contextPath }/adminlte/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">${operator.realName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${pageContext.request.contextPath }/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  ${operator.userName} - ${operator.realName}
                  <small>注册时间<fmt:formatDate value="${operator.regTime }" pattern="yyyy-MM-dd"/></small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
<!--                 <div class="row"> -->
<!--                   <div class="col-xs-4 text-center"> -->
<!--                     <a href="#">信息</a> -->
<!--                   </div> -->
<!--                   <div class="col-xs-4 text-center"> -->
<!--                     <a href="#">手册</a> -->
<!--                   </div> -->
<!--                   <div class="col-xs-4 text-center"> -->
<!--                     <a href="#">配置</a> -->
<!--                   </div> -->
<!--                 </div> -->
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a target="right" href="${pageContext.request.contextPath }/toEditUser?userId=${operator.userId}" class="btn btn-default btn-flat">修改个人信息</a>
                </div>
                <div class="pull-right">
                  <a href="${pageContext.request.contextPath }/logout" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
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
          <img src="${pageContext.request.contextPath }/adminlte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${operator.realName}</p>
          <a href="#"><i class="fa fa-circle text-success"></i>${operator.userName}</a>
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
	<iframe class="content-wrapper" name="right" src="${pageContext.request.contextPath }/notice" style="width:100%;margin:0px" ></iframe>
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.4.0
    </div>
    <strong>Copyright &copy; 2018-2099 <a href="#">嘀嗒出行</a>.</strong> 版权所有
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
      <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
      <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                <p>Will be 23 on April 24th</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-user bg-yellow"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Frodo Updated His Profile</h4>

                <p>New phone +1(800)555-1234</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Nora Joined Mailing List</h4>

                <p>nora@example.com</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-file-code-o bg-green"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Cron Job 254 Executed</h4>

                <p>Execution time 5 seconds</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Custom Template Design
                <span class="label label-danger pull-right">70%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Update Resume
                <span class="label label-success pull-right">95%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-success" style="width: 95%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Laravel Integration
                <span class="label label-warning pull-right">50%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-warning" style="width: 50%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Back End Framework
                <span class="label label-primary pull-right">68%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-primary" style="width: 68%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">
        <form method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Report panel usage
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Some information about this general settings option
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Allow mail redirect
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Other sets of options are available
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Expose author name in posts
              <input type="checkbox" class="pull-right" checked>
            </label>

            <p>
              Allow the user to show his name in blog posts
            </p>
          </div>
          <!-- /.form-group -->

          <h3 class="control-sidebar-heading">Chat Settings</h3>

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Show me as online
              <input type="checkbox" class="pull-right" checked>
            </label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Turn off notifications
              <input type="checkbox" class="pull-right">
            </label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">
              Delete chat history
              <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
            </label>
          </div>
          <!-- /.form-group -->
        </form>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- SlimScroll -->
<script src="${pageContext.request.contextPath }/adminlte/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/adminlte/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/adminlte/dist/js/demo.js"></script>
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