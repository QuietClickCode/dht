<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/19
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>返现记录</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style type="text/css">
        body{
            overflow: hidden;
        }
        .cash_tab a{
            width: 50%;
            height: 1rem;
        }

        .cash_tab a p{
            height: 100%;
            line-height: 0.6rem;
        }


        .coupon-box{
            padding: 0;
        }

        .wait_cash_list{
            margin-top: 0.2rem;

        }

        .wait_cash_item{
            overflow: hidden;
            background-color: #fff;
            padding: 0.35rem 0.2rem;
            font-size: 0.3rem;
            border-bottom: 1px solid rgba(0,0,0,0.1)
        }

        .wait_cash_info{
            float: left;
            padding-bottom: 0.2rem;
            width: 5rem;
            padding-left: 1.1rem;
            background: url(/img/cash.png) no-repeat;
            background-position: left 0.1rem;
            background-size: 0.9rem 0.9rem;
        }

        .wait_cash_price{
            float: right;
            line-height: 1rem;
        }

        .wait_cash_price span{
            padding-right: 0.5rem;
            background: url(/img/right_100.png) no-repeat;
            background-position: right center;
            background-size: 0.45rem 0.45rem;
        }
        .wait_cash_info span:first-child{
            padding-bottom: 0.2rem;
            padding-top: 0.2rem;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }

        .wait_cash_time{
            color: #808080;
            font-size: 0.22rem;
        }

        .wait_cash_time span{
            margin-left: 0.2rem;
        }

        .cash_icon{
            background: url(/img/wait_cash.png) no-repeat;
            background-position: left 0.1rem;
            background-size: 0.9rem 0.9rem;
        }

        ::-webkit-scrollbar {display:none}

        .wait_cash_list{
            overflow: scroll;
            overflow-y: scroll;
            display: block;
            padding-bottom: 0.65rem;
            width: 100%;
            height: 12rem;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>返现记录</span>
</div>

<div class="coupon-tab cash_tab" id="J_couponTab">
    <a href="#cash_info" onclick="createWaitCashList()" class="active">
        <p>返现中</p>
    </a>
    <a href="#wait_cash" onclick="createCashList()">
        <p>已返现</p>
    </a>
</div>

<div class="coupon-cont box2">

    <div class="coupon-box" id="cash_info">
        <div class="cash_info">
            <div class="wait_cash_list wait_user_cashList">

            </div>
        </div>
    </div>

    <div class="coupon-box displayN" id="wait_cash">
        <div class="wait_cash_list user_cashList">
            <div class="wait_cash_item ">
                <div class="wait_cash_info">
                    <span style="display: block;">返现金额：<span>789.00</span></span>
                    <span class="wait_cash_time">订单编号：7824569941200</span>
                </div>

                <div class="wait_cash_price">
                    <span></span>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>
<script>
    
    $(function () {
        createWaitCashList();
    });
    
    function createWaitCashList() {
        $.ajax({
            url: "/ranking/queryUserRankingLists",
            type: "post",
            dataType: "json",
            data:{
                type:1
            },
            success:function (data) {
                $(".wait_user_cashList").html("");
                for(let i = 0;i<data.data.length;i++){
                    let cash = data.data[i];
                    let html = '<div class="wait_cash_item"> <div class="wait_cash_info cash_icon"> ';
                    html += '<span style="display: block;">返现金额：<span>'+cash.ccbqMoneys+'</span></span>';
                    html += '<span class="wait_cash_time">订单编号：'+cash.orderNo+'</span></div>';
                    html += '<div class="wait_cash_price"><span>'+cash.curCashQueue+'人排队</span></div></div>';
                    $(".wait_user_cashList").append(html);
                }
            }
        });
    }


    function createCashList() {
        $.ajax({
            url: "/ranking/queryUserRankingLists",
            type: "post",
            dataType: "json",
            data:{
                type:0
            },
            success:function (data) {
                $(".user_cashList").html("");
                for(let i = 0;i<data.data.length;i++){
                    let cash = data.data[i];
                    let html = '<div class="wait_cash_item"> <div class="wait_cash_info"> ';
                    html += '<span style="display: block;">返现金额：<span>'+cash.ccbqMoneys+'</span></span>';
                    html += '<span class="wait_cash_time">订单编号：'+cash.orderNo+'</span></div>';
                    html += '<div class="wait_cash_price"></div></div>';
                    $(".user_cashList").append(html);
                }
            }
        });
    }
</script>
</body>

</html>