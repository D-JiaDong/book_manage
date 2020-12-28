<%--
  Created by IntelliJ IDEA.
  User: LIANG
  Date: 2020/11/11
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index</title>
        <script type="text/javascript" src="static/bootstrap/js/bootstrap.js" ></script>
        <link rel="stylesheet" href="static/bootstrap/css/bootstrap.css" />
        <style>
            body{
                background:url("static/image/背景.jpg") ;
                background-size: 100% 100%;
            }
        </style>
    </head>
    <body>
        
        <%
            String contextPath = request.getContextPath();
        %>
        <h1 align="center">图书借阅系统</h1>
        <div align="center">
            <a href="admin/login.html"class="btn btn-default ">管理员登陆</a>
            <a href="user/login.html" class="btn btn-default ">用户登陆</a>
            <a href="newuser.html"class="btn btn-default ">用户注册</a>
        </div>
    </body>
</html>
