<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
    <script type="text/javascript" src="/js/laydate/laydate.js"></script>
    <style type="text/css">
        .house_type_table{
            height: 500px;
            overflow-y: scroll;
        }
    </style>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default saveEmployee" type="button">新增员工</button>
        <input type="text" class="form-control tname"  placeholder="请输入员工姓名">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>
</div>
<div>
    <table id="goodsTypeTables"></table>
</div>

<%--新增员工模态框--%>
<div class="modal fade" id="saveEmployee" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addEmployee">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emName"  placeholder="请输入姓名">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别:</label>
                        <div class="col-sm-9">
                            <label class="radio-inline">
                                <input type="radio" name="emSex" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="emSex" value="0"> 女
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">入职时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="emEntryTime" name="emEntryTimes"  placeholder="请输入入职时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">调动时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="emRemoveTime" name="emRemoveTimes"  placeholder="请输入调动时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">职位:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emPosition"  placeholder="请输入职位">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">排序:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emOrder"  placeholder="请输入排序序号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emPhone"  placeholder="请输入手机号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">微信绑定手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="wxPhone"  placeholder="请输入微信手机号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emIdCard"  placeholder="请输入身份证号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注:</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" name="emInfo" rows="4" style="resize: none;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subAddEmployee">确定</button>
            </div>
        </div>
    </div>
</div>

<%--编辑员工信息模态框--%>
<div class="modal fade" id="updateEmployee" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateEmployeeForm">
                    <div class="form-group" style="display: none">
                        <label class="col-sm-3 control-label">ID:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emId"  placeholder="ID">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emName"  placeholder="请输入姓名">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别:</label>
                        <div class="col-sm-9">
                            <label class="radio-inline">
                                <input type="radio" class="emSex" name="emSex" value="1"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="emSex" name="emSex" value="0"> 女
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">入职时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="editEmEntryTime" name="emEntryTimes"  placeholder="请输入入职时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">调动时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="editEmRemoveTime" name="emRemoveTimes"  placeholder="请输入调动时间">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">职位:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emPosition"  placeholder="请输入职位">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">排序:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emOrder"  placeholder="请输入排序序号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emPhone"  placeholder="请输入手机号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">微信绑定手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="wxPhone"  placeholder="请输入微信手机号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emIdCard"  placeholder="请输入身份证号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注:</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" id="emInfo" name="emInfo" rows="4" style="resize: none;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subUpdateEmployeeForm">确定</button>
            </div>
        </div>
    </div>
</div>

<%--删除该员工--%>
<div class="modal fade" id="deleteEmployeeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">移除该员工</h4>
            </div>
            <div class="modal-body">
                <p>是否移除该员工</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary subDeleteThisEmployee">确定</button>
            </div>
        </div>
    </div>
</div>

<%--添加删除楼栋模态框--%>
<div class="modal fade" tabindex="-1" id="saveFloorManage" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改户型</h4>
            </div>
            <div class="modal-body" style="overflow: hidden;">
                <div id="queryFloorToolbar" class="form-inline">
                    <input type="text" class="form-control fmName"  placeholder="请输入团队名称">
                    <button class="btn btn-default" onclick="refreshFloorTableData()">查询</button>
                </div>
                <div class="house_type_table" style="">
                    <table id="house_type_table"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary addTeam">确定</button>
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
<script type="text/javascript" src="/js/layer/layer.js"></script>

