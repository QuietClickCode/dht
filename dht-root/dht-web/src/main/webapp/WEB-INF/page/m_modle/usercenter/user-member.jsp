<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的会员</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
	<style type="text/css">
		.vip_banner_box{
			background: url(/img/vip_card_bg.png) no-repeat;
			background-position: right;
			background-size: 100% 4rem;
			position: relative;
		}

		.vip_banner_box > img{
			width: 70%;
			margin-left: 15%;
			padding-top: 5%;
			height: auto;
		}

		.vip_id{
			color: #fff;
			position: absolute;
			top: 1rem;
			left: 50%;
			width: 3rem;
			text-align: center;
			margin-left: -1.5rem;
			font-size: 0.3rem;
		}
	</style>
</head>

<body class="bge6">
    <div class="specialty-title2 borderB">
        <a href="javascript:void(0);" onclick="window.history.back();return false;" class="icon-return"></a>
        <span>我的会员卡</span>
    </div>
	<div class="vip_banner_box">
		<span class="vip_id">ID:<span class="user_id">${uid}</span></span>
		<img class="vip_card" />
	</div>
	
	<ul class="vip_nav1_list clearfix">
		<li class="vip_nav1_item1">
			<a href="/integral.html">
				<img src="/img/icon-vip1.png"/>
				<p class="p1">积分</p>
			</a>			
		</li>
		<li class="vip_nav1_item2">
			<a href="/user/userWallet">
				<img src="/img/icon-vip2.png"/>
				<p class="p1">余额</p>
			</a>			
		</li>
		<li class="vip_nav1_item3">
			<a href="/user/userCoupon">
				<img src="/img/icon-vip3.png"/>
				<p class="p1">优惠券</p>
			</a>			
		</li>
	</ul>
	
	<ul class="vip_nav2_list">
		<li>
			<a href="">
				<div class="vip_nav2_list_L">返现</div>
				<div class="vip_nav2_list_R">查看返现记录</div>
			</a>
		</li>
		<li>
			<a href="">
				<div class="vip_nav2_list_L">权益</div>
				<div class="vip_nav2_list_R">查看会员相关权益</div>
			</a>
		</li>
		<li>
			<a href="">
				<div class="vip_nav2_list_L">消费</div>
				<div class="vip_nav2_list_R">查看消费记录</div>
			</a>
		</li>
		<li>
			<a href="">
				<div class="vip_nav2_list_L">推荐好友</div>
				<div class="vip_nav2_list_R">推荐好友获得积分、优惠券</div>
			</a>
		</li>
	</ul>
    <script src="/js/jquery-1.9.1.min.js"></script>
	<script>
		var rechange;
        $(function () {
			$.ajax({
				url:"/user/queryLoginUser",
				type:"post",
				dataType:"json",
				success:function (data) {
                    rechange = data.user.rechageNm;
                    console.log(rechange);
                    if(rechange == "普通会员"){
                        $(".vip_card").attr("src","/img/p_vip_card.png");
					}else if(rechange == "银卡会员"){
                        $(".vip_card").attr("src","/img/y_vip_card.png");
					}else if(rechange == "黑卡会员"){
                        $(".vip_card").attr("src","/img/h_vip_card.png");
                    }else if(rechange == "钻石会员"){
                        $(".vip_card").attr("src","/img/z_vip_card.png");
                    }else if(rechange == "铜卡会员"){
                        $(".vip_card").attr("src","/img/t_vip_card.png");
                    }else if(rechange == "铂金会员"){
                        $(".vip_card").attr("src","/img/b_vip_card.png");
                    }else if(rechange == "金卡会员"){
                        $(".vip_card").attr("src","/img/g_vip_card.png");
                    }
                }
			});
        });
	</script>
</body>

</html>