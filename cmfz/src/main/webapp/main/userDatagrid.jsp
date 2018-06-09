<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>


<script type="text/javascript">
    $(function () {
        $("#user").edatagrid({
            url: "${pageContext.request.contextPath}/user/queryAll",
            fitColumns: true,
            fit: true,
            lect: true,
            method: 'post',
            ctrlSelect: true,
            pagination: true,
            striped: true,
            columns: [[
                {field: 'account', title: '账号', width: 40, sortable: true},
                {field: 'name', title: '姓名', width: 40},
                {field: 'dharmaName', title: '法名', width: 40},
                {
                    field: 'sex', title: '性别', width: 40,
                    formatter: function (data) {
                        if (data)
                            return "男";
                        else
                            return "女";
                    }
                },
                {field: 'date', title: '注册日期', width: 40},
                {field: 'status', title: '状态', width: 40}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: '查看详情',
                handler: function () {
                    var opts = $('#user').edatagrid('getSelections');
                    var opt = $('#user').edatagrid('getSelected');
                    if (opts == null || opt == null) {
                        alert("请选择一行");
                    } else {
                        if (opts.length > 1) {
                            alert("只能选择一个用户");
                        } else {
                            $("#user_info").dialog('open');
                            $("#user_intro").form('load', opt);
                            $("#user_head").prop("src", opt.image);
                        }
                    }
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '冻结',
                handler: function () {
                    var opts = $("#user").edatagrid('getSelections');
                    var opt = $("#user").edatagrid('getSelected');
                    var flag = window.confirm("确定要冻结该用户吗？");
                    if (opts == null | opt == null) {
                        alert("请选择行");
                    } else if (opts.length > 1) {
                        alert("只能冻结一行");
                    } else {
                        if (flag) {
                            var index = $("#user").edatagrid('getRowIndex', opt);
                            if (opt.status == 1) {
                                alert("用户已冻结，无需再次操作");
                            } else {
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/user/freeze',
                                    type: 'post',
                                    data: 'id=' + opt.id,
                                    success: function () {
                                        $("#user").edatagrid('reload');
                                    }
                                });
                            }
                        }
                    }
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '解冻',
                handler: function () {
                    var opts = $("#user").edatagrid('getSelections');
                    var opt = $("#user").edatagrid('getSelected');
                    var flag = window.confirm("确定要解冻用户吗？");
                    if (opts == null | opt == null) {
                        alert("请选择行");
                    } else if (opts.length > 1) {
                        alert("只能解冻一行");
                    } else {
                        if (flag) {
                            var index = $("#user").edatagrid('getRowIndex', opt);
                            if (opt.status == 0) {
                                alert("无需解冻");
                            } else {
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/user/unFreeze',
                                    type: 'post',
                                    data: 'id=' + opt.id,
                                    success: function () {
                                        $("#user").edatagrid('reload');
                                    }
                                });
                            }

                        }

                    }
                }
            }, '-', {
                iconCls: 'icon-save',
                text: '导出表格',
                handler: function () {

                    $.ajax({
                        url:'${pageContext.request.contextPath}/user/queryFiled',
                        type:'post',
                        success:function(data){
                            $('#t_cc').combotree('loadData', [{
                                id: 1,
                                text: 'Languages',
                                children: data.titles.children
                            }]);
                            $("#custom_dialog").dialog('open');
                        }
                    });

                }
            }],

            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + rowData.image + '"+ style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>注册日期: ' + rowData.date + '</p>' +
                    '<p>状态: ' + rowData.status + '</p>' +
                    '</td>' +
                    '<td rowspan=2 style="border:0">' +
                    '<p>姓名: ' + rowData.name + '</p>' +
                    '<p>id: ' + rowData.id + '</p>' +
                    '<p>法名: ' + rowData.dharmaName + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
        $("#user_info").dialog({
            title: '用户详情',
            closed: true
        });


        $("#custom_dialog").dialog({
            iconCls: 'icon-save',
            resizable: true,
            modal: true,
            closed: true,
            buttons: [{
                text: '导出',
                handler: function () {
                    var texts = $("#t_cc").combotree('getText');
                    var values = $("#t_cc").combotree('getValues');
                    var c = "";
                    $.each(values, function (index, value) {
                        if (texts.length > index + 1) {
                            c += value + ",";
                        } else {
                            c += value;
                        }
                    });
                    $("#title_form").form('submit', {
                        url: '${pageContext.request.contextPath}/user/excleEx',
                        queryParams: {titles: texts, ids: c},
                        success: function () {
                            $("#custom_dialog").dialog("close");
                        }
                    });
                }
            }, {
                text: '取消',
                handler: function () {
                    $("#custom_dialog").dialog("close");
                }
            }]
        });
        $("#t_cc").combotree({
            onlyLeafCheck: true,
            multiple: true,
            required: true
        });
    });
</script>


<table id="user" style="width:600px;height:200px"
       title="Editable DataGrid"
       singleSelect="true">

</table>

<div id="user_info" title="My Dialog" style="width:400px;height:200px;" data-options="resizable:true">
    <form id="user_intro" method="post">
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>账号</label>
            <input class="easyui-validatebox" name="account" style="width: 100%;height:100%;" required="true"/>
        </div>

        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>密码</label>
            <input class="easyui-validatebox" name="password" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>姓名</label>
            <input class="easyui-validatebox" name="name" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>法名</label>
            <input class="easyui-validatebox" name="dharmaName" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>性别</label>
            <input class="easyui-validatebox" name="sex" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>所在省份</label>
            <input class="easyui-validatebox" name="pro" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>所在市</label>
            <input class="easyui-validatebox" name="city" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>上师</label>
            <input class="easyui-validatebox" name="teacher_id" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>注册时间</label>
            <input class="easyui-validatebox" name="date" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>状态</label>
            <input class="easyui-validatebox" name="status" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>个性签名</label>
            <input class="easyui-validatebox" name="sign" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>头像</label>
            <img src="#" id="user_head" name="url"/>
        </div>
    </form>
</div>


<div id="custom_dialog" title="My Dialog" style="width:400px;height:200px;"
>
    <form id="title_form" method="post">
        <select id="t_cc" style="width:200px;"></select>
    </form>
</div>
