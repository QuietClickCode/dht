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
    <style type="text/css">
        #Client button{
            height: 34px;
            width: 110px;
        }

        .window{
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.8);
            position: fixed;
            top: 0;
            left:0;
            overflow: hidden;
            text-align: center;
            z-index: 9999;
            display: none;

        }
        .window img{
            width: auto;
            height: auto;
            position: relative;
            top: 50%;
            max-width: 90%;
            transform:translateY(-50%);
        }
        .window button{
            position: absolute;
            right: 0px;
            top:0px;
        }
    </style>
</head>
<body>
<div id="toolbar" class="form-inline">
    <button class="btn btn-default" type="button" onclick="addNavigationBar()">新增首页广告</button>
    <input type="text" class="form-control"  id="AdvertisingName" placeholder="请输入广告名称">
    <select id="clientMenu" class="form-control">
        <option value="">客户端</option>
        <option value="0">移动端</option>
        <option disabled="disabled" style="cursor: no-drop" value="1">PC端</option>
        <option disabled="disabled" style="cursor: no-drop" value="2">小程序</option>
    </select>

    <select id="regionMenu" class="form-control">
        <option value="">推送区域</option>
        <option value="0">顶部区域</option>
        <option value="1">中间区域</option>
        <option value="2">底部区域</option>
        <option value="3">特价广告</option>
    </select>

    <select id="countryMenu" class="form-control">
        <option value="">主推方向</option>
        <option value="0">乡村</option>
        <option value="1">城市</option>
        <option value="2">城市和乡村</option>
    </select>

    <div class="window">
        <img src="">
        <button type="button" class="btn btn-default" aria-label="Left Align">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </button>
    </div>

    <button class="btn btn-default" onclick="refreshTableData()">查询</button>
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
                <h4 class="modal-title">新增首页广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="saveHomeAdvertising">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="saveImage" name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveName" id="saveName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送区域</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="area" class="area" value="0">
                                    顶部区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="area" class="area" value="1">
                                    中间区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="area" class="area" value="2">
                                    底部区域
                                </label>
                            </div>

                            <div class="radio">
                                <label>
                                    <input type="radio" name="area" class="area" value="3">
                                    特价广告
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="0">
                                    乡村
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="1">
                                    城市
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <div class="radio">
                            <label>
                                <input type="radio" name="setClient" checked class="setClient" value="0">
                                移动端
                            </label>
                        </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setClient" disabled="disabled" class="setClient" value="1">
                                    PC端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setClient" disabled="disabled" class="setClient" value="2">
                                    小程序
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="saveOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveUrl" id="saveUrl" class="form-control">
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
                            <img id="showImg" src="" style="width: 50px;height: 50px; display: inline-block">
                            <input id="uploadImage" style="display: inline-block;vertical-align: middle;" name="dht_image_upload" type="file"/>
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
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="0">
                                    顶部区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="1">
                                    中间区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="2">
                                    底部区域
                                </label>
                            </div>

                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="3">
                                    特价广告
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="updateCountry" class="updateCountry" value="0">
                                    乡村
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="updateCountry" class="updateCountry" value="1">
                                    城市
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="updateClient" checked class="updateClient" value="0">
                                    移动端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="1">
                                    PC端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="2">
                                    小程序
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="isShow" class="isShow" value="0">
                                    不显示
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="isShow" class="isShow" value="1">
                                    显示
                                </label>
                            </div>
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

