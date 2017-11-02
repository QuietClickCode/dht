<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>资源管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <title>素材管理</title>
</head>
<body>
<div class="main_hd">    <h2>素材管理</h2>    <div class="title_tab" id="topTab"><ul class="tab_navs title_tab" data-index="0">

    <li data-index="0" class="tab_nav first js_top selected" data-id="media10"><a href="/cgi-bin/appmsg?begin=0&amp;count=10&amp;t=media/appmsg_list2&amp;type=10&amp;action=list_card&amp;token=2121049040&amp;lang=zh_CN">图文消息</a></li>
    <li data-index="1" class="tab_nav  js_top" data-id="media2"><a href="/cgi-bin/filepage?type=2&amp;begin=0&amp;count=12&amp;t=media/img_list&amp;token=2121049040&amp;lang=zh_CN">图片</a></li>
    <li data-index="2" class="tab_nav  js_top" data-id="media3"><a href="/cgi-bin/filepage?type=3&amp;begin=0&amp;count=21&amp;t=media/list&amp;token=2121049040&amp;lang=zh_CN">语音</a></li>
    <li data-index="3" class="tab_nav  js_top" data-id="media15"><a href="/cgi-bin/appmsg?begin=0&amp;count=9&amp;t=media/video_list&amp;action=list_video&amp;type=15&amp;token=2121049040&amp;lang=zh_CN">视频</a></li>
</ul>
</div></div>
</body>
</html>
