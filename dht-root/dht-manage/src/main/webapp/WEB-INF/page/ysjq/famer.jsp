<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>农户管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" href="<%=path%>/js/toast/css/toastr.css">


    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
    <script src="<%=path%>/js/toast/js/toastr.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/common/form.js"> </script>


</head>
<body>
<%--UE设置--%>
<script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
<script>
    var newImgArr=new Array();
    var deleteArr=new Array();
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

        if(uploadImgFlag==1){
            for(var i=0; i<arg.length; i++){
                var imgdiv = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                    '<img src="'+arg[i].src+'" style="width: 100%;height: 100%;" >'+
                    '<div onclick="deleteGoodsImage(this,'+arg[i].alt+')" style="display: none; position: absolute;width: 100%;background-color: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                    '</div>';
                $('#uploadImgBtn').before(imgdiv);
                newImgArr.push(arg[i].alt);
            }
        }else if(uploadImgFlag==0){
            for(var i=0; i<arg.length; i++){
                var html = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                    '<img src="'+arg[i].src+'" style="width: 100%;height: 100%;" >'+
                    '<div onclick="deletegsgImage(this,'+arg[i].alt+')" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                    '</div>';
                $('#uploadGoodsSpecilgoodscredentialImgBtn').after(html);
//                addImgArr.push(arg[i].alt);
            }

        }



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
<%--内容--%>
<div id="toolbar" class="form-inline">
    <ex:perm url="/goods/addFamer">
        <button class="btn btn-primary" type="button" onclick="addFamer()" style="margin-bottom: 5px">添加农户</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_Famer_name" placeholder="请输入农户名称">
    </div>

    <ex:perm url="/goods/queryFamerLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="FamerTables" ></table>
</div>
<div class="modal fade" id="editorSysUser"  tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body" >
                <form id="cpImagesForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4" id="cpLogoDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        农户头像<span style="color: red;font-size: 20px">*</span>:
                                    </span>
                                <input type="file" id="dht_image_upload" name="dht_image_upload">
                            </div>
                        </div>
                        <div class="col-lg-4" id="clearCpLogoDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                        农户头像:
                                    </span>
                                <button class="btn btn-default" type="button" onclick="clearCpLogo()">清除</button>
                            </div>
                        </div>
                        <div class="col-lg-4" id="uploadImageDiv">
                            <div class="input-group form-group">
                                <img src="" id="uploadImage" width="96px;" height="48px;">
                            </div>
                        </div>
                    </div>
                </form>
                <form id="editorFamerForm">
                    <input type="hidden" name="fid" id="fid">
                    <input type="hidden" name="version" id="version">
                    <input type="hidden" name="fimg" id="fimg">
                    <input type="hidden" name="oldImg" id="oldImg">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-10 ">
                              <span class="input-group-addon">
                                农户名字<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fname" id="fname">

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-9">
                              <span class="input-group-addon">
                                性别:
                              </span>
                                <select  class="form-control"  id="fsex" name="fsex" >
                                    <option value="1">男</option>
                                    <option value="0">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-12">
                              <span class="input-group-addon">
                                出生日期<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fbirth" id="fbirth">
                            </div>
                        </div>


                        <div class="col-md-4">
                            <div class="input-group form-group col-md-10">
                              <span class="input-group-addon">
                                教育学历:
                              </span>
                                <input type="text" class="form-control" name="feducation" id="feducation">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-9">
                              <span class="input-group-addon">
                                民族:
                              </span>
                                <input type="text" class="form-control" name="fethnic" id="fethnic">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-12">
                              <span class="input-group-addon">
                                政治面貌:
                              </span>
                                <input type="text" class="form-control" name="fpolitical" id="fpolitical">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                电话号码<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fphone" id="fphone">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                身份证号<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fcardNumber" id="fcardNumber">
                            </div>
                        </div>


                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                户籍地址<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <div id="distpicker4">
                                    <div class="form-group col-md-3" style="margin: 0;padding: 0px;float: left">
                                        <label class="sr-only" for="province9">Province</label>
                                        <select class="form-control" id="province9"></select>
                                    </div>
                                    <div class="form-group col-md-9" style="margin: 0;float: left">
                                        <label class="sr-only" for="city9">City</label>
                                        <select class="form-control" id="city9"></select>
                                    </div>
                                    <div class="form-group col-md-9" style="margin: 0;float: left;">
                                        <label class="sr-only" for="district9">District</label>
                                        <select class="form-control" id="district9"></select>
                                    </div>
                                </div>
                                <input type="hidden" class="form-control" name="fcensus" id="fcensus">
                            </div>
                        </div>



                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                详细地址<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="faddress" id="faddress">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                邮编:
                              </span>
                                <input type="text" class="form-control" name="fpost" id="fpost">
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="input-group form-group col-md-8">
                              <span class="input-group-addon">
                                人口数:
                              </span>
                                <input type="text" class="form-control" name="fpopulation" id="fpopulation">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-10">
                              <span class="input-group-addon">
                                留守儿童数:
                              </span>
                                <input type="text" class="form-control" name="fleavechildnum" id="fleavechildnum">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-12">
                              <span class="input-group-addon">
                                家庭类型:
                              </span>
                                <input type="text" class="form-control" name="ffamilytype" id="ffamilytype">
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="input-group form-group col-md-8">
                              <span class="input-group-addon">
                                采集人<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fcollector" id="fcollector">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-10">
                              <span class="input-group-addon">
                                采集人职位<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fcollectorPosition" id="fcollectorPosition">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group form-group col-md-12">
                              <span class="input-group-addon">
                                采集人电话<span style="color: red;font-size: 20px">*</span>:
                              </span>
                                <input type="text" class="form-control" name="fcollectorPhone" id="fcollectorPhone">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                贫困户卡号:
                              </span>
                                <input type="text" class="form-control" name="fpoornum" id="fpoornum">
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-lg-1">
                            家庭情况<span style="color: red;font-size: 20px">*</span>:
                        </div>
                        <div class="col-lg-11">
                            <textarea id="fremark" name="fremark" class="form-control" style="width: 100%;height: 100px;resize:none;"></textarea>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
            </div>
        </div>
    </div>
