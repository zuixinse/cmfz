<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>


<script type="text/javascript">
    $(function () {
        $("#dg").edatagrid({

            url: "${pageContext.request.contextPath}/image/queryAll",
            updateUrl: "${pageContext.request.contextPath}/image/update",
            //   destroyUrl:"{pageContext.request.contextPath}/image/delete",
            fitColumns: true,
            fit: true,
            lect: true,
            method: 'post',

            ctrlSelect:true,
            pagination: true,
            striped: true,
            columns: [[
                {field: 'id', title: 'id', width: 40, sortable: true},
                {field: 'title', title: '标题', width: 40, sortable: true},
                {field: 'date', title: '日期', width: 40},
                {
                    field: 'status', title: '状态', width: 40,
                    editor: {
                        type: 'text'
                    }
                },
                {field: 'url', title: '路径', width: 40}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加',
                handler: function () {
                    $('#dd').dialog('open');
                }
            }, '-', {
                iconCls: 'icon-cut',
                text: '删除',
                handler: function () {
                    var opts = $("#dg").edatagrid('getSelections');
                    var flag = window.confirm("确定要删除吗？");
                    if (opts == null) {
                        alert("请选择行");
                    } else {
                        /*var a=$("#dg").edatagrid('destroyRow');*/
                        if (flag) {
                            $.each(opts, function (index, child) {
                                $.ajax({
                                    type: 'get',
                                    url: "${pageContext.request.contextPath}/image/delete",
                                    data: 'id=' + child.id,
                                    success: function () {
                                        $("#dg").datagrid('reload');
                                    }
                                });

                            });
                        }

                    }
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '修改',
                handler: function () {
                    var opts = $("#dg").edatagrid('getSelections');
                    var opt = $("#dg").edatagrid('getSelected');
                    if (opts == null | opt == null) {
                        alert("请选择行");
                    } else if (opts.length > 1) {
                        alert("只能修改一行");
                    } else {
                        var index = $("#dg").edatagrid('getRowIndex', opt);
                        $("#dg").edatagrid('editRow', index);
                    }
                }
            }, '-', {
                iconCls: 'icon-save',
                text: '保存',
                handler: function () {
                    $("#dg").edatagrid('reload');
                }
            }],

            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + rowData.url + '"+ style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>上传日期: ' + rowData.date + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '</td>' +
                    '<td rowspan=2 style="border:0">' +
                    '<p>标题: ' + rowData.title + '</p>' +
                    '<p>id: ' + rowData.id + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });

    });
</script>


<table id="dg" style="width:600px;height:200px"
       title="Editable DataGrid"
       singleSelect="true">
    <thead>

    </thead>
</table>


