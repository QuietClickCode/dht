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
    <style>
        .house_type_table{
            float: left;
            width: 80%;
            height: 500px;
            overflow-y: scroll;
        }

        .house_type_list{
            float: left;
            width: 20%;
            height: 500px;
            border-left: none;
        }

        #house_type_list{
            padding-left: 20px;
            list-style: none;
            position: relative;
        }

        #house_type_list li{
            list-style: none;
            text-align: center;
            display: block;
            height: 30px;
            line-height: 30px;
            border: 1px solid rgba(0,0,0,0.1);
            margin-bottom: 10px;
            border-radius: 5px;
            position: relative;
        }

        .remove_icon{
            width: 15px;
            height: 15px;
            right: -5;
            top: -5;
            cursor: pointer;
            position: absolute;
            display: block;
            background: url("/img/remove_icon.png") no-repeat;
            background-size: 15px 15px;
        }
    </style>
</head>

<table id="goodsTypeTables" ></table>

<div class="modal fade" id="editFloor" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateEmployeeForm">
                    <div class="form-group" style="display: none">
                        <label class="col-sm-3 control-label">ID:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emId"  placeholder="ID">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">开盘名称:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="oname" id="oname"   disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">开始日期:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="startTime" id="startTime"  placeholder="" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">结束日期:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="endTime" id="endTime"  placeholder="" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">操作时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="createTime" name="createTime"   disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">操作人:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="uploadpersonName" name="uploadpersonName"   disabled="disabled">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
            field: 'oname',
            title: '开盘名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'ostartTime',
            title: '开始日期',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                var html = '';
                if(value!=null){
                    html = value.substring(0,10);
                }else{
                    html = '';
                }
                return html;
            }
        },
        {
            field: 'oendTime',
            title: '结束日期',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                var html = '';
                if(value!=null){
                    html = value.substring(0,10);
                }else{
                    html = '';
                }
                return html;
            }
        },
        {
            field: 'uploadpersonName',
            title: '操作人',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'uploadtype',
            title: '操作类型',
            width:'200px',
            class:'fmInfo',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                var html = '';
                if(value==0){
                    html = '新增';
                }else if(value==1){
                    html = '修改';
                }else if(value==2){
                    html = '删除';
                }

                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '详情',
            formatter:function (value,row,index) {
                rowDatas.set(row.ocId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editFloor('+row.ocId+')">详情</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openingCopy/queryopeningCopyList","goodsTypeTables","fmId",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
        };
    }

</script>



<%--编辑楼栋信息--%>
<script>
    /*防止重复提交*/
    var isSave = false;
    /*当前的楼栋对象*/
    var f;
    /*打开编辑楼栋模态框*/
    function editFloor(fmId) {
        f = rowDatas.get(fmId);

        var oname = f.oname;
        var ostartTime = f.ostartTime;
        var oendTime = f.oendTime;
        var createTime = f.createTime;
        var uploadpersonName = f.uploadpersonName;

        $('#oname').val(oname);
        $('#startTime').val(ostartTime.substring(0,10));
        $('#endTime').val(oendTime.substring(0,10));

        $('#createTime').val(createTime);
        $('#uploadpersonName').val(uploadpersonName);

        $("#editFloor").modal("show");
    }

</script>
</body>
</html>
