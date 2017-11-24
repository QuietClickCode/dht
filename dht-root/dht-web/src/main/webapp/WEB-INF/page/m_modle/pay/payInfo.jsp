<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>支付方式</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<style>
	.div_pay_mode{
		padding: .46rem .3rem;
		margin: 0 .2rem;
		margin-top: .5rem;
		border: 1px solid #e6e6e6;
		background: #fff;
	}
	.div_pay_mode>.pay_mode_title{
		width: 100%;
		font-size: .3rem;
		color: #333;
	}
	.div_pay_mode>.pay_mode_title>.p1{
		float: right;
		text-align: right;
	}
	.div_pay_mode>.pay_mode_title>.p1>span{
		color: #f85039;
	}
	.pay_mode_input_box{
		width: 100%;
		height: 1.2rem;
		border: 1px solid #e5e5e5;
		padding: 0 .3rem;
		line-height: 1.2rem;
		margin-top: .46rem;
	}
	.pay_mode_input_box.active{
		border-color: #f85039;
	}
	.pay_mode_input_box>img{
		display: inline-block;
		width: .62rem;
		height: .62rem;
		vertical-align: middle;
		margin-left: .6rem;
	}
	.pay_mode_input_box>.span1{
		font-size: .2rem;
		color: #333;
		margin-left: .3rem;
	}
	.pay_mode_input_box>a{
		float: right;
		color: #f85039;
		text-decoration: underline;
	}
	.pay_mode_btn{
		border: none;
		display: block;
		width: 4rem;
		height: .8rem;
		background: #f85039;
		font-size: .36rem;
		text-align: center;
		line-height: .8rem;
		color: #fff;
		border-radius: 4px;
		outline: none;
		margin: auto;
		margin-top: 2rem;
	}
	
	
	
	
	.label_box {
		font-size:12px;
		cursor:pointer;
    	display: inline-block;
    	font-size: .3rem;
    	color: #808080;
	}  
	.label_box > .span1{
		margin-right: .2rem;
		color: #333;
	}
	.label_box i {
		font-size:12px;
		font-style:normal;
		display:inline-block;
		width:16px;
		height:16px;
		text-align:center;
		line-height:14px;
		color:#fff;
		vertical-align:middle;
		margin:-2px 2px 1px 0px;
		border:#999 1px solid;
	}  
	input[type="checkbox"],input[type="radio"] {display:none;}  
	input[type="radio"] + i {border-radius:16px;} 
	input[type="checkbox"] + i {border-radius:16px;}  
	input[type="checkbox"]:checked + i,input[type="radio"]:checked + i {background:#e93d3d;border:#e93d3d 1px solid;}  
	input[type="checkbox"]:disabled + i,input[type="radio"]:disabled + i {border-color:#ccc;}  
	input[type="checkbox"]:checked:disabled + i,input[type="radio"]:checked:disabled + i {background:#ccc;}
</style>
<body class="bge6">
	<div class="specialty-title2 borderB">
        <a href="" class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>支付方式</span>
    </div>
	<input type="hidden" id="orderNo" name="orderNo" value="${orderNo}"/>
    <div class="div_pay_mode">
    	<div class="pay_mode_title">
    		请选择支付方式
    		<p class="p1">金额&emsp;<span>${price}</span>元</p>
    	</div>
    	<div class="pay_mode_input_box active">
    		<label class="label_box"><input type="radio" id="radio_input1" name="abc" checked="checked"><i>✓</i></label>
    		<img src="/img/icon-logo.png"/>
    		<span class="span1">余额支付</span>
    	</div>
    	<div class="pay_mode_input_box">
    		<label class="label_box"><input type="radio" id="radio_input2" name="abc"><i>✓</i></label>
    		<img src="/img/wx-logo.png"/>
    		<span class="span1">微信支付</span>
    		<a href="">立即充值</a>
    	</div>
        
    </div>
    <button type="button" class="pay_mode_btn" onclick="confirmPay()">确认支付</button>
</body>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="/js/layer/layer.js" ></script>
<script type="text/javascript" src="/js/common/common.js" ></script>

<script>
	$(function(){
		$(".label_box").click(function(){
			$(".label_box").parent().removeClass("active")
			$(this).parent().addClass("active")			
		})
	});

	var isSubmit=false;
    /**
	 * 确认支付
     */
	function confirmPay(){
		if(!isSubmit){
            isSubmit=true;
            //钱包支付
            var payType=0;
            //默认为钱包支付地址
            let submitUrl="";
            //判断是否是微信支付
            //判断是否是微信内打开
            if(isWeiXin()){
                //公众号支付
                payType=1;
                submitUrl="/wxPay/createWxPay";
            }else{
                //h5支付
                payType=2;
                submitUrl="/wxPay/createWxH5Pay";
            }
            $.ajax({
                url: submitUrl,
                type: 'post',
                cache:false,
                async:false,
                dataType: 'JSON',
                timeout: 5000,
				data:{"orderNo":$("#orderNo").val()},
                success: function(data){
                    if(data.status==0){
                        let msg=data.data;
                        if(payType==1){
                            appId = msg.appId;
                            timeStamp = msg.timeStamp;
                            nonceStr = msg.nonceStr;
                            pg = msg.pkg;
                            signType = msg.signType;
                            paySign = msg.sign;
                            pay();
                            // h5支付
                        }else if(payType==2){
                            isSubmit=false;
                            window.location.href=data.msg;
                        }
					}else{
                        //提示
                        layer.open({
                            content: msg.msg,
                            skin: 'msg',
                            time: 2 //2秒后自动关闭
                        });
					}
                }
            });
		}
	}

    //唤起微信支付
    function pay(){
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
                isSubmit=false;
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
					alert("支付成功");
                    window.location.href="/user/userWallet";
                }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {
                   alert("支付过程中用户取消");
                }else{
                    alert(res.err_msg);
                }
            }
        );
    }
</script>
</html>