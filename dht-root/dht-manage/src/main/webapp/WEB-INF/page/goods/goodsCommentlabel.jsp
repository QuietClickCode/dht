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
                                状态:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="gclStatus" name="gclStatus" type="checkbox" />
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
                    if(data.status==0){
                        //显示提示
                        layer.msg(data.msg);
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editorSysUser').modal('hide');
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
        $("#editorGoodsCommentlabelForm #gclStatus").bootstrapSwitch("state",true);
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsCommentlabelForm #gclId").val(rowData.gclId);
            $("#editorGoodsCommentlabelForm #gclName").val(rowData.gclName);
            $("#editorGoodsCommentlabelForm #version").val(rowData.version);
            var gclStatus = rowData.gclStatus;

            if(gclStatus==1){
                $('#gclStatus').bootstrapSwitch("state",true);
            }else{
                $('#gclStatus').bootstrapSwitch("state",false);
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
    /**
     * 重新加载树型结构
     **/


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


    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "orgNodeContent" || $(event.target).parents("#orgNodeContent").length>0)) {
            hideOrgTree();
        }
    }
    
    function deleteGoodsCommentlabelList() {
        var objs = $('#GoodsCommentlabelTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removeGoodsCommentlabel(objs[i].glId);
                }
            }, function(){
            });
        }else{
            layer.msg("请选择需要删除的品牌！");
        }
    }


</script>
</body>
</html>
