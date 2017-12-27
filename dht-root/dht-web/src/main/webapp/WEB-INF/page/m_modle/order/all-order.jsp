<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>全部订单</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .price{
            float: right;
        }
    </style>
</head>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
    <span>全部订单</span>
</div>

<div class="all-order-tab" id="J_allOrderTab">
    <a href="#allOrder" id="allOrders" class="active">全部订单</a>
    <a id="obligation" href="#dfk">待付款</a>
    <a id="unsent" href="#dfh">待发货</a>
    <a id="receive" href="#dsh">待收货</a>
    <a id="appraise" href="#dpj">待评价</a>
</div>

<div class="box2">
    <!-- 全部订单 -->
    <ul class="all-order-list box2" id="allOrder">
        <%--<li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待付款</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">取消订单</a>
                    <a href="" class="btn2">付款</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">评价</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待收货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">物流详情</a>
                    <a href="">查看订单</a>
                    <a href="">确认收货</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待发货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">提醒发货</a>
                </div>
            </div>
        </li>--%>

    </ul>

    <!-- 待付款 -->
    <ul class="all-order-list displayN" id="dfk">
        <li class="box2">
            <p class="start">待付款</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">取消订单</a>
                    <a href="" class="btn2">付款</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待发货 -->
    <ul class="all-order-list displayN" id="dfh">
        <li class="box2">
            <p class="start">待发货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">提醒发货</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待收货 -->
    <ul class="all-order-list displayN" id="dsh">
        <li class="box2">
            <p class="start">待收货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="/order/checkLogistics">物流详情</a>
                    <a href="">查看订单</a>
                    <a href="/order/dealSuccess">确认收货</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待评价 -->
    <ul class="all-order-list displayN" id="dpj">
        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="/order/checkAppraise" class="btn2">评价</a>
                </div>
            </div>
        </li>
    </ul>

</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>

<script>
    //当前页数据
    var curPage=1;
    $(function(){
        var href = window.location.hash;
        console.log(href)
        if (href != "")
            $(href).click();
        queryOrderByStatus("",100);
    });

    $("#obligation").click(function () {
        $("#dfk").html("");
        queryOrderByStatus(0,0);
    });

    $("#allOrders").click(function () {
        $("#allOrder").html("");
        queryOrderByStatus("",100);
    });

    $("#unsent").click(function () {
        $("#dfh").html("");
        queryOrderByStatus(3,3);
    });

    $("#receive").click(function () {
        $("#dsh").html("");
        queryOrderByStatus(4,4);
    });

    $("#appraise").click(function () {
        $("#dpj").html("");
        queryOrderByStatus(9,9);
    });
    /**
     * 根据订单类型取得订单列表数据
     * @param orderStatus
     */
    function queryOrderByStatus(orderStatus,num){
        var queryParams={
            pageSize: 15,
            pageNo: curPage,
            orderStatus:orderStatus
        }
        $.ajax({
            url: "/order/queryUserOrder",
            type: "post",
            dataType: "json",
            data:queryParams,
            success: function (data) {
                orderView(data.rows,num);
            }
        });
    }

    /**
     * 根据查询结构显示订单数据
     * @param row
     */
    function orderView(rows,num){
        if(rows){
            for(var row of rows){
                let osMsg = '未支付';
                if(row.orderStatus==1){
                    osMsg= "支付中";
                }else if(row.orderStatus==2){
                    osMsg='支付失败';
                }else if(row.orderStatus==3){
                    osMsg='待发货';
                }else if(row.orderStatus==4){
                    osMsg='己发货';
                }else if(row.orderStatus==5){
                    osMsg='确认收货';
                }else if(row.orderStatus==6){
                    osMsg='发起退款';
                }else if(row.orderStatus==9){
                    osMsg='交易完成';
                }

                var ov='<li class="box2"><p class="start">'+row.orderNo+'&nbsp;&nbsp;'+osMsg+'</p>';
                //购买总件数
                let buyTotalNm=0;
                if(row.ods){
                    let orderInfo=row.ods;
                    for(var info of orderInfo){
                        ov+='<div class="order-infor"><a href="javascript:void(0)" class="img">';
                        ov+='<img src="'+info.imgUrl+'" alt=""></a><div class="text-box">';
                        ov+='<a href=""> <span class="text">'+info.gName+'</span><span class="price">￥'+info.gdPrice+'</span>';
                        ov+='</a><p>规格:'+info.gsName+'<span class="number">×'+info.odBuyNumber+'</span></p></div></div>';
                        buyTotalNm+=info.odBuyNumber;
                    }
                }

                if(num == 100){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderGoodsTotalPrice+'(含运费:'+row.orderLogisticsCode+')<div class="btn-box"><a href="">查看订单</a></div></div></li>';
                    $("#allOrder").append(ov);
                }else if(num == 0){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderGoodsTotalPrice+'(含运费:'+row.orderLogisticsCode+')<div class="btn-box"><a href="">取消订单</a><a href="" class="btn2">付款</a></div></div></li>';
                    $("#dfk").append(ov);
                }else if(num == 3){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderGoodsTotalPrice+'(含运费:'+row.orderLogisticsCode+')<div class="btn-box"><a href="">查看订单</a><a href="" class="btn2">提醒发货</a></div></div></li>';
                    $("#dfh").append(ov);
                }else if(num == 4){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderGoodsTotalPrice+'(含运费:'+row.orderLogisticsCode+')<div class="btn-box"><a href="/order/checkLogistics">物流详情</a><a href="">查看订单</a><a href="/order/dealSuccess">确认收货</a></div></div></li>';
                    $("#dsh").append(ov);
                }else if(num == 9){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderGoodsTotalPrice+'(含运费:'+row.orderLogisticsCode+')<div class="btn-box"><a href="">查看订单</a><a href="/order/checkAppraise" class="btn2">评价</a></div></div></li>';
                    $("#dpj").append(ov);
                }
            }
        }
    }
</script>
</body>

</html>