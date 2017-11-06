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
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>
</head>
<body>
<div id="toolbar" class="form-inline">
    <button class="btn btn-default" type="button" onclick="addPromotion()">新增特价名单</button>
    <select id="regionMenu" class="form-control">
        <option value="">活动状态</option>
        <option value="0">已开始</option>
        <option value="1">进行中</option>
        <option value="2">已结束</option>
    </select>

    <button class="btn btn-default" onclick="refreshTableData()">查询</button>
</div>

<div>
    <table id="goodsClassificationTable" ></table>
</div>


<%--添加特价活动--%>
<div class="modal fade" id="saveSalePromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增特价名单</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="saveSalePromotionForm">
                    <div class="form-group">
                        <label for="promotionName" class="col-sm-2 control-label">特价名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="propotionName" id="promotionName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">促销类型</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spType"  value="0">
                                    特价
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spType"  value="1">
                                    秒杀
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="startTime" name="spStartTimes" placeholder="请选择日期" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="endTime"  name="spEndTimes" placeholder="请选择日期" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="spOrder" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="savePromotion()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑特价活动--%>
<div class="modal fade" id="updateSalePromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑特价名单</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateSalePromotionForm">
                    <div class="form-group">
                        <label for="promotionName" class="col-sm-2 control-label">特价名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="propotionName"  class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">促销类型</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spType" class="spType"  value="0">
                                    特价
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spType" class="spType"  value="1">
                                    秒杀
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="spStartTimes" id="spStartTime" placeholder="请选择日期" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="spEndTimes" id="spEndTime" placeholder="请选择日期" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="spOrder" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="updateGoods(0)">确定</button>
            </div>
        </div>
    </div>
</div>


<%--添加商品--%>
<div class="modal fade" id="addSalePromotion" tabindex="-1" style="overflow-y: auto;" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addSalePromotionGoods">
                    <div class="form-group" style="display: none">
                        <label for="" class="col-sm-2 control-label">商品ID</label>
                        <div class="col-sm-10">
                            <input type="text" name="goodsId" class="form-control goodsId" readonly >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                                <label class="sr-only">Amount (in dollars)</label>
                                <div class="input-group">
                                    <div style="cursor: pointer" onclick="chooseGoodsModal(0)" class="input-group-addon">选择商品</div>
                                    <input type="text"  class="form-control goodsName" name="goodsName" readonly>
                                </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">优惠券</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="isCoupon" class="isCoupon" value="1">
                                    使用优惠券
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="isCoupon" class="isCoupon" value="0">
                                    不使用优惠券
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spRegion" class="spRegion" value="0">
                                    乡村
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="spRegion" class="spRegion" value="1">
                                    城市
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="spOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">原价</label>
                        <div class="col-sm-10">
                            <input type="text" name="spPrice" readonly class="goodsPrice form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">折扣率</label>
                        <div class="col-sm-10">
                            <input type="text" name="spDiscountRate" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSale" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">已售数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSoldOut" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSaleSize" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">限购量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spBounds" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="addSaleGoods()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑商品--%>
<div class="modal fade" id="updateSalePromotionGoods" tabindex="-1" style="overflow-y: auto;" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateSalePromotionGoodsFrom">
                    <div class="form-group" style="display: none">
                        <label for="" class="col-sm-2 control-label">商品ID</label>
                        <div class="col-sm-10">
                            <input type="text" name="goodsId" class="form-control goodsId" readonly >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <label class="sr-only">Amount (in dollars)</label>
                            <div class="input-group">
                                <div style="cursor: pointer" onclick="chooseGoodsModal(1)" class="input-group-addon">选择商品</div>
                                <input type="text"  class="form-control goodsName"  name="goodsName" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">优惠券</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isCoupon" class="isCoupon" value="1">使用优惠券
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isCoupon" class="isCoupon" value="0">不使用优惠券
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="spRegion" class="spRegion" value="0">乡村
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="spRegion" class="spRegion" value="1">城市
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="spOrder" id="setAdvertOrder" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">原价</label>
                        <div class="col-sm-10">
                            <input type="text" name="spPrice" readonly class="goodsPrice form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">折扣率</label>
                        <div class="col-sm-10">
                            <input type="text" name="spDiscountRate" id="spDiscountRate" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSale" id="spSale" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">已售数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSoldOut" id="spSoldOut" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spSaleSize" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">限购量</label>
                        <div class="col-sm-10">
                            <input type="text" name="spBounds" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="updateGoods(1)">确定</button>
            </div>
        </div>
    </div>
