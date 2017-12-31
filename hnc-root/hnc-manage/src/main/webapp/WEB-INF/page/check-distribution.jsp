<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>分配名额管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">

    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/font-awesome-4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <style>
        li{
            list-style:none;
        }
    </style>
</head>
<body>
<div id="addandeditgoods" style="">
    <div class="" id="queryClient" tabindex="-1" role="dialog" aria-labelledby="queryClient" >
        <div class="modal-dialog" role="document"  style="width: 100%;">
            <div class="modal-content" style="margin-top: 5px;float: right;width: 100%">
                <div class="modal-header">
                    <h4 class="modal-title" id="queryClientTitle" style="float:left"></h4>
                </div>
                <div class="row">
                    <div class="col-lg-2" style="margin-top: 5px">
                        <select id="selectOpening" class="form-control" onchange="change();">
                        </select>
                    </div>
                </div>

                <div class="container" style="width: 100%;margin-top: 10px">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="tabbable" id="tabs-44711">
                                <ul class="nav nav-tabs" >
                                    <li class="active">
                                        <a href="#checking" data-toggle="tab"  id="nava2">审核中客户</a>
                                    </li>
                                    <li>
                                        <a href="#pass" data-toggle="tab"  id="nava3">已通过客户</a>
                                    </li>
                                    <li>
                                        <a href="#notpass" data-toggle="tab"  id="nava4">未通过客户</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="checking">
                                        <div class="modal-body">
                                            <div id="toolbar" class="form-inline">
                                                <button class="btn btn-primary" type="button" onclick="checkClient(2)" >通过审核</button>
                                                <button class="btn btn-warning" type="button" onclick="checkClient(3)" >未通过审核</button>
                                                <div class="form-group" >
                                                    <input type="text" class="form-control" id="search_client_name" placeholder="请输入客户姓名">
                                                </div>
                                                <button class="btn btn-default" type="button" onclick="refreshCheckingTableData()">查询</button>
                                            </div>
                                            <table id="checkingTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="pass">
                                        <div class="modal-body">
                                            <table id="PassTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="notpass">
                                        <div class="modal-body">
                                            <table id="notPassTable" ></table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="showClientInfo" tabindex="-1" role="dialog" aria-labelledby="showClientInfo">
    <div class="modal-dialog" role="document"  style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgtgclTitle">客户详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客户姓名:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="tmName" id="tmName"  disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">电话:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="tmPhone" id="tmPhone"  disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="tmSex" name="tmSex" disabled="disabled" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">年龄:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="tmAge" name="tmAge"  disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证号码:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="tmIdCard" name="tmIdCard" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">渠道:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="tmChannel" id="tmChannel" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注:</label>
                        <div class="col-sm-9">
                            <textarea type="text" class="form-control" id="tmInfo" name="tmInfo" disabled="disabled">
                            </textarea>
                        </div>
                    </div>
                    <div id="invention">
                        <%--<div class="form-group">--%>
                        <%--<label class="col-sm-3 control-label">备注:</label>--%>
                        <%--<div class="col-sm-9">--%>
                        <%--<textarea type="text" class="form-control" id="" name="tmInfo" disabled="disabled">--%>
                        <%--</textarea>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="hidemodal();">确认</button>
            </div>
        </div>

    </div>
</div>

<!--客户审核框-->
<div class="modal fade" id="checkClientModal" tabindex="-1" role="dialog" aria-labelledby="checkGoodsModal">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >审核意见</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control" id="checkClientTextarea" value="">

                </textarea>
                <input type="hidden" id="oecIds">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="hidemodal()">关闭</button>
                <button type="button" class="btn btn-primary" onclick="checkClientfunction()" >确认</button>
            </div>
        </div>

    </div>
</div>

