<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>确认订单</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/new.css">
</head>
<body class="bge6">
<div class="box">
    <div class="specialty-title2 borderB">
        <a href="javascript:;" class="icon-return" id="go_live"></a>
        <span>确认订单</span>
    </div>

    <div class="address" id="address">
        <input type="hidden" value="" id="uaId">
        <a href="/order/choseAddress">
            <p>
                <span id="uaName">胡月半</span>
                <span id="uaPhone">151****2388</span>
            </p>
            <p id="uaAllAddress">
                重庆重庆市石柱县物流镇明星村
            </p>
            <i class="icon-right"></i>
        </a>
        <img id="ordernoaddressimg" style="display: none;width: 100%;height: 100%" src="/img/ordernoaddress.jpg">
    </div>

    <div id="goodsList">
        <div class="order-product">
            <a href="" class="img">
                <img src="/img/order1.png" alt="">
            </a>
            <div class="text-box">
                <a href="" class="title">荣泰（ROTAI）按摩椅 RT6616 多功能太零重力 足底滚轮指压 支持腰部热敷 专用皮质...。。。。。。家用全自动按摩椅 香槟色</a>
                <p class="norms">土豪金;168cm</p>
                <div class="price-box">
                    <span class="price">￥165</span>
                    <div class="number-box">
                        <span class="buy-num">数量：×7</span>
                    </div>
                </div>
                <div class="coupon-info-box">
                    <span class="coupon-btn">优惠券</span>
                    <ul class="coupon-info-wrap">
                        <li>
                            <span>会员折扣</span>
                            <span>￥ -20.00</span>
                        </li>
                        <li>
                            <span>会员折扣</span>
                            <span>￥ -20.00</span>
                        </li>
                        <li>
                            <span>会员折扣</span>
                            <span>￥ -20.00</span>
                        </li>
                        <li>
                            <span>会员折扣</span>
                            <span>￥ -20.00</span>
                        </li>
                        <li>
                            <span>会员折扣</span>
                            <span>￥ -20.00</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>


    <%--<div class="order-payment">--%>
        <%--<span class="name">支付配送</span>--%>
        <%--<div class="text">--%>
            <%--普通快递--%>
            <%--<br /> 在线支付--%>
        <%--</div>--%>
        <%--<i class="icon-right"></i>--%>
    <%--</div>--%>

    <%--<div class="order-payment">--%>
        <%--<span class="name">发票信息</span>--%>
        <%--<div class="text2">--%>
            <%--（纸质）个人发票--%>
        <%--</div>--%>
        <%--<i class="icon-right"></i>--%>
    <%--</div>--%>

    <div class="order-discount">
        <div class="list">
            <span class="name">优惠详情</span>
        </div>
        <div class="list">
            <span class="name">会员折扣</span>
            <span class="text">5.8折</span>
        </div>
        <div class="list coupon-wrap">
            <a href="javascript: ;">
                <span class="name">优惠券</span>
                <!--<span class="text2">请选择</span>-->
                <!--<i class="icon-right coupon-arrows"></i>-->
            </a>

            <ul class="coupon-list">
                <%--<li>--%>
                    <%--<div class="coupon-item-tittle no-selected">--%>
                        <%--<input disabled="disabled" name="Fruit" type="checkbox" value="苹果" />--%>
                        <%--<label for="">苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果</label>--%>
                        <%--<a class="coupon-detail"></a>--%>
                    <%--</div>--%>
                    <%--<div class="coupon-item-detail" style="display: none;">--%>
                        <%--<img class="get-img" src="/img/coupon-img1.png">--%>
                        <%--<div class="coupon-data">--%>
                            <%--<p class="coupon-price">--%>
                                <%--<small>￥</small>--%>
                                <%--<strong>150</strong>--%>
                            <%--</p>--%>
                            <%--<p class="coupon-condition">满 200 可用</p>--%>
                            <%--<p class="coupon-date">2017.11.11-2017.12.12</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</li>--%>

                <%--<li>--%>
                    <%--<div class="coupon-item-tittle">--%>
                        <%--<input name="Fruit" type="checkbox" value="苹果" />--%>
                        <%--<label for="">苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果苹果</label>--%>
                        <%--<a class="coupon-detail"></a>--%>
                    <%--</div>--%>
                    <%--<div class="coupon-item-detail" style="display: none;">--%>
                        <%--<img class="get-img" src="/img/coupon-img1.png">--%>
                        <%--<div class="coupon-data">--%>
                            <%--<p class="coupon-price">--%>
                                <%--<small>￥</small>--%>
                                <%--<strong>150</strong>--%>
                            <%--</p>--%>
                            <%--<p class="coupon-condition">满 200 可用</p>--%>
                            <%--<p class="coupon-date">2017.11.11-2017.12.12</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>

    <div class="order-price">
        <div class="price1">
            <span class="name">应付总额</span>
            <span class="number"><span class="mr_4">￥</span>168.00</span>
        </div>
        <div class="price2">
            <span class="name">商品总金额</span>
            <span class="number"><span class="mr_4">￥</span>168.00</span>
        </div>
        <div class="price2">
            <span class="name">运费</span>
            <span class="number"><span class="mr_4">￥</span>168.00</span>
        </div>
        <div class="price2">
            <span class="name">会员折扣</span>
            <span class="number"><span class="mr_4">￥</span>-20.00</span>
        </div>
        <div class="price2">
            <span class="name">优惠卷</span>
            <span class="number"><span class="mr_4">￥</span>-20.00</span>
        </div>
        <div class="price3">
            <span class="name">已为你节省</span>
            <span class="number"><span class="mr_4">￥</span>40.00</span>
        </div>
    </div>

    <!-- 占位盒子 -->
    <div class="placeholder-footer3"></div>
    <div class="order-address" id="J_twoAddress">
        <span>送至:</span>
        重庆市 石柱县 物流镇明星村4-4
    </div>
    <div class="order-footer">
            <span class="price">
                实付
                <span class="number"><span class="mr_4">￥</span>128.00</span>
            </span>
        <a href="javascript:void(0);" class="order-payment-btn" id="J_paymentBtn">去支付</a>
    </div>

    <!-- 支付方式选择 -->
    <div class="order-payment-method" id="J_orderPaymentMethod">
        <div class="title">
            请选择支付方式
            <i class="icon-close" id="J_paymentClose"></i>
        </div>
        <div class="prompt">
            请在2小时内完成支付金额
            <span>16.22</span> 元
        </div>
        <div class="payment-choice box2">
            <div class="payment-method">
                <i class="icon-logo"></i>
                <span>余额支付</span>
                <i class="icon-checkbox J_checkbox active"></i>
            </div>
            <div class="payment-method">
                <i class="icon-wx"></i>
                <span>微信支付</span>
                <i class="icon-checkbox J_checkbox"></i>
            </div>
        </div>
        <button onclick="jiesuan();" type="submit" class="payment-btn">确认支付</button>
    </div>