</div>



<%--添加楼层广告--%>
<div class="modal fade" id="chooseGoodsList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline" style="margin-bottom: 15px;">
                    <div class="form-group">
                        <input type="text" class="form-control" id="inputGoodsName" placeholder="请输入商品名称">
                    </div>
                    <button type="button" onclick="queryGoodsName()"  class="btn btn-default">查询</button>
                </form>

                <table id="salePromotionList"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="setGoods()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--删除广告模态框--%>
<div class="modal fade" id="deleteSalePromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">删除分类</h4>
            </div>
            <div class="modal-body">
                <p>删除该分类</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="deleteSalePromotion()">确定</button>
            </div>
        </div>
    </div>
</div>


<!-- 公用下拉择树 -->
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
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>

<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    $(function () {
        //表格的初始化
        $('#goodsClassificationTable').bootstrapTable({
            url:"/openSalePromotion/querySalePromotionLists",
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: function (params) {
                return {
                    pageSize:100
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
            undefinedText:"",
            treeId:"spId",
            treeField:"propotionName",
            treePid:"parentId",                         //上级菜单关联id
            treeRootLevel: 1,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "spId",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            },{
                field: 'propotionName',
                align : 'left',
                valign : 'middle',
                title: '名称'

            },{
                field: 'goodsName',
                align : 'center',
                valign : 'middle',
                title: '商品名称'

            },{
                field: 'spOrder',
                align : 'center',
                valign : 'middle',
                title: '排序'

            },{
                align : 'center',
                valign : 'middle',
                title: '促销类型',
                formatter:function (value,row,index) {
                    rowDatas.set(row.spId,row);
                    let type = row.spType;
                    let html = '';
                    if(type == 0)
                        html+="<p>特价</p>"
                    else if (type == 1)
                        html+="<p>秒杀</p>"
                    return html;
                }

            },{
                field: 'spStartTime',
                align : 'center',
                valign : 'middle',
                title: '开始时间'

            },{
                field: 'spEndTime',
                align : 'center',
                valign : 'middle',
                title: '结束时间'

            },{
                field: 'spPrice',
                align : 'center',
                valign : 'middle',
                title: '原价'

            },{
                field: 'spSale',
                align : 'center',
                valign : 'middle',
                title: '特价'
            },{
                field: 'spBounds',
                align : 'center',
                valign : 'middle',
                title: '限购量'

            },{
                field: 'spDiscountRate',
                align : 'center',
                valign : 'middle',
                title: '已售数量'

            },{
                align : 'center',
                valign : 'middle',
                title: '库存量'

            },{
                align : 'center',
                valign : 'middle',
                title: '添加商品',
                formatter:function (value,row,index) {
                    rowDatas.set(row.spId,row);
                    let html='';
                    if(row.parentId == null)
                    html+='<button class="btn btn-primary" onclick="event.stopPropagation();openAddPromotionModal(\''+row.spId+'\')">添加商品</button>'
                    return html;
                }

            },{
                    title: '编辑',
                    align : 'center',
                    valign : 'middle',
                    formatter:function (value,row,index) {
                        rowDatas.set(row.spId,row);
                        let html='';
                        html+='<button class="btn btn-primary" onclick="event.stopPropagation();updateSalePromotion(\''+row.parentId+'\',\''+row.spId+'\',\''+row.version+'\')">编辑</button>'
                        return html;
                    }
                },
                {
                    title: '删除',
                    align : 'center',
                    valign : 'middle',
                    formatter:function (value,row,index) {
                        rowDatas.set(row.spId,row);
                        let html='';
                        html+='<button class="btn btn-primary" onclick="event.stopPropagation();deletePromotion(\''+row.spId+'\')">删除</button>'
                        return html;
                    }
                }]
        });
    });


    $(function () {
        createSalePromotionTable("/goods/queryGoodsLists","salePromotionList","gid",goodsColumns,queryGoodsParams);
    });


    var goodsColumns=[
        {   radio: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gid',
            title: '商品ID',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gname',
            title: '商品名称',
            align : 'center',
            valign : 'middle'
        }
    ]

    function queryGoodsParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gname:$("#inputGoodsName").val(),
            gtName: $("#search_goodsType_name").val(),
        };
    }

    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsClassificationTable').bootstrapTable(
            "refresh",
            {
                url:"/openSalePromotion/querySalePromotionLists"
            }
        );
    }


    function addPromotion() {
        $("#saveSalePromotion").modal("show");
    }


    /*添加商品*/
    function savePromotion() {
        let bootstrapValidator = $("#saveSalePromotionForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        var saveSale = document.getElementById("saveSalePromotionForm");
        var data = new FormData(saveSale);
        data.append("isDelete", 0);
        $.ajax({
            url:"/openSalePromotion/addSalePromotion",
            method:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function (data) {
                refreshTableData();
                $("#saveSalePromotion").modal("hide");
                $("#saveSalePromotionForm").data('bootstrapValidator').resetForm(true);
            }
        });
    }
    
    function openAddPromotionModal(id) {
        parentId = id;
        $("#addSalePromotion").modal("show");
    }

    var number;
    var parentId;
    var id;
    var version;
    function chooseGoodsModal(num) {
        number = num;
        $("#chooseGoodsList").modal("show");
    }

    function queryGoodsName() {
        $('#salePromotionList').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLists"
            }
        );
    }
    
    function setGoods() {
        var goods = $('#salePromotionList').bootstrapTable('getSelections');
        $(".goodsId").eq(number).val(goods[0].gid);
        $(".goodsName").eq(number).val(goods[0].gname);
        $(".goodsPrice").eq(number).val(goods[0].gprice);
        $("#chooseGoodsList").modal("hide");
    }
    
    function addSaleGoods() {
        let bootstrapValidator = $("#addSalePromotionGoods").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        var goods = document.getElementById("addSalePromotionGoods");
        var data = new FormData(goods);
        data.append("isDelete", 0);
        data.append("parentId", parentId);
        $.ajax({
            url:"/openSalePromotion/addSalePromotion",
            method:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function (data) {
                refreshTableData();
                $("#addSalePromotion").modal("hide");
                $("#addSalePromotionGoods").data('bootstrapValidator').resetForm(true);
            }
        });
    }

    /*设置表单默认值*/
    function updateSalePromotion(parentId,spid,v) {
        id = spid;
        version = v;
        if(parentId == "null"){
            let goods = $("#goodsClassificationTable").bootstrapTable("getRowByUniqueId",spid);
            setInfo("updateSalePromotionForm",goods);
            $("#spStartTime").val(goods.spStartTime);
            $("#spEndTime").val(goods.spEndTime);
            radioChoose("#updateSalePromotionForm .spType",goods.spType);
            $("#updateSalePromotion").modal("show");
        }
        else{
            let goods = $("#goodsClassificationTable").bootstrapTable("getRowByUniqueId",spid);
            setInfo("updateSalePromotionGoodsFrom",goods);
            radioChoose("#updateSalePromotionGoodsFrom .isCoupon",goods.isCoupon);
            radioChoose("#updateSalePromotionGoodsFrom .spRegion",goods.spRegion);
            $("#updateSalePromotionGoods").modal("show");
        }
    }

    /*编辑商品*/
    function updateGoods(number) {
        let fromId = "";
        if(number == 0)
            fromId = "updateSalePromotionForm";
        else
            fromId = "updateSalePromotionGoodsFrom";
        let bootstrapValidator = $("#"+fromId).data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;
        var goods = document.getElementById(fromId);
        var data = new FormData(goods);
        data.append("spId",id);
        data.append("version",version);
        $.ajax({
            url:"/openSalePromotion/updateSalePromotion",
            method:"post",
            data:data,
            processData : false,
            contentType : false,
            success:function (data) {
                refreshTableData();
                $("#updateSalePromotion").modal("hide");
                $("#updateSalePromotionGoods").modal("hide");
                $("#updateSalePromotionForm").data('bootstrapValidator').resetForm(true);
                $("#updateSalePromotionGoodsFrom").data('bootstrapValidator').resetForm(true);
            }
        });
    }
    
    function setInfo(id,goods) {
        $("#"+id).find('[name]').each(function () {
            var name = $(this).attr('name');
            if(name == "spType" || name == "isCoupon" || name == "spRegion"){

            }
            else {
                var type = $(this)[0].nodeName.toLowerCase();
                $(this).val(goods[''+name+'']);
            }
        });
    }

    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }

    function deletePromotion(spId) {
        id = spId;
        $("#deleteSalePromotion").modal("show");
    }

    function deleteSalePromotion() {
        $.ajax({
            url:"/openSalePromotion/removeSalePromotion",
            method:"post",
            dataType:"json",
            data:{
                spId:id
            },
            success:function (data) {
                layer.msg(data.msg);
                $("#deleteSalePromotion").modal("hide");
                refreshTableData();
            }
        });
    }
