<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>部门管理</title>
	<%@include file="/common/common_bs_head_css.jsp"%>
	<link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
	<link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div id="toolbar">
	<ex:perm url="org/addOrganization">
		<button class="btn btn-default" type="button" onclick="addOrganization()">部门添加</button>
	</ex:perm>
</div>

<div>
	<table id="organizationTables" ></table>
</div>
<div class="modal fade" id="editOrg" tabindex="-1" role="dialog" aria-labelledby="editOrg">
	<div class="modal-dialog" role="document"  style="width: 800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="exampleModalLabel">编辑资源菜单</h4>
			</div>
			<div class="modal-body">
				<form id="editOrgForm">
					<input type="hidden" name="orgId" id="orgId">
					<input type="hidden" name="version" id="version">
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group form-group">
                              <span class="input-group-addon">
                                部门名称:
                              </span>
								<input type="text" class="form-control" name="orgName" id="orgName">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="input-group">
                              <span class="input-group-addon">
                                上级资源:
                              </span>
								<input type="hidden" id="orgPid" name="orgPid"/>
								<input type="text" class="form-control" aria-label="..." id="orgPname" name="orgPname" onclick="showOrgNode(); return false;"/>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
                              <span class="input-group-addon">
                                显示顺序:
                              </span>
								<input type="text" class="form-control" aria-label="..." id="orgSort" name="orgSort">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="input-group">
                              <span class="input-group-addon">
                                部门状态:
                              </span>
								<div class="controls">
									<div class="switch" tabindex="0">
										<input id="isValid" name="isValid" type="checkbox" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label for="orgDes" class="control-label">部门描述:</label>
						<textarea class="form-control" id="orgDes" name="orgDes"></textarea>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="editSubmit">确认</button>
			</div>
		</div>
	</div>
</div>
<!-- 公用下拉择树 -->
<div id="orgNodeContent" class="orgNodeContent" style="display:none; position: absolute;z-index:1059">
	<ul id="orgTree" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>

