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
    <title>datagrid的编辑功能</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>

     <!-- 方式三:通过easyui DataDrid 控件的方式 -->
     <table id="mytable"></table>
     <script>
         $(function () {
             //定义全局变量
             var myindex = -1;

             $("#mytable").datagrid({
                 //数据表格列属性
                 columns:[[
                     {width:100,title:'编号',field:'id',checkbox:true},
                     {width:100,title:'姓名',field:'name',editor:{
                         type:'validatebox',
                         options:{}
                     }},
                     {width:100,title:'年龄',field:'age',editor:{
                         type:'numberbox',
                         options:{}

                     }},
                     {width:100,title:'日期',field:'address',editor:{
                         type:'datebox',
                         options:{}

                     }},
                     {width:100,title:'性别',field:'sex',editor:{
                         type:'validatebox',
                         options:{}

                     }}
                 ]],
                 url:'${ pageContext.request.contextPath }/json/datagrid_data.json',
                 rownumbers:true,
                 singleSelect:true,
                 toolbar:[
                     {text:'添加',
                         iconCls:'icon-add',
                         //鼠标单击触发事件
                         handler:function () {
                             //插入一条数据
                             $("#mytable").datagrid("insertRow",{
                                 index:0,
                                 row:{}
                             });
                             myindex = 0;
                             $("#mytable").datagrid("beginEdit",myindex);
                         }},
                     {text:"删除",iconCls:'icon-remove' ,handler:function () {
                         var row = $("#mytable").datagrid("getSelected");
                         myindex = $('#mytable').datagrid("getRowIndex",row);
                         $("#mytable").datagrid("deleteRow",myindex);
                         $.post();
                     }},
                     {text:"修改",iconCls:'icon-edit',handler:function () {
                         var row = $("#mytable").datagrid("getSelected");
                        // alert(row.name);
                         myindex = $('#mytable').datagrid("getRowIndex",row);
                         $("#mytable").datagrid("beginEdit",myindex);
                     }},
                     {text:"保存",iconCls:'icon-save',handler:function () {
                         $("#mytable").datagrid("endEdit",myindex);
                     }}
                 ],
                 pagination:true,
                 //用于监听结束编编辑事件
                 onAfterEdit:function (index,data,changes) {
                     console.info(data);
                     alert("结束编辑了"+data.name);
                     $.post();
                 }
             });
         });
     </script>
</body>
</html>
