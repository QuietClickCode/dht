<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%--<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>--%>
<html>
<head>
<TITLE>ZTREE DEMO - left_menu for Outlook</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="js/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/ztree/jquery.ztree.core.min.js"></script>
<link rel="stylesheet" href="index/css/leftmenu.css" type="text/css">
<SCRIPT type="text/javascript">
	var curMenu = null, zTree_Menu = null;
	var setting = {
		view : {
			showLine : false,
			showIcon : false,
			selectedMulti : false,
			dblClickExpand : false,
			addDiyDom : addDiyDom
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			//beforeClick : beforeClick,
			beforeExpand: beforeExpand,
			onExpand: onExpand,
			onClick: onClick
		}
	};

	var curExpandNode = null;
	function singlePath(newNode) {
		if (newNode === curExpandNode) return;

        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                rootNodes, tmpRoot, tmpTId, i, j, n;

        if (!curExpandNode) {
            tmpRoot = newNode;
            while (tmpRoot) {
                tmpTId = tmpRoot.tId;
                tmpRoot = tmpRoot.getParentNode();
            }
            rootNodes = zTree.getNodes();
            for (i=0, j=rootNodes.length; i<j; i++) {
                n = rootNodes[i];
                if (n.tId != tmpTId) {
                    zTree.expandNode(n, false);
                }
            }
        } else if (curExpandNode && curExpandNode.open) {
			if (newNode.parentTId === curExpandNode.parentTId) {
				zTree.expandNode(curExpandNode, false);
			} else {
				var newParents = [];
				while (newNode) {
					newNode = newNode.getParentNode();
					if (newNode === curExpandNode) {
						newParents = null;
						break;
					} else if (newNode) {
						newParents.push(newNode);
					}
				}
				if (newParents!=null) {
					var oldNode = curExpandNode;
					var oldParents = [];
					while (oldNode) {
						oldNode = oldNode.getParentNode();
						if (oldNode) {
							oldParents.push(oldNode);
						}
					}
					if (newParents.length>0) {
						zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
					} else {
						zTree.expandNode(oldParents[oldParents.length-1], false);
					}
				}
			}
		}
		curExpandNode = newNode;
	}
	function onExpand(event, treeId, treeNode) {
		curExpandNode = treeNode;
	}
	function onClick(e,treeId, treeNode) {
		if(treeNode.path){
			return;
		}else{
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode, null, null, null, true);
		}
	}
	function addDiyDom(treeId, treeNode) {
		var spaceWidth = 5;
		var switchObj = $("#" + treeNode.tId + "_switch"), icoObj = $("#"
				+ treeNode.tId + "_ico");
		switchObj.remove();
		icoObj.before(switchObj);

		if (treeNode.level > 1) {
			var spaceStr = "<span style='display: inline-block;width:"
					+ (spaceWidth * treeNode.level) + "px'></span>";
			switchObj.before(spaceStr);
		}
	}

	function beforeClick(treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		return true;
		//if (treeNode.level == 0) {
		//	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//	zTree.expandNode(treeNode);
		//	return false;
		//}
		//return true;
	}
	function beforeExpand(treeId, treeNode) {
		var pNode = curExpandNode ? curExpandNode.getParentNode():null;
		var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
			if (treeNode !== treeNodeP.children[i]) {
				zTree.expandNode(treeNodeP.children[i], false);
			}
		}
		while (pNode) {
			if (pNode === treeNode) {
				break;
			}
			pNode = pNode.getParentNode();
		}
		if (!pNode) {
			singlePath(treeNode);
		}

	}
	var d1;
	$(document).ready(function() {
		onLoadMenu();
		var treeObj = $("#treeDemo");
		$.fn.zTree.init(treeObj, setting, dl);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		//curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
		//zTree_Menu.selectNode(curMenu);

		treeObj.hover(function() {
			if (!treeObj.hasClass("showIcon")) {
				treeObj.addClass("showIcon");
			}
		}, function() {
			//treeObj.removeClass("showIcon");
		});
	});

	function onLoadMenu(){
		$.ajax({
			type:"post",
			url:'menu/userMenus',
			dataType: "json",
			async:false,
			success:function(data){
				alert(data);
			}
//			,
//			dataFilter:function(data){
//				console.log(data)
//				dl=$.parseJSON(data);
//			}
		});
	}

</SCRIPT>
<style type="text/css">
.ztree * {
	font-size: 10pt;
	font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Web Light",
		"Segoe UI Light", "Segoe UI Web Regular", "Segoe UI",
		"Segoe UI Symbol", "Helvetica Neue", Arial
}

.ztree li ul {
	margin: 0;
	padding: 0
}

.ztree li {
	line-height: 30px;
}

.ztree li a {
	width: 400px;
	height: 30px;
	padding-top: 0px;
}

.ztree li a:hover {
	text-decoration: none;
	background-color: #E7E7E7;
}

.ztree li a span.button.switch {
	visibility: hidden
}

.ztree.showIcon li a span.button.switch {
	visibility: visible
}

.ztree li a.curSelectedNode {
	background-color: #D4D4D4;
	border: 0;
	height: 30px;
}

.ztree li span {
	line-height: 30px;
}

.ztree li span.button {
	margin-top: -7px;
}

.ztree li span.button.switch {
	width: 16px;
	height: 16px;
}

.ztree li a.level0 span {
	font-size: 150%;
	font-weight: bold;
}

.ztree li span.button {
	background-image: url("./index/css/left_menuForOutLook.png");
	*background-image: url("./index/css/left_menuForOutLook.gif")
}

.ztree li span.button.switch.level0 {
	width: 20px;
	height: 20px
}

.ztree li span.button.switch.level1 {
	width: 20px;
	height: 20px
}

.ztree li span.button.noline_open {
	background-position: 0 0;
}

.ztree li span.button.noline_close {
	background-position: -18px 0;
}

.ztree li span.button.noline_open.level0 {
	background-position: 0 -18px;
}

.ztree li span.button.noline_close.level0 {
	background-position: -18px -18px;
}
</style>
</head>
<body>
	<h1>OutLook 样式的左侧菜单</h1>
<h6>[ 文件路径: super/left_menuForOutLook.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left" style="width: 230px;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>实现方法说明</h2>
				<ul class="list">
				<li>帮朋友用 zTree 实现了一个貌似 Outlook.com 的菜单，特拿出来分享给大家</li>
				<li class="highlight_red">1、请注意本页面源码中的 css 部分</li>
				<li class="highlight_red">2、请查看源码中 js 的使用，特别是利用 addDiyDom 回调将 展开按钮 转移到 &lt;a&gt; 标签内</li>
				<li class="highlight_red">3、利用添加 zTree 的 class 实现展开按钮的隐藏、显示</li>
				<li>4、其他辅助规则，请根据实际情况自行编写</li>
				<li>5、当前规则说明:<br/>
				&nbsp;&nbsp;单击根节点可以展开、折叠;<br/>
				&nbsp;&nbsp;非根节点只有点击 箭头 图标才可以展开、折叠;<br/>
				</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>
