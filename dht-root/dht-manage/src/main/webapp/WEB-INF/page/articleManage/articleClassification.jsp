<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
</head>
<body>

</div>
<table id="goodsTypeTables" ></table>
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
    var editorGoodsTypeType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'aname',
            title: '广告名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'aorder',
            title: '排序',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'isShow',
            title: '状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.aid,row);
                let html='';
                let show = row.isShow;
                let isShow;
                if(show == 1)
                    isShow = "显示";
                else
                    isShow = "不显示"
                html+='<p>'+isShow+'</p>'
                return html;
            }
        },
        {
            title: '编辑',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.aid,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveAdvertisingModal(\''+row.aid+'\')">编辑</button>'
                return html;
            }
        },
        {
            field: 'hnCountry',
            title: '删除',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.aid,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();deleteHomeAdv(\''+row.aid+'\')">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openArticleClassification/queryArticleClassifyList","goodsTypeTables","aid",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gtName: $("#search_goodsType_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/openArticleClassification/queryArticleClassifyList"
            }
        );
    }
</script>

</body>
</html>
