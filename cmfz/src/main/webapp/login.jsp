<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/IconExtension.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="js/echarts.min.js"></script>
    <script type="text/javascript" src="js/china.js"></script>
    <script type="text/javascript">

        $(function () {
            //点击更换验证码：


            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/code/image?date=" + new Date());
            });
            //  form 表单提交
            $("#loginForm").bind("submit", function () {

                if (accountVli() & passVli() & codeVli())
                    return true;
                else
                    return false;

            });
            $("#loginForm").form({
                success:function(data){
                    console.log(data)
                    if(JSON.parse(data).flag){
                        window.location.href="${pageContext.request.contextPath}/main/main1.jsp";
                    }

                }
            });
            //ajax请求验证用户存不存在


        })
        ;


        function accountVli() {
            var account = $("#account").val();
            var flag=true;
            $.ajax({
                type: 'post',
                async:false,
                url: '${pageContext.request.contextPath}/user/userVli',
                data: "account=" + account,
                dataType: 'JSON',
                success: function (map) {
                    if (!map.flag) {
                        alert(map.message);
                        flag=false;
                    } else
                        flag=true;
                }
            });
            return flag;
        }

        function passVli() {
            var password = $("#pass").val();
            var account = $("#account").val();
            var flag=true;
            $.ajax({
                type: 'post',
                async:false,
                url: '${pageContext.request.contextPath}/user/passVli',
                data: "account=" + account + "&password=" + password,
                dataType: 'JSON',
                success: function (map) {
                    if (!map.flag) {
                        alert(map.message);
                        flag=false;
                    } else
                        flag=true;
                }
            });
            return flag;
        }


        function codeVli() {
            var code = $("#enCode").val();
            var flag=true;
            $.ajax({
                type: 'post',
                async:false,
                url: '${pageContext.request.contextPath}/user/code',
                data: "code=" + code,
                dataType: 'JSON',
                success: function (map) {
                    if (map.flag) {
                        flag=true;
                    } else {
                        alert(map.message);
                        flag = false;
                    }
                }
            });
            return flag;
        }


    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" action="${pageContext.request.contextPath}/account/login" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input id="account" type="text" name="account" class="text" value="xxx" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input id="pass" type="password" name="password" class="text" value="xxx" maxlength="20"
                           autocomplete="off"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/code/image"
                         title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick="location.href='/'"><input type="submit"
                                                                                                        class="loginButton"
                                                                                                        value="登录">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>