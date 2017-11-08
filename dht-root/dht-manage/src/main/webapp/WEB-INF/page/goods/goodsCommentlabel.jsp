<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品评论管理</title>
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
    <ex:perm url="goods/addGoodsCommentlabel">
        <button class="btn btn-primary" type="button" onclick="addGoodsCommentlabel()">添加商品评论</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_GoodsCommentlabel_name" placeholder="请输入商品评论名称">
    </div>

    <ex:perm url="goods/queryGoodsCommentlabelLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()" style="margin-top: 5px">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsCommentlabelTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsCommentlabelForm">
                    <input type="hidden" name="gclId" id="gclId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品评论名称:
                              </span>
                                <input type="text" class="form-control" name="gclName" id="gclName">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                一星:
                              </span>
                                <input type="hidden" class="form-control" name="startid">
                                <input type="text" onfocus="onfocousstart(this);" onblur="onblurstart(this)" class="form-control" name="start1name" id="start1name">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                二星:
                              </span>
                                <input type="hidden" class="form-control" name="startid">
                                <input type="text" onfocus="onfocousstart(this);" onblur="onblurstart(this)" class="form-control" name="start2name" id="start2name">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                三星:
                              </span>
                                <input type="hidden" class="form-control" name="startid">
                                <input type="text" onfocus="onfocousstart(this);" onblur="onblurstart(this)" class="form-control" name="start3name" id="start3name">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                四星:
                              </span>
                                <input type="hidden" class="form-control" name="startid">
                                <input type="text" onfocus="onfocousstart(this);" onblur="onblurstart(this)" class="form-control" name="start4name" id="start4name">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                五星:
                              </span>
                                <input type="hidden" class="form-control" name="startid">
                                <input type="text" onfocus="onfocousstart(this);" onblur="onblurstart(this)" class="form-control" name="start5name" id="start5name">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                状态:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="gclStatus" name="gclStatus" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否与子类关联:
                              </span>
                                <input type="hidden" name="isClass" id="isClass">
                                <div class="radio radio-info" style="float: left;margin-right: 20px">
                                    <input id="radio101" type="radio" name="isClasss" value="0" onclick="isclassfunction(this)">
                                    <label for="radio101">
                                        不是
                                    </label>
                                </div>
                                <div class="radio radio-info" style="float: left;margin-top: 10px">
                                    <input id="radio102" type="radio" name="isClasss" value="1" onclick="isclassfunction(this)">
                                    <label for="radio102">
                                        是
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4" id="goodschoose">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                选择商品:
                              </span>
                                <button class="btn btn-default" onclick="showggclrel();">点击选择商品</button>
                            </div>
                        </div>
                        <div class="col-lg-4" id="classchoose">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                选择类型:
                              </span>
                                <button class="btn btn-default" onclick="showgcgcllrel();">点击选择类型</button>
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

