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
    <title>message</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            //提示框
            //$.messager.alert("这是标题", "这是内容", "info");
            //对话框
//            $.messager.confirm("确认对话框", "你想要退出该系统吗?", function (a) {
//                alert(a);
//            });
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    alert('确认删除');
                }
            });
        });

            //欢迎框
//            $.messager.show({
//                title:'欢迎光临',
//                msg:'欢迎[admin] 登录。',
//                timeout:3000,
//                showType:'slide'
//
//            });
//        });
    </script>
</head>

<body>
</body>
</html>
