<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>优惠卷</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bge6">
    <div class="specialty-title2 borderB">
        <a class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;" ></a>
        <span>优惠卷</span>
    </div>

    <div class="coupon-tab" id="J_couponTab">
        <a href="#wsy" class="active">
            <p>未使用</p>
            <p>(6)</p>
        </a>
        <a href="#ysy">
            <p>已使用</p>
            <p>(6)</p>
        </a>
        <a href="#ysx">
            <p>已失效</p>
            <p>(6)</p>
        </a>
    </div>

    <div class="coupon-cont box2">
        <div class="coupon-box" id="wsy">
            <a href="/coupon/openCoupon">
                <div class="more-coupon">
                    <span class="icon-coupon"></span>
                    <span class="text">领取更多优惠券</span>
                    <i class="icon-coupon-right"></i>
                </div>
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon2.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon2.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon2.png" alt="">
            </a>
        </div>

        <div class="coupon-box displayN" id="ysy">
            <a href="" class="coupon-img">
                <img src="/img/coupon3.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon3.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon3.png" alt="">
            </a>
        </div>

        <div class="coupon-box displayN" id="ysx">
            <a href="" class="coupon-img">
                <img src="/img/coupon4.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon4.png" alt="">
            </a>
            <a href="" class="coupon-img">
                <img src="/img/coupon4.png" alt="">
            </a>
        </div>

    </div>

    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/tabs.js"></script>
</body>

</html>