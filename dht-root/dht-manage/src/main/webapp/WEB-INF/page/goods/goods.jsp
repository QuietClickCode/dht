<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品管理</title>
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

            for(var i=0; i<arg.length; i++){
                var imgdiv = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                                '<img src="'+arg[i].src+'" style="width: 100%;height: 100%;" >'+
                                '<div onclick="deleteGoodsImage(this,'+arg[i].alt+')" style="display: none; position: absolute;width: 100%;background-color: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                                '</div>';
                $('#uploadImgBtn').before(imgdiv);
                newImgArr.push(arg[i].alt);
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
<div id="tabDiv">
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoods">
        <button class="btn btn-primary" type="button" onclick="addGoods()">添加商品</button>
    </ex:perm>
    <ex:perm url="goods/addGoods">
        <button class="btn btn-default" type="button" onclick="deleteGoodsList()">删除</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
        <input onclick="showMenu2();" type="text" class="form-control" id="search_Goods_typeNm" placeholder="请选择商品类型">
        <input id="search_Goods_type" type="hidden">
        <select id="search_Goods_gmaindirection" class="form-control">
            <option value="">主推方向</option>
            <option value="0">乡村</option>
            <option value="1">城镇</option>
            <option value="1">乡村和城镇</option>
        </select>
        <select id="search_Goods_check" class="form-control">
            <option value="">审核状态</option>
            <option value="0">未审核</option>
            <option value="1">已审核</option>
            <option value="2">未通过审核</option>
        </select>
    </div>

    <ex:perm url="goods/queryGoodsLists">
        <button class="btn btn-default" style="margin-top: 5px" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsTables" ></table>
</div>
</div>

<div id="addandeditgoods">
    <button class="btn btn-default" type="button" onclick="">返回</button>
    <div class="" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser" >
        <div class="modal-dialog" role="document"  style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="editorSysUserTitle"></h4>
                </div>
                <div class="container" style="width: 100%">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <div class="tabbable" id="tabs-44711">
                                <ul class="nav nav-tabs" >
                                    <li class="active" id="navfirstli">
                                        <a href="#goodsPane" data-toggle="tab" id="nava1">基本信息</a>
                                    </li>
                                    <li>
                                        <a href="#goodsConfigPane" data-toggle="tab" onclick="initGoodsConfigForm(this);" id="nava2">商品配置</a>
                                    </li>
                                    <li>
                                        <a href="#goodsImagePane" data-toggle="tab" onclick="initGoodsImages();" id="nava3">商品图片</a>
                                    </li>
                                </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="goodsPane">
                                            <div class="modal-body" style="position: relative">
                                                <form id="editorGoodsForm">
                                                    <input type="hidden" name="gid" id="gid">
                                                    <input type="hidden" name="version" id="version">
                                                    <input type="hidden" name="isDelete" id="isDelete">

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品名称:
                                                          </span>
                                                                <input type="text" class="form-control" name="gname" id="gname">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              商品分类:
                                                          </span>
                                                                <input id="gclassification" name="gclassification" type="hidden" />
                                                                <input id="gclassificationName" name="gclassificationName" type="text" class="form-control" onclick="showMenu(); return false;"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <%--<div class="col-lg-12">--%>
                                                            <%--<div class="input-group form-group">--%>
                                                          <%--<span class="input-group-addon">--%>
                                                            <%--所属仓库/地区:--%>
                                                          <%--</span>--%>
                                                                <%--<input id="garea" name="garea" type="hidden" class="form-control" />--%>
                                                                <%--<input id="gareaName" name="gareaName" type="text" class="form-control" />--%>
                                                            <%--</div>--%>
                                                        <%--</div>--%>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              可售范围:
                                                          </span>
                                                                <input id="gsalescope" name="gsalescope" type="text" class="form-control" placeholder="以千米为单位"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品价格（原价）:
                                                          </span>
                                                                <input id="gprice" name="gprice" type="text" class="form-control" placeholder="单位：元"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              主推方向:
                                                          </span>
                                                                <input id="gmaindirection" name="gmaindirection" type="hidden" />
                                                                <div class="radio " style="display: inline-block;">
                                                                    <input type="radio" name="mainType" id="maincomtrayside" value="0" checked>
                                                                    <label for="maincomtrayside">
                                                                        乡村
                                                                    </label>
                                                                </div>
                                                                <div class="radio " style="display: inline-block;margin-left: 30px;">
                                                                    <input type="radio" name="mainType" id="maincity" value="1" style="">
                                                                    <label for="maincomtrayside">
                                                                        城镇
                                                                    </label>
                                                                </div>
                                                                <div class="radio " style="display: inline-block;margin-left: 30px">
                                                                    <input type="radio" name="mainType" id="maincityandcom" value="2" checked>
                                                                    <label for="maincityandcom">
                                                                        乡村和城市
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            计件单位:
                                                          </span>
                                                                <input id="gunitname" name="gunitname" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            生产地信息:
                                                          </span>
                                                                <input id="gproductioninaddress" name="gproductioninaddress" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              生产人姓名:
                                                          </span>
                                                                <input id="gproductioninperson" name="gproductioninperson" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            收集人地址:
                                                          </span>
                                                                <input id="gpickaddress" name="gpickaddress" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              收集人姓名:
                                                          </span>
                                                                <input id="gpickperson" name="gpickperson" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!--定金 详情描述-->
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            详情描述:
                                                          </span>
                                                                <input id="gdescription" name="gdescription" type="hidden">
                                                                <div style="width: 100%">
                                                                    <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                                <center>
                                                    <button id="editSubmit" class="btn btn-success" >保存</button>
                                                </center>

                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsConfigPane">
                                            <div class="modal-body">
                                                <form id="editorGoodsConfigForm">
                                                    <input id="gcId" name="gcId" type="hidden">
                                                    <input id="gcgid" name="gid" type="hidden">
                                                    <input id="configversion" name="configversion" type="hidden">
                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            是否是服务类商品:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isServicegoods" name="isServicegoods" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            利润率:
                                                          </span>
                                                                <input type="text" class="form-control" name="gprofitability" id="gprofitability" style="width: 60%">%
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12" id="allowsettimediv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon" id="allowsettimespen">
                                                              允许用户设置发货时间:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isAllowsetdeliverytime" name="isAllowsetdeliverytime" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            是否显示销量:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isShowsalesvolume" name="isShowsalesvolume" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12" id="gsalesvolumediv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              销量:
                                                          </span>
                                                                <input id="gsalesvolume" name="gsalesvolume" type="text" class="form-control" value="0"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            会员是否打折:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isMenberdiscount" name="isMenberdiscount" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              是否上架:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isPutway" name="isPutway" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row" id="isadvancesalediv">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            是否预售:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isAdvancesale" name="isAdvancesale" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12" id="gedtdiv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              预计发货时间:
                                                          </span>
                                                                <input id="gedt" name="gedts" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12" id="gfreightdiv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            运费:
                                                          </span>
                                                                <input id="gfreight" name="gfreight" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row" id="iscoddiv">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              能否货到付款:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="isCod" name="isCod" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            起售数量:
                                                          </span>
                                                                <input id="gstartbuy" name="gstartbuy" type="text" class="form-control"style="width: 80%"/>
                                                                <div class="checkbox checkbox-info" style="display: inline-block">
                                                                    <input id="isMultiplebuy" class="styled" type="checkbox" name="isMultiplebuy">
                                                                    <label for="isMultiplebuy">
                                                                        倍数购买
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              限购量:
                                                          </span>
                                                                <input id="gendbuy" name="gendbuy" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!--定金 详情描述-->
                                                    <div class="row">
                                                        <div class="col-lg-12" id="gdepositdiv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            定金:
                                                          </span>
                                                                <input id="gdeposit" name="gdeposit" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                                <center>
                                                    <button id="editGoodsConfigSubmit" class="btn btn-success" >保存</button>
                                                </center>
                                            </div>

                                        </div>
                                        <div class="tab-pane" id="goodsImagePane">
                                            <div class="modal-body">
                                                <form id="editorGoodsImageForm">
                                                    <input id="giId" name="giId" type="hidden">
                                                    <input id="giversion" name="giversion" type="hidden">
                                                    <div class="row">
                                                        <div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                            <img src="" style="width: 100%;height: 100%;" >
                                                            <div onclick="deleteGoodsImage(this,goodsImageId)" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>
                                                        </div>

                                                        <div id="uploadImgBtn"  style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                            <center>
                                                                <button onclick="upImage();return false;" class="btn btn-default" style="margin: 0 auto;margin-top: 33px">+</button>
                                                            </center>
                                                        </div>
                                                    </div>
                                                </form>
                                                <center>
                                                    <button id="editGoodsImageSubmit" class="btn btn-success" >保存</button>
                                                </center>
                                            </div>
                                        </div>

                                    </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--<div class="modal-footer">--%>
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                    <%--<button type="button" class="btn btn-primary" id="editSubmit">确认</button>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>

<!-- 公用下拉择树 -->
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>

<!--删除提示框-->
<div class="modal fade" id="delcfmModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="url"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gname',
            title: '商品名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gclassificationName',
            title: '商品分类',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gmaindirection',
            title: '主推方向',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(row.gmaindirection==0){
                    html+='乡村';
                }else if(row.gmaindirection==1){
                    html+='城镇';
                }else{
                    html+='乡村和城镇';
                }
                return html;
            }
        },
        {
            field: 'isChecked',
            title: '审核状态',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(row.isChecked==1){
                    html+='<span style="color:green;">已审核</span>';
                }
                if(row.isChecked==0){
                    html+='<span style="color:red;">未审核</span>';
                }
                if(row.isChecked==2){
                    html+='<span style="color:red;">未通过审核</span>';
                }
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
                rowDatas.set(row.gid,row);
                let html='';
                <ex:perm url="goods/editGoods">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoods(\''+row.gid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoods">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gid+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsLists","GoodsTables","gbId",treeColumns,queryParams);

        //初始华开关选择器
        $("#isServicegoods").bootstrapSwitch();
        $("#isAllowsetdeliverytime").bootstrapSwitch();
        $("#isShowsalesvolume").bootstrapSwitch();
        $("#isMenberdiscount").bootstrapSwitch();
        $("#isPutway").bootstrapSwitch();
        $("#isCod").bootstrapSwitch();
        $("#isAdvancesale").bootstrapSwitch();


        $("input[name='mainType']").click(function () {
             $('#gmaindirection').val($(this).val());
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsForm').data('bootstrapValidator').validate();
            if(!$('#editorGoodsForm').data('bootstrapValidator').isValid()){
                return;
            }

            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsForm").serializeObject();
            formData["gdescription"]=UE.getEditor('editor').getContent();
            formData["garea"]=1;
            let url="/goods/addGoods";
            if(editorGoodsType==1){
                url="/goods/editGoods";
            }

            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    layer.close(editSubmitIndex);

                    if(url=='/goods/addGoods'){
                        var goods = data.goods;
                        if(goods==null){
                            toastr.error("操作失败！");
                        }else{
                            toastr.success("操作成功！");
                            $('#gid').val(goods.gid);
                            $('#version').val(1);
                            editorGoodsType=1;
                            refreshTableData();
                        }
                    }else{
                        if(data.status==0){
                            toastr.success("操作成功！");
                            $('#version').val(parseInt($('#version').val()) +2);
                        }else{
                            toastr.error("操作失败！");
                        }
                    }



                }
            });
        });
        formValidater();
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorGoodsForm').bootstrapValidator({
                container: 'tooltip',
                //不能编辑 隐藏 不可见的不做校验
                excluded: [':disabled', ':hidden', ':not(:visible)'],
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    gname: {
                        message: '商品名称不能为空',
                        validators: {
                            notEmpty: {
                                message: '商品名称不能为空'
                            },
                            stringLength: {
                                min: 2,
                                max: 25,
                                message: '商品名称长度在4-30之间'
                            }
                        }
                    },
                    gclassificationName: {
                        message: '商品分类校验未通过',
                        validators: {
                            notEmpty: {
                                message: '商品分类不能为空'
                            }
                        }
                    },
                    gunitname: {
                        message: '计件单位校验未通过',
                        validators: {
                            notEmpty: {
                                message: '计件单位不能为空'
                            }
                        }
                    },
                    gprice: {
                        message: '商品价格为空',
                        validators: {
                            notEmpty: {
                                message: '商品价格不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'优惠金额只允许在10位整数和2位小数范围内'
                            }
                        }
                    }
                }
            });

        $('#editorGoodsConfigForm').bootstrapValidator({
            container: 'tooltip',
            //不能编辑 隐藏 不可见的不做校验
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            //live: 'submitted',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                gclassificationName: {
                    message: '商品分类校验未通过',
                    validators: {
                        notEmpty: {
                            message: '商品分类不能为空'
                        }
                    }
                },
                gsalesvolume: {
                    message: '销量校验未通过',
                    validators: {
                        notEmpty: {
                            message: '销量不能为空'
                        }
                    }
                },
                gprofitability: {
                    message: '利润率为空',
                    validators: {
                        notEmpty: {
                            message: '利润率不能为空'
                        },
                        regexp:{
                            regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                            message:'利润率只允许在10位整数和2位小数范围内'
                        }
                    }
                }
            }
        });
    }
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gname: $("#search_Goods_name").val(),
            gclassification: $("#search_Goods_type").val(),
            gmaindirection: $("#search_Goods_gmaindirection").val(),
            isChecked: $("#search_Goods_check").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        if($('#search_Goods_typeNm').val()==''){
            $('#search_Goods_type').val('');
        }
        $('#GoodsTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLists"
            }
        );
    }
    var topGid;

    //删除确认框
    function deleteData(gid){
        $('#delcfmModel').modal();
        topGid=gid;
    }
    function urlSubmit() {
        removeGoods(topGid);
    }

    /**
     * 删除商品大类
     **/
    function removeGoods(gid){
        $.ajax({
            type:"post",
            url:'/goods/removeGoods',
            dataType: "json",
            data:{gid:gid},
            success:function(data){
                if(data.status==0){
                    toastr.success("删除成功！");
                    refreshTableData();
                }else{
                    toastr.error("删除失败！");
                }
            }
        });
    }


    var zNodes;
    function editorGoods(gid){
        editorGoodsType=1;
        initFormData(gid);
        $("#editorSysUserTitle").text("编辑商品");
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#gid").val('');
        $("#version").val('0');
        $("#isDelete").val('0');
        $("#gname").val('');
        $("#gclassification").val('');
        $("#gclassificationName").val('');
        $("#garea").val('');
        $("#gareaName").val('');
        $("#gsalescope").val('');
        $("#gprice").val('');
        $("#gunitname").val('');
        $("#gproductioninaddress").val('');
        $("#gproductioninperson").val('');
        $("#gpickaddress").val('');
        $("#gpickperson").val('');
        $("#gmaindirection").val('0');
        $('#maincomtrayside').get(0).checked=true;
        UE.getEditor('editor').setContent('', false);

        $('#gcId').val('');
        $('#configversion').val('0s');
        $("#gfreight").val('');
        $("#gstartbuy").val('');
        $("#gendbuy").val('');
        $("#gsalesvolume").val('');
        $("#gedt").val('');
        $("#gprofitability").val('');
        $("#gdeposit").val('');

        $("#isServicegoods").val('');
        $("#isServicegoods").bootstrapSwitch("state",false);

        $("#isAllowsetdeliverytime").val('');
        $("#isAllowsetdeliverytime").bootstrapSwitch("state",false);

        $("#isShowsalesvolume").val('');
        $("#isShowsalesvolume").bootstrapSwitch("state",false);

        $("#isMenberdiscount").val('');
        $("#isMenberdiscount").bootstrapSwitch("state",true);

        $("#isPutway").val('');
        $("#isPutway").bootstrapSwitch("state",true);

        $("#isAdvancesale").val('');
        $("#isAdvancesale").bootstrapSwitch("state",false);

        $("#isCod").val('');
        $("#isCod").bootstrapSwitch("state",false);

        $("#isMultiplebuy").val('');
        $('#isMultiplebuy').removeAttr('checked');



    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        document.getElementById("nava1").click();
        clearFormValidation("editorGoodsForm",formValidater);
        if(rowData){
            $("#gid").val(rowData.gid);
            $("#version").val(rowData.version);
            $("#isDelete").val(rowData.isDelete);
            $("#gname").val(rowData.gname);
            $("#gclassification").val(rowData.gclassification);
            $("#gclassificationName").val(rowData.gclassificationName);
            $("#garea").val(rowData.garea);
            $("#gareaName").val(rowData.gareaName);
            $("#gsalescope").val(rowData.gsalescope);
            $("#gprice").val(rowData.gprice);
            $("#gunitname").val(rowData.gunitname);
            $("#gproductioninaddress").val(rowData.gproductioninaddress);
            $("#gproductioninperson").val(rowData.gproductioninperson);
            $("#gpickaddress").val(rowData.gpickaddress);
            $("#gpickperson").val(rowData.gpickperson);
            $('#gmaindirection').val(rowData.gmaindirection);
            if(rowData.gmaindirection==0){
                $('#maincomtrayside').get(0).checked=true;
            }
            if(rowData.gmaindirection==1){
                $('#maincity').get(0).checked=true;
            }
            if(rowData.gmaindirection==2){
                $('#maincityandcom').get(0).checked=true;
            }
            UE.getEditor('editor').setContent(rowData.gdescription, false);
        }else{
            clearFormData();
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoods(){
        editorGoodsType=0;
        initFormData(-1);
        $("#editorSysUserTitle").text("添加商品");
    }

    
    function deleteGoodsList() {
        var objs = $('#GoodsTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removeGoods(objs[i].gbId);
                }
            }, function(){
            });
        }else{
            layer.msg("请选择需要删除的品牌！");
        }
    }
