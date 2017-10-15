<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品规格管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsSpecification">
        <button class="btn btn-primary" type="button" onclick="addGoodsSpecification()" style="margin-bottom: 5px">添加商品规格</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_GoodsSpecification_name" placeholder="请输入商品规格名称">
    </div>

    <ex:perm url="goods/queryGoodsSpecificationLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsSpecificationTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsSpecificationForm">
                    <input type="hidden" name="gtId" id="gtId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品规格名称:
                              </span>
                                <input type="text" class="form-control" name="gsName" id="gsName">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                              <span class="input-group-addon">
                                添加商品规格值:
                              </span>
                                <button class="btn btn-default">+</button>
                            </div>
                        </div>
                    </div>
                    <br>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group">
                              <input class="form-control" type="text" placeholder="请输入规格值">
                            </div>
                        </div>
                        <br>

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
    var editorGoodsSpecificationType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gsName',
            title: '商品规格名称',
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
                rowDatas.set(row.gsId,row);
                let html='';
                <ex:perm url="goods/editGoodsSpecification">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsSpecification(\''+row.gsId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsSpecification">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gsId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsSpecificationLists","GoodsSpecificationTables","gtId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorGoodsSpecificationForm #isParams").bootstrapSwitch();
        $("#editorGoodsSpecificationForm #isTrademark").bootstrapSwitch();
        $("#editorGoodsSpecificationForm #isSpecification").bootstrapSwitch();
        $("#editorGoodsSpecificationForm #isShow").bootstrapSwitch();

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsSpecificationForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsSpecificationForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsSpecificationForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsSpecificationForm").serializeObject();
            var flag =$("#editorGoodsSpecificationForm #isParams").bootstrapSwitch("state");
            if(flag){
                formData["isParams"]=1;
            }else{
                formData["isParams"]=0;
            }
            var flag =$("#editorGoodsSpecificationForm #isTrademark").bootstrapSwitch("state");
            if(flag){
                formData["isTrademark"]=1;
            }else{
                formData["isTrademark"]=0;
            }
            var flag =$("#editorGoodsSpecificationForm #isSpecification").bootstrapSwitch("state");
            if(flag){
                formData["isSpecification"]=1;
            }else{
                formData["isSpecification"]=0;
            }
            var flag =$("#editorGoodsSpecificationForm #isShow").bootstrapSwitch("state");
            if(flag){
                formData["isShow"]=1;
            }else{
                formData["isShow"]=0;
            }


            let url="/goods/addGoodsSpecification";
            if(editorGoodsSpecificationType==1){
                url="/goods/editGoodsSpecification";
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
        $('#editorGoodsSpecificationForm')
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
                        message: '商品规格名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品规格名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品规格名称长度在1-30之间'
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
            gtName: $("#search_GoodsSpecification_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsSpecificationTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsSpecificationLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsSpecification(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品规格
     **/
    function removeGoodsSpecification(gtId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsSpecification',
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
    function editorGoodsSpecification(orgId){
        editorGoodsSpecificationType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品规格");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsSpecificationForm #uid").val("");
        $("#editorGoodsSpecificationForm #version").val("");
        $("#editorGoodsSpecificationForm #uaccount").val("");
        $("#editorGoodsSpecificationForm #uname").val("");
        $("#editorGoodsSpecificationForm #orgIds").val("");
        $("#editorGoodsSpecificationForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsSpecificationForm #gtName").val(rowData.gtName);
            $("#editorGoodsSpecificationForm #gtId").val(rowData.gtId);
            $("#editorGoodsSpecificationForm #version").val(rowData.version);
            var flag =false;
            if(rowData.isParams==1){
                flag=true;
            }
            $("#editorGoodsSpecificationForm #isParams").bootstrapSwitch("state",flag);

            flag =false;
            if(rowData.isSpecification==1){
                flag=true;
            }
            $("#editorGoodsSpecificationForm #isSpecification").bootstrapSwitch("state",flag);

            var flag =false;
            if(rowData.isTrademark==1){
                flag=true;
            }
            $("#editorGoodsSpecificationForm #isTrademark").bootstrapSwitch("state",flag);

            var flag =false;
            if(rowData.isShow==1){
                flag=true;
            }
            $("#editorGoodsSpecificationForm #isShow").bootstrapSwitch("state",flag);

        }else{
            $("#editorGoodsSpecificationForm #gtName").val('');
            $("#editorGoodsSpecificationForm #gtId").val('');
            $("#editorGoodsSpecificationForm #isParams").bootstrapSwitch("state",true);
            $("#editorGoodsSpecificationForm #isSpecification").bootstrapSwitch("state",true);
            $("#editorGoodsSpecificationForm #isTrademark").bootstrapSwitch("state",true);
            $("#editorGoodsSpecificationForm #isShow").bootstrapSwitch("state",true);
        }
    }
    /**
     * 添加商品规格
     **/
    function addGoodsSpecification(){
        editorGoodsSpecificationType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();
        $("#editorGoodsSpecificationForm #isShow").bootstrapSwitch("state",true);
        $("#editorGoodsSpecificationForm #isSpecification").bootstrapSwitch("state",true);
        $("#editorGoodsSpecificationForm #isTrademark").bootstrapSwitch("state",true);
        $("#editorGoodsSpecificationForm #isParams").bootstrapSwitch("state",true);
        $("#editorSysUserTitle").text("添加商品规格");
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
