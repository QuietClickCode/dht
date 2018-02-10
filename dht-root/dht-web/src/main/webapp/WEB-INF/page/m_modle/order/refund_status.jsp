<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/31
  Time: 22:09
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
    <link rel="stylesheet" type="text/css" href="/css/refund.css">
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>申请退款</span>
</div>
<div class="refund">
    <div class="_goods_list">

    </div>

   <%-- <div class="_goods_status">
        <span>货物状态</span>
        <span class="_choose_goods_status">请选择</span>
    </div>--%>

    <div class="_amount">
        <span>退款金额：<span class="_goods_price">￥21.8</span></span>
    </div>

    <div class="_cause">
        <span>退款原因</span>
        <i></i>
    </div>
    <textarea class="_cause_text" placeholder="请输入退款原因"></textarea>
</div>

<div class="shade" style="display: none;">
    <div class="shade_box">
        <div class="shade_title">
            <span>货物状态</span>
        </div>

        <div class="_goods_status_">
            <span>未收到货</span>
            <span class=""></span>
        </div>

        <div class="_goods_status_">
            <span>已收到货</span>
            <span class=""></span>
        </div>
    </div>
</div>

<div class="_sub">
    <span>提交</span>
</div>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/layer_mobile/layer.js"></script>
<script type="text/javascript">
    $("._choose_goods_status").click(function(){
        $(".shade").show();
    });

    $("._goods_status_").click(function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
        $(".shade").hide();
    });
</script>

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
                goods = order.ods;
                for(let i = 0;i<goods.length;i++){
                    let imgUrl = goods[i].gImgUrl == '' ? goods[i].imgUrl : goods[i].gImgUrl;
                    let html = '<div class="refund_goods_info"><div class="refund_goods_img"><img src="'+imgUrl+'">';
                    html += '</div><div class="_goods_info"><span class="_goods_details">'+goods[i].gName+'</span>';
                    html += '<span class="goods_spci">规格:<span class="_goods_weight">'+goods[i].gsName+'</span></span></div></div>';
                    $("._goods_list").append(html);
                }
                $("._goods_price").text("￥"+order.orderTradePrice);
            }
        });
    });

    $("._refund_info").click(function () {
        window.location.href = "/order/refundStatus?orderId="+order.id+"";
    });
</script>

<script>
    $("._sub").click(function () {
        if($("._cause_text").val() == ''){
            layer.open({
                content: '退款原因不能为空'
                ,skin: 'msg'
                ,time: 1
            });
            return;
        }
        $.ajax({
            url:"/refund/createRefund",
            type:"post",
            dataType:"json",
            data:{
                orderId:order.id,
                remark:$("._cause_text").val()
            },
            success:function(data){
                if(data.status == 0){
                    layer.open({
                        content: "申请退款成功"
                        ,skin: 'msg'
                        ,time: 1
                    });

                    window.location.href = "/order/orderList?id=#refund";
                }else{
                    layer.open({
                        content: "申请退款失败"
                        ,skin: 'msg'
                        ,time: 1
                    });
                }
            }
        });
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