<%--删除广告模态框--%>
<div class="modal fade" id="deleteAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                <button type="button" class="btn btn-primary"  onclick="deleteHomeAdvertising()">确定</button>
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
                rowDatas.set(row.haId,row);
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
            field: 'imageUrl',
            title: '图片',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.haId,row);
                let html = '<img style="width: 50px;height: 50px;" class="demo" onclick="event.stopPropagation();wtf(\''+row.imageUrl+'\')" src='+row.imageUrl+'>'
                return html;
            }
        },
        {
            title: '编辑',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.haId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveAdvertisingModal(\''+row.haId+'\',\''+row.imagePath+'\',\''+row.haName+'\',\''+row.haOrder+'\',\''+row.isShow+'\',\''+row.url+'\',\''+row.haClient+'\',\''+row.haCountry+'\',\''+row.haRegion+'\',\''+row.imageUrl+'\')">编辑</button>'
                return html;
            }
        },
        {
            field: 'hnCountry',
            title: '删除',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.haId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();deleteHomeAdv(\''+row.haId+'\')">删除</button>'
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
            haName:$("#AdvertisingName").val(),
            haClient:$("#clientMenu").val(),
            haCountry:$("#countryMenu").val(),
            haRegion:$("#regionMenu").val(),
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

    $("#uploadImage").change(function () {
        let path = $(this).val();
        $("#showImg").attr("src","");
        $("#showImg").attr("src",path);

        var r= new FileReader();
        f=document.getElementById('uploadImage').files[0];
        r.readAsDataURL(f);
        r.onload=function  (e) {
            document.getElementById('showImg').src=this.result;
        };
    });

    $("#Client button").click(function () {
        let clientValue = $(this).attr("data-clientValue");
        $("#clientValue").attr("value",clientValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });
    var haId;
    var imgpath;
    function openSaveAdvertisingModal(id,path,name,order,isShow,url,client,country,region,imageUrl) {
        $("#setAdvName").val(name);
        radioChoose(".region",region);
        radioChoose(".updateCountry",country);
        radioChoose(".updateClient",client);
        radioChoose(".isShow",isShow);
        $("#showImg").attr("src",imageUrl);
        $("#setAdvertUrl").val(url);
        $("#setAdvertOrder").val(order);
        haId = id;
        imgpath = path;
        $("#saveAdvertising").modal("show");
    }

    $("#chooseCountry input").click(function () {
        let countryValue = $(this).attr("data-Country");
        $("#countryValue").attr("value",countryValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });


    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }

    /*
     * 新增首页导航
     * */
    function addNavigationBar() {
        $("#addHomeNavigationBar").modal("show");
    }

    function subHomeAdvertisingChange() {
        let bootstrapValidator = $("#updateHomeAdvertising").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
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
        let country = $(".setCountry:checked").val();
        let client = $(".setClient:checked").val();
        let order = $("#saveOrder").val();
        let url = $("#saveUrl").val();
        let bootstrapValidator = $("#saveHomeAdvertising").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
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
                            $("#saveHomeAdvertising").data('bootstrapValidator').resetForm(true);
                        }
                    });
                }
            });
        }
    }

    function deleteHomeAdv(id) {
        $("#deleteAdvertising").modal("show");
        haId = id;
    }

    function deleteHomeAdvertising() {
        $.ajax({
             url:"/openHomeAdvertising/removeAdvertising",
             method:"post",
             dataType:"json",
             data:{
             haId:haId
             },
         success:function (data) {
             $("#deleteAdvertising").modal("hide");
             layer.msg(data.msg);
             refreshTableData();
            }
         });
    }

    function wtf(url) {
        $(".window img").attr("src",url);
        $(".window").show();
    }
</script>

<script>
    $(".window").click(function(){
        $(this).hide();
    });
</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#saveHomeAdvertising').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                saveName: {
                    validators: {
                        notEmpty: {
                            message: '分类名称不能为空'
                        }
                    }
                },
                saveOrder: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp:{
                            regexp:/\d/,
                            message:"只能输入数字"
                        }
                    }
                },
                area: {
                    validators: {
                        notEmpty: {
                            message: '请选择一个推送区域'
                        }
                    }
                },
                setCountry: {
                    validators: {
                        notEmpty: {
                            message: '请选择一个推送对象'
                        }
                    }
                },
                setClient: {
                    validators: {
                        notEmpty: {
                            message: '必须选择一个客户端对象'
                        }
                    }
                },
                saveUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        },
                        regexp: {
                            regexp: /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/,
                            message: "链接地址不合法"
                        }
                    }
                },
                dht_image_upload:{
                    validators: {
                        notEmpty: {
                            message: '文件不能为空'
                        }
                    }
                }
            }
        });


        $('#updateHomeAdvertising').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                setName: {
                    validators: {
                        notEmpty: {
                            message: '广告名称不能为空'
                        }
                    }
                },
                setOrder: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp:{
                            regexp:/\d/,
                            message:"只能输入数字"
                        }
                    }
                },
                setUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        },
                        regexp: {
                            regexp: /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/,
                            message: "链接地址不合法"
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>
