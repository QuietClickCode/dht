<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/12/17
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
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
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
    <span>全部订单</span>
</div>

<div class="all-order-tab" id="J_allOrderTab">
    <a href="#all-order" class="active">全部订单</a>
    <a id="obligation" href="#dfk">待付款</a>
    <a id="unsent" href="#dfh">待发货</a>
    <a id="receive" href="#dsh">待收货</a>
    <a id="appraise" href="#dpj">待评价</a>
</div>

<div class="box2">
    <!-- 全部订单 -->
    <ul class="all-order-list box2" id="all-order">
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
        </li>

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
                    <a href="">确认收货</a>
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
    $(function(){
        var href = window.location.hash;
        if (href != "")
            $(href).trigger("click");
    });

</script>
</body>

</html>