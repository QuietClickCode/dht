<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>会员中心</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        #footer_user a .icon-user{
            background-size: 100%;
        }

        .user-icon-list7{
            background-size: .36rem .34rem;
        }

        .login_box{
            display: block;
            width: 2rem;
            height: 0.7rem;
            background: #e43f46;
            border-radius: 3px;
            text-align: center;
            line-height: 0.7rem;
            font-size: .26rem;
            color: grey;
            margin: 0 auto;
            margin-top: 1rem;
        }

        .login_btn{
            color: #fff;
            font-size: 0.2rem;
        }
    </style>
</head>
<body class="bge6">
    <div class="box2">
        <div class="user-header" >
            <a href="/user/userDetailInfo" class="icon-change">
                <div class="user-img">
                    <img src="/img/user-header.png" alt=""/>
                </div>
            </a>
            <p class="user-name">56dfsdf</p>
            <div class="vip">

                <span>普通会员</span>
            </div>
            <i class="news">
                <span></span>
            </i>
        </div>

        <div class="user-order">
            <div class="title">
                <i class="icon-order"></i>
                <span>我的订单</span>
            </div>
            <a href="/order/orderList">全部订单></a>
        </div>

        <div class="user-order-state">
            <div class="state">
                <a href="/order/orderList?id=#obligation">
                    <i class="icon-state1">
                        <span>1</span>
                    </i>
                    <p>未付款</p>
                </a>
            </div>
            <div class="state">
                <a href="/order/orderList?id=#unsent">
                    <i class="icon-state2">
                        <span>1</span>
                    </i>
                    <p>待处理</p>
                </a>
            </div>
            <div class="state">
                <a href="/order/orderList?id=#appraise">
                    <i class="icon-state3">
                        <span>1</span>
                    </i>
                    <p>可评价</p>
                </a>
            </div>
        </div>

        <div class="user-order">
            <div class="title">
                <i class="icon-care"></i>
                <span>购物车</span>
            </div>
            <a href="/buyCar/gotoShoppingCar">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list2"></i>
                <span>足迹</span>
            </div>
            <a href="/userFootprint/toUserFootprintpage">
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list3"></i>
                <span>余额</span>
            </div>
            <a href="/user/userWallet">
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list4"></i>
                <span>会员卡</span>
            </div>
            <a href="/user/userMember">
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list5"></i>
                <span>优惠券</span>
            </div>
            <a href="/user/userCoupon">
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list6"></i>
                <span>返现</span>
            </div>
            <a href="">
                <span class="prompt"></span>
            </a>
        </div>
        <div class="user-order">
            <div class="title">
                <i class="user-icon-list7"></i>
                <span>售后服务</span>
            </div>
            <a href="">
                <span class="prompt"></span>
            </a>
        </div>

        <%--<div class="user-order">
            <div class="title">
                <i class="user-icon-list7"></i>
                <span>消息中心</span>
            </div>
            <a href="">有12件商品在等你噢
                <span class="prompt"></span>
            </a>
        </div>--%>

    </div>
    <!-- 底部 -->
    <div class="placeholder-footer"></div>
    <div id="footer"></div>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/footer.js"></script>
    <script>
        var curFooter="#footer_user";
    </script>

    <script type="text/javascript">
        //给功能菜单添加点击事件
        $(".user-order").click(function(){
            let index = $(this).index(".user-order");
            if(index != 0){
                let link = $(".user-order").eq(index).find("a").attr("href");
                location.href = link;
            }
        });


        $(function () {
            $.ajax({
                url:"/user/queryLoginUser",
                method:"post",
                dataType:"json",
                success:function (data) {
                    if(data.status == 3){
                        $(".user-header > *").remove();
                        var login_box = $('<a src="/loginPage" class="login_box"></a>');
                        var login_btn = $('<span class="login_btn">立即登录</span>');
                        login_box.append(login_btn);
                        $(".user-header").append(login_box);
                    }
                    if(data.user != undefined){
                        $(".user-name").text(data.user.uname);
                        $(".user-img img").attr("src","http://image.kuaiyis.com/attachment/"+data.user.headUrl);
                    }
                }
            });
        });

        $(".user-header").on("click",".login_box",function () {
            $(location).attr('href', '/loginPage?redirectUrl=%2Fuser%2FuserCenter');
        });
    </script>
</body>

</html>