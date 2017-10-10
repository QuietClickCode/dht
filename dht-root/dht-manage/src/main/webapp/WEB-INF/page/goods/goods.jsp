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
    </div>

    <ex:perm url="goods/queryGoodsLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
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
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#panel-961258" data-toggle="tab">基本信息</a>
                                    </li>
                                    <li>
                                        <a href="#panel-694947" data-toggle="tab">Section 2</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="panel-961258">
                                        <div class="modal-body">
                                            <form id="editorGoodsForm">
                                                <input type="hidden" name="gid" id="gid">
                                                <input type="hidden" name="version" id="version">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品导入:
                                                          </span>
                                                            <input type="text" class="form-control" name="importGoodsAddress" id="importGoodsAddress" style="width: 60%">
                                                            <button class="btn btn-default" id="importGoodsBtn">导入</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            商品名称:
                                                          </span>
                                                            <input type="text" class="form-control" name="gname" id="gname">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              商品分类:
                                                          </span>
                                                            <input id="gclassification" name="gclassification" type="hidden" />
                                                            <input id="gclassificationName" name="gclassificationName" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            利润率:
                                                          </span>
                                                            <input type="text" class="form-control" name="gprofitability" id="gprofitability" style="width: 60%">%
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6" id="allowsettimediv">
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
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6" id="gsalesvolumediv">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              销量:
                                                          </span>
                                                            <input id="gsalesvolume" name="gsalesvolume" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6" id="gedtdiv">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              预计发货时间:
                                                          </span>
                                                            <input id="gedt" name="gedt" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            所属仓库/地区:
                                                          </span>
                                                            <input id="garea" name="garea" type="hidden" class="form-control" />
                                                            <input id="gareaName" name="gareaName" type="text" class="form-control" />
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              可售范围:
                                                          </span>
                                                            <input id="gsalescope" name="gsalescope" type="text" class="form-control" placeholder="以千米为单位"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6" id="gfreightdiv">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            运费:
                                                          </span>
                                                            <input id="gfreight" name="gfreight" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              主推方向:
                                                          </span>
                                                            <input id="gmaindirection" name="gmaindirection" type="hidden" />
                                                            <div class="radio " style="display: inline-block;">
                                                                <input type="radio" name="mainType" id="maincity" value="0" checked>
                                                                <label for="maincity">
                                                                    城市
                                                                </label>
                                                            </div>
                                                            <div class="radio" style="display: inline-block;margin-left: 30px">
                                                                <input type="radio" name="mainType" id="maincomtrayside" value="1" >
                                                                <label for="maincomtrayside">
                                                                    乡村
                                                                </label>
                                                            </div>
                                                            <div class="radio" style="display: inline-block;margin-left: 30px">
                                                                <input type="radio" name="mainType" id="maincityandcom" value="2" >
                                                                <label for="maincityandcom">
                                                                    乡村和城市
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            计件单位:
                                                          </span>
                                                            <input id="gunitname" name="gunitname" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              限购量:
                                                          </span>
                                                            <input id="gendbuy" name="gendbuy" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            生产地信息:
                                                          </span>
                                                            <input id="gproductioninaddress" name="gproductioninaddress" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                              生产人姓名:
                                                          </span>
                                                            <input id="gproductioninperson" name="gproductioninperson" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            收集人地址:
                                                          </span>
                                                            <input id="gpickaddress" name="gpickaddress" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
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
                                                    <div class="col-lg-6" id="gdepositdiv">
                                                        <div class="input-group form-group">
                                                          <span class="input-group-addon">
                                                            定金:
                                                          </span>
                                                            <input id="gdeposit" name="gdeposit" type="text" class="form-control"/>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
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
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="panel-694947">
                                        <p>
                                            Howdy, I'm in Section 2.
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 公用下拉择树 -->
<div id="orgNodeContent" class="orgNodeContent" style="display:none; position: absolute;z-index:1059">
    <ul id="orgTree" class="ztree" style="margin-top:0; width:320px;"></ul>
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
            field: 'isPutway',
            title: '商品状态',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(row.isPutway==1){
                    html+='<span style="color:green;">已上架</span>';
                }else{
                    html+='<span style="color:red;">未上架</span>';
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
            field: 'gputawaytime',
            title: '上架时间',
            align : 'center',
            valign : 'middle'
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
//        $('#GoodsTables').bootstrapTable({
//            method: 'get',
//            contentType: "application/x-www-form-urlencoded",//必须要有！！！！
//            url:"/goods/queryGoodsLists",
//            columns:treeColumns,
//            queryParams:queryParams,
//            toolbar:'#toolbar',
//            pageNumber: 1, //初始化加载第一页，默认第一页
//            pageSize:10,//单页记录数
//            pageList:[5,10,20,30],//分页步进值
//            locale:'zh-CN',//中文支持,
//        });


        createTable("/goods/queryGoodsLists","GoodsTables","gbId",treeColumns,queryParams);

        //初始华开关选择器
        $("#editorGoodsForm #isServicegoods").bootstrapSwitch();
        $("#editorGoodsForm #isAllowsetdeliverytime").bootstrapSwitch();
        $("#editorGoodsForm #isShowsalesvolume").bootstrapSwitch();
        $("#editorGoodsForm #isMenberdiscount").bootstrapSwitch();
        $("#editorGoodsForm #isPutway").bootstrapSwitch();
        $("#editorGoodsForm #isCod").bootstrapSwitch();
        $("#editorGoodsForm #isAdvancesale").bootstrapSwitch();

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsForm").serializeObject();

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
                    if(data.status==0){
                        //显示提示
                        layer.msg(data.msg);
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editorSysUser').modal('hide')
                    }else{
                        layer.msg(data.msg);
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
        $('#editorGoodsForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    gbName: {
                        message: '商品名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品名称长度在1-30之间'
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
            gbName: $("#search_Goods_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoods(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoods(gbId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoods',
            dataType: "json",
            data:{gbId:gbId},
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


    var zNodes;
    function editorGoods(orgId){
        editorGoodsType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品");
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsForm #uid").val("");
        $("#editorGoodsForm #version").val("");
        $("#editorGoodsForm #uaccount").val("");
        $("#editorGoodsForm #uname").val("");
        $("#editorGoodsForm #orgIds").val("");
        $("#editorGoodsForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsForm #gid").val(rowData.gid);
            $("#editorGoodsForm #version").val(rowData.version);
            $("#editorGoodsForm #gname").val(rowData.gname);
            $("#editorGoodsForm #gclassification").val(rowData.gclassification);
            $("#editorGoodsForm #gclassificationName").val(rowData.gclassificationName);
            $("#editorGoodsForm #gprofitability").val(rowData.gprofitability);
            $("#editorGoodsForm #gsalesvolume").val(rowData.gsalesvolume);
            $("#editorGoodsForm #gedt").val(rowData.gedt);
            $("#editorGoodsForm #garea").val(rowData.garea);
            $("#editorGoodsForm #gareaName").val(rowData.gareaName);
            $("#editorGoodsForm #gsalescope").val(rowData.gsalescope);
            $("#editorGoodsForm #gfreight").val(rowData.gfreight);
            $("#editorGoodsForm #gunitname").val(rowData.gunitname);
            $("#editorGoodsForm #gstartbuy").val(rowData.gstartbuy);
            $("#editorGoodsForm #gendbuy").val(rowData.gendbuy);
            $("#editorGoodsForm #gproductioninaddress").val(rowData.gproductioninaddress);
            $("#editorGoodsForm #gproductioninperson").val(rowData.gproductioninperson);
            $("#editorGoodsForm #gpickaddress").val(rowData.gpickaddress);
            $("#editorGoodsForm #gpickperson").val(rowData.gpickperson);
            $("#ueditor_1").find("body").html(rowData.gdescription);

            var flag;

            flag = false;
            $("#editorGoodsForm #isServicegoods").val(rowData.isServicegoods);
            if(rowData.isServicegoods==1){
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
            $("#editorGoodsForm #isServicegoods").bootstrapSwitch("state",flag);

            flag = false;
            $("#editorGoodsForm #isAllowsetdeliverytime").val(rowData.isAllowsetdeliverytime);
            if(rowData.isAllowsetdeliverytime==1){
                flag = true;
            }
            $("#editorGoodsForm #isAllowsetdeliverytime").bootstrapSwitch("state",flag);

            flag = false;
            $("#editorGoodsForm #isShowsalesvolume").val(rowData.isShowsalesvolume);
            if(rowData.isShowsalesvolume==1){
                flag = true;
                $('#gsalesvolumediv').show();
            }else{
                $('#gsalesvolumediv').hide();
            }
            $("#editorGoodsForm #isShowsalesvolume").bootstrapSwitch("state",flag);

            flag = false;
            $("#editorGoodsForm #isMenberdiscount").val(rowData.isMenberdiscount);
            if(rowData.isMenberdiscount==1){
                flag = true;
            }
            $("#editorGoodsForm #isMenberdiscount").bootstrapSwitch("state",flag);

            flag = false;
            $("#editorGoodsForm #isPutway").val(rowData.isPutway);
            if(rowData.isPutway==1){
                flag = true;
            }
            $("#editorGoodsForm #isPutway").bootstrapSwitch("state",flag);

            flag = false;
            $("#editorGoodsForm #isAdvancesale").val(rowData.isAdvancesale);
            if(rowData.isAdvancesale==1){
                flag = true;
                $('#gedtdiv').show();
            }else{
                $('#gedtdiv').hide();
            }
            $("#editorGoodsForm #isAdvancesale").bootstrapSwitch("state",flag);

            $("#editorGoodsForm #gmaindirection").val(rowData.gmaindirection);
            if(rowData.gmaindirection==0){
                $('#maincity').attr('checked','checked');
            }else if(rowData.gmaindirection==1){
                $('#maincomtrayside').attr('checked','checked');
            }else{
                $('#maincityandcom').attr('checked','checked');
            }

            flag = false;
            $("#editorGoodsForm #isCod").val(rowData.isCod);
            if(rowData.isCod==1){
                flag = true;
            }
            $("#editorGoodsForm #isCod").bootstrapSwitch("state",flag);


            $("#editorGoodsForm #isMultiplebuy").val(rowData.isMultiplebuy);
            if(rowData.isMultiplebuy==1){
                $('#isMultiplebuy').attr('checked','checked');
            }else{
                $('#isMultiplebuy').removeAttr('checked');
            }



        }else{
            $("#editorGoodsForm #gbName").val('');
            $("#editorGoodsForm #gbId").val('');
            $("#editorGoodsForm #gbImgpath").val('');

        }
    }
    /**
     * 添加商品大类
     **/
    function addGoods(){
        editorGoodsType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();

        $("#editorSysUserTitle").text("添加商品");
    }
    /**
     * 重新加载树型结构
     **/
    function reloadOrgTree(uid){
        $.fn.zTree.init($("#orgTree"), setting, zNodes);
        var rowData=rowDatas.get(parseInt(uid,10));
        let selectOrgIds="";
        if(rowData){
            selectOrgIds=rowData.orgIds
        }
        $.ajax({
            type:"post",
            url:'/org/reqOrgTree',
            dataType: "json",
            data:{selectOrgIds:selectOrgIds},
            async:false,
            success:function(data){
                let nodeData=data.data;
                var zTree=$.fn.zTree.init($("#orgTree"), setting, nodeData);
            }
        });
    }

    /***********************************************************************************/
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y" : "s", "N" : "s" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree"),
            nodes = zTree.getCheckedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name+",";
            vId += nodes[i].id+",";
        }
        var orgPname = $("#orgNms");
        var orgPid_ = $("#orgIds");
        orgPname.val(v);
        orgPid_.val(vId);
        //hideOrgTree();
    }

    function showOrgTree() {
        var cityObj = $("#orgNms");
        var cityOffset = $("#orgNms").offset();
        $("#orgNodeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideOrgTree() {
        $("#orgNodeContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "orgNodeContent" || $(event.target).parents("#orgNodeContent").length>0)) {
            hideOrgTree();
        }
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

<!--自定义-->
<script>

    $(function () {
        $('#isServicegoods').siblings().click(function () {
            var flag = $("#editorGoodsForm #isServicegoods").bootstrapSwitch("state");
            if(flag){
                $('#allowsettimediv').hide();
                $('#isadvancesalediv').hide();
                $('#gfreightdiv').hide();
                $('#gdepositdiv').show();
                $('#maincomtrayside').attr('checked','checked');
            }else{
                $('#allowsettimediv').show();
                $('#isadvancesalediv').show();
                $('#gfreightdiv').show();
                $('#gdepositdiv').hide();
            }
        });

        $('#isShowsalesvolume').siblings().click(function () {
            var flag = $("#editorGoodsForm #isShowsalesvolume").bootstrapSwitch("state");
            if(flag){
                $('#gsalesvolumediv').show();
            }else{
                $('#gsalesvolumediv').hide();
            }
        });

        $('#isAdvancesale').siblings().click(function () {
            var flag = $("#editorGoodsForm #isAdvancesale").bootstrapSwitch("state");
            if(flag){
                $('#gedtdiv').show();
            }else{
                $('#gedtdiv').hide();
            }
        });

    });

</script>
</body>
</html>
