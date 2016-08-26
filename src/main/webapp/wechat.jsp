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
            text: '微信数据'
        },
        tooltip: {},
        legend: {
            data: ['微信订阅数', '新订阅数', '退订数', '微信订单数', '微信新订单数'],
            selectedMode: 'multiple',
            selected: {
                '微信订阅数': false,
                '退订数': false,
                '微信订单数': false,
                '微信新订单数': false
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
            name: '微信订阅数',
            type: 'bar',
            data: []
        }, {
            name: '新订阅数',
            type: 'bar',
            data: []
        }, {
            name: '退订数',
            type: 'bar',
            data: []
        }, {
            name: '微信订单数',
            type: 'bar',
            data: []
        }, {
            name: '微信新订单数',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var wx_users = [];
    var wx_users_plus = [];
    var wx_users_minus = [];
    var wx_orders = [];
    var wx_orders_plus = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "wechat"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    wx_users.push(result[i].wxUsers);
                    wx_users_plus.push(result[i].wxUsersPlus);
                    wx_users_minus.push(result[i].wxUsersMinus);
                    wx_orders.push(result[i].wxOrders);
                    wx_orders_plus.push(result[i].wxOrdersPlus);

                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: '微信订阅数',
                        data: wx_users
                    },
                        {
                            name: '新订阅数',
                            data: wx_users_plus
                        }, {
                            name: '退订数',
                            data: wx_users_minus
                        }, {
                            name: '微信订单数',
                            data: wx_orders
                        }, {
                            name: '微信新订单数',
                            data: wx_orders_plus
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