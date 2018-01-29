<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div>
    <table id="menuResource" ></table>
</div>
<div class="modal fade" id="eidtResource" tabindex="-1" role="dialog" aria-labelledby="eidtResource">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">编辑资源菜单</h4>
            </div>
            <div class="modal-body">
                <form id="eidtResourceForm">
                    <input type="hidden" name="id" id="id">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                资源名称:
                              </span>
                                <input type="text" class="form-control" name="label" id="label">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                资源路径:
                              </span>
                                <input type="text" class="form-control" name="resourse" id="resourse" readonly>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                访问路径:
                              </span>
                                <input type="text" class="form-control" name="url" id="url" readonly>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                资源图标:
                              </span>
                                <%--<input type="text" class="form-control" name="icon" id="icon">   data-iconset="mapicon"--%>

                                <button class="btn btn-default iconpicker"
                                        data-iconset="fontawesome" data-placement="bottom" role="iconpicker"
                                        data-cols="5"
                                        data-rows="5"
                                        data-original-title="" title="" id="selectIcon" style="height: 34px;">
                                    <i class="glyphicon glyphicon-asterisk"></i>
                                    <input value="" type="hidden" name="icon" id="icon">
                                    <span class="caret"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                资源类型:
                              </span>
                                <input type="text" class="form-control" aria-label="..." id="type" name="type" readonly>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                资源状态:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isValid" name="isValid" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6" id="parsentNode">
                            <div class="input-group">
                              <span class="input-group-addon">
                                上级资源:
                              </span>
                                <input type="hidden" id="parentId" name="parentId"/>
                                <input type="text" class="form-control" aria-label="..." id="parentNm" name="parentNm" onclick="showMenu(); return false;"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                显示顺序:
                              </span>
                                <input type="text" class="form-control" aria-label="..." id="sort" name="sort">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="description" class="control-label">描述</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
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
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
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
        $('#menuResource').bootstrapTable({
            url:"/menu/queryMenuList",
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
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            treeView: true,
            treeId: "id",
            treeField: "label",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            }, {
                field: 'label',
                title: '资源名称',
                formatter:function(value,row,index){
                    let html='';
                    if(row.icon){
                        html='<i class="fa '+row.icon+'">';
                    }
                    html+=row.label;
                    return html;
                }

            },{
                field: 'url',
                title: '资源路径'

            },{
                field: 'sort',
                title: '显示顺序'

            },{
                field: 'icon',
                title: '图标'

            },{
                field: 'type',
                title: '资源类型',
                formatter:function(value,row,index){
                    if(value==0){
                        return "资源";
                    }else if(value==1){
                        return "菜单";
                    }else if(value==2){
                        return "功能按钮";
                    }else{
                        return "未知"
                    }
                }
            },{
                field: 'isValid',
                title: '状态',
                align : 'center',
                valign : 'middle',
                width:120,
                formatter:function(value,row,index){
                    rowDatas.set(row.id,row);
                    if(value==0){
                        return "启用";
                    }else if(value==1){
                        return "停用";
                    }else{
                        return "未知"
                    }
                }

            },{
                field: 'CreateTime',
                title: '操作',
                align : 'center',
                valign : 'middle',
                width:240,
                formatter:function(value,row,index){
                    let html='';
                    <ex:perm url="menu/editorResource">
                        html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editResource(\''+row.id+'\',\''+row.parentId+'\')"">编辑</button>&nbsp;&nbsp;';
                    </ex:perm>
                    <ex:perm url="menu/removeResource">
                        html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.id+'\',this)">删除</button>';
                    </ex:perm>
                    return html;
                }
            }],
        });
        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            var sendData=new Array();
            var formData=$("#eidtResourceForm").serializeObject();
            var flag =$("#eidtResourceForm #isValid").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            var icon=$("#eidtResourceForm #selectIcon").find("input[type=hidden]").val();
            formData.icon=icon;
            //取得form表单数据
            $.ajax({
                type:"post",
                url:'/menu/editorResource',
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0){
                        //显示提示
                        layer.msg("编辑成功");
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#eidtResource').modal('hide')
                    }
                }
            });
        });
        //初始华开关选择器
        $("#eidtResourceForm #isValid").bootstrapSwitch();
        $('#eidtResource').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
        })
    });
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#menuResource').bootstrapTable(
                "refresh",
                {
                    url:"/menu/queryMenuList"
                }
        );
    }
    //删除确认框
    function deleteData(id,e){
        bootbox.confirm({
            title:"删除确认",
            message: "你确定删除选中的资源吗，删除后不可再恢复?",
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
                        url:'/menu/removeResource',
                        dataType: "json",
                        data:{id:id},
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
    var zNodes;
    function editResource(id,parentId){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        var rowData=rowDatas.get(parseInt(id,10));
        if(rowData.type!=2){
            $.ajax({
                type:"post",
                url:'/menu/queryMenuNode',
                dataType: "json",
                data:{menuId:id},
                async:false,
                success:function(data){
                    let d=data.data;
                    var nodeData=new Array();
                    for(row of d){
                        let treeRow=new Object();
                        treeRow.id=row.id;
                        treeRow.pId=row.parentId;
                        treeRow.name=row.label;
                        nodeData.push(treeRow);
                    }
                    var zTree=$.fn.zTree.init($("#treeDemo"), setting, nodeData);
                    var node = zTree.getNodeByParam("id",parentId);
                    if(node){
                        zTree.selectNode(node);
                    }
                }
            });
        }
        initFormData(id);
        $('#eidtResource').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#eidtResourceForm #id").val("");
        $("#eidtResourceForm #label").val("");
        $("#eidtResourceForm #resourse").val("");
        $("#eidtResourceForm #url").val("");
//        $("#eidtResourceForm #icon").val("");
        $("#eidtResourceForm #type").val("");
        $("#eidtResourceForm #sort").val("");
        $("#eidtResourceForm #description").val("");
        $("#eidtResourceForm #version").val("");
        $("#eidtResourceForm #parentId").val("");
        $("#eidtResourceForm #parentNm").val("");
        $("#eidtResourceForm #selectIcon").find("input[type=hidden]").val("");


    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        $("#eidtResourceForm #id").val(rowData.id);
        $("#eidtResourceForm #label").val(rowData.label);
        $("#eidtResourceForm #resourse").val(rowData.resourse);
        $("#eidtResourceForm #url").val(rowData.url);
//        $("#eidtResourceForm #icon").val(rowData.icon);
        $("#eidtResourceForm #type").val(rowData.type);
        $("#eidtResourceForm #sort").val(rowData.sort);
        $("#eidtResourceForm #description").val(rowData.description);
        $("#eidtResourceForm #version").val(rowData.version);
        $("#eidtResourceForm #selectIcon").find("input[type=hidden]").val(rowData.icon);
        $("#eidtResourceForm #selectIcon").find("i").attr("class","");
        if(rowData.icon){
            $("#eidtResourceForm #selectIcon").find("i").addClass("fa").addClass(rowData.icon);
        }

        var flag =false;
        if(rowData.isValid==0){
            flag=true;
        }

        $("#eidtResourceForm #isValid").bootstrapSwitch("state",flag);
        if(rowData.type==2){
            $("#eidtResourceForm #parentId").val(rowData.parentId);
            $("#parsentNode").hide();
        }else{
            let parObj=rowDatas.get(rowData.parentId);
            if(parObj){
                $("#eidtResourceForm #parentId").val(parObj.id);
                $("#eidtResourceForm #parentNm").val(parObj.label);
            }else{
                $("#eidtResourceForm #parentId").val("");
                $("#eidtResourceForm #parentNm").val("顶端节点");
            }
            $("#parsentNode").show();
        }
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
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    </script>
</body>
</html>
