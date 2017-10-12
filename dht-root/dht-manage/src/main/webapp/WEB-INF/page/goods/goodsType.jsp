<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsType">
        <button class="btn btn-primary" type="button" onclick="addGoodsType()" style="margin-bottom: 5px">添加商品大类</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_goodsType_name" placeholder="请输入商品大类名称">
    </div>

    <ex:perm url="goods/queryGoodsTypeLists">
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
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品大类名称:
                              </span>
                                <input type="text" class="form-control" name="gtName" id="gtName">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                              <span class="input-group-addon">
                                是否关联参数:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isParams" name="isParams" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
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
                        <br>
                        <div class="col-lg-12">
                            <div class="input-group">
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
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
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
            field: 'isParams',
            title: '是否关联参数',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                if(value==0){
                    return "无";
                }else if(value==1){
                    let html='';
                    <ex:perm url="goods/editGoodsType">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsType(\''+row.uid+'\')"">编辑</button>&nbsp;';
                    </ex:perm>
                    return html;
                }
            }
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
                    <ex:perm url="goods/editGoodsType">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsType(\''+row.uid+'\')"">编辑</button>&nbsp;';
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
                    <ex:perm url="goods/editGoodsType">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsType(\''+row.uid+'\')"">编辑</button>&nbsp;';
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
            //判断校验是否通过
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
        $('#editorGoodsTypeForm')
            .bootstrapValidator({
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
        $("#editorGoodsTypeForm #uid").val("");
        $("#editorGoodsTypeForm #version").val("");
        $("#editorGoodsTypeForm #uaccount").val("");
        $("#editorGoodsTypeForm #uname").val("");
        $("#editorGoodsTypeForm #orgIds").val("");
        $("#editorGoodsTypeForm #isValid").val("");
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
            $("#editorGoodsTypeForm #isParams").bootstrapSwitch("state",true);
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
</body>
</html>
