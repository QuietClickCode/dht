<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品运费管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="/goods/addGoodsFreight">
        <button class="btn btn-primary" type="button" onclick="addGoodsFreight()" style="margin-bottom: 5px">添加商品运费</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_GoodsFreight_name" placeholder="请输入商品运费名称">
    </div>

    <ex:perm url="/goods/queryGoodsFreightLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsFreightTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsFreightForm">
                    <input type="hidden" name="gfId" id="gfId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品运费名称:
                              </span>
                                <input type="text" class="form-control" name="gfName" id="gfName">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品运费价格:
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
<!--商品运费-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsFreightType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gfName',
            title: '商品运费名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gfPrice',
            title: '商品运费价格',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.gfId,row);
                let html='';
                <ex:perm url="goods/editGoodsFreight">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsFreight(\''+row.gfId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsFreight">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gfId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsFreightLists","GoodsFreightTables","gfId",treeColumns,queryParams)
        //初始华开关选择器

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorGoodsFreightForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsFreightForm').data('bootstrapValidator').validate();
            if(!$('#editorGoodsFreightForm').data('bootstrapValidator').isValid()){
                return;
            }

            var formData=$("#editorGoodsFreightForm").serializeObject();

            let url="/goods/addGoodsFreight";
            if(editorGoodsFreightType==1){
                url="/goods/editGoodsFreight";
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(url == '/goods/addGoodsFreight'){
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
        $('#editorGoodsFreightForm').bootstrapValidator({
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
                    message: '商品运费名称未通过',
                    validators: {
                        notEmpty: {
                            message: '商品运费名称不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '商品运费名称长度在1-30之间'
                        }
                    }
                },
                gfPrice: {
                    message: '商品运费价格未通过',
                    validators: {
                        notEmpty: {
                            message: '商品运费名称不能为空'
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
            gfName: $("#search_GoodsFreight_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsFreightTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsFreightLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsFreight(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品运费
     **/
    function removeGoodsFreight(gfId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsFreight',
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

    function editorGoodsFreight(gfId){
        editorGoodsFreightType=1;
        initFormData(gfId);
        $("#editorSysUserTitle").text("编辑商品运费");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $('#editorGoodsFreightForm #gfId').val('');
        $('#editorGoodsFreightForm #version').val('');
        $('#editorGoodsFreightForm #gfName').val('');
        $('#editorGoodsFreightForm #gfPrice').val('');
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsFreightForm #gfName").val(rowData.gfName);
            $("#editorGoodsFreightForm #gfPrice").val(rowData.gfPrice);
            $("#editorGoodsFreightForm #gfId").val(rowData.gfId);
            $("#editorGoodsFreightForm #version").val(rowData.version);

        }else{
            clearFormData();
        }
        loadcity();
    }
    /**
     * 添加商品运费
     **/
    function addGoodsFreight(){
        editorGoodsFreightType=0;
        initFormData(-1);
        $("#editorSysUserTitle").text("添加商品运费");
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
