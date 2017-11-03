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
                    addImgArr.push(arg[i].alt);
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
<div id="tabDiv">
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoods">
        <button class="btn btn-primary" type="button" onclick="addGoods()">添加商品</button>
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
            <option value="2">乡村和城镇</option>
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

<div id="addandeditgoods" style="display: none">
    <button class="btn btn-default" type="button" onclick="returnback();">返回</button>
    <div class="" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser" >
        <div class="modal-dialog" role="document"  style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="editorSysUserTitle"></h4>
                </div>
                <div class="container" style="width: 100%">
                    <ex:perm url="goods/checkGoods">
                        <button id="bohuibtn" class="btn btn-warning" style="margin-top: 5px;margin-bottom: 5px;float: right;margin-right: 10px;">驳回</button>
                        <button onclick="checkGoodsfunction(1)" class="btn btn-success" style="margin-top: 5px;margin-bottom: 5px;float: right;margin-right: 10px;">通过</button>
                    </ex:perm>
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
                                    <li>
                                        <a href="#goodsSpecificatioPane" data-toggle="tab"  onclick="initGoodsSpecification()" id="nava4">商品规格</a>
                                    </li>
                                    <li>
                                        <a href="#goodsBrandPane" data-toggle="tab"  onclick="refreshmygbTbody()" id="nava5">商品品牌</a>
                                    </li>
                                    <li>
                                        <a href="#goodsLabelPane" data-toggle="tab"  onclick="refreshmyglTbody()" id="nava6">商品标签</a>
                                    </li>
                                    <li>
                                        <a href="#goodsCommentlabelPane" data-toggle="tab"  onclick="refreshmygclTbody()" id="nava7">评论标签</a>
                                    </li>
                                    <li>
                                        <a href="#goodsCouponPane" data-toggle="tab"  onclick="refreshmygcTbody()" id="nava8">商品优惠</a>
                                    </li>
                                    <li>
                                        <a href="#goodsSpecilgoodscredentialPane" data-toggle="tab"  onclick="initgsgdata()" id="nava9">上传证件</a>
                                    </li>
                                    <li>
                                        <a href="#goodsgcPane" data-toggle="tab"  onclick="refreshmyggcomplimentaryrelTbody()" id="nava10">赠品</a>
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
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品名称:
                                                          </span>
                                                                <input type="text" class="form-control" name="gname" id="gname">
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              商品分类:
                                                          </span>
                                                                <input id="gclassification" name="gclassification" type="hidden" />
                                                                <input id="gclassificationName" name="gclassificationName" type="text" class="form-control" onclick="showMenu(); return false;"/>
                                                            </div>
                                                        </div>



                                                        <%--<div class="col-lg-6" style="height:49px;">--%>
                                                            <%--<div class="input-group form-group">--%>
                                                          <%--<span class="input-group-addon">--%>
                                                            <%--所属仓库/地区:--%>
                                                          <%--</span>--%>
                                                                <%--<input id="garea" name="garea" type="hidden" class="form-control" />--%>
                                                                <%--<input id="gareaName" name="gareaName" type="text" class="form-control" />--%>
                                                            <%--</div>--%>
                                                        <%--</div>--%>
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              可售范围:
                                                          </span>
                                                                <input id="gsalescope" name="gsalescope" type="text" class="form-control" placeholder="以千米为单位"/>
                                                            </div>
                                                        </div>



                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品价格（原价）:
                                                          </span>
                                                                <input id="gprice" name="gprice" type="text" class="form-control" placeholder="单位：元"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;" style="height: 49px">
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



                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            计件单位:
                                                          </span>
                                                                <input id="gunitname" name="gunitname" type="text" class="form-control"/>
                                                            </div>
                                                        </div>



                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            生产地信息:
                                                          </span>
                                                                <input id="gproductioninaddress" name="gproductioninaddress" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              生产人姓名:
                                                          </span>
                                                                <input id="gproductioninperson" name="gproductioninperson" type="text" class="form-control"/>
                                                            </div>
                                                        </div>



                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            收集人地址:
                                                          </span>
                                                                <input id="gpickaddress" name="gpickaddress" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              收集人姓名:
                                                          </span>
                                                                <input id="gpickperson" name="gpickperson" type="text" class="form-control"/>
                                                            </div>
                                                        </div>


                                                    <!--定金 详情描述-->

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
                                                        <div class="col-lg-6" style="height:49px;">
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

                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            利润率:
                                                          </span>
                                                                <input type="text" class="form-control" name="gprofitability" id="gprofitability" style="width: 60%">%
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;" id="allowsettimediv">
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

                                                        <div class="col-lg-6" style="height:49px;">
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
                                                        <div class="col-lg-6" style="height:49px;" id="gsalesvolumediv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              销量:
                                                          </span>
                                                                <input id="gsalesvolume" name="gsalesvolume" type="text" class="form-control" value="0"/>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-6" style="height:49px;">
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
                                                        <div class="col-lg-6" style="height:49px;">
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


                                                    <div id="isadvancesalediv">
                                                        <div class="col-lg-6" style="height:49px;">
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
                                                        <div class="col-lg-6" style="height:49px;" id="gedtdiv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              预计发货时间:
                                                          </span>
                                                                <input id="gedt" name="gedts" type="text" class="form-control"/>
                                                            </div>
                                                        </div>
                                                    </div>


                                                        <div class="col-lg-6" style="height:49px;" id="gfreightdiv">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            支持使用优惠券:
                                                          </span>
                                                                <div class="controls">
                                                                    <div class="switch" tabindex="0">
                                                                        <input id="gfreight" name="gfreight" type="checkbox" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    <div  id="iscoddiv">
                                                        <div class="col-lg-6" style="height:49px;">
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


                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            起售数量:
                                                          </span>
                                                                <input id="gstartbuy" name="gstartbuy" type="text" class="form-control"style="width: 75%"/>
                                                                <div class="checkbox checkbox-info" style="display: inline-block">
                                                                    <input id="isMultiplebuy" class="styled" type="checkbox" name="isMultiplebuy">
                                                                    <label for="isMultiplebuy">
                                                                        倍数购买
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6" style="height:49px;">
                                                            <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              限购量:
                                                          </span>
                                                                <input id="gendbuy" name="gendbuy" type="text" class="form-control"/>
                                                            </div>
                                                        </div>

                                                        <div class="col-lg-6" style="height:49px;" id="gdepositdiv">
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
                                                                <button onclick="giupload();return false;" class="btn btn-default" style="margin: 0 auto;margin-top: 33px">+</button>
                                                            </center>
                                                        </div>
                                                    </div>
                                                </form>
                                                <center>
                                                    <button id="editGoodsImageSubmit" class="btn btn-success" >保存</button>
                                                </center>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsSpecificatioPane">
                                            <div class="modal-body" >
                                                <div id="goodsSpecificatiodiv">

                                                </div>
                                                <div id="gstabeldiv" class="row clearfix" style="margin-top: 5px">
                                                    <div class="col-md-12 column">
                                                        <table class="table table-bordered" id="gstabel">

                                                        </table>
                                                    </div>
                                                </div>
                                                <center>
                                                    <button id="editGoodsSpecificatioSubmit" class="btn btn-success" >保存</button>
                                                </center>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsBrandPane">
                                            <div class="modal-body">
                                                <center>
                                                    <div class="form-group" style="margin-top: 5px;">
                                                        <select id="brandselect" class="form-control">
                                                            <option>请选择品牌</option>
                                                        </select>
                                                    </div>
                                                    <ex:perm url="/goods/addGoodsGgbrel">
                                                        <button class="btn btn-default" type="button" onclick="addggbrel()">确定</button>
                                                    </ex:perm>
                                                </center>

                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsLabelPane">
                                            <div class="modal-body">
                                                <center>
                                                    <div class="form-group" style="margin-top: 5px;">
                                                        <input type="text" class="form-control" id="search_GoodsLabel_name" placeholder="请输入标签名称">
                                                    </div>
                                                    <ex:perm url="/goods/queryGoodsLabelLists">
                                                        <button class="btn btn-default" type="button" onclick="searchlabels()">查询</button>
                                                    </ex:perm>

                                                </center>
                                                <ex:perm url="/goods/addGoodsGglrel">
                                                    <button class="btn btn-default" type="button" onclick="addgglrel()" id="addgglrelbtn">新增</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/removeGoodsGglrel">
                                                    <button class="btn btn-default" type="button" onclick="deletegglrel()" id="deletegglrelbtn">删除</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/queryGoodsGglrelLists">
                                                    <button class="btn btn-primary" type="button" onclick="refreshmyglTbody()" style="float: right">刷新</button>
                                                </ex:perm>
                                                <div class="row clearfix" style="margin-top: 5px">
                                                    <div class="col-md-12 column">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 30px">
                                                                    &nbsp;
                                                                </th>
                                                                <th style="text-align: center;" id="toplabelname">
                                                                    品牌名称（已有）
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="myglTbody">

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsCommentlabelPane">
                                            <div class="modal-body">
                                                <center>
                                                    <div class="form-group" style="margin-top: 5px;">
                                                        <input type="text" class="form-control" id="search_GoodsCommentlabel_name" placeholder="请输入评论标签名称">
                                                    </div>
                                                    <ex:perm url="/goods/queryGoodsLabelLists">
                                                        <button class="btn btn-default" type="button" onclick="searchgoodscommentlabels()">查询</button>
                                                    </ex:perm>

                                                </center>
                                                <ex:perm url="/goods/addGoodsGgclrel">
                                                    <button class="btn btn-default" style="float: left" type="button" onclick="addggclrel()" id="addggclrelbtn">新增</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/removeGoodsGgclrel">
                                                    <button class="btn btn-default" style="float: left" type="button" onclick="deleteggclrel()" id="deleteggclrelbtn">删除</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/addGoodsGgclrel">
                                                    <select onchange="changelevel()" id="levelselect" class="form-control" style="float: left;margin-left: 20px;margin-bottom: 10px;width: 100px">
                                                        <option value="1">一星</option>
                                                        <option value="2">二星</option>
                                                        <option value="3">三星</option>
                                                        <option value="4">四星</option>
                                                        <option value="5">五星</option>
                                                    </select>
                                                </ex:perm>
                                                <ex:perm url="/goods/queryGoodsGgclrelLists">
                                                    <button class="btn btn-primary" type="button" onclick="refreshmygclTbody()" style="float: right">刷新</button>
                                                </ex:perm>
                                                <div class="row clearfix" style="margin-top: 5px">
                                                    <div class="col-md-12 column">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 30px">
                                                                    &nbsp;
                                                                </th>
                                                                <th style="text-align: center;" id="topcommentlabelname">
                                                                    商品评论名称（已有）
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="mygclTbody">

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsCouponPane">
                                            <div class="modal-body">
                                                <center>
                                                    <div class="form-group" style="margin-top: 5px;">
                                                        <input type="text" class="form-control" id="search_GoodsCoupon_name" placeholder="请输入优惠名称">
                                                    </div>
                                                    <ex:perm url="/goods/queryGoodsGgcouponrelLists">
                                                        <button class="btn btn-default" type="button" onclick="searchcoupons()">查询</button>
                                                    </ex:perm>

                                                </center>
                                                <ex:perm url="/goods/addGoodsGgcouponrel">
                                                    <button class="btn btn-default" style="float: left" type="button" onclick="addggcrel()" id="addggcouponrelbtn">新增</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/removeGoodsGgcouponrel">
                                                    <button class="btn btn-default" style="float: left" type="button" onclick="deleteggcrel()" id="deleteggcouponrelbtn">删除</button>
                                                </ex:perm>

                                                <ex:perm url="/goods/queryGoodsGgclrelLists">
                                                    <button class="btn btn-primary" type="button" onclick="refreshmygcTbody()" style="float: right;margin-bottom: 10px">刷新</button>
                                                </ex:perm>
                                                <div class="row clearfix" style="margin-top: 5px">
                                                    <div class="col-md-12 column">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 30px">
                                                                    &nbsp;
                                                                </th>
                                                                <th style="text-align: center;" id="topcouponname">
                                                                    商品评论名称（已有）
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="mygcTbody">

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsSpecilgoodscredentialPane">
                                            <div class="modal-body">
                                                <form id="editorGoodsSpecilgoodscredentialImageForm">
                                                    <div class="row">
                                                        <div id="uploadGoodsSpecilgoodscredentialImgBtn"  style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                            <center>
                                                                <button onclick="gsguploadimg();return false;" class="btn btn-default" style="margin: 0 auto;margin-top: 33px">+</button>
                                                            </center>
                                                        </div>

                                                        <div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">
                                                            <img src="" style="width: 100%;height: 100%;" >
                                                            <div onclick="deletegsgImage(this,gsgImgId)" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>
                                                        </div>

                                                    </div>
                                                    <br>

                                                    <div class="row" id="gsgnumberrow">
                                                        <div class="col-lg-3" id="addnumberbtnparent">
                                                            <button onclick="addnumber(this);return false;" style="margin-left: 40%" class="btn btn-default" >+</button>
                                                        </div>

                                                        <div class="col-lg-3" style="color: red;">
                                                            <div class="input-group form-group">
                                                                <input class="form-control" type="text" placeholder="请输入商品编号" style="float: left;width: 70%">
                                                                <span  style="float: left;margin-left:3px;margin-top: 7px; display: block;cursor: pointer">删除</span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </form>


                                                <center>
                                                    <button id="editGoodsSpecilgoodscredentialSubmit" class="btn btn-success" >保存</button>
                                                </center>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="goodsgcPane">
                                            <div class="modal-body">
                                                <center>
                                                    <div class="form-group" style="margin-top: 5px;">
                                                        <input type="text" class="form-control" id="search_Goodsgc_name" placeholder="请输入优惠名称">
                                                    </div>
                                                    <ex:perm url="/goods/queryGoodsComplimentaryLists">
                                                        <button class="btn btn-default" type="button" onclick="searchgcs()">查询</button>
                                                    </ex:perm>

                                                </center>
                                                <ex:perm url="/goods/addGoodsGglrel">
                                                    <button class="btn btn-default" type="button" onclick="addggcomplimentaryrel()" id="addggcrelbtn">新增</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/removeGoodsGglrel">
                                                    <button class="btn btn-default" type="button" onclick="deleteggcomplimentaryrel()" id="deleteggcrelbtn">删除</button>
                                                </ex:perm>
                                                <ex:perm url="/goods/queryGoodsGglrelLists">
                                                    <button class="btn btn-primary" type="button" onclick="refreshmyggcomplimentaryrelTbody()" style="float: right">刷新</button>
                                                </ex:perm>
                                                <div class="row clearfix" style="margin-top: 5px">
                                                    <div class="col-md-12 column">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th style="width: 30px">
                                                                    &nbsp;
                                                                </th>
                                                                <th style="text-align: center;" id="topgcname">
                                                                    赠品名称（已有）
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="mygcomplimentaryrelTbody">

                                                            </tbody>
                                                        </table>
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
        </div>
    </div>
