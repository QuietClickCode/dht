<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>开盘管理</title>
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
<div id="toolbar" class="form-inline">

        <button class="btn btn-primary" type="button" onclick="addOpening()">添加开盘</button>


        <button class="btn btn-default" type="button" onclick="deleteOpeningList()">删除</button>


    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_Opening_name" placeholder="请输入开盘名称">
    </div>


        <button class="btn btn-default" type="button" onclick="refreshTableData()" style="margin-top: 5px">查询</button>


</div>
<div>
    <table id="OpeningTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorOpeningForm">
                    <input type="hidden" name="oid" id="oid">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                开盘名称:
                              </span>
                                <input type="text" class="form-control" name="oname" id="oname">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                 开盘有效时间:
                              </span>
                                <input id="reportrange" name="reportrange" type="text" class="form-control">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <div class="col-lg-2">
                        涉及楼栋:
                    </div>
                    <div class="col-lg-10">
                        <div class="row" id="hasFloorrow">
                            <div class="col-lg-2">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editorOpening" tabindex="-1" role="dialog" aria-labelledby="editorOpening">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorOpeningTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
                    </div>
                    <button class="btn btn-default" type="button" onclick="searchgoods()">查询</button>
                </center>

                <button class="btn btn-default" type="button" onclick="addgglrel()" id="addgoodsbtn">新增</button>
                <button class="btn btn-default" type="button" onclick="deletegglrel()" id="delgoodsbtn">删除</button>
                <button class="btn btn-primary" type="button" onclick="refreshgoodsTbody()" style="float: right;margin-bottom: 10px;">刷新</button>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgoodsname">
                                    商品名称（已有）
                                </th>
                            </tr>
                            </thead>
                            <tbody id="goodsTbody">
                            <tr>
                                <td>
                                    <div class="checkbox checkbox-info">
                                        <input id="checkbox1" class="styled" type="checkbox">
                                        <label for="checkbox1">

                                        </label>
                                    </div>
                                </td>
                                <td style="text-align: center;display:table-cell; vertical-align:bottom;">
                                    TB - Monthly
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editorOpeningRelSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editorgcglrel" tabindex="-1" role="dialog" aria-labelledby="editorgcglrel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgcglTitle">编辑商品与标签关系</h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input id="gclassNum" onclick="showMenu();" type="text" class="form-control"  placeholder="点击选择商品子类">
                    </div>
                </center>


                    <button class="btn btn-default" type="button" onclick="deletegcgllrel()" id="deletegtgclrelbtn">删除</button>


                    <button class="btn btn-primary" type="button" onclick="initgcglrel()" style="float: right;margin-bottom: 10px">刷新</button>


                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgcglname">
                                    子类名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="gcglTbody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editgcglSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<!-- 公用下拉择树 -->
