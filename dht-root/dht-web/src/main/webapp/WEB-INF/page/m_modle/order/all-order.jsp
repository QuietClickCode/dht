<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>全部订单</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .price{
            float: right;
        }

        .order_tips{
            text-align: center;
            color: #808080;
            display: none;
        }

        .all-order-tab{
            overflow: hidden;
            position: relative;
        }

        #J_allOrderTab a{
            width: 1.4rem;
            outline: none;
        }

        .scoll_view{
            width: 14rem;
            overflow-x: scroll;
        }

        ._right_icon{
            position: absolute;
            right:0;
            width: 0.5rem;
            height: 0.7rem;
            bottom: 0;
            background-color: #fff;
            line-height: 0.3rem;
        }

        ._right_icon span{
            width: 0.3rem;
            height: 0.5rem;
            display: block;
            margin-top: 0.17rem;
            margin-left: 0.1rem;
            background: url("/img/right_icon.png") no-repeat;
            background-size: 0.3rem 0.3rem;
        }

        ._left_icon{
            position: absolute;
            left:0;
            width: 0.5rem;
            height: 0.7rem;
            display: none;
            bottom: 0;
            background-color: #fff;
            line-height: 0.3rem;
        }

        ._left_icon span{
            width: 0.3rem;
            height: 0.5rem;
            display: block;
            margin-top: 0.17rem;
            margin-left: 0.1rem;
            background: url("/img/left_icon.png") no-repeat;
            background-size: 0.3rem 0.3rem;
        }


    </style>
</head>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a class="icon-return" href="javascript:void(0);" onclick="comeBack()"></a>
    <span>全部订单</span>
</div>

<div class="all-order-tab" id="J_allOrderTab">
    <div class="scoll_view">
        <a href="#allOrder" id="allOrders" class="active">全部订单</a>
        <a id="obligation" href="#dfk">未付款</a>
        <a id="unsent" href="#dfh">待发货</a>
        <a id="refund" href="#tkz">退款中</a>
        <a id="receive" href="#dsh">待收货</a>
        <a id="ch_goods" href="#csh">确认收货</a>
        <a id="appraise" href="#dpj">交易完成</a>
    </div>
    <div class="_left_icon"><span></span></div>
    <div class="_right_icon"><span></span></div>
</div>

<div class="box2">
    <!-- 全部订单 -->
    <ul class="all-order-list box2" id="allOrder">
        <%--<li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待付款</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">取消订单</a>
                    <a href="" class="btn2">付款</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">评价</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待收货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">物流详情</a>
                    <a href="">查看订单</a>
                    <a href="">确认收货</a>
                </div>
            </div>
        </li>

        <li class="box2">
            <p class="start">待发货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">提醒发货</a>
                </div>
            </div>
        </li>--%>

    </ul>

    <!-- 待付款 -->
    <ul class="all-order-list displayN" id="dfk">
        <li class="box2">
            <p class="start">未付款</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">取消订单</a>
                    <a href="" class="btn2">付款</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待发货 -->
    <ul class="all-order-list displayN" id="dfh">
        <li class="box2">
            <p class="start">待发货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="" class="btn2">提醒发货</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待收货 -->
    <ul class="all-order-list displayN" id="dsh">
        <li class="box2">
            <p class="start">待收货</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="/order/checkLogistics">物流详情</a>
                    <a href="">查看订单</a>
                    <a href="/order/dealSuccess">确认收货</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待评价 -->
    <ul class="all-order-list displayN" id="dpj">
        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="/order/checkAppraise" class="btn2">评价</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 待退款 -->
    <ul class="all-order-list displayN" id="tkz">
        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="/order/checkAppraise" class="btn2">评价</a>
                </div>
            </div>
        </li>
    </ul>

    <!-- 确认收货 -->
    <ul class="all-order-list displayN" id="csh">
        <li class="box2">
            <p class="start">交易完成</p>
            <div class="order-infor">
                <a href="" class="img">
                    <img src="/img/list2.jpg" alt="">
                </a>
                <div class="text-box">
                    <a href="">
                        <span class="text">乐事多力多滋薯片多口味零食大礼盒400克</span>
                        <span class="price">￥29.9</span>
                    </a>
                    <p>规格:400g
                        <span class="number">×1</span>
                    </p>
                </div>
            </div>
            <div class="count-infor">
                <span class="number">共1件</span>
                合计：￥155(含运费:10.0)
                <div class="btn-box">
                    <a href="">查看订单</a>
                    <a href="/order/checkAppraise" class="btn2">评价</a>
                </div>
            </div>
        </li>
    </ul>
</div>

<div class="order_tips"><span>暂无订单信息~</span></div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>

