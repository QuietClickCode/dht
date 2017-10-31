<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/bwizard/bwizard.min.css"/>
</head>
<body>
    <form id="wxForm">
        <div id="wizard" style="padding: 10px;">
            <ol>
                <li>基本信息</li>
                <li>配置权限域名</li>
                <li>配置公众号</li>
            </ol>
            <div>
                <input type="hidden" name="wxId" id="wxId" value="${curWx.wxId}">
                <input type="hidden" name="version" id="version" value="${curWx.version}">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            请登录公众号获取以下信息!<a href="https://mp.weixin.qq.com/" target="_blank">进入公众号平台</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            <span class="input-group-addon">
                                原始ID:
                            </span>
                            <input type="text" class="form-control" name="wxOriginalId" id="wxOriginalId" placeholder="请输入微信原始ID" value="${curWx.wxOriginalId}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="input-group form-group">
                              <span class="input-group-addon">
                               微信号:
                              </span>
                            <input type="text" class="form-control" name="wxName" id="wxName" placeholder="请输入微信号" value="${curWx.wxName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                                <span class="input-group-addon">
                                    公众号类型:
                                </span>
                            <input type="text" class="form-control" name="cpNum" id="cpNum">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 配置授权域名 -->
            <div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            请登录公众号获取以下信息!<a href="https://mp.weixin.qq.com/" target="_blank">进入公众号平台</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            <span class="input-group-addon">
                                接口域名:
                            </span>
                            <input type="text" class="form-control" name="wxDomainName" id="wxDomainName" placeholder="请入接口域名" value="${curWx.wxDomainName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="input-group form-group">
                              <span class="input-group-addon">
                               授权验证文件:
                              </span>
                            <input type="text" class="form-control" name="wxValidate" id="wxValidate" placeholder="请输入微信号">
                        </div>
                    </div>
                </div>
            </div>
            <!-- 配置公众号-->
            <div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            请登录公众号获取以下信息!<a href="https://mp.weixin.qq.com/" target="_blank">进入公众号平台</a>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                            <span class="input-group-addon">
                                AppId:
                            </span>
                            <input type="text" class="form-control" name="appId" id="appId" placeholder="请输入appId" value="${curWx.appId}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="input-group form-group">
                              <span class="input-group-addon">
                               AppSecret:
                              </span>
                            <input type="text" class="form-control" name="appSecret" id="appSecret" placeholder="请输入AppSecret" value="${curWx.appSecret}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                                <span class="input-group-addon">
                                    接口URL:
                                </span>
                            <input type="text" class="form-control" name="wxDomainUrl" id="wxDomainUrl" value="${curWx.wxDomainUrl}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group form-group">
                                <span class="input-group-addon">
                                    Token:
                                </span>
                            <input type="text" class="form-control" name="wxToken" id="wxToken" value="${curWx.wxToken}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <%@include file="/common/common_bs_head_js.jsp"%>
    <script type="text/javascript" src="<%=path%>/js/bwizard/bwizard.min.js"></script>
<!-- 图标选择器-->
<script>
    $("#wizard").bwizard({"backBtnText":"上一步","nextBtnText":"下一步",activeIndexChanged:function (data,selectPanl) {
        if(selectPanl.index==2){

        }
    }});
</script>
</body>
</html>
