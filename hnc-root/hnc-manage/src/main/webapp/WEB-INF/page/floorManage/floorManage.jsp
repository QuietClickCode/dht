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
    <style>

    </style>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default saveFloor" type="button">新增楼栋</button>
        <input type="text" class="form-control"  id="AdvertisingName" placeholder="请输入楼栋名称">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>

</div>
<table id="goodsTypeTables" ></table>
</div>

<div class="modal fade" id="addFloor" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增楼栋</h4>
            </div>
            <div class="modal-body">
                <form id="saveFloor">
                    <div class="form-group">
                        <label>楼栋名称:</label>
                        <input type="text" class="form-control" name="fmName" placeholder="楼栋名称">
                    </div>
                    <div class="form-group">
                        <label>物业性质:</label>
                        <input type="text" class="form-control" name="fmType" placeholder="物业性质">
                    </div>
                    <div class="form-group">
                        <label>房屋套数:</label>
                        <input type="text" class="form-control" name="fmQuantity" placeholder="楼栋名称">
                    </div>
                    <div class="form-group">
                        <label>备注:</label>
                        <textarea style="outline:none;resize:none;height: 200px;" name="fmInfo" class="form-control" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary addFloor">保存</button>
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

<%--初始化表格数据--%>
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
            field: 'fmName',
            title: '楼栋名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fmType',
            title: '物业性质',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fmQuantity',
            title: '房屋套数',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '户型',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fmInfo',
            title: '备注',
            align : 'center',
            valign : 'middle'
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">编辑</button>'
                </ex:perm>
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '删除',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/floorManage/queryFloorList","goodsTypeTables","fmId",treeColumns,queryParams)
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
                url:"/floorManage/queryFloorList"
            }
        );
    }

</script>


<%--新增楼栋--%>
<script type="text/javascript">

    $(".saveFloor").click(function () {
        $("#addFloor").modal("show");
    });

    $(".addFloor").click(function () {
        var floor = document.getElementById("saveFloor");
        var data = new FormData(floor);
        data.append("isDelete", 0);
        $.ajax({
            url:"/floorManage/addFloor",
            method:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function (data) {
                refreshTableData();
                $("#addFloor").modal("hide");
            }
        });
    });

</script>
</body>
</html>
