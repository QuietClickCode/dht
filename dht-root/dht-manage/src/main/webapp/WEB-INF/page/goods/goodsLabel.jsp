<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品标签管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">

    <link rel="stylesheet" href="<%=path%>/js/timer/css/daterangepicker-1.3.7.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/daterangepicker-bs3.css">
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
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsLabel">
        <button class="btn btn-primary" type="button" onclick="addGoodsLabel()">添加商品标签</button>
    </ex:perm>
    <ex:perm url="goods/addGoodsLabel">
        <button class="btn btn-default" type="button" onclick="deleteGoodsLabelList()">删除</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_GoodsLabel_name" placeholder="请输入商品标签名称">
    </div>

    <ex:perm url="goods/queryGoodsLabelLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()" style="margin-top: 5px">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsLabelTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsLabelForm">
                    <input type="hidden" name="glId" id="glId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品标签名称:
                              </span>
                                <input type="text" class="form-control" name="glName" id="glName">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                 商品标签有效时间:
                              </span>
                                <input type="hidden" class="form-control" name="glStarttime" id="glStarttime">
                                <input type="hidden" class="form-control" name="glEndtime" id="glEndtime">
                                <div id="reportrange" class="pull-left dateRange" style="width:350px;background:white;">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    <span id="searchDateRange"></span>
                                    <b class="caret"></b>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                标签类型:
                              </span>
                                <div class="radio " style="display: inline-block;">
                                    <input type="hidden" id="isGoodslabel" name="isGoodslabel">
                                    <input type="radio" name="labelType" id="isGoodsShow" value="1" checked>
                                    <label for="isGoodsShow">
                                        选择商品
                                    </label>
                                </div>
                                <div class="radio" style="display: inline-block;margin-left: 30px">
                                    <input type="radio" name="labelType" id="isClassShow" value="0" >
                                    <label for="isClassShow">
                                        选择类型
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span id="goodsBtn" class="input-group-addon" style="cursor: pointer">
                                 选择商品
                              </span>
                                <span id="classBtn" class="input-group-addon" style="cursor: pointer;display: none">
                                    选择类型
                                </span>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editorGoodsLabel" tabindex="-1" role="dialog" aria-labelledby="editorGoodsLabel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorGoodsLabelTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input type="text" class="form-control" id="search_GoodsBrand_name" placeholder="请输入商品名称">
                    </div>
                    <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
                </center>
                <button class="btn btn-default" type="button" onclick="refreshTableData()">新增</button>
                <button class="btn btn-default" type="button" onclick="refreshTableData()">删除</button>


                <form id="editorGoodsLabelRelForm">

                        <div class="row clearfix">
                            <div class="col-md-12 column">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="width: 30px;text-align: center">
                                            <div class="checkbox checkbox-info">
                                                <input id="checkbox4" class="styled" type="checkbox">
                                                <label for="checkbox4">

                                                </label>
                                            </div>
                                        </th>
                                        <th style="text-align: center">
                                            商品名称（已有）
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="myTbody">
                                    <tr>
                                        <td>
                                            <div class="checkbox checkbox-info">
                                                <input id="checkbox1" class="styled" type="checkbox">
                                                <label for="checkbox1">

                                                </label>
                                            </div>
                                        </td>
                                        <td style="text-align: center">
                                            TB - Monthly
                                        </td>
                                    </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editorGoodsLabelRelSubmit">确认</button>
            </div>
        </div>
    </div>
</div>


