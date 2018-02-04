<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/validate/css/bootstrapValidator.min.css">
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="<%=path%>js/validate/bootstrapValidator.min.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/common/form.js"> </script>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <select id="openingMenu" class="form-control">
        </select>

        <select id="TeamMenu" class="form-control">
            <option value="">选择团队</option>
        </select>

        <select id="UseMenu" class="form-control">
            <option value="">选择到场状态</option>
            <option value="1">已到场</option>
            <option value="0">未到场</option>
        </select>
        <button class="btn btn-default" onclick="refreshTableData()" type="button">查询</button>
        <a id="exportExcelA" class="btn btn-primary" type="button" >导出表格</a>
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>

<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/form.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>



<%--加载开盘期数--%>
<script>
    var oid = "";
    var tids = "";
    var isUse = "";
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
                    $("#openingMenu").append("<option value='"+data.rows[i].oid+"'>"+data.rows[i].oname+"</option>");
                }
                if(data.rows.length != 0){
                    oid = data.rows[0].oid;
                    console.log(oid);
                    createCheckUserTable();
                }
                $("#"+oid).attr("selected","selected");
            }
        });
    });
</script>

<%--加载开盘期数--%>
<script>
    $(function () {
        $.ajax({
            url:"/team/queryTeamList",
            method:"post",
            dataType:"json",
            data:{
                pageNo:1,
                pageSize:10000
            },
            success:function (data) {
                for(let i = 0;i<data.rows.length;i++){
                    $("#TeamMenu").append("<option value='"+data.rows[i].tid+"'>"+data.rows[i].tname+"</option>");
                }
            }
        });
    });
</script>

<script>
    $("#openingMenu").change(function () {
        oid = $(this).val();
    });

    $("#TeamMenu").change(function () {
        tids = $(this).val();
    });

    $("#UseMenu").change(function () {
        isUse = $(this).val();
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
            field: 'clientName',
            title: '客户名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'clientPhone',
            title: '客户电话号码',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'empName',
            title: '所属置业顾问名称',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '是否到场',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.isUse == 1){
                    return "已到场";
                }else{
                    return "未到场";
                }
            }
        }
    ]



    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            oid:oid,
            tids:tids,
            isUse:isUse,
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
                url:"/checkUser/queryCheckUserVoList"
            }
        );
    }

    function createCheckUserTable(){
        createTable("/checkUser/queryCheckUserVoList", "goodsTypeTables", "cuId", treeColumns, queryParams);
    }

    $("#exportExcelA").click(function () {
        window.location.href = '/checkUser/CheckUserOnExcel?oid='+oid+'&tids='+tids+'&isUse='+isUse;
    });

//    $('#exportExcelA').attr('href','/checkUser/CheckUserOnExcel?oid='+oid+'&tids='+tids+'&isUse='+isUse);
</script>

</body>
</html>
