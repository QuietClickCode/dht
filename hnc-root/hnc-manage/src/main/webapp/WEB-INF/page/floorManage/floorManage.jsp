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
            height: 500px;
            overflow-y: scroll;
        }

        .house_type_list{
            float: left;
            width: 20%;
            height: 500px;
            border-left: none;
        }

        #house_type_list{
            padding-left: 20px;
            list-style: none;
            position: relative;
        }

        #house_type_list li{
            list-style: none;
            text-align: center;
            display: block;
            height: 30px;
            line-height: 30px;
            border: 1px solid rgba(0,0,0,0.1);
            margin-bottom: 10px;
            border-radius: 5px;
            position: relative;
        }

        .remove_icon{
            width: 15px;
            height: 15px;
            right: -5;
            top: -5;
            cursor: pointer;
            position: absolute;
            display: block;
            background: url("/img/remove_icon.png") no-repeat;
            background-size: 15px 15px;
        }
    </style>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default saveFloor" type="button">新增楼栋</button>
        <input type="text" class="form-control fmName"  placeholder="请输入楼栋名称">
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


<div class="modal fade" id="editFloor" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑楼栋</h4>
            </div>
            <div class="modal-body">
                <form id="updateFloor">
                    <div class="form-group">
                        <label>是否显示:</label>
                        <div class="radio">
                            <label>
                                <input type="radio" class="isShow" name="isShow" value="1">显示
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" class="isShow" name="isShow" value="0">不显示
                            </label>
                        </div>
                    </div>
                    <div class="form-group" style="display: none;">
                        <label>ID:</label>
                        <input type="text" class="form-control" name="fmId" placeholder="楼栋ID" readonly>
                    </div>
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
                <button type="button" class="btn btn-primary updateFloor">保存</button>
            </div>
        </div>
    </div>
</div>


