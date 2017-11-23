<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
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
    <a href="" class="icon-return"></a>
    <span>优惠卷</span>
</div>

<div class="coupon-tab" id="J_couponTab">
    <a href="#wsy" class="active">
        <p>未使用</p>
        <p class="couponUnusedSize"></p>
    </a>
    <a href="#ysy">
        <p>已使用</p>
        <p class="couponUsedSize"></p>
    </a>
    <a href="#ysx">
        <p>已失效</p>
        <p class="couponLostSize"></p>
    </a>
</div>

<div class="coupon-cont box2">

    <div class="coupon-box" id="wsy">
        <div class="more-coupon">
            <a href="">
                <span class="icon-coupon"></span>
                <span class="text">领取更多优惠券</span>
                <i class="icon-coupon-right"></i>
            </a>
        </div>
        <ul class="coupon_list1 couponUnused">

        </ul>
    </div>

    <div class="coupon-box displayN" id="ysy">
        <ul class="coupon_list2 couponUsed">

        </ul>
    </div>

    <div class="coupon-box displayN" id="ysx">
        <ul class="coupon_list2 couponLost">
        </ul>
    </div>

</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>
<script>
    $(function(){
        $(".img_open").click(function(){
            var src = $(this).attr("src");
            if(src=="img/icon_down.png"){
                $(this).attr("src","img/icon_up.png");
                $(this).parent().css({"height":"0.6rem","white-space":"normal"})
            }
            if(src=="img/icon_up.png"){
                $(this).attr("src","img/icon_down.png");
                $(this).parent().css({"height":"auto","white-space":"normal"})
            }
        })
        createCouponList(0,".couponUnused",".couponUnusedSize");
        createCouponList(1, ".couponUsed", ".couponUsedSize");
        createCouponList(2, ".couponLost", ".couponLostSize");
    })
    
    
    function createCouponList(type,couponList,couponNum) {
        $.ajax({
            type:"post",
            url:"/coupon/queryUserCoupon",
            dataType:"json",
            data:{
                type:type,
                pageSize:10,
                pageNo:1
            },
            success:function (data) {
                $(couponNum).text("("+data.rows.length+")");
                for(let i = 0;i<data.rows.length;i++){
                    var html = '<li><a href="javascript:void(0)"> <div class="coupon_item_box clearfix"> <div class="item_box_left"> <span>￥</span>';
                    html+='<strong class="condition">'+data.rows[i].couponVal+'</strong></div>';
                    html += '<div class="item_box_right"><p class="term">'+data.rows[i].useCondition+'</p>';
                    html += '<p class="time">'+data.rows[i].cpSendStartDate+'</p></div></div>';
                    html += '<p class="coupon_list1_bg"></p><span class="btn_span">立即使用</span>';
                    html += '<p class="coupon_list1_info"><img class="img_open" src="/img/icon_up.png" />';
                    html += ''+data.rows[i].cpIsRestricted+'</p></a></li>';
                    $(couponList).append(html);
                }
            }
        });
    }
</script>
</body>

</html>