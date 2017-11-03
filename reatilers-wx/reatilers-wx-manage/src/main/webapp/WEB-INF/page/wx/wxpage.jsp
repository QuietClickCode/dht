<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/wechat.css">
</head>
<body>
<header style="margin-top: 10px;margin-bottom: 70px;">
    <div class="popover right">
        <div class="arrow"></div>
        <h3 class="popover-title">Popover 右侧</h3>
        <div class="popover-content">
            <img style="width: 478px;height: 201px;" src="/img/step_two.jpg">
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <ul class="navigation list-inline">
                <li data-value="0">1.基本信息</li>
                <li data-value="1">2.配置授权域名</li>
                <li data-value="2">3.配置公众号</li>
            </ul>
        </div>
    </div>
</header>

<div id="mainbody">
    <div class="row">
        <div class="col-md-6">
            <form class="form-horizontal wx_info" data-value="0">
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <p>请登录公众号获取以下信息！<a  target="_blank" href="https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN">进入公众号平台</a></p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-3 control-label">微信原始ID:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="微信原始ID" id="wx_id" onblur="closePopover()" onfocus="openPopover('#wx_id','第一步：登录微信公众号，左侧菜单 公众号设置—公开信息 栏目获取','/img/step_one.jpg')" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">微信号:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="wx_number" placeholder="微信号" onblur="closePopover()" onfocus="openPopover('#wx_number','第二步：登录微信公众号，左侧菜单  公众号设置 —注册信息 栏目获取 - 公开信息 栏目获取','/img/step_two.png')" data-toggle="popover" data-placement="right">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">公众号类型:</label>
                    <div class="col-sm-7">
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" onblur="closePopover()" class="inlineRadioOptions" id="inlineRadio1" value="option1"> 服务号
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" onblur="closePopover()" class="inlineRadioOptions" value="2"> 订阅号
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" onblur="closePopover()" class="inlineRadioOptions" value="3"> 认证服务号
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" onblur="closePopover()" class="inlineRadioOptions" value="option3"> 认证订阅号
                        </label>
                    </div>
                </div>

                <div class="form-group" style="display: none;" id="pre_info">
                    <label for="inputPassword3" class="col-sm-3 control-label"></label>
                    <div class="col-sm-7">
                        <label class="radio-inline">
                            <input type="radio" name="pre_info" class="pre_info" checked value="1"> 显示授权信息
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="pre_info" class="pre_info" value="2"> 隐藏授权信息
                        </label>
                        <p class="help-block" style="display: none;">隐藏授权信息后不能获取客户的微信信息如：昵称、头像等</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">微信二维码:</label>
                    <div class="col-sm-6">
                        <input class="btn btn-default" id="addCode" onblur="closePopover()" onfocus="openPopover('#addCode')" type="button" value="添加二维码">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <img src="/img/demo.jpg">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn-default" onclick="navigationLink(1)" type="button">下一步</button>
                    </div>
                </div>
            </form>

            <form class="form-horizontal wx_info" data-value="1">
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <p>请登录公众号获取以下信息！<a target="_blank" href="https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN">进入公众号平台</a></p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-3 control-label">接口域名:</label>
                    <div class="col-sm-6">
                        <select id="regionMenu" style="width: 200px;" onblur="closePopover()" onfocus="openPopover('#regionMenu','第五步：复制您选定的接口域名    第六步：登录微信公众号，将接口域名填写到：接口权限 —网页授权 —修改—网页授权域名 （如下图）','/img/step_five.jpg')"  class="form-control">
                            <option value="">---请选择---</option>
                            <option value="0">www.bilibili.com</option>
                            <option value="1">www.bilibili.com</option>
                            <option value="2">www.bilibili.com</option>
                        </select>
                        <p class="help-block" style="margin-top: 10px;">请将接口域名填写到公众平台网页授权域名处</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label">授权验证文件:</label>
                    <div class="col-sm-6">
                        <input type="password" style="width: 200px;display: inline-block;" onblur="closePopover()" onfocus="openPopover('#inputPassword3','第七步：请下载如图所示的MP_文件，并将文件上传到平台：验证授权文件！','/img/step_six.jpg')" class="form-control" id="inputPassword3" placeholder="授权验证文件">
                        <button class="btn btn-default" type="submit">上传</button>
                        <p class="help-block" style="margin-top: 10px;margin-bottom: 0px;">您之前已经上传了验证文件</p>
                        <p class="help-block" style="margin-top: 0px;"><ins>MP_verify_BBdZj3kUbD9sl2e7.txt</ins>，如需更新验证文件请再次上传，否则请忽略</p>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn-default" onclick="navigationLink(0)" type="button">上一步</button>
                        <button class="btn btn-default" onclick="navigationLink(2)" type="button">下一步</button>
                    </div>
                </div>
            </form>

            <form class="form-horizontal wx_info" data-value="2">
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <p>请登录公众号获取以下信息！<a target="_blank" href="https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN">进入公众号平台</a></p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="AppId" class="col-sm-3 control-label">AppId:</label>
                    <div class="col-sm-6">
                        <input type="text" placeholder="AppId" class="form-control" id="AppId" onblur="closePopover()" onfocus="openPopover('#AppId','第八步：登录公众号：将 基本配置 中的中的  APPID 和 AppSecret  填写到平台','/img/step_seven.png')" data-toggle="popover" data-placement="right">
                    </div>
                </div>

                <div class="form-group">
                    <label for="AppSecret" class="col-sm-3 control-label">AppSecret:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="AppSecret" onblur="closePopover()" onfocus="openPopover('#AppSecret')" id="AppSecret">
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn-default" onclick="navigationLink(1)" type="button">上一步</button>
                        <button class="btn btn-default" type="button">下一步</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div id="Wxinformation">
        <table class="table table-bordered">
            <tbody>
            <tr>
                <td>微信号</td>
                <td><span type="text" name="WeiXin" data-bind="text:WeiXin">zg-rmdht</span></td>
                <td rowspan="8" width="30%" align="center">
                    <h4>公众号二维码</h4>
                    <img src="/img/demo.jpg">
                </td>
                <td rowspan="8" align="center">
                    <button class="btn btn-default center-block" style="width: 100px;margin-bottom: 20px;margin-top: 80px;" type="button">修改</button>
                    <button class="btn btn-default center-block" style="width: 100px;" type="button">重置公众号</button>
                </td>
            </tr>
            <tr>
                <td>原始ID</td>
                <td><span type="text" name="WeiXinId" data-bind="text:WeiXinId">gh_1fe6db1bb841</span></td>
            </tr>
            <tr>
                <td>公众号类型</td>
                <td><span id="finshWxType">认证服务号</span></td>
            </tr>
            <tr>
                <td>AppId</td>
                <td><span type="text" name="AppId" data-bind="text:AppId">wxd2f8d593a73b3604</span></td>
            </tr>
            <tr>
                <td>AppSecret</td>
                <td><span type="text" name="AppSecret" data-bind="text:AppSecret">adfdfa1c8609de65bf2a71e5216b09d7</span></td>
            </tr>
            <tr>
                <td>接口域名</td>
                <td><span type="text" name="DomainName" data-bind="text:DomainName">www.zgrmdht.com</span></td>
            </tr>
            <tr>
                <td>接口URL</td>
                <td>
                    <span id="finshUrl">http://www.zgrmdht.com/wxapi?InitSiteID=23078&amp;wxid=gh_1fe6db1bb841</span>

                </td>
            </tr>
            <tr>
                <td>Token</td>
                <td> <span data-bind="text: Token">589a78ad77dd3</span></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>


