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
    <title>ztree</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>

<body class="easyui-layout">
   <div title="xxx管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
   <div style="width: 100px" data-options="region:'east'">东部区域</div>
   <div data-options="region:'center'">
       <!-- 制作选项卡面板 -->
       <div id="mytabs" class="easyui-tabs" data-options="fit:true">
           <div data-options="closable:true" data-options="iconCls:'icon-save'" title="面板一">111</div>
           <div data-options="closable:true" data-options="iconCls:'icon-edit'" title="面板二">222</div>
           <div data-options="closable:true" data-options="iconCls:'icon-cut'" title="面板三">333</div>
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
           <div data-options="iconCls:'icon-edit'" title="面板二">
               <!-- 构造树形菜单 采用标准json-->
               <ul id="tree1" class="ztree"></ul>
               <script type="text/javascript">
                   $(function () {
                          var setting = {};
                          //构造节点数
                          var zNodes = [
                                  {"name":"节点一" ,"children":[
                                      {"name":"节点一_1" },
                                      {"name":"节点一_2" },
                                      {"name":"节点一_3" }
                                  ]},
                                  {"name":"节点二" },
                                  {"name":"节点三" },
                                  {"name":"节点四" }
                              ];
                       //初始化后.创造这些节点
                       $.fn.zTree.init($("#tree1"),setting,zNodes);

                   });
               </script>
           </div>
           <div data-options="iconCls:'icon-cut'" title="面板三">
               <!-- 构造树形菜单 采用简单json -->
               <ul id="tree2" class="ztree"></ul>
               <script type="text/javascript">
                   $(function () {
                       var setting2 = {
                           data: {
                               simpleData: {
                                   enable: true //采用简单json构造树形菜单
                               }
                           }
                       };
                       //构造节点数
                       var zNodes2 = [
                           {"id":"1", "pId":"0", "name":"节点一" },
                           {"id":"2", "pId":"1", "name":"节点二" },
                           {"id":"3", "pId":"2", "name":"节点三" }
                       ];
                       //调用API后,创造这些节点
                       $.fn.zTree.init($("#tree2"),setting2,zNodes2);

                   });
               </script>
           </div>

           <div data-options="iconCls:'icon-ok'" title="面板四">
               <!-- 构造树形菜单 通过发送ajax请求构造ztree树 -->
               <ul id="tree3" class="ztree"></ul>
               <script type="text/javascript">
                   $(function () {
                       var setting3 = {
                           data: {
                               simpleData: {
                                   enable: true //采用简单json构造ztree树
                               }
                           },
                           callback: {
                               onClick: function (event, treeId, treeNode) {
                                  //alert(treeNode.page);
                                  if(treeNode.page != undefined) {
                                      var e = $("#mytabs").tabs("exists",treeNode.name);
                                      if(e) {
                                          $("#mytabs").tabs("select",treeNode.name);
                                      }else {
                                          $("#mytabs").tabs("add", {
                                              title: treeNode.name,
                                              closable: true,
                                              content: '<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
                                          });
                                      }
                                  }
                               }
                           }

                       };

                       //发送ajax请求
                       var url= "${pageContext.request.contextPath}/json/menu.json";
                       $.post(url,function(data){
                           //调用API后,创造这些节点
                           $.fn.zTree.init($("#tree3"),setting3,data);

                       },"json");
                   });
               </script>
           </div>
       </div>
   </div>
   <div style="height: 100px" data-options="region:'south'">南部区域</div>

</body>
</html>
