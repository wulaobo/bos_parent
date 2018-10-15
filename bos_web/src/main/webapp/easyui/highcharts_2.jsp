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
    <title>higncharts_柱状图</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/modules/exporting.js"></script>
    <script>
        $(function () {
            $('#test').highcharts({
                title: {
                    text: '水果销量'
                },
                subtitle:{text:'------传智播客统计'},
                xAxis: {
                    categories: ['苹果', ' 橙', '梨', '香蕉', '李']
                },
                series: [{
                    type: 'column',
                    name: 'Jane',
                    data: [3, 2, 1, 3, 4]
                }, {
                    type: 'column',
                    name: 'John',
                    data: [2, 3, 5, 7, 6]
                }, {
                    type: 'column',
                    name: 'Joe',
                    data: [4, 3, 3, 9, 0]
                }]
            });
        });
    </script>
</head>
<body>
   <div id="test">

   </div>
</body>
</html>
