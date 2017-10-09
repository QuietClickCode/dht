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
        <button class="btn btn-primary" type="button" onclick="addGoodsClassification()">添加商品子类</button>
    </ex:perm>
</div>
<div>
    <table id="goodsClassificationTable" ></table>
</div>
<div class="modal fade" id="editGoodsClassification" tabindex="-1" role="dialog" aria-labelledby="editGoodsClassification">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">编辑商品子类</h4>
            </div>
            <div class="modal-body">
                <form id="editGoodsClassificationForm">
                    <input type="hidden" name="ggId" id="ggId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                商品子类名称:
                              </span>
                                <input type="text" class="form-control" name="ggName" id="ggName">
                            </div>
                        </div>
                        <br>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                是否为顶级元素:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0" id="isTopSwitch">
                                        <input id="isTop" name="isTop" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6"  id="parentNmElement">
                            <br>
                            <div class="input-group">
                              <span class="input-group-addon">
                                上级分类:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input type="hidden" id="parentId" name="parentId"/>
                                        <input type="text" class="form-control" aria-label="..." id="parentNm" name="parentNm" onclick="showMenu(); return false;"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                所属商品大类:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input type="hidden" id="ggHome" name="ggHome"/>
                                        <input type="text" class="form-control" aria-label="..." id="ggHomeNm" name="ggHomeNm" onclick="showGoodsClassification(); return false;"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                图片:
                              </span>
                                        <input id="ggImgpath" name="ggImgpath" type="hidden" class="form-control"/>
                                        <img style="width: 50px;"src="" id="goodsClassificationImg" />
                                        <button onclick="upImage()" class="btn btn-default" style="line-height: 100%">添加图片</button>
                            </div>
                        </div>
                        <br>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                排序:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="ggOrder" name="ggOrder" type="text" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
            </div>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
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
            url:"/goods/queryGoodsClassificationLists",
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
            treeId:"ggId",
            treeField:"ggName",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ggId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            }, {
                field: 'ggName',
                title: '商品子类名称'

            },{
                field: 'homeName',
                title: '所属大类'

            },{
                field: 'ggOrder',
                title: '显示顺序'

            },{
                field: 'CreateTime',
                title: '操作',
                align : 'center',
                valign : 'middle',
                width:240,
                formatter:function(value,row,index){
                    rowDatas.set(row.ggId,row);
                    let html='';
                    <ex:perm url="goods/editGoodsClassification">
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editGoodsClassification(\''+row.ggId+'\',\''+row.parentId+'\')"">编辑</button>&nbsp;&nbsp;';
                    </ex:perm>
                    <ex:perm url="goods/removeGoodsClassification">
                    html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.ggId+'\',this)">删除</button>';
                    </ex:perm>
                    return html;
                }
            }],
        });

        //初始化选择器开关
        $("#editGoodsClassificationForm #isTop").bootstrapSwitch();


        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            var myResult=myValidate();
            if(!myResult){
                return ;
            }
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
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsClassificationTable').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsClassificationLists"
            }
        );
    }
    //删除确认框
    function deleteData(id,e){
        bootbox.confirm({
            title:"删除确认",
            message: "你确定删除选中的商品子类吗，删除后不可再恢复?",
            buttons: {
                confirm: {
                    label: '确定',
                    className: 'btn-success'
                },
                cancel: {
                    label: '取消',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result){
                    $.ajax({
                        type:"post",
                        url:'/goods/removeGoodsClassification',
                        dataType: "json",
                        data:{ggId:id},
                        success:function(data){
                            if(data.status==0){
                                bootbox.alert("删除成功");
                                refreshTableData();
                            }else{
                                bootbox.alert(data.msg);
                            }
                        }
                    });
                }
            }
        });
    }

    function initGoodsClassification(GoodsClassificationId,GoodsClassificationName){
        $("#ggHomeNm").val(GoodsClassificationName);
        $("#ggHome").val(GoodsClassificationId);
        hideGoodsClassification();
    }
    


    var editorGoodsClassificationType;
    var zNodes;
    var GoodsClassifications=new Map();
    function editGoodsClassification(id,parentId){
        editorGoodsClassificationType=1;
//        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        var rowData=rowDatas.get(parseInt(id,10));
//            $.ajax({
//                type:"post",
//                url:'/goods/queryGoodsClassificationNode',
//                dataType: "json",
//                data:{ggId:id},
//                async:false,
//                success:function(data){
//                    let d=data.data;
//                    var nodeData=new Array();
//                    for(row of d){
//                        let treeRow=new Object();
//                        treeRow.id=row.ggId;
//                        treeRow.pId=row.parentId;
//                        treeRow.name=row.ggName;
//                        nodeData.push(treeRow);
//                    }
//                    var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
//                    var node = zTree.getNodeByParam("id",parentId);
//                    if(node){
//                        zTree.selectNode(node);
//                    }
//                }
//            });
        initFormData(id);

    }

    function addGoodsClassification(){
        editorGoodsClassificationType=0;
        initFormData(-1);
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editGoodsClassificationForm #ggId").val("");
        $("#editGoodsClassificationForm #ggName").val("");
        $("#editGoodsClassificationForm #parentNm").val("");
        $("#editGoodsClassificationForm #ggHomeNm").val("");
//        $("#editGoodsClassificationForm #icon").val("");
        $("#editGoodsClassificationForm #ggimgpath").val("");
        $("#editGoodsClassificationForm #ggorder").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        $.ajax({
            type:"post",
            url:'/goods/queryGoodsClassificationNode',
            dataType: "json",
            data:{ggId:key},
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

        $.ajax({
            type:"post",
            url:'/goods/queryGoodsTypeLists',
            dataType: "json",
            data:{pageSize:1000,pageNo:0,isShow:1},
            async:false,
            success:function(data){
                var html='';
                var d = data.rows;
                for(var i=0; i<d.length; i++){
                    var id=d[i].gtId;
                    var name=d[i].gtName.toString();
                    var str=id+',"'+name+'"';
                    html += '<li style="margin-top: 5px"   >'+
                        '<a  onclick=initGoodsClassification('+str+')>'+
                        '<span >'+d[i].gtName+'</span>'+
                        '</a>'+
                        '</li>';
//                        var val=d[i].gtId;
//                        var key=d[i].gtName;
                    GoodsClassifications.set(name,id);
                }
                $('#GoodsClassificationDemo').html(html);
            }
        });


        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editGoodsClassificationForm #ggId").val(rowData.ggId);
            $("#editGoodsClassificationForm #version").val(rowData.version);
            $("#editGoodsClassificationForm #ggName").val(rowData.ggName);
            $("#editGoodsClassificationForm #ggOrder").val(rowData.ggOrder);
            $("#editGoodsClassificationForm #ggImgpath").val(rowData.ggImgpath);
            $("#editGoodsClassificationForm #goodsClassificationImg")[0].src=rowData.imgUrl;
            $("#editGoodsClassificationForm #ggHomeNm").val(rowData.homeName);
            $("#editGoodsClassificationForm #ggHome").val(rowData.ggHome);

            var flag =false;
            if(rowData.isTop==1){
                flag=true;
            }
            $("#editGoodsClassificationForm #isTop").bootstrapSwitch("state",flag);

            if(rowData.isTop==0){
                $("#editGoodsClassificationForm #parentId").val(rowData.parentId);
                $("#editGoodsClassificationForm #parentNm").val(rowData.parentName);
                $("#parentNmElement").show();
            }else{
                $("#editGoodsClassificationForm #parentId").val("");
//                $("#editGoodsClassificationForm #parentNm").val("顶端节点");
                $("#parentNmElement").hide();

            }
        }else{
            $("#editGoodsClassificationForm #isTop").bootstrapSwitch("state",true);
            $("#editGoodsClassificationForm #ggName").val('');
            $("#editGoodsClassificationForm #ggHomeNm").val('');
            $("#editGoodsClassificationForm #parentNm").val('');
            $("#editGoodsClassificationForm #parentId").val('');
            $("#editGoodsClassificationForm #ggImgpath").val('');
            $("#editGoodsClassificationForm #goodsClassificationImg")[0].src='';
            $("#editGoodsClassificationForm #ggOrder").val('');
        }

        if(editorGoodsClassificationType==1){
            $("#exampleModalLabel").text("编辑商品子类");
        }else{
            $("#exampleModalLabel").text("添加商品子类");
        }
        $('#editGoodsClassification').modal("show")
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

    /**
     * form 校验
     * */
    function formValidater(){
        $('#editGoodsClassificationForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    ggName: {
                        message: '商品子类名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品子类名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品子类名称长度在1-30之间'
                            }
                        }
                    },
//                    parentId: {
//                        message: '上级分类名称未通过',
//                        validators: {
//                            notEmpty: {
//                                message: '上级分类名称不能为空'
//                            },
//                            stringLength: {
//                                min: 1,
//                                max: 30,
//                                message: '上级分类名称长度在1-30之间'
//                            }
//                        }
//                    },
//                    ggHome: {
//                        message: '所属大类未通过',
//                        validators: {
//                            notEmpty: {
//                                message: '所属大类不能为空'
//                            },
//                            stringLength: {
//                                min: 1,
//                                max: 30,
//                                message: '上级分类名称长度在1-30之间'
//                            }
//                        }
//                    }
                }
            });
    }
    function  myValidate() {
        var ggName=$("#ggName").val();
        var ggHome=$("#ggHome").val();
        var ggOrder=$("#ggOrder").val();
        var flag=$("#editGoodsClassificationForm #isTop").bootstrapSwitch("state");
        var parentId=$("#parentId").val();

        if(ggName==null || ggName==""){
            layer.msg("名称不能为空！");
            return false;
        }
        if(ggHome==null || ggHome==""){
            layer.msg("所属大类不能为空！");
            return false;
        }
        if(ggOrder==null || ggOrder==""){
            layer.msg("排序不能为空！");
            return false;
        }
        if(flag){

        }else {
            if(ggOrder==null || ggOrder==""){
                layer.msg("排序不能为空！");
                return false;
            }
        }
        return true;




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
