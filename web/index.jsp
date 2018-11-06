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
</head>
<body>
<div class="search d5">
    <form id="phone-form" method="get">
        <input type="text" id="key" name="key" value="" placeholder="搜索从这里开始...">
        <input type="hidden" id="city" name="city" value="769">
        <input type="hidden" id="status" name="status" value="private">
        <button type="button" id="form-button" onclick="searchData()"></button>
    </form>
</div>
<div id="nav">
    <ul id="phone-data"></ul>
    <p id="load-more" style="display:none;" class="load-more">点击加载更多</p>
</div>
<script>
    var pageNo = 0;
    function searchData() {
        $("#phone-data").html("");
        pageNo = 0;
        getData();
    }
    function getData() {
        $("#form-button").hide();
        var keyword = $("#key").val();
        if(!keyword){
            keyword = "888";
        }
        var city = $("#city").val();
        var status = $("#status").val();
        if(!keyword || !city){
            $("#phone-data").html("");
            $("#phone-data").html("<li><div><p>请输入查询的关键号码</p></div></li>");
        }
        $.ajax({
            url:"${pageContext.request.contextPath}/data/phoneList",
            type:"get",
            data:{key:keyword,city:city,status:status,no:pageNo},
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
        setTimeout("$(\"#form-button\").show()",10000);
    }
    $('#load-more').on('click', function(){
        pageNo++;
        getData();
    });
    getData();
</script>
</body>
</html>