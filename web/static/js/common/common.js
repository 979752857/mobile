(function ($) {
    /**
     * 提示框
     * @type {{alert, confirm, dialog}}
     */
    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                              '<div class="modal-dialog modal-sm">' +
                                  '<div class="modal-content">' +
                                      '<div class="modal-header">' +
                                          '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
                                          '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                                      '</div>' +
                                      '<div class="modal-body">' +
                                      '<p>[Message]</p>' +
                                      '</div>' +
                                       '<div class="modal-footer">' +
        '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
        '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
    '</div>' +
                                  '</div>' +
                              '</div>' +
                          '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
                              '<div class="modal-dialog">' +
                                  '<div class="modal-content">' +
                                      '<div class="modal-header">' +
                                          '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
                                          '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
                                      '</div>' +
                                      '<div class="modal-body">' +
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
                width: 300,
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
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
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
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {$(this).modal('hide'); callback(true); });
                            modal.find('.cancel').click(function () {$(this).modal('hide'); callback(false); });
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
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () { },
                    onShown: function (e) { }
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
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
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
function uploadFile(fileId,param,callback,config){
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


function initDatePicker(idName) {
    $('#'+idName).datepicker({
        autoclose: true
    })
}