</div>
<%--关联模态--%>
<div class="modal fade" id="editorFG" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgtgsTitle"></h4>
            </div>
            <div class="modal-body" >
                <form id="fg" style="margin-bottom: 0px;">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="input-group form-group col-md-8">
                                <input type="text" class="form-control" id="goodsName" placeholder="请输入商品名称">
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="input-group form-group col-md-4">
                                <button class="btn btn-default" type="button" onclick="refreshNotHave()">查询</button>
                            </div>
                        </div>
                        <div class="col-md-2">
                        </div>
                        <input type="hidden" id="fg_fid">
                    </div>
                </form>

                <div class="row">
                    <div class="panel panel-default col-md-6" style="border: none;box-shadow: none">
                        <!-- Default panel contents -->
                        <div class="panel-heading col-md-12">已关联商品</div>

                        <!-- Table -->
                        <table class="table table-hover" id="FamerHave">
                            <tr>
                                <th>商品名称</th>
                                <th>操作</th>
                            </tr>
                            <%--<tr>--%>
                                <%--<td>冬瓜</td>--%>
                                <%--<td><button class="btn btn-primary btn-sm" >shangchu</button></td>--%>
                            <%--</tr>--%>
                        </table>
                    </div>
                    <div class="panel panel-default col-md-6" style="border: none;box-shadow: none">
                        <!-- Default panel contents -->
                        <div class="panel-heading col-md-12">未关联商品</div>

                        <!-- Table -->
                        <table class="table table-hover" id="FamerNotHave">
                            <tr>
                                <th>商品名称</th>
                                <th>操作</th>
                            </tr>
                            <%--<tr class="tishi">--%>
                                <%--<td style="color: red"  colspan="2">请在搜索栏中输入商品名称</td>--%>
                            <%--</tr>--%>
                        </table>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>

    </div>
