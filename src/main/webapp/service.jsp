<%--
  Created by IntelliJ IDEA.
  User: Kris
  Date: 2016/7/5
  Time: 14:14
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
            text: '客服数据'
        },
        tooltip: {},
        legend: {
            data: ['外呼数', '呼入数', '黑名单数', '待验证数'],
            selectedMode: 'multiple',
            selected: {
                '外呼数': false,
                '黑名单数': false,
                '待验证数': false
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
            name: '外呼数',
            type: 'bar',
            data: []
        }, {
            name: '呼入数',
            type: 'bar',
            data: []
        }, {
            name: '黑名单数',
            type: 'bar',
            data: []
        }, {
            name: '待验证数',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var call_out = [];
    var call_in = [];
    var blacklist = [];
    var to_verify = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "service"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    call_out.push(result[i].callOut);
                    call_in.push(result[i].callIn);
                    blacklist.push(result[i].blacklist);
                    to_verify.push(result[i].toVerify);
                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: '外呼数',
                        data: call_out
                    },
                        {
                            name: '呼入数',
                            data: call_in
                        }, {
                            name: '黑名单数',
                            data: blacklist
                        }, {
                            name: '待验证数',
                            data: to_verify
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