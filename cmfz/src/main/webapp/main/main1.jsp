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
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js​ "></script>

    <script type="text/javascript">
        <!--菜单处理-->

        $(function () {
            //请求菜单项数据
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/menu/queryMenu',
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (index, first) {

                        var child = "";
                        $.each(first.menus, function (index2, second) {
                            child += "<p><a href='#' class='easyui-linkbutton' onclick=\"addTabs('" + second.id + "','" + second.title + "','" + second.date + "','" + second.iconCls + "','" + second.url + "','" + second.intro + "')\" style='width:100%' data-options=\"iconCls:'" + second.iconCls + "',plain:true,selected:false\">" + second.title + "</a></p>";
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
            //初始化添加对话框
            $('#dd').dialog({
                title: 'My Dialog',
                width: 400,
                height: 300,
                closed: true,
                cache: false,
                modal: true,
                buttons:[{
                    text:'添加',
                    iconCls:'icon-add',
                    handler:function(){
                        $('#ff').form('submit',{
                            url: '${pageContext.request.contextPath}/image/add',
                            iframe:false,
                            ajax:true,
                            success:function(){
                                $('#dd').dialog('close');
                                $("#dg").edatagrid('reload');
                            }
                        });
                    }
                },{
                    text:'取消',
                    iconCls:'icon-redo',
                    handler:function(){
                        $('#dd').dialog('close');
                    }
                }]
            });
        });
        //添加选项卡
        function addTabs(id, title, date, iconCls, url, intro) {
            var flag = $('#tt').tabs('exists', title);
            if (flag) {
                $('#tt').tabs('select', title)
            } else {
                //添加选项卡
                $('#tt').tabs('add', {
                    title: title,
                    closable: true,
                    selected: true,
                    iconCls: iconCls,
                    href:'${pageContext.request.contextPath}/'+url
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
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px"><c:if test="${not empty sessionScope.loginok}">欢迎您:${sessionScope.user.name}</c:if><c:if test="${empty sessionScope.loginok}">请登录</c:if>
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


<div id="dd" title="My Dialog" style="width:400px;height:200px;">
    <form id="ff" method="post" enctype="multipart/form-data">
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input name="title"  class="easyui-textbox" data-options="iconCls:'icon-man',prompt:'输入标题'" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input name="sttt" class="easyui-switchbutton" data-options="onText:'y',offText:'n'">
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input name="intro"  class="easyui-textbox" data-options="iconCls:'icon-man',prompt:'输入简介'" style="width: 100%;height:100%;" required="true"/>
        </div>
        <div style="height:30px;padding-left: 20%;padding-right: 20%;margin-top: 20px">
            <input name="pic" class="easyui-filebox" style="width: 100%;height:100%;">
        </div>
    </form>
</div>




</body>
</html>