<div class="modal fade" id="editorggclrel" tabindex="-1" role="dialog" aria-labelledby="editorggclrel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorggclTitle">编辑商品与评论关系</h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input id="gnamecopy" onclick="" type="text" class="form-control"  placeholder="请输入商品名称">
                    </div>
                    <ex:perm url="/goods/queryGoodsLists">
                        <button class="btn btn-default" type="button" onclick="searchggclgoods()">查询</button>
                    </ex:perm>
                </center>

                <ex:perm url="/goods/addGoodsGgclrel">
                    <button class="btn btn-default" type="button" onclick="addggclrel()" id="addggclrelbtn">新增</button>
                </ex:perm>
                <ex:perm url="/goods/removeGoodsGgclrel">
                    <button class="btn btn-default" type="button" onclick="deleteggclrel()" id="deleteggclrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/selectGoodsGgclrelLists">
                    <button class="btn btn-primary" type="button" onclick="initggclrel()" style="float: right;margin-bottom: 10px">刷新</button>
                </ex:perm>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topggclname">
                                    商品名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="ggclTbody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editggclSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editorgcgcllrel" tabindex="-1" role="dialog" aria-labelledby="editorgcgcllrel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgcgclTitle">编辑商品与评论关系</h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input id="gclassNum" onclick="showMenu();" type="text" class="form-control"  placeholder="点击选择商品子类">
                    </div>
                </center>

                <ex:perm url="/goods/removeGoodsGgcrel">
                    <button class="btn btn-default" type="button" onclick="deletegcgcllrel()" id="deletegtgclrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/queryGoodsGgcrelLists">
                    <button class="btn btn-primary" type="button" onclick="initgcgcllrel()" style="float: right;margin-bottom: 10px">刷新</button>
                </ex:perm>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgcgclname">
                                    子类名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="gcgclTbody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editgcgclSubmit">确认</button>
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
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsCommentlabelType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gclName',
            title: '商品评论名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'start1name',
            title: '一星',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'start2name',
            title: '二星',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'start3name',
            title: '三星',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'start4name',
            title: '四星',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'start5name',
            title: '五星',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gclStatus',
            title: '状态',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(row.gclStatus==1){
                    html = '启用';
                }else{
                    html = '停用';
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
                rowDatas.set(row.gclId,row);
                let html='';
                <ex:perm url="goods/editGoodsCommentlabel">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsCommentlabel(\''+row.gclId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsCommentlabel">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gclId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsCommentlabelLists","GoodsCommentlabelTables","gclId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorGoodsCommentlabelForm #gclStatus").bootstrapSwitch();

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();

            clearFormValidation("editorGoodsCommentlabelForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsCommentlabelForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsCommentlabelForm').data('bootstrapValidator').isValid()){
                return;
            }

            var formData=$("#editorGoodsCommentlabelForm").serializeObject();

            var flag = false;
            flag = $("#editorGoodsCommentlabelForm #gclStatus").bootstrapSwitch("state");
            if(flag){
                formData["gclStatus"]=1;
            }else{
                formData["gclStatus"]=0;
            }
            if(document.getElementById("radio101").checked){
                formData["isClass"] = 0;
            }else{
                formData["isClass"] = 1;
            }
            let url="/goods/addGoodsCommentlabel";
            if(editorGoodsCommentlabelType==1){
                url="/goods/editGoodsCommentlabel";
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0 || data.row!=null){
                        //显示提示

                        if(url == '/goods/addGoodsCommentlabel'){
                            var row = data.row;
                            addstart(row.gclId);
                        }else{
                            updatestart();
                        }

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
        $('#editorGoodsCommentlabelForm')
            .bootstrapValidator({
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
                    gclName: {
                        message: '商品评论名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品评论名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品评论名称长度在1-30之间'
                            }
                        }
                    },
                    start1name: {
                        message: '不能为空',
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '长度在1-30之间'
                            }
                        }
                    },
                    start2name: {
                        message: '不能为空',
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '长度在1-30之间'
                            }
                        }
                    },
                    start3name: {
                        message: '不能为空',
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '长度在1-30之间'
                            }
                        }
                    },
                    start4name: {
                        message: '不能为空',
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '长度在1-30之间'
                            }
                        }
                    },
                    start5name: {
                        message: '不能为空',
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '长度在1-30之间'
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
            gclName: $("#search_GoodsCommentlabel_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsCommentlabelTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsCommentlabelLists"
            }
        );
    }
    //删除确认框
    function deleteData(gclId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsCommentlabel(gclId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsCommentlabel(gclId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsCommentlabel',
            dataType: "json",
            data:{gclId:gclId},
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
    function editorGoodsCommentlabel(orgId){
        editorGoodsCommentlabelType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品评论");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsCommentlabelForm #gclName").val("");
        $("#editorGoodsCommentlabelForm #gclId").val("");
        $("#editorGoodsCommentlabelForm #version").val("");
        $("#editorGoodsCommentlabelForm").find('input').val("");
        $("#editorGoodsCommentlabelForm #gclStatus").bootstrapSwitch("state",true);
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        updatestartArr = [];
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsCommentlabelForm #gclId").val(rowData.gclId);
            $("#editorGoodsCommentlabelForm #gclName").val(rowData.gclName);
            $("#editorGoodsCommentlabelForm #version").val(rowData.version);
            $("#editorGoodsCommentlabelForm #start1name").val(rowData.start1name);
            $("#editorGoodsCommentlabelForm #start1name").prev().val(rowData.start1id);
            $("#editorGoodsCommentlabelForm #start2name").val(rowData.start2name);
            $("#editorGoodsCommentlabelForm #start2name").prev().val(rowData.start2id);
            $("#editorGoodsCommentlabelForm #start3name").val(rowData.start3name);
            $("#editorGoodsCommentlabelForm #start3name").prev().val(rowData.start3id);
            $("#editorGoodsCommentlabelForm #start4name").val(rowData.start4name);
            $("#editorGoodsCommentlabelForm #start4name").prev().val(rowData.start4id);
            $("#editorGoodsCommentlabelForm #start5name").val(rowData.start5name);
            $("#editorGoodsCommentlabelForm #start5name").prev().val(rowData.start5id);

            var gclStatus = rowData.gclStatus;

            if(gclStatus==1){
                $('#gclStatus').bootstrapSwitch("state",true);
            }else{
                $('#gclStatus').bootstrapSwitch("state",false);
            }

            var isClass = rowData.isClass;
            if(isClass==0){
                document.getElementById("radio101").checked=true;
                $('#goodschoose').show();
                $('#classchoose').hide();
            }else{
                document.getElementById("radio102").checked=true;
                $('#goodschoose').hide();
                $('#classchoose').show();
            }

        }else{
            clearFormData();
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsCommentlabel(){
        editorGoodsCommentlabelType=0;
        initFormData();

        $("#editorSysUserTitle").text("添加商品评论");
        $('#editorSysUser').modal("show")
    }

    var updatestartArr = new Array();
    var changvalue;
    function updatestart() {
        if(updatestartArr.length>0){
            for(var i=0;i<updatestartArr.length;i++){
                var id = $(updatestartArr[i]).prev().val();
                var name = $(updatestartArr[i]).val();
                $.ajax({
                    type:"post",
                    url:'/goods/editGoodsCommentlabelStart',
                    dataType: "json",
                    async :false,
                    data:{gclId:id,gclName:name},
                    success:function(data){
                        if(i==updatestartArr.length-1){
                            layer.msg('操作成功');
                            //刷新数据
                            refreshTableData();
                            //关闭弹窗
                            $('#editorSysUser').modal('hide');
                        }
                    }
                });
            }
        }else{
            refreshTableData();
            //关闭弹窗
            $('#editorSysUser').modal('hide');
        }
    }
    function addstart(gclId) {
        var startids = $('#editorGoodsCommentlabelForm').find('input[name=startid]');
        for(var i=0;i<startids.length;i++){
            var name = $(startids[i]).next().val();
            $.ajax({
                type:"post",
                url:'/goods/addGoodsCommentlabel',
                dataType: "json",
                async :false,
                data:{gclName:name,gclParenid:gclId,gclStatus:1,gclLevel:i+1},
                success:function(data){
                    if(i==updatestartArr.length-1){
                        layer.msg('操作成功');
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editorSysUser').modal('hide');
                    }
                }
            });
        }
    }

    function onfocousstart(obj) {
        changvalue = $(obj).val();
    }
    function onblurstart(obj) {
        if($(obj).val()!=changvalue){
            updatestartArr.push(obj);
        }
    }
</script>
<!--商品与评论的关系-->
<script>
    var initgcomplimentaryreldataArr = new Array();

    $(function () {
        $('#editggclSubmit').click(function () {
            $('#editorggclrel').modal('hide');
        });
    });

    /**
     * 清除form 表单数据
     * */
    function initggclrel(){
        var gclId = $('#gclId').val();
        $('#gnamecopy').val('');
        initgcomplimentaryreldataArr = [];
        $('#addggclrelbtn').hide();
        $('#deleteggclrelbtn').show();
        $('#topggclname').html('商品名称（已有）');
        $.ajax({
            type:"post",
            url:"/goods/selectGoodsGgclrelLists",
            dataType: "json",
            data:{gclId:gclId,isClass:0,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="ggclcheckbox" class="styled" type="checkbox" value="'+rows[i].ggclId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgcomplimentaryreldataArr.push(rows[i].gid);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定商品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#ggclTbody').html(html);
            }
        });
    }

    function searchggclgoods() {
        var gnamecopy = $('#gnamecopy').val();
        if(gnamecopy==''){
            layer.msg('请输入商品名称');
            return;
        }
        $('#addggclrelbtn').show();
        $('#deleteggclrelbtn').hide();
        $('#topggclname').html('商品名称');
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsLists",
            dataType: "json",
            data: {gname: gnamecopy,pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgcomplimentaryreldataArr.length>0){
                            for (var j=0; j<initgcomplimentaryreldataArr.length; j++){
                                if (rows[i].gid==initgcomplimentaryreldataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="ggclcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gid+'">'+
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
                $('#ggclTbody').html(html);
            }
        });
    }

    function addggclrel() {
        var checkboxs = $('input:checkbox[name="ggclcheckbox"]:checked');
        var addggb = "";
        var gclId = $('#gclId').val();
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggb += ","+checkboxs[i].value;
                }
            }
            addggb=addggb.substr(1);
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGgclrelByGids",
                dataType: "json",
                data: {gids: addggb,gclId:gclId},
                success: function (data) {
                    layer.msg('新增成功!');
                    initggclrel();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
        }

    }

    function deleteggclrel() {
        var checkboxs = $('input:checkbox[name="ggclcheckbox"]:checked');
        if(checkboxs.length > 0){
            var gglIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    gglIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGgclrel",
                dataType: "json",
                data: {ggclIds: gglIds},
                success: function (data) {
                    layer.msg('删除成功!');
                    initggclrel();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
        }

    }

</script>

<!--子类与评论关系-->
<script>

    $(function () {
        $('#editgcgclSubmit').click(function () {
            cleargcgcldata();
        });
    });

    function cleargcgcldata() {
        var gcId = $('#gcId').val();

        $.ajax({
            type:"post",
            url:"/goods/clearGoodsGgcrel",
            dataType: "json",
            data:{gcId:gcId},
            success:function(data){
                addgcgcllrel();
            }
        });
    }

    function addgcgcllrel() {
        var gclId = $('#gclId').val();
        var gcgclcheckboxs = $('#gcgclTbody').find('input[type=checkbox]');
        if(gcgclcheckboxs!=null && gcgclcheckboxs.length>0){
            var gclassIds = '';
            for(var i=0; i<gcgclcheckboxs.length; i++){
                gclassIds += ','+gcgclcheckboxs[i].value;
            }
            gclassIds = gclassIds.substr(1);
            $.ajax({
                type:"post",
                url:"/goods/addGoodsGgcrelByGclass",
                dataType: "json",
                data:{gclId:gclId,gclassIds:gclassIds},
                success:function(data){
                    if(data.status==0){
                        layer.msg("操作成功");
                        $('#editorgcgcllrel').modal('hide');
                    }else{
                        layer.msg("系统错误");
                    }

                }
            });
        }else{
            $('#editorgcgcllrel').modal('hide');
        }
    }

    function deletegcgcllrel() {
        var gcgclcheckboxs = $('#gcgclTbody').find('input[type=checkbox]:checked');
        if(gcgclcheckboxs!=null&&gcgclcheckboxs.length>0){
            for(var i=0;i<gcgclcheckboxs.length;i++){
                $(gcgclcheckboxs[i]).parent().parent().parent().remove();
            }
        }else{
            layer.msg("请选择想操作的数据");
        }
    }

    function initgcgcllrel() {
        var gclId = $('#gclId').val();
        $('#gclassNum').val('');
        $.ajax({
            type:"post",
            url:"/goods/selectGoodsGgclrelLists",
            dataType: "json",
            data:{gclId:gclId,isClass:1,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0;i<rows.length;i++){
                        html += '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="gcgclcheckbox'+i+'"  type="checkbox" name="gcgclcheckbox" onclick="" value="'+rows[i].gclassId+'">'+
                            '<label for="gcgclcheckbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            rows[i].gclassName+
                            '</td>'+
                            '</tr>';
                    }
                }
                $('#gcgclTbody').html(html);

                var gcgclcheckboxs = $('#gcgclTbody').find('input[type=checkbox]');
                if(gcgclcheckboxs!=null && gcgclcheckboxs.length>0){
                    var ggIds = '';
                    for(var i=0; i<gcgclcheckboxs.length; i++){
                        ggIds += ','+gcgclcheckboxs[i].value;
                    }
                    ggIds = ggIds.substr(1);
                    initgcgcltree(ggIds);
                }else{
                    initgcgcltree(-1);
                }

            }
        });
    }

    <!--初始化树形结构-->
    function initgcgcltree(ggIds) {
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
        var gcgclcheckboxs = $('#gcgclTbody').find('input[type=checkbox]');
        if(gcgclcheckboxs!=null && gcgclcheckboxs.length>0){
            for(var i=0; i<gcgclcheckboxs.length; i++){
                if(vId==gcgclcheckboxs[i].value){
                    $(gcgclcheckboxs[i]).parent().parent().parent().remove();
                    console.log(gcgclcheckboxs[i]);
                    flag++;
                }
            }
        }

        if(flag == 0){
            var html = '<tr>'+
                '<td>'+
                '<div class="checkbox checkbox-info">'+
                '<input id="gcgclcheckbox'+vId+'"  type="checkbox" name="gcgclcheckbox" onclick="" value="'+vId+'">'+
                '<label for="gcgclcheckbox'+vId+'">'+
                '</label>'+
                '</div>'+
                '</td>'+
                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                v+
                '</td>'+
                '</tr>';
            $('#gcgclTbody').prepend(html);
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

    function showggclrel() {
        initggclrel();
        $('#editorggclrel').modal('show');
    }
    function showgcgcllrel() {
        initgcgcllrel();
        $('#editorgcgcllrel').modal('show');
    }

    function isclassfunction(obj) {
        var val = $(obj).val();
        if(val==0){
            $('#goodschoose').show();
            $('#classchoose').hide();
            $('#isClass').val('0');
        }else{
            $('#goodschoose').hide();
            $('#classchoose').show();
            $('#isClass').val('1');
        }
    }
</script>
</body>
</html>
