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
    <button class="btn btn-primary" type="button" onclick="addSalePromotion()">新增特价名单</button>
    <input type="text" class="form-control" id="hnName" placeholder="请输入导航名称">
    <select id="clientMenu" class="form-control">
        <option value="">状态</option>
        <option value="0">未开始</option>
        <option value="1">进行中</option>
        <option value="2">已结束</option>
    </select>

    <select id="styleMenu" class="form-control">
        <option value="">类型</option>
        <option value="0">特价</option>
        <option value="1">秒杀</option>
    </select>

    <select id="countryMenu" class="form-control">
        <option value="">主推方向</option>
        <option value="0">乡村</option>
        <option value="1">城市</option>
    </select>

    <button class="btn btn-default" onclick="openQueryGoodsModal()">查询</button>
</div>

<div>
    <table id="goodsTypeTables" ></table>
</div>


<%--新增特价名单--%>
<div class="modal fade" id="addSalePromotion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增首页广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveName" id="saveName" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">促销类型</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="0">
                                    特价
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="1">
                                    秒杀
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">推送对象</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="0">
                                    乡村
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="1">
                                    城市
                                </label>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">优惠券</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="0">
                                    不使用优惠券
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="setCountry" class="setCountry" value="1">
                                    使用优惠券
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">原价</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">折扣率</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">特价数量</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">限购量</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">开始时间</label>
                        <div class="col-sm-10">
                            <input type="date"  class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">结束时间</label>
                        <div class="col-sm-10">
                            <input type="date"  class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="saveOrder" id="saveOrder" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="saveAdvertising()">确定</button>
            </div>
        </div>
    </div>
</div>



<%--删除导航模态框--%>
<div class="modal fade" id="queryGoods" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">选择商品</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="请输入商品名称">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>


                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="0">
                                    顶部区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="1">
                                    中间区域
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="region" class="region" value="2">
                                    底部区域
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="deleteNavigation()">确定</button>
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
            field: 'goodsId',
            title: '商品ID',
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
            field: 'spPrice',
            title: '原价',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spSale',
            title: '特价',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spDiscountRate',
            title: '折扣率',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spType',
            title: '促销类型',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spOrder',
            title: '排序',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spSaleSize',
            title: '特价数量',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spSoldOut',
            title: '已售数量',
            align : 'center',
            valign : 'middle'
        }
        ,
        {
            field: 'spStartTime',
            title: '开始时间',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'spEndTime',
            title: '结束时间',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '编辑',
            align : 'center',
            valign : 'middle'
        },
        {
            title: '删除',
            align : 'center',
            valign : 'middle'
        }
    ]


    $(function () {
        createTable("/openSalePromotion/querySalePromotionLists","goodsTypeTables","spId",treeColumns,queryParams)
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
                url:"/openHomeNavigation/queryNavigationLists"
            }
        );
    }
    
    function openQueryGoodsModal() {
        $("#queryGoods").modal("show");
    }

    /*添加首页特价名单*/
    function addSalePromotion() {
        $("#addSalePromotion").modal("show");
    }

</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#addHomeNavigation').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                NavigationName: {
                    validators: {
                        notEmpty: {
                            message: '导航名称不能为空'
                        }
                    }
                },
                hnOrder: {
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
                setStyle: {
                    validators: {
                        notEmpty: {
                            message: '请选择样式'
                        }
                    }
                },
                country: {
                    validators: {
                        notEmpty: {
                            message: '请选择一个推送对象'
                        }
                    }
                },
                client: {
                    validators: {
                        notEmpty: {
                            message: '请选择一个客户端对象'
                        }
                    }
                },
                hnUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                },
                dht_image_upload: {
                    validators: {
                        notEmpty: {
                            message: '文件不能为空'
                        }
                    }
                },
                mainTitle: {
                    validators: {
                        notEmpty: {
                            message: '主标题不能为空'
                        },
                        stringLength:{
                            min:4,
                            message:"主标题至少输入四个字符"
                        }
                    }
                },
                subTitle: {
                    validators: {
                        notEmpty: {
                            message: '副标题不能为空'
                        },
                        stringLength:{
                            min:4,
                            message:"副标题至少输入四个字符"
                        }
                    }
                }
            }
        });


        $('#updateNavigationInfo').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                NaviOrder: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp:{
                            regexp:/\d/,
                            message:"只能输入数字"
                        }
                    }
                },
                NaviName: {
                    validators: {
                        notEmpty: {
                            message: '导航名称不能为空'
                        }
                    }
                },
                imageUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                },
                inputMainTitle: {
                    validators: {
                        notEmpty: {
                            message: '主标题不能为空'
                        },
                        stringLength:{
                            min:4,
                            message:"主标题至少输入四个字符"
                        }
                    }
                },
                inputSubTitle: {
                    validators: {
                        notEmpty: {
                            message: '副标题不能为空'
                        },
                        stringLength:{
                            min:4,
                            message:"副标题至少输入四个字符"
                        }
                    }
                }
            }
        });
    });

</script>
</body>
</html>
