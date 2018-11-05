<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/paging.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/table.css">
<title>列表</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/paging.js"></script>

<style type="text/css">
</style>

</head>
<body>
<div id="table">
	<div class="add">
		<input type="text" id="key" name="key" value="" placeholder="手机号" />
		<input type="hidden" id="city" name="city" value="769"/>
		<button type="button" @click="reloadData(1,true)">查找</button>
	</div>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th>手机号</th>
				<th>链接</th>
				<th>价格</th>
				<th>状态</th>
				<th>发布时间</th>
				<%--<th>操作</th>--%>
			</tr>
		</thead>
		<tbody>
			<tr v-for="(item,index) in newsList">
				<td width="15%">{{item.phone}}</td>
				<td>{{item.url}}</td>
				<td>{{item.price}}</td>
				<td width="10%">{{item.status}}</td>
				<td width="15%">{{item.date}}</td>
				<%--<td width="10%"><span @click="deletelist(item.id,index)" class="delete">删除</span><span class="edit" @click="edit(item)">编辑</span></td>--%>
			</tr>
		</tbody>
	</table>
	<div id="mask" v-if="editlist">
		<div class="mask">
			<div class="title">
				编辑
				<span @click="editlist=false">
					X
				</span>
			</div>
			<div class="content">
				<input type="text" v-model="editDetail.title" name="title" value="" placeholder="标题" />
				<input type="text" v-model="editDetail.user" name="user" value="" placeholder="发布人" />
				<input type="date" v-model="editDetail.dates" name="date" value="" placeholder="发布时间" />
				<button @click="update">更新</button>
				<button @click="editlist=false">取消</button>
			</div>
		</div>
	</div>
</div>
<div class="box" id="box"></div>
<script>
    var totalCount = 0;
    var currentPage = 1;
    var pageSize = 10;
    var totalPage = Math.ceil(totalCount / pageSize); //(totalCount%pageSize==0)?(totalCount/pageSize):((totalCount/pageSize)+1);
	var app = new Vue({
		el: '#table',
		data: {
			addDetail: {},
			editlist: false,
			editDetail: {},
			newsList: [],
			editid:''
		},
		mounted() {
		},
		methods: {
			//reload
			reloadData(pageNo, isReload) {
                var keyword = $("#key").val();
                var city = $("#city").val();
                $.ajax({
                    url:"${pageContext.request.contextPath}/data/phoneListDetail.do",
                    type:"GET",
                    data:{key:keyword,city:city,no:pageNo},
                    success:function(data){
                        if(data){
                            var result = eval('(' + data + ')');
                            if(result.code == 0){
                                console.log(result);
                                app.newsList.splice(0, app.newsList.length);
                                if(result.list){
                                    var resList = eval('(' + result.list + ')');
                                    for(var i = 0; i <resList.length; i++){
                                        console.log(resList[i]);
                                        app.newsList.push({
                                            phone: resList[i].phone,
                                            url: resList[i].url,
                                            price: resList[i].price,
                                            status: resList[i].status,
                                            date: resList[i].time
                                        });
                                    }
                                }
                                if(isReload){
                                    reloadPage(result.total, pageNo);
                                }
                            }else{
                                app.newsList.splice(0, app.newsList.length);
                                alert("暂无数据！！");
                            }
                        }
                    },
                    error:function(e){
                        alert("网络传输错误！！");
                    }
                });
			}
		}
	});
	function reloadPage(total, no){
	    totalCount = total;
	    currentPage = no;
        totalPage = Math.ceil(totalCount / pageSize); //(totalCount%pageSize==0)?(totalCount/pageSize):((totalCount/pageSize)+1);
//        $('#box').render({initPageNo: currentPage,totalPages: totalPage,totalCount: '合计' + totalCount + '条数据'});
        $('#box').paging({
            initPageNo: currentPage, // 初始页码
            totalPages: totalPage, //总页数
            totalCount: '合计' + totalCount + '条数据', // 条目总数
            slideSpeed: 600, // 缓动速度。单位毫秒
            jump: true, //是否支持跳转
            callback: function(page) { // 回调函数
               app.reloadData(page, false);
            }
        });
	}
</script>
</body>
</html>