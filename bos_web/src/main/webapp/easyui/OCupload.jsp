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
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
    <script>
        $(function () {
            //页面加载完成之后,调用upload方法,动态修改了html元素.
            $("#myButton").upload({
                action:'xxx',
                name:'MyFile'
            });
        });
    </script>
</head>
<body>
    <%--<iframe style="display :none" name="abc"></iframe>--%>
    <%--<form target="abc" action="xxx" enctype="multipart/form-data" method="post">--%>
        <%--<input type="file" name="myfile">--%>
        <%--<input type="submit" name="文件上传">--%>
    <%--</form>--%>
   <input id="myButton" type="button" value="一键上传">
<form action="${ pageContext.request.contextPath }/RegionAction_upload.action" enctype="multipart/form-data" method="post">
    <input type="file" name="myFile"><br/>
    <input type="submit" value="文件上传">
</form>
</body>
</html>
