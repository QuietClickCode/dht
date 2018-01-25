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
                        <label class="col-sm-3 control-label">姓名:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emName" id="emName"  placeholder="请输入姓名" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">性别:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="sex" id="sex"  placeholder="" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">员工类型:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emType" id="emType"  placeholder="" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">入职时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="editEmEntryTime" name="emEntryTimes"  placeholder="请输入入职时间" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">调动时间:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="editEmRemoveTime" name="emRemoveTimes"  placeholder="请输入调动时间" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">职位:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="emPosition"  placeholder="请输入职位" disabled="disabled">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="emPhone" name="emPhone"  placeholder="请输入手机号" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">微信绑定手机号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="wxPhone" name="wxPhone"  placeholder="请输入微信手机号" disabled="disabled">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">身份证号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="emIdCard" name="emIdCard"  placeholder="请输入身份证号" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属团队:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="tname" name="tname"   disabled="disabled">
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
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注:</label>
                        <div class="col-sm-9">
                            <textarea disabled="disabled" class="form-control" id="emInfo" name="emInfo" rows="4" style="resize: none;"></textarea>
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
            field: 'emName',
            title: '扫码员姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'uploadpersonName',
            title: '操作人',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'createTime',
            title: '操作时间',
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
        }

    ]

    $(function () {
        createTable("/scanCodeCopy/queryscanCodeCopyList","goodsTypeTables","fmId",treeColumns,queryParams)
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

        var emName = f.emName;
        var emSex = f.emSex;
        var emType = f.emType;
        var emEntryTime = f.emEntryTime;
        var emRemoveTime = f.emRemoveTime;
        var emPosition = f.emPosition;
        var emPhone = f.emPhone;
        var wxPhone = f.wxPhone;
        var emIdCard = f.emIdCard;
        var emInfo = f.emInfo;
        var createTime = f.createTime;
        var tname = f.tname;
        var uploadpersonName = f.uploadpersonName;

        $('#emName').val(emName);
        $('#sex').val(emSex==1?'男':'女');
        $('#emType').val(emType==1?'置业顾问':emType==2?'管理员':'录入员');
        $('#editEmEntryTime').val(emEntryTime==null?'':emEntryTime);
        $('#editemremovetime').val(emRemoveTime==null?'':emRemoveTime);
        $('#emPosition').val(emPosition);
        $('#emPhone').val(emPhone);
        $('#wxPhone').val(wxPhone);
        $('#emIdCard').val(emIdCard);
        $('#emInfo').val(emInfo);
        $('#createTime').val(createTime);
        $('#tname').val(tname);
        $('#uploadpersonName').val(uploadpersonName);

        $("#editFloor").modal("show");
    }

</script>
</body>
</html>
