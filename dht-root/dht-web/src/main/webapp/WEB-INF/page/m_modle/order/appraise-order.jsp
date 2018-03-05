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
    <div class="order_infor" id="info">
        <%--<a href="" class="img">--%>
            <%--<img src="/img/list2.jpg" alt="">--%>
        <%--</a>--%>
        <%--<div class="text_box">--%>
            <%--<a href="">--%>
                <%--<span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>--%>
                <%--<span class="price">￥29.9</span>--%>
            <%--</a>--%>
            <%--<p>规格:400g--%>
                <%--<span class="number">×1</span><i class="fa fa-check-square"></i>List icons--%>
            <%--</p>--%>
        <%--</div>--%>
    </div>

    <input id="input-21b" value="4" type="number" class="rating" min=0 max=5 step=1 data-size="xs">

    <div class="comment_btn_box clearfix" id="tag">
        <%--<span class="comment_checkbox">包装好</span>--%>
        <%--<span class="comment_checkbox">物流快</span>--%>
        <%--<span class="comment_checkbox">商品新鲜</span>--%>
        <%--<span class="comment_checkbox">价格划算</span>--%>
        <%--<span class="comment_checkbox">服务态度好</span>--%>
    </div>
</div>

<div class="pay_footer_box">
    <a href="javascript:void(0);" class="pay_footer_btn" onclick="submit();" style="color: #fff;">发表评论</a>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/TouchSlide.1.1.js"></script>
<script src="/js/tabs.js"></script>
<script src="/js/layer/layer.js"></script>
<script src="/js/public.js"></script>
<script>

    <%--var flag1 = <%=session.getAttribute("flag")%>;--%>
    var gid = <%=session.getAttribute("gid")%>;
    var uid = <%=session.getAttribute("uid")%>;
    var orderNo = <%=session.getAttribute("orderNo")%>;

    var map1 = new Map();
    var map2 = new Map();
    var map3 = new Map();
    var map4 = new Map();
    var map5 = new Map();




    // 加载评论标签
    $.ajax({
        url:"/comment/queryGclassGoodsGgclrelLists",
        type:"post",
        dataType: "json",
        data:{
            gid:gid
        },
        success:function (data) {
            // var datass = data.rows;
            $(data.rows).each(function (index, element) {
                // element: 指向当前元素的值
                // index: 指向当前索引
                // array: 指向Array对象本身
                map1.set(element.start1id,element.start1name);
                map2.set(element.start2id,element.start2name);
                map3.set(element.start3id,element.start3name);
                map4.set(element.start4id,element.start4name);
                map5.set(element.start5id,element.start5name);
            });
            changeTag(4);
        }
    });
    $('.comment_layout').on('click', '.rating-container', function(){
        changeTag($("#input-21b").val());
    });
    // 加载商品信息
    $.ajax({
        url:"/comment/queryGoods",
        type:"post",
        dataType: "json",
        data:{
            gid:gid
        },
        success:function (data) {
            // var datass = data.rows;
            $(data).each(function (index, element) {
                $("#info").html('');
                var html = '<a href="/goods/'+element.gid+'.html" class="img">\n' +
                    '            <img src="'+element.imgUrl+'" alt="">\n' +
                    '        </a>\n' +
                    '        <div class="text_box">\n' +
                    '            <a href="">\n' +
                    '                <span class="text">'+element.gname+'</span>\n' +
                    '                <span class="price">'+parseFloat(element.minprice/100).toFixed(2)+'元</span>\n' +
                    '            </a>\n' +
                    '            <p>规格：'+element.gunitname+'\n' +
                    '                <span class="number"></span><i class="fa fa-check-square"></i>\n' +
                    '            </p>\n' +
                    '        </div>';
                $(html).appendTo("#info");

            });

        }
    });

    // 提交评价
    function submit() {
        var arr = [];
        var i = 0;
        $(".comment_checkbox").each(function(){
            arr[i] = $(this).attr("value");
            i++;
        });
        var json = JSON.stringify(arr);
        $.ajax({
            url:"/comment/addComment",
            type:"post",
            dataType: "json",
            data:{
                gid:gid,
                uid:uid,
                orderNo:orderNo,
                gclIdStr:json
            },
            success:function (data) {
                // var datass = data.rows;
                // alert("添加评论成功");
                layer.msg('评价商品成功');
                // window.location.href="/order/orderList?id=#appraise";
                // window.redirect("/order/orderList?id=#appraise");
                window.history.back(-1);
            }
        });
    }

    // $("#input-21b").onclick(function () {
    //     alert($("#input-21b").val());
    // })


    function changeTag(star) {
        $("#tag").html('');
        var html = '';
        if (star == 1) {
            map1.forEach(function (value, key, map) {
                html += '<span class="comment_checkbox" onclick="clinkTag(this);" value="'+key+'">'+value+'</span>';
            });
        } else if (star == 2) {
            map2.forEach(function (value, key, map) {
                html += '<span class="comment_checkbox" onclick="clinkTag(this);" value="'+key+'">'+value+'</span>';
            });
        } else if (star == 3) {
            map3.forEach(function (value, key, map) {
                html += '<span class="comment_checkbox" onclick="clinkTag(this);" value="'+key+'">'+value+'</span>';
            });
        } else if (star == 4) {
            map4.forEach(function (value, key, map) {
                html += '<span class="comment_checkbox" onclick="clinkTag(this);" value="'+key+'">'+value+'</span>';
            });
        } else if (star == 5) {
            map5.forEach(function (value, key, map) {
                html += '<span class="comment_checkbox" onclick="clinkTag(this);" value="'+key+'">'+value+'</span>';
            });
        }
        $(html).appendTo("#tag");
    }


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
        });
        // 已评价返回页面
        // if (flag1=="yes") {
        //     alert("11");layer.msg('已评价过商品');
        // }
        $.ajax({
            url:"/comment/haveComment",
            type:"post",
            dataType: "json",
            success:function (data) {
                // var datass = data.rows;
                // alert("添加评论成功");
                // alert(data.toString());
                if (data.toString() == 'have') {
                    layer.msg('已评价过商品');
                    setTimeout(function(){
                        // window.redirect("/order/orderList?id=#appraise");
                        // window.location.href="/order/orderList?id=#appraise";
                        window.history.back(-1);
                    }, 1000);
                }
            }
        });
    })

    function clinkTag(obj){
        // $(".comment_btn_box .comment_checkbox").removeClass("active")
        if ($(obj).hasClass("active")) {
            $(obj).removeClass("active")
        } else {
            $(obj).addClass("active")
        }
    }


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
