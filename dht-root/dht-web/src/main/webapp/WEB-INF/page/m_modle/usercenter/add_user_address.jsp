<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新加地址</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
	<style>
		.add_adress_box_R .input_text{
			height: 0.5rem;
			margin-top: 0.25rem;
			width: 3rem;
		}

		.add_adress_box_R select{
			height: 0.5rem;
			margin-top: 0.25rem;
		}
	</style>
</head>

<body class="bge6">
    <div class="specialty-title2 borderB">
		<a class="icon-return"  href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>新加地址</span>
    </div>
	<form id="userAddressFrom">
		<input type="hidden" class="input_text" name="uaId" id="uaId" value="${userAddress.uaId}"/>
		<input type="hidden" class="input_text" name="uaUid" id="uaUid" value="${userAddress.uaUid}"/>
		<input type="hidden" class="input_text" name="version" id="version" value="${userAddress.version}"/>
		<!-- 缓存用户详细地址-->
		<input type="hidden" class="input_text" name="uaAllAddress" id="uaAllAddress" value="${userAddress.uaAllAddress}"/>
		<ul class="add_adress_box">
			<li>
				<div class="add_adress_box_L">收货人</div>
				<div class="add_adress_box_R">
					<input type="text" class="input_text" name="uaName" id="uaName" value="${userAddress.uaName}" placeholder="请填写收货人姓名" />
				</div>
			</li>
			<li>
				<div class="add_adress_box_L">联系方式</div>
				<div class="add_adress_box_R">
					<input type="text" class="input_text" name="uaPhone" id="uaPhone" value="${userAddress.uaPhone}" placeholder="请填写联系人号码" />
				</div>
			</li>
			<div data-toggle="distpicker" id="distpicker">
				<li>
					<div class="add_adress_box_L">省</div>
					<div class="add_adress_box_R">
						<select data-province="${userAddress.uaProvince}" id="uaProvince" name="uaProvince"></select>
					</div>
				</li>
				<li>
					<div class="add_adress_box_L">市</div>
					<div class="add_adress_box_R">
						<select data-city="${userAddress.uaCity}" id="uaCity" name="uaCity"></select>
					</div>
				</li>
				<li>
					<div class="add_adress_box_L">区/县</div>
					<div class="add_adress_box_R">
						<select data-district="${userAddress.uaDistrict}" id="uaDistrict" name="uaDistrict"></select>
					</div>
				</li>
			</div>
			<li>
				<div class="add_adress_box_L">详细地址</div>
				<div class="add_adress_box_R">
					<input type="text" class="input_text" name="uaAddress" id="uaAddress" value="${userAddress.uaAddress}" placeholder="请输入街道、楼牌号" />
				</div>
			</li>
			<li>
				<div class="add_adress_box_L">邮编</div>
				<div class="add_adress_box_R">
					<input type="text" class="input_text" name="uaZipCode" id="uaZipCode" value="${userAddress.uaZipCode}" placeholder="请填写邮政编码" />
				</div>
			</li>
		</ul>
	</form>
	<div class="add_address_footer">
		<span class="add_address_footer_btn" onclick="saveUserAddress()">保存地址</span>
	</div>

    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/dist/distpicker.data.js"></script>
    <script src="/js/dist/distpicker.js"></script>
	<script src="/js/layer_mobile/layer.js"></script>
	<script>
		var countdown=60;
		function settime(val) {
			if (countdown == 0) {
				val.removeAttribute("disabled");
				val.value="获取验证码";
				countdown = 60;
				return;
			} else {
				val.setAttribute("disabled", true);
				val.value="重新发送(" + countdown + ")";
				countdown--;
			}
			setTimeout(function() {
				settime(val)
			},1000)
		}
		var initUaProvince='${userAddress.uaProvince}'==''?'---- 选择省 ----':'${userAddress.uaProvince}';
		var initUauaCity='${userAddress.uaCity}'==''?'---- 选择市 ----':'${userAddress.uaCity}';
		var initUauaDistrict='${userAddress.uaDistrict}'==''?'---- 选择区 ----':'${userAddress.uaDistrict}';
//		console.log(initUaProvince)
//		console.log(initUauaCity)
//		console.log(initUauaDistrict)

		$(function(){
	        $("#replace_nav > li").click(function(){
	            var $this = $(this),
	                index = $this.index("#replace_nav > li");
	            $this.addClass("active").siblings("li").removeClass("active");
	            $(".replace_container > .replace_container_item").eq(index).addClass("active").siblings(".replace_container_item").removeClass("active");
	        });
//            $('#distpicker').distpicker({
//                province: initUaProvince,
//                city: initUauaCity,
//                district: initUauaDistrict
//			});
	    });

		var isSaves=false;
        /**
		 * 保存用户地址
         */
		function saveUserAddress(){
		    if(!isSaves){
		        isSaves=true;
                let curIndex = layer.open({
                    type: 2,
                    shadeClose:false,
                    content: '正在添加收货地址'
                });
                var formData=$("#userAddressFrom").serializeObject();
                let allAddress="";
                if($("#uaProvince").val()){
//                    allAddress=$("#uaProvince").val();
                    allAddress+=$("#uaProvince").find("option:selected").text();
				}
                if($("#uaCity").val()){
//                    allAddress+=$("#uaCity").val();
                    allAddress+=$("#uaCity").find("option:selected").text();
                }
                if($("#uaDistrict").val()){
//                    allAddress+=$("#uaDistrict").val();
                    allAddress+=$("#uaDistrict").find("option:selected").text();
                }
                if($("#uaAddress").val()){
                    allAddress+=$("#uaAddress").val();
                }
                let url="/userAddress/addUserAddress";
                if($("#uaId").val()){
                    url="/userAddress/editorUserAddress";
                }
				formData["uaAllAddress"]=allAddress;
                $.ajax({
                    type:"post",
                    url:url,
                    dataType: "json",
                    data:formData,
                    success:function(data){
                        layer.close(curIndex);
                        if(data.status==0){
                            //提示
                            layer.open({
                                content: data.msg,
                                skin: 'msg',
                                time: 2, //2秒后自动关闭
                                end:function(){
                                    window.history.back();
                                }
                            });

                        }
                        isSaves=false;

                    }
                });
			}
		}
        $.fn.serializeObject = function()
        {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
	</script>
</body>

</html>
