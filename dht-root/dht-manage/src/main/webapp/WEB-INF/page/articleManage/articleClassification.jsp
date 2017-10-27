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
<body>

<div id="toolbar" class="form-inline">
    <button class="btn btn-default" type="button" onclick="addClassify()">添加分类</button>
    <input type="text" class="form-control" id="classificationName" placeholder="请输入分类名称">
    <button class="btn btn-default" onclick="refreshTableData()">查询</button>
</div>

<%--添加分类模态框--%>
<div class="modal fade" id="addArticleClassify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">添加分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addArticle">
                    <div class="form-group">
                        <label for="classifyName" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="classifyName" id="classifyName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="order" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="order" id="order" class="form-control">
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="subAddArticle()">确定</button>
                </div>
            </div>

        </div>
    </div>
</div>


<%--编辑分类模态框--%>
<div class="modal fade" id="saveArticleClassify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="saveArticle">
                    <div class="form-group">
                        <label for="saveClassifyName" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveClassifyName" id="saveClassifyName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="saveOrder" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="saveOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isShow" class="isShow" value="1">显示
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isShow" class="isShow" value="0">不显示
                            </label>
                        </div>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary"  onclick="saveArticle()">确定</button>
                </div>
            </div>

        </div>
    </div>
</div>


<%--删除分类模态框--%>
<div class="modal fade" id="deleteArticleClassify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除分类</h4>
            </div>
            <div class="modal-body">
                <p>删除该分类</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="deleteArticle()">确定</button>
            </div>
        </div>
    </div>
</div>


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
            title: '分类名称',
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
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveArticleClassifyModal(\''+row.aid+'\',\''+row.version+'\',\''+row.aname+'\',\''+row.aorder+'\',\''+row.isShow+'\')">编辑</button>'
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
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openDeleteArticleModel(\''+row.aid+'\')">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openArticleClassification/queryArticleClassifyList","goodsTypeTables","aid",treeColumns,queryParams)
    });

    function addClassify() {
        $("#addArticleClassify").modal("show");
    }

    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            aname:$("#classificationName").val(),
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

    var aid;
    var aversion;
    function openSaveArticleClassifyModal(id,version,name,order,isShow) {
        aid = id;
        aversion = version;
        $("#saveArticleClassify").modal("show");
        $("#saveClassifyName").val(name);
        $("#saveOrder").val(order);
        if(isShow == 1)
            $(".isShow")[0].checked = 'checked';
        else
            $(".isShow")[1].checked = 'checked';

    }

    function subAddArticle() {
        let name = $("#classifyName").val();
        let order = $("#order").val();
        let bootstrapValidator = $("#addArticle").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            $.ajax({
                url:"/openArticleClassification/addArticleClassify",
                method:"post",
                dataType:"json",
                data:{
                    aname:name,
                    aorder:order,
                    isDelete:0,
                    isShow:1,
                    version:0
                },
                success:function (data) {
                    $("#addArticleClassify").modal("hide");
                    layer.msg(data.msg);
                    refreshTableData();
                    $("#addArticle").data('bootstrapValidator').resetForm(true);
                }
            });
        }
        else
            return;
    }

    function saveArticle() {
        let name = $("#saveClassifyName").val();
        let order = $("#saveOrder").val();
        let show = $(".isShow:checked").val();
            $.ajax({
                url:"/openArticleClassification/updateArticleClassify",
                method:"post",
                dataType:"json",
                data:{
                    aid:aid,
                    aname:name,
                    isShow:show,
                    aorder:order,
                    version:aversion
                },
                success:function (data) {
                    $("#saveArticleClassify").modal("hide");
                    layer.msg(data.msg);
                    refreshTableData();
                    $("#saveArticle").data('bootstrapValidator').resetForm(true);
                }
            });
    }

    function deleteArticle() {
        $.ajax({
            url:"/openArticleClassification/deleteArticleClassify",
            method:"post",
            dataType:"json",
            data:{
                aid:aid
            },
            success:function (data) {
                $("#deleteArticleClassify").modal("hide");
                layer.msg(data.msg);
                refreshTableData();
            }
        });
    }
    
    function openDeleteArticleModel(id) {
        aid = id;
        $("#deleteArticleClassify").modal("show");
    }

    $("#addArticle").submit(function (e) {
        e.preventDefault();
    });

    $("#saveArticle").submit(function (e) {
        e.preventDefault();
    });
</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#addArticle').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                classifyName: {
                    validators: {
                        notEmpty: {
                            message: '分类名称不能为空'
                        }
                    }
                },
                order: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp:{
                            regexp:/\d/,
                            message:"只能输入数字"
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>
