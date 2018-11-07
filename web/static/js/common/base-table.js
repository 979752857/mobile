
/**
 * 可自定义的table表初始化
 * @param id
 * @param url
 * @param arrCol
 * @param paramMap
 * @param callbackFunc 请求服务器成功后回调方法
 * @returns {*|jQuery}
 */
function initDataTable(id, url, arrCol, arrParam, callbackFunc) {
    return initDataTableAll(id, url, arrCol, arrParam, callbackFunc, 20);
}

function initDataTableLength(id, url, arrCol, arrParam, length) {
    return initDataTableAll(id, url, arrCol, arrParam, null, length);
}

function initDataTableAll(id, url, arrCol, arrParam, callbackFunc, length) {
    var paramMap = [];
    for(var key in arrParam){
        paramMap.push({"name":key, "value":arrParam[key]});
    }
    for(var key in arrCol){
        arrCol[key]["sClass"]="text-center";
    }
    var datatable = $('#'+id).dataTable( {
        "lengthChange": false,
        "bPaginate" : true,// 分页按钮
        "bProcessing" : true,
        "bServerSide" : true,
        "searching" : false,
        "bDestroy" : true,
        "language" : oLanguageData,
        "pageLength": length,
        "order": [[0,'desc']], // [[0,'desc'],[2,'asc']],
        "ordering": false,
        "sAjaxSource" : url,
        "fnInitComplete": function(settings, json) {
            console.log( 'DataTables has finished its initialisation.  settings:'+settings+'   json:'+json );
        },
        // "sScrollY": "100%",
        // "sScrollCollapse": true,
        "aoColumns" : arrCol,
        "fnServerParams" : function(aoData) {
            for(var i in paramMap){
                aoData.push({'name': paramMap[i].name, 'value': paramMap[i].value});
            }
        },
        "footerCallback": function( tfoot, data, start, end, display ) {
            console.log('footerCallback    tfoot:'+tfoot+'   start:'+start+'    end:'+end+'   display:'+display);
        },
        "fnServerData" : function(sSource, aoData, fnCallback) {
            $.ajax({
                "type" : 'GET',
                "url" : sSource,
                "dataType" : "json",
                "data" : {
                    aoData : JSON.stringify(aoData)
                },
                "success" : function(resp) {
                    if(callbackFunc && (typeof callbackFunc == "function")){
                        callbackFunc();
                    }
                    fnCallback(resp);
                },
                "error": function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                    console.log(errorThrown);
                }
            });
        },
        "dom" : '<"row"<"col-sm-6"i><"col-sm-6"p>><"row"<"col-sm-12"t>><"row"<"col-sm-6"i><"col-sm-6"p>>'
    });
    return datatable;
}

/**
 * 可自定义的分页动态传参的table表初始化
 * @param id
 * @param url
 * @param arrCol
 * @param paramMap
 * @returns {*|jQuery}
 */
function initPageParamDataTable(id, url, arrCol, funParam) {
    for(var key in arrCol){
        arrCol[key]["sClass"]="text-center";
    }
    var datatable = $('#'+id).dataTable( {
        "lengthChange": false,
        "bPaginate" : true,// 分页按钮
        "bProcessing" : true,
        "bServerSide" : true,
        "searching" : false,
        "bDestroy" : true,
        "language" : oLanguageData,
        "pageLength": 20,
        "order": [[0,'desc']], // [[0,'desc'],[2,'asc']],
        "ordering": false,
        "sAjaxSource" : url,
        "fnInitComplete": function(settings, json) {
            console.log( 'DataTables has finished its initialisation.  settings:'+settings+'   json:'+json );
        },
        // "sScrollY": "100%",
        // "sScrollCollapse": true,
        "aoColumns" : arrCol,
        "fnServerParams" : function(aoData) {
            var arrParam = funParam();
            for(var key in arrParam){
                aoData.push({"name":key, "value":arrParam[key]});
            }
        },
        "footerCallback": function( tfoot, data, start, end, display ) {
            console.log('footerCallback    tfoot:'+tfoot+'   start:'+start+'    end:'+end+'   display:'+display);
        },
        "fnServerData" : function(sSource, aoData, fnCallback) {
            $.ajax({
                "type" : 'GET',
                "url" : sSource,
                "dataType" : "json",
                "data" : {
                    aoData : JSON.stringify(aoData)
                },
                "success" : function(resp) {
                    fnCallback(resp);
                }
            });
        },
        "dom" : '<"row"<"col-sm-6"><"col-sm-6"p>><"row"<"col-sm-12"t>><"row"<"col-sm-6"i><"col-sm-6"p>>'
    });
    return datatable;
}

