var searche_type = 0;

$(document).ready(function(){
    changeSearchType('手机号搜用户', 1);
});

/**
 * 搜索用户信息展示用户主页
 */
function searchUser(){
    var submitFlag = true;
	var searchContent = $("#search-user-input").val().trim();
    var searchUrl = "";
    if (searche_type == 1){
        searchUrl = all_scope_path+'/homepage/getUserHomepage?phone=' + searchContent;
    }else if(searche_type == 2){
        if (searchContent.length > 10){
            Ewin.error({ message: "用户ID过长，请重新输入" });
            submitFlag = false;
        }else {
            searchUrl = all_scope_path+'/homepage/getUserHomepage?userId=' + searchContent;
        }
    }
    else if(searche_type == 3){
        searchUrl = all_scope_path+'/taxiDriverHomepage/getTaxiDriverHomepage?phone=' + searchContent;
    }
    else if(searche_type == 4){
        if (searchContent.length > 10){
            Ewin.error({ message: "用户ID过长，请重新输入" });
            submitFlag = false;
        }else {
            searchUrl = all_scope_path+'/taxiDriverHomepage/getTaxiDriverHomepage?taxiDriverId=' + searchContent;
        }
    }
    if (submitFlag){
        $("#search-user-a").prop("href",searchUrl);
    }else {
        $("#search-user-a").prop("href","javascript:void(0)");
    }

}

/**
 * 清空模糊搜索菜单内容
 */
function clearSearch(searchId){
    $("#"+searchId).val("");
    searchMenuUtil(searchId);
}

/**
 * 搜索菜单修改事件
 * @param typeName
 * @param type
 */
function changeSearchType(typeName, type) {
	searche_type = type;
	$("#searche-type").html(typeName);
	if(typeName.length > 3){
		var length = 240+(typeName.length/2)*8;
        $("#search-div-width").width(length);
	}
}

/**
 * 模糊查询菜单
 * @param searchId
 */
function searchMenuUtil(searchId) {
	var searchKey = $("#"+searchId).val();
    searchKey = searchKey.replace(/(^s*)|(s*$)/g, "");
    if(searchKey == ''){
        $("#main-menu-list").children("li").each(function(index){
            $(this).show();
            checkChildMenu($(this), searchKey, true);
        });
    }else{
        $("#main-menu-list").children("li").each(function(index){
            var resultIndex = $(this).html().indexOf(searchKey);
            if(resultIndex < 0){
                $(this).hide();
            }else{
                $(this).show();
                checkChildMenu($(this), searchKey);
            }
        });
    }
}

/**
 * 递归扫描子菜单
 * @param select
 * @param searchKey
 */
function checkChildMenu(select, searchKey, isClose){
    if(isClose){
        select.children("ul").children("li").each(function(index){
            $(this).show();
            select.children("ul").css("display", "none");
            checkChildMenu($(this), searchKey, isClose);
        });
    }else{
        select.children("ul").children("li").each(function(index){
            var resultIndex = $(this).html().indexOf(searchKey);
            if(resultIndex < 0){
                $(this).hide();
            }else{
                $(this).show();
                select.children("ul").css("display", "block");
                checkChildMenu($(this), searchKey, isClose);
            }
        });
    }
}