<footer id="footer">
    <nav>
        <h3>常见问题</h3>
        <a target="_blank" href="http://help.yz168.com/NewsDetail/161574.html">问1：公众号如何绑定？</a><br/>
        <a target="_blank" href="http://help.yz168.com/NewsDetail/151407.html">问2：公众号常见报错问题？</a>
    </nav>
    <div id="type">
        <h3 style="display: inline-block;">公众号类型及权限</h3>
        <a target="_blank" href="http://mp.weixin.qq.com/wiki/8/71e1908fa08e67c6251ebdd78fd6b6b4.html">查看更多详情</a>
        <div class="bs-example" data-example-id="bordered-table">
            <table class="table table-bordered" border="1">
                <tbody><tr>
                    <th style="width:15%">公众号基础功能</th>
                    <th class="t2" style="width:20%">订阅号</th>
                    <th class="t3" style="width:20%">认证订阅号</th>
                    <th class="t4" style="width:20%">服务号</th>
                    <th class="t5" style="width:20%">认证服务号</th>
                </tr>
                <tr>
                    <td>自定义菜单</td>
                    <td class="t2">无</td>
                    <td class="t3">有</td>
                    <td class="t4">有</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>自动回复</td>
                    <td class="t2">有</td>
                    <td class="t3">有</td>
                    <td class="t4">有</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>群发管理</td>
                    <td class="t2">无</td>
                    <td class="t3">有</td>
                    <td class="t4">无</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>素材管理</td>
                    <td class="t2">有</td>
                    <td class="t3">有</td>
                    <td class="t4">有</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>用户管理</td>
                    <td class="t2">无</td>
                    <td class="t3">有</td>
                    <td class="t4">无</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td colspan="5"></td>
                </tr>
                <tr>
                    <td>微信授权及接口</td>
                    <td colspan="4"></td>
                </tr>
                <tr>
                    <td>网页授权</td>
                    <td class="t2">无</td>
                    <td class="t3">无</td>
                    <td class="t4">无</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>生成分销二维码</td>
                    <td class="t2">无</td>
                    <td class="t3">无</td>
                    <td class="t4">无</td>
                    <td class="t5">有</td>
                </tr>
                <tr>
                    <td>微信支付接口</td>
                    <td class="t2">无</td>
                    <td class="t3">无</td>
                    <td class="t4">无</td>
                    <td class="t5">需到微信申请</td>
                </tr>
                <tr>
                    <td>微信小店</td>
                    <td class="t2">无</td>
                    <td class="t3">无</td>
                    <td class="t4">无</td>
                    <td class="t5">需到微信申请</td>
                </tr>
                <tr>
                    <td>微信硬件接口</td>
                    <td class="t2">无</td>
                    <td class="t3">无</td>
                    <td class="t4">无</td>
                    <td class="t5">需到微信申请</td>
                </tr>
                </tbody></table>
        </div>
    </div>
