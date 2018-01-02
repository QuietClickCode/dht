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
                                    <li class="active" id="navfirstli">
                                        <a href="#notGivenPane" data-toggle="tab" id="nava1" >未分配客户</a>
                                    </li>
                                    <li>
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
                                    <div class="tab-pane active" id="notGivenPane">
                                        <div class="modal-body">
                                            <div id="toolbar" class="form-inline">
                                                <button class="btn btn-primary" type="button" onclick="checkClient()" >提交审核</button>
                                                <div class="form-group" >
                                                    <input type="text" class="form-control" id="search_client_name" placeholder="请输入客户姓名">
                                                </div>
                                                <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
                                                <span style="margin-left: 20px" id="personNum">您还有0个名额未分配</span>
                                            </div>

                                            <table id="notGivenTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="checking">
                                        <div class="modal-body">
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
    ];

    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            cmName:$('#search_client_name').val()
        };
    }
    function queryCheckingParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:1
        };
    }
    function queryPassParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:2
        };
    }
    function queryNotPassParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
            status:3
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#notGivenTable').bootstrapTable(
            "refresh",
            {
                url:"/OpeningEmpClient/queryNotGivenList"
            }
        );
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
        loadThisOpeningPersonNum();
        createTable("/OpeningEmpClient/queryNotGivenList","notGivenTable","tmId",treeColumns,queryParams,"");
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","checkingTable","tmId",treeColumns,queryCheckingParams,"miss");
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","PassTable","tmId",treeColumns,queryPassParams,"miss");
        createTable("/OpeningEmpClient/queryCheckingandpassandnotpassList","notPassTable","tmId",notPassColumns,queryNotPassParams,"miss");
        bindClickEvent();
        $('#notGivenTable').find('input[name=btSelectAll]').attr('disabled','disabled');
    }

    function change() {
        oid = $('#selectOpening').val();
        loadThisOpeningPersonNum();
        refreshTableData();
        refreshCheckingTableData();
        refreshPassableData();
        refreshNotPassTableData();
        bindClickEvent();
    }

    function checkClient() {
        var rows = $('#notGivenTable').bootstrapTable('getSelections');
        console.log(rows);
        var cmIds = '';
        if(rows.length>0){
            for(var i=0;i<rows.length;i++){
                cmIds += ','+rows[i].tmId;
            }
            cmIds = cmIds.substr(1);
        }else{
            layer.msg('请您选择客户');
            return;
        }

        $.ajax({
            type: "post",
            url: "/OpeningEmpClient/addCheckClient",
            dataType: "json",
            data: {oid:oid,cmIds:cmIds},
            success: function (data) {
                if(data.status==0){
                    layer.msg('操作成功');
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
    }

    var personNumber = 0;
    <!--加载本期人数-->
    function loadThisOpeningPersonNum() {
        $.ajax({
            type: "post",
            url: "/OpeningEmpClient/loadThisOpeningPersonNum",
            dataType: "json",
            data: {oid:oid},
            success: function (data) {
                var personNum = data.personNum;
                var usePersonNum = data.usePersonNum;
                personNumber = Number(personNum) - Number(usePersonNum);
                $('#personNum').html('您还有'+personNumber+'个名额未分配');
            }
        });
    }

    //绑定点击行事件
    function bindClickEvent() {
        $('#notGivenTable').on('check.bs.table', function (e, row, element)
        {

            var selectedArr = $('#notGivenTable').bootstrapTable('getSelections');
            if(selectedArr.length+1>personNumber){
                layer.msg('最多选中'+personNumber+'个客户');
                element.removeAttr('checked');
            }

        });

    }

</script>

<script>
    loadOpening();


</script>

<!--timer时间选择器-->
<script type="text/javascript"  src="/js/daterangepicker/moment.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        //时间插件

        $('#reportrange').daterangepicker(
            {
                startDate: moment(),
                minDate : moment(),
                showDropdowns : true,
                timePicker:true,
                timePickerIncrement:5,
                timePicker24Hour:true,//24 小时制
                opens : 'right', //日期选择框的弹出位置
                buttonClasses : [ 'btn btn-default' ],
                applyClass : 'btn-small btn-primary blue',
                cancelClass : 'btn-small',
                format: 'YYYY-MM-DD',
                locale : {
                    format: 'YYYY-MM-DD',
                    applyLabel : '确定',
                    cancelLabel : '取消',
                    fromLabel : '起始时间',
                    toLabel : '结束时间',
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    firstDay : 1
                }
            }, function(start, end, label) {
                $("#editorGoodsCouponForm #gcpStartTime").val(start.format('YYYY-MM-DD'));
                $("#editorGoodsCouponForm #gcpEndTime").val(end.format('YYYY-MM-DD'));
                $("#editorGoodsCouponForm #gcpValidTime").val(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
            });
        //设置日期菜单被选项  --结束--
    })
</script>
</body>
</html>
