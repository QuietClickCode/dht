<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">

</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="/goods/addGoodsType">
        <button class="btn btn-primary" type="button" onclick="addGoodsType()" style="margin-bottom: 5px">添加商品大类</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_goodsType_name" placeholder="请输入商品大类名称">
    </div>

    <ex:perm url="/goods/queryGoodsTypeLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsTypeForm">
                    <input type="hidden" name="gtId" id="gtId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品大类名称:
                              </span>
                                <input type="text" class="form-control" name="gtName" id="gtName">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否关联品牌:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isTrademark" name="isTrademark" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否关联规格:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isSpecification" name="isSpecification" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否关联评论:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isParams" name="isParams" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否显示:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isShow" name="isShow" type="checkbox" />
                                    </div>
                                </div>
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
<div class="modal fade" id="editorGtGb" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgtgbTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;">
                        <input type="text" class="form-control" id="search_GoodsBrand_name" placeholder="请输入品牌名称">
                    </div>
                    <ex:perm url="/goods/queryGoodsBrandLists">
                        <button class="btn btn-default" type="button" onclick="searchbrands()">查询</button>
                    </ex:perm>

                </center>
                <ex:perm url="/goods/addGoodsGtgbrel">
                    <button class="btn btn-default" type="button" onclick="addgtgbrel()" id="addgtgbrelbtn">新增</button>
                </ex:perm>
                <ex:perm url="/goods/removeGoodsGtgbrel">
                    <button class="btn btn-default" type="button" onclick="deletegtgbrel()" id="deletegtgbrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/queryGoodsGtgbrelLists">
                    <button class="btn btn-primary" type="button" onclick="refreshmyTbody()" style="float: right">刷新</button>
                </ex:perm>
                    <div class="row clearfix" style="margin-top: 5px">
                        <div class="col-md-12 column">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th style="width: 30px">
                                        &nbsp;
                                    </th>
                                    <th style="text-align: center;" id="topbrandname">
                                        品牌名称（已有）
                                    </th>
                                </tr>
                                </thead>
                                <tbody id="myTbody">
                                <tr>
                                    <td>
                                        <div class="checkbox checkbox-info">
                                            <input id="checkbox1" class="styled" type="checkbox" value="">
                                            <label for="checkbox1">
                                            </label>
                                        </div>
                                    </td>
                                    <td style="text-align: center;display:table-cell; vertical-align:bottom;">
                                        <span style="line-height: 100%">TB - Monthly</span>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editGtGbSubmit">确认</button>
            </div>
        </div>

        </div>
    </div>
<div class="modal fade" id="editorGtGs" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgtgsTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;">
                        <input type="text" class="form-control" id="search_GoodsSpecification_name" placeholder="请输入规格名称">
                    </div>
                    <ex:perm url="/goods/queryGoodsBrandLists">
                        <button class="btn btn-default" type="button" onclick="searchspecifications()">查询</button>
                    </ex:perm>

                </center>
                <ex:perm url="/goods/addGoodsGtgsrel">
                    <button class="btn btn-default" type="button" onclick="addgtgsrel()" id="addgtgsrelbtn">新增</button>
                </ex:perm>
                <ex:perm url="/goods/removeGoodsGtgsrel">
                    <button class="btn btn-default" type="button" onclick="deletegtgsrel()" id="deletegtgsrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/queryGoodsGtgsrelLists">
                    <button class="btn btn-primary" type="button" onclick="refreshmygtgsTbody()" style="float: right">刷新</button>
                </ex:perm>
                <div class="row clearfix" style="margin-top: 5px">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px">
                                    &nbsp;
                                </th>
                                <th style="text-align: center;" id="topspecificationname">
                                    品牌名称（已有）
                                </th>
                            </tr>
                            </thead>
                            <tbody id="mygtgsTbody">
                            <tr>
                                <td>
                                    <div class="checkbox checkbox-info">
                                        <input id="checkbox10" class="styled" type="checkbox" value="">
                                        <label for="checkbox10">
                                        </label>
                                    </div>
                                </td>
                                <td style="text-align: center;display:table-cell; vertical-align:bottom;">
                                    <span style="line-height: 100%">TB - Monthly</span>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editGtGsSubmit">确认</button>
            </div>
        </div>

    </div>