<!-- 公用下拉择树 -->
<div id="orgNodeContent" class="orgNodeContent" style="display:none; position: absolute;z-index:1059">
    <ul id="orgTree" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsLabelType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'glName',
            title: '商品标签名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'glStarttime',
            title: '开始时间',
            align : 'center',
            valign : 'middle',
        },
        {
            field: 'glEndtime',
            title: '结束时间',
            align : 'center',
            valign : 'middle',
        },
        {
            field: 'CreateTime',
            title: '状态',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                rowDatas.set(row.glId,row);
                let html='';
                var now = new Date();
                if(now<new Date(row.glStarttime)){
                    html='未生效';
                }
                if(now>new Date(row.glStarttime) && now<new Date(row.glEndtime)){
                    html='生效中';
                }
                if(now>new Date(row.glEndtime)){
                    html='已失效';
                }
                return html;
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.glId,row);
                let html='';
                <ex:perm url="goods/editGoodsLabel">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsLabel(\''+row.glId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsLabel">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.glId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsLabelLists","GoodsLabelTables","glId",treeColumns,queryParams)
        //初始华开关选择器

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsLabelForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsLabelForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsLabelForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var arr = $("#searchDateRange").html().split(" - ");
            $('#glStarttime').val(arr[0]);
            $('#glEndtime').val(arr[1]);

            var sendData=new Array();
            var formData=$("#editorGoodsLabelForm").serializeObject();

            let url="/goods/addGoodsLabel";
            if(editorGoodsLabelType==1){
                url="/goods/editGoodsLabel";
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
                        layer.msg(data.msg);
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
        $('#editorGoodsLabelForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    gbName: {
                        message: '商品品牌名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品品牌名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品品牌名称长度在1-30之间'
                            }
                        }
                    },
                    gbImgpath: {
                        message: '商品品牌logo未通过',
                        validators: {
                            notEmpty: {
                                message: '商品品牌logo不能为空'
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
            gbName: $("#search_GoodsLabel_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsLabelTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLabelLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsLabel(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsLabel(glId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsLabel',
            dataType: "json",
            data:{glId:glId},
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


    var zNodes;
    function editorGoodsLabel(orgId){
        editorGoodsLabelType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品标签");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsLabelForm #uid").val("");
        $("#editorGoodsLabelForm #version").val("");
        $("#editorGoodsLabelForm #uaccount").val("");
        $("#editorGoodsLabelForm #uname").val("");
        $("#editorGoodsLabelForm #orgIds").val("");
        $("#editorGoodsLabelForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsLabelForm #glId").val(rowData.glId);
            $("#editorGoodsLabelForm #glName").val(rowData.glName);
            $("#editorGoodsLabelForm #isGoodslabel").val(rowData.isGoodslabel);
            $("#editorGoodsLabelForm #glStarttime").val(rowData.glStarttime);
            $("#editorGoodsLabelForm #glEndtime").val(rowData.glEndtime);
            $("#searchDateRange").html(rowData.glStarttime + ' - ' + rowData.glEndtime);

            if(rowData.isGoodslabel==1){
                $('#isGoodsShow').attr("checked","checked");
            }else{
                $('#isClassShow').attr("checked","checked");
            }

        }else{
            $("#editorGoodsLabelForm #glName").val('');
            $("#editorGoodsLabelForm #glId").val('');
            $("#editorGoodsLabelForm #isGoods").val('');
            $("#editorGoodsLabelForm #glStarttime").val('');
            $("#editorGoodsLabelForm #glEndtime").val('');
            $("#searchDateRange").html('');
            $('#isGoodsShow').attr("checked","checked");
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsLabel(){
        editorGoodsLabelType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();

        $("#editorSysUserTitle").text("添加商品标签");
        $('#editorSysUser').modal("show")
    }
    /**
     * 重新加载树型结构
     **/
    function reloadOrgTree(uid){
        $.fn.zTree.init($("#orgTree"), setting, zNodes);
        var rowData=rowDatas.get(parseInt(uid,10));
        let selectOrgIds="";
        if(rowData){
            selectOrgIds=rowData.orgIds
        }
        $.ajax({
            type:"post",
            url:'/org/reqOrgTree',
            dataType: "json",
            data:{selectOrgIds:selectOrgIds},
            async:false,
            success:function(data){
                let nodeData=data.data;
                var zTree=$.fn.zTree.init($("#orgTree"), setting, nodeData);
            }
        });
    }

    /***********************************************************************************/
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y" : "s", "N" : "s" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree"),
            nodes = zTree.getCheckedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name+",";
            vId += nodes[i].id+",";
        }
        var orgPname = $("#orgNms");
        var orgPid_ = $("#orgIds");
        orgPname.val(v);
        orgPid_.val(vId);
        //hideOrgTree();
    }

    function showOrgTree() {
        var cityObj = $("#orgNms");
        var cityOffset = $("#orgNms").offset();
        $("#orgNodeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideOrgTree() {
        $("#orgNodeContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "orgNodeContent" || $(event.target).parents("#orgNodeContent").length>0)) {
            hideOrgTree();
        }
    }
    
    function deleteGoodsLabelList() {
        var objs = $('#GoodsLabelTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removeGoodsLabel(objs[i].glId);
                }
            }, function(){
            });
        }else{
            layer.msg("请选择需要删除的品牌！");
        }
    }