</div>

<!-- 遮罩层 -->
<div class="mark" id="J_mark"></div>
<div class="footprint-prompt" id="J_footprintPrompt">
    <p>确定清空所有浏览记录？</p>
    <div class="footprint-confirm">
        <span class="cancel" id="J_cancel">取消</span>
        <span class="confirm" id="J_confirm">确定</span>
    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<!--页面交互-->
<script>
    // 弹出支付选择框
    $('#J_paymentBtn').click(function () {
        $('#J_orderPaymentMethod').show().animate({
            bottom: 0
        })
    });
    // 隐藏支付选择
    $('#J_paymentClose').click(function () {
        $('#J_orderPaymentMethod').hide().css('bottom', '-4.85rem');
    });
    // 切换支付选择
    $('.J_checkbox').click(function () {
        $('.J_checkbox').removeClass('active');
        $(this).addClass('active');
    });
    // 下方地址隐藏显示
    $(window).scroll(function () {
        var scrollH = $(window).scrollTop();
        if (scrollH > 80) {
            $('#J_twoAddress').show();
        } else {
            $('#J_twoAddress').hide();
        }
    })

    $("#go_live").click(function(){
        $("#J_mark,#J_footprintPrompt").show()
    })
    $("#J_confirm,#J_cancel").click(function(){
        $("#J_mark,#J_footprintPrompt").hide()
    })


    function initcoupon() {
        //优惠券详情
        var couponDetailBts = $('.coupon-detail');       //展开详情按钮列表
        var couponDetailBoxs = $('.coupon-item-detail');  //展开详情盒子
        couponDetailBts.each(function () {
            $(this).on('click',function () {
                if($(this).hasClass('show-status')){
                    $(this).removeClass('show-status');
                    $(this).parent().parent().find('.coupon-item-detail').hide('slow')
                }else {
                    couponDetailBoxs.each(function () {
                        $(this).hide('slow')
                    });
                    couponDetailBts.each(function () {
                        $(this).removeClass('show-status');
                    });
                    $(this).addClass('show-status');
                    $(this).parent().parent().find('.coupon-item-detail').show('slow')
                }
            })
        });
    }


        //    商品项暂开隐藏优惠券
        var couponBtns = $('.coupon-btn');  //商品项
        var couponInfoWraps = $('.coupon-info-wrap'); //商品优惠列表

        couponBtns.each(function () {
            $(this).on('click',function () {
                console.log(222);
                if($(this).hasClass('active')){
                    $(this).removeClass('active');
                    $(this).parent().find('.coupon-info-wrap').css('display','none')
                }else {
                    var haveActive = $(this).parent().parent().parent().parent().find('.active');  //此前是否有打开的列表
                    if(haveActive){
                        $(haveActive).parent().find('.coupon-info-wrap').css('display','none');
                        $(haveActive).removeClass('active');
                    }

                    //为当前添加样式
                    $(this).addClass('active');
                    $(this).parent().find('.coupon-info-wrap').css('display','block');
                }
            })
        })



