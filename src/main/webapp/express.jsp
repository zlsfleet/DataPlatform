<%--
  Created by IntelliJ IDEA.
  User: Kris
  Date: 2016/6/6
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
            text: '快递数据'
        },
        tooltip: {},
        legend: {
            data: ['总收件人数', '新增收件人数', '总快递员数', '新增快递员数', '总快递数', '新增快递数', '退件数'],
            selectedMode: 'multiple',
            selected: {
                '总收件人数': false,
                '新增收件人数': false,
                '总快递员数': false,
                '新增快递员数': false,
                '总快递数': false,
                '退件数': false
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
            name: '总收件人数',
            type: 'bar',
            data: []
        }, {
            name: '新增收件人数',
            type: 'bar',
            data: []
        }, {
            name: '总快递员数',
            type: 'bar',
            data: []
        }, {
            name: '新增快递员数',
            type: 'bar',
            data: []
        }, {
            name: '总快递数',
            type: 'bar',
            data: []
        }, {
            name: '新增快递数',
            type: 'bar',
            data: []
        }, {
            name: '退件数',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var recipients = [];
    var recipients_plus = [];
    var couriers = [];
    var couriers_plus = [];
    var packages = [];
    var packages_plus = [];
    var returns = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "express"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    recipients.push(result[i].recipients);
                    recipients_plus.push(result[i].recipientsPlus);
                    couriers.push(result[i].couriers);
                    couriers_plus.push(result[i].couriersPlus);
                    packages.push(result[i].packages);
                    packages_plus.push(result[i].packagesPlus);
                    returns.push(result[i].returns);

                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [
                        {
                            name: '总收件人数',
                            data: recipients
                        }, {
                            name: '新增收件人数',
                            data: recipients_plus
                        }, {
                            name: '总快递员数',
                            data: couriers
                        }, {
                            name: '新增快递员数',
                            data: couriers_plus
                        }, {
                            name: '总快递数',
                            data: packages
                        }, {
                            name: '新增快递数',
                            data: packages_plus
                        }, {
                            name: '退件数',
                            data: returns
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