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

    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>

</head>
<body>
<script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
<script>
//实例化编辑器
var o_ueditorupload = UE.getEditor('j_ueditorupload',
    {
        autoHeightEnabled:false
    });
o_ueditorupload.ready(function ()
{

    o_ueditorupload.hide();//隐藏编辑器

    //监听图片上传
    o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
    {
        console.log(arg);
        console.log(t);
        $('#ggImgpath').val(arg[0].alt);
        $('#goodsClassificationImg')[0].src=arg[0].src;
        $('#editGoodsClassification').modal('show');
        //alert('这是图片地址：'+arg[0].src);
    });

    /* 文件上传监听
     * 需要在ueditor.all.min.js文件中找到
     * d.execCommand("insertHtml",l)
     * 之后插入d.fireEvent('afterUpfile',b)
     */
    o_ueditorupload.addListener('afterUpfile', function (t, arg)
    {
        console.log(arg)
        console.log(t)
        alert('这是文件地址：'+arg[0].url);
    });
});

//弹出图片上传的对话框
function upImage()
{

    var myImage = o_ueditorupload.getDialog("insertimage");
    myImage.open();
    $('#edui_fixedlayer').css("z-index","10000000");
}
//弹出文件上传的对话框
function upFiles()
{
    var myFiles = o_ueditorupload.getDialog("attachment");
    myFiles.open();
}

//重写图片上传地址
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    //判断路径   这里是config.json 中设置执行上传的action名称
    console.log(action)
    if (action == 'uploadimage') {
        //return 'http://localhost:8080/file/imageUpload?type=goods&isWatermark=true&isCompress=false';
        return ueditorUploadUrl("goods",false,false);
        //上传视频
    } else if (action == 'uploadvideo') {
        return '';
    } else {
        return this._bkGetActionUrl.call(this, action);
    }
}
</script>


<div id="toolbar"  class="form-inline" style="margin-bottom: 5px">
    <ex:perm url="goods/addGoodsClassification">
        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#addFloorModal">新增楼层</button>
    </ex:perm>
</div>
<div>
    <table id="goodsClassificationTable" ></table>
</div>

<%--添加楼层的模态框--%>
<div class="modal fade bs-example-modal-lg" id="addFloorModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myFloorModalLabel">添加子楼层</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="FloorTiele" class="col-sm-3 control-label">楼层名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="FloorTiele" placeholder="楼层名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">是否成为顶级元素</label>
                        <div class="col-sm-9">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="inlineCheckbox1" value="option1">
                                <span>选中上级分类将消失</span>
                            </label>
                        </div>
                    </div>

                    <div class="form-group dropdown">
                        <label for="" class="col-sm-3 control-label">上级分类</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="parentId" name="parentId"/>
                            <input type="text" class="form-control" aria-label="..." id="parentNm" name="parentNm" onclick="showMenu(); return false;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">排序</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="" placeholder="排序">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

<%--编辑该楼层--%>
<div class="modal fade" id="updateFloors" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">修改楼层</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="thisFloorTitle" class="col-sm-2 control-label">楼层标题</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" id="thisFloorTitle" placeholder="更改楼层标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="thisFloorOrder" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="thisFloorOrder" placeholder="排序">
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="subupdateFloors()">确定</button>
            </div>
        </div>
    </div>
</div>

    <%--删除确认框--%>
    <div class="modal fade" id="removeThisFloor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="">确认删除</h4>
                </div>
                <div class="modal-body">
                    <p>你确定删除选中的商品子类吗，删除后不可在恢复</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="deleteFloor()">确定</button>
                </div>
            </div>
        </div>
    </div>

