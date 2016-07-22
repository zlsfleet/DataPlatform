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
            text: '点位数据'
        },
        tooltip: {},
        legend: {
            data: ['已签约', '已勘察', '已施工', '已安装', '待拆除', '总数'],
            selectedMode: 'multiple',
            selected: {
                '已勘察': false,
                '已施工': false,
                '已安装': false,
                '待拆除': false,
                '总数': false
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
            name: '已签约',
            type: 'bar',
            data: []
        }, {
            name: '已勘察',
            type: 'bar',
            data: []
        }, {
            name: '已施工',
            type: 'bar',
            data: []
        }, {
            name: '已安装',
            type: 'bar',
            data: []
        }, {
            name: '待拆除',
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
    var signed = [];
    var surveyed = [];
    var worked = [];
    var installed = [];
    var to_remove = [];
    var total = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "spot"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    signed.push(result[i].signed);
                    surveyed.push(result[i].surveyed);
                    worked.push(result[i].worked);
                    installed.push(result[i].installed);
                    to_remove.push(result[i].toRemove);
                    total.push(result[i].totalSpots);

                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: '已签约',
                        data: signed
                    },
                        {
                            name: '已勘察',
                            data: surveyed
                        }, {
                            name: '已施工',
                            data: worked
                        }, {
                            name: '已安装',
                            data: installed
                        }, {
                            name: '待拆除',
                            data: to_remove
                        }, {
                            name: '总数',
                            data: total
                        }

                    ],
                    dataZoom: [
                        {
                            type: 'slider',
                            xAxisIndex: 0,
                            start: result.length - 31,
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