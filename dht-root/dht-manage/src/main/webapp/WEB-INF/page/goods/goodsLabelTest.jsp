<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品标签管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">

    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/font-awesome-4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <style>
        li{
            list-style:none;
        }
    </style>
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsLabel">
        <button class="btn btn-primary" type="button" onclick="addGoodsLabel()">添加商品标签</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_GoodsLabel_name" placeholder="请输入商品标签名称">
    </div>

    <ex:perm url="goods/queryGoodsLabelLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()" style="margin-top: 5px">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsLabelTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsLabelForm">
                    <input type="hidden" name="glId" id="glId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品标签名称:
                              </span>
                                <input type="text" class="form-control" name="glName" id="glName">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                 商品标签有效时间:
                              </span>

                                <input id="reportrange" name="reportrange" type="text" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                标签类型:
                              </span>
                                <div class="radio " style="display: inline-block;">
                                    <input type="hidden" id="isGoodslabel" name="isGoodslabel">
                                    <input type="radio" name="labelType" id="isGoodsShow" value="1" checked>
                                    <label for="isGoodsShow">
                                        选择商品
                                    </label>
                                </div>
                                <div class="radio" style="display: inline-block;margin-left: 30px">
                                    <input type="radio" name="labelType" id="isClassShow" value="0" >
                                    <label for="isClassShow">
                                        选择类型
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6" id="chosebtn">
                            <div class="input-group form-group">
                              <span id="goodsBtn" class="input-group-addon" style="cursor: pointer">
                                 选择商品
                              </span>
                                <span id="classBtn" class="input-group-addon" style="cursor: pointer;display: none">
                                    选择类型
                                </span>
                            </div>
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

