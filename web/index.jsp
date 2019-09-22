<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>搜索</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index/normalize.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index/demo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/index/style.css">
    <script>var all_scope_path = "${pageContext.servletContext.contextPath }";</script>
    <script src="${pageContext.request.contextPath}/static/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/common/util.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/index.js"></script>
</head>
<body>
<!-- screening -->
<div class="screening">
    <ul>
        <li class="Type"><span id="type-word">运营商</span></li>
        <li class="Sort"><span id="sort-word">类型</span></li>
        <li class="Brand"><span id="brand-word">特征</span></li>
        <li class="Position"><span id="position-word">位置</span></li>
    </ul>
</div>
<!-- End screening -->
<div class="Type-eject Type-height">
    <ul class="Type-Sort" id="Type-Sort">
        <li onclick="Type(this, '', '运营商')">不限</li>
        <li onclick="Type(this, 'Mobile', '移动')">移动</li>
        <li onclick="Type(this, 'Unicom', '联通')">联通</li>
        <li onclick="Type(this, 'Telecom', '电信')">电信</li>
    </ul>
</div>
<div class="Sort-eject Sort-height">
    <ul class="Sort-Sort" id="Sort-Sort">
        <li onclick="Sorts(this, '', '类型')">不限</li>
        <li onclick="Sorts(this, 'ABAC', 'ABAC靓号')">ABAC靓号</li>
        <li onclick="Sorts(this, 'ABC', '3顺精品靓号')">3顺精品靓号</li>
        <li onclick="Sorts(this, 'AAA', '3A精品靓号')">3A精品靓号</li>
        <li onclick="Sorts(this, 'ABAB', 'ABAB靓号')">ABAB靓号</li>
        <li onclick="Sorts(this, 'AABB', 'AABB靓号')">AABB靓号</li>
        <li onclick="Sorts(this, 'ABCD', '4顺精品靓号')">4顺精品靓号</li>
        <li onclick="Sorts(this, 'AAAA', '4A精品靓号')">4A精品靓号</li>
        <li onclick="Sorts(this, 'ABACAD', 'ABACAD靓号')">ABACAD靓号</li>
        <li onclick="Sorts(this, 'ABABAB', 'ABABAB靓号')">ABABAB靓号</li>
        <li onclick="Sorts(this, 'ABCCBA', '3位回环靓号')">3位回环靓号</li>
        <li onclick="Sorts(this, 'AABBCC', 'AABBCC靓号')">AABBCC靓号</li>
        <li onclick="Sorts(this, 'ABCABC', 'ABCABC靓号')">ABCABC靓号</li>
        <li onclick="Sorts(this, 'AAABCCC', 'AAABCCC靓号')">AAABCCC靓号</li>
        <li onclick="Sorts(this, 'ABCDE', '5顺精品靓号')">5顺精品靓号</li>
        <li onclick="Sorts(this, 'AAAAA', '5A精品靓号')">5A精品靓号</li>
        <li onclick="Sorts(this, 'ABCDEF', '6顺精品靓号')">6顺精品靓号</li>
        <li onclick="Sorts(this, 'AAAAAA', '6A精品靓号')">6A精品靓号</li>
    </ul>
</div>
<div class="Category-eject">
    <ul class="Category-w" id="Categorytw">
        <li onclick="Categorytw(this, '', '特征')">不限</li>
        <li onclick="Categorytw(this, '0', '不含0')">不含0</li>
        <li onclick="Categorytw(this, '1', '不含1')">不含1</li>
        <li onclick="Categorytw(this, '2', '不含2')">不含2</li>
        <li onclick="Categorytw(this, '3', '不含3')">不含3</li>
        <li onclick="Categorytw(this, '4', '不含4')">不含4</li>
        <li onclick="Categorytw(this, '5', '不含5')">不含5</li>
        <li onclick="Categorytw(this, '6', '不含6')">不含6</li>
        <li onclick="Categorytw(this, '7', '不含7')">不含7</li>
        <li onclick="Categorytw(this, '8', '不含8')">不含8</li>
        <li onclick="Categorytw(this, '9', '不含9')">不含9</li>
    </ul>
</div>
<div class="Position-eject">
    <ul class="Position-w" id="Positiontw">
        <li onclick="Positiontw(this, '', '位置')">不限</li>
        <li onclick="Positiontw(this, '0', '中间')">中间</li>
        <li onclick="Positiontw(this, '1', '末尾')">末尾</li>
    </ul>
</div>
<!-- End 专业 -->
<div class="search d5">
    <form id="phone-form" method="get">
        <input type="text" id="key" name="key" value="" placeholder="输入关键数字号码...">
        <input type="hidden" id="status" name="status" value="private">
        <input type="hidden" id="tag" name="tag" value="">
        <input type="hidden" id="type" name="type" value="">
        <input type="hidden" id="notPhone" name="notPhone" value="">
        <input type="hidden" id="position" name="position" value="">
        <button type="button" id="form-button" onclick="searchData()"></button>
        <%--<button class="search-button" href="javascript:searchData();" id="form-button" ></button>--%>
    </form>
</div>
<div id="nav">
    <ul id="phone-data"></ul>
    <div class="load-more"><p id="load-more" style="display:none;">点击加载更多</p></div>
    <div id="bus-info" class="float-bottom-div"></div>
</div>
<script>
    var pageNo = 0;
    $('#load-more').on('click', function(){
        pageNo++;
        getData();
    });
    getData();
    getShowInfo();
</script>
</body>
</html>