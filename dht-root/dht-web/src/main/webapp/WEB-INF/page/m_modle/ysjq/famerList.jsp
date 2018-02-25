
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>远山结亲</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/f-style.css">
    <style>
        ._user_item{
            position: relative;
        }

        ._f_item_tips{
            position: absolute;
            display: block;
            width: 1.3rem;
            height: 0.5rem;
            line-height: 0.5rem;
            color: #fff;
            right: 0.2rem;
            top:0.2rem;
            background-color: #f84a4c;
        }

        ._fa_name{
            color: #000;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0)" onclick="window.history.back();return false;" class="icon-return"></a>
    <span>远山结亲</span>
</div>

<div class="_user_box">
    <div class="_user_address">
        <span id="all" >全部</span>
        <div class="main">
            <div class="citys mb20" id="demo1">
                <select class="city-select" name="province" id="province"></select>
                <select class="city-select" name="city" id="city"></select>
            </div>
        </div>
    </div>
    <div class="_user_list">
        <div class="_user_item">
            <div class="_user_item_info">
                <img src="/img/_fa_header.png">
                <span class="_type">特困户</span>
            </div>
            <div class="_user_address_details">
                <p><span>高远山</span><span class="_user_s">男</span><span>78岁</span></p>
                <p><span>家庭人口:</span><span>五口人</span></p>
                <p class="_home_address"><span>家庭住址:</span><span>重庆市黔江区史家镇</span></p>
            </div>
        </div>

    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script src="/js/jquery-citys.min.js"></script>
<script>
    $(function(){
        /*demo1
         ***默认设置
         */

//        $('#demo1').citys();
        $('#demo1').citys({type: 'name',province: '重庆市',city: '石柱土家族自治县'});
        refreshItem(pageNo,pageSize);
    });
    var type = 1;
    $("#all").click(function () {
        type = 0;
        pageNo = 1;
        refreshItem(pageNo,pageSize);
    });
    $("#city").change(function(){
//            if (city == '-请选择-') {
//                return;
//            }
        pageNo = 1;
        refreshItem(pageNo,pageSize);
    });

    var flag = true;
    var pageNo = 1;
    var pageSize = 5;
    $(document).ready(function(){
        var range = 50;             //距下边界长度/单位px
        var totalheight = 0;
        $(window).scroll(function(){
            var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)

            totalheight = parseFloat($(window).height()) + parseFloat(srollPos);

            if(($(document).height()-range) <= totalheight && flag) {
                flag = false;
                pageNo = pageNo + 1;
                refreshItem(pageNo,pageSize);
            }
        });
    });
    function intoDetil(fid) {
        window.location.href="/famer/gotoFamerDetail?fid="+fid;
    }
    // 刷新列表
    function refreshItem(pageNo,pageSize) {

        var province = $("#province option:selected").text();
        var city = $("#city option:selected").text();

        if (type == 0) {
            province = '';city = '';
            $('#demo1').citys({type: 'name',province: "重庆市",city: "石柱土家族自治区"});
        };
        type = 1;

        var fcensus = province+'-'+city;
//        alert(pageNo);
//        console.log(province+'___'+city);
        var pflag = province.indexOf('请选择')>=0 || city.indexOf('请选择')>=0;
        if (pflag) {
            fcensus = '';
        }
//        alert(pageNo);
        if (pageNo == 1) {
            $("._user_list").empty();
        }
        $.ajax({
            type:"post",
            url:'/famer/queryFamerList',
            dataType: "json",
            data:{fcensus:fcensus,pageNo:pageNo,pageSize:pageSize},
            success:function(data){
                var rows = data.rows;
                var sex;
                if(rows!=null&&rows.length>0){
                    $.each(data.rows, function (index, element) {
                        if(element.fsex==1){
                            sex = "男";
                        }else{
                            sex = "女"
                        }
                        var uid = element.uid;
                        var jq = '';
                        if(uid!=null&&uid!=''){
                            jq = '<span class="_f_item_tips">已结亲</span>';
                        }
                        var html = '<div class="_user_item" onclick="intoDetil('+element.fid+')">\n' +
                            '            <div class="_user_item_info">\n' +
                            '                <img src="'+element.imgUrl+'">\n' +
                            '                <span class="_type">'+element.ffamilytype+'</span>'+jq+'\n' +
                            '            </div>\n' +
                            '            <div class="_user_address_details">\n' +
                            '                <p><span class="_fa_name">'+element.fname+'</span><span class="_user_s">'+sex+'</span><span>'+jsGetAge(element.fbirth)+'岁</span></p>\n' +
                            '                <p><span>家庭人口:</span><span>'+element.fpopulation+'人</span></p>\n' +
                            '                <p class="_home_address"><span>家庭住址:</span><span>'+element.faddress+'</span></p>\n' +
                            '            </div>\n' +
                            '        </div>';
                        $(html).appendTo("._user_list");

//                        $("._user_list").append(html);
                    });
                    flag = true;
                }


            }
        });
    }
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
</script>
</body>
</html>

