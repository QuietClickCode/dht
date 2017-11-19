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
            <div class="more-coupon">
                <a href="/coupon/openCoupon">
                    <span class="icon-coupon"></span>
                    <span class="text">领取更多优惠券</span>
                    <i class="icon-coupon-right"></i>
                </a>
            </div>
            <ul class="coupon_list1">
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <span class="btn_span">立即使用</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <span class="btn_span">立即使用</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="coupon-box displayN" id="ysy">
            <ul class="coupon_list2">
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <span class="btn_span">立即使用</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <span class="btn_span">立即使用</span>
                    </a>
                </li>
            </ul>
        </div>

        <div class="coupon-box displayN" id="ysx">
            <ul class="coupon_list2">
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <!--<span class="btn_span">立即使用</span>-->
                    </a>
                </li>
                <li>
                    <a href="">
                        <div class="coupon_item_box clearfix">
                            <div class="item_box_left">
                                <span>￥</span>200
                            </div>
                            <div class="item_box_right">
                                <p class="term">满200可用</p>
                                <p class="time">2017-11-11</p>
                            </div>
                        </div>
                        <p class="p1">服装类商品通用</p>
                        <!--<span class="btn_span">立即使用</span>-->
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/tabs.js"></script>
</body>
</html>