</script>

<!--定义函数-->
<script>
    var goodsData = (${sessionScope.checkOrderData});
    var isActivity = goodsData.isActivity;
    console.log(goodsData);
    <!--加载商品信息-->
    function loadgoodsinfo() {
        var rows = goodsData.data;
        console.log(rows);
        var element = $('#goodsList');
        var html = '';
        for(var i=0;i<rows.length;i++){
            html = '<div class="order-product">'+
                '<a  class="img">'+
            '<img src="'+rows[i].imgurl+'" alt="">'+
            '</a>'+
            '<div class="text-box">'+
            '<a  class="title">'+rows[i].gname+'</a>'+
            '<p class="norms">'+rows[i].gsvals+'</p>'+
            '<div class="price-box">'+
            '<span class="price">￥'+rows[i].gdprice+'</span>'+
            '<div class="number-box">'+
            '<span class="buy-num">数量：×'+rows[i].num+'</span>'+
            '</div>'+
            '</div>'+
            '</div>'+
            '</div>';
              element.append(html);
        }
    }

    function loadAddress() {
        var sedata = "${sessionScope.checkOrderAddress.uaId}";
        console.log(sedata);
        var uaId;
        var uaName;
        var uaAllAddress;
        var uaPhone;
        if(sedata!=null&&sedata!=''){
            uaId = "${sessionScope.checkOrderAddress.uaId}";
            uaName = "${sessionScope.checkOrderAddress.uaName}";
            uaAllAddress = "${sessionScope.checkOrderAddress.uaAllAddress}";
            uaPhone = "${sessionScope.checkOrderAddress.uaPhone}";
            addAdressData(uaId,uaName,uaPhone,uaAllAddress);
        }else{
            $.ajax({
                type:"post",
                url:"/userAddress/queryUserAddress",
                dataType: "json",
                data:{pageNo:1,pageSize:1},
                success:function(data){
                    var rows = data.rows;
                    if(rows!=null&&rows.length>0){
                        var row = rows[0];
                        uaId = row.uaId;
                        uaName = row.uaName;
                        uaAllAddress = row.uaAllAddress;
                        uaPhone = row.uaPhone;
                        addAdressData(uaId,uaName,uaPhone,uaAllAddress);
                    }else{
                        $('#ordernoaddressimg').siblings().remove();
                        $('#ordernoaddressimg').show();
                    }
                }
            });
        }
    }

    function addAdressData(uaId,uaName,uaPhone,uaAllAddress) {
        $('#uaId').val(uaId);
        $('#uaName').html(uaName);
        $('#uaPhone').html(uaPhone);
        $('#uaAllAddress').html(uaAllAddress);
    }

    function loadgcpandcp(){
        var rows = goodsData.data;
        var reqRows=new Array();

        for(var i=0;i<rows.length;i++){
            var row = rows[i];
            var reqRow=new Object();
            reqRow["gdId"]=row.gdId;
            reqRow["num"]=row.num;
            reqRows.push(reqRow);
        }
        $.ajax({
            type:"post",//请求方式
            url: "/goodsCoupon/queryGoodsCouponLists",//发送请求地址
            data:JSON.stringify(reqRows),
            dataType:"json",
            contentType: "application/json",
            //请求成功后的回调函数有两个参数
            success:function(sdata){
                if(sdata.status==0){
                    var gcLists = sdata.data.gcLists;
                    var userCoupons = sdata.data.userCoupons;

                    if(userCoupons!=null&&userCoupons.length>0){
                        var userCouponsUl = $('.coupon-list');
                        for(var i=0;i<userCoupons.length;i++){
                            var row = userCoupons[i];
                            var html = '<li>'+
                                '<div class="coupon-item-tittle">'+
                            '<input name="Fruit" type="checkbox" value="苹果" />'+
                            '<label for="">'+row.cpName+'</label>'+
                            '<a class="coupon-detail"></a>'+
                            '</div>'+
                            '<div class="coupon-item-detail" style="display: none;">'+
                            '<img class="get-img" src="/img/coupon-img1.png">'+
                            '<div class="coupon-data">'+
                            '<p class="coupon-price">';

                            var cpCoinType = row.cpCoinType;
                            var small = '';
                            var strong = '';
                            if(cpCoinType==1){
                                small = '打'
                                strong = row.couponVal+'折';
                            }else{
                                small = '￥';
                                strong = row.couponVal;
                            }

                            html +=  '<small>'+small+'</small>'+
                            '<strong>'+strong+'</strong>'+
                            '</p>'+
                            '<p class="coupon-condition">'+row.useCondition+'</p>'+
                            '<p class="coupon-date">'+row.cpStartDate+'-'+row.cpEndDate+'</p>'+
                            '</div>'+
                            '</div>'+
                            '</li>';
                            userCouponsUl.append(html);
                        }
                        initcoupon();
                        jiesuan();
                    }

                }else{
                    layer.msg(sdata);
                }
            },
            error:function(sdata){
                layer.msg(sdata);
            }
        });

    }



    function jiesuan(){
        var rows = goodsData.data;
        var reqRows=new Array();

        for(var i=0;i<rows.length;i++){
            var row = rows[i];
            var reqRow=new Object();
            reqRow["buyCarId"]=row.buyCarId;
            reqRow["gcpIds"]='10';
            reqRow["gdId"]=row.gdId;
            reqRow["goodsId"]=57;
            reqRow["num"]=row.num;
            reqRow["remark"]=row.remark;
            reqRows.push(reqRow);
        }
        var data = new Object();
        data["address"] = 65;
        data["cpIds"] = 13;
        data["buyGoods"] = reqRows;
        $.ajax({
            type:"post",//请求方式
            url: "/order/buyGoods",//发送请求地址
            data:JSON.stringify(data),
            dataType:"json",
            contentType: "application/json",
            //请求成功后的回调函数有两个参数
            success:function(sdata){

            },
            error:function(sdata){
                layer.msg(sdata);
            }
        });

    }
</script>

<!--调用函数-->
<script>
    <!--加载商品-->
    loadgoodsinfo();

    <!--加载地址-->
    loadAddress();

    <!--加载优惠与优惠券-->
    loadgcpandcp();
</script>
</body>
</html>
