//Brand开始
$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')) {
            $('.Category-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Category-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".Sort").removeClass('current');
			$('.screening').attr('style','position: fixed;top:0;');
        }
    });
    $(".Sort").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')) {
            $('.Sort-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Sort-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".Brand").removeClass('current');
			$('.screening').attr('style','position: fixed;top:0;');
        }
    });
    $(".Position").click(function(){
        if ($('.Position-eject').hasClass('grade-w-roll')) {
            $('.Position-eject').removeClass('grade-w-roll');
            $(this).removeClass('current');
            $('.screening').attr('style',' ');
        } else {
            $('.Position-eject').addClass('grade-w-roll');
            $(this).addClass('current');
            $(".Sort").removeClass('current');
            $('.screening').attr('style','position: fixed;top:0;');
        }
    });
});

$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
        if ($('.Position-eject').hasClass('grade-w-roll')){
            $('.Position-eject').removeClass('grade-w-roll');
        };
    });
    $(".Sort").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
        if ($('.Position-eject').hasClass('grade-w-roll')){
            $('.Position-eject').removeClass('grade-w-roll');
        };
    });
    $(".Position").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});

//js点击事件监听开始
function Categorytw(wbj, notPhone, txt){
    var arr = document.getElementById("Categorytw").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
	wbj.style.background = "#eee";
    $("#notPhone").val(notPhone);
    $("#brand-word").html(txt);
    $(".Brand").click();
}

function Positiontw(wbj, position, txt){
    var arr = document.getElementById("Positiontw").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
	wbj.style.background = "#eee";
    $("#position").val(position);
    $("#position-word").html(txt);
    $(".Position").click();
}

function Sorts(sbj, tag, txt){
    var arr = document.getElementById("Sort-Sort").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
    sbj.style.background = "#eee";
    $("#tag").val(tag);
    $("#sort-word").html(txt);
    $(".Sort").click();
}

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
        url:all_scope_path+"/index/phoneList",
        type:"get",
        data:{key:keyword,cid:cid,status:status,no:pageNo,tag:tag,notPhone:notPhone,position:position},
        success:function(data){
            if(data){
                var result = eval('(' + data + ')');
                if(result.code == 0){
                    var resList = result.list;
                    for(var i = 0; i <resList.length; i++){
                        console.log(resList[i]);
                        changeToHtml(resList[i], keyword);
                    }
                    if(resList.length == 10){
                        $("#load-more").show();
                    }else{
                        $("#load-more").hide();
                    }
                }else if(result.code == 204){
                    if(pageNo == 0){
                        $("#phone-data").append("<li><div><p>"+result.message+"</p></div></li>");
                    }
                    $("#load-more").hide();
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

function changeToHtml(item, key){
    if(!key){
        key = item.key;
    }
    var html = '<li><div><span class="span-left">';
    var phone = item.phone.split(key).join('<span class="text-red">'+key+'</span>');
    html += processPhone(phone);
    // html += '1<span class="text-red">88&nbsp;&nbsp;8</span>974&nbsp;&nbsp;1124';
    html += '</span><span class="span-right">'+item.price+'</span></div>';
    html += '<div><span class="span-left-brand">';
    html += item.city;
    if(item.type == "Mobile"){
        html += '移动';
    }else if(item.type == "Telecom"){
        html += '电信';
    }else if(item.type == "Unicom"){
        html += '联通';
    }
    html += '</span>';
    if(item.fare){
        html += '<span class="span-right-brand">含话费:'+item.fare+'</span></div></li>';
    }
    $("#phone-data").append(html);
}

function processPhone(phone) {
    var numIndex = 0;
    var i = 0;
    phone = insertContent(phone, 4);
    phone = insertContent(phone, 8);
    return phone;
}

function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if(regPos.test(val) || regNeg.test(val)) {
        return true;
    } else {
        return false;
    }
}

function insertContent(phone, index) {
    var numIndex = 0;
    for(var i = 0; i < phone.length; i++){
        if(isNumber(phone.charAt([i]))){
            numIndex++;
        }
        if(numIndex == index){
            var first = phone.substring(0,i);
            var second = phone.substring(i,phone.length);
            phone = first+"&nbsp;&nbsp;"+second;
            break;
        }
    }
    return phone;
}