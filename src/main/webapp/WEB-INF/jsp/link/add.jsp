<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>友情链接添加</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
</head>

<body style=" background-color:white;">
<div>
    <form class="layui-form" style="width:40%;padding-top:2%;">
        <div class="layui-form-item">
            <label class="layui-form-label">网站名称</label>
            <div class="layui-input-block">
                <input type="text" name="description" class="layui-input" lay-verify="required" placeholder="请输入网站名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">网站地址</label>
            <div class="layui-input-block">
                <input type="text" name="url" class="layui-input" lay-verify="required|url" placeholder="请输入网站地址">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addLinks">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">取消</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script>
    layui.config({
        base: basePath+'/static/js/',
        v: new Date().getTime()
    }).use(['table', 'form'], function () {
        var $ = layui.jquery,
                layer = layui.layer,//获取当前窗口的layer对象;
                form = layui.form;

        //监听提交
        form.on('submit(addLinks)', function(data){
            $.post('${ctx}/link/add',data.field,function (e) {
                layer.closeAll();
                layer.alert("提交成功！",{icon:6},function (index) {
                    layer.close(index);
                    $("input").val("");
                });
            }).error(function () {
                layer.msg("提交失败了哟！")
            });
            return false;
        });
    });
</script>
</body>

</html>