</div>

<%--背景图片修改--%>
    <div class="modal fade" id="editorSysUser2" tabindex="-1" role="dialog" aria-labelledby="editorSysUser" >
        <div class="modal-dialog" role="document"  style="width: 85%;">
            <%--<button class="btn btn-default" type="button" onclick="returnback();" style="float: right;margin-right: 5px">返回</button>--%>
            <div class="modal-content" style="margin-top: 5px;float: right;width: 100%">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editorSysUserTitle2" style="float:left"></h4>
                    <%--<span id="bhspan" style="color: red;float: left;margin-left: 10px;display: block"></span>--%>
                </div>
                <div class="container" style="width: 100%">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="tabbable" id="tabs-44711">
                                <%--<ul class="nav nav-tabs" >--%>
                                    <%--<li>--%>
                                        <%--<a href="#goodsImagePane" data-toggle="tab" onclick="initGoodsImages();" id="nava3">商品图片</a>--%>
                                    <%--</li>--%>
                                <%--</ul>--%>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="goodsImagePane">
                                        <div class="modal-body">
                                            <form id="editorGoodsImageForm">
                                                <input id="gfId" name="gfId" type="hidden">
                                                <input id="gfversion" name="gfversion" type="hidden">
                                                <div class="row">
                                                    <div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                        <img src="" style="width: 100%;height: 100%;" >
                                                        <div onclick="deleteGoodsImage(this,goodsImageId)" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>
                                                    </div>

                                                    <div id="uploadImgBtn"  style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                        <center>
                                                            <button onclick="giupload();return false;" class="btn btn-default" style="margin: 0 auto;margin-top: 33px">+</button>
                                                        </center>
                                                    </div>
                                                </div>
                                            </form>
                                            <center>
                                                <button id="editGoodsImageSubmit" class="btn btn-success" >确定</button>
                                            </center>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

<%@include file="/common/common_bs_head_js.jsp"%>
<%--<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/form.js"></script>
<script type="text/javascript" src="<%=path%>/js/filestyle/bootstrap-filestyle.min.js"></script>
<%--<script type="text/javascript"  src="<%=path%>/js/daterangepicker/moment.js"></script>--%>
<%--<script type="text/javascript"  src="<%=path%>/js/daterangepicker/daterangepicker.js"></script>--%>
<script type="text/javascript" src="<%=path%>/js/laydate/laydate.js"></script>
<script src="<%=path%>/js/distpicker.data.js"></script>
<script src="<%=path%>/js/distpicker.js"></script>
<!--农户-->

