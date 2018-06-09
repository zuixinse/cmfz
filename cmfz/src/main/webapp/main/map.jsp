<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>


<div id="map_status" style="width: 600px;height:400px;">

</div>

<script type="text/javascript">
    $(function () {
        var goEasy = new GoEasy({appkey: 'BS-449df163a50d4e13afded1380b6e569a'});
        goEasy.subscribe({
            channel: 'sxx_channel',
            onMessage: function (message) {
                console.log(message);
                var data = JSON.parse(message.content);
                var myChart = echarts.init(document.getElementById('map_status'));
                var option = {
                    title: {
                        text: '持名法州APP用户分布图',
                        subtext: '2017年6月15日 最新数据',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    // 说明
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['男', '女']
                    },
                    visualMap: {
                        min: 0,
                        max: 2500,
                        left: 'left',
                        top: 'bottom',
                        text: ['高', '低'],           // 文本，默认为数值文本
                        calculable: true
                    },
                    // 工具箱
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    series: [
                        {
                            name: '男',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: true
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.man
                        },
                        {
                            name: '女',
                            type: 'map',
                            mapType: 'china',
                            label: {
                                normal: {
                                    show: true
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.women
                        }
                    ]
                };
                myChart.setOption(option);
            }
        })

    });


</script>
