<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品大类管理</title>
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
    <style type="text/css">
        #Client button{
            height: 34px;
            width: 110px;
        }
    </style>
</head>
<body>
<div id="toolbar" class="form-inline">
    <button class="btn btn-primary" type="button" onclick="addNavigationBar()" style="margin-bottom: 5px">新增首页广告</button>
    <div id="Client">
        <button class="btn btn-primary" data-clientValue="0">移动端</button>
        <button class="btn btn-primary" data-clientValue="1">PC端</button>
        <button class="btn btn-primary" data-clientValue="2">小程序</button>
        <input id="clientValue" style="display: none" value="">
    </div>
</div>
<div>
    <table id="goodsTypeTables" ></table>
</div>
<%--新增首页导航--%>
<div class="modal fade" id="addHomeNavigationBar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该导航栏</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">选择样式</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">选择推送区域<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" id="chooseStyle" aria-labelledby="dropdownMenu1">
                                    <li><a style="cursor: pointer" data-styleValue="0">顶部区域</a></li>
                                    <li><a style="cursor: pointer" data-styleValue="1">中间区域</a></li>
                                    <li><a style="cursor: pointer" data-styleValue="1">底部区域</a></li>
                                </ul>
                                <input id="styleText" style="display: none">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">选择推送对象<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                    <li><a style="cursor: pointer" data-CountryValue="0">乡村</a></li>
                                    <li><a style="cursor: pointer" data-CountryValue="1">城市</a></li>
                                </ul>
                                <input id="countryText" style="display: none">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="NaviName" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">选择客户端<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
                                    <li><a style="cursor: pointer" data-clientValue="0">移动端</a></li>
                                    <li class="disabled"><a  data-clientValue="1">PC端</a></li>
                                    <li class="disabled"><a  data-clientValue="2">小程序</a></li>
                                </ul>
                                <input id="clientText" style="display: none">
                            </div>
                        </div>
                    </div>


                    <div class="form-group" id="">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input type="file" id="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" id="" class="form-control">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="updateNavigator()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑导航--%>
<div class="modal fade" id="saveAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">编辑该广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10" id="">
                            <input type="text" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group" id="chooseClassifyLabel">
                        <label for="" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input type="file" id="exampleInputFile">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" id="" class="form-control">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="updateNavigator()">确定</button>
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
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsTypeType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'haName',
            title: '广告名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'haOrder',
            title: '排序',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'isShow',
            title: '状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                let show = row.isShow;
                let isShow;
                if(show == 1)
                    isShow = "显示";
                else
                    isShow = "不显示"
                html+='<p>'+isShow+'</p>'
                return html;
            }
        },
        {
            field: 'url',
            title: '链接',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '编辑',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();openSaveAdvertisingModal()">编辑</button>'
                return html;
            }
        },
        {
            field: 'hnCountry',
            title: '删除',
            align : 'center',
            valign : 'middle',
            formatter:function (value,row,index) {
                rowDatas.set(row.flId,row);
                let html='';
                html+='<button class="btn btn-primary" onclick="event.stopPropagation();">删除</button>'
                return html;
            }
        }
    ]

    $(function () {
        createTable("/openHomeAdvertising/queryAdvertisingLists","goodsTypeTables","haId",treeColumns,queryParams)
    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gtName: $("#search_goodsType_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsTypeTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsTypeLists"
            }
        );
    }

    $("#Client button").click(function () {
        let clientValue = $(this).attr("data-clientValue");
        $("#clientValue").attr("value",clientValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });

    function openSaveAdvertisingModal() {
        $("#saveAdvertising").modal("show");
    }

    $("#chooseCountry input").click(function () {
        let countryValue = $(this).attr("data-Country");
        $("#countryValue").attr("value",countryValue);
        $(this).addClass("btn-success").siblings().removeClass("btn-success");
    });


    function updateNavigator() {
        let clientValue = $("#clientValue").val();
        let countryValue = $("#countryValue").val();
        let naviName = $("#NaviName").val();
        $("#NaviOrder").val();
        /*alert(clientValue+" "+countryValue);*/
        layer.msg(clientValue +" "+ countryValue);
    }

    /*
     * 新增首页导航
     * */
    function addNavigationBar() {
        $("#addHomeNavigationBar").modal("show");
    }
</script>

</body>
</html>
