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

<%--修改客户备注--%>
<div class="modal fade" id="editClientInfo" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改客户备注</h4>
            </div>
            <div class="modal-body">
                <form id="editClientInfoForm">
                    <div class="form-group">
                        <label>客户备注</label>
                        <textarea class="form-control" name="tmInfo" id="tmInfo" style="resize: none;height: 300px;" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subEditClientInfoForm">确定</button>
            </div>
        </div>
    </div>
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
            title: '姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'curRegisterClientCount',
            title: '当日登记',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'registerClientCount',
            title: '总登记',
            align : 'center',
            valign : 'middle'
        },
        {
            align : 'center',
            valign : 'middle',
            title: '查看当日登记',
            formatter:function (value,row,index) {
                let html = "";
                rowDatas.set(''+row.emId+'',row);
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();getCurRegisterClient(\'' + row.emId + '\')">查看当日登记</button>'
                </ex:perm>
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '查看总登记',
            formatter:function (value,row,index) {
                let html = "";
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();getRegisterClient(\'' + row.emId + '\')">查看总登记</button>'
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
                url:"/employeeManage/queryEmployeeManageList"
            }
        );
    }

</script>


<script>
    var floorManagesMap = new Map();
    function createFloorManageTable(){
        //表格的初始化
        $("#house_type_table").bootstrapTable({
            url:"/houseManage/queryHouseType",
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
            uniqueId: "htId",                     //每一行的唯一标识，一般为主键列
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
                url:"/clientInfo/queryClientList"
            }
        );
    }

    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmName',
            title: '客户姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmSex',
            title: '客户性别',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmStatus',
            title: '购房状态',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmPhone',
            title: '客户电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmRegisterTime',
            title: '登记时间',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html = "";
                if(row.tmRegisterTime != "")
                    html = row.tmRegisterTime.split(" ")[0];
                return html;
            }
        },
        {
            field: 'tmChannel',
            title: '来访渠道',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmInfo',
            title: '客户备注',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html = "";
                if(row.tmInfo.length != "" && row.tmInfo.length > 6)
                    html = row.tmInfo.substring(0,6) + '...';
                return html;
            }
        }
    ]
</script>

</body>
</html>
