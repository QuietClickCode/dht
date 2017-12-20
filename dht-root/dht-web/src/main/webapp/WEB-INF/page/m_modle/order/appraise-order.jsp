<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/12/20
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>商品评价</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">

    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/1.10.2.jquery.min.js"></script>
    <link href="/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="/js/star-rating.js" type="text/javascript"></script>
</head>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back();" class="icon-return"></a>
    <span>商品评价</span>
</div>

<div class="comment_layout">
    <div class="order_infor">
        <a href="" class="img">
            <img src="/img/list2.jpg" alt="">
        </a>
        <div class="text_box">
            <a href="">
                <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                <span class="price">￥29.9</span>
            </a>
            <p>规格:400g
                <span class="number">×1</span><i class="fa fa-check-square"></i>List icons
            </p>
        </div>
    </div>

    <input id="input-21b" value="4" type="number" class="rating" min=0 max=5 step=1 data-size="xs">

    <div class="comment_btn_box clearfix">
        <span class="comment_checkbox active">包装好</span>
        <span class="comment_checkbox">物流快</span>
        <span class="comment_checkbox">商品新鲜</span>
        <span class="comment_checkbox">价格划算</span>
        <span class="comment_checkbox">服务态度好</span>
    </div>
</div>

<div class="pay_footer_box">
    <a href="" class="pay_footer_btn" style="color: #fff;">发表评论</a>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/TouchSlide.1.1.js"></script>
<script src="/js/tabs.js"></script>
<script src="/js/public.js"></script>
<script>
    TouchSlide({
        slideCell: "#banner",
        titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell: ".bd ul",
        effect: "left",
        autoPlay: true, //自动播放
        autoPage: true, //自动分页
        switchLoad: "_src" //切换加载，真实图片路径为"_src"
    });

    $(function(){
        $(".pay_mode_list li").click(function(){
            $(".pay_mode_list li").removeClass("active")
            $(this).addClass("active")
        })
    })

    $(".comment_btn_box .comment_checkbox").click(function(){
        $(".comment_btn_box .comment_checkbox").removeClass("active")
        $(this).addClass("active")
    })
</script>
<script>
    jQuery(document).ready(function () {
        $("#input-21b").rating({
            'size':' xl',
            'showClear':false
        });
    });
</script>

</body>

</html>
