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
    <style type="text/css">
        #Client button{
            height: 34px;
            width: 110px;
        }
    </style>
</head>
<body>
<div id="toolbar" class="form-inline">
    <button class="btn btn-primary" type="button" onclick="addNavigationBar()">新增首页导航</button>
    <div id="Client" style="display: inline-block">
        <button class="btn btn-success" data-clientValue="0">移动端</button>
        <button class="btn" data-clientValue="1" disabled="disabled">PC端</button>
        <button class="btn" data-clientValue="2" disabled="disabled">小程序</button>
        <input id="clientValue" style="display: none" value="0">
    </div>

    <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            主推样式 <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="#">带副标题的样式</a></li>
            <li><a href="#">不带副标题的样式</a></li>
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
    <table id="goodsTypeTables" ></table>
</div>
<%--新增首页导航--%>
<div class="modal fade" id="addHomeNavigationBar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该导航栏</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addHomeNavigation">
                    <div class="form-group">
                        <label for="addImagePath" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="addImagePath"  name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">导航名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="NavigationName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="client" checked class="client" value="0">移动端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="client" disabled="disabled" class="client" value="1">PC端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="client" disabled="disabled" class="client" value="2">小程序
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="country" class="country" value="0">乡村
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="country" class="country" value="1">城市
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">样式</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="setStyle" class="setStyle" value="1">带副标题的样式
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="setStyle" class="setStyle" value="0">不带副标题的样式
                            </label>
                        </div>
                    </div>

                    <div class="form-group" >
                        <label for="mainTitle" class="col-sm-2 control-label">主标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="mainTitle" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" id="subTitleModal">
                        <label for="subTitle" class="col-sm-2 control-label">副标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="subTitle" class="form-control">
                        </div>
                    </div>



                    <div class="form-group" id="">
                        <label for="NaviOrder" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" id="hnOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="NaviOrder" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" id="hnUrl" class="form-control">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="addHomeNavigation()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑导航--%>
