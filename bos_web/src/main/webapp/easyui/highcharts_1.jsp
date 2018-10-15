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
    <title>higncharts_饼状图</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/highcharts/modules/exporting.js"></script>
    <script>
        $(function () {
            $('#test').highcharts({
//                title: {
//                    text: '各浏览器份额'
//                },
//                series: [{
//                    type: 'pie',
//                    name: '浏览器占比',
//                    data: [
//                        ['Firefox',   45.0],
//                        ['IE',       26.8],
//                        ['Safari',    8.5],
//                        ['Opera',     6.2],
//                        ['Others',   0.7]
//                    ]
//                }]
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '2014 某网站各浏览器浏览量占比'
                },
                tooltip: {
                    headerFormat: '{series.name}<br>',
                    pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '浏览器访问量占比',
                    data: [
                        ['Firefox',   45.0],
                        ['IE',       26.8],
                        {
                            name: 'Chrome',
                            y: 12.8,
                            sliced: true,
                            selected: true
                        },
                        ['Safari',    8.5],
                        ['Opera',     6.2],
                        ['其他',   0.7]
                    ]
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