</script>

<!--百度编辑器-->
<script>
    var ue = UE.getEditor('editor');
</script>

<!--商品配置-->
<script>
    $(function () {
        $('#isServicegoods').siblings().click(function () {
            var flag = $("#isServicegoods").bootstrapSwitch("state");
            if(flag){
                $('#allowsettimediv').hide();
                $('#isadvancesalediv').hide();
                $('#gfreightdiv').hide();
                $('#gdepositdiv').show();
                $('#maincomtrayside').attr('checked','checked');
                $('#iscoddiv').hide();
            }else{
                $('#allowsettimediv').show();
                $('#isadvancesalediv').show();
                $('#gfreightdiv').show();
                $('#gdepositdiv').hide();
                $('#iscoddiv').show();
            }
        });

        $('#isAdvancesale').siblings().click(function () {
            var flag = $("#isAdvancesale").bootstrapSwitch("state");
            if(flag){
                $('#gedtdiv').show();
            }else{
                $('#gedtdiv').hide();
            }
        });
        
        <!--提交商品配置-->
        $('#editGoodsConfigSubmit').click(function () {
            $('#editorGoodsConfigForm').data('bootstrapValidator').validate();
            if(!$('#editorGoodsConfigForm').data('bootstrapValidator').isValid()){
                return;
            }
            var formData=$("#editorGoodsConfigForm").serializeObject();

            formData["version"]=$('#configversion').val();
            var gid = $('#gid').val();
            if(gid==null || gid==''){
                toastr.warning('请保存商品基本信息！');
                document.getElementById('nava1').click();
                return;
            }else{
                formData["gid"]=gid;
            }

            var flag = $("#isServicegoods").bootstrapSwitch("state");
            if(flag){
                formData["isServicegoods"]=1;
                formData["isAllowsetdeliverytime"]=1;
                formData["isAdvancesale"]=0;
                formData["gedts"]='';
                formData["gfreight"]=null;
                formData["isCod"]=null;
            }else{
                formData["isServicegoods"]=0;
                formData["gedts"]=$('#gedt').val();

                flag = $("#isAllowsetdeliverytime").bootstrapSwitch("state");
                if(flag){
                    formData["isAllowsetdeliverytime"]=1;
                }else{
                    formData["isAllowsetdeliverytime"]=0;
                }

                flag = $("#isAdvancesale").bootstrapSwitch("state");
                if(flag){
                    formData["isAdvancesale"]=1;
                }else{
                    formData["isAdvancesale"]=0;
                    formData["gedts"]='';
                }

                formData["gdeposit"]='';

            }

            flag = $("#isShowsalesvolume").bootstrapSwitch("state");
            if(flag){
                formData["isShowsalesvolume"]=1;
            }else{
                formData["isShowsalesvolume"]=0;
            }

            flag = $("#isMenberdiscount").bootstrapSwitch("state");
            if(flag){
                formData["isMenberdiscount"]=1;
            }else{
                formData["isMenberdiscount"]=0;
            }

            flag = $("#isPutway").bootstrapSwitch("state");
            if(flag){
                formData["isPutway"]=1;
            }else{
                formData["isPutway"]=0;
            }

            flag = $("#isMultiplebuy").is(':checked');
            if(flag){
                formData["isMultiplebuy"]=1;
            }else{
                formData["isMultiplebuy"]=0;
            }

            $.ajax({
                type:"post",
                url:"/goods/editGoodsConfig",
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0){
                        //显示提示
                        toastr.success('操作成功！');
                        document.getElementById('nava2').click();
//                        var gcId = $('#gcId').val();
//                        if(gcId=='' || gcId==null){
//                            $('#configversion').val(1);
//                        }else{
//                            var configversion = $('#configversion').val();
//                            $('#configversion').val(parseInt(configversion)+2);
//                        }
                    }else{
                        toastr.error('操作失败！');
                    }
                }
            });


        });

    });

    <!--初始化商品配置信息-->
    function initGoodsConfigForm(e) {
        var gid = $('#gid').val();
        if(gid=='' || gid==null ){
            return ;
        }

        $.ajax({
            type:"post",
            url:'/goods/queryGoodsConfigBygid',
            dataType: "json",
            data:{gid:gid,pageSize:1,pageNo:1},
            async:false,
            success:function(data){
                var goodsConfig = data.goodsConfig;
                if(goodsConfig==null){

                    $('#gcId').val('');
                    $('#gcgid').val();
                    $('#configversion').val('');
                    $("#gfreight").val('');
                    $("#gstartbuy").val('');
                    $("#gendbuy").val('');
                    $("#gsalesvolume").val('');
                    $("#gedt").val('');
                    $("#gprofitability").val('');
                    $("#gdeposit").val('');

                    $("#isServicegoods").val('');
                    $("#isServicegoods").bootstrapSwitch("state",false);

                    $("#isAllowsetdeliverytime").val('');
                    $("#isAllowsetdeliverytime").bootstrapSwitch("state",false);

                    $("#isShowsalesvolume").val('');
                    $("#isShowsalesvolume").bootstrapSwitch("state",false);

                    $("#isMenberdiscount").val('');
                    $("#isMenberdiscount").bootstrapSwitch("state",true);

                    $("#isPutway").val('');
                    $("#isPutway").bootstrapSwitch("state",true);

                    $("#isAdvancesale").val('');
                    $("#isAdvancesale").bootstrapSwitch("state",false);

                    $("#isCod").val('');
                    $("#isCod").bootstrapSwitch("state",false);

                    $("#isMultiplebuy").val('');
                    $('#isMultiplebuy').removeAttr('checked');
                }else{
                    $('#gcId').val(goodsConfig.gcId);
                    $('#gcgid').val(goodsConfig.gid);
                    $("#gfreight").val(goodsConfig.gfreight);
                    $("#gstartbuy").val(goodsConfig.gstartbuy);
                    $("#gendbuy").val(goodsConfig.gendbuy);
                    $("#gsalesvolume").val(goodsConfig.gsalesvolume);
                    $("#gedt").val(goodsConfig.gedt);
                    $("#gprofitability").val(goodsConfig.gprofitability);
                    $("#gdeposit").val(goodsConfig.gdeposit);
                    $('#configversion').val(goodsConfig.version);

                    var flag;

                    flag = false;
                    $("#isServicegoods").val(goodsConfig.isServicegoods);
                    if(goodsConfig.isServicegoods==1){
                        flag = true;
                        $('#allowsettimediv').hide();
                        $('#isadvancesalediv').hide();
                        $('#gfreightdiv').hide();
                        $('#gdepositdiv').show();
                    }else{
                        $('#allowsettimediv').show();
                        $('#isadvancesalediv').show();
                        $('#gfreightdiv').show();
                        $('#gdepositdiv').hide();
                    }
                    $("#isServicegoods").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isAllowsetdeliverytime").val(goodsConfig.isAllowsetdeliverytime);
                    if(goodsConfig.isAllowsetdeliverytime==1){
                        flag = true;
                    }
                    $("#isAllowsetdeliverytime").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isShowsalesvolume").val(goodsConfig.isShowsalesvolume);
                    if(goodsConfig.isShowsalesvolume==1){
                        flag = true;
                    }
                    $("#isShowsalesvolume").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isMenberdiscount").val(goodsConfig.isMenberdiscount);
                    if(goodsConfig.isMenberdiscount==1){
                        flag = true;
                    }
                    $("#isMenberdiscount").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isPutway").val(goodsConfig.isPutway);
                    if(goodsConfig.isPutway==1){
                        flag = true;
                    }
                    $("#isPutway").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isAdvancesale").val(goodsConfig.isAdvancesale);
                    if(goodsConfig.isAdvancesale==1){
                        flag = true;
                        $('#gedtdiv').show();
                    }else{
                        $('#gedtdiv').hide();
                    }
                    $("#isAdvancesale").bootstrapSwitch("state",flag);

                    flag = false;
                    $("#isCod").val(goodsConfig.isCod);
                    if(goodsConfig.isCod==1){
                        flag = true;
                    }
                    $("#isCod").bootstrapSwitch("state",flag);

                    $("#isMultiplebuy").val(goodsConfig.isMultiplebuy);
                    if(goodsConfig.isMultiplebuy==1){
                        $('#isMultiplebuy').attr('checked','checked');
                    }else{
                        $('#isMultiplebuy').removeAttr('checked');
                    }
                }

            }
        });
    }
