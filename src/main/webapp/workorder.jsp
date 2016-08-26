<%--
  Created by IntelliJ IDEA.
  User: Kris
  Date: 2016/6/7
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="jquery-2.2.3.min.js"></script>
    <script src="echarts.min.js"></script>
    <title>全日通数据分析</title>
</head>

<body>
<div id="buttons"></div>
<div id="main" style="width: 1200px;height:900px;"></div>

<script type="text/javascript">

    var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
        title: {
            text: '工单数据'
        },
        tooltip: {},
        legend: {
            data: ['新工单', '完成工单', '遗留工单'],
            selectedMode: 'multiple',
            selected: {
                '完成工单': false,
                '遗留工单': false
            }
        },
        dataZoom: [
            {
                type: 'slider',
                xAxisIndex: 0,
                start: 0,
                end: 100
            },
            {
                type: 'slider',
                yAxisIndex: 0,
                start: 0,
                end: 100
            }
        ],
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '新工单',
            type: 'bar',
            data: []
        }, {
            name: '完成工单',
            type: 'bar',
            data: []
        }, {
            name: '遗留工单',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var orders_plus = [];
    var orders_minus = [];
    var orders = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "workorder"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    orders_plus.push(result[i].newOrders);
                    orders_minus.push(result[i].finishedOrders);
                    orders.push(result[i].remainingOrders);
                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: '新工单',
                        data: orders_plus
                    },
                        {
                            name: '完成工单',
                            data: orders_minus
                        }, {
                            name: '遗留工单',
                            data: orders
                        }

                    ],
                    dataZoom: [
                        {
                            type: 'slider',
                            xAxisIndex: 0,
                            start: 0,
                            end: 100
                        },
                        {
                            type: 'slider',
                            yAxisIndex: 0,
                            start: 0,
                            end: 100
                        }
                    ]
                });

            }

        },
        error: function (errorMsg) {
            alert(errorMsg);
            myChart.hideLoading();
        }
    })


</script>

</body>
</html>