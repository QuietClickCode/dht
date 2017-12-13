<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/css/gclassa.css">
    <link rel="stylesheet" href="<%=path%>/css/gclassc.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/toast/css/toastr.css">


    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.all.min.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/common/form.js"> </script>
    <style>
        li{
            list-style: none;
        }
        #chosegclassdiv a:hover{
            background: #337AB7;
            cursor: pointer;
            text-decoration: none;
            color:#fff;
        }
        .myactive{
            background: #337AB7;
        }
    </style>
</head>

<body>
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
                <div class="col-lg-6" style="height:49px;display: none">
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
</body>



</html>
