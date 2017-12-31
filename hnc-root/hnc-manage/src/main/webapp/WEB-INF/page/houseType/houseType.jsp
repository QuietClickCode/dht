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

        .houseTypeImg{
            width: 50px;
            height: 50px;
        }

        .houseTypeImage{
            width: 40px;
            height: 40px;
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

<%--修改户型--%>
<div class="modal fade" id="editHouseType" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑户型信息</h4>
            </div>
            <div class="modal-body">
                <form id="updateHouseType">
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

                    <div class="form-group">
                        <label>是否主推</label>
                        <div class="radio">
                            <label>
                                <input type="radio" class="htRecommend" name="htRecommend" value="0">非主推
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" class="htRecommend" name="htRecommend" value="1">主推
                            </label>
                        </div>
                    </div>

                    <div class="form-group" style="display: none;">
                        <label>户型ID:</label>
                        <input type="text" class="form-control" name="htId" placeholder="楼栋ID" readonly>
                    </div>
                    <div class="form-group">
                        <label>户型名称:</label>
                        <input type="text" class="form-control" name="htTypeName" placeholder="户型名称">
                    </div>
                    <div class="form-group">
                        <label>房屋类型:</label>
                        <input type="text" class="form-control" name="htType" placeholder="房屋类型">
                    </div>
                    <div class="form-group">
                        <label>户型面积:</label>
                        <input type="text" class="form-control" name="htArea" placeholder="户型面积">
                    </div>
                    <div class="form-group">
                        <label>户型描述:</label>
                        <textarea style="outline:none;resize:none;height: 200px;" name="htInfo" class="form-control" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subEditHouseType">保存</button>
            </div>
        </div>
    </div>
</div>

<%--删除户型模态框--%>
<div class="modal fade" id="deleteHouseType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除楼栋</h4>
            </div>
            <div class="modal-body">
                <p>是否删除该户型</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default editImage" data-dismiss="modal" style="display: none;">编辑</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary deleteHouseType">确定</button>
            </div>
        </div>
    </div>
</div>


<%--查看户型效果图--%>
<div class="modal fade" id="editHouseTypeImg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看户型效果图</h4>
            </div>
            <div class="modal-body">
                <form id="uploadFileForm" class="form-inline">
                    <div class="form-group" style="display: none;">
                        <label >请选择图片</label>
                        <input type="file" name="dht_image_upload" id="filed">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary chooseImg">选择图片</button>
                    </div>

                    <div class="form-group houseTypeImgBox" style="display: none;">
                        <button type="button" class="btn btn-primary houseTypeImg">选择图片</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary subEditHouseTypeImg">确定</button>
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
            title: '是否显示',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                if(row.isShow == 0){
                    return "不显示";
                }else if (row.isShow == 1){
                    return "显示";
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
                let html = "";
                if(row.htImage == null)
                    html += '<button class="btn btn-primary" onclick="event.stopPropagation();editHouseTypeImg(\''+row.htId+'\')">添加户型效果图</button>';
                else
                    html += '<img class="houseTypeImage" src="'+row.imagePath+'">';
                return html;
            }
        },
        {
            title: '楼栋分布',
            align : 'center',
            valign : 'middle',
            class:'house_type',
            formatter:function (value,row,index) {
                let html = "";
                if(row.floorManages.length == 0)
                    return '<button class="btn btn-primary" onclick="event.stopPropagation();addFloorManage(\''+row.htId+'\')">添加楼栋</button>';
                else
                    for(let i = 0;i<row.floorManages.length;i++){
                        html += ''+row.floorManages[i].fmName+' '
                    }
                return html;
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
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editHouseType(\''+row.htId+'\')">编辑</button>'
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
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();deleteHouseType(\''+row.htId+'\')">删除</button>'
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

    /*提交新增户型*/
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

<%--修改户型--%>
<script>

    var houseType;
    /*打开修改户型模态框*/
    function editHouseType(id) {
        houseType = rowDatas.get(id);
        $("#updateHouseType input[type='text']").each(function () {
            let name = $(this).attr("name");
            $(this).val(houseType[name]);
        });
        radioChoose(".isShow",houseType['isShow']);
        radioChoose(".htRecommend",houseType['htRecommend']);
        $("#updateHouseType textarea").val(houseType['htInfo']);
        $("#editHouseType").modal("show");
    }

    /*提交户型修改信息*/
    $(".subEditHouseType").click(function () {
        let bootstrapValidator = $("#updateHouseType").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave) {
            isSave = true;
            var floor = document.getElementById("updateHouseType");
            var data = new FormData(floor);
            $.ajax({
                url:"/houseManage/updateHouseType",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    $("#editHouseType").modal("hide");
                    $("#updateHouseType").data('bootstrapValidator').resetForm(true);
                    refreshTableData();
                }
            });
        }
    });
</script>

<%--删除该户型--%>
<script>
    /*打开删除户型模态框*/
    function deleteHouseType(id) {
        houseType = rowDatas.get(id);
        $("#deleteHouseType").modal("show");
    }

    $(".deleteHouseType").click(function () {
        if(!isSave){
            isSave = true;
            $.ajax({
                url:"/houseManage/removeHouseType",
                method:"post",
                data:{
                    htId:houseType['htId']
                },
                dataType:"json",
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#deleteHouseType").modal("hide");
                }
            });
        }
    });
</script>

<%--添加修改与户型绑定的楼栋--%>
<script>
    function addFloorManage(id) {
        houseType = rowDatas.get(id);
        for(let i = 0;i<houseType.floorManages.length;i++){
            floorManagesMap.set(houseType.floorManages[i].fmId,houseType.htId);
            $("#house_type_list").append("<li id='"+houseType.floorManages[i].fmId+"'>"+houseType.floorManages[i].fmName+"<span class='remove_icon'></span></li>");
        }
        $("#house_type_table").bootstrapTable('destroy');
        createFloorManageTable();
        $("#saveFloorManage").modal("show");
    }

    var relationships = new Array();
    $(".addFloorRelationship").click(function () {
        for (let key of floorManagesMap.keys()) {
            var floorRe = new Object();
            floorRe['hrId'] = key;
            floorRe['htId'] = floorManagesMap.get(key);
            relationships.push(floorRe);
        }

        $.ajax({
            url:"/houseManage/addFloorRelationship",
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

    $("#saveFloorManage").on("hidden.bs.modal",function () {
        $("#house_type_list").children().remove();
        for (let key of floorManagesMap.keys()) {
            console.log(key+" "+floorManagesMap.get(key));
        }
        floorManagesMap.clear();
        $("#house_type_list").html("");
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

<%--添加户型效果图--%>
<script>
    function editHouseTypeImg(id) {
        houseType = rowDatas.get(id);
        $("#editHouseTypeImg").modal("show");
    }

    $(".subEditHouseTypeImg").click(function () {
        var fd = new FormData($("#uploadFileForm")[0]);
        fd.append("imageUse","image/jpeg");
        fd.append("isWatermark","false");
        fd.append("isCompress", "false");
        $.ajax({
            url:"/file/imageUpload",
            type:"post",
            dataType:"json",
            data: fd,
            processData : false,
            contentType : false,
            success:function (data) {
                addHouseTypeImg(data.original);
            }
        })
    });
    
    function addHouseTypeImg(original) {
        $.ajax({
            url:"/houseManage/updateHouseType",
            method:"post",
            data:{
                htId:houseType['htId'],
                htImage:original
            },
            dataType:"json",
            success:function (data) {
                layer.msg(data.msg);
                $("#editHouseTypeImg").modal("hide");
                refreshTableData();
            }
        });
    }
    
    $("#filed").change(function () {
        var file = $('#filed').get(0).files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function(e){
            console.log(e);
            $('.houseTypeImg').get(0).src = e.target.result;
        }
        $(".houseTypeImgBox").show();
    });

    $(".chooseImg").click(function () {
        $("#filed").click();
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

<script>
    var floorManagesMap = new Map();
    function createFloorManageTable(){
        //表格的初始化
        $("#house_type_table").bootstrapTable({
            url:"/floorManage/queryFloorList",
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
            uniqueId: "fmId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType: "json",
            columns: treeFloorColumns,
            onCheckAll:function(rows){
                for(var row of rows){
                    if(!floorManagesMap.has(row.fmId)){
                        floorManagesMap.set(row.fmId,houseType.htId);
                        $("#house_type_list").append("<li id='"+row.fmId+"'>"+row.fmName+"<span class='remove_icon'></span></li>");
                    }
                }
            },
            //点击每一个单选框时触发的操作
            onCheck:function(row){
                //判断是否己经存在
                if(!floorManagesMap.has(row.fmId)){
                    floorManagesMap.set(row.fmId,houseType.htId);
                    $("#house_type_list").append("<li id='"+row.fmId+"'>"+row.fmName+"<span class='remove_icon'></span></li>");
                }
            },
            //取消每一个单选框时对应的操作；
            onUncheck:function(row){
                //判断是否己经存在
                if(floorManagesMap.has(row.fmId)){
                    floorManagesMap.delete(row.fmId);
                    console.log(floorManagesMap.size);
                    $("#house_type_list #"+row.fmId).remove();
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
            fmName:$(".fmName").val(),
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
                url:"/floorManage/queryFloorList"
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
        }
    ]

    function commonFloorSelectCheckFormatter(value, row, index) {
        let curId = row.fmId;
        for(let i = 0;i<houseType.floorManages.length;i++){
            if(curId == houseType.floorManages[i].fmId){
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


    $('#updateHouseType').bootstrapValidator({
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
</body>
</html>
