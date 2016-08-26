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
            text: '商城数据'
        },
        tooltip: {},
        legend: {
            data: ['APP用户数', 'APP新用户数', '首页访问数', '下单用户数', '新下单用户数', '总订单数', '新订单数'],
            selectedMode: 'multiple',
            selected: {
                'APP新用户数': false,
                '首页访问数': false,
                '下单用户数': false,
                '新下单用户数': false,
                '总订单数': false,
                '新订单数': false
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
            name: 'APP用户数',
            type: 'bar',
            data: []
        }, {
            name: 'APP新用户数',
            type: 'bar',
            data: []
        }, {
            name: '首页访问数',
            type: 'bar',
            data: []
        }, {
            name: '下单用户数',
            type: 'bar',
            data: []
        }, {
            name: '新下单用户数',
            type: 'bar',
            data: []
        }, {
            name: '总订单数',
            type: 'bar',
            data: []
        }, {
            name: '新订单数',
            type: 'bar',
            data: []
        }
        ]
    });

    myChart.showLoading();

    var dates = [];
    var app_users = [];
    var app_users_plus = [];
    var ordered_users = [];
    var ordered_users_plus = [];
    var app_orders = [];
    var app_orders_plus = [];
    var home_visits = [];

    $.ajax({
        type: "post",
        async: true,
        url: "bar.do",
        data: {"type": "o2o"},
        dataType: "json",
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    dates.push(result[i].date);
                    app_users.push(result[i].appUsers);
                    app_users_plus.push(result[i].appUsersPlus);
                    ordered_users.push(result[i].orderedUsers);
                    ordered_users_plus.push(result[i].orderedUsersPlus);
                    app_orders.push(result[i].appOrders);
                    app_orders_plus.push(result[i].appOrdersPlus);
                    home_visits.push(result[i].homeVisits)

                }

                myChart.hideLoading();
                myChart.setOption({

                    xAxis: {
                        name: '日期',
                        data: dates
                    },
                    series: [{
                        name: 'APP用户数',
                        data: app_users
                    },
                        {
                            name: 'APP新用户数',
                            data: app_users_plus
                        }, {
                            name: '首页访问数',
                            data: home_visits
                        }, {
                            name: '下单用户数',
                            data: ordered_users
                        }, {
                            name: '新下单用户数',
                            data: ordered_users_plus
                        }, {
                            name: '总订单数',
                            data: app_orders
                        }, {
                            name: '新订单数',
                            data: app_orders_plus
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