</div>
<div class="modal fade" id="editorGtGcl" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgtgclTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;">
                        <input type="text" class="form-control" id="search_GoodsCommentlabel_name" placeholder="请输入评论标签名称">
                    </div>
                    <ex:perm url="/goods/queryGoodsCommentlabelLists">
                        <button class="btn btn-default" type="button" onclick="searchCommentlabels()">查询</button>
                    </ex:perm>

                </center>
                <ex:perm url="/goods/addGoodsGtgsrel">
                    <button class="btn btn-default" type="button" onclick="addgtgclrel()" id="addgtgclrelbtn">新增</button>
                </ex:perm>
                <ex:perm url="/goods/removeGoodsGtgsrel">
                    <button class="btn btn-default" type="button" onclick="deletegtgclrel()" id="deletegtgclrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/queryGoodsGtgsrelLists">
                    <button class="btn btn-primary" type="button" onclick="refreshmygtgclTbody()" style="float: right">刷新</button>
                </ex:perm>
                <div class="row clearfix" style="margin-top: 5px">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px">
                                    &nbsp;
                                </th>
                                <th style="text-align: center;" id="topcommentlabelname" colspan="6">
                                    商品评论名称（已有）
                                </th>
                            </tr>
                            <tr>
                                <th style="width: 30px">
                                    &nbsp;
                                </th>
                                <th style="text-align: center;" >
                                    评论分类名称
                                </th>
                                <th style="text-align: center;" >
                                    一星
                                </th>
                                <th style="text-align: center;" >
                                    二星
                                </th>
                                <th style="text-align: center;" >
                                    三星
                                </th>
                                <th style="text-align: center;" >
                                    四星
                                </th>
                                <th style="text-align: center;" >
                                    五星
                                </th>
                            </tr>
                            </thead>
                            <tbody id="mygtgclTbody">
                            <tr>
                                <td>
                                    <div class="checkbox checkbox-info">
                                        <input id="checkbox101" class="styled" type="checkbox" value="">
                                        <label for="checkbox101">
                                        </label>
                                    </div>
                                </td>
                                <td style="text-align: center;display:table-cell; vertical-align:bottom;">
                                    <span style="line-height: 100%">TB - Monthly</span>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editGtGclSubmit">确认</button>
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
<!--商品大类-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsTypeType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gtName',
            title: '商品大类名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'isTrademark',
            title: '是否关联品牌',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                if(value==0){
                    return "无";
                }else if(value==1){
                    let html='';
                    <ex:perm url="goods/queryGoodsGtgbrelLists">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editGtGb(\''+row.gtId+'\')"">编辑</button>&nbsp;';
                    </ex:perm>
                    return html;
                }
            }
        },
        {
            field: 'isSpecification',
            title: '是否关联规格',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                if(value==0){
                    return "无";
                }else if(value==1){
                    let html='';
                    <ex:perm url="goods/queryGoodsGtgsrelLists">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editGtGs(\''+row.gtId+'\')"">编辑</button>&nbsp;';
                    </ex:perm>
                    return html;
                }
            }
        },
        {
            field: 'isParams',
            title: '是否关联商品评论',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                if(value==0){
                    return "无";
                }else if(value==1){
                    let html='';
                    <ex:perm url="goods/queryGoodsGtgclrelLists">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editGtGcl(\''+row.gtId+'\')"">编辑</button>&nbsp;';
                    </ex:perm>
                    return html;
                }
            }
        },
        {
            field: 'isShow',
            title: '是否显示',
            align : 'center',
            valign : 'middle',
            width:120,
            formatter:function(value,row,index){
                if(value==0){
                    return "隐藏";
                }else if(value==1){
                    return "显示";
                }
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.gtId,row);
                let html='';
                <ex:perm url="goods/editGoodsType">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsType(\''+row.gtId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsType">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gtId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsTypeLists","goodsTypeTables","gtId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorGoodsTypeForm #isParams").bootstrapSwitch();
        $("#editorGoodsTypeForm #isTrademark").bootstrapSwitch();
        $("#editorGoodsTypeForm #isSpecification").bootstrapSwitch();
        $("#editorGoodsTypeForm #isShow").bootstrapSwitch();

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsTypeForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsTypeForm').data('bootstrapValidator').validate();
            if(!$('#editorGoodsTypeForm').data('bootstrapValidator').isValid()){
                return;
            }

            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsTypeForm").serializeObject();
            var flag =$("#editorGoodsTypeForm #isParams").bootstrapSwitch("state");
            if(flag){
                formData["isParams"]=1;
            }else{
                formData["isParams"]=0;
            }
            var flag =$("#editorGoodsTypeForm #isTrademark").bootstrapSwitch("state");
            if(flag){
                formData["isTrademark"]=1;
            }else{
                formData["isTrademark"]=0;
            }
            var flag =$("#editorGoodsTypeForm #isSpecification").bootstrapSwitch("state");
            if(flag){
                formData["isSpecification"]=1;
            }else{
                formData["isSpecification"]=0;
            }

            var flag =$("#editorGoodsTypeForm #isShow").bootstrapSwitch("state");
            if(flag){
                formData["isShow"]=1;
            }else{
                formData["isShow"]=0;
            }


            let url="/goods/addGoodsType";
            if(editorGoodsTypeType==1){
                url="/goods/editGoodsType";
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
        $('#editorGoodsTypeForm').bootstrapValidator({
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
                gtName: {
                    message: '商品大类名称未通过',
                    validators: {
                        notEmpty: {
                            message: '商品大类名称不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '商品大类名称长度在1-30之间'
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
            gtName: $("#search_goodsType_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsTypeLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsType(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsType(gtId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsType',
            dataType: "json",
            data:{gtId:gtId},
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
    function editorGoodsType(orgId){
        editorGoodsTypeType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品大类");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){

    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsTypeForm #gtName").val(rowData.gtName);
            $("#editorGoodsTypeForm #gtId").val(rowData.gtId);
            $("#editorGoodsTypeForm #version").val(rowData.version);
            var flag =false;
            if(rowData.isParams==1){
                flag=true;
            }
            $("#editorGoodsTypeForm #isParams").bootstrapSwitch("state",flag);

            flag =false;
            if(rowData.isSpecification==1){
                flag=true;
            }
            $("#editorGoodsTypeForm #isSpecification").bootstrapSwitch("state",flag);

            var flag =false;
            if(rowData.isTrademark==1){
                flag=true;
            }
            $("#editorGoodsTypeForm #isTrademark").bootstrapSwitch("state",flag);

            var flag =false;
            if(rowData.isShow==1){
                flag=true;
            }
            $("#editorGoodsTypeForm #isShow").bootstrapSwitch("state",flag);


        }else{
            $("#editorGoodsTypeForm #gtName").val('');
            $("#editorGoodsTypeForm #gtId").val('');
//            $("#editorGoodsTypeForm #isParams").bootstrapSwitch("state",true);
            $("#editorGoodsTypeForm #isSpecification").bootstrapSwitch("state",true);
            $("#editorGoodsTypeForm #isTrademark").bootstrapSwitch("state",true);
            $("#editorGoodsTypeForm #isShow").bootstrapSwitch("state",true);
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsType(){
        editorGoodsTypeType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();
        $("#editorGoodsTypeForm #isShow").bootstrapSwitch("state",true);
        $("#editorGoodsTypeForm #isSpecification").bootstrapSwitch("state",true);
        $("#editorGoodsTypeForm #isTrademark").bootstrapSwitch("state",true);
        $("#editorGoodsTypeForm #isParams").bootstrapSwitch("state",true);
        $("#editorSysUserTitle").text("添加商品大类");
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
</script>
<!--大类与品牌关联关系-->
<script type="text/javascript">
    var initdataArr = new Array();
    $(function () {

        $('#editorGtGb').on('hide.bs.modal', function () {
            //清除数据
            cleargtgbFormData();
        });

        //编辑按钮提交操作
        $("#editGtGbSubmit").click("click",function(e){
            $('#editorGtGb').modal('hide');
        });
    });


    function cleargtgbFormData(){

    }

    function refreshmyTbody() {
        var gtId = $('#gtId').val();
        initgtgbFormData(gtId);
    }
    /**
     * 清除form 表单数据
     * */
    function initgtgbFormData(gtId){
        $('#topbrandname').html('品牌名称（已有）');
        initdataArr = [];
        $('#addgtgbrelbtn').hide();
        $('#deletegtgbrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGtgbrelLists",
            dataType: "json",
            data:{gtId:gtId,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                         html   +=  '<tr>'+
                                    '<td>'+
                                    '<div class="checkbox checkbox-info">'+
                                    '<input id="checkbox'+i+'" name="gtgbcheckbox" class="styled" type="checkbox" value="'+rows[i].gtgbId+'">'+
                                    '<label for="checkbox'+i+'">'+
                                    '</label>'+
                                    '</div>'+
                                    '</td>'+
                                    '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                    '<span style="line-height: 100%">'+rows[i].brandname+'</span>'+
                                    '</td>'+
                                    '</tr>';
                        initdataArr.push(rows[i].gbId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定品牌</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#myTbody').html(html);
            }
        });
    }

    function editGtGb(gtId){
        $('#gtId').val(gtId);
        initgtgbFormData(gtId);
        $("#editorgtgbTitle").text("编辑大类与品牌关系");
        $('#editorGtGb').modal("show")
    }

    function searchbrands() {
        var gbName = $('#search_GoodsBrand_name').val();
        $('#topbrandname').html('品牌名称');
        $('#addgtgbrelbtn').show();
        $('#deletegtgbrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsBrandLists",
            dataType: "json",
            data: {gbName: gbName, pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    console.log(initdataArr);
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initdataArr.length>0){
                            for (var j=0; j<initdataArr.length; j++){
                                if (rows[i].gbId==initdataArr[j]){
                                    flag = 1;
                                }
                            }
                        }
                        if(flag == 0){
                            html   +=  '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gtgbcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gbId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gbName+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                    if(html == ''){
                        html += '<tr>'+
                            '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%;color:red;">没有找到符合要求的品牌</span>'+
                            '</td>'+
                            '</tr>';
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的品牌</span>'+
                        '</td>'+
                        '</tr>';
                }
                $('#myTbody').html(html);
            }
        });
    }

    function addgtgbrel() {
        var checkboxs = $('input:checkbox[name="gtgbcheckbox"]:checked');
        var addgtgb = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addgtgb += ","+checkboxs[i].value;
                }
            }
            var gtId = $('#gtId').val();
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGtgbrel",
                dataType: "json",
                data: {gbIds: addgtgb,gtId:gtId},
                success: function (data) {
                    layer.msg('新增成功');
                    refreshmyTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

    function deletegtgbrel() {
        var checkboxs = $('input:checkbox[name="gtgbcheckbox"]:checked');
        if(checkboxs.length > 0){
            var gtgsIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    gtgsIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGtgbrel",
                dataType: "json",
                data: {gtgbIds: gtgsIds},
                success: function (data) {
                    layer.msg('删除成功');
                    refreshmyTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

</script>
<!--大类与规格关联关系-->
<script type="text/javascript">
    var initgtgclArr = new Array();
    $(function () {

        $('#').on('hide.bs.editorGtGsmodal', function () {
            //清除数据
        });

        //编辑按钮提交操作
        $("#editGtGsSubmit").click("click",function(e){
            $('#editorGtGs').modal('hide');
        });
    });




    function refreshmygtgsTbody() {
        var gtId = $('#gtId').val();
        initgtgsFormData(gtId);
    }
    /**
     * 清除form 表单数据
     * */
    function initgtgsFormData(gtId){

        $('#topspecificationname').html('规格名称（已有）');
        initgtgclArr = [];
        $('#addgtgsrelbtn').hide();
        $('#deletegtgsrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGtgsrelLists",
            dataType: "json",
            data:{gtId:gtId,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input name="gtgscheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gtgsId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gsname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgtgclArr.push(rows[i].gsId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定规格</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygtgsTbody').html(html);
            }
        });
    }

    function editGtGs(gtId){
        $('#gtId').val(gtId);
        initgtgsFormData(gtId);
        $("#editorgtgsTitle").text("编辑大类与规格关系");
        $('#editorGtGs').modal("show")
    }

    function searchspecifications() {
        var gsName = $('#search_GoodsSpecification_name').val();
        $('#topspecificationname').html('规格名称');
        $('#addgtgsrelbtn').show();
        $('#deletegtgsrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsSpecificationLists",
            dataType: "json",
            data: {gsName: gsName, pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgtgclArr.length>0){
                            for (var j=0; j<initgtgclArr.length; j++){
                                if (rows[i].gsId==initgtgclArr[j]){
                                    flag = 1;
                                }
                            }
                        }
                        if(flag == 0){
                            html   +=  '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gtgscheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gsId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gsName+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                    if(html == ''){
                        html += '<tr>'+
                            '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%;color:red;">没有找到符合要求的规格</span>'+
                            '</td>'+
                            '</tr>';
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的品牌</span>'+
                        '</td>'+
                        '</tr>';
                }
                $('#mygtgsTbody').html(html);
            }
        });
    }

    function addgtgsrel() {
        var checkboxs = $('input:checkbox[name=gtgscheckbox]:checked');

        if(checkboxs.length > 0){
            var addgtgs = '';
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addgtgs += ','+checkboxs[i].value;
                }
            }
            var gtId = $('#gtId').val();
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGtgsrel",
                dataType: "json",
                data: {gsIds: addgtgs,gtId:gtId},
                success: function (data) {
                    layer.msg('新增成功');
                    refreshmygtgsTbody();
                }
            });
        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

    function deletegtgsrel() {
        var checkboxs = $('input:checkbox[name=gtgscheckbox]:checked');
        var delgtgs = '';
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    delgtgs += checkboxs[i].value + ',';
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGtgsrel",
                dataType: "json",
                data: {gtgsIds: delgtgs},
                success: function (data) {
                    layer.msg('删除成功');
                    refreshmygtgsTbody();
                }
            });
        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

</script>
<!--大类与评论标签关联关系-->
<script type="text/javascript">
    var initgtgclArr = new Array();
    $(function () {

        $('#editorGtGcl').on('hide.bs.modal', function () {
            //清除数据
        });

        //编辑按钮提交操作
        $("#editGtGsSubmit").click("click",function(e){
            $('#editorGtGcl').modal('hide');
        });
    });




    function refreshmygtgclTbody() {
        var gtId = $('#gtId').val();
        initgtgclFormData(gtId);
    }
    /**
     * 清除form 表单数据
     * */
    function initgtgclFormData(gtId){

        $('#topcommentlabelname').html('商品评论名称（已有）');
        initgtgclArr = [];
        $('#addgtgclrelbtn').hide();
        $('#deletegtgclrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGtgclrelLists",
            dataType: "json",
            data:{gtId:gtId,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input name="gtgclcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gtgclId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gclName+'</span>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].start1name+'</span>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].start2name+'</span>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].start3name+'</span>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].start4name+'</span>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].start5name+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgtgclArr.push(rows[i].gclId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定商品评论</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygtgclTbody').html(html);
            }
        });
    }

    function editGtGcl(gtId){
        $('#gtId').val(gtId);
        initgtgclFormData(gtId);
        $("#editorgtgclTitle").text("编辑大类与评论关系");
        $('#editorGtGcl').modal("show")
    }

    function searchCommentlabels() {
        var gclName = $('#search_GoodsCommentlabel_name').val();
        $('#topcommentlabelname').html('商品评论名称');
        $('#addgtgclrelbtn').show();
        $('#deletegtgclrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsCommentlabelLists",
            dataType: "json",
            data: {gclName: gclName, pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgtgclArr.length>0){
                            for (var j=0; j<initgtgclArr.length; j++){
                                if (rows[i].gclId==initgtgclArr[j]){
                                    flag = 1;
                                }
                            }
                        }
                        if(flag == 0){
                            html   +=  '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gtgclcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gclId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gclName+'</span>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].start1name+'</span>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].start2name+'</span>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].start3name+'</span>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].start4name+'</span>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].start5name+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                    if(html == ''){
                        html += '<tr>'+
                            '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%;color:red;">没有找到符合要求的商品评论</span>'+
                            '</td>'+
                            '</tr>';
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的商品评论</span>'+
                        '</td>'+
                        '</tr>';
                }
                $('#mygtgclTbody').html(html);
            }
        });
    }

    function addgtgclrel() {
        var checkboxs = $('input:checkbox[name=gtgclcheckbox]:checked');

        if(checkboxs.length > 0){
            var addgtgcl = '';
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addgtgcl += ','+checkboxs[i].value;
                }
            }
            var gtId = $('#gtId').val();
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGtgclrel",
                dataType: "json",
                data: {gclIds: addgtgcl,gtId:gtId},
                success: function (data) {
                    layer.msg('新增成功');
                    refreshmygtgclTbody();
                }
            });
        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

    function deletegtgclrel() {
        var checkboxs = $('input:checkbox[name=gtgclcheckbox]:checked');
        var delgtgcl = '';
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    delgtgcl += checkboxs[i].value + ',';
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGtgclrel",
                dataType: "json",
                data: {gtgclIds: delgtgcl},
                success: function (data) {
                    layer.msg('删除成功');
                    refreshmygtgclTbody();
                }
            });
        }else{
            layer.msg('请选择您想操作的数据');
        }

    }

</script>
</body>
</html>