</div>

<!--商品审核框-->
<div class="modal fade" id="checkGoodsModal" tabindex="-1" role="dialog" aria-labelledby="checkGoodsModal">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >驳回意见</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control" id="checkGoodsTextarea">

                </textarea>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeCheckGoodsModal()">关闭</button>
                <button type="button" class="btn btn-primary" onclick="checkGoodsfunction(2)" >确认</button>
            </div>
        </div>

    </div>
</div>

<!-- 公用下拉择树1 -->
<div id="menuContent1" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo1" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<!-- 公用下拉择树1 -->
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

<img style="width: 200px;height: 200px;display: none;position: absolute" src="" id="mybigimg">
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>
<!--商品基本信息-->
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
                            refreshTableData();
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

    function editorGoods(gid){
        editorGoodsType=1;
        initFormData(gid);
        $("#editorSysUserTitle").text("编辑商品");
        $('#tabDiv').hide();
        $('#addandeditgoods').show();
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
        $('#tabDiv').hide();
        $('#addandeditgoods').show();
    }

    function returnback() {
        $('#tabDiv').show();
        $('#addandeditgoods').hide();
    }

</script>

<!--商品审查-->
<script>
    $(function () {
        $('#bohuibtn').click(function () {
            $('#checkGoodsModal').modal('show');
        });
    });

    function checkGoodsfunction(myidea) {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.error("请先保存商品！");
            return;
        }
        var message = $('#checkGoodsTextarea').val();

        $.ajax({
            type:"post",
            url:"/goods/checkGoods",
            dataType: "json",
            data:{gid:gid,message:message,myidea:myidea},
            success:function(data){
                if(data.status==0){
                    toastr.success(data.msg);
                    refreshTableData();
                    $('#checkGoodsModal').modal('hide');
                }else{
                    toastr.error(data.msg);
                }
            }
        });
    }

    function closeCheckGoodsModal() {
        $('#checkGoodsModal').modal('hide');
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
                $('#gdepositdiv').show();
                $('#maincomtrayside').attr('checked','checked');
                $('#iscoddiv').hide();
            }else{
                $('#allowsettimediv').show();
                $('#isadvancesalediv').show();
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

            flag = $("#gfreight").bootstrapSwitch("state");
            if(flag){
                formData["gfreight"]=1;
            }else{
                formData["gfreight"]=0;
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
                        refreshTableData();
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
                    $("#gstartbuy").val('');
                    $("#gendbuy").val('');
                    $("#gsalesvolume").val('');
                    $("#gedt").val('');
                    $("#gprofitability").val('');
                    $("#gdeposit").val('');

                    $("#gfreight").val('');
                    $("#gfreight").bootstrapSwitch("state",true);

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


                    flag = false;
                    $("#gfreight").val(goodsConfig.gfreight);
                    if(goodsConfig.gfreight==1){
                        flag = true;
                    }
                    $("#gfreight").bootstrapSwitch("state",flag);

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
                    url:"/goods/removeGoodsImage",
                    dataType: "json",
                    data:{giId:deleteArr[i]},
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

    function giupload() {
        uploadImgFlag = 1;
        upImage();
    }
</script>

<!--商品规格-->
<script>
    var uploadimgindex = 0;
    var imglength = 0;
    $(function () {
        $('#editGoodsSpecificatioSubmit').click(function () {
            if($('#gid').val()=='' || $('#gclassification').val()==''){
                toastr.warning('请先保存商品!');
                document.getElementById("nava1").click();
                return;
            }

            var index = 0;
            var inputs = $('#gstabel').find('input[type!=file]');
            for(var i=0; i<inputs.length; i++){
                if(inputs[i].value == ''){
                    index ++;
                }
            }
            if(index != 0){
                toastr.warning('请把数据填写完整!');
                return;
            }
            if(inputs!=null && inputs.length>0) {
                for (var i = 0; i < inputs.length; i++) {
                    if(isNaN(inputs[i].value)){
                        toastr.warning("请填写数字");
                        return;
                    }
                }
            }

            var gid = $('#gid').val();
            clearggsdata(gid);

        });


    });

    <!--初始化规格-->
    function initGoodsSpecification() {
        clearggsdatas();
        var ggId = $('#gclassification').val();
        var gid = $('#gid').val();
        if(gid == ''){
            toastr.warning('请先保存商品!');
            return;
        }
        if(gclassification == ''){
            toastr.warning('请先选择商品分类!');
            return;
        }
        getggHome(ggId);
    }

    function getggHome(ggId) {
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsClassificationById",
            dataType: "json",
            data:{ggId:ggId,pageNo:1,pageSize:2},
            success:function(data){
                var goodsClassification = data.goodsClassification;
                if(goodsClassification!=null){
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsTypeById",
                        dataType: "json",
                        data:{gtId:goodsClassification.ggHome},
                        success:function(data){
                            var goodsType = data.goodsType;
                            if(goodsType!=null && goodsType.isSpecification==1){
                                loadSpecifications(goodsType.gtId);
                            }

                        }
                    });

                }
            }
        });
    }

    function loadSpecifications(gtId) {
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGtgsrelLists",
            dataType: "json",
            data:{gtId:gtId,pageNo:1,pageSize:100},
            success:function(data){
                var gtgsrels = data.rows;
                var html = '';
                if(gtgsrels != null && gtgsrels.length>0){
                    for(var i=0; i<gtgsrels.length; i++){
                        html += '<div class="row">'+
                            '<div class="col-lg-1" style="text-align: right;margin-top: 10px">'+
                            '<input type="hidden" name="specificationId" value="'+gtgsrels[i].gsId+'">'+
                        '<span >'+gtgsrels[i].gsname+'</span>'+
                        '</div>'+
                        '<div class="col-lg-11">'+
                        '<div class="row">'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                    }
                    $('#goodsSpecificatiodiv').html(html);
                    loadgsvals();
                }
            }
        });
    }
    <!--加载规格值-->
    function loadgsvals() {
        var gsIds = $('input[name="specificationId"]');
        if(gsIds!=null && gsIds.length>0){
            for(var i=0; i<gsIds.length; i++){
                loadgsval(gsIds[i],i,gsIds.length-1);
            }
        }
    }
    function loadgsval(obj,x,y) {
        var showobj = $($(obj).parent().next().children().get(0));
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGsvalLists",
            dataType: "json",
            async:false,
            data:{gsId:$(obj).val(),pageNo:1,pageSize:100},
            success:function(data){
                var html = '';
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html += '<div class="col-lg-2">'+
                                '<div class="checkbox checkbox-info" style="display: block">'+
                                '<input onclick="createtabel();" id="gsvalId'+i+rows[i].gsvVal+'" class="styled" name="gsvalId" type="checkbox" value="'+rows[i].gsvId+'">'+
                                '<label for="gsvalId'+i+rows[i].gsvVal+'">'+ rows[i].gsvVal+'</label>'+
                                '</div>'+
                                '</div>';
                    }
                    showobj.html(html);
                    if(x==y){
                        var gid = $('#gid').val();
                        inithavegsandcreatetabel(gid);
                    }
                }
            }
        });
    }
    <!--创建表格-->
    function createtabel() {
        var gsIds = new Array();
        var gsNames = new Array();
        var gsvalId = $('input[name="gsvalId"]:checked');
        var index = 0;
        var a = 0;
        for(var i=0; i<gsvalId.length; i++){
            var parentdiv = $($(gsvalId[i]).parent().parent().parent().parent().prevAll().get(0));
            var gsid = $(parentdiv.find('input').get(0));
            if(gsIds.length==0){
                gsIds.push(gsid);
                gsNames.push($(parentdiv.find('span').get(0)));
            }else{
                var flag = false;
                for (var j=0; j<gsIds.length; j++){
                    if(gsid.val() == gsIds[j].val()){
                        flag = true;
                    }
                }
                if(!flag){
                    gsIds.push(gsid);
                    gsNames.push($(parentdiv.find('span').get(0)));
                }
            }

        }

        index = gsIds.length;
        if(index == 0){
            $('#gstabeldiv').hide();
        }else{
                var html = '<thead><tr>';

                for(var i=0; i<gsNames.length; i++){
                    html += '<th > <input type="hidden" value="'+gsIds[i].val()+'">'+gsNames[i].html()+'</th>';
                }
                html += '<th >'+
                        '售价'+
                        '</th>'+
                        '<th >'+
                        '成本'+
                        '</th>'+
                        '<th >'+
                        '库存'+
                        '</th>'+
                        '<th >'+
                        '剩余库存'+
                        '</th>'+
                        '<th >'+
                        '图片'+
                        '</th>'+
                        '<th >'+
                        '操作'+
                        '</th>'+
                        '</tr>'+
                        '</thead>'+
                        '<tbody id="ggsvaldetailTbody">';
                if(index == 1){
                    var tabelIdArr = new Array();
                    var tabelNameArr = new Array();
                    for(var i=0; i<gsvalId.length; i++){
                        tabelIdArr[i] = gsvalId[i].value;
                        tabelNameArr[i] = $(gsvalId[i]).next().html();
                    }
                    for(var i=0; i<tabelIdArr.length; i++){
                        html += '<tr>'+
                            '<td> <input type="hidden" value="'+tabelIdArr[i]+'"> '+tabelNameArr[i]+' </td>'+
                            '<td> <input class="form-control" type="text" placeholder="售价"> </td>'+
                            '<td> <input class="form-control" type="text" placeholder="成本"> </td>'+
                            '<td> <input class="form-control" type="text" placeholder="库存"> </td>'+
                            '<td> <input class="form-control" type="text" placeholder="剩余库存"> </td>'+
                            '<td> <form>' +
                            '<input type="hidden" value="-1"> ' +
                            '<img style="display: none;width: 20px;height: 20px;margin-top:5px;float:left" onmouseover="showbigimg(this)" onmouseleave="hidebigimg()"/>' +
                            '<input name="dht_image_upload" style="margin-left:20px;" type="file" placeholder="上传图片"> ' +
                            '</form> </td>'+
                            '<td> <button class="btn btn-primary" onclick="deleterow(this);">删除</button> </td>'+
                            '</tr>';

                    }
                }
                if(index == 2){
                    var gsvalNamesArr1 = new Array();
                    var gsvalNamesArr2 = new Array();

                     var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                    }

                     var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr2.length; i++){
                        gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                    }

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        for(var j=0; j<gsvalIdsArr2.length; j++){
                            html += '<tr>'+
                                '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                                '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                                '<td> <input class="form-control" type="text" placeholder="售价"> </td>'+
                                '<td> <input class="form-control" type="text" placeholder="成本"> </td>'+
                                '<td> <input class="form-control" type="text" placeholder="库存"> </td>'+
                                '<td> <input class="form-control" type="text" placeholder="剩余库存"> </td>'+
                                '<td> <form>' +
                                '<input type="hidden" value="-1"> ' +
                                '<img style="display: none;width: 20px;height: 20px;margin-top:5px;float:left" onmouseover="showbigimg(this)" onmouseleave="hidebigimg()"/>' +
                                '<input name="dht_image_upload" style="margin-left:20px;" type="file" placeholder="上传图片"> ' +
                                '</form> </td>'+
                                '<td> <button class="btn btn-primary" onclick="deleterow(this);">删除</button> </td>'+
                                '</tr>';
                        }
                    }
                }
                if(index == 3){
                    var gsvalNamesArr1 = new Array();
                    var gsvalNamesArr2 = new Array();
                    var gsvalNamesArr3 = new Array();

                    var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                    }

                    var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr2.length; i++){
                        gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                    }

                    var gsvalIdsArr3 = gsIds[2].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr3.length; i++){
                        gsvalNamesArr3.push($(gsvalIdsArr3[i]).next());
                    }

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        for(var j=0; j<gsvalIdsArr2.length; j++){
                            for(var x=0; x<gsvalIdsArr3.length; x++){
                                html += '<tr>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr3[x].value+'"> '+gsvalNamesArr3[x].html()+' </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="售价"> </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="成本"> </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="库存"> </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="剩余库存"> </td>'+
                                    '<td> <form>' +
                                    '<input type="hidden" value="-1"> ' +
                                    '<img style="display: none;width: 20px;height: 20px;margin-top:5px;float:left" onmouseover="showbigimg(this)" onmouseleave="hidebigimg()"/>' +
                                    '<input name="dht_image_upload" style="margin-left:20px;" type="file" placeholder="上传图片"> ' +
                                    '</form> </td>'+
                                    '<td> <button class="btn btn-primary" onclick="deleterow(this);">删除</button> </td>'+
                                    '</tr>';
                            }
                        }
                    }
                }
                if(index == 4){
                    var gsvalNamesArr1 = new Array();
                    var gsvalNamesArr2 = new Array();
                    var gsvalNamesArr3 = new Array();
                    var gsvalNamesArr4 = new Array();

                    var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                    }

                    var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr2.length; i++){
                        gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                    }

                    var gsvalIdsArr3 = gsIds[2].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr3.length; i++){
                        gsvalNamesArr3.push($(gsvalIdsArr3[i]).next());
                    }

                    var gsvalIdsArr4 = gsIds[3].parent().next().find('input:checked');

                    for(var i=0; i<gsvalIdsArr4.length; i++){
                        gsvalNamesArr4.push($(gsvalIdsArr4[i]).next());
                    }

                    for(var i=0; i<gsvalIdsArr1.length; i++){
                        for(var j=0; j<gsvalIdsArr2.length; j++){
                            for(var x=0; x<gsvalIdsArr3.length; x++){
                                for(var y=0; y<gsvalIdsArr4.length; y++){
                                    html += '<tr>'+
                                        '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                                        '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                                        '<td> <input type="hidden" value="'+gsvalIdsArr3[x].value+'"> '+gsvalNamesArr3[x].html()+' </td>'+
                                        '<td> <input type="hidden" value="'+gsvalIdsArr4[y].value+'"> '+gsvalNamesArr4[y].html()+' </td>'+
                                        '<td> <input class="form-control" type="text" placeholder="售价"> </td>'+
                                        '<td> <input class="form-control" type="text" placeholder="成本"> </td>'+
                                        '<td> <input class="form-control" type="text" placeholder="库存"> </td>'+
                                        '<td> <input class="form-control" type="text" placeholder="剩余库存"> </td>'+
                                        '<td> <form>' +
                                        '<input type="hidden" value="-1"> ' +
                                        '<img style="display: none;width: 20px;height: 20px;margin-top:5px;float:left" onmouseover="showbigimg(this)" onmouseleave="hidebigimg()"/>' +
                                        '<input name="dht_image_upload" style="margin-left:20px;" type="file" placeholder="上传图片"> ' +
                                        '</form> </td>'+
                                        '<td> <button class="btn btn-primary" onclick="deleterow(this);">删除</button> </td>'+
                                        '</tr>';
                                }
                            }
                        }
                    }
                }

                html += '</tbody>';
                $('#gstabel').html(html);





            $('#gstabeldiv').show();
        }
    }
    <!--清除所有关系-->
    function clearggsdata(gid) {
        $.ajax({
            type:"post",
            url:"/goods/clearAllGgsrel",
            dataType: "json",
            data:{gid:gid},
            success:function(data){
                refreshTableData();
                var imgs = $('#gstabel').find('input[type="file"]');
                imglength = imgs.length;
                uploadimgindex = 0;

                var index = 0;
                for(var i=0; i<imgs.length; i++){
                    if(imgs[i].value!='' && imgs[i].value!=null){
                        index ++;
                    }
                }

                if(index==0 || index==imglength){
                    if(index != 0){
                        for(var i=0; i<imgs.length; i++){
                            uploadimgfunction(imgs[i]);
                        }
                    }else{
                        submitggsvaldetaildata();
                    }
                }else{
                    toastr.warning("请将图片上传完整！");
                }
            }
        });
    }
    <!--删除行-->
    function deleterow(obj) {
        $(obj).parent().parent().remove();
    }
    
    function uploadimgfunction(obj) {
            var fd = new FormData($(obj).parent()[0]);
            fd.append("imageUse","image/jpeg");
            fd.append("isWatermark","false");
            fd.append("isCompress", "false");
            $.ajax({
                type:"post",
                url:"/file/imageUpload",
                dataType: "json",
                data:fd,
                processData : false,
                contentType : false,
                success:function(data){
                    var original = data.original;
                    $(obj).parent().children().get(0).value = original;
                    uploadimgindex++;
                    if(uploadimgindex == imglength){
                        submitggsvaldetaildata();
                    }
                }
            });




    }

    function submitggsvaldetaildata() {
           var ggsvaldetailTtrs = $('#ggsvaldetailTbody').children();
           var gid = $('#gid').val();
           if(ggsvaldetailTtrs!=null && ggsvaldetailTtrs.length>0){

               for(var i=0; i<ggsvaldetailTtrs.length;i++){
                   var ggsvaldetailTtds = $(ggsvaldetailTtrs[i]).children();

                   var gdImgid = $(ggsvaldetailTtds[ggsvaldetailTtds.length-2]).find('input').get(0).value;
                   var gdResidueinventory = $(ggsvaldetailTtds[ggsvaldetailTtds.length-3]).find('input').get(0).value;
                   var gdInventory = $(ggsvaldetailTtds[ggsvaldetailTtds.length-4]).find('input').get(0).value;
                   var gdCostprice = $(ggsvaldetailTtds[ggsvaldetailTtds.length-5]).find('input').get(0).value;
                   var gdPrice = $(ggsvaldetailTtds[ggsvaldetailTtds.length-6]).find('input').get(0).value;

                   $.ajax({
                       type:"post",
                       url:"/goods/addGoodsDetail",
                       dataType: "json",
                       async :false,
                       data:{gdImgid:gdImgid,gdResidueinventory:gdResidueinventory,gdInventory:gdInventory,gdCostprice:gdCostprice,gdPrice:gdPrice,gid:gid},
                       success:function(data){
                            for(var j=0; j<ggsvaldetailTtds.length-6;j++){
                                var gsId = $($($('#gstabel').find('tr').get(0)).find('th').get(j)).find('input').get(0).value;

                                var gdId = data.goodsDetail.gdId;
                                var gsvId = $(ggsvaldetailTtds[j]).find('input').get(0).value;
                                $.ajax({
                                    type:"post",
                                    url:"/goods/addGoodsGgsvalDetail",
                                    dataType: "json",
                                    data:{gsId:gsId,gid:gid,gdId:gdId,gsvId:gsvId},
                                    success:function(data){

                                    }
                                });
                            }
                       }
                   });
               }

               var gsvIds = $('#goodsSpecificatiodiv').find('input:checked');
               if(gsvIds!=null && gsvIds.length>0){
                   for(var i=0; i<gsvIds.length; i++){
                       var gsvId = gsvIds[i].value;
                       var gsId = $($(gsvIds[i]).parent().parent().parent().parent().prevAll().get(0)).find('input').get(0).value;
                       $.ajax({
                           type:"post",
                           url:"/goods/addGoodsGgsval",
                           dataType: "json",
                           data:{gsId:gsId,gid:gid,gsvId:gsvId},
                           success:function(data){

                           }
                       });
                   }
               }
           }
            toastr.success("操作成功！");
    }

    function inithavegsandcreatetabel(gid) {
        var allgscheckbox = $('#goodsSpecificatiodiv').find('input[type=checkbox]');

        $.ajax({
            type:"post",
            url:"/goods/queryGgsrelLists",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:1000},
            success:function(data){
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    for (var i=0; i<rows.length; i++){
                        for(var j=0; j<allgscheckbox.length; j++){
                            if(rows[i].gsvId == allgscheckbox[j].value){
                                $(allgscheckbox[j]).attr('checked','checked');
                            }
                        }
                    }
                    createtabel();
                    loadgsdata();
                }
            }
        });
    }

    function loadgsdata() {
        var ggsvaldetailtrs = $('#ggsvaldetailTbody').children();
        var gid = $('#gid').val();
        if(ggsvaldetailtrs!=null && ggsvaldetailtrs.length>0){
            for(var i=0;i<ggsvaldetailtrs.length; i++){
                var ggsvaldetailtds = $(ggsvaldetailtrs[i]).children();
                var gdIds = new Array();
                for(var j=0; j<ggsvaldetailtds.length-6; j++){
                    var gsvId = $(ggsvaldetailtds[j]).find('input').get(0).value;
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsGgsvalDetailLists",
                        dataType: "json",
                        async:false,
                        data:{gid:gid,gsvId:gsvId,pageNo:1,pageSize:100},
                        success:function(data){
                            var rows = data.rows;
                            if(rows!=null && rows.length>0) {
                                for (var k = 0; k < rows.length; k++) {
                                    gdIds.push(rows[k].gdId);
                                }

                            }
                        }
                    });
                }

                var gdId = getMostTimes(gdIds);

                if(gdId == null){
                    $(ggsvaldetailtrs[i]).remove();
                }else{
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsDetailById",
                        dataType: "json",
                        async:false,
                        data:{gdId:gdId},
                        success:function(data){
                            var goodsDetail = data.goodsDetail;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-6]).find('input').get(0).value = goodsDetail.gdPrice;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-5]).find('input').get(0).value = goodsDetail.gdCostprice;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-4]).find('input').get(0).value = goodsDetail.gdInventory;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-4]).find('input').attr('disabled','disabled');
                            $(ggsvaldetailtds[ggsvaldetailtds.length-3]).find('input').get(0).value = goodsDetail.gdResidueinventory;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-3]).find('input').attr('disabled','disabled');
                            $(ggsvaldetailtds[ggsvaldetailtds.length-2]).find('input').get(0).value = goodsDetail.gdImgid;
                            if(goodsDetail.imgUrl.substring(goodsDetail.imgUrl.length-4)!="null"){
                                $(ggsvaldetailtds[ggsvaldetailtds.length-2]).find('img').get(0).src = goodsDetail.imgUrl;
                                $(ggsvaldetailtds[ggsvaldetailtds.length-2]).find('img').show();
                            }
                        }
                    });
                }

            }
        }

    }

    function getMostTimes(arr) {
        if(arr!=null && arr.length>0){
            var MostTimeObj = null;
            var times = new Array();
            for(var i=0; i<arr.length;i++){
                var index = 0;
                for(var j=0; j<arr.length;j++){
                    if(arr[i]==arr[j]){
                        index ++;
                    }
                }
                times.push(index);
            }
            var largst = 0;
            for(var i=0; i<times.length;i++){
                if(times[i]>largst){
                    largst = times[i];
                }
            }

            var gsIds = new Array();
            var gsvalId = $('input[name="gsvalId"]:checked');
            var index = 0;
            var a = 0;
            for(var i=0; i<gsvalId.length; i++){
                var parentdiv = $($(gsvalId[i]).parent().parent().parent().parent().prevAll().get(0));
                var gsid = $(parentdiv.find('input').get(0));
                if(gsIds.length==0){
                    gsIds.push(gsid);
                }else{
                    var flag = false;
                    for (var j=0; j<gsIds.length; j++){
                        if(gsid.val() == gsIds[j].val()){
                            flag = true;
                        }
                    }
                    if(!flag){
                        gsIds.push(gsid);
                    }
                }

            }

            if(largst==1 && (arr.length>1 && gsIds.length>0)){
                return null;
            }

            var flag;
            for(var i=0; i<times.length;i++){
                if(times[i]==largst){
                    flag = arr[i];
                    break;
                }
            }
            return flag;

        }
    }

    function showbigimg(obj) {
        $('#mybigimg')[0].src = obj.src;
        var top =$(obj).offset().top;
        var left = $(obj).offset().left - 200;
        $('#mybigimg').css('top',top+"px");
        $('#mybigimg').css('left',left+"px");
        $('#mybigimg').show();
    }
    function hidebigimg() {
        $('#mybigimg').hide();
    }
    
    function clearggsdatas() {
        $('#goodsSpecificatiodiv').html('');
        $('#gstabel').html('');
    }
