<%@ taglib prefix="ex" uri="http://www.zgrmdht.com/permission"%>
<%-- bootstrap 框架所依赖的公用样式包 --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<link rel="stylesheet" href="<%=path%>/main/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=path%>/main/css/bootstrap-table.min.css">
<link rel="stylesheet" href="<%=path%>/main/css/bootstrap-switch.css">
<link rel="stylesheet" href="<%=path%>/main/css/font-awesome.min93e3.css?v=4.4.0">
<link rel="stylesheet" href="<%=path%>/js/layer/theme/default/layer.css">
<link rel="stylesheet" href="<%=path%>/js/validate/css/bootstrapValidator.min.css"/>
<script type="text/javascript">
	var base_path_url="<%=path%>/";
</script>
