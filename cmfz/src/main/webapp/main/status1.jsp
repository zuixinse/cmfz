<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>



<div id="eharts1" style="width: 600px;height:400px;">

</div>

<script type="text/javascript">
    $(function(){
        $.ajax({
            url:'${pageContext.request.contextPath}/user/queryActive',
            type:'post',
            success:function(data){
                var myEcharts=echarts.init(document.getElementById("eharts1"));
                var option = {
                    title: {
                        text: '持名佛洲用户周活跃度'
                    },
                    tooltip:{},
                    legend:{
                        data:['人数']
                    },
                    xAxis: {
                        data:data.name
                    },
                    yAxis: {

                    },
                    legend:{
                        data:['人数']
                    },
                    series: [{
                        name:'人数',
                        data: data.count,
                        type: 'bar'
                    }]
                };
                myEcharts.setOption(option);
            }
        });
    });


</script>