<%--删除楼栋模态框--%>
<div class="modal fade" id="deleteFloor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除楼栋</h4>
            </div>
            <div class="modal-body">
                <p>删除该楼栋</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary deleteFloor">确定</button>
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
                <div class="house_type_list">
                    <ul id="house_type_list">

                    </ul>
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
            field: 'fmInfo',
            title: '备注',
            width:'200px',
            class:'fmInfo',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.fmInfo == null){
                    return "";
                }

                if(row.fmInfo.length > 8){
                    return row.fmInfo.substring(0,8) + '...';
                }

                return row.fmInfo;
            }
        },
        {
            title: '户型',
            align : 'center',
            valign : 'middle',
            class:'house_type',
            formatter:function (value,row,index) {
                let html = "";
                if(row.typeManages.length == 0)
                    return '<button class="btn btn-primary" onclick="event.stopPropagation();addHouseTypeManage(\'' + row.fmId + '\')">添加户型</button>';
                else{
                    for(let i = 0;i<row.typeManages.length;i++){
                        html +=''+row.typeManages[i].htTypeName+' '
                    }
                }
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(''+row.fmId+'',row);
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editFloor(\''+row.fmId+'\')">编辑</button>'
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
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openDeleteFloorModal(\''+row.fmId+'\')">删除</button>'
                </ex:perm>
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
            fmName:$(".fmName").val(),
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
    /*防止重复提交标记*/
    var flag = false;
    /*打开添加楼栋信息模态框*/
    $(".saveFloor").click(function () {
        $("#addFloor").modal("show");
    });
    /*提交新建楼栋信息*/
    $(".addFloor").click(function () {
        let bootstrapValidator = $("#saveFloor").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!flag){
            flag=true;
            var floor = document.getElementById("saveFloor");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            data.append("isShow", 1);
            $.ajax({
                url:"/floorManage/addFloor",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    flag = false;
                    refreshTableData();
                    $("#addFloor").modal("hide");
                    $("#saveFloor").data('bootstrapValidator').resetForm(true);
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

<%--编辑楼栋信息--%>
<script>
    /*防止重复提交*/
    var isSave = false;
    /*当前的楼栋对象*/
    var f;
    /*打开编辑楼栋模态框*/
    function editFloor(fmId) {
        f = rowDatas.get(fmId);
        $("#updateFloor input[type='text']").each(function () {
            let name = $(this).attr("name");
            $(this).val(f[name]);
        });
        radioChoose(".isShow",f['isShow']);
        $("#updateFloor textarea").val(f['fmInfo']);
        $("#editFloor").modal("show");
    }

    /*提交修改的楼栋信息*/
    $(".updateFloor").click(function () {
        let bootstrapValidator = $("#updateFloor").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave = true;
            var floor = document.getElementById("updateFloor");
            var data = new FormData(floor);
            $.ajax({
                url:"/floorManage/updateFloor",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    $("#editFloor").modal("hide");
                    $("#updateFloor").data('bootstrapValidator').resetForm(true);
                    refreshTableData();
                }
            });
        }
    });
</script>

<script>
    <%--打开删除楼栋模态框--%>
    function openDeleteFloorModal(id) {
        f = rowDatas.get(id);
        $("#deleteFloor").modal("show");
    }

    var delflag = false;
    $(".deleteFloor").click(function () {
        if(!delflag){
            delflag = true;
            $.ajax({
                url:"/floorManage/removeFloor",
                method:"post",
                data:{
                    fmId:f['fmId']
                },
                dataType:"json",
                success:function (data) {
                    delflag = false;
                    refreshTableData();
                    $("#deleteFloor").modal("hide");
                }
            });
        }
    });
</script>

<%--添加修改与楼栋绑定的户型--%>
<script>
    var floorManage;
    /*打开模态框并显示与该楼栋所关联的户型*/
    function addHouseTypeManage(id) {
        floorManage = rowDatas.get(id);
        for(let i = 0;i<floorManage.typeManages.length;i++){
            floorManagesMap.set(floorManage.typeManages[i].htId,houseType.fmId);
            $("#house_type_list").append("<li id='"+floorManage.typeManages[i].htId+"'>"+floorManage.typeManages[i].htTypeName+"<span class='remove_icon'></span></li>");
        }
        $("#house_type_table").bootstrapTable('destroy');
        createFloorManageTable();
        $("#saveFloorManage").modal("show");
    }

    /*绑定楼栋所关联的户型*/
    var relationships = new Array();
    $(".addFloorRelationship").click(function () {
        for (let key of floorManagesMap.keys()) {
            var floorRe = new Object();
            floorRe['frId'] = key;
            floorRe['fmId'] = floorManagesMap.get(key);
            relationships.push(floorRe);
        }

        $.ajax({
            url:"/floorManage/addHouseTypeRelationship",
            type:"post",
            contentType: "application/json",
            data:JSON.stringify(relationships),
            success:function () {
                $("#saveFloorManage").modal("hide");
                refreshTableData();
                floorManagesMap.clear();
                relationships.length = 0;
            }
        });
    });

    /*关闭模态框时清空缓存*/
    $("#saveFloorManage").on("hidden.bs.modal",function () {
        $("#house_type_list").children().remove();
        floorManagesMap.clear();
        $("#house_type_list").html("");
        relationships.length = 0;
    });

    $('#house_type_list').on('click', '.remove_icon', function(){
        let id = $(this).parent().attr("id");
        $(this).parent().remove();
        $("#house_type_table tbody .selected").each(function () {
            let gid = $(this).attr("data-uniqueid");
            if(id == gid){
                $(this).removeClass("selected");
                $(this).find(":input[name='parentItem']").removeAttr("checked");
                $(".bs-checkbox .th-inner input").removeAttr("checked");
            }
        });
        for(var key of floorManagesMap.keys()){
            if(key == id){
                floorManagesMap.delete(key);
                console.log(floorManagesMap.size);
            }
        }
    })
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
            onCheckAll:function(rows){
                for(var row of rows){
                    if(!floorManagesMap.has(row.htId)){
                        floorManagesMap.set(row.htId,floorManage.fmId);
                        $("#house_type_list").append("<li id='"+row.htId+"'>"+row.htTypeName+"<span class='remove_icon'></span></li>");
                    }
                }
            },
            //点击每一个单选框时触发的操作
            onCheck:function(row){
                //判断是否己经存在
                if(!floorManagesMap.has(row.htId)){
                    floorManagesMap.set(row.htId,floorManage.fmId);
                    $("#house_type_list").append("<li id='"+row.htId+"'>"+row.htTypeName+"<span class='remove_icon'></span></li>");
                }
            },
            //取消每一个单选框时对应的操作；
            onUncheck:function(row){
                //判断是否己经存在
                if(floorManagesMap.has(row.htId)){
                    floorManagesMap.delete(row.htId);
                    $("#house_type_list #"+row.htId).remove();
                }
            },
            onUncheckAll:function(rows){
                floorManagesMap.clear();
                $("#house_type_list").html("");
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
                url:"/houseManage/queryHouseType"
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
        let curId = row.htId;
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
    $('#saveFloor').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            fmName: {
                validators: {
                    notEmpty: {
                        message: '楼栋名称不能为空'
                    }
                }
            },
            fmType: {
                validators: {
                    notEmpty: {
                        message: '物业性质不能为空'
                    }
                }
            },
            fmQuantity: {
                validators: {
                    notEmpty: {
                        message: '房屋套数不能为空'
                    },
                    regexp: {
                        regexp: /\d/,
                        message: "只能输入数字"
                    }
                }
            },
            fmInfo: {
                validators: {
                    notEmpty: {
                        message: '备注不能为空'
                    }
                }
            }
        }
    });


    $('#updateFloor').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            isShow: {
                validators: {
                    notEmpty: {
                        message: '必须选择一个'
                    }
                }
            },
            fmName: {
                validators: {
                    notEmpty: {
                        message: '楼栋名称不能为空'
                    }
                }
            },
            fmType: {
                validators: {
                    notEmpty: {
                        message: '物业性质不能为空'
                    }
                }
            },
            fmQuantity: {
                validators: {
                    notEmpty: {
                        message: '房屋套数不能为空'
                    },
                    regexp: {
                        regexp: /\d/,
                        message: "只能输入数字"
                    }
                }
            },
            fmInfo: {
                validators: {
                    notEmpty: {
                        message: '备注不能为空'
                    }
                }
            }
        }
    });
</script>
</body>
</html>
