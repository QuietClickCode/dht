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
        <input type="text" class="form-control" id="emId" placeholder="请输入置业顾问ID">
        <input type="text" class="form-control" id="tmName" placeholder="请输入客户姓名">
        <button class="btn btn-default" onclick="refreshTableData()">查询</button>
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>

<%--修改客户备注--%>
<div class="modal fade" id="editClientInfo" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改客户备注</h4>
            </div>
            <div class="modal-body">
                <form id="editClientInfoForm">
                    <div class="form-group">
                        <label>客户备注</label>
                        <div class="radio">
                            <label>
                                <input type="radio" class="tmStatus" name="tmStatus" value="0">
                                未购房
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" class="tmStatus" name="tmStatus" value="1">
                                已购房
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>客户备注</label>
                        <textarea class="form-control" name="tmInfo" id="tmInfo" style="resize: none;height: 300px;" rows="3"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary subEditClientInfoForm">确定</button>
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
            field: 'tmName',
            title: '客户姓名',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmSex',
            title: '客户性别',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'empName',
            title: '置业顾问',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmAge',
            title: '客户年龄',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmStatus',
            title: '购房状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html = "";
                if(row.tmStatus == 0)
                    return "未购房";
                if(row.tmStatus == 1)
                    return "已购房";
                return html;
            }
        },
        {
            field: 'tmPhone',
            title: '客户电话',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmRegisterTime',
            title: '登记时间',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html = "";
                if(row.tmRegisterTime != null)
                    html = row.tmRegisterTime.split(" ")[0];
                return html;
            }
        },
        {
            field: 'tmChannel',
            title: '来访渠道',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'tmInfo',
            title: '客户备注',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                let html = "";
                if(row.tmInfo != null)
                    if(row.tmInfo.length > 6)
                        html = row.tmInfo.substring(0,6) + '...';
                return html;
            }
        },
        {
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(''+row.tmId+'',row);
                let html='';
                <ex:perm url="floorManage/updateFloor">
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();editClient(\'' + row.tmId + '\')">编辑</button>'
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/clientInfo/queryClientList","goodsTypeTables","tmId",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            emId:$("#emId").val(),
            tmName:$("#tmName").val(),
            pageSize: that.pageSize,
            pageNo: that.pageNumber
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/clientInfo/queryClientList"
            }
        );
    }

</script>

<%--编辑客户备注信息--%>
<script>
    var client;
    function editClient(id) {
        client = rowDatas.get(id);
        $("#tmInfo").val(client['tmInfo']);
        if(client['tmStatus'] != null)
            radioChoose(".tmStatus",client['tmStatus']);
        $("#editClientInfo").modal("show");
    }

    var isSave = false;
    $(".subEditClientInfoForm").click(function () {
        let status = $("#editClientInfoForm input[type='radio']:checked").val();

        if($("#tmInfo").val() == ""){
            layer.msg("请输入用户备注",{time:'1000'});
            return;
        }

        if(!isSave){
            isSave = true;
            $.ajax({
                url:"/clientInfo/updateClient",
                method:"post",
                data:{
                    tmId:client['tmId'],
                    tmInfo:$("#tmInfo").val(),
                    tmName:client['tmName'],
                    tmStatus:status
                },
                dataType:"json",
                success:function (data) {
                    isSave = false;
                    refreshTableData();
                    layer.msg(data.msg,{time:'1000'});
                    $("#tmInfo").val("")
                    $("#editClientInfo").modal("hide");
                }
            });
        }
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


</body>
</html>