<div id="GoodsClassificationContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="GoodsClassificationDemo" class="ztree" style="margin-top:0; width:320px;">
        <li style="margin-top: 5px" onclick="initGoodsClassification(id)">
            <a>
                <span>aaa</span>
            </a>
        </li>
        <li style="margin-top: 5px">2</li>
    </ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<!-- 图标选择器-->
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker-iconset-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/jquery.treegrid.extension.js"></script>
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
            url:"/floorManage/queryFloorsLists",
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
            treeId:"flId",
            treeField:"flName",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "flId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            }, {
                field: 'flId',
                align : 'center',
                valign : 'middle',
                title: 'ID'

            },{
                field: 'flName',
                align : 'center',
                valign : 'middle',
                title: '名称'

            },{
                field: 'flOrder',
                align : 'center',
                valign : 'middle',
                title: '排序'

            },
            {
                field: 'isShow',
                align : 'center',
                valign : 'middle',
                title: '是否显示',
                formatter:function (value,row,index) {
                    rowDatas.set(row.flId,row);
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
                field: 'isShow',
                align : 'center',
                valign : 'middle',
                title: '操作',
                formatter:function (value,row,index) {
                    rowDatas.set(row.flId,row);
                    let html='';
                    <ex:perm url="floorManage/updateFloor">
                    html+='<button class="btn btn-primary" onclick="event.stopPropagation();updateData(\''+row.flId+'\',\''+row.flName+'\',\''+row.flOrder+'\',\''+row.isShow+'\')" style="margin-right: 6px">编辑</button>' +
                        '<button class="btn btn-primary" onclick="event.stopPropagation();deleteData(\''+row.flId+'\')">删除</button>'
                    </ex:perm>
                    return html;
                }

            }]
        });

        //初始化选择器开关
        $("#editGoodsClassificationForm #isTop").bootstrapSwitch();


        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            var formData=$("#editGoodsClassificationForm").serializeObject();

            var flag =$("#editGoodsClassificationForm #isTop").bootstrapSwitch("state");
            if(flag){
                formData["isTop"]=1;
            }else{
                formData["isTop"]=0;
            }
            var url='';
            if(editorGoodsClassificationType==0){
                url='/goods/addGoodsClassification'
            }else{
                url='/goods/editGoodsClassification';
            }

            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0){
                        //显示提示
                        layer.msg(data.msg);
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editGoodsClassification').modal('hide')
                    }
                }
            });
            clearFormValidation("editGoodsClassificationForm",formValidater);
        });
        //初始华开关选择器
        $("#editGoodsClassificationForm #isValid").bootstrapSwitch();
        $('#editGoodsClassification').on('hide.bs.modal', function () {
            //清除数据
            //clearFormData();
        })

        formValidater();
    });

    /*/!**
     * 刷新表格数据
     **!/

    }*/

    var flid;

    function showParentMenu() {
        layer.msg("Test");
    }

    /*
    * 显示删除楼层的模态框
    * */
    function deleteData(id) {
        flid = id;
        $("#removeThisFloor").modal('toggle');
    }


    /*
    * 删除楼层
    * */
    function deleteFloor() {
        $.ajax({
            url:"/floorManage/deleteFloor",
            method:"post",
            dataType:"json",
            data:{
                flId:flid
            },
            success:function (data) {
                refreshTableData();
                bootbox.alert("删除成功");
            },
            error:function () {
                
            }
        });
    }

    /*
    * 调用编辑楼层的模态框
    * */
    function updateData(id,name,order,isShow) {
        flid = id;
        $("#thisFloorTitle").val(name);
        $("#thisFloorOrder").val(order);
        if(isShow == 1)
            $(".isShow")[0].checked = true;
        else
            $(".isShow")[1].checked = true;
        $("#updateFloors").modal('toggle');
    }

    /*
    * 编辑楼层
    * */
    function subupdateFloors() {
        let title = $("#thisFloorTitle").val();
        let order = $("#thisFloorOrder").val();
        let show = $(".isShow:checked").val();
        $.ajax({
            url:"/floorManage/updateFloor",
            method:"post",
            data:{
                flId:flid,
                flName:title,
                flOrder:order,
                isShow:show,
                version:1
            },
            dataType:"json",
            success:function (data) {
                layer.msg(data.msg);
                refreshTableData();
            },
            error:function () {

            }
        });
    }


    /*
    * 显示新增楼层的模态框
    * */
    function  saveFloor(flid,parentId) {
        layer.msg(flid + " " + parentId);
        $("#addFloors").modal("toggle");
    }

    function refreshTableData() {
        $('#goodsClassificationTable').bootstrapTable(
            "refresh",
            {
                url:"/floorManage/queryFloorsLists"
            }
        );
    }



    /***********************************************************************************/
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
        var parentNm = $("#parentNm");
        var parentId_ = $("#parentId");
        parentNm.val(v);
        parentId_.val(vId);
    }

    function showMenu() {
        var cityObj = $("#parentNm");
        var cityOffset = $("#parentNm").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function showGoodsClassification() {
        var cityObj = $("#ggHomeNm");
        var cityOffset = $("#ggHomeNm").offset();
        $("#GoodsClassificationContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideGoodsClassification() {
        $("#GoodsClassificationContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }

        if (!(event.target.id == "menuBtn" || event.target.id == "GoodsClassificationContent" || $(event.target).parents("#GoodsClassificationContent").length>0)) {
            hideGoodsClassification();
        }
    }

</script>
<script>
    $(function () {
        $("#isTopSwitch span").click(function () {

            var flag =$("#editGoodsClassificationForm #isTop").bootstrapSwitch("state");

            if(flag){
                $("#parentNmElement").hide();
            }else{
                $("#parentNmElement").show();
            }
        });
    });
</script>


</body>
</html>
