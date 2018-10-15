<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/11 0011
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>datagrid的三种使用方式</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>
     <!-- 第一种静态html页面  -->
     <%--<table class="easyui-datagrid">--%>
         <%--<thead>--%>
             <%--<tr>--%>
                 <%--<th data-options="field:'id'">编号</th>--%>
                 <%--<th data-options="field:'name'">姓名</th>--%>
                 <%--<th data-options="field:'age'">年龄</th>--%>
             <%--</tr>--%>
         <%--</thead>--%>
         <%--<tbody>--%>
             <%--<tr>--%>
                 <%--<td>001</td><td>小红</td><td>20</td>--%>
             <%--</tr>--%>
             <%--<tr>--%>
                 <%--<td>002</td><td>小明</td><td>21</td>--%>
             <%--</tr>--%>
         <%--</tbody>--%>
     <%--</table>--%>

     <!-- 方式二:利用ajax动态请求获取json -->
     <table data-options="url:'${ pageContext.request.contextPath }/json/datagrid_data.json'" class="easyui-datagrid">
         <thead>
         <tr>
             <th data-options="field:'id'">编号</th>
             <th data-options="field:'name'">姓名</th>
             <th data-options="field:'name'">年龄</th>
             <th data-options="field:'address'">地址</th>
         </tr>
         </thead>
     </table>
     <hr/>

     <!-- 方式三:通过easyui DataDrid 控件的方式 -->
     <table id="mytable"></table>
     <script>
         function doAdd() {
             alert("添加");
         }
         function dodelete() {
             alert("删除");
         }

         $(function () {
             $("#mytable").datagrid({
                 columns:[[
                     {title:'编号',field:'id',checkbox:true},
                     {title:'姓名',field:'name'},
                     {title:'年龄',field:'age'},
                     {title:'地址',field:'address'},
                     {title:'性别',field:'sex'}
                 ]],
                 url:'${ pageContext.request.contextPath }/json/datagrid_data.json',
                //rownumbers:true,
                 singleSelect:true,
                 toolbar:[
                     {text:'添加',
                         iconCls:'icon-add',
                         //鼠标单击触发事件
                         handler:doAdd},
                     {text:"删除",iconCls:'icon-remove' ,handler:dodelete},
                     {text:"修改",iconCls:'icon-edit'},
                     {text:"查询",iconCls:'icon-serch'}
                 ],
                 pagination:true

             });
         });
     </script>
</body>
</html>