/**
 * 简单table表初始化（没有增删改的那种）
 * @param id
 * @param url
 * @param arrCol
 * @param arrParam
 * @returns {*|jQuery}
 */
function initDataTableCommon(id, url, arrCol, arrParam) {
    var arrColumn = [];
    for(var item in arrCol){
        arrColumn.push({"mData" : arrCol[item]});
    }
    var paramMap = [];
    for(var key in arrParam){
        paramMap.push({"name":key, "value":arrParam[key]});
    }
    console.log(arrColumn);
    console.log(paramMap);
    var datatable = $('#'+id).dataTable( {
        "lengthChange": false,//是否显示一个每页长度的选择条（须要分页器支撑）
        "bPaginate" : true,// 是否显示（应用）分页器
        "sPaginationType": "full_numbers",//用于指定分页器风格
        "bProcessing" : true,//是否显示“正在加载”这个提示信息
        "bServerSide" : true,//
        "searching" : false,
        "bDestroy" : true,//将之前的那个数据对象清除掉，换以新的对象设置
        "language" : oLanguageData,
        "pageLength": 20,//用于指定一屏显示的条数，需开启分页器
        "order": [[0,'desc']], // [[0,'desc'],[2,'asc']],
        "ordering": false,
        "sAjaxSource" : url,//指定要从哪个URL获取数据
        "fnInitComplete": function(settings, json) {
            console.log( 'DataTables has finished its initialisation.  settings:'+settings+'   json:'+json );
        },
        // "sScrollY": "100%",
        // "sScrollCollapse": true,
        "aoColumns" : arrColumn,
        "fnServerParams" : function(aoData) {
            for(var i in paramMap){
                aoData.push({'name': paramMap[i].name, 'value': paramMap[i].value});
            }
        },
        "footerCallback": function( tfoot, data, start, end, display ) {
            console.log('footerCallback    tfoot:'+tfoot+'   start:'+start+'    end:'+end+'   display:'+display);
        },
        "fnServerData" : function(sSource, aoData, fnCallback) {
            $.ajax({
                "type" : 'GET',
                "url" : sSource,
                "dataType" : "json",
                "data" : {
                    aoData : JSON.stringify(aoData)
                },
                "success" : function(resp) {
                    fnCallback(resp);
                }
            });
        },
        "dom" : '<"row"<"col-sm-6"><"col-sm-6"p>><"row"<"col-sm-12"t>><"row"<"col-sm-6"i><"col-sm-6"p>>'
    });
    return datatable;
}

/**
 * 清除原来table的样式
 * @param table
 */
function clearTable(queryTable) {
    if(queryTable){
        queryTable.fnClearTable(false);
        queryTable.hide();
        if(queryTable[0]){
            $("#"+queryTable[0].id+"_wrapper").hide();
        }
    }
}

/**
 * 配置datatables的中文样式
 */
var oLanguageData = {
    "sProcessing" : "加载中...",  //"<span style=\"color:#ff0000;\"><img src='./media/image/ajax-loading.gif'></span>",//这里很重要，如果你的加载中是文字，则直接写上文字即可，如果是gif的图片，使用img标签就可以加载
    "sLengthMenu" : "显示 _MENU_ 项结果",
    "sZeroRecords" : "没有匹配结果",
    "sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix" : "",
    "sSearch" : "搜索:",
    "sUrl" : "",
    "sEmptyTable" : "表中数据为空",
    "sLoadingRecords" : "载入中...",
    "sInfoThousands" : ",",
    "oPaginate" : {
        "sFirst" : "首页",
        "sPrevious" : "上页",
        "sNext" : "下页",
        "sLast" : "末页"
    },
    "oAria" : {
        "sSortAscending" : ": 以升序排列此列",
        "sSortDescending" : ": 以降序排列此列"
    }
};