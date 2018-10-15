<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/16 0016
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>combobox</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>

</head>
<body>
    <%--<form action="xxx" method="get">--%>
        <%--<select>--%>
            <%--<option value="aaa">小红</option>--%>
            <%--<option value="bbb">小名</option>--%>
            <%--<option value="ccc">小刚</option>--%>
        <%--</select>--%>
        <%--<input type="submit" value="提交">--%>
    <%--</form>--%>
    <input id="cc"  data-options="valueField:'id',textField:'name',
          url:'${pageContext.request.contextPath}/json/combobox.json', editable:false"
         class="easyui-combobox">
    <hr/>

</body>
</html>
