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

    function queryFarmer() {
        $.ajax({
            url:'/famer/queryUserList',
            dataType:"json",
            type:"post",
            success:function(data){
                for(let i = 0;i<data.rows.length;i++){
                    let farmer = data.rows[i];
                    let age = jsGetAge(farmer.fbirth);
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
            }
        });
    }

    $(function () {
        queryFarmer();
    });

    // 得到岁数
    function jsGetAge(strBirthday)
    {
        var returnAge;
        var strBirthdayArr=strBirthday.split("-");
        var birthYear = strBirthdayArr[0];
        var birthMonth = strBirthdayArr[1];
        var birthDay = strBirthdayArr[2].split(" ")[0];

        d = new Date();
        var nowYear = d.getFullYear();
        var nowMonth = d.getMonth() + 1;
        var nowDay = d.getDate();

        if(nowYear == birthYear)
        {
            returnAge = 0;//同年 则为0岁
        }
        else
        {
            var ageDiff = nowYear - birthYear ; //年之差
            if(ageDiff > 0)
            {
                if(nowMonth == birthMonth)
                {
                    var dayDiff = nowDay - birthDay;//日之差
                    if(dayDiff < 0)
                    {
                        returnAge = ageDiff - 1;
                    }
                    else
                    {
                        returnAge = ageDiff ;
                    }
                }
                else
                {
                    var monthDiff = nowMonth - birthMonth;//月之差
                    if(monthDiff < 0)
                    {
                        returnAge = ageDiff - 1;
                    }
                    else
                    {
                        returnAge = ageDiff ;
                    }
                }
            }
            else
            {
                returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
            }
        }

        return returnAge;//返回周岁年龄
    }
    
    function gotoFarmerDetail($this) {
        window.location.href = $($this).attr('data-src');
    }

</script>
</body>

</html>