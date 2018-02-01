
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
    <link rel="stylesheet" type="text/css" href="/css/refund.css">
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>申请退款</span>
</div>
<div class="refund">
    <div class="refund_goods_info">
        <div class="refund_goods_img">
            <img src="img/goods_img.png">
        </div>

        <div class="_goods_info">
            <span class="_goods_details">乐事多力多紫薯片多口味零食大礼盒400克</span>
            <span class="goods_spci">规格:<span class="_goods_weight">400g</span></span>
        </div>
    </div>

    <div class="_refund_info">
        <div class="_refund_left">
            <span>仅退款</span>
            <span>未收到货(包含未签收),或卖家协商同意前提下</span>
        </div>

        <div class="_refund_right">
            <span></span>
        </div>
    </div>
</div>

<%--<div class="_sub">
    <span>提交</span>
</div>--%>
<script src="/js/jquery-1.9.1.min.js"></script>
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
                console.log(order);
                $(".refund_goods_img img").attr("src",goods.gImgUrl);
                $("._goods_details").text(goods.gName);
                $("._goods_weight").text(goods.gsName);
            }
        });
    });

    $("._refund_info").click(function () {
        window.location.href = "/order/refundStatus?orderId="+order.id+"";
    });
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