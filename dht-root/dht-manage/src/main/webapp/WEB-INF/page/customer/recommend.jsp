<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>会员管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body id="contextDiv">
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <sapn>开始时间:</sapn>
        <input type="text" class="form-control" id="search_startDate" placeholder="请输入开始时间YYYY-MM-DD">
    </div>
    <div class="form-group">
        <sapn>结束时间:</sapn>
        <input type="text" class="form-control" id="search_endDate" placeholder="请输入结束时间YYYY-MM-DD">
    </div>
    <div class="form-group">
        <span>推广人手机号:</span>
        <input type="text" class="form-control" id="search_phone" placeholder="请输入推广人手机号">
    </div>
    <div class="form-group">
        <span>提成类型:</span>
        <select id="search_type" name="search_type"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0">首单提成</option>
            <option value="1">推荐提成</option>
        </select>
    </div>
    <div class="form-group">
        <span>推广人类型:</span>
        <select id="search_userType" name="search_userType"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="1">兼职推广人员</option>
            <option value="2">推广人员</option>
        </select>
    </div>
    <div class="form-group">
        <span>订单号:</span>
        <input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单号">
    </div>
    <ex:perm url="customer/queryCustomerLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
</div>
<div id="tableContext">
    <table id="customerListsTables" class="table text-nowrap"></table>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorCustomerType=0;

    var treeColumns=[
        {checkbox: true},
        {
            field: 'rsType',
            title: '提成类型',
            formatter:function(value,row,index){
                if(value==0){
                    html="首单提成"
                }else if(value==1){
                    html="推荐提成"
                }
                return html;
            }
        },
        {
            field: 'rsOrderNo',
            title: '订单号'
        },
        {
            field: 'goodsNm',
            title: '商品名称'
        },
        {
            field: 'rsSalesPrice',
            title: '消费金额'
        },
        {
            field: 'rsRatio',
            title: '提成比例(%)',
            formatter:function(value,row,index){
                return value+'%'
            }
        },
        {
            field: 'rsPrice',
            title: '提成金额'
        },
        {
            field: 'rsRecommendNm',
            title: '推广用户'
        },
        {
            field: 'rsUtype',
            title: '推广用户类型',
            formatter:function(value,row,index){
                if(value==1){
                    html="兼职推广人员"
                }else if(value==3){
                    html="推广人员"
                }
                return html;
            }
        },
        {
            field: 'rsNm',
            title: '购买人'
        },
        {
            field: 'rsTime',
            title: '创建时间'
        }
    ]

    $(function () {
        createTable("/recommend/queryRecommendLists","customerListsTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $('#editorCustomer').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorCustomerForm",formValidater)
        });
        formValidater();
        $("#contextDiv").height($(window).height()-80);
        $("#tableContext").height($(window).height()-90);
        $('#customerListsTables').bootstrapTable("resetView");
    });
    $(window).resize(function() {
        $("#contextDiv").height($(window).height()-80);
        $("#tableContext").height($(window).height()-90);
        $('#customerListsTables').bootstrapTable("resetView");
    });

    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            startDate: $("#search_startDate").val(),
            endDate: $("#search_endDate").val(),
            phone: $("#search_phone").val(),
            type: $("#search_type").val(),
            userType: $("#search_userType").val(),
            orderNo: $("#search_orderNo").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#customerListsTables').bootstrapTable(
                "refresh",
                {
                    url:"/recommend/queryRecommendLists"
                }
        );
    }
</script>
</body>
</html>
