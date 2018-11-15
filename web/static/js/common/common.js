(function ($) {
    /**
     * 提示框
     * @type {{alert, confirm, dialog}}
     */
    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm" style="top: 30%;">' +
            '<div class="modal-content">' +
            '<div class="modal-header" style="border-bottom: none;padding-bottom: 7px;">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" style="font-size: 15px;" id="modalLabel"><i class="icon fa"></i>&nbsp;[Title]</h4>' +
            '</div>' +
            '<div class="modal-body" style="font-size: 19px;text-align: center;padding-top: 10px">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer" style="border-top: none;padding: 0px 10px 10px 10px;">' +
            '<button type="button" class="btn btn-primary cancel pull-left" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';

        var loadingHtml = '<div id="globalLoadingFade" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<img src="'+all_scope_path+'/image/global_loading.gif" style="width: 60px;height: 60px;position:relative;top: 40%;left: 49%;"/>'+
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header" style="padding: 10px;">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body" style="padding: 10px;overflow-y: auto;">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 400,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).css("overflow","auto");
            $('#' + modalId +' .modal-dialog').css("width",options.width);
            $('#' + modalId).modal({
                backdrop: false
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            success: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.modal-content').css({'background-color': '#00a65a','color':'#fff'});
                modal.find('.ok').removeClass('btn-primary').addClass('btn-outline btn-success');
                modal.find('.cancel').removeClass('btn-primary').addClass('btn-outline btn-success');
                modal.find('.icon').addClass("fa-check");
                if (options.btncl == undefined){
                    modal.find('.cancel').hide();
                }
                if (options.btnok == undefined){
                    modal.find('.ok').hide();
                }
                if(options.btncl == undefined && options.btnok == undefined){
                    modal.click(function () {
                        $(this).modal('hide');
                    });
                }
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            error: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.modal-content').css({'background-color': '#dd4b39','color':'#fff'});
                modal.find('.ok').removeClass('btn-primary').addClass('btn-outline btn-danger');
                modal.find('.cancel').removeClass('btn-primary').addClass('btn-outline btn-danger');
                modal.find('.icon').addClass("fa-ban");
                if (options.btncl == undefined){
                    modal.find('.cancel').hide();
                }
                if (options.btnok == undefined){
                    modal.find('.ok').hide();
                }
                if(options.btncl == undefined && options.btnok == undefined){
                    modal.click(function () {
                        $(this).modal('hide');
                    });
                }
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            warning: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.modal-content').css({'background-color': '#f39c12','color':'#fff'});
                modal.find('.ok').removeClass('btn-primary').addClass('btn-outline btn-warning');
                modal.find('.cancel').removeClass('btn-primary').addClass('btn-outline btn-warning');
                modal.find('.icon').addClass("fa-warning");
                if (options.btncl == undefined){
                    modal.find('.cancel').hide();
                }
                if (options.btnok == undefined){
                    modal.find('.ok').hide();
                }
                if(options.btncl == undefined && options.btnok == undefined){
                    modal.click(function () {
                        $(this).modal('hide');
                    });
                }
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            info: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.modal-content').css({'background-color': '#00c0ef','color':'#fff'});
                modal.find('.ok').removeClass('btn-primary').addClass('btn-outline btn-info');
                modal.find('.cancel').removeClass('btn-primary').addClass('btn-outline btn-info');
                modal.find('.icon').addClass("fa-info");
                if (options.btncl == undefined){
                    modal.find('.cancel').hide();
                }
                if (options.btnok == undefined){
                    modal.find('.ok').hide();
                }
                if(options.btncl == undefined && options.btnok == undefined){
                    modal.click(function () {
                        $(this).modal('hide');
                    });
                }
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            normal: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.modal-content').css({'background-color': '#eee','color':'#333'});
                modal.find('.ok').removeClass('btn-primary').addClass('btn-outline btn-info');
                modal.find('.cancel').removeClass('btn-primary').addClass('btn-outline btn-info');
                modal.find('.icon').addClass("fa-info");
                if (options.btncl == undefined){
                    modal.find('.cancel').hide();
                }
                if (options.btnok == undefined){
                    modal.find('.ok').hide();
                }
                if(options.btncl == undefined && options.btnok == undefined){
                    modal.click(function () {
                        $(this).modal('hide');
                    });
                }
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {callback(true); });
                            modal.find('.cancel').click(function () {callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: '标题',
                    url: '',
                    content:'',
                    width: '',
                    height: ''
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                if (options.width != ''){
                    target.find('.modal-dialog').css({"width":options.width});
                }else {
                    target.find('.modal-dialog').css({"width":800});
                }
                if (options.height != ''){
                    target.find('.modal-body').css({"height":options.height});
                }
                var documentScrollTop =  $(window.top.document).scrollTop();
                target.find('.modal-dialog').css({"margin-top":documentScrollTop + 30});
                if (options.url != ''){
                    if (options.url.indexOf("?") != -1) {
                        var requestUrl = options.url.substring(0, options.url.indexOf("?"));
                        var requestParam = parseUrlParam(options.url);
                        $.get(requestUrl, requestParam, function (result) {
                            target.find('.modal-body').html(result);
                        });
                    } else {
                        $.get(options.url, function (result) {
                            target.find('.modal-body').html(result);
                        });
                    }
                }else if (options.content != ''){
                    target.find('.modal-body').html(options.content);
                }
                target.css("overflow","auto");
                target.modal({
                    backdrop: false
                });
                $('#' + modalId).on('hide.bs.modal', function (e) {
                    $('body').find('#' + modalId).remove();
                });
                return {
                    id: modalId
                };
            },
            loading: function () {
                $('body').append(loadingHtml);
                $('#globalLoadingFade').modal({
                    backdrop: false
                });
            },
            unblock: function () {
                //$('#globalLoadingFade').remove();
                $('#globalLoadingFade').modal('hide');
            },
            carousel: function (options) {
                options = $.extend({}, {
                    title: '图片',
                    srcArr: undefined,
                    width: 1000,
                    height: 700
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-dialog').css({"width":options.width + 30});
                var imageHtml = '';
                if (typeof options.srcArr == 'string'){
                    options.srcArr = options.srcArr.split(",");
                }
                if (options.srcArr.length == 0){
                    return;
                }else if (options.srcArr.length == 1){
                    var srcItem = options.srcArr[0];
                    var desc = srcItem.substring(0,srcItem.indexOf("|"));
                    var src = srcItem.substring(srcItem.indexOf("|")+1);
                    if(desc != ''){
                        target.find('.modal-body').css({"height":options.height + 60});
                        imageHtml += '<div style="text-align: center;font-size: 18px;height: 30px;">'+ desc +'</div>';
                    }else {
                        target.find('.modal-body').css({"height":options.height + 30});
                    }
                    imageHtml += '<img src="'+src+'" style="width: '+options.width+'px;height: '+ options.height +'px;">';
                }else if (options.srcArr.length > 1){
                    imageHtml += '<div id="carousel-example-generic'+modalId+'" class="carousel slide" data-ride="carousel">';
                    imageHtml += '<ol class="carousel-indicators">';
                    for(var i = 0; i< options.srcArr.length; i++){
                        if (i == 0){
                            imageHtml += '<li data-target="#carousel-example-generic'+ modalId +'"  data-slide-to="'+i+'" class="active"></li>';
                        }else {
                            imageHtml += '<li data-target="#carousel-example-generic'+modalId+'"  data-slide-to="'+i+'" class=""></li>';
                        }
                    }
                    imageHtml += '</ol>';
                    imageHtml += '<div class="carousel-inner">';
                    for(var i = 0; i< options.srcArr.length; i++){
                        var srcItem = options.srcArr[i];
                        var desc= srcItem.substring(0,srcItem.indexOf("|"));
                        var src = srcItem.substring(srcItem.indexOf("|")+1);
                        if (i == 0){
                            imageHtml += '<div class="item active">';
                        }else {
                            imageHtml += '<div class="item">';
                        }
                        imageHtml += '<div style="text-align: center;font-size: 18px;height: 30px;">'+ desc +'</div>';
                        imageHtml += '<img src="'+src+'" style="width: '+ options.width +'px;height: '+ options.height +'px;">';
                        imageHtml += '</div>';
                    }
                    imageHtml += '</div>';
                    imageHtml += '<a class="left carousel-control" href="#carousel-example-generic'+modalId+'" data-slide="prev">';
                    imageHtml += '<span class="fa fa-angle-left" style="color: #000;"></span>';
                    imageHtml += '</a>';
                    imageHtml += '<a class="right carousel-control" href="#carousel-example-generic'+modalId+'" data-slide="next">';
                    imageHtml += '<span class="fa fa-angle-right" style="color: #000;"></span>';
                    imageHtml += '</a>';
                    imageHtml += '</div>';
                }
                target.find('.modal-body').html(imageHtml);
                target.css("overflow","auto");
                target.modal({
                    backdrop: false
                });
                $('#' + modalId).click(function (e) {
                    if(e.target.id == modalId){
                        target.modal('hide');
                    }
                });
                $('#' + modalId).on('hide.bs.modal', function (e) {
                    $('body').find('#' + modalId).remove();
                });
                return {
                    id: modalId
                };
            },
            close : function (modalId) {
                $('#'+modalId).find('.close').click();
            }
        }
    }();
})(jQuery);

String.prototype.endsWith=function(s){
    if(s==null||s==""||this.length==0||s.length>this.length){
        return false;
    }

    if(this.substring(this.length-s.length)==s){
        return true;
    }else{
        return false;
    }
    return true;
};
/**
 * 上传图片 注意图片的name值必须是file
 * @param fileId fileId 图片id
 * @param param 图片设置属性，目前只有图片名 fileName 这一个属性
 * @param callback 上传成功回调函数
 * @param config ajax 的一些配置
 */
function uploadFile(all_scope_path, fileId, param, callback, config){
	if (!fileId || !$("#"+fileId).val()) {
		Ewin.alert({ message: "请上传文件信息!" });
		return;
	}
	param = param || {};
	var url = all_scope_path + "/commonTool/uploadPic";
	$.ajaxFileUpload({
    	url: url,
    	type: "post",
        data: param,
        secureuri: false,
        fileElementId: fileId,
        dataType: 'json',
        success: function (data, status){
        	callback(data);
        },
        error: function (data, status, e){
        	Ewin.alert({ message: "上传文件失败!" });
        }
	});
}
/**
 * 验证图片是否满足格式
 * @param imgId
 * @param format 验证图片格式
 * @returns {boolean}
 */
function verifyImg(imgId,format){
	var location = $('#'+imgId).val();
	var fileFormats = ['bmp','jpg','png','gif','BMP','JPG','PNG','GIF'];
	if(format){
		if (typeof format == 'string') {
			fileFormats = [format];
		}else if (format instanceof Array) {
			fileFormats = format;
		}
	}
    for(var i=0;i<fileFormats.length;i++){
    	if(location.endsWith(fileFormats[i])){
    		return true;
    	}
    }
    return false;
}