</script>

<!--商品图片-->
<script>

    function initGoodsImages() {
        $('#uploadImgBtn').prevAll().remove();

        var gid = $('#gid').val();
        if(gid=='' || gid==null){
            return;
        }

        $.ajax({
            type:"post",
            url:"/goods/queryGoodsImages",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:100},
            success:function(data){
                var goodsImages = data.goodsImages;
                if(goodsImages!=null && goodsImages.length>0){
                    for(var i=0; i<goodsImages.length; i++){
                        var imgdiv = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                            '<img src="'+goodsImages[i].imgUrl+'" style="width: 100%;height: 100%;" >'+
                            '<div onclick="deleteGoodsImage(this,'+goodsImages[i].giId+')" style="display: none; position: absolute;width: 100%;background-color: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                            '</div>';
                        $('#uploadImgBtn').before(imgdiv);
                    }
                }
            }
        });
    }
    var deleteArr = new Array();
    var newImgArr = new Array();

    $('#editGoodsImageSubmit').click(function () {

        if(deleteArr.length==0 && newImgArr.length==0){
            toastr.warning('您不存在修改操作！');
            return;
        }

        var gid = $('#gid').val();
        if(gid==null || gid==''){
            toastr.warning('请保存商品基本信息！');
            document.getElementById('nava1').click();
            return;
        }

        <!--新增商品图片-->
        if (newImgArr.length>0){
            for(var i=0; i<newImgArr.length; i++){
                $.ajax({
                    type:"post",
                    url:"/goods/addGoodsImage",
                    dataType: "json",
                    data:{gid:gid,giId:newImgArr[i]},
                    success:function(data){
                        if(data.status==0){
                            //显示提示
                            toastr.success('操作成功！');
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
                    url:"/goods/removeGoodsImage",
                    dataType: "json",
                    data:{giId:deleteArr[i]},
                    success:function(data){
                        if(data.status==0){
                            //显示提示
                            toastr.success('操作成功！');
                        }else{
                            toastr.error('操作失败！');
                        }
                    }
                });
            }

        }

        deleteArr=[];
        newImgArr=[];
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

    <!--显示商品删除按钮-->
    function showDeleteImage(obj) {
        $(obj).find('div').show();
    }
    <!--隐藏商品删除按钮-->
    function hideDeleteImage(obj) {
        $(obj).find('div').hide();
    }
</script>

<!--商品子类选择1-->
<script>

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
            var gclassificationName = $("#gclassificationName");
            var gclassification = $("#gclassification");
            gclassificationName.val(v);
            gclassification.val(vId);
        }

        var zNodes;
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $.ajax({
            type:"post",
            url:'/goods/queryGoodsClassificationNode',
            dataType: "json",
            data:{ggId:-1,pageSize:1000,pageNo:1},
            async:false,
            success:function(data){
                let d=data.data;
                var nodeData=new Array();
                for(row of d){
                    let treeRow=new Object();
                    treeRow.id=row.ggId;
                    treeRow.pId=row.parentId;
                    treeRow.name=row.ggName;
                    nodeData.push(treeRow);
                }
                var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
                var node = zTree.getNodeByParam("id",parentId);
                if(node){
                    zTree.selectNode(node);
                }
            }
        });

        function showMenu() {
            var cityObj = $("#gclassificationName");
            var cityOffset = $("#gclassificationName").offset();
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
<!--商品子类选择2-->
<script>

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
        var search_Goods_typeNm = $("#search_Goods_typeNm");
        var search_Goods_type = $("#search_Goods_type");
        search_Goods_typeNm.val(v);
        search_Goods_type.val(vId);
    }

    var zNodes;
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    $.ajax({
        type:"post",
        url:'/goods/queryGoodsClassificationNode',
        dataType: "json",
        data:{ggId:-1,pageSize:1000,pageNo:1},
        async:false,
        success:function(data){
            let d=data.data;
            var nodeData=new Array();
            for(row of d){
                let treeRow=new Object();
                treeRow.id=row.ggId;
                treeRow.pId=row.parentId;
                treeRow.name=row.ggName;
                nodeData.push(treeRow);
            }
            var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
            var node = zTree.getNodeByParam("id",parentId);
            if(node){
                zTree.selectNode(node);
            }
        }
    });

    function showMenu2() {
        var cityObj = $("#search_Goods_typeNm");
        var cityOffset = $("#search_Goods_typeNm").offset();
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

<!--弹出框-->
<script>
    toastr.options = {

        closeButton: false,
        debug: false,
        progressBar: false,
        positionClass: "toast-bottom-center",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "4000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
</script>
</body>
</html>
