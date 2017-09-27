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
			beforeExpand: beforeExpand,
			onExpand: onExpand,
			onClick: onClick
		}
	};
	//当前展开节点
	var curExpandNode = null;
	//单一展开节点
	function singlePath(newNode) {

		if (newNode === curExpandNode) return;
		var zTree = $.fn.zTree.getZTreeObj("index_left_tree"),
                rootNodes, tmpRoot, i, j, n;
		var tmpTId="";
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
		menuResize();
	}
	function onClick(e,treeId, treeNode) {
		menuResize();
		if(treeNode.path){
			curExpandNode = treeNode;
			addTabs(treeNode.id,treeNode.name, base_path_url+treeNode.path);
		}else{
			zTree_Menu.expandNode(treeNode, null, null, null, true);
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
		zTree_Menu.expandNode(treeNode);
		return true;
	}
	function beforeExpand(treeId, treeNode) {
		/* var pNode = curExpandNode ? curExpandNode.getParentNode():null;
		var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
		for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
			if (treeNode !== treeNodeP.children[i]) {
				zTree_Menu.expandNode(treeNodeP.children[i], false);
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
		}  */
		menuResize();
	}
	var d1;
	$(document).ready(function() {
		onLoadMenu();
		var treeObj = $("#index_left_tree");
		$.fn.zTree.init(treeObj, setting, dl);
		zTree_Menu = $.fn.zTree.getZTreeObj("index_left_tree");

		treeObj.addClass("showIcon");
		//设置左边导航栏事件
		var c = $('#index_all');
        var p = c.layout('panel','west');
        p.panel({onResize:function(w,h){
        	menuResize();
        }});
        //重新测试左边菜单栏高度与宽度
        menuResize();
    	//中间部分tab
    	$('#tabs').tabs({
    		border:false,
    		fit:true,
    		onSelect: function(title, index){
    			var pp = $('#tabs').tabs('getSelected');
    			var id = pp.panel('options').id; // 相应的标签页（tab）对象
    			if(id){
    				var node = zTree_Menu.getNodeByParam("id", id);
        			zTree_Menu.selectNode(node);
    			}
    		},
    	});
	});
	function menuResize(){
		var w=$("#leftMenus").width();
		var h=$("#leftMenus").height();
		var treeWidth = w;
		var treeNodeWidth=treeWidth-6;
		$("#index_left_tree").width(treeWidth);
		$("#index_left_tree").height(h-2);
		$(".ztree li a").width(treeNodeWidth);
		$(".curSelectedNode").width(treeNodeWidth);
		$(".ztree li a .curSelectedNode").width(treeNodeWidth);
	}
	function onLoadMenu(){
		$.ajax({
			type:"post",
			url:base_path_url+'menu/userMenus',
			dataType: "json",
			async:false,
			success:function(data){
				if(data){
					if(data.status==0){
						dl=data.data;
					}else if(data.status==3){
						alert(data.msg);
						window.location=data.data;
					}
				}
			}
			// ,
			// dataFilter:function(data){
			// 	dl=$.parseJSON(data);
			// }
		});
	}
	//添加一个选项卡面板
	function addTabs(id,title, url, icon){
		//取得所有的tabs页签
		var t =$('#tabs').tabs("tabs");
		for (var i = 0; i < t.length; i++) {
			var tab = t[i];
			//判断是否己经创建了页签
			if (tab.panel("options").id==id) {
				$('#tabs').tabs('select', i);
				//选中页签
				return;
			}
		}
		//创建tabs页签
		$('#tabs').tabs('add',{
			title:title,
			id:id,
			content:'<iframe src="'+url+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',
			closable:true
		});
	}
	//系统时间显示
	setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
