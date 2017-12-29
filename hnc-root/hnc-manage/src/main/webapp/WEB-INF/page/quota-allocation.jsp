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
    <div class="" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser" >
        <div class="modal-dialog" role="document"  style="width: 100%;">
            <div class="modal-content" style="margin-top: 5px;float: right;width: 100%">
                <div class="modal-header">
                    <h4 class="modal-title" id="editorSysUserTitle" style="float:left"></h4>

                </div>
                <div class="row">
                    <div class="col-lg-2" style="margin-top: 5px">
                        <select id="selectOpening" class="form-control">
                            <option value="">请选择开盘</option>
                        </select>
                    </div>
                </div>

                <div class="container" style="width: 100%;margin-top: 10px">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="tabbable" id="tabs-44711">
                                <ul class="nav nav-tabs" >
                                    <li class="active" id="navfirstli">
                                        <a href="#notGivenPane" data-toggle="tab" id="nava1" onclick="loadDate();">未分配客户</a>
                                    </li>
                                    <li>
                                        <a href="#goodsConfigPane" data-toggle="tab" onclick="initGoodsConfigForm(this);" id="nava2">审核中客户</a>
                                    </li>
                                    <li>
                                        <a href="pass" data-toggle="tab" onclick="initGoodsConfigForm(this);" id="nava3">已通过客户</a>
                                    </li>
                                    <li>
                                        <a href="#goodsImagePane" data-toggle="tab" onclick="initGoodsImages();" id="nava4">未通过客户</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="notGivenPane">
                                        <div class="modal-body">
                                            <table id="notGivenTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="goodsConfigPane">
                                        <div class="modal-body">
                                            <table id="checkingTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="pass">
                                        <div class="modal-body">
                                            <table id="PassTable" ></table>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="goodsImagePane">
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
    //编辑部门类型 0 新增 1 修改
    var editorOpeningType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmName',
            title: '客户姓名',
            align : 'center',
            valign : 'middle'
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
    ]

    $(function () {
//        createTable("/OpeningEmpClient/queryNotGivenList","notGivenTable","cmId",treeColumns,queryParams)
        //初始华开关选择器

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorOpeningForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorOpeningForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorOpeningForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var arr = $("#reportrange").val().split(" - ");
            $('#ostartTimes').val(arr[0]);
            $('#oendTimes').val(arr[1]);

            var formData=$("#editorOpeningForm").serializeObject();

            formData["ostartTimes"] = arr[0];
            formData["oendTimes"] = arr[1];
            formData["oremark"] = $('#oremark').val();
            var floors = '';
            var checkboxs = $('#hasFloorrow').find('input[type=checkbox]:checked');
            if(checkboxs.length>0){
                for(var i=0;i<checkboxs.length;i++){
                    floors += ','+checkboxs[i].value;
                }
                floors = floors.substr(1);
            }
            formData["floors"] = floors;
            let url="/opening/saveOpening";
            if(editorOpeningType==1){
                url="/opening/updateOpening";
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    layer.close(editSubmitIndex);
                    if(data.status==0){
                        //显示提示
                        layer.msg('操作成功');
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editorSysUser').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorOpeningForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    glName: {
                        message: '分配名额名称未通过',
                        validators: {
                            notEmpty: {
                                message: '分配名额名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '分配名额名称长度在1-30之间'
                            }
                        }
                    }
                }
            });
    }
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            oid: oid,
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#OpeningTables').bootstrapTable(
            "refresh",
            {
                url:"/opening/queryOpeningList"
            }
        );
    }
    //删除确认框
    function deleteData(oid){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeOpening(oid);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeOpening(oid){
        $.ajax({
            type:"post",
            url:'/opening/removeOpening',
            dataType: "json",
            data:{oid:oid},
            success:function(data){
                if(data.status==0){
                    layer.msg("删除成功");
                    refreshTableData();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }

    function editorOpening(orgId){
        editorOpeningType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑分配名额");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorOpeningForm #uid").val("");
        $("#editorOpeningForm #version").val("");
        $("#editorOpeningForm #uaccount").val("");
        $("#editorOpeningForm #uname").val("");
        $("#editorOpeningForm #orgIds").val("");
        $("#editorOpeningForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        initFloors(key);
        if(rowData){
            $("#editorOpeningForm #oid").val(rowData.oid);
            $("#editorOpeningForm #oname").val(rowData.oname);
            $("#editorOpeningForm #ostartTime").val(rowData.ostartTime);
            $("#editorOpeningForm #oendTime").val(rowData.oendTime);
            $("#editorOpeningForm #onum").val(rowData.onum);
            $("#editorOpeningForm #omenberNum").val(rowData.omenberNum);
            $("#editorOpeningForm #oendTime").val(rowData.oendTime);
            $("#editorOpeningForm #version").val(rowData.version);
            $("#reportrange").val(rowData.ostartTime.split(' ')[0] + ' - ' + rowData.oendTime.split(' ')[0]);
            $("#editorOpeningForm #oremark").val(rowData.oremark);
        }else{
            $("#editorOpeningForm #oname").val('');
            $("#editorOpeningForm #oid").val('');
            $("#editorOpeningForm #ostartTime").val('');
            $("#editorOpeningForm #glEnoendTimedtime").val('');
            $("#editorOpeningForm #onum").val('');
            $("#editorOpeningForm #omenberNum").val('');
            $("#editorOpeningForm #oremark").val('');
            $("#reportrange").val('');
            $("#searchDateRange").html('');

        }
    }
    /**
     * 添加商品大类
     **/
    function addOpening(){
        editorOpeningType=0;
        initFormData();

        $("#editorSysUserTitle").text("添加分配名额");
        $('#editorSysUser').modal("show")
    }

    function initFloors(oid) {
        $.ajax({
            type: "post",
            url: "/opening/queryOFrelByOid",
            dataType: "json",
            data: {oid: oid},
            success: function (data) {
                var rows = data.ofRelList;
                $('#hasFloorrow').html('');
                var html = '';
                for(var i=0;i<rows.length;i++){
                    var selected = '';
                    if(rows[i].selectedfid!=null){
                        selected = 'checked="checked"';
                    }else{
                        selected = '';
                    }
                    html  = '<div class="col-lg-2">'+
                        '<input type="checkbox" value="'+rows[i].fid+'" '+selected+'> '+rows[i].fname+
                        '</div>';
                    $('#hasFloorrow').append(html);
                }
            }
        });
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
        createTable("/OpeningEmpClient/queryNotGivenList","notGivenTable","cmId",treeColumns,queryParams)
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
