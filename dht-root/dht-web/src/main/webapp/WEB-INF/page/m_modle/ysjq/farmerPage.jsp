<%--
  Created by IntelliJ IDEA.
  User: zaqhr
  Date: 2018/2/9
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的结亲对象</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/y-style.css">
    <link rel="stylesheet" href="/css/f-style.css">
    <style>
        ._user_address_details{
            font-size: 0.22rem;
        }
        ._tips{
            display: block;
            text-align: center;
            margin-top: 0.5rem;
        }

        ._fa_name{
            color: #000;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0)" class="icon-return" onclick="window.history.back();return false;" class="icon-return"></a>
    <span>我的结亲对象</span>
</div>

<div class="_user_box">

    <div class="_user_list">
        <%--<div class="_user_item">
            <div class="_user_item_info">
                <img src="img/_fa_header.png">
                <span class="_type">特困户</span>
            </div>
            <div class="_user_address_details">
                <p><span>高远山</span><span class="_user_s">男</span><span>78岁</span></p>
                <p><span>家庭人口:</span><span>五口人</span></p>
                <p class="_home_address"><span>家庭住址:</span><span>重庆市黔江区史家镇</span></p>
            </div>
        </div>--%>
    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script>
    var outpageNo=1;
    var outpageSize=10;
    var flag = true;

    function queryFarmer(pageNo,pageSize) {
        $.ajax({
            url:'/famer/queryUserList',
            dataType:"json",
            type:"post",
            success:function(data){
                for(let i = 0;i<data.rows.length;i++){
                    let farmer = data.rows[i];
                    let age = GetDateDiff(new Date(farmer.fbirth),new Date());
                    var sex = farmer.fsex==0?'女':'男';
                    let dataTime = new Date();
                    let birth = farmer.fbirth.substr(0,4);
                    let html = '<div class="_user_item" onclick="gotoFarmerDetail(this)" data-src="/famer/gotoFamerDetail?fid='+farmer.fid+'"><div class="_user_item_info"><img src="'+farmer.imgUrl+'">';
                    html += '<span class="_type">'+farmer.ffamilytype+'</span></div><div class="_user_address_details">';
                    html += '<p><span class="_fa_name">'+farmer.fname+'</span><span class="_user_s">'+sex+'</span><span>'+age+'岁</span></p>';
                    html += '<p><span>家庭人口:</span><span>'+farmer.fpopulation+'口人</span></p>';
                    html += '<p class="_home_address"><span>家庭住址:</span><span>'+farmer.faddress+'</span></p></div></div>';
                    $("._user_list").append(html);
                }
                if(data.rows.length == 0){
                    $("._user_list").append("<span class='_tips'>暂无结亲对象~</span>");
                }
                outpageNo++;
                flag = true;
            }
        });
    }

    $(function () {
        queryFarmer(1,10);
//        scrollloadCoupon();
    });

    function scrollloadCoupon() {
        $(document).ready(function(){
            var range = 50;             //距下边界长度/单位px
            var totalheight = 0;
            $(window).scroll(function(){
                var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)

                totalheight = parseFloat($(window).height()) + parseFloat(srollPos);

                if(($(document).height()-range) <= totalheight && flag) {
                    flag = false;
                    queryFarmer(outpageNo,outpageSize);
                }
            });
        });
    }

    function GetDateDiff(startDate,endDate)
    {
        var startTime = new Date(startDate).getTime();
        var endTime = new Date(endDate).getTime();
        var dates = Math.abs((startTime - endTime))/(1000*60*60*24*365);
        return   Math.floor(dates);
    }
    
    function gotoFarmerDetail($this) {
        window.location.href = $($this).attr('data-src');
    }

</script>
</body>

</html>