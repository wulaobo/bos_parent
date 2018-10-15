<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/7 0007
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layout布局页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
   <div title="xxx管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
   <div style="width: 100px" data-options="region:'east'">东部区域</div>
   <div data-options="region:'center'">中部区域</div>
   <div title="菜单栏" style="width: 100px" data-options="region:'west'">西部区域</div>
   <div style="height: 100px" data-options="region:'south'">南部区域</div>

</body>
</html>