<div class="modal fade" id="editorGoodsLabel" tabindex="-1" role="dialog" aria-labelledby="editorGoodsLabel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorGoodsLabelTitle"></h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
                    </div>
                    <button class="btn btn-default" type="button" onclick="searchgoods()">查询</button>
                </center>

                <button class="btn btn-default" type="button" onclick="addgglrel()" id="addgoodsbtn">新增</button>
                <button class="btn btn-default" type="button" onclick="deletegglrel()" id="delgoodsbtn">删除</button>
                <button class="btn btn-primary" type="button" onclick="refreshgoodsTbody()" style="float: right;margin-bottom: 10px;">刷新</button>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgoodsname">
                                    商品名称（已有）
                                </th>
                            </tr>
                            </thead>
                            <tbody id="goodsTbody">
                            <tr>
                                <td>
                                    <div class="checkbox checkbox-info">
                                        <input id="checkbox1" class="styled" type="checkbox">
                                        <label for="checkbox1">

                                        </label>
                                    </div>
                                </td>
                                <td style="text-align: center;display:table-cell; vertical-align:bottom;">
                                    TB - Monthly
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editorGoodsLabelRelSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editorgcglrel" tabindex="-1" role="dialog" aria-labelledby="editorgcglrel">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorgcglTitle">编辑商品与标签关系</h4>
            </div>
            <div class="modal-body">
                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input id="gclassNum" onclick="showMenu();" type="text" class="form-control"  placeholder="点击选择商品子类">
                    </div>
                </center>

                <ex:perm url="/goods/removeGoodsGgcrel">
                    <button class="btn btn-default" type="button" onclick="deletegcgllrel()" id="deletegtgclrelbtn">删除</button>
                </ex:perm>
                <ex:perm url="/goods/queryGoodsGgcrelLists">
                    <button class="btn btn-primary" type="button" onclick="initgcglrel()" style="float: right;margin-bottom: 10px">刷新</button>
                </ex:perm>

                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgcglname">
                                    子类名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="gcglTbody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editgcglSubmit">确认</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="addGoodsLabelAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增标签广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addFloorAdv">
                    <div class="form-group">
                        <label for="img" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="img" name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="AdvName" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="AdvName" id="AdvName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="country" class="country" value="0">
                                    乡村
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="country" class="country" value="1">
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
                                    <input type="radio" name="client" checked class="client" value="0">
                                    移动端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="client" class="client" value="1" disabled="disabled">
                                    PC端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="client" class="client" value="2" disabled="disabled">
                                    小程序
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="url" id="url" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="order" id="order" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="saveFloorAdv()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updateAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑该标签广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateFloorAdvertising">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <img id="showImg" style="width: 50px;height: 50px;display: inline-block;">
                            <input id="uploadImage" style="display: inline-block" name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="setName" id="AdvertisingName" class="form-control">
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
<div id="menuContent1" class="menuContent1" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker-iconset-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/jquery.treegrid.extension.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>
<!--商品标签编辑-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsLabelType=0;

    $(function () {

            //表格的初始化
            $('#GoodsLabelTables').bootstrapTable({
                url:"/goods/queryGoodsLabelAdvertisingList",
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                //pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: function () {
                    return {
                        glName:$("#search_GoodsLabel_name").val()
                    };
                },                                  //传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
//            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                treeView: true,
                treeId:"glId",
                treeField:"glName",
                treePid:"parentId",                         //上级菜单关联id
                treeRootLevel: 1,
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                uniqueId: "glId",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                selectItemName: 'parentItem',
                dataType:"json",
                contentType : "application/x-www-form-urlencoded",
                columns: [
                    {   checkbox: true,
                        align : 'center',
                        valign : 'middle'
                    },
                    {
                        field: 'glName',
                        title: '商品标签名称',
                        align : 'left',
                        valign : 'middle'
                    },
                    {
                        field: 'glStarttime',
                        title: '开始时间',
                        align : 'center',
                        valign : 'middle',
                    },
                    {
                        field: 'glEndtime',
                        title: '结束时间',
                        align : 'center',
                        valign : 'middle',
                    },
                    {
                        field: 'CreateTime',
                        title: '状态',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            rowDatas.set(row.glId,row);
                            let html='';
                            var now = new Date();
                            if(now<new Date(row.glStarttime)){
                                html='未生效';
                            }
                            if(now>new Date(row.glStarttime) && now<new Date(row.glEndtime)){
                                html='生效中';
                            }
                            if(now>new Date(row.glEndtime)){
                                html='已失效';
                            }
                            if(row.glEndtime==''||row.glEndtime==null){
                                html = '-';
                            }
                            return html;
                        }
                    },
                    {
                        field: 'imgUrl',
                        title: '图片',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            var html = '';
                            if(value!=''&&value!=null){
                                html='<img src="'+value+'" style="width: 50px;height: 50px">'
                            }else{
                                html='-';
                            }
                            return html;
                        }
                    },
                    {
                        field: 'isShow',
                        title: '广告状态',
                        align : 'center',
                        valign : 'middle',
                        formatter:function(value,row,index){
                            var html = '';
                            if(value==1){
                                html='显示';
                            }else if(value==0){
                                html='隐藏';
                            }
                            return html;
                        }
                    },
                    {
                        align : 'center',
                        valign : 'middle',
                        title: '添加子项',
                        formatter:function (value,row,index) {
                            rowDatas.set(row.faId,row);
                            let html='';
                            let parentId = row.parentId;
                            if(parentId == null)
                                html+='<button class="btn btn-default addSubitem" type="button"  onclick="event.stopPropagation();addGoodsLabelAdvertising(\''+(-row.glId)+'\')">添加子项</button>'
                            else
                                html + "";
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
                            rowDatas.set(row.glId,row);
                            let html='';
                            if(row.parentId==null){
                                <ex:perm url="goods/editGoodsLabel">
                                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsLabel(\''+row.glId+'\')"">编辑</button>&nbsp;';
                                </ex:perm>
                                <ex:perm url="goods/removeGoodsLabel">
                                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+(-row.glId)/100000+'\',this)">删除</button>';
                                </ex:perm>
                            }else{
                                html+='<button class="btn btn-default" type="button" onclick="event.stopPropagation();updateFloorAdvertising(\''+row.glId+'\',\''+row.imageId+'\',\''+row.imgUrl+'\',\''+row.glName+'\',\''+row.isShow+'\',\''+row.url+'\',\''+row.glOrder+'\')">编辑</button>';
                                html+='<button class="btn btn-default" type="button" onclick="event.stopPropagation();deleteFloorAdv(\''+row.glId+'\')">删除</button>';
                            }

                            return html;
                        }
                    }
                ]
            });


        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorGoodsLabelForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsLabelForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsLabelForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var arr = $("#reportrange").val().split(" - ");
            $('#glStarttime').val(arr[0]);
            $('#glEndtime').val(arr[1]);

            var formData=$("#editorGoodsLabelForm").serializeObject();

            formData["glStarttimes"] = arr[0];
            formData["glEndtimes"] = arr[1];
            let url="/goods/addGoodsLabel";
            if(editorGoodsLabelType==1){
                url="/goods/editGoodsLabel";
                formData["glId"] = -$("#editorGoodsLabelForm #glId").val()/100000;
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
        $('#editorGoodsLabelForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    glName: {
                        message: '商品标签名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品标签名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品标签名称长度在1-30之间'
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
            glName: $("#search_GoodsLabel_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsLabelTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLabelAdvertisingList"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsLabel(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsLabel(glId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsLabel',
            dataType: "json",
            data:{glId:glId},
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

    function editorGoodsLabel(orgId){
        editorGoodsLabelType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品标签");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsLabelForm #uid").val("");
        $("#editorGoodsLabelForm #version").val("");
        $("#editorGoodsLabelForm #uaccount").val("");
        $("#editorGoodsLabelForm #uname").val("");
        $("#editorGoodsLabelForm #orgIds").val("");
        $("#editorGoodsLabelForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsLabelForm #glId").val(rowData.glId);
            $("#editorGoodsLabelForm #glName").val(rowData.glName);
            $("#editorGoodsLabelForm #isGoodslabel").val(rowData.isGoodslabel);
            $("#editorGoodsLabelForm #glStarttime").val(rowData.glStarttime);
            $("#editorGoodsLabelForm #glEndtime").val(rowData.glEndtime);
            $("#editorGoodsLabelForm #version").val(rowData.version);
            $("#reportrange").val(rowData.glStarttime + ' - ' + rowData.glEndtime);

            if(rowData.isGoodslabel==1){
                document.getElementById("isGoodsShow").checked = true;
                $("#goodsBtn").show();
                $("#classBtn").hide();
            }else{
                document.getElementById("isClassShow").checked = true;
                $("#goodsBtn").hide();
                $("#classBtn").show();
            }
            $('#chosebtn').show();

        }else{
            $("#editorGoodsLabelForm #glName").val('');
            $("#editorGoodsLabelForm #glId").val('');
            $("#editorGoodsLabelForm #isGoods").val('');
            $("#editorGoodsLabelForm #glStarttime").val('');
            $("#editorGoodsLabelForm #glEndtime").val('');
            $("#searchDateRange").html('');
            document.getElementById("isGoodsShow").checked = true;
            $('#chosebtn').hide();
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsLabel(){
        editorGoodsLabelType=0;
        initFormData();

        $("#editorSysUserTitle").text("添加商品标签");
        $('#editorSysUser').modal("show")
    }


</script>

<!--商品与标签关系编辑-->
<script>
    $(function () {
        $('#editorGoodsLabelRelSubmit').click(function () {
            $('#editorGoodsLabel').modal("hide");
        });
    });
    
    var initgdataArr = new Array();

    function refreshgoodsTbody() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        initgoodsFormData(glId);
    }
    /**
     * 清除form 表单数据
     * */
    function initgoodsFormData(glId){
        $('#topgoodsname').html('商品名称（已有）');
        initgdataArr = [];
        $('#addgoodsbtn').hide();
        $('#delgoodsbtn').show();
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGglrelLists",
            dataType: "json",
            data:{glId:glId,pageNo:1,pageSize:1000},
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
                            '<span style="line-height: 100%">'+rows[i].gname+'</span>'+
                            '</td>'+
                            '</tr>';
                        initgdataArr.push(rows[i].gid);
                    }
                }else{
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">暂时没有绑定商品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#goodsTbody').html(html);
            }
        });
    }

    function searchgoods() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        var gname = $('#search_Goods_name').val();
        $('#topgoodsname').html('商品名称');
        $('#addgoodsbtn').show();
        $('#delgoodsbtn').hide();
        $.ajax({
            type: "post",
            url: "/goods/queryGoodsLists",
            dataType: "json",
            data: {gname: gname,pageNo: 1, pageSize: 100},
            success: function (data) {
                var rows = data.rows;
                var html = '';
                if(rows!=null){
                    for(var i=0; i<rows.length; i++){
                        var flag = 0;
                        if (initgdataArr.length>0){
                            for (var j=0; j<initgdataArr.length; j++){
                                if (rows[i].gid==initgdataArr[j]){
                                    flag = 1;
                                }
                            }
                        }

                        if(flag == 0){
                            html += '<tr>'+
                                '<td>'+
                                '<div class="checkbox checkbox-info">'+
                                '<input name="gglcheckbox" id="checkbox'+i+'" class="styled" type="checkbox" value="'+rows[i].gid+'">'+
                                '<label for="checkbox'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                '<span style="line-height: 100%">'+rows[i].gname+'</span>'+
                                '</td>'+
                                '</tr>';
                        }

                    }
                }

                if(html == ''){
                    html += '<tr>'+
                        '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                        '<span style="line-height: 100%;color:red;">没有找到符合要求的商品</span>'+
                        '</td>'+
                        '</tr>';

                }
                $('#goodsTbody').html(html);
            }
        });
    }

    function addgglrel() {
        var glId = $('#glId').val();
        if(glId==''){
            layer.msg('请先保存标签!');
            return;
        }
        var checkboxs = $('input:checkbox[name="gglcheckbox"]:checked');
        var addggl = "";
        if(checkboxs.length > 0){
            for(var i=0; i<checkboxs.length; i++){
                if(checkboxs[i].checked){
                    addggl += ","+checkboxs[i].value;
                }
            }
            $.ajax({
                type: "post",
                url: "/goods/addGoodsGglrel",
                dataType: "json",
                data: {gids: addggl,glId:glId},
                success: function (data) {
                    layer.msg('新增成功!');
                    refreshgoodsTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
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
                    layer.msg('删除成功!');
                    refreshgoodsTbody();
                }
            });

        }else{
            layer.msg('请选择您想操作的数据!');
        }

    }