<script type="text/javascript">

    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorFamerType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fname',
            title: '姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fsex',
            title: '姓别',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                var html='';
                if (value == 0) {
                    html = '女';
                } else {
                    html = '男';
                }
                return html;
            }
        },
        {
            field: 'fethnic',
            title: '民族',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fphone',
            title: '电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'imgUrl',
            title: '头像',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                var html='';
                var html = '<img  src="'+value+'" style="height: 60px"/>';
                return html;
            }
        },
        {
            field: 'fbirth',
            title: '出生日期',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                var html='';
                html = value.substr(0,10);
                return html;
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:150,
            formatter:function(value,row,index){
                var html='';
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorBG(\''+row.fid+'\')"">修改背景图片</button>&nbsp;';
                return html;
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:150,
            formatter:function(value,row,index){
                rowDatas.set(row.fid,row);
                var html='';
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorFG(\''+row.fid+'\')"">关联商品</button>&nbsp;';
                return html;
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.fid,row);
                var html='';
                <ex:perm url="goods/editFamer">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorFamer(\''+row.fid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <%--if(row.fId!=0){--%>
                    <%--<ex:perm url="goods/removeFamer">--%>
                    <%--html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.fid+'\')">删除</button>';--%>
                    <%--</ex:perm>--%>
                <%--}--%>
                return html;
            }
        }
    ]


    // 关联商品
    function editorFG(fid) {
        $("#goodsName").val('');
        $(".nitem").remove();
        $(".tishi").remove();
        $("#FamerNotHave").append('<tr class="tishi">\n' +
            '                                <td style="color: red"  colspan="2">请在搜索栏中输入商品名称</td>\n' +
            '                            </tr>');
        refreshHave(fid);
        $("#editorgtgsTitle").text("编辑农户与商品关系");
        $('#editorFG').modal("show");
    }
    function addFG(fid, gid) {
        $.ajax({
            type:"post",
            url:"/famerGoods/saveFamerGoods",
            dataType: "json",
            data:{"fid":fid,"gid":gid},
            success:function(data){
                if(data.status==0){
                    layer.msg(data.msg);
                    refreshNotHave();
                    refreshHave($("#fg_fid").val());
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function rmoveFG(fgId) {
        $.ajax({
            type:"post",
            url:"/famerGoods/deleteFamerGoods",
            dataType: "json",
            data:{"fgId":fgId},
            success:function(data){
                if(data.status==0){
                    layer.msg(data.msg);
                    refreshNotHave();
                    refreshHave($("#fg_fid").val());
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function refreshHave(fid) {
        $.ajax({
            type:"post",
            url:"/famerGoods/queryHaveGoodsListByFid",
            dataType: "json",
            data:{"fid":fid},
            success:function(data){
                $(".item").remove();
                $("#fg_fid").val(fid);
                $.each(data, function (index, element) {
                    $("#FamerHave").append('<tr class="item">\n' +
                        '                       <td>'+element.goodsName+'</td>\n' +
                        '                       <td><button class="btn btn-primary btn-sm" onclick="rmoveFG('+element.fgId+')" >移除</button></td>\n' +
                        '                   </tr>');
                });
            }
        });
    }
    function refreshNotHave() {
        if ($("#goodsName").val() == '') {
            layer.msg("请输入商品名称");
            return;
        }
        $.ajax({
            type:"post",
            url:"/famerGoods/queryNotHaveGoodsListByFid",
            dataType: "json",
            data:{"fid":$("#fg_fid").val(),"goodsName":$("#goodsName").val()},
            success:function(data){
                $(".nitem").remove();
                $(".tishi").remove();
                $.each(data, function (index, element) {
                    $("#FamerNotHave").append('<tr class="nitem">\n' +
                        '                       <td>'+element.goodsName+'</td>\n' +
                        '                       <td><button class="btn btn-primary btn-sm" onclick="addFG('+$("#fg_fid").val()+','+element.gid+')" >添加</button></td>\n' +
                        '                   </tr>');
                });
            }
        });
    }


    //图片
    function clearCpLogo(){
        $('#dht_image_upload').filestyle('clear');
        $("#uploadImageDiv").hide();
        $("#cpLogoDiv").show();
        $("#clearCpLogoDiv").hide();
        $("#fimg").val('');
    }
    $('#cpImagesForm #dht_image_upload').filestyle({
        btnClass : "btn-primary",
        text:"选择文件",
        onChange:function(){
            editSubmitIndex = layer.load(2);
            cpImagesFormSummit();
        }
    });

    $(function () {
        createTable("/famer/queryFamerLists","FamerTables","fid",treeColumns,queryParams)
        //初始华开关选择器
        $('#distpicker4').distpicker({
            province: '重庆市',
            city: '重庆市市辖区',
            district: '石柱土家族自治区'
        });

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
        });

        //初始化选择省市插件
//        $('#demo1').citys({type: 'name',province: "重庆市",city: "石柱土家族自治区"});

//        dateTools("#rangeTime","","#fBirth","#rangeTime",true);

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            if($('#fbirth').val()=='' ||
                $('#fphone').val()=='' ||
                $('#fname').val()=='' ||
                $('#fcardNumber').val()=='' ||
                $('#fimg').val()=='' ||
//                $('#fcensus').val()=='' ||
//                $('#faddress').val()=='' ||
                $('#fcollector').val()=='' ||
                $('#fcollectorPosition').val()=='' ||
                $('#fcollectorPhone').val()=='' ||
                $('#fremark').val()=='' ) {
                layer.msg("请完成必填信息");
                return;
            }
            var formData=$("#editorFamerForm").serializeObject();

            var url="/famer/addFamer";
            if(editorFamerType==1){
                url="/famer/editFamer";
            }

            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0){
                        layer.msg(data.msg);
                        refreshTableData();
                        $('#editorSysUser').modal('hide');
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
//        formValidater();
    });

    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            fname: $("#search_Famer_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#FamerTables').bootstrapTable(
            "refresh",
            {
                url:"/famer/queryFamerLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeFamer(gtId);
        }, function(){
        });
    }
    /**
     * 删除农户
     **/
    function removeFamer(gfId){
        $.ajax({
            type:"post",
            url:'/famer/removeFamer',
            dataType: "json",
            data:{fid:gfId},
            success:function(data){
                if(data.status==0){
                    layer.msg("删除成功");
                    refreshTableData();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }

    function editorFamer(gfId){
        editorFamerType=1;
        initFormData(gfId);
        $("#editorSysUserTitle").text("编辑农户");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorFamerForm #fname").val('');
        $("#editorFamerForm #fid").val('');
        $("#editorFamerForm #fsex").val('');
        $("#editorFamerForm #fbirth").val('');
        $("#editorFamerForm #feducation").val('');
        $("#editorFamerForm #fethnic").val('');
        $("#editorFamerForm #fpolitical").val('');
        $("#editorFamerForm #fphone").val('');
        $("#editorFamerForm #fcardNumber").val('');
        $("#editorFamerForm #fimg").val('');
        $("#editorFamerForm #fcensus").val('');
        $("#editorFamerForm #faddress").val('');
        $("#editorFamerForm #fpopulation").val('');
        $("#editorFamerForm #version").val('');
        $("#editorFamerForm #fleavechildnum").val('');
        $("#editorFamerForm #ffamilytype").val('');
        $("#editorFamerForm #fpoornum").val('');
        $("#editorFamerForm #fpost").val('');
        $("#editorFamerForm #fcollector").val('');
        $("#editorFamerForm #fcollectorPosition").val('');
        $("#editorFamerForm #fcollectorPhone").val('');
        $("#editorFamerForm #oldImg").val('');
//        $("#editorFamerForm #city").val('');
//        $("#editorFamerForm #province").val('');
        $("#editorFamerForm #fremark").val('');
//        var city = '石柱土家族自治县';
//        $('#demo1').citys({type: 'name',province: '重庆市'});
//        var $distpicker = $('#distpicker4');
        changeVal();
        $('#cpImagesForm #dht_image_upload').filestyle('clear');
        $("#cpImagesForm #uploadImageDiv").hide();
        $("#cpImagesForm #cpLogoDiv").show();
        $("#cpImagesForm #clearCpLogoDiv").hide();
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorFamerForm #fname").val(rowData.fname);
            $("#editorFamerForm #fsex").val(rowData.fsex);
            $("#editorFamerForm #fid").val(rowData.fid);
            var time = rowData.fbirth.split(" ")[0];
            $("#editorFamerForm #fbirth").val(time);
            $("#editorFamerForm #fcollector").val(rowData.fcollector);
            $("#editorFamerForm #fcollectorPosition").val(rowData.fcollectorPosition);
            $("#editorFamerForm #fcollectorPhone").val(rowData.fcollectorPhone);
            $("#editorFamerForm #feducation").val(rowData.feducation);
            $("#editorFamerForm #fethnic").val(rowData.fethnic);
            $("#editorFamerForm #fpolitical").val(rowData.fpolitical);
            $("#editorFamerForm #fphone").val(rowData.fphone);
            $("#editorFamerForm #fcardNumber").val(rowData.fcardNumber);
            $("#editorFamerForm #fimg").val(rowData.fimg);
            $("#editorFamerForm #oldImg").val(rowData.fimg);
            $("#editorFamerForm #fcensus").val(rowData.fcensus);
            $("#editorFamerForm #faddress").val(rowData.faddress);
            $("#editorFamerForm #fpopulation").val(rowData.fpopulation);
            $("#editorFamerForm #version").val(rowData.version);
            $("#editorFamerForm #fleavechildnum").val(rowData.fleavechildnum);
            $("#editorFamerForm #ffamilytype").val(rowData.ffamilytype);
            $("#editorFamerForm #fpoornum").val(rowData.fpoornum);
            $("#editorFamerForm #fpost").val(rowData.fpost);
            var city = rowData.fcensus.split("-")[1];
            var province = rowData.fcensus.split("-")[0];

            if (province == '北京市' ||
                province == '上海市' ||
                province == '天津市' ||
                province == '重庆市') {



                var $distpicker = $('#distpicker4');
                $distpicker.distpicker('destroy');

                $('#distpicker4').distpicker({
                    province: province,
                    city: province+'市辖区',
                    district: city
                });
                $("#city9").parent().hide();
                $("#district9").parent().show();

//                $("#province9").val(province);
//                $("#city9").val(province+'市辖区');
//                $("#district9").val(city);
            } else {


                var $distpicker = $('#distpicker4');
                $distpicker.distpicker('destroy');
                $('#distpicker4').distpicker({
                    province: province,
                    city: city
                });
                $("#city9").parent().show();
                $("#district9").parent().hide();
//                $("#province9").val(province);
//                $("#city9").val(city);
            }


//            $("#editorFamerForm #province").val(province);
//            $("#editorFamerForm #city").val(city);
            $("#editorFamerForm #fremark").val(rowData.fremark);

            if(rowData.fimg){
                $("#cpImagesForm #uploadImageDiv").show();
                $("#cpImagesForm #cpLogoDiv").hide();
                $("#cpImagesForm #clearCpLogoDiv").show();
                $("#cpImagesForm #uploadImage").attr("src",rowData.imgUrl);
            }else{
                $("#cpImagesForm #uploadImageDiv").hide();
                $("#cpImagesForm #cpLogoDiv").show();
                $("#cpImagesForm #clearCpLogoDiv").hide();
            }

        }else{
            $("#editorFamerForm #fname").val('');
            $("#editorFamerForm #fid").val('');
            $("#editorFamerForm #fsex").val('');
            $("#editorFamerForm #fbirth").val('');
            $("#editorFamerForm #feducation").val('');
            $("#editorFamerForm #fethnic").val('');
            $("#editorFamerForm #fpolitical").val('');
            $("#editorFamerForm #fphone").val('');
            $("#editorFamerForm #fcardNumber").val('');
            $("#editorFamerForm #fimg").val('');
            $("#editorFamerForm #fcensus").val('');
            $("#editorFamerForm #fcollector").val('大汇堂');
            $("#editorFamerForm #fcollectorPosition").val('远山结亲小组');
            $("#editorFamerForm #fcollectorPhone").val('02373365789');
            $("#editorFamerForm #oldImg").val('');
            $("#editorFamerForm #faddress").val('');
            $("#editorFamerForm #fpopulation").val('');
            $("#editorFamerForm #version").val('');
            $("#editorFamerForm #fleavechildnum").val('');
            $("#editorFamerForm #ffamilytype").val('');
            $("#editorFamerForm #fpoornum").val('');
            $("#editorFamerForm #fpost").val('');
            $("#editorFamerForm #fremark").val('');

            var $distpicker = $('#distpicker4');
            $distpicker.distpicker('destroy');

            $('#distpicker4').distpicker({
                province: '重庆市',
                city: '重庆市'+'市辖区',
                district: '石柱土家族自治县'
            });
            $("#city9").parent().hide();
            $("#district9").parent().show();
//            $("#city").val('石柱土家族自治县');
//            console.log(1111);
//            $('#city option').each(function(){
//                console.log($(this).text());
//                if($(this).text() == '石柱土家族自治县'){
//                    $(this).attr('selected',true);
//                }
//            });

            changeVal();


            $('#cpImagesForm #dht_image_upload').filestyle('clear');
            $("#cpImagesForm #uploadImageDiv").hide();
            $("#cpImagesForm #cpLogoDiv").show();
            $("#cpImagesForm #clearCpLogoDiv").hide();

        }
    }


//    $(function(){
//        $('#test').citys({
//            url : '/json/jquery-citys.json',          // 省市区json数据地址
//            province : '重庆市',                        // 省份(省级),可以为地区编码或者省份名称
//            city : '',                            // 城市(地级),可以为地区编码或者城市名称
//            area : '',                            // 地区(县区级),可以为地区编码或者地区名称
//            type : 'name',                        // 下拉框值的类型,code行政区划代码,name地名
//            selProvince : "province",             // 省份、直辖市列表框name
//            selCity : "city",                     // 城市、区列表框name
//            selArea : "area",                     // 区、县列表框name
//        });
//    });

    /*function createCity(province,city) {

        console.log($("#verify option").size());
        while($("#verify option").size() != 0){
            $("#verify option").each(function(){
                if($(this).text() == '石柱土家族自治县'){
                    console.log(111);
                    $(this).attr('selected',true);
                }
            });
        }

    }*/

/*    function initCity(province,city) {
        $('#demo1').html('');
        var html = '<select style="height: 34px" class="city-select " name="province" id="province"></select>\n' +
            '       <select style="height: 34px" class="city-select " name="city" id="city"></select>';
        $('#demo1').html(html);
//        $('#demo1').citys({type: 'name',province: province,city: city});
    }*/
    var fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=famer"
    function cpImagesFormSummit(){
        var formData = new FormData($( "#cpImagesForm" )[0]);
        $.ajax({
            url: fileUpload,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function (returndata) {
                if(returndata.state=="SUCCESS"){
                    $("#uploadImageDiv").show();
                    $("#cpLogoDiv").hide();
                    $("#clearCpLogoDiv").show();
                    $("#uploadImage").attr("src",returndata.url);
                    $("#fimg").val(returndata.original);
                }
                layer.close(editSubmitIndex);
            },
            error: function (returndata) {
                layer.close(editSubmitIndex);
            }
        });
    };

    /**
     * 添加农户
     **/
    function addFamer(){
        editorFamerType=0;
        initFormData(-1);
        $("#editorSysUserTitle").html("添加农户&nbsp;&nbsp;&nbsp;<span style=\"color: red;\">*</span><span style=\"font-weight: 3;font-size: 12px;\">为必填选项</span>");
        $('#editorSysUser').modal("show");
    }


</script>
<%--设置多图片上传背景--%>
<%--<script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.config.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.all.min.js"> </script>--%>
<script src="<%=path%>/js/toast/js/toastr.js"></script>
<script type="text/javascript">
    /////////////背景图片关联//////////////
    // 编辑背景
    function editorBG(fid){
//        editorGoodsType=1;
//        initFormData(fid);
        $("#editorSysUserTitle2").html("编辑背景图片<span style='font-size: 12px;'>(建议图片大小:375px*200px)</span>");
        $('#gfId').val(fid);
        $('#editorSysUser2').modal("show");
        initBGimages(fid);
    }
    <!--显示商品删除按钮-->
    function showDeleteImage(obj) {
        $(obj).find('div').show();
    }
    <!--隐藏商品删除按钮-->
    function hideDeleteImage(obj) {
        $(obj).find('div').hide();
    }
    function giupload() {
        uploadImgFlag = 1;
        upImage();
    }
    // 确定编辑图片
    $('#editGoodsImageSubmit').click(function () {

        if(deleteArr.length==0 && newImgArr.length==0){
            toastr.error("您不存在修改操作!");
            $('#editorSysUser2').modal("hide");
            return;
        }

        var gfId = $('#gfId').val();
//        if(gid==null || gid==''){
//            toastr.warning('请保存商品基本信息！');
//            document.getElementById('nava1').click();
//            return;
//        }

        <!--新增商品图片-->
        if (newImgArr.length>0){
            for(var i=0; i<newImgArr.length; i++){
                $.ajax({
                    type:"post",
                    url:"/famerImg/saveFamerImg",
                    dataType: "json",
                    data:{fid:gfId,imgId:newImgArr[i]},
                    success:function(data){
                        if(data.status==0){
                            //显示提示
                            toastr.success('操作成功！');
                            if(i==0){
                                refreshTableData();
                            }
                        }else{
                            toastr.error('操作失败！');
                        }
                    }
                });
            }
        }

        <!--删除商品图片-->
        if(deleteArr.length>0){
            for(var i=0; i<deleteArr.length; i++){
                $.ajax({
                    type:"post",
                    url:"/famerImg/deleteFamerImg",
                    dataType: "json",
                    data:{fid:gfId,imgId:deleteArr[i]},
                    success:function(data){
                        if(data.status==0){
                            //显示提示
                            toastr.success('操作成功！');
                            if(i==0){
                                refreshTableData();
                            }
                        }else{
                            toastr.error('操作失败！');
                        }
                    }
                });
            }
            refreshTableData();
        }

//        alert('xxx'+gfId);
//        alert(deleteArr.toString());
//        alert(newImgArr.toString());
        $('#editorSysUser2').modal("hide");
        deleteArr=[];
        newImgArr=[];
//        document.getElementById('nava4').click();
    });

    <!--点击删除按钮-->
    function deleteGoodsImage(obj,goodsImgId) {
        $(obj).parent().hide();
        var index = 0;
        for(var i=0; i<newImgArr.length; i++){
            if(newImgArr[i]==goodsImgId){
                newImgArr.splice(i,1);
                index = 1;
            }
        }
        if(index==0){
            deleteArr.push(goodsImgId);
        }

    }
    // 初始化编辑
    function initBGimages(fid) {
        $('#uploadImgBtn').prevAll().remove();

        if(fid=='' || fid==null){
            return;
        }

        $.ajax({
            type:"post",
            url:"/famerImg/queryFamerImgListByFid",
            dataType: "json",
            data:{fid:fid,pageNo:1,pageSize:999},
            success:function(data){
                if(data!=null && data.length>0){
                    for(var i=0; i<data.length; i++){
                        var imgdiv = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                            '<img src="'+data[i].imgUrl+'" style="width: 100%;height: 100%;" >'+
                            '<div onclick="deleteGoodsImage(this,'+data[i].imgId+')" style="display: none; position: absolute;width: 100%;background-color: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                            '</div>';
                        $('#uploadImgBtn').before(imgdiv);
                    }
                }
            }
        });
    }
    //////////////////////////////////////
    laydate.render({
        elem: '#fBirth'
        ,format: 'yyyy-MM-dd'
    });
</script>

<%--<script src="/js/jquery-1.9.1.min.js"></script>--%>

<script>

    $("#province9").change(function () {
        if ($("#province9").val() == '北京市' ||
            $("#province9").val() == '上海市' ||
            $("#province9").val() == '天津市' ||
            $("#province9").val() == '重庆市') {
            $("#city9").parent().hide();
            $("#district9").parent().show();
        } else {
            $("#city9").parent().show();
            $("#district9").parent().hide();
        }
    });

    $("#district9").change(function(){
        changeVal();
    });

    $("#city9").change(function(){
        changeVal();
    });

    function changeVal() {
        var province = $("#province9 option:selected").text();
        var city = $("#city9 option:selected").text();
        var district = $("#district9 option:selected").text();
        if ($("#province9").val() == '北京市' ||
            $("#province9").val() == '上海市' ||
            $("#province9").val() == '天津市' ||
            $("#province9").val() == '重庆市') {
            $("#fcensus").val(province+'-'+district);
            $("#faddress").val(province+district);
        } else {
            $("#fcensus").val(province+'-'+city);
            $("#faddress").val(province+city);
        }
    }

</script>

</body>
</html>
