<%--
  Created by IntelliJ IDEA.
  User: Kris
  Date: 2016/5/18
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
            text: '设备数据'
        },
        tooltip: {},
        legend: {
            data: ['使用中', '测试中', '维修中', '已安装', '总数'],
            selectedMode: 'multiple',
            selected: {
                '使用中': false,
                '测试中': false,
                '维修中': false,
                '已安装': false
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
            name: '使用中',
            type: 'bar',
            data: []
        }, {
            name: '测试中',
            type: 'bar',
            data: []
        }, {
            name: '维修中',
            type: 'bar',
            data: []
        }, {
            name: '已安装',
            type: 'bar',
            data: []
        }, {
            name: '总数',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var working = [];
    var testing = [];
    var repairing = [];
    var installed = [];
    var total = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "device"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    working.push(result[i].working);
                    testing.push(result[i].testing);
                    repairing.push(result[i].repairing);
                    installed.push(result[i].installed);
                    total.push(result[i].totalDevices);

                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: '使用中',
                        data: working
                    },
                        {
                            name: '测试中',
                            data: testing
                        }, {
                            name: '维修中',
                            data: repairing
                        }, {
                            name: '已安装',
                            data: installed
                        }, {
                            name: '总数',
                            data: total
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