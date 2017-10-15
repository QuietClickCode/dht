<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>系统参数配置表</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <input type="text" class="form-control" id="search_user_name" placeholder="请输入常名称">
    </div>
    <ex:perm url="	sysUser/querySysUserLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
</div>
<div>
    <table id="sysParamTables" ></table>
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
            field: 'parameterKey',
            title: '参数key'
        },
        {
            field: 'parameterName',
            title: '参数名称'
        },
        {
            field: 'parameterValue',
            title: '参数值'
        },
        {
            field: 'parameterDes',
            title: '参数描述'
        },
        {
            field: 'parameterCreateTime',
            title: '创建时间'
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
                return html;
            }
        }
    ]

    $(function () {
        createTable("/sysUser/querySysUserLists","sysParamTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorSysUserForm #isValid").bootstrapSwitch();
        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
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
        $('#sysParamTables').bootstrapTable(
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
        initFormData();
        $("#editorSysUserForm #isValid").bootstrapSwitch("state",true);
        $("#editorSysUserTitle").text("添加职工");
        $('#editorSysUser').modal("show")
    }
</script>
</body>
</html>
