<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品品牌管理</title>
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
            $('#gbImgpath').val(arg[0].alt);
            $('#logoImg')[0].src=arg[0].src;
            $('#logoImg').show();
            $('#logoImgSpan').hide();
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

<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsBrand">
        <button class="btn btn-primary" type="button" onclick="addGoodsBrand()">添加商品品牌</button>
    </ex:perm>
    <ex:perm url="goods/addGoodsBrand">
        <button class="btn btn-default" type="button" onclick="deleteGoodsBrandList()">删除</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_GoodsBrand_name" placeholder="请输入商品品牌名称">
    </div>

    <ex:perm url="goods/queryGoodsBrandLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsBrandTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsBrandForm">
                    <input type="hidden" name="gbId" id="gbId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品品牌名称:
                              </span>
                                <input type="text" class="form-control" name="gbName" id="gbName">
                            </div>
                        </div>
                        <input type="hidden" class="form-control" name="gbImgpath" id="gbImgpath">
                        <%--<div class="col-lg-12">--%>
                            <%--<div class="input-group form-group">--%>
                              <%--<span class="input-group-addon">--%>
                                <%--商品品牌logo:--%>
                              <%--</span>--%>
                                <%--<input type="hidden" class="form-control" name="gbImgpath" id="gbImgpath">--%>
                                <%--<img style="width: 50px;"src=""  id="logoImg"/>--%>
                                <%--<span id="logoImgSpan">无图片</span>--%>
                                <%--<button onclick="upImage()" class="btn btn-default" style="line-height: 100%">添加图片</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <br>
                </form>
                <form id="cpImagesForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4" id="cpLogoDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        商品品牌logo:
                                    </span>
                                <input type="file" id="dht_image_upload" name="dht_image_upload">
                            </div>
                        </div>
                        <div class="col-lg-4" id="clearCpLogoDiv" style="display: none;">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                        商品品牌logo:
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
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
<script type="text/javascript" src="<%=path%>/js/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsBrandType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gbName',
            title: '商品品牌名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'imgUrl',
            title: 'logo',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                var flag =row.imgUrl.substring(row.imgUrl.length-4)=="null";
                if(flag){
                    html+='无图片';
                }else{
                    html+='<img style="width: 50px;height: 60px;;"src="'+row.imgUrl+'"  />';
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
                rowDatas.set(row.gbId,row);
                let html='';
                <ex:perm url="goods/editGoodsBrand">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsBrand(\''+row.gbId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsBrand">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gbId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsBrandLists","GoodsBrandTables","gbId",treeColumns,queryParams)
        //初始华开关选择器

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsBrandForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){

            $('#editorGoodsBrandForm').data('bootstrapValidator').validate();
            if(!$('#editorGoodsBrandForm').data('bootstrapValidator').isValid()){
                return;
            }

            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsBrandForm").serializeObject();

            let url="/goods/addGoodsBrand";
            if(editorGoodsBrandType==1){
                url="/goods/editGoodsBrand";
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
        $('#editorGoodsBrandForm')
            .bootstrapValidator({
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
                    gbName: {
                        message: '品牌名称未通过',
                        validators: {
                            notEmpty: {
                                message: '品牌名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '品牌名称长度在1-30之间'
                            }
                        }
                    },
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
            gbName: $("#search_GoodsBrand_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsBrandTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsBrandLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsBrand(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsBrand(gbId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsBrand',
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
    function editorGoodsBrand(orgId){
        editorGoodsBrandType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品品牌");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsBrandForm #uid").val("");
        $("#editorGoodsBrandForm #version").val("");
        $("#editorGoodsBrandForm #uaccount").val("");
        $("#editorGoodsBrandForm #uname").val("");
        $("#editorGoodsBrandForm #orgIds").val("");
        $("#editorGoodsBrandForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsBrandForm #gbId").val(rowData.gbId);
            $("#editorGoodsBrandForm #version").val(rowData.version);
            $("#editorGoodsBrandForm #gbName").val(rowData.gbName);
            $("#editorGoodsBrandForm #gbImgpath").val(rowData.gbImgpath);
            var flag =rowData.imgUrl.substring(rowData.imgUrl.length-4)=="null";

            if(flag){
                $("#cpLogoDiv").show();
                $("#clearCpLogoDiv").hide();
                return;
            }else{
                $("#cpLogoDiv").hide();
                $("#clearCpLogoDiv").show();
                $("#uploadImage")[0].src=rowData.imgUrl;
            }
//            $("#editorGoodsBrandForm #logoImg").show();
//            $("#editorGoodsBrandForm #logoImgSpan").hide();

        }else{
            $("#cpLogoDiv").show();
            $("#clearCpLogoDiv").hide();
            $("#editorGoodsBrandForm #gbName").val('');
            $("#editorGoodsBrandForm #gbId").val('');
            $("#editorGoodsBrandForm #gbImgpath").val('');

        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsBrand(){
        editorGoodsBrandType=0;
        let orgId,orgPid;
        reloadOrgTree();
        initFormData();

        $("#editorSysUserTitle").text("添加商品品牌");
        $('#editorSysUser').modal("show")
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
    
    function deleteGoodsBrandList() {
        var objs = $('#GoodsBrandTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removeGoodsBrand(objs[i].gbId);
                }
            }, function(){
            });
        }else{
            layer.msg("请选择需要删除的品牌！");
        }
    }
</script>
<script>
    $('#cpImagesForm #dht_image_upload').filestyle({
        btnClass : "btn-primary",
        text:"选择文件",
        onChange:function(){
            editSubmitIndex = layer.load(2);
            cpImagesFormSummit();
        }
    });

    let fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods"
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
                    $("#gbImgpath").val(returndata.original);
                }
                layer.close(editSubmitIndex);
            },
            error: function (returndata) {
                layer.close(editSubmitIndex);
            }
        });
    }
    //清除文件
    function clearCpLogo(){
        $('#cpImagesForm #dht_image_upload').filestyle('clear');
        $("#cpImagesForm #uploadImageDiv").hide();
        $("#cpImagesForm #cpLogoDiv").show();
        $("#cpImagesForm #clearCpLogoDiv").hide();
        $("#gbImgpath").val('-1');
    }
</script>
</body>
</html>
