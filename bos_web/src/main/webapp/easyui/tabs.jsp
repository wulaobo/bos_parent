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
    <title>tabs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
   <div title="xxx管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
   <div style="width: 100px" data-options="region:'east'">东部区域</div>
   <div data-options="region:'center'">
       <!-- 制作选项卡面板 -->
       <div id="mytabs" class="easyui-tabs" data-options="fit:true">
           <div  data-options="iconCls:'icon-save',closable:true" title="面板一">111</div>
           <div data-options="iconCls:'icon-edit',closable:true" title="面板二">222</div>
           <div data-options="iconCls:'icon-cut',closable:true" title="面板三">333</div>
       </div>
   </div>
   <div title="系统菜单" style="width: 200px" data-options="region:'west'">
       <!-- 折叠面板 -->
       <div class="easyui-accordion" data-options="fit:true">
           <div data-options="iconCls:'icon-save'" title="面板一">
               <%--<input type="button" value="添加一个选项卡">--%>
               <a id="aaa" class="easyui-linkbutton">添加一个选项卡</a>
               <script type="text/javascript">
                   $(function () {
                       $("#aaa").click(
                           function () {
                               var e  = $("#mytabs").tabs("exists","面板四");
                               if(e) {
                                   $("#mytabs").tabs("select","面板四");
                               }else{
                                   //动态添加选项卡
                                   $("#mytabs").tabs("add",{
                                       title:'面板四',
                                       closable:true,
                                       content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
                                   });
                               }

                           }
                       );
                   });
               </script>
           </div>
           <div data-options="iconCls:'icon-edit'" title="面板二">222</div>
           <div data-options="iconCls:'icon-cut'" title="面板三">333</div>
       </div>
   </div>
   <div style="height: 100px" data-options="region:'south'">南部区域</div>

</body>
</html>
