<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">

<script type="text/javascript">
    $(function () {
        $("#adg").treegrid({
            idField: 'id',
            treeField: 'name',
            url: "${pageContext.request.contextPath}/album/queryAll",
            updateUrl: "${pageContext.request.contextPath}/album/update",
            fitColumns: true,
            fit: true,
            pagination: true,
            lect: true,
            method: 'post',
            singleSelect: true,
            ctrlSelect: true,
            /*onLoadSuccess:function(){
                $("#adg").treegrid('collapseAll');
            },*/
            onDblClickRow: function (section) {
                $("#album_paly").dialog('open');
                $("#audio").prop('src', section.url);
            },
            striped: true,
            columns: [[
                {field: 'name', title: '专辑名称', width: 40},
                {field: 'size', title: '章节大小', width: 40},
                {field: 'time', title: '时长', width: 40},
                {field: 'count', title: '数量', width: 40},
                {field: 'date', title: '发行日期', width: 40}
            ]],
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加专辑',
                handler: function () {
                    $("#album").dialog('open');
                }
            }, '-', {
                iconCls: 'icon-cut',
                text: '删除',
                handler: function () {
                    var opt = $("#adg").treegrid('getSelected');
                    var flag = window.confirm("确定要删除吗？");
                    if (opt == null) {
                        alert("请选择要删除的章节或者专辑");
                    } else {
                        if (opt.size == null) {
                            if (flag) {
                                $.ajax({
                                    url: ' ${pageContext.request.contextPath}/album/delete',
                                    type: 'post',
                                    data: 'id=' + opt.id,
                                    success: function () {
                                        $("#adg").treegrid('reload');
                                    }
                                });
                            }
                        } else {
                            if (flag) {
                                $.ajax({
                                    url: ' ${pageContext.request.contextPath}/section/delete',
                                    type: 'post',
                                    data: 'id=' + opt.id + "&album_id=" + opt.album_id,
                                    success: function () {
                                        $("#adg").treegrid('reload');
                                    }
                                });
                            }
                        }
                    }
                }
            }, '-', {
                iconCls: 'icon-edit',
                text: '专辑详情',
                handler: function () {
                    var album = $("#adg").treegrid('getSelected');
                    if (album == null) {
                        alert("请先选中专辑");
                    } else {
                        if (album.size != null) {
                            alert("请选择要查看的专辑");
                        } else {
                            $("#album_info").dialog('open');
                            $("#album_intro").form('load', album);
                            $("#album_image").prop('src', album.image);
                        }
                    }

                }
            }, '-', {
                iconCls: 'icon-save',
                text: '下载音频',
                handler: function () {
                    var section = $("#adg").treegrid('getSelected');
                    if (section == null) {
                        alert("请先选择章节");
                    } else {
                        if (section.size == null) {
                            alert("请先选择章节");
                        } else {
                            location.href = "${pageContext.request.contextPath}/section/download?name=" + section.name + "&url=" + section.url;
                            /*$.ajax({
                                url: ' {pageContext.request.contextPath}/section/download',
                                type: 'post',
                                data: 'name=' + section.name + "&url=" + section.url,
                                success: function () {
                                    alert("===================");
                                }
                            });*/
                        }
                    }

                }
            }, '-', {
                iconCls: 'icon-add',
                text: '添加章节',
                handler: function () {
                    var album = $("#adg").treegrid('getSelected');
                    if (album == null) {
                        alert("请先选中专辑");
                    } else {
                        if (album.size != null) {
                            alert("请选择要添加的专辑");
                        } else {
                            $("#section").dialog('open');
                            $("#al_id").textbox('setValue', album.id);
                        }
                    }

                }
            }]
        });

        $("#album").dialog({
            width: 400,
            height: 300,
            closed: true,
            closed: true,
            cache: false,
            modal: true,
            pagination:true,
            buttons: [{
                text: '添加',
                iconCls: 'icon-add',
                handler: function () {
                    $("#alb").form('submit', {
                        url: '${pageContext.request.contextPath}/album/add',
                        success: function (data) {
                            $("#album").dialog('close');
                            $("#adg").treegrid('reload');
                        }
                    });
                }
            }, {
                text: '取消',
                iconCls: 'icon-redo',
                handler: function () {
                    $("#album").dialog('close');
                }
            }]
        });
        $("#section").dialog({
            width: 400,
            height: 300,
            closed: true,
            closed: true,
            cache: false,
            modal: true,
            buttons: [{
                text: '添加',
                iconCls: 'icon-add',
                handler: function () {
                    $("#sect").form('submit', {
                        url: '${pageContext.request.contextPath}/section/add',
                        success: function (data) {
                            $("#section").dialog('close');
                            $("#adg").treegrid('reload');
                        }
                    });
                }
            }, {
                text: '取消',
                iconCls: 'icon-redo',
                handler: function () {
                    $("#section").dialog('close');
                }
            }]
        });
        $("#album_info").dialog({
            width: 400,
            height: 300,
            closed: true,
            cache: false,
            closable: true,
            modal: true

        });
        $("#alb_name").textbox({
            iconCls: 'icon-man',
            prompt: '专辑名称'
        })
        $("#alb_info").textbox({
            iconCls: 'icon-man',
            prompt: '专辑详情'
        })
        $("#alb_image").filebox({
            iconCls: 'icon-man'
        })
        $("#al_id").textbox({})

        $("#s_url").filebox({
            iconCls: 'icon-man'
        })
        $("#album_paly").dialog({
            iconCls: 'icon-save',
            resizable: true,
            modal: true,
            closed: true
        });

    });
</script>


<table id="adg" style="width:600px;height:200px"
       title="Editable DataGrid"
       singleSelect="true">
    <thead>

    </thead>
</table>


<div id="album" title="My Dialog" style="width:400px;height:200px;">
    <form id="alb" method="post" enctype="multipart/form-data">
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input id="alb_name" name="name" style="width: 100%;height:100%;" required="true"/>
        </div>

        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input id="alb_info" name="info" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input id="alb_image" name="pic" style="width: 100%;height:100%;">
        </div>
    </form>
</div>

<div id="section" title="My Dialog" style="width:400px;height:200px;">
    <form id="sect" method="post" enctype="multipart/form-data">
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input class="easyui-textbox" name="album_id" id="al_id" style="width: 100%;height:100%;"
                   value="0" <%--style="display: none"--%>/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input id="s_url" name="sec" style="width: 100%;height:100%;">
        </div>
    </form>
</div>


<div id="album_info" title="My Dialog" style="width:400px;height:200px;">
    <form id="album_intro" method="post">
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>name</label>
            <input class="easyui-validatebox" name="name" style="width: 100%;height:100%;" required="true"/>
        </div>

        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>name</label>
            <input class="easyui-validatebox" name="teacher_id" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>count</label>
            <input class="easyui-validatebox" name="count" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>score</label>
            <input class="easyui-validatebox" name="score" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>info</label>
            <input class="easyui-validatebox" name="info" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <label>image</label>
            <img src="#" id="album_image" name="url"/>
        </div>
    </form>
</div>

<div id="album_paly" title="My Dialog" style="width:400px;height:200px;">

    <audio id="audio" src="" controls="controls" autoplay="autoplay"></audio>

</div>