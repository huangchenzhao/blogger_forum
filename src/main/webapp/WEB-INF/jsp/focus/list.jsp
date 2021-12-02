<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>关注列表</title>
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

<body style=" background-color: gainsboro;">

<div style="margin:0px; background-color: white; margin:0 10px;">
    <blockquote class="layui-elem-quote">
        <button type="button" class="layui-btn layui-btn-small" id="getAll"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <form class="layui-form" style="float:right;">
            <div class="layui-form-item" style="margin:0;">
                <div class="layui-input-inline">
                    <input type="text" name="title" placeholder="请输入关键词" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="padding:0;">
                    <button lay-filter="search" class="layui-btn" lay-submit><i class="fa fa-search" aria-hidden="true"></i> 查询</button>
                </div>
            </div>
        </form>
    </blockquote>
    <table class="layui-hide" id="newsList" lay-filter="news"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
    </script>
</div>
<jsp:include page="update.jsp"></jsp:include>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script>
    layui.config({
        base: basePath+'/static/js/',
        v: new Date().getTime()
    }).use(['table', 'form'], function () {
        var table = layui.table,
            $ = layui.jquery,
            layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
            layer = layui.layer,//获取当前窗口的layer对象;
            form = layui.form;
        //表单渲染
        table.render({
            elem: '#newsList'
            ,url: '/display/search'
            ,cellMinWidth: 80
            ,method: 'POST'
            ,page: true
            ,limits:[10,20,30]
            ,cols: [[
                {checkbox: true,field:'id', title: 'ID', sort: true}
                ,{field:'articleName', title: '标题'}
                ,{field:'type', title: '栏目'}
                ,{field:'userName', title: '作者'}
                ,{field:'read', title: '浏览量'}
                ,{fixed:'right',title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });



        //监听提交
        form.on('submit(search)', function(data){
            table.reload('newsList', {where:data.field});
            return false;
        });

        //监听工具条
        table.on('tool(news)', function(obj){
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                $.post("${ctx}/news/read",{id:data.id},function (e) {
                    layer.open({
                        title: "查看博文",
                        type: 1,
                        area: ['850px', '550px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: $(".news_read"),
                        success:function () {
                            $(".news_read textarea").remove();
                            $(".news_read input[name=title]").val(e.title);
                            $(".news_read input[name=typeName]").val(e.type.name);
                            $(".news_read input[name=createDate]").val(e.createDate);
                            $(".news_read input[name=username]").val(e.createBy.username);
                            var ue = UE.getEditor('container');
                            ue.ready(function(){
                                ue.setContent(e.content);
                                ue.setDisabled('fullscreen');
                            });
                            UE.getEditor('container').addListener('beforefullscreenchange',function(event,isFullScreen){
                                $(".layui-layer-shade").remove();
                            });
                        },
                        cancel:function () {
                            UE.delEditor('container');
                            $(".news_read .news_content").append('<script id="container" name="content" lay-verify="required" type="text/plain" style="width:700px;"><\/script>');
                        }
                    });


                });
            } else if(layEvent === 'del'){
                layer.confirm('真的删除这条博文吗?', function(index){
                    //向服务端发送删除指令
                    $.post(basePath+"/news/delete",{id:data.id},function(data){
                        obj.del();
                        layer.close(index);
                    });
                });
            } else if(layEvent === 'edit') {
                $.post("${ctx}/news/read",{id:data.id},function (e) {
                    layer.open({
                        title: "修改博文",
                        type: 1,
                        area: ['850px', '550px'],
                        fixed: false, //不固定
                        maxmin: true,
                        zIndex:20,
                        content: $(".news_update"),
                        success:function () {
                            $(".news_update textarea").remove();
                            $(".news_update input[name=title]").val(e.title);
                            $(".news_update input[name=id]").val(e.id);
                            var ue = UE.getEditor('container1');
                            ue.ready(function(){
                                ue.setContent(e.content);
                                //window.frames["ueditor_0"].contentWindow.document.body.innerHTML=e.content;
                            });
                            UE.getEditor('container1').addListener('beforefullscreenchange',function(event,isFullScreen){
                                $(".layui-layer-shade").remove();
                            });
                        },
                        cancel:function () {
                            UE.delEditor('container1');
                            $(".news_update .news_content").append('<script id="container1" name="content" lay-verify="required" type="text/plain" style="width:700px;"><\/script>');
                        }
                    });

                });
            }
        });



        form.on("submit(addNews)",function (data) {
            if(data.field.content){
                $.post("${ctx}/news/update",data.field,function (data) {
                    table.reload('newsList');
                    layer.closeAll();
                });
            }else{
                layer.msg("请输入文本内容");
            }
            return false;
        });

        $(".close_btn").click(function () {
            UE.delEditor('container1');
            $(".news_update .news_content").append('<script id="container1" name="content" lay-verify="required" type="text/plain" style="width:700px;"><\/script>');
            UE.delEditor('container');
            $(".news_read .news_content").append('<script id="container" name="content" lay-verify="required" type="text/plain" style="width:700px;"><\/script>');
            layer.closeAll();
        });

        //批量删除
        $("#getAll").click(function () {
            var checkStatus = table.checkStatus('newsList')
                ,data = checkStatus.data;
            if(data.length==0){
                layer.msg("请选择要删除的数据");
            }else{
                var ids=new Array();
                $.each(data,function (index,val) {
                    ids.push(val.id);
                });
                $.post("${ctx}/news/batchDelete",{ids:ids},function () {
                    layer.alert("删除成功",{icon:6},function (index) {
                        layer.close(index);
                        table.reload('newsList');
                    });
                });
            }
        });

    });
</script>
</body>

</html>