<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
	<link href="${ctxStatic}/css/logo.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="wrapper">

    <div class="froyo-frame login-body">
        <div class="admin-face">
            <div class="face-img">
            <img src="${ctxStatic}/images/user.png" alt="logo">
        </div>
        </div>
        <h1 class="admin-login-tittle">「从现在开始，为自己的未来努力一把」</h1>
        <div class="admin-login-form">
            <form class="form" action="${ctx}/register" method="post" autocomplete="off">
                <div class="admin-name"><input name="username" type="text" placeholder="用户名"></div>
                <div class="admin-password"><input name="password" type="password" placeholder="密码"></div>
                <div class="admin-email"><input name="email" type="email" placeholder="邮箱"></div>
                <div class="admin-button"><button type="submit" id="login-button">注册</button></div>
            </form>
        </div>
        <h1 class="admin-login-tittle">${msg}</h1>
    </div>

</div>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/valid.js"></script>
<script type="text/javascript">
    layui.use('layer', function() {
        var $ = layui.jquery,
                layer = layui.layer;

        $('#login-button').click(function (event) {
            event.preventDefault();
            var username=$("input[name=username]").val();
            var password=$("input[name=password]").val();
            var email=$("input[name=email]").val();
            if(!username){
                layer.msg("用户名不能为空");
                return false;
            }else if(!valid.IsIntegerAndEnglishCharacter(username)){
                layer.msg("用户名只能由字母或者数字组成");
                return false;
            }
            if(!password){
                layer.msg("密码不能为空");
                return false;
            }else if(!email){
                layer.msg("邮箱不能为空");
                return false;
            }else if(!valid.IsEmail(email)){
                layer.msg("邮箱格式错误");
                return false;
            }else {
                $('form').fadeOut(500);
                $('.wrapper').addClass('form-success');
                $('.form').submit();
            }
        });
    });
</script>

</body>
</html>