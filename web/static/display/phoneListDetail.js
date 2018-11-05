// Data Tables - Config
(function($) {
    'use strict';
    if ( $.isFunction( $.fn[ 'dataTable' ] ) ) {
        $.extend(true, $.fn.dataTable.defaults, {
            sDom: "<'row datatables-header form-inline'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>r><'table-responsive't><'row datatables-footer'<'col-sm-12 col-md-6'i><'col-sm-12 col-md-6'p>>",
            oLanguage: {
                sLengthMenu: '_MENU_ 每页显示记录条数',
                sProcessing: '<i class="fa fa-spinner fa-spin"></i> 加载中'
            },
            fnInitComplete: function( settings, json ) {
                // select 2
                if ( $.isFunction( $.fn[ 'select2' ] ) ) {
                    $('.dataTables_length select', settings.nTableWrapper).select2({
                        minimumResultsForSearch: -1
                    });
                }
                var options = $( 'table', settings.nTableWrapper ).data( 'plugin-options' ) || {};
                // search
                var $search = $('.dataTables_filter input', settings.nTableWrapper);
                $search
                    .attr({
                        placeholder: typeof options.searchPlaceholder !== 'undefined' ? options.searchPlaceholder : '查找'
                    })
                    .addClass('form-control');
                if ( $.isFunction( $.fn.placeholder ) ) {
                    $search.placeholder();
                }
            }
        });
    }
}).apply( this, [ jQuery ]);

/*Datatable - editable*/
(function( $ ) {
    'use strict';
    var EditableTable = {
        options: {
            addButton: '#addToTable',
            table: '#datatable-editable',
            dialog: {
                wrapper: '#dialog',
                cancelButton: '#dialogCancel',
                confirmButton: '#dialogConfirm',
            }
        },
        initialize: function() {
            this
                .setVars()
                .build()
                .events();
        },
        setVars: function() {
            this.$table				= $( this.options.table );
            this.$addButton			= $( this.options.addButton );
            // dialog
            this.dialog				= {};
            this.dialog.$wrapper	= $( this.options.dialog.wrapper );
            this.dialog.$cancel		= $( this.options.dialog.cancelButton );
            this.dialog.$confirm	= $( this.options.dialog.confirmButton );
            return this;
        },
        build: function() {
            this.datatable = this.$table.DataTable({
                aoColumns: [
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    { "bSortable": false }
                ]
            });
            window.dt = this.datatable;
            return this;
        },
        events: function() {
            var _self = this;
            this.$table
                .on('click', 'a.save-row', function( e ) {
                    e.preventDefault();
                    _self.rowSave( $(this).closest( 'tr' ) );
                })
                .on('click', 'a.cancel-row', function( e ) {
                    e.preventDefault();

                    _self.rowCancel( $(this).closest( 'tr' ) );
                })
                .on('click', 'a.edit-row', function( e ) {
                    e.preventDefault();

                    _self.rowEdit( $(this).closest( 'tr' ) );
                })
                .on( 'click', 'a.remove-row', function( e ) {
                    e.preventDefault();
					/*var $row = $(this).closest( 'tr' );
					 var value;
					 $row.children( 'td' ).each(function( i ) {
					 var $this = $row;
					 if(i == 0) {
					 value = $this.find('input').val();
					 alert($this.find('td').val());
					 }
					 });
					 if(window.confirm('你确定要删除'+value+'吗？')){
					 _self.rowRemove( $row );
					 return true;
					 }	*/
                });
            this.$addButton.on( 'click', function(e) {
                e.preventDefault();
                _self.rowAdd();
            });
            this.dialog.$cancel.on( 'click', function( e ) {
                e.preventDefault();
                $.magnificPopup.close();
            });
            return this;
        },
        // ==========================================================================================
        // ROW FUNCTIONS
        // ==========================================================================================
        rowCancel: function( $row ) {
            var _self = this,
                $actions,
                i,
                data;
            if ( $row.hasClass('adding') ) {
                this.rowRemove( $row );
            } else {
                data = this.datatable.row( $row.get(0) ).data();
                this.datatable.row( $row.get(0) ).data( data );
                $actions = $row.find('td.actions');
                if ( $actions.get(0) ) {
                    this.rowSetActionsDefault( $row );
                }
                this.datatable.draw();
            }
        },
        rowEdit: function( $row ) {
            var _self = this,
                data;
            data = this.datatable.row( $row.get(0) ).data();
            $row.children( 'td' ).each(function( i ) {
                var $this = $( this );
                if ( $this.hasClass('actions') ) {
                    _self.rowSetActionsEditing( $row );
                } else if($this.hasClass('employeeid')) {
                    $this.html( '<input type="text" readOnly="true" class="form-control input-block" value="' + data[i] + '"/>' );
                }  else {
                    $this.html( '<input type="text" class="form-control input-block" value="' + data[i] + '"/>' );
                }
            });
        },
        rowSave: function( $row ) {
            var _self     = this,
                $actions,
                values    = [];
            if ( $row.hasClass( 'adding' ) ) {
                this.$addButton.removeAttr( 'disabled' );
                $row.removeClass( 'adding' );
            }
            var num = 0;
            values = $row.find('td').map(function() {
                var $this = $(this);
                if ( $this.hasClass('actions') ) {
                    _self.rowSetActionsDefault( $row );
                    return _self.datatable.cell( this ).data();
                } else {
                    return $.trim( $this.find('input').val() );
                }
            });
            if(values[0] == ""){
                window.location.href=contextPath+"/property/add?property="+values[1]+"&flag="+values[2]+"&info="+values[3];
            }else{
                window.location.href=contextPath+"/employee/update?employeeid="+values[0]+"&account="+values[1]+"&name="+values[2]+"&phone="+values[3]+"&moneycard="+values[4]+"&sex="+values[5]+"&identity="+values[6];
            }
            this.datatable.row( $row.get(0) ).data( values );
            $actions = $row.find('td.actions');
            if ( $actions.get(0) ) {
                this.rowSetActionsDefault( $row );
            }
            this.datatable.draw();
        },
        rowRemove: function( $row ) {
            if ( $row.hasClass('adding') ) {
                this.$addButton.removeAttr( 'disabled' );
            }
            this.datatable.row( $row.get(0) ).remove().draw();
        },
        rowSetActionsEditing: function( $row ) {
            $row.find( '.on-editing' ).removeClass( 'hidden' );
            $row.find( '.on-default' ).addClass( 'hidden' );
        },
        rowSetActionsDefault: function( $row ) {
            $row.find( '.on-editing' ).addClass( 'hidden' );
            $row.find( '.on-default' ).removeClass( 'hidden' );
        }
    };
    $(function() {
        EditableTable.initialize();
    });
}).apply( this, [ jQuery ]);