<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<!--分配名额编辑-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();

    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmName',
            title: '客户姓名',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                rowDatas.set(row.tmId,row);
                var cmId = row.tmId;
                var html = '';
                html = '<a onclick="showClientInfo('+cmId+')">'+value+'</a>';
                return html;
            }
        },
        {
            field: 'tmPhone',
            title: '电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'empName',
            title: '职业顾问',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmInfo',
            title: '备注',
            align : 'center',
            valign : 'middle'
        }
    ];
    var notPassColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmName',
            title: '客户姓名',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                rowDatas.set(row.tmId,row);
                var cmId = row.tmId;
                var html = '';
                html = '<a onclick="showClientInfo('+cmId+')">'+value+'</a>';
                return html;
            }
        },
        {
            field: 'tmPhone',
            title: '电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'empName',
            title: '职业顾问',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmInfo',
            title: '备注',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'oecMsg',
            title: '审核意见',
            align : 'center',
            valign : 'middle'
        }
    ]


    function queryCheckingParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:1,
            cmName:$('#search_client_name').val(),
            isManage:'yes'
        };
    }
    function queryPassParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:2,
            isManage:'yes'
        };
    }
    function queryNotPassParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:3,
            isManage:'yes'
        };
    }

    function refreshCheckingTableData() {
        $('#checkingTable').bootstrapTable(
            "refresh",
            {
                url:"/OpeningEmpClient/queryCheckingandpassandnotpassList"
            }
        );
    }
    function refreshPassableData() {
        $('#PassTable').bootstrapTable(
            "refresh",
            {
                url:"/OpeningEmpClient/queryCheckingandpassandnotpassList"
            }
        );
    }
    function refreshNotPassTableData() {
        $('#notPassTable').bootstrapTable(
            "refresh",
            {
                url:"/OpeningEmpClient/queryCheckingandpassandnotpassList"
            }
        );
    }


    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));

        if(rowData){
            loadInvention(key);
            $(" #tmName").val(rowData.tmName);
            $(" #tmPhone").val(rowData.tmPhone);
            $(" #tmAge").val(rowData.tmAge);
            $(" #tmIdCard").val(rowData.tmIdCard);
//            $(" #tmChannel").val(rowData.tmChannel);
            $(" #tmInfo").val(rowData.tmInfo);

            var sex = rowData.tmSex;
            if(sex==0){
                $(" #tmSex").val('女');
            }else{
                $(" #tmSex").val('男');
            }


//            $(" #version").val(rowData.version);
//            $("#reportrange").val(rowData.ostartTime.split(' ')[0] + ' - ' + rowData.oendTime.split(' ')[0]);
//            $(" #oremark").val(rowData.oremark);
        }else{
//            $(" #oname").val('');
//            $(" #oid").val('');
//            $(" #ostartTime").val('');
//            $(" #glEnoendTimedtime").val('');
//            $(" #onum").val('');
//            $(" #omenberNum").val('');
//            $(" #oremark").val('');
//            $("#reportrange").val('');
//            $("#searchDateRange").html('');

        }
    }

    function loadOpening() {
        $.ajax({
            type: "post",
            url: "/opening/queryOpeningList",
            dataType: "json",
            data: {pageNo:1,pageSize:10000},
            success: function (data) {
                var rows = data.rows;
                if(rows.length>0&&rows!=null){
                    for(var i=0;i<rows.length;i++){
                        var selected = '';
                        if(i==0){
                            selected = 'selected="selected"';
                        }
                        var html = '<option '+selected+' value="'+rows[i].oid+'">'+rows[i].oname+'</option>';
                        $('#selectOpening').append(html);
                    }
                    loadDate();
                }
            }
        });
    }

    var oid;
    function loadDate() {
        oid = $('#selectOpening').val();
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","checkingTable","tmId",treeColumns,queryCheckingParams,"");
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","PassTable","tmId",treeColumns,queryPassParams,"miss");
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","notPassTable","tmId",notPassColumns,queryNotPassParams,"toolbar");
    }

    function change() {
        oid = $('#selectOpening').val();
        refreshCheckingTableData();
        refreshPassableData();
        refreshNotPassTableData();
    }

    function checkClient(status) {
        var rows = $('#checkingTable').bootstrapTable('getSelections');
        var oecIds = '';
        if( rows!=null&&rows.length>0){
            for(var i=0;i<rows.length;i++){
                oecIds += ','+rows[i].oecId;
            }
            oecIds = oecIds.substr(1);
        }else{
            layer.msg('请您选择客户');
            return;
        }

        if(status==3){
            $('#oecIds').val(oecIds);
            $('#checkClientTextarea').val('');
            $('#checkClientModal').modal('show');
        }else{
            uploadClientStatus(oecIds,status,'');
        }

    }

    function uploadClientStatus(oecIds,status,msg) {
        $.ajax({
            type: "post",
            url: "/OpeningEmpClient/updateOpeningEmpClient",
            dataType: "json",
            data: {oecIds:oecIds,status:status,msg:msg},
            success: function (data) {
                if(data.status==0){
                    layer.msg('操作成功');
                    hidemodal();
                    change();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }

    function showClientInfo(cmId) {
        initFormData(cmId);
        $('#showClientInfo').modal("show");
    }

    function loadInvention(cmId) {
        $('#invention').html('');
        $('#tmChannel').val('');
        $.ajax({
            type: "post",
            url: "/clientIntention/queryClientIntentionList",
            dataType: "json",
            data: {cmId:cmId},
            success: function (data) {
                var rows = data.rows;

                if(rows!=null &&rows.length>0){
                    var channelName = rows[0].channelName;
                    $('#tmChannel').val(channelName);
                    var html = '';
                    for(var i=0;i<rows.length;i++){
                        var row = rows[i];
                        html = '<div class="form-group">'+
                            '<label class="col-sm-3 control-label">需求'+(i+1)+':</label>'+
                            '<div class="col-sm-9">'+
                            '<textarea type="text" class="form-control" id="" name="tmInfo" disabled="disabled">' +
                            '意向楼栋:' + row.floorsName +
                            '\n意向户型:' + row.hoursesName +
                            '\n个性需求:'+ row.iremark +
                            '</textarea>'+
                            '</div>'+
                            '</div>';
                        $('#invention').append(html);
                    }
                }
            }
        });
    }

    function hidemodal() {
        $('#showClientInfo').modal('hide');
        $('#checkClientModal').modal('hide');
    }

    function checkClientfunction() {
        var checkClientTextarea = $('#checkClientTextarea').val();
        var oecIds = $('#oecIds').val();
        uploadClientStatus(oecIds,3,checkClientTextarea);
    }

    <!--日期格式化-->
    Date.prototype.pattern=function(fmt) {
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        var week = {
            "0" : "/u65e5",
            "1" : "/u4e00",
            "2" : "/u4e8c",
            "3" : "/u4e09",
            "4" : "/u56db",
            "5" : "/u4e94",
            "6" : "/u516d"
        };
        if(/(y+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        if(/(E+)/.test(fmt)){
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
        }
        for(var k in o){
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
</script>

<script>
    loadOpening();
</script>


</body>
</html>
