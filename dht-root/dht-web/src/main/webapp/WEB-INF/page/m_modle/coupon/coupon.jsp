<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>领券中心</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/deal-success.css">
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
    <script src="/js/layer_mobile/layer.js"></script>
    <script>
        $("#couponLists").html("");
        function queryCoupon(pageNo,pageSize){
            $.ajax({
                type:"post",
                url:'/coupon/couponList',
                dataType: "json",
                async:false,
                data:{pageNo:pageNo,pageSize:pageSize},
                success:function(data){
                    if(data.status==0){
                        var rows=data.data;
                        if(rows&&rows.length>0){
                            for(var row of rows){
                                showCoupon("#couponLists",row);
                            }
                        }
                        outpageNo++;
                        flag = true;
                    }
                }
            });
        }
        $(function () {
            queryCoupon(1,10);
            scrollloadCoupon();
        });
        var outpageNo=1;
        var outpageSize=10;
        var flag = true;
        <!--滚动加载商品-->
        function scrollloadCoupon() {
            $(document).ready(function(){
                var range = 50;             //距下边界长度/单位px
                var totalheight = 0;
                $(window).scroll(function(){
                    var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)

                    totalheight = parseFloat($(window).height()) + parseFloat(srollPos);

                    if(($(document).height()-range) <= totalheight && flag) {
                        flag = false;
                        queryCoupon(outpageNo,outpageSize);
                    }
                });
            });
        }

        /**
         * 展示优惠卷
         * @param couponId 元素id
         * @param row 行数据
         */
        function showCoupon(couponId,row) {
            let html = '<div class="couponUnused"><div class="couponInfo"><div class="price">';
            if(row.cpCoinType==0){
                if(row.cpType==0){
                    html+='<p>'+row.couponVal+'<span class="flag">￥</span></p>';
                }else {
                    html+='<p>'+row.couponVal+'<span class="flag">折</span></p>';
                }
            }else{
                if(row.cpType==0){
                    html+='<p>'+row.couponVal+'<span class="flag">￥</span></p>';
                }else {
                    html+='<p>'+row.couponVal+'<span class="flag">折</span></p>';
                }
            }

            html += '</div><div class="date"><p class="test">'+row.useCondition+'</p><p>'+row.useTime+'</p>';
            html += '</div><div class="useCoupon"><span onclick="userGrabCoupon('+row.cpId+');return false;">立即使用</span></div></div>';
            html += '<div class="couponType"><p>'+row.useRange+'</p><span class="care_button"></span></div></div>';
            $(couponId).append(html);
        }
        /*function showCoupon(couponId,row) {
            let html='<li><a href="javascript:void(0)"><div class="coupon_item_box clearfix">';
            if(row.cpCoinType==0){
                if(row.cpType==0){
                    html+='<div class="item_box_left">'+row.couponVal+'<span>￥</span></div>';
                }else {
                    html+='<div class="item_box_left">'+row.couponVal+'<span>折</span></div>';
                }
            }else{
                if(row.cpType==0){
                    html+='<div class="item_box_left"><span>拼手气代金</span></div>';
                }else {
                    html+='<div class="item_box_left"><span>拼手气折扣</span></div>';
                }
            }
            html+='<div class="item_box_right"><p class="term">'+row.useCondition+'</p>';
            html+='<p class="time">'+row.useTime+'</p></div></div><p class="p1">'+row.useRange+'</p>';
            html+='<span class="btn_span" onclick="userGrabCoupon('+row.cpId+');return false;">立即领取</span></a></li>';
            $(couponId).append(html);
        }*/
        /**
         * 领取优惠卷
         * @param id 优惠卷id
         */
        function userGrabCoupon(id){
            //loading带文字
            let curIndex = layer.open({
                type: 2,
                shadeClose:false,
                content: '努力抢夺中…………'
            });
            $.ajax({
                type:"post",
                url:'/coupon/userGrabCoupon',
                dataType: "json",
                async:false,
                data:{cpId:id},
                success:function(data){
                    layer.close(curIndex);
                    let msg = data.msg;
                   if(data.status==0){
                       $("#couponLists").html("");
                       outpageNo=1;
                       outpageSize=10;
                       queryCoupon(1,10);
                       msg='领取成功';
                    }
                    //提示
                    layer.open({
                        content: msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                }
            });
        }

        var flag = 1;
        $('#couponLists').on('click', '.care_button', function(){
            if(flag == 2){
                flag = 1;
                $(this).prev("p").css("white-space","nowrap");
                return;
            }
            if(flag == 1){
                flag = 2;
                $(this).prev("p").attr("style","white-space:normal");
                return;
            }
        })
    </script>
</body>
</html>
