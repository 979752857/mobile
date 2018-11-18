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
    <script src="${pageContext.request.contextPath}/static/adminlte/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/common/util.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/index.js"></script>
</head>
<body>
<!-- screening -->
<div class="screening">
    <ul>
        <li class="Sort"><span id="sort-word">类型</span></li>
        <li class="Brand"><span id="brand-word">特征</span></li>
        <li class="Position"><span id="position-word">位置</span></li>
    </ul>
</div>
<!-- End screening -->
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
        <input type="hidden" id="notPhone" name="notPhone" value="">
        <input type="hidden" id="position" name="position" value="">
        <button type="button" id="form-button" onclick="searchData()"></button>
        <%--<button class="search-button" href="javascript:searchData();" id="form-button" ></button>--%>
    </form>
</div>
<div id="nav">
    <ul id="phone-data"></ul>
    <p id="load-more" style="display:none;" class="load-more">点击加载更多</p>
</div>
<%--<div id="bus-info" class="float-bottom-div"></div>--%>
<script>
    var pageNo = 0;
    function searchData() {
        $("#phone-data").html("");
        pageNo = 0;
        getData();
    }
    function getData() {
        $("#form-button").hide();
        var Request = GetRequest();
        var cid = Request['cid'];
        var status = $("#status").val();
        var notPhone = $("#notPhone").val();
        var tag = $("#tag").val();
        var position = $("#position").val();
        var keyword = $("#key").val();
        if(!keyword && !tag && !keyword){
            keyword = "888";
        }
        if(!cid){
            $("#phone-data").html("");
            $("#phone-data").html("<li><div><p>请重新扫描二维码</p></div></li>");
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/index/phoneList",
            type:"get",
            data:{key:keyword,cid:cid,status:status,no:pageNo,tag:tag,notPhone:notPhone,position:position},
            success:function(data){
                if(data){
                    var result = eval('(' + data + ')');
                    if(result.code == 0){
                        var resList = result.list;
                        for(var i = 0; i <resList.length; i++){
                            console.log(resList[i]);
                            $("#phone-data").append(resList[i]);
                        }
                        if(resList.length == 10){
                            $("#load-more").show();
                        }else{
                            $("#load-more").hide();
                        }
                    }else if(result.code == 204){
                        if(pageNo == 0){
                            $("#phone-data").append("<li><div><p>"+result.message+"</p></div></li>");
                        }else{
                            $("#load-more").hide();
                        }
                    }else{
                        $("#load-more").hide();
                        $("#phone-data").html("");
                        $("#phone-data").append("<li><div><p>"+result.message+"</p></div></li>");
                    }
                }
            },
            error:function(e){
                alert("网络传输错误！！");
            }
        });
        setTimeout("$(\"#form-button\").show()",500);
    }
    function getBusInfo(){
        var Request = GetRequest();
        var cid = Request['cid'];
        $.ajax({
            url:"${pageContext.request.contextPath}/index/businessInfo",
            type:"get",
            data:{cid:cid},
            success:function(data){
                if(data){
                    var result = eval('(' + data + ')');
                    if(result.code == 0){
                        var phone = result.phone;
                        var name = result.name;
                        var address = result.address;
                        var html = '<p>'+name+'</p><p>'+address+'&nbsp;&nbsp;&nbsp;&nbsp;<a href="tel:'+phone+'">'+phone+'</a></p>';
                        $("#bus-info").html(html);
                    }
                }
            }
        });
    }
    $('#load-more').on('click', function(){
        pageNo++;
        getData();
    });
    getData();
//    getBusInfo();
</script>
</body>
</html>