<script>
    //当前页数据
    var curPage=1;
    $(function(){
        var href = window.location.hash;
        console.log(href)
        if (href != ""){
            $(href).click();
            $(".order_tips").show();
        }

        if(href == '#appraise'){
            $("._right_icon").click();
        }
        queryOrderByStatus("",100);
    });

    $("#obligation").click(function () {
        $("#dfk").html("");
        queryOrderByStatus(0,0);
    });

    $("#allOrders").click(function () {
        $("#allOrder").html("");
        queryOrderByStatus("",100);
    });

    $("#unsent").click(function () {
        $("#dfh").html("");
        queryOrderByStatus(3,3);
    });

    $("#receive").click(function () {
        $("#dsh").html("");
        queryOrderByStatus(4,4);
    });

    $("#refund").click(function () {
        $("#tkz").html("");
        queryOrderByStatus(6,6);
    });

    $("#appraise").click(function () {
        $("#dpj").html("");
        queryOrderByStatus(9,9);
    });

    $("#ch_goods").click(function () {
        $("#csh").html("");
        queryOrderByStatus(5,5);
    });
    /**
     * 根据订单类型取得订单列表数据
     * @param orderStatus
     */
    function queryOrderByStatus(orderStatus,num){
        var queryParams={
            pageSize: 30,
            pageNo: curPage,
            orderStatus:orderStatus
        }
        $.ajax({
            url: "/order/queryUserOrder",
            type: "post",
            dataType: "json",
            data:queryParams,
            success: function (data) {
                orderView(data.rows,num);
            }
        });
    }

    /**
     * 根据查询结构显示订单数据
     * @param row
     */
    function orderView(rows,num){
        if(rows){
            if(rows.length == 0){
                $(".order_tips").show();
            }else{
                $(".order_tips").hide();
            }
            for(var row of rows){
                let osMsg = '未支付';
                if(row.orderStatus==1){
                    osMsg= "支付中";
                }else if(row.orderStatus==2){
                    osMsg='支付失败';
                }else if(row.orderStatus==3){
                    osMsg='待发货';
                }else if(row.orderStatus==4){
                    osMsg='己发货';
                }else if(row.orderStatus==5){
                    osMsg='确认收货';
                }else if(row.orderStatus==6){
                    osMsg='发起退款';
                }else if(row.orderStatus==7){
                    osMsg='用户取消';
                }else if(row.orderStatus==8){
                    osMsg='订单失效';
                }
                else if(row.orderStatus==9){
                    osMsg='交易完成';
                }

                var ov='<li class="box2"><p class="start">'+row.orderNo+'&nbsp;&nbsp;'+osMsg+'</p>';
                //购买总件数
                let buyTotalNm=0;
                if(row.ods){
                    let orderInfo=row.ods;
                    for(var info of orderInfo){
                        ov+='<div class="order-infor"><a href="javascript:void(0)" class="img">';
                        ov+='<img src="'+info.gImgUrl+'" alt=""></a><div class="text-box">';
                        ov+='<a href=""> <span class="text">'+info.gName+'</span><span class="price">￥'+info.gdPrice+'</span>';
                        ov+='</a><p>规格:'+info.gsName+'<span class="number">×'+info.odBuyNumber+'</span></p></div></div>';
                        buyTotalNm+=info.odBuyNumber;
                    }
                }

                if(num == 100){
                    if(row.orderType == 'RECHARGE'){
                        if(row.orderStatus == 8){
                            ov+='<div class="count-infor" style="line-height: 1rem">';
                            ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"></div></div></li>';
                            $("#allOrder").append(ov);
                        }else{
                            ov+='<div class="count-infor">';
                            ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"></div></div></li>';
                            $("#allOrder").append(ov);
                        }

                    }else{
                        if(row.orderStatus == 8){
                            ov+='<div class="count-infor" style="line-height: 1rem">';
                            ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"></div></div></li>';
                            $("#allOrder").append(ov);
                        }else{
                            ov+='<div class="count-infor">';
                            ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a></div></div></li>';
                            $("#allOrder").append(ov);
                        }
                    }
                }else if(num == 0){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a onclick="cancelOrder('+row.id+')">取消订单</a><a href="/wxPay/payInfo?orderNo='+row.orderNo+'&price='+row.orderTradePrice+'&type='+row.orderType+'&formate=true" class="btn2">付款</a></div></div></li>';
                    $("#dfk").append(ov);
                }else if(num == 3){
                    if(row.orderType == 'RECHARGE'){
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="" class="btn2">提醒发货</a></div></div></li>';
                        $("#dfh").append(ov);
                    }else{
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a></div></div></li>';
                        $("#dfh").append(ov);
                    }
                }else if(num == 4){
                    if(row.orderType == 'RECHARGE'){
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a onclick="orderConfirm('+row.id+')">确认收货</a></div></div></li>';
                        $("#dsh").append(ov);
                    }else{
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a><a onclick="orderConfirm('+row.id+')">确认收货</a></div></div></li>';
                        $("#dsh").append(ov);
                    }
                }else if(num == 6){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a></div></div></li>';
                    $("#tkz").append(ov);
                }else if(num == 9){
                    ov+='<div class="count-infor">';
                    ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a></div></div></li>';
                    $("#dpj").append(ov);
                }else if(num == 5){
                    if(row.orderType == 'RECHARGE'){
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"></div></div></li>';
                        $("#csh").append(ov);
                    }else{
                        ov+='<div class="count-infor">';
                        ov+='<span class="number">共'+buyTotalNm+'件</span>合计：￥'+row.orderTradePrice+'(含运费:'+row.orderLogisticsPrice+')<div class="btn-box"><a href="/order/orderInfo?orderId='+row.id+'">查看订单</a></div></div></li>';
                        $("#csh").append(ov);
                    }
                }
            }
        }
    }

    /*取消订单*/
    function cancelOrder(id) {
        $.ajax({
            url:"/order/cancelOrder",
            type:"post",
            dataType: "json",
            data:{
                orderId:id
            },
            success:function (data) {
//                window.location.reload();
            }
        });
    }

    function orderConfirm(id) {
        $.ajax({
            url:"/order/orderConfirm",
            type:"post",
            dataType: "json",
            data:{
                orderId:id
            },
            success:function (data) {
                window.location.reload();
            }
        });
    }
</script>

<script>
    $("._right_icon").click(function () {
        $("._right_icon").hide();
        $("._left_icon").show();
        $(".scoll_view").animate({marginLeft:'-6.5rem'});
    });

    $("._left_icon").click(function () {
        $("._left_icon").hide();
        $("._right_icon").show();
        $(".scoll_view").animate({marginLeft:'0rem'});
    });

    function comeBack() {
        window.location.href = '/user/userCenter';
    }
</script>
</body>

</html>
