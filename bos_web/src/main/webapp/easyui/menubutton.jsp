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
    <title>menubutton</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        function print() {
            alert('增加');
        }
    </script>
</head>

<body>
   <a data-options="menu:'#aaa', iconCls:'icon-help'" class="easyui-menubutton">用户管理</a>
<div id="aaa">
    <div data-options="iconCls:'icon-undo'" onclick="print()">增加</div>
    <div data-options="iconCls:'icon-reload'">删除</div>
    <div data-options="iconCls:'icon-reload'">修改</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-reload'">查询</div>
</div>
</body>
</html>