<div id="menuContent1" class="menuContent1" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<!--开盘编辑-->
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
            field: 'oname',
            title: '开盘名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'hasFloors',
            title: '涉及楼栋',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'ostartTime',
            title: '开始时间',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(value!=null&&value!=''){
                    html = value.toString().split(' ')[0];
                }else{
                    html = '-';
                }
                return html;
            }
        },
        {
            field: 'oendTime',
            title: '结束时间',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(value!=null&&value!=''){
                    html = value.toString().split(' ')[0];
                }else{
                    html = '-';
                }
                return html;
            }
        },
        {
            field: 'CreateTime',
            title: '状态',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                var now = new Date();
                if(now<new Date(row.ostartTime)){
                    html='未开始';
                }
                if(now>new Date(row.ostartTime) && now<new Date(row.oendTime)){
                    html='进行中';
                }
                if(now>new Date(row.oendTime)){
                    html='已结束';
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
                rowDatas.set(row.oid,row);
                let html='';

                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorOpening(\''+row.oid+'\')"">编辑</button>&nbsp;';


                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.oid+'\',this)">删除</button>';

                return html;
            }
        }
    ]

    $(function () {
        createTable("/opening/queryOpeningList","OpeningTables","oid",treeColumns,queryParams)
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
            $('#glStarttime').val(arr[0]);
            $('#glEndtime').val(arr[1]);

            var formData=$("#editorOpeningForm").serializeObject();

            formData["glStarttimes"] = arr[0];
            formData["glEndtimes"] = arr[1];
            let url="/goods/addOpening";
            if(editorOpeningType==1){
                url="/goods/editOpening";
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
                        message: '开盘名称未通过',
                        validators: {
                            notEmpty: {
                                message: '开盘名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '开盘名称长度在1-30之间'
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
            oname: $("#search_Opening_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#OpeningTables').bootstrapTable(
            "refresh",
            {
                url:"/opening/queryOpeningLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeOpening(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeOpening(glId){
        $.ajax({
            type:"post",
            url:'/goods/removeOpening',
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

    function editorOpening(orgId){
        editorOpeningType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑开盘");
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
        if(rowData){
            $("#editorOpeningForm #oid").val(rowData.oid);
            $("#editorOpeningForm #oname").val(rowData.oname);
            $("#editorOpeningForm #ostartTime").val(rowData.ostartTime);
            $("#editorOpeningForm #oendTime").val(rowData.oendTime);
            $("#editorOpeningForm #version").val(rowData.version);
            $("#reportrange").val(rowData.ostartTime + ' - ' + rowData.oendTime);
        }else{
            $("#editorOpeningForm #oname").val('');
            $("#editorOpeningForm #oid").val('');
            $("#editorOpeningForm #ostartTime").val('');
            $("#editorOpeningForm #glEnoendTimedtime").val('');
            $("#searchDateRange").html('');

        }
    }
    /**
     * 添加商品大类
     **/
    function addOpening(){
        editorOpeningType=0;
        initFormData();

        $("#editorSysUserTitle").text("添加开盘");
        $('#editorSysUser').modal("show")
    }


</script>

<!--商品与标签关系编辑-->
<script>
    $(function () {
        $('#editorOpeningRelSubmit').click(function () {
            $('#editorOpening').modal("hide");
        });
    });
    
    var initgdataArr = new Array();

    function refreshgoodsTbody() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        initgoodsFormData(glId);
    }
    /**
     * 清除form 表单数据
     * */
    function initgoodsFormData(glId){
        $('#topgoodsname').html('商品名称（已有）');
        initgdataArr = [];
        $('#addgoodsbtn').hide();
        $('#delgoodsbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGglrelLists",
            dataType: "json",
            data:{glId:glId,pageNo:1,pageSize:1000},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="gglcheckbox" class="styled" type="checkbox" value="'+rows[i].gglId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgdataArr.push(rows[i].gid);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定商品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#goodsTbody').html(html);
            }
        });
    }

    function searchgoods() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        var gname = $('#search_Goods_name').val();
        $('#topgoodsname').html('商品名称');
        $('#addgoodsbtn').show();
        $('#delgoodsbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsLists",
            dataType: "json",
            data: {gname: gname,pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgdataArr.length>0){
                            for (var j=0; j<initgdataArr.length; j++){
                                if (rows[i].gid==initgdataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gglcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gid+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gname+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的商品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#goodsTbody').html(html);
            }
        });
    }

    function addgglrel() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        var checkboxs = $('input:checkbox[name="gglcheckbox"]:checked');
        var addggl = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggl += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGglrel",
                dataType: "json",
                data: {gids: addggl,glId:glId},
                success: function (data) {
                    layer.msg('新增成功!');
                    refreshgoodsTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
        }

    }

    function deletegglrel() {
        var checkboxs = $('input:checkbox[name="gglcheckbox"]:checked');
        if(checkboxs.length > 0){
            var gglIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    gglIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGglrel",
                dataType: "json",
                data: {gglIds: gglIds},
                success: function (data) {
                    layer.msg('删除成功!');
                    refreshgoodsTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
        }

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

<!--标签与子类关系1-->
<script>

    $(function () {
        $('#editgcglSubmit').click(function () {
            addgcglrel();
        });
    });

    function addgcglrel() {
        var glId = $('#glId').val();
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
        if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
            var gclassIds = '';
            for(var i=0; i<gcglcheckboxs.length; i++){
                gclassIds += ','+gcglcheckboxs[i].value;
            }
            gclassIds = gclassIds.substr(1);
            $.ajax({
                type:"post",
                url:"/goods/addGoodsGglrel",
                dataType: "json",
                data:{glId:glId,gclassIds:gclassIds},
                success:function(data){
                    if(data.status==0){
                        layer.msg("操作成功");
                        $('#editorgcglrel').modal('hide');
                    }else{
                        layer.msg("系统错误");
                    }

                }
            });
        }else{
            $('#editorgcglrel').modal('hide');
        }
    }

    function deletegcgllrel() {
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]:checked');
        if(gcglcheckboxs!=null&&gcglcheckboxs.length>0){
            for(var i=0;i<gcglcheckboxs.length;i++){
                $(gcglcheckboxs[i]).parent().parent().parent().remove();
            }
        }else{
            layer.msg("请选择想操作的数据");
        }
    }

    function initgcglrel() {
        var glId = $('#glId').val();
        $('#gclassNum').val('');
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGglrelLists",
            dataType: "json",
            data:{glId:glId,isOpening:0,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0;i<rows.length;i++){
                        html += '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="gcglcheckbox'+i+'"  type="checkbox" name="gcglcheckbox" onclick="" value="'+rows[i].gclassId+'">'+
                            '<label for="gcglcheckbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            rows[i].gclassname+
                            '</td>'+
                            '</tr>';
                    }
                }
                $('#gcglTbody').html(html);

                var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
                if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
                    var ggIds = '';
                    for(var i=0; i<gcglcheckboxs.length; i++){
                        ggIds += ','+gcglcheckboxs[i].value;
                    }
                    ggIds = ggIds.substr(1);
                    initgcgltree(ggIds);
                }else{
                    initgcgltree(-1);
                }

            }
        });
    }

    <!--初始化树形结构-->
    function initgcgltree(ggIds) {
        $.ajax({
            type:"post",
            url:'/goods/queryGoodsClassificationNode',
            dataType: "json",
            data:{ggId:ggIds,pageSize:1000,pageNo:1},
            async:false,
            success:function(data){
                let d=data.data;
                var nodeData=new Array();
                for(row of d){
                    let treeRow=new Object();
                    treeRow.id=row.ggId;
                    treeRow.pId=row.parentId;
                    treeRow.name=row.ggName;
                    nodeData.push(treeRow);
                }
                var zTree=$.fn.zTree.init($("#treeDemo"), setting1, nodeData);
            }
        });
    }




