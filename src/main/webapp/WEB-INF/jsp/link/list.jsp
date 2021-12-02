<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>友情链接列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
</head>

<body style=" background-color: gainsboro;">

<div style="margin:0px; background-color: white; margin:0 10px;">
    <blockquote class="layui-elem-quote">
        <button type="button" class="layui-btn layui-btn-small" id="getAll"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <form class="layui-form" style="float:right;">
            <div class="layui-form-item" style="margin:0;">
                <div class="layui-input-inline">
                    <input type="text" name="description" placeholder="请输入关键词" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="padding:0;">
                    <button lay-filter="search" class="layui-btn" lay-submit><i class="fa fa-search" aria-hidden="true"></i> 查询</button>
                </div>
            </div>
        </form>
    </blockquote>
    <table class="layui-hide" id="linkList" lay-filter="link"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<jsp:include page="update.jsp"/>
<script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
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
            elem: '#linkList'
            ,url: basePath+'/link/search'
            ,cellMinWidth: 80
            ,method: 'POST'
            ,page: true
            ,limits:[10,20,30]
            ,cols: [[
                 {checkbox: true,field:'id', title: 'ID', sort: true}
                ,{field:'description', title: '描述'}
                ,{field:'url', title: '链接地址',templet: function(d){
                    var url='<a href="'+d.url+'">'+d.url+'</a>';
                    return url;
                }}
                ,{field:'createBy.username',  title: '创建者',templet: function(d){
                    return d.createBy.username;
                }}
                ,{field:'createDate', title: '创建时间',sort: true}
                ,{fixed:'right',title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听提交
        form.on('submit(search)', function(data){
            console.log(data.field);
            table.reload('linkList', {where:data.field});
            return false;
        });

        //监听工具条
        table.on('tool(link)', function(obj){
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除这条日志吗?', function(index){
                    //向服务端发送删除指令
                    $.post(basePath+"/link/delete",{id:data.id},function(data){
                        obj.del();
                        layer.close(index);
                    });
                });
            }else if(layEvent==='edit'){
                layer.open({
                    title: "修改链接",
                    type: 1,
                    area: ['500px', '230px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: $(".link_edit"),
                    success:function () {
                        $(".link_edit input[name=id]").val(data.id);
                        $(".link_edit input[name=description]").val(data.description);
                        $(".link_edit input[name=url]").val(data.url);
                    }
                });
            }
        });

        //批量删除
        $("#getAll").click(function () {
            var checkStatus = table.checkStatus('linkList')
                    ,data = checkStatus.data;
            if(data.length==0){
                layer.msg("请选择要删除的数据");
            }else{
                var ids=new Array();
                $.each(data,function (index,val) {
                    ids.push(val.id);
                });
                layer.confirm('真的删除这些链接吗?', function(index){
                    layer.close(index);
                    $.post("${ctx}/link/bathDelete",{ids:ids},function () {
                        layer.alert("删除成功",{icon:6},function (index) {
                            layer.close(index);
                            table.reload('linkList');
                        });
                    });
                });
            }
        });

        //取消按钮
        $(".close_btn").click(function () {
           layer.closeAll();
        });

        form.on("submit(updateLinks)",function (data) {
            console.log(data.field);
            $.post("${ctx}/link/update",data.field,function (e) {
                layer.closeAll();
                layer.msg("修改成功！",{icon:6});
            }).error(function () {
                layer.msg("出错了哟！");
            });
            return false;
        });
    });
</script>
</body>

</html>