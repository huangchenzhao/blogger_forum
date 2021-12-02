<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>新闻添加</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
    <style>
        .layui-form-selected dl{
            z-index:10000;
        }
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" action="${ctx}/news/add" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">文章标题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="title" lay-verify="required" placeholder="请输入文章标题">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">栏目</label>
            <div class="layui-input-inline">
                <select class="layui-input" name="typeId" lay-verify="required">
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">文章内容</label>
        <div class="layui-input-block">
            <script id="container" name="content" lay-verify="required" type="text/plain" style="width:800px;height:400px;"></script>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
    layui.config({
        base: basePath+'/static/js/',
        v: new Date().getTime()
    }).use(['form'], function () {
        var $ = layui.jquery,
                layer = layui.layer,//获取当前窗口的layer对象;
                form = layui.form;
        var ue = UE.getEditor('container');

        form.on("submit(addNews)",function (data) {
            if(!data.field.content){
                layer.msg("请输入文本内容");
                return false;
            }
            $.post("${ctx}/news/add",data.field,function () {
                layer.msg("添加成功");
                $("input").val("");
                ue.setContent('');
            });
            return false;
        });
    });
</script>
</body>
</html>