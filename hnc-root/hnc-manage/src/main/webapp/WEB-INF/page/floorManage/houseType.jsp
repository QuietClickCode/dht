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
        .house_type_table{
            float: left;
            width: 80%;
            height: auto;
        }

        .house_type_list{
            float: left;
            width: 20%;
            height: auto;
            border-left: none;
        }
    </style>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default saveHouseType" type="button">新增户型</button>
        <input type="text" class="form-control fmName"  placeholder="请输入楼栋名称">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>

</div>
<table id="goodsTypeTables" ></table>
</div>


<%--新增户型--%>
<div class="modal fade" id="saveHouseTypeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="saveHouseType">
                    <div class="form-group">
                        <label>是否主推</label>
                        <div class="radio">
                            <label>
                                <input type="radio" name="htRecommend" value="0">非主推
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" name="htRecommend" value="1">主推
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>户型名称</label>
                        <input type="text" class="form-control" name="htTypeName" placeholder="请输入户型名称">
                    </div>

                    <div class="form-group">
                        <label>户型</label>
                        <input type="text" class="form-control" name="htType" placeholder="请输入户型">
                    </div>

                    <div class="form-group">
                        <label>户型面积</label>
                        <input type="text" class="form-control" name="htArea" placeholder="请输入户型面积">
                    </div>

                    <div class="form-group">
                        <label>户型描述</label>
                        <textarea style="outline:none;resize:none;height: 200px;" name="htInfo" class="form-control" rows="3" placeholder="户型描述"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subSaveHouseType">确定</button>
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
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
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
            valign : 'middle'
        },
        {
            title: '是否主推',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.htRecommend == 0){
                    return "非主推类型";
                }else if (row.htRecommend == 1){
                    return "主推类型";
                }
            }
        },
        {
            field: 'fmInfo',
            title: '户型描述',
            width:'200px',
            class:'fmInfo',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.htInfo == null){
                    return "";
                }

                if(row.htInfo.length > 8){
                    return row.htInfo.substring(0,8) + '...';
                }

                return row.htInfo;
            }
        },
        {
            title: '户型效果图',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                    return '<button class="btn btn-primary" onclick="event.stopPropagation();">添加户型效果图</button>';
            }
        },
        {
            title: '楼栋分布',
            align : 'center',
            valign : 'middle',
            class:'house_type',
            formatter:function (value,row,index) {
                if(row.relationships.length == 0)
                    return '<button class="btn btn-primary" onclick="event.stopPropagation();">请添加楼栋</button>';
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(''+row.htId+'',row);
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
                let html='';
                <ex:perm url="floorManage/removeFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">删除</button>'
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/houseManage/queryHouseType","goodsTypeTables","htId",treeColumns,queryParams)
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
                url:"/houseManage/queryHouseType"
            }
        );
    }

</script>

<%--新增户型--%>
<script>
    /*防止重复提交标记*/
    var isSave = false;

    /*打开新增户型模态框*/
    $(".saveHouseType").click(function () {
        $("#saveHouseTypeModal").modal("show");
    });

    /*提交*/
    $(".subSaveHouseType").click(function () {
        let bootstrapValidator = $("#saveHouseType").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave=true;
            var floor = document.getElementById("saveHouseType");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            data.append("isShow", 1);
            $.ajax({
                url:"/houseManage/addHouseType",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#saveHouseTypeModal").modal("hide");
                    $("#saveHouseType").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>



<%--自定义方法--%>
<script>
    /*为单选框赋值*/
    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }
</script>


<%--表单校验--%>
<script type="text/javascript">
    $('#saveHouseType').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            htRecommend: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
            htTypeName: {
                validators: {
                    notEmpty: {
                        message: '户型名称不能为空'
                    }
                }
            },
            htType: {
                validators: {
                    notEmpty: {
                        message: '户型不能为空'
                    }
                }
            },
            htArea: {
                validators: {
                    notEmpty: {
                        message: '户型面积不能为空'
                    },
                    regexp: {
                        regexp: /\d/,
                        message: "只能输入数字"
                    }
                }
            },
            htInfo: {
                validators: {
                    notEmpty: {
                        message: '户型信息不能为空'
                    }
                }
            }
        }
    });
</script>

<script>
    /**
     * 初始化 树型表结构创建
     * @param url 请求数据的url
     * @param tableId 数据表格id
     * @param uniqueId 树型唯一值
     * @param columns 展示列表
     */
    function createHouseTypeTable(url,tableId,uniqueId,columns,searchParams,toolbarId){
        if(!toolbarId){
            toolbarId='#toolbar';
        }
        //表格的初始化
        $('#'+tableId).bootstrapTable({
            url:url,
            method: 'post',                      //请求方式（*）
            toolbar:toolbarId ,                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: false,                     //是否启用排序
            queryParams: function (params) {
                if(searchParams){
                    var params=searchParams(this);
                    return params;
                }else{
                    return params;
                }
            },                                  //传递参数（*）
            pagination:true,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: uniqueId,                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType: "json",
            columns: columns,
            contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
        });
    }
</script>
</body>
</html>