</script>

<script>
    toastr.options = {

        closeButton: false,
        debug: false,
        progressBar: false,
        positionClass: "toast-bottom-center",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "2000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
</script>

<script>
    function createSalePromotionTable(url,tableId,uniqueId,columns,searchParams){
//表格的初始化
        $('#'+tableId).bootstrapTable({
            url:url,
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: false,                     //是否启用排序
            queryParams: function (params) {
                if(searchParams){
                    var params=searchParams(this);
                    return params;
                }else{
                    return params;
                }
            },                                  //传递参数（*）
            pagination:true,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,

            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: uniqueId,                     //每一行的唯一标识，一般为主键列
            selectItemName: 'parentItem',
            dataType: "json",
            columns: columns,
            contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
        });
    }

</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#saveSalePromotionForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                propotionName: {
                    validators: {
                        notEmpty: {
                            message: '特价名称不能为空'
                        }
                    }
                },
                spOrder: {
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
                spType: {
                    validators: {
                        notEmpty: {
                            message: '促销类型不能为空'
                        }
                    }
                },
                spStartTimes: {
                    validators: {
                        notEmpty: {
                            message: '开始时间不能为空'
                        }
                    }
                },
                spEndTimes: {
                    validators: {
                        notEmpty: {
                            message: '结束时间不能为空'
                        }
                    }
                }
            }
        });

        $('#updateSalePromotionForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                propotionName: {
                    validators: {
                        notEmpty: {
                            message: '特价名称不能为空'
                        }
                    }
                },
                spOrder: {
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
                spStartTimes: {
                    validators: {
                        notEmpty: {
                            message: '开始时间不能为空'
                        }
                    }
                },
                spEndTimes: {
                    validators: {
                        notEmpty: {
                            message: '结束时间不能为空'
                        }
                    }
                }
            }
        });


        $('#addSalePromotionGoods').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                goodsName: {
                    validators: {
                        notEmpty: {
                            message: '商品名称不能为空'
                        }
                    }
                },
                spOrder: {
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
                spPrice: {
                    validators: {
                        notEmpty: {
                            message: '原价不能为空'
                        }
                    }
                },
                spDiscountRate: {
                    validators: {
                        notEmpty: {
                            message: '折扣率不能为空'
                        }
                    }
                },
                spSale: {
                    validators: {
                        notEmpty: {
                            message: '特价不能为空'
                        }
                    }
                }
                ,
                spSoldOut: {
                    validators: {
                        notEmpty: {
                            message: '已售数量不能为空'
                        }
                    }
                },
                spSaleSize: {
                    validators: {
                        notEmpty: {
                            message: '特价数量不能为空'
                        }
                    }
                },
                spBounds: {
                    validators: {
                        notEmpty: {
                            message: '限购量不能为空'
                        }
                    }
                },
                isCoupon: {
                    validators: {
                        notEmpty: {
                            message: '优惠券不能为空'
                        }
                    }
                },
                spRegion: {
                    validators: {
                        notEmpty: {
                            message: '必须选择一个推送对象'
                        }
                    }
                }
            }
        });

        $('#updateSalePromotionGoodsFrom').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                goodsName: {
                    validators: {
                        notEmpty: {
                            message: '商品名称不能为空'
                        }
                    }
                },
                spOrder: {
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
                spPrice: {
                    validators: {
                        notEmpty: {
                            message: '原价不能为空'
                        }
                    }
                },
                spDiscountRate: {
                    validators: {
                        notEmpty: {
                            message: '折扣率不能为空'
                        }
                    }
                },
                spSale: {
                    validators: {
                        notEmpty: {
                            message: '特价不能为空'
                        }
                    }
                }
                ,
                spSoldOut: {
                    validators: {
                        notEmpty: {
                            message: '已售数量不能为空'
                        }
                    }
                },
                spSaleSize: {
                    validators: {
                        notEmpty: {
                            message: '特价数量不能为空'
                        }
                    }
                },
                spBounds: {
                    validators: {
                        notEmpty: {
                            message: '限购量不能为空'
                        }
                    }
                }
            }
        });
    });

</script>

<script type="text/javascript" src="/js/laydate/laydate.js"></script>
<script>
    laydate.render({
        elem: '#startTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#endTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#spStartTime'
        ,type: 'datetime'
    });

    laydate.render({
        elem: '#spEndTime'
        ,type: 'datetime'
    });
</script>

</body>
</html>
