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
        <a onclick="returnhistory();" class="icon-return" ></a>
        <span>确认订单</span>
    </div>

    <div class="address" id="address">
        <input type="hidden" value="" id="uaId">
        <a href="/order/choseAddress">
            <p>
                <span id="uaName"></span>
                <span id="uaPhone"></span>
            </p>
            <p id="uaAllAddress">

            </p>
            <i class="icon-right"></i>
        </a>
        <a href="/order/choseAddress">
            <img id="ordernoaddressimg" style="display: none;width: 100%;height: 100%" src="/img/ordernoaddress.jpg">
        </a>

    </div>

    <div id="goodsList">
        <%--<div class="order-product clear-fix">--%>
            <%--<a  class="img">--%>
                <%--<img src="/img/order1.png" alt="">--%>
            <%--</a>--%>
            <%--<div class="text-box">--%>
                <%--<a  class="title">荣泰（ROTAI）按摩椅 RT6616 多功能太零重力 足底滚轮指压 支持腰部热敷 专用皮质...。。。。。。家用全自动按摩椅 香槟色</a>--%>
                <%--<p class="norms">土豪金;168cm</p>--%>
                <%--<div class="price-box">--%>
                    <%--<span class="price">￥165</span>--%>
                    <%--<div class="number-box">--%>
                        <%--<span class="buy-num">数量：×7</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="coupon-info-box">--%>
                    <%--<span class="coupon-btn">商品优惠</span>--%>
                    <%--<ul class="coupon-info-wrap">--%>
                        <%--<li>--%>
                            <%--<span style="margin-top: 4px"><input type="checkbox"></span>--%>
                            <%--<span style="margin-left: 20px">会员折扣</span>--%>
                            <%--<span>￥ -20.00</span>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span style="margin-top: 4px"><input type="checkbox"></span>--%>
                            <%--<span style="margin-left: 20px">会员折扣</span>--%>
                            <%--<span>￥ -20.00</span>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span style="margin-top: 4px"><input type="checkbox"></span>--%>
                            <%--<span style="margin-left: 20px">会员折扣</span>--%>
                            <%--<span>￥ -20.00</span>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<span style="margin-top: 4px"><input type="checkbox"></span>--%>
                            <%--<span style="margin-left: 20px">会员折扣</span>--%>
                            <%--<span>￥ -20.00</span>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

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
                    <%--<div class="coupon-item-detail" style="">--%>
                        <%--<ul class="coupon_list1 ">--%>
                            <%--<li><a href="javascript:void(0)">--%>
                                <%--<div class="coupon_item_box clearfix">--%>
                                    <%--<div class="item_box_left"><span>￥</span><strong class="condition">1</strong>--%>
                                    <%--</div>--%>
                                    <%--<div id="item_box_right" class="item_box_right"><p class="term">满1.00可用</p>--%>
                                        <%--<p class="time" id="time"> - </p></div>--%>
                                <%--</div>--%>
                                <%--<p class="coupon_list1_info">测试1</p>--%>
                            <%--</a></li>--%>
                        <%--</ul>--%>
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
            <span class="number" id="shouldPay"><span class="mr_4">￥</span>0.00</span>
        </div>
        <div class="price2">
            <span class="name">商品总金额</span>
            <span class="number" id="goodsprice"><span class="mr_4">￥</span>0.00</span>
        </div>
        <div class="price2">
            <span class="name">运费</span>
            <span class="number" id="goodsFreight"><span class="mr_4">￥</span>0.00</span>
        </div>
        <div class="price2">
            <span class="name">会员折扣</span>
            <span class="number"><span class="mr_4">￥</span>-0.00</span>
        </div>
        <div class="price2">
            <span class="name">商品优惠</span>
            <span class="number" id="gcpspan"><span class="mr_4">￥</span>-0.00</span>
        </div>
        <div class="price2">
            <span class="name">优惠卷</span>
            <span class="number" id="couponspan"><span class="mr_4">￥</span>-0.00</span>
        </div>
        <div class="price3">
            <span class="name">已为你节省</span>
            <span class="number" id="cutdownprice"><span class="mr_4">￥</span>0.00</span>
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
                <span class="number" id="finalPrice"><span class="mr_4">￥</span>0.00</span>
            </span>
        <a onclick="jiesuan();" class="order-payment-btn" >去支付</a>
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
<script src="/js/layer/layer.js"></script>
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

    function initgcp() {
        //    商品项暂开隐藏优惠券
        var couponBtns = $('.coupon-btn');  //商品项
        var couponInfoWraps = $('.coupon-info-wrap'); //商品优惠列表

        couponBtns.each(function () {
            $(this).on('click',function () {
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
    }



</script>

<!--定义函数-->
<script>
    var goodsData = (${sessionScope.checkOrderData});
    var isActivity = goodsData.isActivity;
    console.log(goodsData);

    var inittotalPrice = 0;
    <!--加载商品信息-->
    function loadgoodsinfo() {
        var rows = goodsData.data;
//        console.log(rows);
        var element = $('#goodsList');
        var html = '';
        var index = 0;
        for(var i=0;i<rows.length;i++){
            var gdPrice = Number(rows[i].gdprice);
            var num = Number(rows[i].num);
            index += gdPrice * num;
            html = '<div class="order-product clear-fix">' +
                '<input type="hidden" value="'+rows[i].gdId+'" name="gdId">'+
                '<input type="hidden" value="'+rows[i].goodsId+'" name="goodsId">'+
                '<input type="hidden" value="'+rows[i].remark+'" name="remark">'+
                '<input type="hidden" value="'+rows[i].num+'" name="num">'+
                '<input type="hidden" value="'+rows[i].buyCarId+'" name="buyCarId">'+
                '<input type="hidden" value="'+rows[i].gdprice+'" name="gdprice">'+
//                '<input type="hidden" value="'+rows[i].gdspId+'" name="gdspId">'+
                '<a  class="img">'+
            '<img style="margin-top: 15px" src="'+rows[i].imgurl+'" alt="">'+
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

        $('#goodsprice').html('<span class="mr_4">￥</span>'+parseFloat(index).toFixed(2));
        inittotalPrice = index;
    }

    function loadAddress() {
        var sedata = "${sessionScope.checkOrderAddress.uaId}";
//        console.log(sedata);
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
                        $('#ordernoaddressimg').parent().siblings().remove();
                        $('#ordernoaddressimg').show();
                    }
                }
            });
        }
    }

    function addAdressData(uaId,uaName,uaPhone,uaAllAddress) {
        loadGoodsFreight(uaAllAddress);
        $('#uaId').val(uaId);
        $('#uaName').html(uaName);
        $('#uaPhone').html(uaPhone);
        $('#uaAllAddress').html(uaAllAddress);

        $('#J_twoAddress').html('<span>送至:</span>'+uaAllAddress);
    }

    <!--加载优惠和优惠券-->
    function loadgcpandcp(){
        if(isActivity==3){
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
                                    '<input onclick="choosecp(this);" name="Fruit" type="checkbox" val="'+row.couponVal+'" value="'+row.cpId+'" cpIsOverlapUse="'+row.cpIsOverlapUse+'" cpType="'+row.cpType+'" cpIsFirst="'+row.cpIsFirst+'"/>'+
                                    '<label for="">'+row.cpName+'</label>'+
                                    '<a class="coupon-detail"></a>'+
                                    '</div>'+
                                    '<div class="coupon-item-detail" style="display: none;margin-top: -.3rem;height: 2.7rem">';

                                var cpType = row.cpType;
                                var small = '';
                                var strong = '';
                                var small1='';
                                if(cpType==1){
                                    small = '打'
                                    strong = row.couponVal;
                                    small1 = '折';
                                }else{
                                    small = '￥';
                                    strong = row.couponVal;
                                }
                                var useRange = row.useRange;
                                if(useRange==''||useRange==null){
                                    useRange = '全场可用';
                                }

                                html +=  '<ul class="coupon_list1 ">'+
                                    '<li><a href="javascript:void(0)">'+
                                '<div class="coupon_item_box clearfix">'+
                                '<div class="item_box_left"><span>'+small+'</span><strong class="condition">'+strong+'</strong><span>'+small1+'</span>'+
                                '</div>'+
                                '<div id="item_box_right" class="item_box_right"><p class="term">'+row.useCondition+'</p>'+
                                '<p class="time" id="time"> '+row.cpStartDate + '- '+row.cpEndDate+'</p></div>'+
                                '</div><p class="coupon_list1_bg"></p>'+
                                '<p class="coupon_list1_info overflow" style="font-size: .3rem">'+useRange+'</p>'+
                                ' </a></li>'+
                                '</ul>'+
                                    '</div>'+
                                    '</li>';
                                userCouponsUl.append(html);
                            }
                            initcoupon();
                        }

                        var keyFlag = false;
                        for ( var key in gcLists ) {
                            keyFlag = true;
                        }
