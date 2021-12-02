<%--
  Created by IntelliJ IDEA.
  User: jimingrui
  Date: 7/29/21
  Time: 2:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>查询关注列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <script type="text/javascript" src="${ctxStatic}/js/jquery.1.4.2.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css" type="text/css">
    <style>
        .layui-form-selected dl{
            z-index:10000;
        }
    </style>

    <script th:inline="javascript">
        $(document).ready(function () {
            document.getElementById("getAll").addEventListener("click", function () {
                var user=$("#getUser").val()
                console.log(user)
                var data = {userName:user}
                console.log(data);
                $.ajax({
                    url: "/focus/getUser",
                    type: "POST",
                    dataType: "json",
                    data: data,
                    success: function (data) {
                        if(data==null){
                            alert("更新用户状态成功！")
                        }
                    }
                });
            });
        });
    </script>

</head>
<body style=" background-color: gainsboro;">

<div style="margin:0px; background-color: white; margin:0 10px; text-align:center">
    <blockquote class="layui-elem-quote">
        <button type="button" class="layui-btn layui-btn-small" id="getAll"><i class="layui-icon">&#xe629;</i>更新用户状态</button>
        <input type="hidden" value="${fns:getUser().getUsername()}" id="getUser"/>
        <form id="form1" method="post" action="/focus/list">
            <dd>
                <br>
                <button type="submit" class="layui-btn layui-btn-small"><i class="layui-icon">&#xe629;</i>立即查询  </button></dd>
        </form>
    </blockquote>
</div>

</body>
</html>