function getData() {
    var keyword = "";
    var pageNo = "1";
    var pageSize = "10";
    $.ajax({
        type: "GET",
        url:contextPath + "/data/phoneListDetail",
        data:{keyword:keyword, pageNo:pageNo, pageSize:pageSize},// 要提交的表单
        success: function(data) {
            if(!data){
                $('#errmsg').html("网络传输错误,请重试！");
                return ;
            }
            var result = eval('(' + data + ')');
            var html = "";
            html += "<tr id=\"property${employee.employeeId}\" class=\"gradeX\">";
            html += "<td class=\"employeeid\">${employee.employeeId}</td>";
            html += "<td>${employee.account}</td>";
            html += "<td class=\"actions\">";
            html += "<a href=\"javascript:;\" class=\"hidden on-editing save-row\"><i class=\"fa fa-save\"></i></a>";
            html += "<a href=\"#\" class=\"hidden on-editing cancel-row\"><i class=\"fa fa-times\"></i></a>";
            html += "<a href=\"javascript:;\" class=\"on-default edit-row\"><i class=\"fa fa-pencil\"></i></a>";
            html += "<a href=\"#\" class=\"hidden on-editing cancel-row\"><i class=\"fa fa-times\"></i></a>";
            html += "<a href=\"javascript:;\" onclick=\"deleteProperty(${employee.employeeId})\" class=\"on-default remove-row\"><i class=\"fa fa-search\"></i></a>";
            html += "</td> </tr>";

        },
        error:function(e){
            alert("网络传输错误！！");
        }
    });
}

function deleteProperty(propertyid){
    window.location.href=contextPath + "/property/delete?propertyid="+propertyid;
}
function addemployee(){
    window.location.href=contextPath + "/employee/addshow";
}