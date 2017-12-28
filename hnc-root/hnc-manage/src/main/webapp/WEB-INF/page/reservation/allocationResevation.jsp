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
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default saveTeam" type="button">新增团队</button>
        <input type="text" class="form-control tname"  placeholder="请输入团队名称">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>



<%--新增团队--%>
<div class="modal fade" id="saveTeamModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="saveTeamForm">
                    <div class="form-group">
                        <label>团队名称</label>
                        <input type="text" class="form-control" name="tname" placeholder="请输入团队名称">
                    </div>

                    <div class="form-group">
                        <label>公司名称</label>
                        <input type="text" class="form-control" name="tcompany" placeholder="请输入公司名称">
                    </div>

                    <div class="form-group">
                        <label>排序</label>
                        <input type="text" class="form-control" name="torder" placeholder="请输入排序序号">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subSaveTeam">确定</button>
            </div>
        </div>
    </div>
</div>

<%--编辑团队--%>
<div class="modal fade" id="editTeamModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="editTeamForm">
                    <div class="form-group" style="display: none;">
                        <label>ID</label>
                        <input type="text" class="form-control" name="tid">
                    </div>

                    <div class="form-group">
                        <label>团队名称</label>
                        <input type="text" class="form-control" name="tname" placeholder="请输入团队名称">
                    </div>

                    <div class="form-group">
                        <label>公司名称</label>
                        <input type="text" class="form-control" name="tcompany" placeholder="请输入公司名称">
                    </div>

                    <div class="form-group">
                        <label>排序</label>
                        <input type="text" class="form-control" name="torder" placeholder="请输入排序序号">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subEditTeam">确定</button>
            </div>
        </div>
    </div>
</div>


<%--删除团队模态框--%>
<div class="modal fade" id="deleteTeamModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除该团队</h4>
            </div>
            <div class="modal-body">
                <p>删除该团队</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary subDeleteThisTeam">确定</button>
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
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    $(function () {
        //表格的初始化
        $('#goodsTypeTables').bootstrapTable({
            url:"/employeeRelationship/queryAllClient",
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                                  //传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
//            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            treeView: true,
            undefinedText:"",
            treeId:"tid",
            treeField:"propotionName",
            treePid:"emTeam",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "emId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            },{
                field: 'emReservation',
                align : 'left',
                valign : 'middle',
                title: '预约人数'

            },{
                field: 'pid',
                align : 'center',
                valign : 'middle',
                title: '开盘期数ID'

            },{
                field: 'emId',
                align : 'center',
                valign : 'middle',
                title: '置业顾问ID'

            },{
                field: 'tid',
                align : 'center',
                valign : 'middle',
                title: '团队ID'

            }]
        });
    });

    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/employeeRelationship/queryAllClient"
            }
        );
    }
</script>


<%--新增团队--%>
<script>
    var isSave = false;
    /*打开添加团队模态框*/
    $(".saveTeam").click(function () {
        $("#saveTeamModal").modal("show");
    });

    /*提交新建团队*/
    $(".subSaveTeam").click(function () {
        let bootstrapValidator = $("#saveTeamForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave = true;
            var floor = document.getElementById("saveTeamForm");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            $.ajax({
                url:"/team/addTeam",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#saveTeamModal").modal("hide");
                    $("#saveTeamForm").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>


<script>
    var team;
    /*打开编辑团队模态框*/
    function editTeam(id) {
        team = rowDatas.get(id);
        $("#editTeamForm input[type='text']").each(function () {
            let name = $(this).attr("name");
            $(this).val(team[name]);
        });
        $("#editTeamModal").modal("show");
    }

    /*提交修改*/
    $(".subEditTeam").click(function () {
        let bootstrapValidator = $("#editTeamForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        if(!isSave){
            isSave = true;
            var floor = document.getElementById("editTeamForm");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            $.ajax({
                url:"/team/updateTeam",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#editTeamModal").modal("hide");
                    $("#editTeamForm").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>


<%--删除团队--%>
<script>
    /*提交删除*/
    $(".subDeleteThisTeam").click(function () {
        $.ajax({
            url:"/employeeRelationship/queryAllClient",
            method:"post",
            dataType:"json",
            success:function (data) {
                /*isSave = false;
                refreshTableData();
                $("#deleteTeamModal").modal("hide");*/
            }
        });
    });

    /*打开是删除团队模态框*/
    function removeTeam(id) {
        team = rowDatas.get(id);
        $("#deleteTeamModal").modal("show");
    }
</script>

<%--表单校验--%>
<script type="text/javascript">
    $('#saveTeamForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            tname: {
                validators: {
                    notEmpty: {
                        message: '团队名称不能为空'
                    }
                }
            },
            tcompany: {
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
            },
            torder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            }
        }
    });


    $('#editTeamForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            tname: {
                validators: {
                    notEmpty: {
                        message: '团队名称不能为空'
                    }
                }
            },
            tcompany: {
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
            },
            torder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            }
        }
    });
</script>
</body>
</html>
