<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的返现</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style type="text/css">
        .cash_bd{
            width: 100%;
            height: 6rem;
            background-color: #c94e28;
        }

        .cash_top{
            height: 4.7rem;
        }

        .cash_bottom{
            height: 1.3rem;
        }

        .cash_title{
            text-align: center;
            color: #fff;
            font-size: 0.38rem;
            padding-top: 1.6rem;
            letter-spacing: 0.05rem;
        }

        .cash_price{
            color: #fff;
            padding-top: 0.6rem;
            text-align: center;
            font-size: 0.6rem;
            padding-right: 0.2rem;
        }

        .icon{
            font-size: 0.4rem;
        }

        .cash_bottom{
            background-color: #d1643b;
        }

        .cash_info{
            float: left;
            width: 33.33%;
            height: 1.3rem;
            text-align: center;
            line-height: 1.3rem;
        }

        .cash_info p{
            height: 50%;
            line-height: 0.65rem;
            color: #fff;
            font-size: 0.3rem;
        }

        .cash_info p:first-child{
            letter-spacing: 1px;
            line-height: 0.9rem;
        }

        .cash_info p:last-child{
            line-height: 0.4rem;
        }

        .cash_ft{
            font-size: 0.32rem;
        }

        .cash_rank{
            margin-top: 0.25rem;
            padding: 0.25rem 0.2rem;
            background-color: #f6f5f5;
        }

        .cash_rank p{
            background: url(/img/right_icon.png) no-repeat;
            background-position: center right;
            padding-top: 0.07rem;
            padding-bottom: 0.07rem;
        }

        .cash_tips{
            padding:  0rem 0.2rem;
            margin-top: 0.8rem;
        }

        .title{
            margin-bottom: 0.4rem;
        }

        .tip_item{
            margin-left: 0.1rem;
            margin-bottom: 0.3rem;
            color: #808080;
            letter-spacing: 0.5px;
            font-size:0.25rem;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>我的返现</span>
</div>

<div class="cash_box">
    <div class="cash_bd">
        <div class="cash_top">
            <p class="cash_title">累计返现金额</p>
            <p class="cash_price"><span class="icon">￥</span>${details.cashTotal}</p>
        </div>
        <div class="cash_bottom">
            <div class="cash_info waitCash">
                <p>待提现</p>
                <p>${details.waitCash}</p>
            </div>
            <div class="cash_info allowCash">
                <p>可提现</p>
                <p>${details.allowCash}</p>
            </div>
            <div class="cash_info cash">
                <p>已提现</p>
                <p>${details.cash}</p>
            </div>
        </div>
    </div>
    <div class="cash_ft">
        <div class="cash_rank">
            <p>查看我的返现排名</p>
        </div>
        <div class="cash_tips">
            <p class="title">提现说明</p>
            <p class="tip_item">1. 待提现金额需要在七个工作日之后才能提现</p>
            <p class="tip_item">2. 可提现金额可以立即提现</p>
            <p class="tip_item">3. 已提现是历史提现金额</p>
        </div>

    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>
<script>
    $(".cash_info").click(function () {
        $(location).attr('href', '/ranking/openUserCashDetails');
    });

    $(".cash_rank").click(function () {
        $(location).attr('href', '/ranking/openUserRankingLists');
    });
</script>
</body>
</html>