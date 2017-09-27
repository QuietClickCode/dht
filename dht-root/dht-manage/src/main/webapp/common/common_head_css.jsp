<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link rel="stylesheet" type="text/css" href="<%=path %>/index/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui/themes/icon.css" />
<script type="text/javascript">
	var base_path_url="<%=path%>/";
</script>
