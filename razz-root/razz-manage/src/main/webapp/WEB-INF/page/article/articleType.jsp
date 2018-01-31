<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>商品子类管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>

</head>
<body>
<div id="toolbar" class="form-inline">
    <button class="btn btn-default" onclick="addArticleType()" type="button">添加类别</button>
</div>

<div>
    <table id="goodsClassificationTable" ></table>
</div>

<div class="modal fade" id="add_article" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加类别</h4>
            </div>
            <div class="modal-body">
                <form id="saveArticleType">
                    <div class="form-group">
                        <label></label>类别名称:</label>
                        <input type="text" class="form-control" name="tname" placeholder="请输入类别名称">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary sub_saveArticle">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 公用下拉择树 -->
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<!-- 图标选择器-->
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker-iconset-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/jquery.treegrid.extension.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>
<script src="/js/layer/layer.js"></script>

<%--添加类别--%>
<script>
    function addArticleType() {
        $("#add_article").modal("show");
    }
    
    $(".sub_saveArticle").click(function () {
        var data = new FormData($("#saveArticleType"));
        data.append("isDelete",0);
        data.append("version",0);
        $.ajax({
            url:"/article/addArticleType",
            method:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function (data) {
                console.log(data);
            }
        });
    });
</script>

<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    $(function () {
        $('#goodsClassificationTable').bootstrapTable({
            url:"/article/queryArticleTypeList",
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: function () {

            },                                  //传递参数（*）
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
            contentType:"application/x-www-form-urlencoded",
            treeField:"tname",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            treeCollapseAll:true,
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "tid",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            },{
                field: 'tname',
                align : 'left',
//                valign : 'middle',
                halign:'center',
                title: '文章类别',
            },{
                align : 'center',
                valign : 'middle',
                title: '添加子类',
                formatter:function (value,row,index) {
                    let html = "";
                    if(row.level == 1){
                        html += '<button class="btn btn-default" type="button">添加子类</button>';
                    }
                    return html;
                }
            },{
                align : 'center',
                valign : 'middle',
                title: '编辑',
                formatter:function (value,row,index) {
                    let html = "";
                    html += '<button class="btn btn-default" type="button">编辑</button>';
                    return html;
                }
            },{
                align : 'center',
                valign : 'middle',
                title: '删除',
                formatter:function (value,row,index) {
                    let html = "";
                    html += '<button class="btn btn-default" type="button">删除</button>';
                    return html;
                }
            }]
        });
    });
</script>
</body>
</html>
