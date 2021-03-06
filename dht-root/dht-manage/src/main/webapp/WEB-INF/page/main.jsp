﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@include file="/common/common_head_css.jsp"%>
  <title>后台管理系统模板</title>
<link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" href="<%=path %>/index/css/menu.css" type="text/css">
</head>

<body class="easyui-layout" id="index_all">
<!-- 头部标题 -->
<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#F3F3F3">
	<a href="www.solooo.net"><span class="northTitle">www.solooo.net</span></a>
    <span class="loginInfo">登录用户：admin&nbsp;&nbsp;姓名：管理员&nbsp;&nbsp;角色：系统管理员</span>
</div>

<!-- 左侧导航 -->
<div data-options="region:'west',split:true,title:'导航菜单', fit:false" style="width:200px;" id="leftMenus">
  <ul id="index_left_tree" class="ztree"></ul>
</div>

<!-- 页脚信息 -->
<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
	<span id="sysVersion">系统版本：V1.0</span>
    <span id="nowTime"></span>
</div>

<!-- 内容tabs -->
<div id="center" data-options="region:'center'">
  <div id="tabs" class="easyui-tabs">
    <div title="首页" style="padding:5px;display:block;" class="fa-home">
      <p>模板说明：</p>
        <ul>
          <li>主界面使用 easyui1.3.5</li>
          <li>导航树使用 JQuery-zTree-v3.5.15</li>
        </ul>
      <p>特性说明：</p>
        <ul>
          <li>所有弹出框均显示在顶级父窗口</li>
          <li>修改easyui window拖动，移动时显示窗口而不显示虚线框，并限制拖动范围</li>
        </ul>
    </div>
  </div>
</div>

<!-- 用于弹出框 -->
<div id="parent_win"></div>
<%@include file="/common/common_head_js.jsp"%>
<script type="text/javascript" src="<%=path %>/index/main.js"></script>
<script type="text/javascript" src="<%=path %>/index/extends.js"></script>
<script type="text/javascript" src="<%=path %>/index/common.js"></script>
<script type="text/javascript" src="<%=path %>/js/ztree/jquery.ztree.core.min.js"></script>
</body>
</html>