<div class="modal fade" id="saveHomeNavigation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该导航栏</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateNavigationInfo">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="uploadImage" name="dht_image_upload" type="file"/>
                            <p class="help-block">不更改就不上传图片</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10" id="chooseCountry">
                            <input class="btn btn-default" data-Country="0" type="button" value="乡村">
                            <input class="btn btn-default" data-Country="1" type="button" value="城市">
                            <input id="countryValue" style="display: none">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="NaviName" class="col-sm-2 control-label">导航名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="NaviName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">样式</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="style" class="style" value="1">带副标题的样式
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="style" class="style" value="0">不带副标题的样式
                            </label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">主标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="inputMainTitle" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" id="updateSubtitle">
                        <label for="" class="col-sm-2 control-label">副标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="inputSubTitle" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" id="imageUrl" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" id="chooseClassifyLabel">
                        <label for="NaviOrder" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" id="NaviOrder" class="form-control">
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
                <button type="submit" class="btn btn-primary" onclick="updateNavigator()">确定</button>
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
            field: 'hnName',
            title: '导航名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'hnOrder',
            title: '排序',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'hnMianTitle',
            title: '主标题',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'hnSubTitle',
            title: '副标题',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'isShow',
            title: '状态',
            align : 'center',
            valign : 'middle',
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
            title: '编辑',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveNavigatorModal(\''+row.hnId+'\',\''+row.hnImgpath+'\')">编辑</button>'
                return html;
            }
        },
        {
            title: '删除',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();deleteHomeNavigation(\''+row.hnId+'\')">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openHomeNavigation/queryNavigationLists","goodsTypeTables","hnId",treeColumns,queryParams)
    });
    var hnid;
    var pathid;
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


    $("#Client button").click(function () {
        let clientValue = $(this).attr("data-clientValue");
        $("#clientValue").attr("value",clientValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });

    function openSaveNavigatorModal(id,imagePath) {
        hnid = id;
        pathid = imagePath;
        $("#saveHomeNavigation").modal("show");
    }

    $("#chooseCountry input").click(function () {
        let countryValue = $(this).attr("data-Country");
        $("#countryValue").attr("value",countryValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });


    function updateNavigator() {
        let clientValue = $("#clientValue").val();
        let countryValue = $("#countryValue").val();
        let naviName = $("#NaviName").val();
        let navOrder = $("#NaviOrder").val();
        let show = $(".isShow:checked").val();
        let imagePath = $("#uploadImage").val();
        let style = $(".style:checked").val();
        let mainTitle = $("#inputMainTitle").val();
        let subTitle = $("#subTitle").val()
        let url = $("#imageUrl").val();
        if(imagePath != ""){
            var fd = new FormData($("#updateNavigationInfo")[0]);
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
                        url:"/openHomeNavigation/updateNavigatorBar",
                        method:"post",
                        dataType:"json",
                        data:{
                            hnId:hnid,
                            hnClient:clientValue,
                            hnName:naviName,
                            hnOrder:navOrder,
                            isShow:show,
                            hnImgpath:imagepath,
                            hnCountry:countryValue,
                            hnStyle:style,
                            hnUrl:url,
                            hnMianTitle:mainTitle,
                            hnSubTitle:subTitle
                        },
                        success:function (data) {
                            refreshTableData();
                            $("#saveHomeNavigation").modal("hide");
                        }
                    });
                }
            });
        }else{
            $.ajax({
                url:"/openHomeNavigation/updateNavigatorBar",
                method:"post",
                dataType:"json",
                data:{
                    hnId:hnid,
                    hnClient:clientValue,
                    hnName:naviName,
                    hnOrder:navOrder,
                    isShow:show,
                    hnImgpath:pathid,
                    hnCountry:countryValue,
                    hnStyle:style,
                    hnUrl:url,
                    hnMianTitle:mainTitle,
                    hnSubTitle:subTitle
                },
                success:function (data) {
                    refreshTableData();
                    $("#saveHomeNavigation").modal("hide");
                }
            });
        }

    }

    /*
    * 新增首页导航
    * */
    function addNavigationBar() {
        $("#addHomeNavigationBar").modal("show");
    }

    /*
    * 上传图片
    * */
    function ajaxUploadFile(fromId,num) {

    }

    $(".style").click(function () {
        let style = $(".style:checked").val();
        if(style == 0)
            $("#updateSubtitle").hide();
        else
            $("#updateSubtitle").show();
    });


    $(".setStyle").click(function () {
        let style = $(".setStyle:checked").val();
        if(style == 0)
            $("#subTitleModal").hide();
        else
            $("#subTitleModal").show();
    });


    function deleteHomeNavigation(id) {
        $.ajax({
            url:"/openHomeNavigation/removeNavigatorBar",
            method:"post",
            dataType:"json",
            data:{
                hnId:id
            },
            success:function(data){
                refreshTableData();
                bootbox.alert("删除成功");
            }
        });
    }

    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/openHomeNavigation/queryNavigationLists"
            }
        );
    }
    
    function addHomeNavigation() {
        var fd = new FormData($("#addHomeNavigation")[0]);
        let name = $("#NavigationName").val();
        let client = $(".client:checked").val();
        let country = $(".country:checked").val();
        let style = $(".setStyle:checked").val();
        let maintitle = $("#mainTitle").val();
        let subtitle = $("#subTitle").val();
        let order = $("#hnOrder").val();
        let url = $("#hnUrl").val();

        if($("#addImagePath").val() == "") {
            layer.msg("请上传图片");
            return;
        }

        if(name == "") {
            layer.msg("请输入导航名称");
            return;
        }

        if(maintitle == ""){
            layer.msg("请输入主标题");
            return;
        }

        if(url == ""){
            layer.msg("请输入链接");
            return;
        }


        var fd = new FormData($("#addHomeNavigation")[0]);
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
                alert(imagepath);
                $.ajax({
                    url:"/openHomeNavigation/addNavigatorBar",
                    method:"post",
                    dataType:"json",
                    data:{
                        hnName:name,
                        hnOrder:order,
                        hnImgpath:imagepath,
                        hnCountry:country,
                        hnStyle:style,
                        hnUrl:url,
                        hnClient:client,
                        hnMianTitle:maintitle,
                        hnSubTitle:subtitle,
                        isDelete:0,
                        isShow:1
                    },
                    success:function (data) {
                        bootbox.alert(data.msg);
                        refreshTableData();
                        $("#addHomeNavigationBar").modal("hide");
                    }
                });
            },
            error:function(){

            }
        });

    }
</script>

</body>
</html>
