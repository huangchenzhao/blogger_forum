<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
</head>

<body>
<div style="margin:0px; background-color: white; margin:0 10px;width: 44%;padding-top: 3%;">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" placeholder="请输入用户名" lay-verify="required" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" placeholder="请输入密码" lay-verify="required" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="repassword" placeholder="请确认密码" lay-verify="required" class="layui-input">
            </div>
        </div>
       <div class="layui-form-item">
             <label class="layui-form-label">用户角色</label>
             <div class="layui-input-block">
                 <c:forEach items="${roles}" var="role">
                     <input type="checkbox" name="role" value="${role.id}" title="${role.name}">
                 </c:forEach>
             </div>
         </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-filter="add_user" lay-submit>提交</button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script>
    layui.use('form', function() {
        var form = layui.form,$=layui.$;

        form.on("submit(add_user)",function(data){
            if(data.field.password!=data.field.repassword){
                layer.msg("两次密码不一致！");
                return false;
            }else if (!data.field.role) {
                layer.msg("请选择用户角色！");
                return false;
            }
            var roles=new Array();
            $("input[type=checkbox]:checked").each(function(){
                roles.push($(this).val());
            });
            data.field.userRoles=roles;
            $.post("${ctx}/user/add",data.field,function (data) {
                layer.msg("添加成功！",function(){
                    location.reload();
                });
            });
            return false;
        });

        //验证用户名是否被注册
        $("input[name=username]").blur(function () {
            var obj=this;
            $.post("${ctx}/user/findCount",{username:$(obj).val()},function (data) {
                if(data=='false'){
                    layer.msg("该用户名已注册！");
                    $(obj).focus();
                }
            });
        });
    });
</script>
</html>