</script>

<script>
    $("input[name='isGoods']").click(function () {
        var val = $(this).val();
        if(val==1){
            $("#goodsBtn").show();
            $("#classBtn").hide();
        }else{
            $("#goodsBtn").hide();
            $("#classBtn").show();
        }
    });

    $('#goodsBtn').click(function () {
        $("#editorGoodsLabelTitle").text("商品标签");
        $('#editorGoodsLabel').modal("show");
    });
    $('#classBtn').click(function () {
        $("#editorGoodsLabelTitle").text("类型标签");
        $('#editorGoodsLabel').modal("show");
    });



</script>

<!--timer时间选择器-->
<script type="text/javascript"  src="/js/timer/js/moment.js"></script>
<script type="text/javascript"  src="/js/timer/js/daterangepicker-1.3.7.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        //时间插件
        $('#reportrange span').html(moment().subtract('hours', 1).format('YYYY-MM-DD HH:mm:ss') + ' - ' + moment().format('YYYY-MM-DD HH:mm:ss'));

        $('#reportrange').daterangepicker(
            {
                // startDate: moment().startOf('day'),
                //endDate: moment(),
                //minDate: '01/01/2012',	//最小时间
                //maxDate : moment(), //最大时间
                /*dateLimit : {
                 days : 30
                 }, //起止时间的最大间隔*/
                showDropdowns : true,
                showWeekNumbers : false, //是否显示第几周
                timePicker : true, //是否显示小时和分钟
                timePickerIncrement : 60, //时间的增量，单位为分钟
                timePicker12Hour : false, //是否使用12小时制来显示时间
                ranges : {
                    //'最近1小时': [moment().subtract('hours',1), moment()],
                    '今日': [moment().startOf('day'), moment()],
                    '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                    '最近7日': [moment().subtract('days', 6), moment()],
                    '最近30日': [moment().subtract('days', 29), moment()]
                },
                opens : 'right', //日期选择框的弹出位置
                buttonClasses : [ 'btn btn-default' ],
                applyClass : 'btn-small btn-primary blue',
                cancelClass : 'btn-small',
                format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式
                separator : ' to ',
                locale : {
                    applyLabel : '确定',
                    cancelLabel : '取消',
                    fromLabel : '起始时间',
                    toLabel : '结束时间',
                    customRangeLabel : '自定义',
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    firstDay : 1
                }
            }, function(start, end, label) {//格式化日期显示框

                $('#reportrange span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });

        //设置日期菜单被选项  --开始--
        var dateOption ;
        if("${riqi}"=='day') {
            dateOption = "今日";
        }else if("${riqi}"=='yday') {
            dateOption = "昨日";
        }else if("${riqi}"=='week'){
            dateOption ="最近7日";
        }else if("${riqi}"=='month'){
            dateOption ="最近30日";
        }else if("${riqi}"=='year'){
            dateOption ="最近一年";
        }else{
            dateOption = "自定义";
        }
        $(".daterangepicker").find("li").each(function (){
            if($(this).hasClass("active")){
                $(this).removeClass("active");
            }
            if(dateOption==$(this).html()){
                $(this).addClass("active");
            }
        });
        //设置日期菜单被选项  --结束--
    })
</script>
</body>
</html>
