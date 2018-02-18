<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/new.css">
    <link rel="stylesheet" href="/css/y-style.css">
    <link rel="stylesheet" href="/css/f-style.css">
    <title>农户详情</title>
</head>

<body class="bge6">
<!--分享引导-->
<div class="guide-share" style="display: none;position: fixed" onclick="ikonw();">
    <!--<button class="i-know" onclick="ikonw();"></button>-->
    <div class="heigh-light"></div>

    <img class="guide-share-arrows" src="/img/arrows.png" >


</div>

<div class="specialty-title2 borderB">
    <a href="javascript:void(0)" onclick="window.history.back();return false;" class="icon-return"></a>
    <span>远山结亲</span>
</div>

<div class="_fa_box">
    <div class="_fa_hd">
        <div class="_fa_img">
            <div id="banner" class="focus">
                <div class="hd" style="display: none;">
                    <ul></ul>
                </div>
                <div class="bd">
                    <ul id="imgs">
                        <li><a href="#"><img _src="/img/famerback.jpg"  src="/img/famerback.jpg" /></a></li>
                    </ul>
                </div>
            </div>
            <a class="_all_fa" href="/famer/gotoFamerList" style="z-index: 9;color: #fff;">全部结亲对象</a>
        </div>
        <div class="_fa_info_box">
            <div class="_fa_info">
                <img id="imgUrl" src="">
                <p class="_tips"><span style="display: none;" id="jq">已结亲</span></p>
                <span id="fname"></span>
                <span id="fsex" class="_fa_info_s"></span>
                <span id="age" class="_fa_info_s"></span>
            </div>
            <div class="_fa_details_info">
                <p>家庭人口:<span id="population"></span></p>
                <p>家庭住址:<span id="faddress"></span></p>
                <p>家庭状况:<span id="ffamilytype"></span></p>
                <p class="_fa_details_s" id="fremark">

                </p>
            </div>
        </div>

        <div class="_recommend">
            <div class="_recommend_info">
                <p>采集人:<span class="_recommend_name" id="fcollector">古月</span><span class="_recommend_m" id="fcollectorPosition">史家镇镇长</span><span id="fcollectorPhone">13220284173</span></p>
            </div>

            <div class="_recommend_box">
                <span class="_recommend_tips">成功结亲</span>
                <div class="_recommend_item">
                    <img src="/img/_recommend.png">
                    <img src="/img/_recommend.png">
                </div>
                <span>0人</span>
            </div>
        </div>

        <div class="_goods">
            <span class="_goods_tips">在售土货</span>
            <div class="_goods_list">
                <%--<div class="_goods_item">--%>
                    <%--<img src="/img/_goods.png">--%>
                    <%--<p class="_goods_info">--%>
                        <%--<span class="_goods_name">纯天然蜂蜜</span>--%>
                        <%--<span class="_goods_price">--%>
                        <%--<span>￥</span>189</span>--%>
                    <%--</p>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>

    <div class="_fa_ft">
        <span onclick="shareGoods()">分享好友</span>
        <span onclick="jieqin();">与TA结亲</span>
    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script src="/js/TouchSlide.1.1.js"></script>
<script src="/js/layer/layer.js"></script>
<script type="text/javascript">
    function initbanner() {
        TouchSlide({
            slideCell: "#banner",
            titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
            mainCell: ".bd ul",
            effect: "left",
            autoPlay: true, //自动播放
            autoPage: true, //自动分页
            switchLoad: "_src" //切换加载，真实图片路径为"_src"
        });
    }
    initbanner();