</footer>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript">
    $(".navigation li").click(function(){
        $(this).css("color","black");
        $(this).siblings().css("color","white");
        let value = $(this).attr("data-value");
        $(".wx_info").each(function(){
            let wx_info = $(this).attr("data-value");
            if(value == wx_info){
                $("#Wxinformation").hide();
                $(this).show();
                $(this).siblings().hide();
            }
        });
    });

    function navigationLink(num){
        let dom = $(".wx_info").eq(num);
        dom.siblings().hide();
        dom.show();
    }

    function openPopover(id,title,imgUrl){
        let o = $(id).offset().top;
        $(".popover-title").text(title);
        $(".popover-content img").attr('src',imgUrl);
        let pop = $(".popover").height() / 2;
        let h = $(id).height() / 2;
        $(".popover").css("top",o - pop + h);
        $(".popover").show();
    }

    function closePopover(){
        $(".popover").hide();
    }

    $(".inlineRadioOptions").click(function(){
        openPopover('#inlineRadio1','第三步：下载 8CM 尺寸的二维码上传','/img/step_four.jpg');
        if($(this).val() != 3)
            $("#pre_info").show();
        else
            $("#pre_info").hide();

    });

    $(".pre_info").click(function(){
        if($(this).val() == 1)
            $(".help-block").hide();
        else
            $(".help-block").show();
    });

</script>
</body>
</html>
