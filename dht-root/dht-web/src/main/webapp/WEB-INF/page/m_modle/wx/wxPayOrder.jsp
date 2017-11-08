<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信端支付订单创建</title>
</head>
<script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="/js/qrcode.min.js" type="text/javascript"></script>
<body style="margin: 100px;">
<input type="button" value="公众号支付" onclick="createWxPay()" style="font-size:40px;"/>
<input type="button" value="扫码支付" onclick="createWxQRPay()" style="font-size:40px;"/>
<a id="getBrandWCPayRequest" href="#" style="font-size:40px;">H5非微信app支付</a>

<div id="qrcode" style="display: none;margin-top: 20px;"></div>
<script>
    var appId = "";
    var timeStamp = "";
    var nonceStr = "";
    var pg = "";
    var signType = "";
    var paySign = "";
    //开始支付
    function createWxQRPay(){
        $.ajax({
            url: '/wxPay/createWxQRPay',
            type: 'post',
            cache:false,
            async:false,
            dataType: 'JSON',
            timeout: 5000,
            success: function(msg){
                console.log(msg)
                if(msg.status==0){
                    new QRCode(document.getElementById('qrcode'), msg.msg);
                    $("#qrcode").show();
                }
            }
        });
    }
    //开始支付
    function createWxH5Pay(){
        $.ajax({
            url: '/wxPay/createWxH5Pay',
            type: 'post',
            cache:false,
            async:false,
            dataType: 'JSON',
            timeout: 5000,
            success: function(msg){
                console.log(msg);
                if(msg!=null){
                    $("#getBrandWCPayRequest").attr("href",msg.msg);
                }
            }
        });
    }
    //开始支付
    function createWxPay(){
        $.ajax({
            url: '/wxPay/createWxPay',
            type: 'post',
            cache:false,
            async:false,
            dataType: 'JSON',
            timeout: 5000,
            success: function(msg){
                console.log(msg);
                if(msg!=null){
                    appId = msg.appId;
                    timeStamp = msg.timeStamp;
                    nonceStr = msg.nonceStr;
                    pg = msg.pkg;
                    signType = msg.signType;
                    paySign = msg.sign;
                    pay();
                }
            }
        });
    }


    //唤起微信支付
    function pay(){
        console.log("pay-------------------------------->>>>:")
        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }

    }

    //开始支付
    function onBridgeReady(){
        console.log("-------------------------------->>>>:")
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId" : appId,     //公众号名称，由商户传入
                "timeStamp": timeStamp+"",         //时间戳，自1970年以来的秒数
                "nonceStr" : nonceStr, //随机串
                "package" : pg,
                "signType" : signType,         //微信签名方式:
                "paySign" : paySign    //微信签名
            },

            function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                    alert("支付成功");  // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {
                    alert("支付过程中用户取消");
                }else{
                    //支付失败
                    alert(res.err_msg)
                }
            }
        );
    }
    $(function(){
        createWxH5Pay();
    })
</script>
</body>
</html>