</script>

<!--树形结构展示-->
<script>
    var setting1 = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick1,
            onClick: onClick1
        }
    };

    function beforeClick1(treeId, treeNode) {
        return true;
    }

    function onClick1(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name;
            vId += nodes[i].id;
        }
        var gclassNum = $("#gclassNum");
        gclassNum.val(v);

        var flag = 0;
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
        if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
            for(var i=0; i<gcglcheckboxs.length; i++){
                if(vId==gcglcheckboxs[i].value){
                    $(gcglcheckboxs[i]).parent().parent().parent().remove();
                    console.log(gcglcheckboxs[i]);
                    flag++;
                }
            }
        }

        if(flag == 0){
            var html = '<tr>'+
                '<td>'+
                '<div class="checkbox checkbox-info">'+
                '<input id="gcglcheckbox'+vId+'"  type="checkbox" name="gcglcheckbox" onclick="" value="'+vId+'">'+
                '<label for="gcglcheckbox'+vId+'">'+
                '</label>'+
                '</div>'+
                '</td>'+
                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                v+
                '</td>'+
                '</tr>';
            $('#gcglTbody').prepend(html);
        }
    }

    var zNodes1;
    $.fn.zTree.init($("#treeDemo1"), setting1, zNodes1);

    function showMenu() {
        var cityObj = $("#gclassNum");
        var cityOffset = $("#gclassNum").offset();
        $("#menuContent1").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown1);
    }
    function hideMenu() {
        $("#menuContent1").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown1);
    }
    function onBodyDown1(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent1" || $(event.target).parents("#menuContent1").length>0)) {
            hideMenu();
        }
    }
</script>

<script>
    $("input[name='labelType']").click(function () {
        var val = $(this).val();
        $('#isOpening').val(val);
        if(val==1){
            $("#goodsBtn").show();
            $("#classBtn").hide();
        }else{
            $("#goodsBtn").hide();
            $("#classBtn").show();
        }
    });

    $('#goodsBtn').click(function () {
        $("#editorOpeningTitle").text("开盘");
        refreshgoodsTbody();
        $('#editorOpening').modal("show");
    });
    $('#classBtn').click(function () {
        initgcglrel();
        $('#editorgcglrel').modal("show");
    });



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
                format: 'YYYY-MM-DD HH:mm:ss',
                locale : {
                    format: 'YYYY-MM-DD HH:mm:ss',
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
                $("#editorGoodsCouponForm #gcpStartTime").val(start.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorGoodsCouponForm #gcpEndTime").val(end.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorGoodsCouponForm #gcpValidTime").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });
        //设置日期菜单被选项  --结束--
    })
</script>
</body>
</html>
