<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>远山结亲商品</title>
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


<div>
    <table id="famerGoodsTables" ></table>
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
    var editorfamerGoodsType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'goodsName',
            title: '商品名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'fname',
            title: '所属农户',
            align : 'center',
            valign : 'middle'
        }
    ]

    $(function () {
        createTable("/famerGoods/queryFamerGoodsVoList","famerGoodsTables","rtId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorfamerGoodsForm   #rtType").bootstrapSwitch();

        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorfamerGoodsForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){

            $('#editorfamerGoodsForm').data('bootstrapValidator').validate();
            if(!$('#editorfamerGoodsForm').data('bootstrapValidator').isValid()){
                return;
            }

            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorfamerGoodsForm").serializeObject();

            var flag =$("#editorfamerGoodsForm #rtType").bootstrapSwitch("state");
            if(flag){
                formData["rtType"]=1;
            }else{
                formData["rtType"]=0;
            }

            let url="/goods/addfamerGoods";
            if(editorfamerGoodsType==1){
                url="/goods/editfamerGoods";
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
        $('#editorfamerGoodsForm')
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
                    rtName: {
                        message: '返现类型名称未通过',
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
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#famerGoodsTables').bootstrapTable(
            "refresh",
            {
                url:"/famerGoods/queryFamerGoodsVoList"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removefamerGoods(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removefamerGoods(rtId){
        $.ajax({
            type:"post",
            url:'/goods/removefamerGoods',
            dataType: "json",
            data:{rtId:rtId},
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
    function editorfamerGoods(orgId){
        editorfamerGoodsType=1;
        reloadOrgTree(orgId);
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑返现类型名称");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorfamerGoodsForm #uid").val("");
        $("#editorfamerGoodsForm #version").val("");
        $("#editorfamerGoodsForm #uaccount").val("");
        $("#editorfamerGoodsForm #uname").val("");
        $("#editorfamerGoodsForm #orgIds").val("");
        $("#editorfamerGoodsForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorfamerGoodsForm #rtId").val(rowData.rtId);
            $("#editorfamerGoodsForm #version").val(rowData.version);
            $("#editorfamerGoodsForm #rtName").val(rowData.rtName);

            var flag =false;
            if(rowData.rtType==1){
                flag=true;
            }
            $("#editorfamerGoodsForm #rtType").bootstrapSwitch("state",flag);

        }else{
            $("#editorfamerGoodsForm #rtName").val('');
            $("#editorfamerGoodsForm #rtId").val('');
            $("#editorfamerGoodsForm #rtType").bootstrapSwitch("state",true);
        }
    }
    /**
     * 添加商品大类
     **/
    function addfamerGoods(){
        editorfamerGoodsType=0;
        initFormData();
        $("#editorSysUserTitle").text("添加返现类型名称");
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
    
    function deletefamerGoodsList() {
        var objs = $('#famerGoodsTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removefamerGoods(objs[i].rtId);
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
