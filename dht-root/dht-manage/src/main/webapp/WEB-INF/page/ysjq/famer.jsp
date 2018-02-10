<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>农户管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="/goods/addFamer">
        <button class="btn btn-primary" type="button" onclick="addFamer()" style="margin-bottom: 5px">添加农户</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_Famer_name" placeholder="请输入农户名称">
    </div>

    <ex:perm url="/goods/queryFamerLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="FamerTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorFamerForm">
                    <input type="hidden" name="gfId" id="gfId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                农户名称:
                              </span>
                                <input type="text" class="form-control" name="gfName" id="gfName">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                农户价格:
                              </span>
                                <input type="text" class="form-control" name="gfPrice" id="gfPrice">
                            </div>
                        </div>
                    </div>
                </form>
                <div class="row" id="cityrow">
                    <div class="col-lg-1">
                        <div class="input-group">
                            <div class="checkbox checkbox-info">
                                <input id="checkbox1" class="styled" type="checkbox" value="">
                                <label for="checkbox1">
                                    a
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" id="freeAreadiv">
                    <textarea id="textArea" name="textArea" class="form-control" style="width: 100%">

                    </textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
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
<!--农户-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorFamerType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fname',
            title: '姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fethnic',
            title: '民族',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fphone',
            title: '电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'imgUrl',
            title: '头像',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                var html='';
                var html = '<img src="'+value+'" style="width: 200px;height: 150px"/>';
                return html;
            }
        },
        {
            field: 'fbirth',
            title: '生日',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                html = value.substr(0,10);
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
                rowDatas.set(row.fid,row);
                let html='';
                <ex:perm url="goods/editFamer">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorFamer(\''+row.fid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                if(row.gfId!=0){
                    <ex:perm url="goods/removeFamer">
                    html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.fid+'\')">删除</button>';
                    </ex:perm>
                }
                return html;
            }
        }
    ]

    $(function () {
        createTable("/famer/queryFamerLists","FamerTables","fid",treeColumns,queryParams)
        //初始华开关选择器

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorFamerForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorFamerForm').data('bootstrapValidator').validate();
            if(!$('#editorFamerForm').data('bootstrapValidator').isValid()){
                return;
            }

            var formData=$("#editorFamerForm").serializeObject();

            let url="/goods/addFamer";
            if(editorFamerType==1){
                url="/goods/editFamer";
            }
            formData["gfPrice"] = parseInt(formData["gfPrice"]*100);

            if(formData["gfId"]==0){
                formData["gfFreeArea"] = $('#textArea').val();
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(url == '/goods/addFamer'){
                        var rows = data.rows;
                        if(rows!=null){
                            $('#gfId').val(rows.gfId);
                            refreshTableData();
                            layer.msg('新增成功');
                            savecgfrel();
                            $('#editorSysUser').modal('hide');
                        }else{
                            layer.msg('新增失败');
                        }
                    }else{
                        if(data.status==0){
                            //显示提示
                            layer.msg(data.msg);
                            //刷新数据
                            refreshTableData();
                            savecgfrel();
                            //关闭弹窗
                            $('#editorSysUser').modal('hide');
                        }else{
                            layer.msg(data.msg);
                        }
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
        $('#editorFamerForm').bootstrapValidator({
            container: 'tooltip',
            //不能编辑 隐藏 不可见的不做校验
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            //live: 'submitted',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                gfName: {
                    message: '农户名称未通过',
                    validators: {
                        notEmpty: {
                            message: '农户名称不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '农户名称长度在1-30之间'
                        }
                    }
                },
                gfPrice: {
                    message: '农户价格未通过',
                    validators: {
                        notEmpty: {
                            message: '农户名称不能为空'
                        },
                        regexp:{
                            regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                            message:'利润率只允许在10位整数和2位小数范围内'
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
            gfName: $("#search_Famer_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#FamerTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryFamerLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeFamer(gtId);
        }, function(){
        });
    }
    /**
     * 删除农户
     **/
    function removeFamer(gfId){
        $.ajax({
            type:"post",
            url:'/goods/removeFamer',
            dataType: "json",
            data:{gfId:gfId},
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

    function editorFamer(gfId){
        editorFamerType=1;
        initFormData(gfId);
        $("#editorSysUserTitle").text("编辑农户");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $('#editorFamerForm #gfId').val('');
        $('#editorFamerForm #version').val('');
        $('#editorFamerForm #gfName').val('');
        $('#editorFamerForm #gfPrice').val('');
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            if(rowData.gfId==0){
                $('#cityrow').hide();
                $('#freeAreadiv').show();
                $('#textArea').html(rowData.gfFreeArea);
            }else{
                $('#cityrow').show();
                $('#freeAreadiv').hide();
            }
            $("#editorFamerForm #gfName").val(rowData.gfName);
            $("#editorFamerForm #gfPrice").val(parseFloat(rowData.gfPrice/100).toFixed(2) );
            $("#editorFamerForm #gfId").val(rowData.gfId);
            $("#editorFamerForm #version").val(rowData.version);

        }else{
            clearFormData();
        }
        loadcity();
    }
    /**
     * 添加农户
     **/
    function addFamer(){
        editorFamerType=0;
        initFormData(-1);
        $("#editorSysUserTitle").text("添加农户");
        $('#editorSysUser').modal("show");
    }
</script>

<!--地区与运费关联关系-->
<script type="text/javascript">

    function loadcity() {
        $.ajax({
            type:"post",
            url:'/city/queryCityLists',
            dataType: "json",
            data:{},
            success:function(data){
                var html = '';
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    $('#cityrow').html(html);
                    for(var i=0; i<rows.length; i++){
                        html = '<div class="col-lg-2">'+
                            '<div class="input-group">'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="citycheckbox'+i+'" class="styled" type="checkbox" value="'+rows[i].cityid+'">'+
                            '<label for="citycheckbox'+i+'">'+
                            rows[i].cityname+
                            '</label>'+
                            '</div>'+
                            '</div>'+
                            '</div>';
                        $('#cityrow').append(html);
                    }
                    loadcgfrel();
                }
            }
        });
    }

    function loadcgfrel() {
        var gfId = $('#gfId').val();
        if(gfId == ''){
            return;
        }
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsCgfrelLists",
            dataType: "json",
            data: {gfId: gfId,pageNo:1,pageSize:50},
            success: function (data) {
                var rows = data.rows;
                if(rows!=null && rows.length>0) {
                    var checkboxs = $('#cityrow').find('input');
                    for (var i = 0; i < rows.length; i++) {
                        for(var j=0; j<checkboxs.length;j++){
                            if(rows[i].cid == checkboxs[j].value){
                                $(checkboxs[j]).attr('checked','checked');
                                break;
                            }
                        }

                    }
                }
            }
        });
    }

    function savecgfrel() {
        var gfId = $('#gfId').val();
        if(gfId==''){
            return;
        }
        var checkbox = $('#cityrow').find('input:checked');
        var cids = '';
        if(checkbox!=null && checkbox.length>0) {
            for (var i = 0; i < checkbox.length; i++) {
                cids += ','+ checkbox[i].value;
            }
        }
        cids = cids.substr(1);
        if(cids!=''){
            $.ajax({
                type: "post",
                url: "/goods/addGoodsCgfrel",
                dataType: "json",
                data: {cids: cids,gfId:gfId},
                success: function (data) {

                }
            });
        }
    }
</script>

</body>
</html>