</script>

<!--商品与品牌关系-->
<script>
    var initdataArr = new Array();

    function refreshmygbTbody() {
        var gid = $('#gid').val();
        searchbrands();
    }


    function searchbrands() {
        var ggId = $('#gclassification').val();
        if(ggId == ''){
            toastr.warning('请先保存商品!');
            return;
        }
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsGtgbrelLists",
            dataType: "json",
            data: {ggId:ggId, pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '<option>请选择品牌</option>';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        html += '<option value="'+rows[i].gbId+'">'+rows[i].brandname+'</option>';
                    }
                }
                $('#brandselect').html(html);

                if(rows!=null){
                    $.ajax({
                        type: "post",
                        url: "/goods/queryGoodsGgbrelLists",
                        dataType: "json",
                        data: {gid:$('#gid').val(), pageNo: 1, pageSize: 100},
                        success: function (data) {
                            var rows = data.rows;
                            if(rows!=null){
                                var brandselectchildren = $('#brandselect').children();
                                for(var x=0; x<brandselectchildren.length; x++){
                                    if(brandselectchildren[x].value==rows[0].gbId){
                                        $(brandselectchildren[x]).attr('selected','selected');
                                    }
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    function addggbrel() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var brandselectval = $('#brandselect').val();
        var addggb = ","+brandselectval;
        $.ajax({
            type: "post",
            url: "/goods/addGoodsGgbrel",
            dataType: "json",
            data: {gbIds: addggb,gid:gid},
            success: function (data) {
                updateGoodsSetNotChecked();
                toastr.success('操作成功!');
            }
        });
    }

</script>

<!--商品与标签关系-->
<script>
    var initgldataArr = new Array();

    function refreshmyglTbody() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        initgglFormData(gid);
    }
    /**
     * 清除form 表单数据
     * */
    function initgglFormData(gid){
        $('#toplabelname').html('标签名称（已有）');
        initgldataArr = [];
        $('#addgglrelbtn').hide();
        $('#deletegglrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGglrelLists",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="gglcheckbox" class="styled" type="checkbox" value="'+rows[i].gglId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].labelname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgldataArr.push(rows[i].glId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定标签</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#myglTbody').html(html);
            }
        });
    }

    function searchlabels() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var glName = $('#search_GoodsLabel_name').val();
        $('#toplabelname').html('标签名称');
        $('#addgglrelbtn').show();
        $('#deletegglrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsLabelLists",
            dataType: "json",
            data: {glName: glName,now:new Date().format("yyyy-MM-dd hh:mm:ss"),pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgldataArr.length>0){
                            for (var j=0; j<initgldataArr.length; j++){
                                if (rows[i].glId==initgldataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gglcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].glId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].glName+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的品牌</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#myglTbody').html(html);
            }
        });
    }

    function addgglrel() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var checkboxs = $('input:checkbox[name="gglcheckbox"]:checked');
        var addggb = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggb += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGglrel",
                dataType: "json",
                data: {glIds: addggb,gid:gid},
                success: function (data) {
                    toastr.success('新增成功!');
                    refreshmyglTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    function deletegglrel() {
        var checkboxs = $('input:checkbox[name="gglcheckbox"]:checked');
        if(checkboxs.length > 0){
            var gglIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    gglIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGglrel",
                dataType: "json",
                data: {gglIds: gglIds},
                success: function (data) {
                    toastr.success('删除成功!');
                    refreshmyglTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
</script>

<!--商品与评论标签关系-->
<script>
    var initgcldataArr = new Array();

    function refreshmygclTbody() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var ggId = $('#gclassification').val();
        if(ggId==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }

        initggclFormData(gid);


    }
    /**
     * 清除form 表单数据
     * */
    function initggclFormData(gid){
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var level = $('#levelselect').val();
        $('#toplabelname').html('评论标签名称（已有）');
        initgcldataArr = [];
        $('#addggclrelbtn').hide();
        $('#deleteggclrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGgclrelLists",
            dataType: "json",
            data:{gid:gid,level:level,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="ggclcheckbox" class="styled" type="checkbox" value="'+rows[i].ggclId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gclname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgcldataArr.push(rows[i].gclId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定标签</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygclTbody').html(html);
            }
        });
    }

    function searchgoodscommentlabels() {
        var gclName = $('#search_GoodsCommentlabel_name').val();

        $('#topcommentlabelname').html('评论标签名称');
        $('#addggclrelbtn').show();
        $('#deleteggclrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsGtgclrelLists",
            dataType: "json",
            data: {gclName:gclName,ggId:$('#gclassification').val(),pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgcldataArr.length>0){
                            for (var j=0; j<initgcldataArr.length; j++){
                                if (rows[i].gclId==initgcldataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="ggclcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gclId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gclname+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的品牌</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygclTbody').html(html);
            }
        });
    }

    function addggclrel() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var checkboxs = $('input:checkbox[name="ggclcheckbox"]:checked');
        var addggcl = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggcl += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGgclrel",
                dataType: "json",
                data: {gclIds: addggcl,level:$('#levelselect').val(),gid:gid},
                success: function (data) {
                    toastr.success('新增成功!');
                    refreshmygclTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    function deleteggclrel() {
        var checkboxs = $('input:checkbox[name="ggclcheckbox"]:checked');
        if(checkboxs.length > 0){
            var ggclIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    ggclIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGgclrel",
                dataType: "json",
                data: {ggclIds: ggclIds},
                success: function (data) {
                    toastr.success('删除成功!');
                    refreshmygclTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    function changelevel() {
        refreshmygclTbody();
    }
</script>

<!--商品与优惠关系-->
<script>
    var initgcdataArr = new Array();

    function refreshmygcTbody() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        initggcFormData(gid);
    }
    /**
     * 清除form 表单数据
     * */
    function initggcFormData(gid){
        $('#topcouponname').html('优惠名称（已有）');
        initgcdataArr = [];
        $('#addggcouponrelbtn').hide();
        $('#deleteggcouponrelbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGgcouponrelLists",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   +=  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="ggccheckbox" class="styled" type="checkbox" value="'+rows[i].ggcId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gcname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgcdataArr.push(rows[i].gcId);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定优惠</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygcTbody').html(html);
            }
        });
    }

    function searchcoupons() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var gcName = $('#search_GoodsCoupon_name').val();
        $('#topgoodsname').html('优惠名称');
        $('#addggcouponrelbtn').show();
        $('#deleteggcouponrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goodsCoupon/queryGoodsCoupons",
            dataType: "json",
            data: {gcpName: gcName,now:new Date().format("yyyy-MM-dd hh:mm:ss"),pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgcdataArr.length>0){
                            for (var j=0; j<initgcdataArr.length; j++){
                                if (rows[i].gcpId==initgcdataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="ggccheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gcpId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gcpName+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的优惠</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygcTbody').html(html);
            }
        });
    }

    function addggcrel() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var checkboxs = $('input:checkbox[name="ggccheckbox"]:checked');
        var addggc = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggc += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGgcouponrel",
                dataType: "json",
                data: {gcIds: addggc,gid:gid},
                success: function (data) {
                    toastr.success('新增成功!');
                    refreshmygcTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    function deleteggcrel() {
        var checkboxs = $('input:checkbox[name="ggccheckbox"]:checked');
        if(checkboxs.length > 0){
            var ggcIds = "";
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    ggcIds += checkboxs[i].value + ",";
                }
            }

            $.ajax({
                type: "post",
                url: "/goods/removeGoodsGgcouponrel",
                dataType: "json",
                data: {ggcIds: ggcIds},
                success: function (data) {
                    toastr.success('删除成功!');
                    refreshmygcTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

</script>

<!--商品与证件关系-->
<script>
    var uploadImgFlag = 0;

    var addImgArr = new Array();
    var delImgArr = new Array();

    var addgsgParent = new Array();
    var delgsgParent = new Array();
    var updgsgParent = new Array();

    function addnumber(obj) {

        var html = '<div class="col-lg-3" style="color: red;">'+
                    '<div class="input-group form-group">' +
                    '<input type="hidden">'+
                    '<input onfocus="focusnumber(this);" onblur="blurnumber(this);" class="form-control" type="text" placeholder="请输入商品编号" style="float: left;width: 70%">'+
                    '<span  onclick="deletenumber(this);" style="float: left;margin-left:3px;margin-top: 7px; display: block;cursor: pointer">删除</span>'+
                    '</div>'+
                    '</div>';
        $(obj).parent().after(html);
    }
    function deletenumber(obj) {
        var gsgId = $(obj).parent().find('input[type=hidden]').get(0).value;
        if(gsgId!=''){
            delgsgParent.push(gsgId);
        }
        $(obj).parent().parent().remove();
    }

    function gsguploadimg() {
        uploadImgFlag = 0;
        upImage();
    }

    function deletegsgImage(obj,imgId) {
        var index = 0;
        for(var i=0; i<addImgArr.length; i++){
            if(addImgArr[i]==imgId){
                addImgArr.splice(i,1);
                index = 1;
            }
        }
        if(index==0){
            delImgArr.push(imgId);
        }
        $(obj).parent().remove();
    }

    var numberchange;
    function focusnumber(obj) {
        numberchange = $(obj).val();

    }
    function blurnumber(obj) {
        var preval = $(obj).prev().val();
        if(preval!=''){
            var flag = $(obj).val() == numberchange;
            if(!flag){
                updgsgParent.push($(obj).parent())
            }
        }
    }

    $(function () {
        $('#editGoodsSpecilgoodscredentialSubmit').click(function () {
            var gid = $('#gid').val();
            if(gid==''){
                toastr.warning('请先保存商品!');
                document.getElementById("nava1").click();
                return;
            }
            var gsgnumberrowinputs = $('#gsgnumberrow').find('input[type=text]');
            if(gsgnumberrowinputs!=null && gsgnumberrowinputs.length>0){
                for(var i=0;i<gsgnumberrowinputs.length;i++){
                    if(gsgnumberrowinputs[i].value==''){
                        toastr.warning('请将数据填写完整!');
                        return;
                    }

                }
            }


            if(addImgArr!=null && addImgArr.length>0){
                for(var i=0; i<addImgArr.length; i++){
                    $.ajax({
                        type:"post",
                        url:"/goods/addGoodsSpecilgoodscredential",
                        dataType: "json",
                        async :false,
                        data:{gid:gid,gsgImgid:addImgArr[i],gsgType:1},
                        success:function(data){
                            if(i==addImgArr.length-1){
                                addImgArr=[];
                            }
                        }
                    });
                }
                toastr.success('操作成功');
                updateGoodsSetNotChecked();
            }

            if(delImgArr!=null && delImgArr.length>0){
                for(var i=0; i<delImgArr.length; i++){
                    $.ajax({
                        type:"post",
                        url:"/goods/removeGoodsSpecilgoodscredential",
                        dataType: "json",
                        async :false,
                        data:{gsgId:delImgArr[i]},
                        success:function(data){
                            if(i==delImgArr.length-1){
                                delImgArr=[];
                            }
                        }
                    });
                }
                toastr.success('操作成功');
                updateGoodsSetNotChecked();
            }

            var gsghidden = $("#gsgnumberrow").find('input[type=hidden]');
            if(gsghidden!=null && gsghidden.length>0){
                for(var i=0;i<gsghidden.length; i++){
                    if(gsghidden[i].value==''){
                        addgsgParent.push($(gsghidden[i]).next());
                    }
                }
            }

            if(addgsgParent!=null && addgsgParent.length>0){
                for(var i=0; i<addgsgParent.length; i++){
                    $.ajax({
                        type:"post",
                        url:"/goods/addGoodsSpecilgoodscredential",
                        dataType: "json",
                        async :false,
                        data:{gid:gid,gsgType:0,gsgNumber:addgsgParent[i].val()},
                        success:function(data){
                            if(i==addgsgParent.length-1){
                                addgsgParent=[];
                            }
                        }
                    });
                }
                toastr.success('操作成功');
                updateGoodsSetNotChecked();
            }

            if(delgsgParent!=null && delgsgParent.length>0){
                for(var i=0; i<delgsgParent.length; i++){
                    $.ajax({
                        type:"post",
                        url:"/goods/removeGoodsSpecilgoodscredential",
                        dataType: "json",
                        async :false,
                        data:{gsgId:delgsgParent[i]},
                        success:function(data){
                            if(i==delgsgParent.length-1){
                                delgsgParent=[];
                            }
                        }
                    });
                }
                toastr.success('操作成功');
                updateGoodsSetNotChecked();
            }

            if(updgsgParent!=null && updgsgParent.length>0){
                for(var i=0; i<updgsgParent.length; i++){
                    $.ajax({
                        type:"post",
                        url:"/goods/editorGoodsSpecilgoodscredential",
                        dataType: "json",
                        async :false,
                        data:{gid:gid,gsgId:updgsgParent[i].children().get(0).value,gsgNumber:updgsgParent[i].children().get(1).value,gsgType:0,isDelete:0},
                        success:function(data){
                            if(i==updgsgParent.length-1){
                                updgsgParent=[];
                            }
                        }
                    });
                }
                toastr.success('操作成功');
                updateGoodsSetNotChecked();
            }


        });
    });
    
    function initgsgdata() {
        addImgArr = [];
        delImgArr = [];
        addgsgParent = [];
        delgsgParent = [];
        updgsgParent = [];

        var gid = $('#gid').val();
        if (gid == '') {
            toastr.warning('请先保存商品!');
            $('#editorGoodsSpecilgoodscredentialImageForm').hide();
            return;
        } else {
            $('#editorGoodsSpecilgoodscredentialImageForm').show();
        }

        cleargsgdata();

        $.ajax({
            type: "post",
            url: "/goods/queryGoodsSpecilgoodscredentialLists",
            dataType: "json",
            data: {gid: gid,pageNo:1,pageSize:1000},
            success: function (data) {
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    var html = '';
                    for(var i=0;i<rows.length;i++){
                        if(rows[i].gsgType==1){
                            html = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
                                        '<img src="'+rows[i].imgUrl+'" style="width: 100%;height: 100%;" >'+
                                        '<div onclick="deletegsgImage(this,'+rows[i].gsgId+')" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
                                        '</div>';

                            $('#uploadGoodsSpecilgoodscredentialImgBtn').after(html);
                        }else{
                            var html = '<div class="col-lg-3" style="color: red;">'+
                                '<div class="input-group form-group">' +
                                '<input type="hidden" value="'+rows[i].gsgId+'">'+
                                '<input value="'+rows[i].gsgNumber+'" onfocus="focusnumber(this);" onblur="blurnumber(this);" class="form-control" type="text" placeholder="请输入商品编号" style="float: left;width: 70%">'+
                                '<span  onclick="deletenumber(this);" style="float: left;margin-left:3px;margin-top: 7px; display: block;cursor: pointer">删除</span>'+
                                '</div>'+
                                '</div>';
                            $('#addnumberbtnparent').after(html);
                        }
                    }
                }
            }
        });
    }

    function cleargsgdata() {
        $('#uploadGoodsSpecilgoodscredentialImgBtn').nextAll().remove();
        $('#addnumberbtnparent').nextAll().remove();
    }
</script>

<!--商品与赠品关系-->
<script>
    var initgcomplimentaryreldataArr = new Array();

    function refreshmyggcomplimentaryrelTbody() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        initggcomplimentaryrelFormData(gid);
    }
    /**
     * 清除form 表单数据
     * */
    function initggcomplimentaryrelFormData(gid){
        $('#topgcname').html('赠品名称（已有）');
        initgcomplimentaryreldataArr = [];
        $('#addggcrelbtn').hide();
        $('#deleteggcrelbtn').show();
        $('#mygcomplimentaryrelTbody').html('');
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGgcrelLists",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   =  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="checkbox'+i+'" name="ggcomplimentaryrelcheckbox" class="styled" type="checkbox" value="'+rows[i].ggcId+'">'+
                            '<label for="checkbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%">'+rows[i].gcname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgcomplimentaryreldataArr.push(rows[i].gcId);
                        $('#mygcomplimentaryrelTbody').prepend(html);
                    }
                }

            }
        });

        $.ajax({
            type:"post",
            url:"/goods/queryGclassGoodsGgcrelLists",
            dataType: "json",
            data:{gid:gid},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html   =  '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="xcheckbox'+i+'" name="gclassggcomplimentaryrelcheckbox" class="styled" type="checkbox" value="'+rows[i].gcId+'">'+
                            '<label for="xcheckbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%"> <span style="color:red">(子类关联)</span> '+rows[i].gcname+'</span>'+
                            '</td>'+
                            '</tr>';
                        $('#mygcomplimentaryrelTbody').prepend(html);

                        if(i == rows.length-1){
                             removedeletedgclass();
                        }
                    }
                }
            }
        });
    }

    function searchgcs() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var gcName = $('#search_Goodsgc_name').val();
        $('#topcommentlabelname').html('赠品名称');
        $('#addggcrelbtn').show();
        $('#deleteggcrelbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsComplimentaryLists",
            dataType: "json",
            data: {gcName: gcName,isClass:0,pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgcomplimentaryreldataArr.length>0){
                            for (var j=0; j<initgcomplimentaryreldataArr.length; j++){
                                if (rows[i].gcId==initgcomplimentaryreldataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="ggcomplimentaryrelcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gcId+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gcName+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的赠品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#mygcomplimentaryrelTbody').html(html);
            }
        });
    }

    function addggcomplimentaryrel() {
        var gid = $('#gid').val();
        if(gid==''){
            toastr.warning('请先保存商品!');
            document.getElementById("nava1").click();
            return;
        }
        var checkboxs = $('input:checkbox[name="ggcomplimentaryrelcheckbox"]:checked');
        var addggb = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggb += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGgcrel",
                dataType: "json",
                data: {ggcIds: addggb,gid:gid},
                success: function (data) {
                    toastr.success('新增成功!');
                    refreshmyggcomplimentaryrelTbody();
                    updateGoodsSetNotChecked();
                }
            });

        }else{
            toastr.warning('请选择您想操作的数据!');
        }

    }

    function deleteggcomplimentaryrel() {
        var checkboxs = $('input:checkbox[name="ggcomplimentaryrelcheckbox"]:checked');
        var glcasscheckboxs = $('input:checkbox[name="gclassggcomplimentaryrelcheckbox"]:checked');

        if(checkboxs.length > 0 || glcasscheckboxs.length > 0) {
            if(checkboxs.length > 0){
                var gglIds = "";
                for (var i = 0; i < checkboxs.length; i++) {
                    if (checkboxs[i].checked) {
                        gglIds += checkboxs[i].value + ",";
                    }
                }

                $.ajax({
                    type: "post",
                    url: "/goods/removeGoodsGgcrel",
                    dataType: "json",
                    data: {ggcIds: gglIds},
                    success: function (data) {
                        toastr.success('删除成功!');
                        refreshmyggcomplimentaryrelTbody();
                        updateGoodsSetNotChecked();
                    }
                });
            }

            if (glcasscheckboxs.length > 0) {
                var gclassgglIds = "";
                for (var i = 0; i < glcasscheckboxs.length; i++) {
                    if (glcasscheckboxs[i].checked) {
                        gclassgglIds += glcasscheckboxs[i].value + ",";
                    }
                }

                $.ajax({
                    type: "post",
                    url: "/goods/removeGclassGoodsGgcrel",
                    dataType: "json",
                    data: {gcIds: gclassgglIds, gid: $('#gid').val()},
                    success: function (data) {
                        toastr.success('删除成功!');
                        refreshmyggcomplimentaryrelTbody();
                        updateGoodsSetNotChecked();
                    }
                });
            }
        }else {
            toastr.warning('请选择您想操作的数据!');
        }
    }

    function removedeletedgclass() {
        var gid = $('#gid').val();
        $.ajax({
            type: "post",
            url: "/goods/querydeletedgclass",
            dataType: "json",
            data: {gid:gid},
            success: function (data) {
                var rows = data.rows;
                var gclasscheckboxs = $('input:checkbox[name="gclassggcomplimentaryrelcheckbox"]');
                if(rows!=null && rows.length>0){
                    for(var i=0; i<gclasscheckboxs.length; i++){
                        for(var j=0; j<rows.length; j++){
                            if(gclasscheckboxs[i].value==rows[j].gcId){
                                $(gclasscheckboxs[i]).parent().parent().parent().remove();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
</script>

<!--商品子类选择1-->
<script>

        var setting1 = {
            view: {
                dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick1,
                onClick: onClick1
            }
        };

        function beforeClick1(treeId, treeNode) {
            return true;
        }

        function onClick1(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo1"),
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

        var zNodes1;
        $.fn.zTree.init($("#treeDemo1"), setting1, zNodes1);
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
                var zTree=$.fn.zTree.init($("#treeDemo1"), setting1, nodeData);
//                var node = zTree.getNodeByParam("id",parentId);
//                if(node){
//                    zTree.selectNode(node);
//                }
            }
        });

        function showMenu() {
            var cityObj = $("#gclassificationName");
            var cityOffset = $("#gclassificationName").offset();
            $("#menuContent1").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown1);
        }
        function hideMenu() {
            $("#menuContent1").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown1);
        }
        function onBodyDown1(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent1" || $(event.target).parents("#menuContent1").length>0)) {
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
//            var node = zTree.getNodeByParam("id",parentId);
//            if(node){
//                zTree.selectNode(node);
//            }
        }
    });

    function showMenu2() {
        var cityObj = $("#search_Goods_typeNm");
        var cityOffset = $("#search_Goods_typeNm").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu2() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu2();
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

    <!--更新商品时让商品为未审核状态-->
    function updateGoodsSetNotChecked() {
        var gid = $('#gid').val();
        if(gid==''){
            return;
        }
        $.ajax({
            type: "post",
            url: "/goods/updateGoodsSetNotChecked",
            dataType: "json",
            data: {gid:gid},
            success: function (data) {
                refreshTableData();
            }
        });
    }
</script>
</body>
</html>