//                        console.log(keyFlag);
                        if(keyFlag){
                            var goodsdivs = $('.text-box');
//                            console.log(goodsdivs.length);
                            for(var i=0;i<goodsdivs.length;i++){
                                var godosdiv = $(goodsdivs[i]);
                                var gdId = godosdiv.parent().find('input[name=gdId]')[0].value;
                                var goodscoupons = gcLists[gdId];
//                                console.log(goodscoupons[1].gcpName);
                                if(goodscoupons.length>0){
                                    var html = '<div class="coupon-info-box">'+
                                        '<span class="coupon-btn">商品优惠</span>'+
                                        '<ul class="coupon-info-wrap">';

                                    for(var j=0;j<goodscoupons.length;j++){
                                        var goodscoupon = goodscoupons[j];
                                        var gcpIsOverlapUse = goodscoupon.gcpIsOverlapUse;
                                        var type = goodscoupon.gcpType;
                                        var val = goodscoupon.val;

                                        html += '<li>'+
                                            '<span style="margin-top: 4px"><input onclick="choosegcp(this);" val="'+goodscoupon.val+'" type="checkbox" value="'+goodscoupon.gcpId+'" gcpIsOverlapUse="'+gcpIsOverlapUse+'" gcpType="'+goodscoupon.gcpType+'"></span>'+
                                            '<span style="margin-left: 20px">'+goodscoupon.gcpName+'</span>';
                                        var yh = '';
                                        if(type==0){
                                            yh = '￥'+val;
                                        }else{
                                            yh = '打'+val+'折';
                                        }
                                        html += '<span>'+yh+'</span>'+
                                            '</li>';
                                    }

                                     html += '</ul>'+
                                        '</div>';
                                    godosdiv.append(html);
                                }else{
                                    continue;
                                }
                            }
                            initgcp();
                        }
                    }else{
                        layer.msg(sdata);
                    }
                },
                error:function(sdata){
                    layer.msg(sdata);
                }
            });
        }else{

        }
    }

    <!--点击选择优惠券-->
    function choosecp(obj) {
        outprice();
        var cpisoverlapuse = $(obj).attr("cpisoverlapuse");
        var inputs = $('.coupon-list').find('input[type=checkbox]');
        var flag = obj.checked;
        if(cpisoverlapuse==1&&flag){
            inputs.attr("disabled","disabled");
            inputs.parent().addClass("no-selected");
            $(obj).removeAttr("disabled");
            $(obj).parent().removeClass("no-selected");
        }
        if(cpisoverlapuse==1&&!flag){
            inputs.removeAttr("disabled");
            inputs.parent().removeClass("no-selected");
        }
        if(cpisoverlapuse==0&&flag){
            for(var i=0;i<inputs.length;i++){
                var overUse = $(inputs[i]).attr('cpisoverlapuse');
                if(overUse==1){
                    $(inputs[i]).attr('disabled','disabled');
                    $(inputs[i]).parent().addClass('no-selected');
                }
            }
        }
        if(cpisoverlapuse==0&&!flag){
            var flag = false;
            for(var i=0;i<inputs.length;i++){
                var overUse = $(inputs[i]).attr('cpisoverlapuse');
                if(overUse==0&&inputs[i].checked){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                inputs.removeAttr("disabled");
                inputs.parent().removeClass("no-selected");
            }
        }
    }

    <!--点击选择商品优惠-->
    function choosegcp(obj) {
        var gcpisoverlapuse = $(obj).attr("gcpisoverlapuse");
        var flag = obj.checked;
        if(gcpisoverlapuse==0&&flag){
            var inputs = $(obj).parent().parent().siblings().find('input[type=checkbox]');
            for(var i=0;i<inputs.length;i++){
                var othergcpisoverlapuse = $(inputs[i]).attr("gcpisoverlapuse");
                if(othergcpisoverlapuse==1){
                    $(inputs[i]).attr("disabled","disabled");
                }
            }
        }
        if(gcpisoverlapuse==0&&!flag){
            var inputs = $(obj).parent().parent().siblings().find('input[type=checkbox]');
            var myFlag = false;
            for(var i=0;i<inputs.length;i++){
                var othergcpisoverlapuse = $(inputs[i]).attr("gcpisoverlapuse");
                if(othergcpisoverlapuse==0&&inputs[i].checked){
                    myFlag = true;
                }
            }
            if(!myFlag){
                inputs.removeAttr("disabled");
            }
        }
        if(gcpisoverlapuse==1&&flag){
            $(obj).parent().parent().siblings().find('input[type=checkbox]').attr("disabled","disabled");
        }
        if(gcpisoverlapuse==1&&!flag){
            $(obj).parent().parent().siblings().find('input[type=checkbox]').removeAttr("disabled");
        }
        outprice();
    }

    var gfPrice = 0;
    <!--加载运费-->
    function loadGoodsFreight(address) {
        $.ajax({
            type:"post",
            url:"/goodsFreight/queryFreightByAddress",
            dataType: "json",
            data:{address:address},
            success:function(data){
                var row = data.row;
                if(row!=null){
                    $('#goodsFreight').html('<span class="mr_4">￥</span>'+parseFloat(row.gfPrice/100).toFixed(2) );
                    var goodsPrice = $('#goodsprice').html();

                    var goodsPriceFloat = Number(parseFloat(row.gfPrice/100).toFixed(2));
                    var goodsFreight = Number(removeHTMLTag(goodsPrice).substr(1));
                    var total = goodsPriceFloat+goodsFreight;
//                    console.log(total);
                    $('#finalPrice').html('<span class="mr_4">￥</span>'+ total.toFixed(2));
                    $('#shouldPay').html('<span class="mr_4">￥</span>'+total.toFixed(2));

                    gfPrice = goodsPriceFloat;
                }
            }
        });
    }

    <!--计算价格-->
    function outprice() {
        var downprice = 0;
        var goodsdivs = $('.order-product');
        var gcpdowncutprice = 0;
        if(goodsdivs!=null && goodsdivs.length>0){
            for(var i=0;i<goodsdivs.length;i++){
                var goods = $(goodsdivs[i]);
                var gdprice = goods.find('input[name=gdprice]')[0].value;
                var num = goods.find('input[name=num]')[0].value;
                var gcps = goods.find('.coupon-info-box').find('input[type=checkbox]:checked');
                var index = 0;
                if(gcps!=null&&gcps.length>0){
                    var gcpRateArr = new Array();
                    var gcpCashArr = new Array();

                    for(var j=0;j<gcps.length;j++){
                        var gcpType = $(gcps[j]).attr('gcptype');
                        if(gcpType==1){
                            gcpRateArr.push(gcps[j]);
                        }else{
                            gcpCashArr.push(gcps[j]);
                        }
                    }
                    var ingdPrice = Number(gdprice);
                    for(var j=0;j<gcpRateArr.length;j++){
                        var val =  Number($(gcpRateArr[j]).attr('val'));
                        ingdPrice = ingdPrice * val / 10;
                    }
                    for(var j=0;j<gcpCashArr.length;j++){
                        var val =  Number($(gcpCashArr[j]).attr('val'));
                        ingdPrice = ingdPrice - val;
                    }
                    ingdPrice = ingdPrice.toFixed(2);
                    var nowoneprice = (gdprice - ingdPrice).toFixed(2);

                    gcpdowncutprice += Number(nowoneprice) * num;
                    downprice += nowoneprice;
                }
            }
        }
        $('#gcpspan').html('<span class="mr_4">￥</span>-'+Number(gcpdowncutprice).toFixed(2));

        var lastPrice = inittotalPrice - gcpdowncutprice;
        var secondPrice = lastPrice;

        var coupons = $('.coupon-list').find('input[type=checkbox]:checked');
        var coupondowncutprice = 0;

        if(coupons!=null&&coupons.length>0){
            var firstRateArr = new Array();
            var firstCashArr = new Array();
            var lastRateArr = new Array();
            var lastCashArr = new Array();
            console.log(coupons.length);
            for(var i=0;i<coupons.length;i++){
                console.log(i);
                var coupon = $(coupons[i]);
                var cpisfirst = Number(coupon.attr('cpisfirst'))==0;
                var cptype = Number(coupon.attr('cptype'))==1;

                if(cpisfirst&&cptype){
                    firstRateArr.push(coupons[i]);
                }
                if(cpisfirst&&!cptype){
                    firstCashArr.push(coupons[i]);
                }
                if(!cpisfirst&&cptype){
                    lastRateArr.push(coupons[i]);
                }
                if(!cpisfirst&&!cptype){
                    lastCashArr.push(coupons[i]);
                }
            }
            console.log(firstRateArr.length);
            console.log(firstCashArr.length);
            console.log(lastRateArr.length);
            console.log(lastCashArr.length);
            for(var i=0;i<firstRateArr.length;i++){
                secondPrice = secondPrice * Number($(firstRateArr[i]).attr('val'))/10;
            }
            for(var i=0;i<firstCashArr.length;i++){
                secondPrice = secondPrice - Number($(firstCashArr[i]).attr('val'));
            }
            for(var i=0;i<lastRateArr.length;i++){
                secondPrice = secondPrice * Number($(lastRateArr[i]).attr('val'))/10;
            }
            for(var i=0;i<lastCashArr.length;i++){
                secondPrice = secondPrice - Number($(lastCashArr[i]).attr('val'));
            }
            coupondowncutprice = lastPrice - secondPrice;
        }

        $('#couponspan').html('<span class="mr_4">￥</span>-'+coupondowncutprice.toFixed(2));
        lastPrice = lastPrice - coupondowncutprice +gfPrice;
        var cutdownprice = gcpdowncutprice+coupondowncutprice;

        $('#cutdownprice').html('<span class="mr_4">￥</span>'+Number(cutdownprice).toFixed(2));
        $('#finalPrice').html('<span class="mr_4">￥</span>'+ Number(lastPrice).toFixed(2));
        $('#shouldPay').html('<span class="mr_4">￥</span>'+Number(lastPrice).toFixed(2));
    }

    function jiesuan(){
        var addressId = $('#address').find('input[type=hidden]')[0].value;
        if(addressId==null||addressId==''){
            layer.msg('请选择地址');
            return;
        }

//        var rows = goodsData.data;
//        var reqRows=new Array();
//
//        for(var i=0;i<rows.length;i++){
//            var row = rows[i];
//            var reqRow=new Object();
//            reqRow["buyCarId"]=row.buyCarId;
//            reqRow["gcpIds"]='10';
//            reqRow["gdId"]=row.gdId;
//            reqRow["goodsId"]=row.goodsId;
//            reqRow["num"]=row.num;
//            reqRow["remark"]=row.remark;
//            reqRows.push(reqRow);
//        }
//        var data = new Object();
//        data["address"] = 65;
//        data["cpIds"] = 13;
//        data["buyGoods"] = reqRows;


        var goodsrows = $('.order-product');
        var reqRows=new Array();

        for(var i=0;i<goodsrows.length;i++){
            var row = $(goodsrows[i]);
            var gcpIds = '';
            var checkboxs = row.find('.coupon-info-box').find('input[type=checkbox]:checked');
            if(checkboxs!=null&&checkboxs.length>0){
                for(var j=0;j<checkboxs.length;j++){
                    gcpIds += ','+checkboxs[j].value;
                }
                gcpIds = gcpIds.substr(1);
            }
            var reqRow=new Object();
            var buyCarId = row.find('input[name=buyCarId]')[0].value;
            if(buyCarId==null||buyCarId == 'undefined'){
                buyCarId = '';
            }
            reqRow["buyCarId"]=buyCarId;
            reqRow["gcpIds"]=gcpIds;
            reqRow["gdId"]=row.find('input[name=gdId]')[0].value;
//            reqRow["gdspId"]=row.find('input[name=gdspId]')[0].value;
            reqRow["goodsId"]=row.find('input[name=goodsId]')[0].value;
            reqRow["num"]=row.find('input[name=num]')[0].value;
            reqRow["remark"]=row.find('input[name=remark]')[0].value;
            reqRows.push(reqRow);
        }
        var data = new Object();
        var cpIds = '';
        var coupons = $('.coupon-list').find('input[type=checkbox]:checked');
        if(coupons!=null && coupons.length>0){
            for(var i=0;i<coupons.length;i++){
                cpIds += ','+coupons[i].value;
            }
            cpIds = cpIds.substr(1);
        }

        data["address"] = addressId;
        data["cpIds"] = cpIds;
        data["buyGoods"] = reqRows;
//        console.log(data);
        var url = sureUrl();

        $.ajax({
            type:"post",//请求方式
            url: url,//发送请求地址
            data:JSON.stringify(data),
            dataType:"json",
            contentType: "application/json",
            //请求成功后的回调函数有两个参数
            success:function(sdata){
                if(sdata.status==0){
                    var data = sdata.data;
                    var orderNo = data.orderNo;
                    var price = data.totalPrice;
                    window.location.href = "/wxPay/payInfo?orderNo="+orderNo+"&price="+price;
                }else{
                    alert(sdata.msg);
                }

            },
            error:function(sdata){
                layer.msg(sdata);
            }
        });

    }

    function sureUrl() {
        var url = '';
        if(isActivity==0){
            url = '/order/buySpecialOfferGoods';
        }
        if(isActivity==1){
            url = '/order/buySeckillGoods';
        }
        if(isActivity==2){
            url = '/order/buyCutPrice';
        }
        if(isActivity==3){
            url = '/order/buyGoods';
        }
        return url;
    }

    <!--获取纯文本内容-->
    function removeHTMLTag(str) {
        str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
        str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
        //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
        str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
        str=str.replace(/\s/g,''); //将空格去掉
        return str;
    }

    <!--返回上一页-->
    function returnhistory() {
        if(document.referrer===''){
            window.location.href='/';
        }else{
            window.history.go(-1);
        }
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