<!-- 部门授权 -->
<div class="modal fade" id="editorOrgPermission" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="modaltitle"><i class="fa fa-envelope-o"></i> 部门授权</h4>
			</div>
			<div id="auto-div" style="height: 400px;">
				<ul id="orgPermissionTree" class="ztree" style="width: 280px;"></ul>
			</div>
			<div class="modal-footer clearfix">
				<button type="button" class="btn btn-danger" onclick="closeOrgPermiss();"><i class="fa fa-times"></i> 取消</button>
				<button type="button" class="btn btn-primary pull-left" onclick="saveOrgPermiss();"><i class="fa fa-envelope"></i>提交</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<!-- 图标选择器-->
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker-iconset-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/jquery.treegrid.extension.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/bootstrap_treetable.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/form.js"></script>
<script type="text/javascript">
	//用于缓存资源表格数据
	var rowDatas=new Map();
	//编辑部门类型 0 新增 1 修改
	var editOrgType=0;
	var orgPermissionTreeObj;
	var treeColumns=[
		{checkbox: true},
		{
			field: 'orgName',
			title: '部门名称',
			formatter:function(value,row,index){
				let html='';
				if(row.icon){
					html='<i class="fa '+row.icon+'">';
				}
				html+=row.orgName;
				return html;
			}
		},
		{
			field: 'orgDes',
			title: '部门描述'
		},
		{
			field: 'isValid',
			title: '状态',
			align : 'center',
			valign : 'middle',
			width:120,
			formatter:function(value,row,index){
				rowDatas.set(row.orgId,row);
				if(value==0){
					return "启用";
				}else if(value==1){
					return "停用";
				}else{
					return "未知"
				}
			}
		},
		{
			field: 'permission',
			title: '权限',
			align : 'center',
			valign : 'middle',
			width:120,
			formatter:function(value,row,index){
				let html='';
				<ex:perm url="org/reqOrgPermission">
					html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorPermission(\''+row.orgId+'\')"">授权</button>';
				</ex:perm>
				return html;
			}
		},
		{
			field: 'CreateTime',
			title: '操作',
			align : 'center',
			valign : 'middle',
			width:240,
			formatter:function(value,row,index){
				let html='';
				<ex:perm url="org/editorOrgPermission">
					html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorOrganization(\''+row.orgId+'\',\''+row.orgPid+'\')"">编辑</button>&nbsp;&nbsp;';
				</ex:perm>
				<ex:perm url="org/removeOrg">
					html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.orgId+'\',this)">删除</button>';
				</ex:perm>
				return html;
			}
		}
	]

	$(function () {
		createTreeTable("/org/queryOrganizationLists","organizationTables","orgId","orgName","orgId","orgPid",treeColumns)
		//初始华开关选择器
		$("#editOrgForm #isValid").bootstrapSwitch();
		$('#editOrg').on('hide.bs.modal', function () {
			//清除数据
			clearFormData();
			//隐藏下拉菜单
			hideOrgTree();
			clearFormValidation("editOrgForm",formValidater)
		});
		//关闭弹窗 销毁校验，再重新添加校验
		$('#editorOrgPermission').on('hide.bs.modal', function () {
			editorOrgPermissionId=null;
		});

		//编辑按钮提交操作
		$("#editSubmit").click("click",function(e){
			//开启校验
			$('#editOrgForm').data('bootstrapValidator').validate();
			//判断校验是否通过
			if(!$('#editOrgForm').data('bootstrapValidator').isValid()){
				return;
			}
			var editSubmitIndex = layer.load(2);
			var sendData=new Array();
			var formData=$("#editOrgForm").serializeObject();
			var flag =$("#editOrgForm #isValid").bootstrapSwitch("state");
			if(flag){
				formData["isValid"]=0;
			}else{
				formData["isValid"]=1;
			}
			let url="/org/addOrganization";
			if(editOrgType==1){
				url="/org/editOrganization";
			}
			//取得form表单数据
			$.ajax({
				type:"post",
				url:url,
				dataType: "json",
				data:formData,
				success:function(data){
					//关闭
					layer.close(editSubmitIndex);
					if(data.status==0){
						//显示提示
						layer.msg(data.msg);
						//刷新数据
						refreshTableData();
						//关闭弹窗
						$('#editOrg').modal('hide')
					}else{
						layer.msg(data.msg);
					}
				}
			});
		});
		//初始化教研
		formValidater();
	});
	function formValidater(){
		$('#editOrgForm')
				.bootstrapValidator({
					message: 'This value is not valid',
					//live: 'submitted',
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
						orgName: {
							message: '部门名称校验未通过',
							validators: {
								notEmpty: {
									message: '部门名称不能为空'
								},
								stringLength: {
									min: 6,
									max: 30,
									message: '部门名称长度在6-30之间'
								}
							}
						}
					}
				});
	}
	/**
	 * 刷新表格数据
	 **/
	function refreshTableData() {
		$('#organizationTables').bootstrapTable(
				"refresh",
				{
					url:"/org/queryOrganizationLists"
				}
		);
	}
	//删除确认框
	function deleteData(orgId){
		//询问框
		layer.confirm('确定要删除选中的数据吗？', {
			btn: ['确认','取消'] //按钮
		}, function(){
			removeOrg(orgId);
		}, function(){
		});
	}
	/**
	 * 删除部门
	 **/
	function removeOrg(orgId){
		$.ajax({
			type:"post",
			url:'/org/removeOrg',
			dataType: "json",
			data:{orgId:orgId},
			success:function(data){
				if(data.status==0){
					layer.msg("删除成功");
					refreshTableData();
				}else{
					layer.msg(data.msg);
				}
			}
		});
	}


	var zNodes;
	function editorOrganization(orgId,orgPid){
		editOrgType=1;
		reloadOrgTree(orgId,orgPid);
		initFormData(orgId);
        $("#exampleModalLabel").text("编辑部门");
		$('#editOrg').modal("show")
	}
	/**
	 * 清除form 表单数据
	 * */
	function clearFormData(){
		$("#editOrgForm #orgId").val("");
		$("#editOrgForm #version").val("");
		$("#editOrgForm #orgName").val("");
		$("#editOrgForm #orgPid").val("");
		$("#editOrgForm #orgPname").val("");
		$("#editOrgForm #orgSort").val("");
		$("#editOrgForm #isValid").val("");
		$("#editOrgForm #orgDes").val("");
	}
	/**
	 * 清除form 表单数据
	 * */
	function initFormData(key){
		var rowData=rowDatas.get(parseInt(key,10));
		if(rowData){
			$("#editOrgForm #orgId").val(rowData.orgId);
			$("#editOrgForm #version").val(rowData.version);
			$("#editOrgForm #orgName").val(rowData.orgName);
			$("#editOrgForm #orgPid").val(rowData.orgPid);
			$("#editOrgForm #orgPname").val(rowData.orgPname);
			$("#editOrgForm #orgSort").val(rowData.orgSort);
			$("#editOrgForm #orgDes").val(rowData.orgDes);

			var flag =false;
			if(rowData.isValid==0){
				flag=true;
			}
			$("#editOrgForm #isValid").bootstrapSwitch("state",flag);

			let parObj=rowDatas.get(rowData.orgPid);
			if(parObj){
				$("#editOrgForm #orgPid").val(parObj.orgId);
				$("#editOrgForm #orgPname").val(parObj.orgName);
			}else{
				$("#editOrgForm #orgPid").val("");
				$("#editOrgForm #orgPname").val("顶端节点");
			}
		}
	}
	/**
	 * 编辑部门
	 **/
	function addOrganization(){
		editOrgType=0;
        let orgId,orgPid;
        var a= $("#organizationTables").bootstrapTable('getSelections');
        if(a&&a.length>0){
            orgId=a[0].orgId;
            orgPid=a[0].orgPid;
        }
		reloadOrgTree(orgId,orgPid);
		initFormData();
        let parObj=rowDatas.get(orgId);
        if(parObj) {
            $("#editOrgForm #orgPid").val(parObj.orgId);
            $("#editOrgForm #orgPname").val(parObj.orgName);
        }
		$("#editOrgForm #isValid").bootstrapSwitch("state",true);
        $("#exampleModalLabel").text("添加部门");
		$('#editOrg').modal("show")
	}
	/**
	 * 重新加载树型结构
	 **/
	function reloadOrgTree(orgId,orgPid){
		$.fn.zTree.init($("#orgTree"), setting, zNodes);
		var rowData=rowDatas.get(parseInt(orgId,10));
		$.ajax({
			type:"post",
			url:'/org/queryOrgNode',
			dataType: "json",
			data:{orgId:orgId},
			async:false,
			success:function(data){
				let d=data.data;
				var nodeData=new Array();
                let firstNode=new Object();
                firstNode.name="顶端节点";
                nodeData.push(firstNode);
				for(row of d){
					let treeRow=new Object();
					treeRow.id=row.orgId;
					treeRow.pId=row.orgPid;
					treeRow.name=row.orgName;
					nodeData.push(treeRow);
				}
				var zTree=$.fn.zTree.init($("#orgTree"), setting, nodeData);
				var node = zTree.getNodeByParam("id",orgPid);
				if(node){
					zTree.selectNode(node);
				}
			}
		});
	}
	var editorOrgPermissionId;
	/**
	 * 编辑部门权限
	 **/
	function editorPermission(orgId){
		editorOrgPermissionId=orgId;
		$.ajax({
			type:"post",//请求方式
			url:"/org/reqOrgPermission",//发送请求地址
			dataType:"json",
			data:{//发送给数据库的数据
				orgId:orgId
			},
			//请求成功后的回调函数有两个参数
			success:function(data,textStatus){
				if(data.status==0){
					let zNodes = data.data;
					orgPermissionTreeObj = $.fn.zTree.init($("#orgPermissionTree"), orgPermissionTreeSetting, zNodes);
					orgPermissionTreeObj.expandAll(true);
				}else{
					alert(data.msg);
				}
			},error:function(data,textStatus){
				alert(data);
			}
		});
		$('#editorOrgPermission').modal('show')
	}
	//授权
	function saveOrgPermiss(){
		var note = orgPermissionTreeObj.getCheckedNodes();
		var resIds="";
		if(note!=null&&note!==''){
			for ( var i = 0; i < note.length; i++) {
				resIds+=note[i].id;
				if(i!=note.length-1){
					resIds+=",";
				}
			}
		}
		$.ajax({
			type:"post",//请求方式
			url:"/org/editorOrgPermission",//发送请求地址
			dataType:"json",
			data:{//发送给数据库的数据
				orgId:editorOrgPermissionId,
				resIds:resIds
			},
			//请求成功后的回调函数有两个参数
			success:function(data,textStatus){
				if(data.status==0){
					layer.msg("权限添加成功");
					$('#editorOrgPermission').modal('hide')
				}else{
					alert(data.msg);
				}
			}
		});
	}
	function closeOrgPermiss(){
		$('#editorOrgPermission').modal('hide')
	}
	var orgPermissionTreeSetting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	/***********************************************************************************/
	var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};

	function beforeClick(treeId, treeNode) {
		return true;
	}

	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree"),
				nodes = zTree.getSelectedNodes(),
				v = "",vId="";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name;
			vId += nodes[i].id;
		}
		var orgPname = $("#orgPname");
		var orgPid_ = $("#orgPid");
		orgPname.val(v);
		orgPid_.val(vId);
		hideOrgTree();
	}

	function showOrgNode() {
		var cityObj = $("#orgPname");
		var cityOffset = $("#orgPname").offset();
		$("#orgNodeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideOrgTree() {
		$("#orgNodeContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "orgNodeContent" || $(event.target).parents("#orgNodeContent").length>0)) {
			hideOrgTree();
		}
	}
</script>
</body>
</html>
