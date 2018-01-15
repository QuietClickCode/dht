<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>排名公示</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <span>排名公示类型:</span>
        <select id="search_rtn_type" name="search_rtn_type"  class="form-control" style="width: auto;">
            <c:forEach items="${gts}" var="rtnList" varStatus="vs">
                <option value="${rtnList.rtId}" <c:if test="${vs.index==0}">selected="selected" </c:if>>${rtnList.rtName}</option>
            </c:forEach>
        </select>
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" id="search_buy_name" placeholder="请输入购买人名">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" id="search_buy_phone" placeholder="请输入购买电话">
    </div>&nbsp;&nbsp;
    <ex:perm url="ranking/openRankingPage">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
</div>
<div>
    <table id="orderTables" ></table>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var sendGoodsDialogType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'rtName',
            title: '排名公示名称'
        },
        {
            field: 'userName',
            title: '用户名'
        },
        {
            field: 'userHead',
            title: '用户头像',
            formatter:function(value,row,index){
                var html="";
                if(value){
                    html ='<img src="'+value+'" width="96px;" height="48px;">';
                }
                return html;
            }
        },
        {
            field: 'orderNo',
            title: '订单号'
        },
        {
            field: 'ccbqCreateTime',
            title: '创建时间'
        },
        {
            field: 'ccbqMoneys',
            title: '返现金额'
        },
        {
            field: 'ccbqStatus',
            title: '返现状态',
            formatter:function(value,row,index){
                var html = '排队中';
                if(value==1){
                    html= "正在筹款";
                }else if(value==2){
                    html='己返现';
                }
                return html;
            }
        }
    ]

    $(function () {
        createExpandTable("/ranking/queryRankingList","orderTables","orgId",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gcId: $("#search_rtn_type").val(),
            userNm: $("#search_buy_name").val(),
            phone: $("#search_buy_phone").val()
        };
    }

    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#orderTables').bootstrapTable(
            "refresh",
            {
                url:"/ranking/queryRankingList"
            }
        );
    }
</script>
</body>
</html>
