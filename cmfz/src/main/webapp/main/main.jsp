<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->

        $(function () {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/menu/queryMenu',
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (index, first) {

                        var child = "";
                        $.each(first.menus, function (index2, second) {
                            child += "<p><a href='#' class='easyui-linkbutton' onclick=\"addTabs('" + second.id + "','" +second.title + "','"+second.date + "','"+ second.iconCls + "','" + second.url + "')\" style='width:100%' data-options=\"iconCls:'" + second.iconCls + "',plain:true,selected:false\">" + second.title + "</a></p>";
                        });
                        $('#aa').accordion('add', {
                            title: first.title,
                            content: child,
                            selected: true,
                            iconCls: first.iconCls
                        });
                    });
                }
            });
        });

        function addTabs(id,title,date, iconCls, url) {
            console.log(url);
            var flag = $('#tt').tabs('exists', title);
            if (flag) {
                $('#tt').tabs('select', title)
            } else {

                $('#tt').tabs({
                    onAdd:function(){
                        $("#"+id).edatagrid({
                            url: "${pageContext.request.contextPath}/"+url,
                            fitColumns: true,
                            fit:true,
                            ctrlSelect:true,
                            method:'post',
                            pagination:true,
                            striped:true,
                            toolbar:[{
                                iconCls: 'icon-add',
                                text:'添加',
                                handler: function(){}
                            },'-',{
                                iconCls: 'icon-cut',
                                text:'删除',
                                handler: function(){}
                            },'-',{
                                iconCls: 'icon-edit',
                                text:'修改',
                                handler: function(){}
                            },'-',{
                                iconCls: 'icon-save',
                                text:'保存',
                                handler: function(){}
                            }],
                            columns: [[
                                {field: 'id', title: 'id', width: 40, sortable: true},
                                {field: 'title', title: '标题',  width: 40, sortable: true},
                                {field: 'date', title: '日期', width: 40},
                                {field: 'status', title: '状态', width: 40},
                                {field: 'url', title: '路径', width: 40}
                            ]],
                            view: detailview,
                            detailFormatter: function(rowIndex, rowData){
                                return '<table><tr>' +
                                    '<td rowspan=2 style="border:0"><img src="../' + rowData.url + '" style="height:50px;"></td>' +
                                    '<td style="border:0">' +
                                    '<p>上传日期: ' + rowData.date + '</p>' +
                                    '<p>状态: ' + rowData.status + '</p>' +
                                    '</td>' +
                                    '<td rowspan=2 style="border:0">'+
                                    '<p>标题: ' + rowData.title + '</p>' +
                                    '<p>id: ' + rowData.id + '</p>' +
                                    '</td>' +
                                    '</tr></table>';
                            }
                        });
                    }
                });
                $('#tt').tabs('add', {
                    title: title,
                    content: "<table id='"+id+"'></table>",
                    closable: true,
                    selected: true,
                    iconCls:iconCls
                });
            }

        }

    </script>


</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#"
                                                                                                              class="easyui-linkbutton"
                                                                                                              data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div id="ddd" title="主页" data-options="iconCls:'icon-neighbourhood',"
             style=" background-image : url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>

</div>
<div id="dg"></div>
</body>
</html>