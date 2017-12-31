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
        <select id="openingMenu" class="form-control">
        </select>
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>

<%--添加删除楼栋模态框--%>
<div class="modal fade bs-example-modal-lg" tabindex="-1" id="saveFloorManage" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改户型</h4>
            </div>
            <div class="modal-body" style="overflow: hidden;">
                <div id="queryFloorToolbar" class="form-inline">
                    <input type="text" class="form-control fmName"  placeholder="请输入楼栋名称">
                    <button class="btn btn-default" onclick="refreshTableData()">查询</button>
                </div>
                <div class="house_type_table">
                    <table id="house_type_table"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary addFloorRelationship">确定</button>
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

<%--加载开盘期数--%>
<script>
    var oid;
    var opening = new Map();
    $(function () {
        $.ajax({
            url:"/opening/queryOpeningList",
            method:"post",
            dataType:"json",
            data:{
                pageNo:1,
                pageSize:10000
            },
            success:function (data) {
                for(let i = 0;i<data.rows.length;i++){
                    opening.set(data.rows[i].oid, data.rows[i]);
                    $("#openingMenu").append("<option id='"+data.rows[i].oid+"' value='"+data.rows[i].oid+"'>"+data.rows[i].oname+"</option>");
                }
                if(data.rows.length != 0){
                    oid = data.rows[0].oid;
                    $("#MenberNum").val(data.rows[0].omenberNum);
                }
                $("#"+oid).attr("selected","selected");
                createEmployeeTable(oid);
            }
        });
    });

    $("#openingMenu").change(function () {
        oid = $(this).val();
        rowDatas.clear();
        $("#goodsTypeTables").bootstrapTable('destroy');
        createEmployeeTable(oid);
    });
</script>

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
            field: 'teamName',
            title: '团队名称',
            align : 'center',
            valign : 'middle'
        },
        {
            align : 'center',
            valign : 'middle',
            title: '删除',
            formatter:function (value,row,index) {
                let html='';
                rowDatas.set(row.emId,row);
                <ex:perm url="floorManage/removeFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">删除</button>'
                </ex:perm>
                return html;
            }
        }
    ]

    function createEmployeeTable(oid) {
        createTable("/scanCode/queryScanCodeList","goodsTypeTables","scId",treeColumns,queryParams)
    }

    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            oid: oid,
            pageSize: that.pageSize,
            pageNo: that.pageNumber
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/scanCode/queryScanCodeList"
            }
        );
    }

</script>

<script>
    var floorManagesMap = new Map();
    function createFloorManageTable(){
        //表格的初始化
        $("#house_type_table").bootstrapTable({
            url:"/employeeManage/queryEmployeeManageList",
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
            uniqueId: "emId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType: "json",
            columns: treeFloorColumns,
            onCheckAll:function(rows){
                for(var row of rows){
                    if(!floorManagesMap.has(row.htId)){
                        floorManagesMap.set(row.htId,floorManage.fmId);
                    }
                }
            },
            //点击每一个单选框时触发的操作
            onCheck:function(row){
                //判断是否己经存在
                if(!floorManagesMap.has(row.htId)){
                    floorManagesMap.set(row.htId,floorManage.fmId);
                }
            },
            //取消每一个单选框时对应的操作；
            onUncheck:function(row){
                //判断是否己经存在
                if(floorManagesMap.has(row.htId)){
                    floorManagesMap.delete(row.htId);
                }
            },
            onUncheckAll:function(rows){
                floorManagesMap.clear();
            },
            contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
        });
    }

    /**
     * 查询条件
     **/
    function commonFloorParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber
        };
    }

    /**
     * 刷新表格数据
     **/
    function refreshFloorTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/employeeManage/queryEmployeeManageList"
            }
        );
    }

    var treeFloorColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle',
            formatter:commonFloorSelectCheckFormatter
        },
        {
            field: 'htTypeName',
            title: '户型名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'htType',
            title: '户型',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'htArea',
            title: '户型面积',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.htArea != "")
                    return row.htArea + "m²";
                return "";
            }
        }
    ]

    function commonFloorSelectCheckFormatter(value, row, index) {
        let curId = row.emId;
        for(let i = 0;i<floorManage.typeManages.length;i++){
            if(curId == floorManage.typeManages[i].htId){
                return {
                    checked : true//设置选中
                };
            }
        }
        return {
            checked : false//设置选中
        };
    }
</script>

<%--表单校验--%>
<script type="text/javascript">
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
