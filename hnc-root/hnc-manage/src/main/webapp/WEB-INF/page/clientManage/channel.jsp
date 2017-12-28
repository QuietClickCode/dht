<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
</head>
<div>
    <div id="toolbar" class="form-inline">
        <button class="btn btn-default addChannel" type="button">新增来访渠道</button>
        <input type="text" class="form-control channelVal"  placeholder="请输入来访渠道">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>



<%--新增团队--%>
<div class="modal fade" id="saveChannel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">增加来访渠道</h4>
            </div>
            <div class="modal-body">
                <form id="saveChannelForm">
                    <div class="form-group">
                        <label>来访渠道</label>
                        <input type="text" class="form-control" name="cchannel" placeholder="请输入来访渠道">
                    </div>

                    <div class="form-group">
                        <label>排序序号</label>
                        <input type="text" class="form-control" name="corder" placeholder="请输入排序序号">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subSaveChannel">确定</button>
            </div>
        </div>
    </div>
</div>

<%--编辑团队--%>
<div class="modal fade" id="editChannel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>
            <div class="modal-body">
                <form id="editChannelForm">
                    <div class="form-group" style="display: none;">
                        <label>CID</label>
                        <input type="text" class="form-control" name="cid">
                    </div>

                    <div class="form-group">
                        <label>是否显示</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" class="isShow" name="isShow" value="1"> 显示
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="isShow" name="isShow" value="0"> 隐藏
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>来访渠道</label>
                        <input type="text" class="form-control" name="cchannel" placeholder="请输入来访渠道">
                    </div>

                    <div class="form-group">
                        <label>排序序号</label>
                        <input type="text" class="form-control" name="corder" placeholder="请输入排序序号">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subEditChannel">确定</button>
            </div>
        </div>
    </div>
</div>


<%--删除团队模态框--%>
<div class="modal fade" id="deleteChannel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除该渠道</h4>
            </div>
            <div class="modal-body">
                <p>删除该渠道</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary subDeleteThisChannel">确定</button>
            </div>
        </div>
    </div>
</div>


<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript" src="/js/layer/layer.js"></script>

<%--初始化表格数据--%>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'cid',
            title: '渠道ID',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'cchannel',
            title: '来访渠道',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'corder',
            title: '排序',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'isShow',
            title: '是否显示',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html='';
                if(row.isShow == 1){
                    return "显示"
                }else if(row.isShow == 0){
                    return "隐藏";
                }
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(''+row.cid+'',row);
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editChannel(\'' + row.cid + '\')">编辑</button>'
                </ex:perm>
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '删除',
            formatter:function (value,row,index) {
                let html='';
                <ex:perm url="floorManage/removeFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();removeChannel(\'' + row.cid + '\')">删除</button>'
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/channel/queryChannelList","goodsTypeTables","cid",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            channelVal:$(".channelVal").val(),
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/channel/queryChannelList"
            }
        );
    }

</script>

<%--新增来访渠道--%>
<script>
    var isSave = false;
    $(".addChannel").click(function () {
        $("#saveChannel").modal("show");
    });

    $(".subSaveChannel").click(function () {
        let bootstrapValidator = $("#saveChannelForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        if(!isSave){
            isSave = true;
            var floor = document.getElementById("saveChannelForm");
            var data = new FormData(floor);
            data.append("isDelete", 0);
            data.append("isShow", 1);
            $.ajax({
                url:"/channel/addChannel",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    /*layer.msg(data.msg,{time:'1000'});*/
                    $("#saveChannel").modal("hide");
                    $("#saveChannelForm").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>

<%--编辑来访渠道--%>
<script>
    var channel;
    function editChannel(id) {
        channel = rowDatas.get(id);

        $("#editChannelForm input[type='text']").each(function () {
            let name = $(this).attr("name");
            $(this).val(channel[name]);
        });
        radioChoose(".isShow",channel['isShow']);
        $("#editChannel").modal("show");
    }

    $(".subEditChannel").click(function () {
        let bootstrapValidator = $("#editChannelForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        if(!isSave){
            isSave = true;
            var floor = document.getElementById("editChannelForm");
            var data = new FormData(floor);
            $.ajax({
                url:"/channel/updateChannel",
                method:"post",
                data:data,
                processData : false,
                contentType : false,
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    $("#editChannel").modal("hide");
                    $("#editChannelForm").data('bootstrapValidator').resetForm(true);
                }
            });
        }
    });
</script>


<%--删除团队--%>
<script>
    /*打开是删除团队模态框*/
    function removeChannel(id) {
        channel = rowDatas.get(id);
        $("#deleteChannel").modal("show");
    }

    /*提交删除*/
    $(".subDeleteThisChannel").click(function () {
        $.ajax({
            url:"/channel/removeChannel",
            method:"post",
            data:{
                cid:channel['cid']
            },
            success:function (data) {
                isSave = false;
                refreshTableData();
                $("#deleteChannel").modal("hide");
            }
        });
    });
</script>

<%--自定义方法--%>
<script>
    /*为单选框赋值*/
    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }
</script>

<%--表单校验--%>
<script type="text/javascript">
    $('#saveChannelForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            cchannel: {
                validators: {
                    notEmpty: {
                        message: '来访渠道不能为空'
                    }
                }
            },
            corder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            }
        }
    });

    $('#editChannelForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            cchannel: {
                validators: {
                    notEmpty: {
                        message: '来访渠道不能为空'
                    }
                }
            },
            corder: {
                validators: {
                    notEmpty: {
                        message: '排序序号不能为空'
                    }
                }
            }
        }
    });

</script>
</body>
</html>
