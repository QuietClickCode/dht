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

</div>
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
                <form class="form-horizontal" id="saveHomeAdvertising">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="saveImage" name="dht_image_upload" type="file"/>
                            <p class="help-block">不更改就不上传图片</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="saveName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送区域</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="area" class="area" value="1">顶部区域
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="area" class="area" value="0">中间区域
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="area" class="area" value="0">底部区域
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="setCountry" class="setCountry" value="1">乡村
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="setCountry" class="setCountry" value="0">城市
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="setClient" checked class="setClient" value="1">移动端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="setClient" disabled="disabled" class="setClient" value="0">PC端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="setClient" disabled="disabled" class="setClient" value="0">小程序
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" id="saveOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" id="saveUrl" class="form-control">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="saveAdvertising()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑导航--%>
<div class="modal fade" id="saveAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateHomeAdvertising">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="uploadImage" name="dht_image_upload" type="file"/>
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
                        <label for="" class="col-sm-2 control-label">推送区域</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="region" class="region" value="1">顶部区域
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="region" class="region" value="0">中间区域
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="region" class="region" value="0">底部区域
                            </label>
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
                <button type="submit" class="btn btn-primary" onclick="subHomeAdvertisingChange()">确定</button>
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
            field: 'haName',
            title: '广告名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'haOrder',
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
            field: 'url',
            title: '链接',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '编辑',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveAdvertisingModal(\''+row.haId+'\',\''+row.imagePath+'\')">编辑</button>'
                return html;
            }
        },
        {
            field: 'hnCountry',
            title: '删除',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openHomeAdvertising/queryAdvertisingLists","goodsTypeTables","haId",treeColumns,queryParams)
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
                url:"/openHomeAdvertising/queryAdvertisingLists"
            }
        );
    }

    $("#Client button").click(function () {
        let clientValue = $(this).attr("data-clientValue");
        $("#clientValue").attr("value",clientValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });
    var haId;
    var imgpath;
    function openSaveAdvertisingModal(id,path) {
        haId = id;
        imgpath = path;
        $("#saveAdvertising").modal("show");
    }

    $("#chooseCountry input").click(function () {
        let countryValue = $(this).attr("data-Country");
        $("#countryValue").attr("value",countryValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });


    /*
     * 新增首页导航
     * */
    function addNavigationBar() {
        $("#addHomeNavigationBar").modal("show");
    }

    function subHomeAdvertisingChange() {
        let name = $("#setAdvName").val();
        let region = $(".region:checked").val();
        let country = $(".updateCountry:checked").val();
        let client = $(".updateClient:checked").val();
        let isshow = $(".isShow:checked").val();
        let url = $("#setAdvertUrl").val();
        let order = $("#setAdvertOrder").val();
        let imageVal = $("#uploadImage").val();
        if(imageVal != ""){
            var fd = new FormData($("#updateHomeAdvertising")[0]);
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
                        url:"/openHomeAdvertising/updateAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            haId:haId,
                            haName:name,
                            haOrder:order,
                            isShow:isshow,
                            url:url,
                            imagePath:imagepath,
                            haClient:client,
                            haCountry:country,
                            haRegion:region,
                        },
                        success:function (data) {
                            layer.msg(data.msg);
                            refreshTableData();
                            $("#saveAdvertising").modal("hide");
                        }
                    });
                }
            });
        }else{
            $.ajax({
                url:"/openHomeAdvertising/updateAdvertising",
                method:"post",
                dataType:"json",
                data:{
                    haId:haId,
                    haName:name,
                    haOrder:order,
                    isShow:isshow,
                    url:url,
                    imagePath:imgpath,
                    haClient:client,
                    haCountry:country,
                    haRegion:region,
                },
                success:function (data) {
                    layer.msg(data.msg);
                    refreshTableData();
                    $("#saveAdvertising").modal("hide");
                }
            });
        }
    }
    
    function saveAdvertising() {
        let name = $("#saveName").val();
        let area = $(".area:checked").val();
        let country = $("#setCountry:checked").val();
        let client = $("#setClient:checked").val();
        let order = $("#saveOrder").val();
        let url = $("#saveUrl").val();

        let path = $("#saveImage").val();
        if(path != ""){
            var fd = new FormData($("#saveHomeAdvertising")[0]);
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
                        url:"/openHomeAdvertising/addAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            haName:name,
                            haOrder:order,
                            url:url,
                            imagePath:imagepath,
                            haClient:client,
                            haCountry:country,
                            haRegion:area,
                            isShow:1,
                            isDelete:0
                        },
                        success:function (data) {
                            layer.msg(data.msg);
                            refreshTableData();
                            $("#addHomeNavigationBar").modal("hide");
                        }
                    });
                }
            });
        }
    }



    /*$(function () {
        $('#updateHomeAdvertising').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                setName: {
                    message: '广告名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                setUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                },
                setOrder: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        }
                    }
                }
            }
        });
    });*/
</script>

</body>
</html>