</script>
<script>
    var fid = ${requestScope.fid};
    <!--加载农户详情-->
    function loadfamerDetail() {
        $.ajax({
            type:"post",
            url:'/famer/queryFamerList',
            dataType: "json",
            async :false,
            data:{fid:fid,pageNo:1,pageSize:1},
            success:function(data){
                var rows = data.rows;
                if(rows!=null&&rows.length>0){
                    var row = rows[0];
                    var headurl = row.imgUrl;
                    var fname = row.fname;
                    var sex = row.fsex==0?'女':'男';
                    var fpopulation = row.fpopulation;
                    var faddress = row.faddress;
                    var ffamilytype = row.ffamilytype;
                    var fremark = row.fremark;
                    var fcollector = row.fcollector;
                    var fcollectorPosition = row.fcollectorPosition;
                    var fcollectorPhone = row.fcollectorPhone;
                    var fbirth = row.fbirth;

                    $('#imgUrl')[0].src = headurl;
                    $('#fname').html(fname);
                    $('#fsex').html(sex);
                    $('#population').html(fpopulation+'口人');
                    $('#faddress').html(faddress);
                    $('#ffamilytype').html(ffamilytype);
                    $('#fremark').html(fremark);
                    $('#fcollector').html(fcollector);
                    $('#fcollectorPosition').html(fcollectorPosition);
                    $('#fcollectorPhone').html(fcollectorPhone);
                    $('#fname').html(fname);
                    var age = jsGetAge(row.fbirth);
                    $('#age').html(age+'岁');

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

    <!--加载农户图片详情-->
    function loadimgs() {
        $.ajax({
            type:"post",
            url:'/famerImg/queryFamerImgVoList',
            dataType: "json",
            async :false,
            data:{famerid:fid},
            success:function(data){
                var rows = data.rows;
                if(rows!=null&&rows.length>0){
                    $('#imgs').html('');
                    var rows = data.rows;
                    if(rows!=null&&rows.length>0){
                        for(var i=0;i<rows.length;i++){
                            var html = '<li><a><img _src="'+rows[i].imgUrl+'"  src="'+rows[i].imgUrl+'" /></a></li>';
                            $('#imgs').append(html);
                        }
                        initbanner();
                    }
                }
            }
        });
    }
    <!--加载与其结亲成功的人-->
    function loadjqcgperson() {
        $.ajax({
            type:"post",
            url:'/famerUser/queryFamerUserImgUrlList',
            dataType: "json",
            async :false,
            data:{famerid:fid},
            success:function(data){
                var rows = data.rows;
                if(rows!=null&&rows.length>0){
                    $('._recommend_item').html('');
                    $('._recommend_item').next().html(data.total+'人');
                    $('#jq').show();
                    for(var i=0;i<rows.length&&i<4;i++){
                        var html = '<img src="'+rows[i]+'" style="width: 0.8rem;height: 0.8rem;border-radius: 50%;display: inline-block">';
                        $('._recommend_item').append(html);
                    }
                }
            }
        });
    }
    <!--加载农户所有商品-->
    function loadGoods() {
        $.ajax({
            type:"post",
            url:'/famerGoods/queryFamerGoodsGoodsVoList',
            dataType: "json",
            async :false,
            data:{famerid:fid},
            success:function(data){
                var rows = data.rows;
                if(rows!=null&&rows.length>0){
                    for(var i=0;i<rows.length;i++){
                        var html = '<a href="/goods/'+rows[i].gid+'.html">' +
                                    '<div class="_goods_item">'+
                                    '<img src="'+rows[i].imgUrl+'">'+
                                    '<p class="_goods_info">'+
                                    '<span class="_goods_name">'+rows[i].gname+'</span>'+
                                    '<span class="_goods_price">'+
                                    '<span>￥</span>'+(rows[i].minprice/100).toFixed(2)+'</span>'+
                                    '</p>'+
                                    '</div>' +
                                    '</a>';
                        $('._goods_list').append(html);
                    }
                }
            }
        });
    }
    <!--与农户结亲-->
    function jieqin() {
        $.ajax({
            type:"post",
            url:'/famerUser/saveFamerUser',
            dataType: "json",
            async :false,
            data:{famerid:fid,pageNo:1,pageSize:1},
            success:function(data){
                var status = data.status;
                layer.msg(data.msg);
            }
        });
    }

    <!--调用分享函数-->
    function shareGoods() {
        $('.bge6').css('overflow', 'hidden');
        $('.guide-share').fadeIn('slow');
    }
    <!--点击知道了-->
    function ikonw() {
        $('.bge6').css('overflow', 'auto');
        $('.guide-share').fadeOut('slow');
    }
</script>

<!--调用函数-->
<script>
    loadimgs();

    loadfamerDetail();

    loadjqcgperson();

    loadGoods();


</script>

</body>
</html>
