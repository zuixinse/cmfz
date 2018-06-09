<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

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
    <script language="javascript">
        $(function () {


            var config = {
                id: "tg1",
                width: "800",
                renderTo: "div1",
                headerAlign: "left",
                headerHeight: "30",
                dataAlign: "left",
                indentation: "20",
                folderOpenIcon: "images/folderOpen.gif",
                folderCloseIcon: "images/folderClose.gif",
                defaultLeafIcon: "images/defaultLeaf.gif",
                hoverRowBackground: "false",
                folderColumnIndex: "1",
                itemClick: "itemClickEvent",
                columns: [
                    {
                        headerText: "<input type='checkbox' class='allcheck'>",
                        headerAlign: "center",
                        dataAlign: "center",
                        width: "20",
                        handler: "customCheckBox"
                    },
                    {headerText: "名称", dataField: "name", headerAlign: "center", handler: "customOrgName"},
                    {headerText: "拼音码", dataField: "code", headerAlign: "center", dataAlign: "center", width: "100"},
                    {
                        headerText: "负责人",
                        dataField: "assignee",
                        headerAlign: "center",
                        dataAlign: "center",
                        width: "100"
                    },
                    {headerText: "查看", headerAlign: "center", dataAlign: "center", width: "50", handler: "customLook"}
                ],
                data: [
                    {
                        name: "北京市销售中心", code: "bj", assignee: "余世维", children: [
                            {name: "东城区销售中心", code: "dc", assignee: "张三"},
                            {
                                name: "海淀区销售中心", code: "hd", assignee: "李四", children: [
                                    {name: "上地南路销售中心", code: "sdnl", assignee: "王五"},
                                    {name: "上地桥销售中心", code: "sdq", assignee: "赵六"},
                                    {
                                        name: "上地软件园一期销售中心", code: "sdrjy", assignee: "何其", children: [
                                            {name: "环宇大厦销售点", assignee: "赵明", code: "hyds"},
                                            {name: "启明星辰销售点", assignee: "天竺", code: "qmxc"},
                                            {name: "中国核电销售点", assignee: "信史", code: "zghd"},
                                            {name: "图灵销售点", assignee: "图杏", code: "tl"}
                                        ]
                                    }
                                ]
                            },
                            {name: "朝阳区销售中心", code: "cy", assignee: "于海武"}
                        ]
                    },
                    {name: "湖北销售中心", code: "hb", assignee: "胡卫宇", children: []},
                    {name: "广东销售中心", code: "gd", assignee: "广深海", children: []},
                    {name: "浙江销售中心", code: "zj", assignee: "浙蝴", children: []}
                ]
            };

            $(document).ready(function () {
                $(".allcheck").click(function () {
                    $("#tg1").find("input[name='cblbox']").attr("checked", $(this).attr("checked"));
                });

            });

        });

        /*
            单击数据行后触发该事件
            id：行的id
            index：行的索引。
            data：json格式的行数据对象。
        */
        function itemClickEvent(id, index, data) {


            jQuery("#currentRow").val(id + ", " + index + ", " + TreeGrid.json2str(data));
        }

        /*
            通过指定的方法来自定义栏数据
        */
        function customCheckBox(row, col) {
            return "<input name='cblbox' type='checkbox'>";
        }

        function customOrgName(row, col) {
            var name = row[col.dataField] || "";
            return name;
        }

        function customLook(row, col) {

            return "<a href='javascript:void(0)' onclick='showrowname(\"" + row.code + "\",\"" + row.name + "\")' style='color:blue;'>查看</a>";
        }

        function showrowname(code, name) {
            alert(code + name);

        }

        //创建一个组件对象
        var treeGrid = new TreeGrid(config);
        treeGrid.show();

        /*
            展开、关闭所有节点。
            isOpen=Y表示展开，isOpen=N表示关闭
        */
        function expandAll(isOpen) {
            treeGrid.expandAll(isOpen);
        }

        /*
            取得当前选中的行，方法返回TreeGridItem对象
        */
        function selectedItem() {
            var treeGridItem = treeGrid.getSelectedItem();
            if (treeGridItem != null) {
                //获取数据行属性值
                //alert(treeGridItem.id + ", " + treeGridItem.index + ", " + treeGridItem.data.name);

                //获取父数据行
                var parent = treeGridItem.getParent();
                if (parent != null) {
                    //jQuery("#currentRow").val(parent.data.name);
                }

                //获取子数据行集
                var children = treeGridItem.getChildren();
                if (children != null && children.length > 0) {
                    jQuery("#currentRow").val(children[0].data.name);
                }
            }
        }
    </script>

</head>

<input type="button" value="关闭所有节点" onclick="expandAll('N')">
<input type="button" value="展开所有节点" onclick="expandAll('Y')">
<input type="button" value="取得当前行的数据" onclick="selectedItem()"><br>
当前选中的行：<input type="text" id="currentRow" size="110">

<div id="div1"></div>


</html>