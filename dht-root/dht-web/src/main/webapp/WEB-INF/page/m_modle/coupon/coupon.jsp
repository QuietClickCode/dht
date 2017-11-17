<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>领券中心</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bge6">
    <div class="specialty-title2 borderB">
        <a class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;" ></a>
        <span>领券中心</span>
    </div>

    <div class="coupon-box">
        <ul class="coupon_list1" id="couponLists">
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
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/coupon/coupon.js"></script>
    <script>
        function queryCoupon(pageNo,pageSize){
            $("#couponLists").html("");
            $.ajax({
                type:"post",
                url:'/coupon/couponList',
                dataType: "json",
                data:{pageNo:pageNo,pageSize:pageSize},
                success:function(data){
                    if(data.status==0){
                        var rows=data.data;
                        if(rows&&rows.length>0){
                            for(var row of rows){
                                showCoupon("#couponLists",row);
                            }
                        }
                    }
                }
            });
        }
        $(function () {
            queryCoupon(1,10);
        })
    </script>

</body>
</html>
