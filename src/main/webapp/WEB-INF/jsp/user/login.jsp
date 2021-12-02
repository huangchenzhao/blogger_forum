<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link href="${ctxStatic}/css/logo.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="wrapper">

    <div class="froyo-frame login-body">
        <div class="admin-face">
            <div class="face-img">
                <img src="${ctxStatic}/images/editor.png" alt="logo">
            </div>
        </div>
        <h1 class="admin-login-tittle">「从现在开始，为每一天写下一篇记录」</h1>
        <div class="admin-login-form">
            <form class="form" action="${ctx}/login" method="post" autocomplete="off">
                <div class="admin-name"><input name="username" type="text" placeholder="用户名"></div>
                <div class="admin-password"><input name="password" type="password" placeholder="密码"></div>
                <div class="admin-button"><button type="submit" id="login-button">登录</button></div>
            </form>
          <!--   其他账号登录：
            <a href=""><img src="${ctxStatic}/images/qqLogin.png" style="width: 50px;margin: 10px" onclick="loginQQ()"></a>
            <img src="${ctxStatic}/images/weChatLogin.png" style="width: 50px;margin: 10px">
            <img src="${ctxStatic}/images/weboLogin.png" style="width: 50px;margin: 10px"> -->
            <div class="admin-button"><a id="login-button" href="${ctx}/index">返回首页</a></div>
        </div>
        <h1 class="admin-login-tittle">
            ${message}
            <c:if test="${status eq 2}">
                <a href="javascript:;" id="setReEmail">重新发送邮件激活</a>
            </c:if>
        </h1>
    </div>

</div>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script type="text/javascript">
    layui.use('layer', function() {
        var $ = layui.jquery,
            layer = layui.layer;
        //login-button的点击事件..
        $('#login-button').click(function (event) {
            event.preventDefault();
            if (!$("input[name=username]").val()) {
                layer.msg("用户名不能为空");
                return false;
            } else if (!$("input[name=password]").val()) {
                layer.msg("密码不能为空");
                return false;
            } else {
                $('form').fadeOut(500);
                $('.wrapper').addClass('form-success');
                $('.form').submit();
            }
        });

        //重新发送激活邮件!
        $('#setReEmail').click(function () {
            var username='${user.username}';
            var name1=$("input[name=username]").val();
            if(username||(name1&&name1==username)){
                $.post("${ctx}/reEmail",{username:username},function(data){
                    if(data=="success"){
                        layer.alert("邮件已发送，请到邮箱查收...",{icon:6});
                    }else{
                        layer.alert("邮件发送失败，请到联系管理员",{icon:5});
                    }
                });
            }else{
                layer.msg("用户名不能为空");
            }
        });

    });


    //qq登录
    function openWin(url,name,iWidth,iHeight) {
        //获得窗口的垂直位置
        var iTop = (iHeight) / 2;
        //获得窗口的水平位置
        var iLeft = (iWidth) / 2;
        window.open(url, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
    }
    function loginQQ() {
        var url = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101392599&redirect_uri=http://www.xulian.net.cn:8088/afterlogin&scope=get_user_info";
        openWin(url,"qqLogin",650,500);
    }
</script>
</body>
</html>