<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/31
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>订单详情</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style type="text/css">
        .obligations-footer a{
            height: auto;
        }

        .price{
            display: block;
            float: right;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>订单详情</span>
</div>

<div class="obligations-start">
    <p class="start-text">等待商家发货</p>
    <p class="time">
        交易成功：2017-10-17
        <i></i>
    </p>
</div>

<div class="obligations-user">
    <p class="user-name">
        收货人:胡月半
        <span class="phone">151****2388</span>
    </p>
    <p class="obligations-address">重庆 重庆市石柱县物流镇明星村</p>
</div>

<div class="obligations-order box2">
    <div class="order-box">
        <a href="" class="img">
            <img src="img/list2.jpg" alt="">
        </a>
        <div class="text-box">
            <a href="">
                <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                <span class="price">￥29.9</span>
            </a>
            <p class="gsName">规格:400g
                <span class="number">×1</span>
            </p>
        </div>
    </div>
    <%--<div class="after-sales">
        <a href="">申请退款</a>
    </div>--%>
</div>

<div class="obligations-total box2">
    <p class="total-number1">
        应付总额
        <span class="number1">￥150.22</span>
    </p>
    <p class="total-number2">
        商品总金额
        <span class="number2">￥150.22</span>
    </p>
    <p class="total-number2">
        运费
        <span class="number2 number3">￥150.22</span>
    </p>
    <p class="total-number2">
        会员折扣
        <span class="number2 number4">￥150.22</span>
    </p>
    <p class="total-number2">
        优惠卷
        <span class="number2 number5">￥150.22</span>
    </p>
    <p class="total-number2">
        以为您节省
        <span class="number2 number6">￥150.22</span>
    </p>
</div>

<div class="obligations-order-number">
    <p class="order_num">订单编号：2017234974325446840</p>
    <p class="order_time">下单时间：2017-10-17 17：35：22</p>
</div>

<div class="obligations-payment">
    <p style="line-height: 1rem;">
        支付方式
        <span class="orderPayUseWay">余额支付</span>
    </p>

</div>

<div class="placeholder-footer2"></div>
<div class="obligations-footer">
    <%--<a href="">查看物流</a>
    <a href="">确认收货</a>--%>
</div>

<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script>
    var order;
    var goods;
    $(function () {
        $.ajax({
            url:"/order/queryOrderInfos",
            dataType:"json",
            type: "post",
            data:{
                orderId:getUrlParam("orderId")
            },
            success:function (data) {
                order = data.data;
                goods = order.ods[0];
                orderStatus();
            }
        });
    });

</script>

<%--订单状态--%>
<script>
    function orderStatus() {
        console.log(order);
        if(order.orderStatus == 0){
            orderStatus_0();
        }else if(order.orderStatus == 3){
            orderStatus_3();
        }else if(order.orderStatus == 4){
            orderStatus_4();
        }else if(order.orderStatus == 9){
            orderStatus_9();
        }else if(order.orderStatus == 6){
            orderStatus_6();
        }
    }

    <%--未支付--%>
    function orderStatus_0() {
        $(".start-text").text("等待用户付款");
        setOrderInfo();
        $(".orderPayUseWay").text("未支付");
        $(".orderPayWay").text("无");
        $(".obligations-footer").append('<a href="/wxPay/payInfo?orderNo='+order.orderNo+'&price='+order.orderTradePrice+'&type='+order.orderType+'&formate=true">去付款</a>');

    }

    <%--待发货--%>
    function orderStatus_3(){
        setOrderInfo();
        $(".obligations-footer").append('<a href="/order/refundType?orderId='+order.id+'">申请售后</a>');
    }

    <%--待收货--%>
    function orderStatus_4(){
        $(".start-text").text("等待用户收货");
        setOrderInfo();
        $(".obligations-footer").append('<a href="/order/refundType?orderId='+order.id+'">申请售后</a>');
    }

    <%--退款中--%>
    function orderStatus_6(){
        $(".start-text").text("退款中");
        setOrderInfo();
//        $(".obligations-footer").append('<a href="/order/refundType?orderId='+order.id+'">申请售后</a>');
        $(".obligations-footer").hide();
    }

    <%--待收货--%>
    function orderStatus_9(){
        $(".start-text").text("交易已完成");
        setOrderInfo();
        $(".obligations-footer").append('<a href="/order/refundType?orderId='+order.id+'">申请售后</a>');
    }
    
    function setOrderInfo() {
        $(".time").text("下单时间："+order.orderCreateDate.substring(0,10));
        $(".time").append("<i></i>");
        $(".user-name").text("收货人："+order.orderBuyNm);
        $(".user-name").append('<span class="phone">'+order.orderUaPhone+'</span>');
        $(".obligations-address").text(order.orderUaAddress);
        $(".img img").attr("src",goods.gImgUrl);
        $(".text").text(goods.gName);
        $(".price").text("￥"+goods.gdPrice);
        $(".gsName").text("规格:"+goods.gsName);
        $(".gsName").append('<span class="number">'+'x'+goods.odBuyNumber+'</span>');
        $(".number1").text("￥"+Number(order.orderTradePrice).toFixed(2));
        $(".number2").text("￥"+Number(order.orderGoodsTotalPrice).toFixed(2));
        $(".number3").text("￥"+Number(order.orderLogisticsPrice).toFixed(2));
        $(".number4").text("￥-"+Number(order.orderDiscount).toFixed(2));
        $(".number5").text("￥-"+Number(order.orderCouponPrice).toFixed(2));
        let num = Number(order.orderDiscount) + Number(order.orderCouponPrice);
        $(".number6").text("￥"+num)
        $(".order_num").text("订单编号："+order.orderNo);
        $(".order_time").text("下单时间："+order.orderCreateDate);
        if(order.orderPayWay == 0){
            $(".orderPayUseWay").text("微信");
        }else if(order.orderPayWay == 1){
            $(".orderPayUseWay").text("支付宝");
        }else if(order.orderPayWay == 2){
            $(".orderPayUseWay").text("用户钱包");
        }
    }
</script>

<%--获取订单ID--%>
<script>
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
</script>
</body>

</html>