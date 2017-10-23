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
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/toast/css/toastr.css">

    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>

</head>
<body>
<div id="toolbar"  class="form-inline" style="margin-bottom: 5px">
    <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            客户端 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="#">移动端</a></li>
            <li><a href="#">PC端</a></li>
            <li><a href="#">小程序</a></li>
        </ul>
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            推送对象 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="#">乡村</a></li>
            <li><a href="#">城市</a></li>
        </ul>
    </div>

    <button class="btn btn-default">查询</button>
</div>
<div>
    <table id="goodsClassificationTable" ></table>
</div>

<%--添加楼层广告--%>
<div class="modal fade" id="saveFloorAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增楼层广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="" name="dht_image_upload" type="file"/>
                            <p class="help-block">不更改就不上传图片</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="setName" id="setAdvName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="updateCountry" class="updateCountry" value="1">乡村
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateCountry" class="updateCountry" value="0">城市
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" checked class="updateClient" value="1">移动端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="0">PC端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="0">小程序
                            </label>
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

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="setUrl" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="setOrder" id="" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑导航--%>
<div class="modal fade" id="updateAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该楼层广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateFloorAdvertising">
                    <div class="form-group">
                        <label for="updateImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="updateImage" name="dht_image_upload" type="file"/>
                            <p class="help-block">不更改就不上传图片</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="setName" id="AdvertisingName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="updateCountry" class="updateCountry" value="1">乡村
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateCountry" class="updateCountry" value="0">城市
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" checked class="updateClient" value="1">移动端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="0">PC端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="0">小程序
                            </label>
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

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="setUrl" id="setAdvertUrl" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="setOrder" id="setAdvertOrder" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="updateFloor()">确定</button>
            </div>
        </div>
    </div>
</div>

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
<script type="text/javascript">
    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    //用于缓存资源表格数据
    var rowDatas=new Map();
    $(function () {
        //表格的初始化
        $('#goodsClassificationTable').bootstrapTable({
            url:"/floorAdvertising/queryFloorAdvertisingList",
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: function (params) {
                return params;
            },                                  //传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
//            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            treeView: true,
            treeId:"faId",
            treeField:"faName",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "faId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            },{
                field: 'faName',
                align : 'left',
                valign : 'middle',
                title: '名称'

            },{
                field: 'faOrder',
                align : 'center',
                valign : 'middle',
                title: '排序'

            },{
                field: 'isShow',
                align : 'center',
                valign : 'middle',
                title: '是否显示',
                formatter:function (value,row,index) {
                    rowDatas.set(row.faId,row);
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
                },{
                align : 'center',
                valign : 'middle',
                title: '添加子项',
                formatter:function (value,row,index) {
                    rowDatas.set(row.faId,row);
                    let html='';
                    let parentId = row.parentId;
                    if(parentId == null)
                        html+='<button class="btn btn-default" type="button" onclick="event.stopPropagation();addFloorAdvertising()">添加子项</button>'
                    else
                        html + "";
                    return html;
                }
            },{
                align : 'center',
                valign : 'middle',
                title: '编辑',
                formatter:function (value,row,index) {
                    rowDatas.set(row.faId,row);
                    let html;
                    let parentId = row.parentId;
                    if(parentId != null)
                        html='<button class="btn btn-default" type="button" onclick="event.stopPropagation();updateFloorAdvertising(\''+row.faId+'\',\''+row.imageId+'\')">编辑</button>';
                    return html;
                }
            },{
                align : 'center',
                valign : 'middle',
                title: '删除',
                formatter:function (value,row,index) {
                    rowDatas.set(row.faId,row);
                    let html;
                    let parentId = row.parentId;
                    if(parentId != null)
                        html='<button class="btn btn-default" type="button" onclick="event.stopPropagation();">删除</button>';
                    return html;
                }
            }
            ]
        });
    });

    var faid;
    var imageId;
    /*打开编辑楼层广告模态框*/
    function updateFloorAdvertising(id,imgId) {
        faid = id;
        imageId = imgId;
        $("#updateAdvertising").modal("show");
    }

    /*编辑楼层广告*/
    function updateFloor() {
        let imgPath = $("#updateImage").val();
        let name = $("#AdvertisingName").val();
        let country = $(".updateCountry:checked").val();
        let client = $(".updateClient:checked").val();
        let isShow = $(".isShow:checked").val();
        let url = $("#setAdvertUrl").val();
        let order = $("#setAdvertOrder").val();
        if(imgPath != ""){
            var fd = new FormData($("#updateFloorAdvertising")[0]);
            fd.append("imageUse","image/jpeg");
            fd.append("isWatermark","false");
            fd.append("isCompress", "false");
            $.ajax({
                url:"/file/imageUpload",
                type:"post",
                data: fd,
                processData : false,
                contentType : false,
                success:function (data) {
                    var imagepath = JSON.parse(data).original;
                    $.ajax({
                        url:"/floorAdvertising/updateFloorAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            faId:faid,

                        },
                        success:function () {

                        }
                    });
                }
            });
        }

    }

    /*打开新增楼层广告模态框*/
    function addFloorAdvertising() {
        $("#saveFloorAdvertising").modal("show");
    }



</script>

<script>

    let Classify;
    let ClassifyName;

    var setting = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };

    function beforeClick(treeId, treeNode) {
        return true;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name;
            vId += nodes[i].id;
        }
        var floorClassifyName = $(ClassifyName);
        var floorClassify = $(Classify);
        floorClassifyName.val(v);
        floorClassify.val(vId);
    }

    var zNodes;
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    $.ajax({
        type:"post",
        url:'/floorManage/queryFloorManageNode',
        dataType: "json",
        data:{flId:-1,pageSize:1000,pageNo:1},
        async:false,
        success:function(data){
            let d=data.data;
            var nodeData=new Array();
            for(row of d){
                let treeRow=new Object();
                treeRow.id=row.flId;
                treeRow.pId=row.parentId;
                treeRow.name=row.flName;
                nodeData.push(treeRow);
            }
            var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
            var node = zTree.getNodeByParam("id",parentId);
            if(node){
                zTree.selectNode(node);
            }
        }
    });

    function initMenuContent() {
        $.ajax({
            type:"post",
            url:'/floorManage/queryFloorManageNode',
            dataType: "json",
            data:{flId:-1,pageSize:1000,pageNo:1},
            async:false,
            success:function(data){
                let d=data.data;
                var nodeData=new Array();
                for(row of d){
                    let treeRow=new Object();
                    treeRow.id=row.flId;
                    treeRow.pId=row.parentId;
                    treeRow.name=row.flName;
                    nodeData.push(treeRow);
                }
                var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
                var node = zTree.getNodeByParam("id",parentId);
                if(node){
                    zTree.selectNode(node);
                }
            }
        });
    }

    function showMenu(floorClassify,floorClassifyName) {
        Classify = floorClassify;
        ClassifyName = floorClassifyName;
        var cityObj = $(floorClassifyName);
        var cityOffset = $(floorClassifyName).offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }


</script>

<script>
    toastr.options = {

        closeButton: false,
        debug: false,
        progressBar: false,
        positionClass: "toast-bottom-center",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "2000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
</script>

</body>
</html>