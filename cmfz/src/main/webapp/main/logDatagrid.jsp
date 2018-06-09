<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">

<script type="text/javascript">
    $(function () {
        $("#log").datagrid({
            idField: 'id',
            treeField: 'name',
            url: "${pageContext.request.contextPath}/log/queryAll",
            fitColumns: true,
            fit: true,
            pagination: true,
            lect: true,
            method: 'post',
            singleSelect: true,
            ctrlSelect: true,
            striped: true,
            columns: [[
                {field: 'id', title: 'id', width: 40},
                {field: 'name', title: '管理员姓名', width: 40},
                {field: 'date', title: '日期', width: 40},
                {field: 'method', title: '操作', width: 40},
                {field: 'flag', title: '结果', width: 40}
            ]],
            toolbar:[{
                text:'导出日志',
                handler:function(){
                    $.ajax({
                        url:'${pageContext.request.contextPath}/'
                    });
                }
            }]
        });

    });
</script>


<table id="log" style="width:600px;height:200px">

</table>

