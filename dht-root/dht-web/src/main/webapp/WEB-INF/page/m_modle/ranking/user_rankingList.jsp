<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/17
  Time: 22:47
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
        .cash_tab a{
            width: 33.33%;
            height: 1rem;
        }

        .cash_tab a p{
            height: 100%;
            line-height: 0.6rem;
        }

        .cash_info_hd{
            background-color: #fff;
            margin-top: 0.2rem;
            padding: 0.35rem 0.2rem;
        }

        .coupon-box{
            padding: 0;
        }

        .price_info{
            font-size: 0.3rem;
            color: #f2763b;
        }

        .price{
            font-size: 0.4rem;
            color: #f2763b;
            float: right;
        }

        .cash_item{
            background-color: #fff;
            padding: 0.38rem 0.2rem;
            border-bottom: 1px solid rgba(0,0,0,0.1);
        }

        .cash_list{
            margin-top: 0.2rem;
        }

        .time{
            float: right;
            color: #808080;
            font-size: 0.23rem;
        }

        .item_price{
            font-size: 0.3rem;
        }

        .wait_cash_hd{
            background-color: #fff;
            margin-top: 0.2rem;
            padding: 0.35rem 0.2rem;
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
            width: 4rem;

        }

        .wait_cash_price{
            float: right;
            line-height: 0.7rem;
        }

        .wait_cash_info span:first-child{
            padding-bottom: 0.2rem;
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

        .container_box{
            margin: 0.2rem 0.2rem;
            background-color: #fff;
            position: relative;
        }

        .container_box .hd{
            padding: 0.3rem 0.2rem;
            font-size: 0.3rem;
            border-bottom: 1px solid rgba(0,0,0,0.1);
        }

        .container_box .price{
            height: 0.3rem;
            color: 0.3rem;
            line-height: 0.3rem;
            margin-left: 0.2rem;
        }

        .container_box .bd{
            height: 2.5rem;
        }

        .container_box .info{
            margin-top: 0.4rem;
            display: block;
            font-size: 0.25rem;
            margin-left: 0.2rem;
            letter-spacing: 1px;
        }

        .container_box .cash{
            text-align: center;
            display: block;
            margin-top: 1rem;
            width: 3.5rem;
            position: absolute;
            padding: 0.22rem;
            letter-spacing: 1px;
            font-size: 0.25rem;
            left: 50%;
            margin-left: -1.75rem;
            border-radius: 0.05rem;
            background-color: #e77036;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="" class="icon-return"></a>
    <span>返现记录</span>
</div>

<div class="coupon-tab cash_tab" id="J_couponTab">
    <a href="#wait_cash" class="active">
        <p>待提现</p>
    </a>

    <a href="#cash_allaow">
        <p>可提现</p>
    </a>

    <a href="#cash_info">
        <p>已提现</p>
    </a>


</div>

<div class="coupon-cont box2">

    <div class="coupon-box" id="wait_cash">
        <div class="wait_cash_hd">
            <span class="price_info">总金额</span>
            <span class="price"><span class="icon">￥</span>300</span>
        </div>

        <div class="wait_cash_list">
            <div class="wait_cash_item">
                <div class="wait_cash_info">
                    <span style="display: block;">圣诞复古红V领显瘦毛衣圣诞复古红V领显瘦毛衣</span>
                    <span class="wait_cash_time">返现时间<span >2017-08-09 10:53</span></span>
                </div>

                <div class="wait_cash_price">
                    <span>798.00</span>
                </div>
            </div>

        </div>
    </div>

    <div class="coupon-box displayN" id="cash_allaow">
        <div class="container_box">
            <div class="hd">
                <span>输入提现金额</span>
                <input type="text" name="">
                <span style="float: right;">元</span>
            </div>
            <div class="bd">
                <span class="info">可提现余额<span>100</span><span>,全部提现</span></span>
                <a href="" class="cash" style="color: #fff">提现</a>
            </div>
        </div>
    </div>

    <div class="coupon-box displayN" id="cash_info">
        <div class="cash_info">
            <div class="cash_info_hd">
                <span class="price_info">总金额</span>
                <span class="price"><span class="icon">￥</span>200</span>
            </div>

            <div class="cash_list">
                <div class="cash_item">
                    <span class="item_price">200.25</span>
                    <span class="time">2017-10-23 10:16:30</span>
                </div>

                <div class="cash_item">
                    <span class="item_price">200.25</span>
                    <span class="time">2017-10-23 10:16:30</span>
                </div>

                <div class="cash_item">
                    <span class="item_price">200.25</span>
                    <span class="time">2017-10-23 10:16:30</span>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>
</body>

</html>