</script>

<!--标签与子类关系1-->
<script>

    $(function () {
        $('#editgcglSubmit').click(function () {
            addgcglrel();
        });
    });

    function addgcglrel() {
        var glId = $('#glId').val();
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
        if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
            var gclassIds = '';
            for(var i=0; i<gcglcheckboxs.length; i++){
                gclassIds += ','+gcglcheckboxs[i].value;
            }
            gclassIds = gclassIds.substr(1);
            $.ajax({
                type:"post",
                url:"/goods/addGoodsGglrel",
                dataType: "json",
                data:{glId:glId,gclassIds:gclassIds},
                success:function(data){
                    if(data.status==0){
                        layer.msg("操作成功");
                        $('#editorgcglrel').modal('hide');
                    }else{
                        layer.msg("系统错误");
                    }

                }
            });
        }else{
            $('#editorgcglrel').modal('hide');
        }
    }

    function deletegcgllrel() {
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]:checked');
        if(gcglcheckboxs!=null&&gcglcheckboxs.length>0){
            for(var i=0;i<gcglcheckboxs.length;i++){
                $(gcglcheckboxs[i]).parent().parent().parent().remove();
            }
        }else{
            layer.msg("请选择想操作的数据");
        }
    }

    function initgcglrel() {
        var glId = $('#glId').val();
        $('#gclassNum').val('');
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGglrelLists",
            dataType: "json",
            data:{glId:glId,isGoodslabel:0,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0;i<rows.length;i++){
                        html += '<tr>'+
                            '<td>'+
                            '<div class="checkbox checkbox-info">'+
                            '<input id="gcglcheckbox'+i+'"  type="checkbox" name="gcglcheckbox" onclick="" value="'+rows[i].gclassId+'">'+
                            '<label for="gcglcheckbox'+i+'">'+
                            '</label>'+
                            '</div>'+
                            '</td>'+
                            '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            rows[i].gclassname+
                            '</td>'+
                            '</tr>';
                    }
                }
                $('#gcglTbody').html(html);

                var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
                if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
                    var ggIds = '';
                    for(var i=0; i<gcglcheckboxs.length; i++){
                        ggIds += ','+gcglcheckboxs[i].value;
                    }
                    ggIds = ggIds.substr(1);
                    initgcgltree(ggIds);
                }else{
                    initgcgltree(-1);
                }

            }
        });
    }

    <!--初始化树形结构-->
    function initgcgltree(ggIds) {
        $.ajax({
            type:"post",
            url:'/goods/queryGoodsClassificationNode',
            dataType: "json",
            data:{ggId:ggIds,pageSize:1000,pageNo:1},
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
                var zTree=$.fn.zTree.init($("#treeDemo"), setting1, nodeData);
            }
        });
    }