<%--初始化表格数据--%>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emName',
            title: '员工姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emSex',
            title: '员工性别',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html='';
                if(row.emSex == 1){
                    return '男';
                }else if(row.emSex == 0){
                    return '女';
                }
                return html;
            }
        },
        {
            field: 'emPosition',
            title: '职位',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emEntryTime',
            title: '入职时间',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emRemoveTime',
            title: '调动时间',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emPosition',
            title: '职位',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emPhone',
            title: '员工手机号',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'emIdCard',
            title: '身份证号码',
            align : 'center',
            valign : 'middle'
        },
        {
            align : 'center',
            valign : 'middle',
            title: '团队',
            formatter:function (value,row,index) {
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();chooseTeam(\'' + row.emId + '\')">选择团队</button>'
                </ex:perm>
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(''+row.emId+'',row);
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editEmployee(\'' + row.emId + '\')">编辑</button>'
                </ex:perm>
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '删除',
            formatter:function (value,row,index) {
                let html='';
                <ex:perm url="floorManage/removeFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();removeEmployee(\'' + row.emId + '\')">删除</button>'
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/employeeManage/queryEmployeeManageList","goodsTypeTables","emId",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/employeeManage/queryEmployeeManageList"
            }
        );
    }

</script>

<%--新增员工--%>
<script>
    var isSave = false;
    /*打开新增员工模态框*/
    $(".saveEmployee").click(function () {
        $("#saveEmployee").modal("show");
    });

    /*提交新增员工*/
    $(".subAddEmployee").click(function () {
        let bootstrapValidator = $("#addEmployee").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave = true;
            var floor = document.getElementById("addEmployee");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            data.append("isShow", 1);
            $.ajax({
                url:"/employeeManage/addEmployee",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#saveEmployee").modal("hide");
                    $("#addEmployee").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>

<%--编辑员工信息--%>
<script>
    var employee;
    function editEmployee(id) {
        employee = rowDatas.get(id);
        $("#updateEmployeeForm input[type='text']").each(function () {
            let name = $(this).attr("name");
            $(this).val(employee[name]);
        });
        radioChoose(".emSex",employee["emSex"]);
        $("#editEmEntryTime").val(employee["emEntryTime"]);
        $("#editEmRemoveTime").val(employee["emRemoveTime"]);
        $("#emInfo").val(employee["emInfo"]);
        $("#updateEmployee").modal("show");
    }

    $('#updateEmployee').on('hidden.bs.modal', function () {
        $("#updateEmployeeForm").data('bootstrapValidator').resetForm(true);
    })

    $(".subUpdateEmployeeForm").click(function () {
        let bootstrapValidator = $("#updateEmployeeForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave = true;
            var floor = document.getElementById("updateEmployeeForm");
            var data = new FormData(floor);
            $.ajax({
                url:"/employeeManage/updateEmployee",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#updateEmployee").modal("hide");
                    $("#updateEmployeeForm").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>

<%--删除该员工--%>
<script>
    function removeEmployee(id) {
        employee = rowDatas.get(id);
        $("#deleteEmployeeModal").modal("show");
    }

    $(".subDeleteThisEmployee").click(function () {
        if(!isSave){
            isSave = true;
            $.ajax({
                url:"/employeeManage/removeEmployee",
                method:"post",
                data:{
                    emId:employee['emId']
                },
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#deleteEmployeeModal").modal("hide");
                }
            });
        }
    });
</script>

<%--选择团队--%>
<script>
    function chooseTeam(id) {
        employee = rowDatas.get(id);
        $("#house_type_table").bootstrapTable('destroy');
        createFloorManageTable();
        $("#saveFloorManage").modal("show");
    }

    $(".addTeam").click(function () {
        var team = $("#house_type_table").bootstrapTable('getSelections');
        if(team.length != 0){
            $.ajax({
                url:"/employeeManage/updateEmployee",
                method:"post",
                dataType:"json",
                data:{
                    emId:employee['emId'],
                    emTeam:team[0].tid
                },
                success:function (data) {
                    $("#saveFloorManage").modal("hide");
                    layer.msg("修改成功",{time:1000});
                    refreshTableData();
                }
            });
        }
        return;
    });
</script>

<%--自定义方法--%>
<script>
    /*为单选框赋值*/
    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }
</script>

<%--日期选择器--%>
<script>
    laydate.render({
        elem: '#emEntryTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#emRemoveTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#editEmEntryTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#editEmRemoveTime'
        ,type: 'datetime'
    });
</script>

<script>
    var floorManagesMap = new Map();
    function createFloorManageTable(){
        //表格的初始化
        $("#house_type_table").bootstrapTable({
            url:"/team/queryTeamList",
            method: 'post',                      //请求方式（*）
            toolbar:'#queryFloorToolbar' ,                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: false,                     //是否启用排序
            queryParams: function (params) {
                var params=commonFloorParams(this);
                return params;
            },                                  //传递参数（*）
            pagination:true,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 1000,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "tid",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType: "json",
            columns: treeFloorColumns,
            contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
        });
    }

    /**
     * 查询条件
     **/
    function commonFloorParams(that){
        return {
            name:$(".fmName").val(),
            pageSize: that.pageSize,
            pageNo: that.pageNumber
        };
    }

    /**
     * 刷新表格数据
     **/
    function refreshFloorTableData() {
        $('#house_type_table').bootstrapTable(
            "refresh",
            {
                url:"/team/queryTeamList"
            }
        );
    }

    var treeFloorColumns=[
        {   radio: true,
            align : 'center',
            valign : 'middle',
            formatter:commonFloorSelectCheckFormatter
        },
        {
            field: 'tname',
            title: '团队名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tcompany',
            title: '所属公司',
            align : 'center',
            valign : 'middle'
        }
    ]

    function commonFloorSelectCheckFormatter(value, row, index) {
        let curId = row.tid;
        console.log(curId);
        console.log(employee['emTeam']);
        if(employee['emTeam'] != ""){
            if(employee['emTeam'] == curId){
                return {
                    checked : true//设置选中
                };
            }else{
                return {
                    checked : false//设置选中
                };
            }
        }
    }
</script>


<%--表单校验--%>
<script type="text/javascript">
    $('#addEmployee').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            emName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            emOrder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            },
            emPosition: {
                validators: {
                    notEmpty: {
                        message: '职位不能为空'
                    }
                }
            },
            emPhone: {
                validators: {
                    notEmpty: {
                        message: '员工手机号不能为空'
                    }
                }
            },
            wxPhone: {
                validators: {
                    notEmpty: {
                        message: '微信手机号不能为空'
                    }
                }
            },
            emIdCard: {
                validators: {
                    notEmpty: {
                        message: '员工身份证号不能为空'
                    }
                }
            }
        }
    });


    $('#updateEmployeeForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            emName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            emOrder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            },
            emPosition: {
                validators: {
                    notEmpty: {
                        message: '职位不能为空'
                    }
                }
            },
            emPhone: {
                validators: {
                    notEmpty: {
                        message: '员工手机号不能为空'
                    }
                }
            },
            wxPhone: {
                validators: {
                    notEmpty: {
                        message: '微信手机号不能为空'
                    }
                }
            },
            emIdCard: {
                validators: {
                    notEmpty: {
                        message: '员工身份证号不能为空'
                    }
                }
            }
        }
    });
</script>
</body>
</html>
