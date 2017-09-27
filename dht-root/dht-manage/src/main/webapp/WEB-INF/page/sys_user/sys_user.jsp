<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>职员管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <input type="text" class="form-control" id="search_user_name" placeholder="请输入职员姓名">
    </div>

    <div class="form-group">
        <input type="text" class="form-control" id="search_user_org" placeholder="请选择职员所在部门">
    </div>
    <ex:perm url="	sysUser/querySysUserLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
    <ex:perm url="	sysUser/addSysUser">
        <button class="btn btn-default" type="button" onclick="addSysUser()">添加职员</button>
    </ex:perm>
</div>
<div>
    <table id="sysUserTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorSysUserForm">
                    <input type="hidden" name="uid" id="uid">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                登录帐号:
                              </span>
                                <input type="text" class="form-control" name="uaccount" id="uaccount">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  职工姓名:
                              </span>
                                <input type="text" class="form-control" id="uname" name="uname"/>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                所属部门:
                              </span>
                                <input type="hidden" id="orgIds" name="orgIds"/>
                                <input type="text" class="form-control" aria-label="..." id="orgNms" name="orgNms"  onclick="showOrgTree(); return false;">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                是否有效:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isValid" name="isValid" type="checkbox" />
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
    var editorSysUserType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'uaccount',
            title: '帐户'
        },
        {
            field: 'uname',
            title: '姓名'
        },
        {
            field: 'orgNms',
            title: '所在部门'
        },
        {
            field: 'ucreateTime',
            title: '创建时间'
        },
        {
            field: 'isValid',
            title: '状态',
            align : 'center',
            valign : 'middle',
            width:120,
            formatter:function(value,row,index){
                rowDatas.set(row.uid,row);
                if(value==0){
                    return "启用";
                }else if(value==1){
                    return "停用";
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
                let html='';
                <ex:perm url="sysUser/editorSysUser">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorOrganization(\''+row.uid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="	sysUser/delSysUser">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.uid+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/sysUser/querySysUserLists","sysUserTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorSysUserForm #isValid").bootstrapSwitch();
        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorSysUserForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorSysUserForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorSysUserForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorSysUserForm").serializeObject();
            var flag =$("#editorSysUserForm #isValid").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            let url="/sysUser/addSysUser";
            if(editorSysUserType==1){
                url="/sysUser/editorSysUser";
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
        $('#editorSysUserForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    //live: 'submitted',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        uaccount: {
                            message: '职工账号校验未通过',
                            validators: {
                                notEmpty: {
                                    message: '职工登录账号不能为空'
                                },
                                stringLength: {
                                    min: 1,
                                    max: 30,
                                    message: '职工登录账号长度在4-30之间'
                                }
                            }
                        },
                        uname: {
                            message: '职工姓名校验未通过',
                            validators: {
                                notEmpty: {
                                    message: '职工姓名不能为空'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 10,
                                    message: '职工姓名长度在2-10之间'
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
            userName: $("#search_user_name").val(),
            orgs: $("#search_user_org").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#sysUserTables').bootstrapTable(
            "refresh",
            {
                url:"/sysUser/querySysUserLists"
            }
        );
    }
    //删除确认框
    function deleteData(uid){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeSysUser(uid);
        }, function(){
        });
    }
    /**
     * 删除部门
     **/
    function removeSysUser(uid){
        $.ajax({
            type:"post",
            url:'/sysUser/delSysUser',
            dataType: "json",
            data:{uid:uid},
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
    function editorOrganization(orgId){
        editorSysUserType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑职工");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorSysUserForm #uid").val("");
        $("#editorSysUserForm #version").val("");
        $("#editorSysUserForm #uaccount").val("");
        $("#editorSysUserForm #uname").val("");
        $("#editorSysUserForm #orgIds").val("");
        $("#editorSysUserForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorSysUserForm #uid").val(rowData.uid);
            $("#editorSysUserForm #version").val(rowData.version);
            $("#editorSysUserForm #uaccount").val(rowData.uaccount);
            $("#editorSysUserForm #uname").val(rowData.uname);
            $("#editorSysUserForm #orgIds").val(rowData.orgIds);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorSysUserForm #isValid").bootstrapSwitch("state",flag);
            $("#editorSysUserForm #orgIds").val(rowData.orgIds);
            $("#editorSysUserForm #orgNms").val(rowData.orgNms);
        }
    }
    /**
     * 编辑部门
     **/
    function addSysUser(){
        editorSysUserType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();
        $("#editorSysUserForm #isValid").bootstrapSwitch("state",true);
        $("#editorSysUserTitle").text("添加职工");
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