</script>

<!--树形结构展示-->
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
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getSelectedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name;
            vId += nodes[i].id;
        }
        var gclassNum = $("#gclassNum");
        gclassNum.val(v);

        var flag = 0;
        var gcglcheckboxs = $('#gcglTbody').find('input[type=checkbox]');
        if(gcglcheckboxs!=null && gcglcheckboxs.length>0){
            for(var i=0; i<gcglcheckboxs.length; i++){
                if(vId==gcglcheckboxs[i].value){
                    $(gcglcheckboxs[i]).parent().parent().parent().remove();
                    console.log(gcglcheckboxs[i]);
                    flag++;
                }
            }
        }

        if(flag == 0){
            var html = '<tr>'+
                '<td>'+
                '<div class="checkbox checkbox-info">'+
                '<input id="gcglcheckbox'+vId+'"  type="checkbox" name="gcglcheckbox" onclick="" value="'+vId+'">'+
                '<label for="gcglcheckbox'+vId+'">'+
                '</label>'+
                '</div>'+
                '</td>'+
                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                v+
                '</td>'+
                '</tr>';
            $('#gcglTbody').prepend(html);
        }
    }

    var zNodes1;
    $.fn.zTree.init($("#treeDemo1"), setting1, zNodes1);

    function showMenu() {
        var cityObj = $("#gclassNum");
        var cityOffset = $("#gclassNum").offset();
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

<script>
    $("input[name='labelType']").click(function () {
        var val = $(this).val();
        $('#isGoodslabel').val(val);
        if(val==1){
            $("#goodsBtn").show();
            $("#classBtn").hide();
        }else{
            $("#goodsBtn").hide();
            $("#classBtn").show();
        }
    });

    $('#goodsBtn').click(function () {
        $("#editorGoodsLabelTitle").text("商品标签");
        refreshgoodsTbody();
        $('#editorGoodsLabel').modal("show");
    });
    $('#classBtn').click(function () {
        initgcglrel();
        $('#editorgcglrel').modal("show");
    });



</script>

<script>
    var parentId;
    function addGoodsLabelAdvertising(id) {
        parentId = id / 100000;
        $("#addGoodsLabelAdvertising").modal("show");
    }
    function saveFloorAdv() {
        let bootstrapValidator = $("#addFloorAdv").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        let img = $("#img").val();
        let name = $("#AdvName").val();
        let country = $(".country:checked").val();
        let client = $(".client:checked").val();
        let url = $("#url").val();
        let order = $("#order").val();
        if(img != ""){
            var fd = new FormData($("#addFloorAdv")[0]);
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
                        url:"/floorAdvertising/addFloorAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            parentId:parentId,
                            imageId:imagepath,
                            url:url,
                            faName:name,
                            faOrder:order,
                            faCountry:country,
                            faClient:client,
                            version:0,
                            isShow:1,
                            isDelete:0
                        },
                        success:function (data) {
                            $("#saveFloorAdvertising").modal("hide");
                            refreshTableData();
                            layer.msg(data.msg);
                            $("#addFloorAdv").data('bootstrapValidator').resetForm(true);
                        }
                    });
                }
            });
        }
    }
    var faid;
    var imageId;
    /*打开编辑楼层广告模态框*/
    function updateFloorAdvertising(id,imgId,imageUrl,name,isshow,url,order) {
        $("#AdvertisingName").val(name);
        radioChoose(".isShow",isshow);
        $("#setAdvertUrl").val(url);
        $("#setAdvertOrder").val(order);
        $("#showImg").attr("src",imageUrl);
        faid = id;
        imageId = imgId;
        $("#updateAdvertising").modal("show");
    }

    function deleteFloorAdv(id) {
        $.ajax({
            url:"/floorAdvertising/deleteFloorAdvertising",
            method:"post",
            dataType:"json",
            data:{
                faId:id
            },
            success:function (data) {
                refreshTableData();
                layer.msg(data.msg)
            }
        });
    }
    function saveFloorAdv() {
        let bootstrapValidator = $("#addFloorAdv").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        let img = $("#img").val();
        let name = $("#AdvName").val();
        let country = $(".country:checked").val();
        let client = $(".client:checked").val();
        let url = $("#url").val();
        let order = $("#order").val();
        if(img != ""){
            var fd = new FormData($("#addFloorAdv")[0]);
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
                        url:"/floorAdvertising/addFloorAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            parentId:-parentId,
                            imageId:imagepath,
                            url:url,
                            faName:name,
                            faOrder:order,
                            faCountry:country,
                            faClient:client,
                            version:0,
                            isShow:1,
                            isDelete:0
                        },
                        success:function (data) {
                            $("#saveFloorAdvertising").modal("hide");
                            refreshTableData();
                            layer.msg(data.msg);
                            $("#addFloorAdv").data('bootstrapValidator').resetForm(true);
                        }
                    });
                }
            });
        }
    }

    /*编辑楼层广告*/
    function updateFloor() {
        let bootstrapValidator = $("#updateFloorAdvertising").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        let imgPath = $("#uploadImage").val();
        let name = $("#AdvertisingName").val();
        let show = $(".isShow:checked").val();
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
                            imageId:imagepath,
                            url:url,
                            faName:name,
                            faOrder:order,
                            isShow:show
                        },
                        success:function (data) {
                            $("#updateAdvertising").modal("hide");
                            refreshTableData();
                            layer.msg(data.msg);
                        }
                    });
                }
            });
        }else{
            $.ajax({
                url:"/floorAdvertising/updateFloorAdvertising",
                method:"post",
                dataType:"json",
                data:{
                    faId:faid,
                    imageId:imageId,
                    url:url,
                    faName:name,
                    faOrder:order,
                    isShow:show
                },
                success:function (data) {
                    $("#updateAdvertising").modal("hide");
                    refreshTableData();
                    layer.msg(data.msg);
                }
            });
        }

    }

    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }
</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#addFloorAdv').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                AdvName: {
                    validators: {
                        notEmpty: {
                            message: '分类名称不能为空'
                        }
                    }
                },
                order: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                },
                country: {
                    validators: {
                        notEmpty: {
                            message: '请选择一个推送对象'
                        }
                    }
                },
                client: {
                    validators: {
                        notEmpty: {
                            message: '必须选择一个客户端对象'
                        }
                    }
                },
                url: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                },
                dht_image_upload: {
                    validators: {
                        notEmpty: {
                            message: '文件不能为空'
                        }
                    }
                }
            }
        });

        $('#updateFloorAdvertising').bootstrapValidator({
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
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                },
                setUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                }
            }
        });
    });
</script>

<!--timer时间选择器-->
<script type="text/javascript"  src="/js/daterangepicker/moment.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        //时间插件

        $('#reportrange').daterangepicker(
            {
                startDate: moment(),
                minDate : moment(),
                showDropdowns : true,
                timePicker:true,
                timePickerIncrement:5,
                timePicker24Hour:true,//24 小时制
                opens : 'right', //日期选择框的弹出位置
                buttonClasses : [ 'btn btn-default' ],
                applyClass : 'btn-small btn-primary blue',
                cancelClass : 'btn-small',
                format: 'YYYY-MM-DD HH:mm:ss',
                locale : {
                    format: 'YYYY-MM-DD HH:mm:ss',
                    applyLabel : '确定',
                    cancelLabel : '取消',
                    fromLabel : '起始时间',
                    toLabel : '结束时间',
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    firstDay : 1
                }
            }, function(start, end, label) {
                $("#editorGoodsCouponForm #gcpStartTime").val(start.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorGoodsCouponForm #gcpEndTime").val(end.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorGoodsCouponForm #gcpValidTime").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });
        //设置日期菜单被选项  --结束--
    })
</script>
</body>
</html>
