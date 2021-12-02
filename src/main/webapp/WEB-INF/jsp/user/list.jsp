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

<body style=" background-color: gainsboro;">

<div style="margin:0px; background-color: white; margin:0 10px;">
    <blockquote class="layui-elem-quote">
        <button type="button" class="layui-btn layui-btn-small" id="getAll"><i class="layui-icon">&#xe640;</i>批量删除</button>
        <form class="layui-form" style="float:right;">
            <div class="layui-form-item" style="margin:0;">
                <div class="layui-input-inline">
                    <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux" style="padding:0;">
                    <button lay-filter="search" class="layui-btn" lay-submit><i class="fa fa-search" aria-hidden="true"></i> 查询</button>
                </div>
            </div>
        </form>
    </blockquote>
    <table class="layui-hide" id="userList" lay-filter="user"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
<jsp:include page="update.jsp"></jsp:include>
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
            elem: '#userList'
            ,url: basePath+'/user/search'
            ,cellMinWidth: 80
            ,method: 'POST'
            ,page: true
            ,limits:[10,20,30]
            ,cols: [[
                {checkbox: true,field:'id', title: 'ID', sort: true}
                ,{field:'username', title: '用户名'}
                ,{field:'email',  title: '邮箱'}
                ,{field:'createDate',  title: '创建时间', sort: true}
                ,{field:'status',  title: '状态', sort: true,templet: function(d){
                   if(d.status==0){
                        return "<span style='color: green'>正常</span>";
                   }else if(d.status==2){
                        return "<span style='color: red'>未激活</span>";
                   }
                }}
                ,{fixed:'right',title: '操作', align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听工具条
        table.on('tool(user)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                $.post("${ctx}/user/read",{id:data.id},function (e) {
                    $(".user_read input,textarea").val('');
                    layer.open({
                        title: "查看用户信息",
                        type: 1,
                        area: ['600px', '500px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: $(".user_read")
                    });
                    $(".user_read input[name=username]").val(e.username);
                    $(".user_read input[name=email]").val(e.email);
                    var roles="";
                    $.each(e.roles,function(index,val){
                        roles+=val.name+",";
                    });
                    $(".user_read .user_role input").val(roles.substr(0,roles.length-1));
                    if(e.userInfo){
                        $(".user_read input[name=name]").val(e.userInfo.name);
                        $(".user_read input[name=address]").val(e.userInfo.address);
                        $(".user_read input[name=birthday]").val(e.userInfo.birthday);
                        $(".user_read input[name=mobile]").val(e.userInfo.mobile);
                        $(".user_read textarea[name=personal]").val(e.userInfo.personal);
                        if(e.userInfo.sex){
                            $(".user_read input[name=sex]").each(function(){
                               if(e.userInfo.sex==$(this).val()){
                                    $(this).attr("checked","true");
                               }
                            });
                        }
                    }
                });
            } else if(layEvent === 'del'){
                console.log(data);
                layer.confirm('真的删除用户['+data.username+']吗?', function(index){
                    //向服务端发送删除指令
                    $.post(basePath+"/user/delete",{id:data.id},function(data){
                        obj.del();
                        layer.close(index);
                    });
                });
            } else if(layEvent === 'edit'){
                $.post(basePath+"/user/read",{id:data.id},function(data) {
                    layer.open({
                        title: "更新用户信息",
                        type: 1,
                        area: ['600px', '370px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: $(".user-update"),
                        success: function () {
                            $(".user-update input[name=id]").val(data.id);
                            $(".user-update input[name=username]").val(data.username);
                            $(".user-update .user_role").empty();
                            $.each(JSON.parse('${roleList}'),function (index,val) {
                                $(".user-update .user_role").append('<input type="checkbox" name="'+val.name+'" value="'+val.id+'" title="'+val.name+'">')
                            });
                            $.each(data.roles,function(index,val){
                                $(".user-update input[name="+val.name+"]").attr('checked', true);
                            });
                        }
                    });
                    form.render('checkbox');
                });
            }
        });

        //监听提交
        form.on('submit(search)', function(data){
            table.reload('userList', {where:data.field});
            return false;
        });

        //取消按钮
        $(".update_reset").click(function () {
            $(".user-update input").not("input[name=username]").val('');
        });

        //关闭按钮
        $('.close_btn').click(function () {
           layer.closeAll();
        });

        //更新用户信息提交
        form.on("submit(update_user)",function (data) {
           if (data.field.password!=data.field.repassword){
               layer.msg("两次密码不一致！");
               return false;
           }
           var roleIds=new Array();
           $(data.form).find("input:checked").each(function () {
               roleIds.push($(this).val());
           });
            data.field.roleIds=roleIds;
           $.post("${ctx}/user/update",data.field,function(data){
                layer.closeAll();
                layer.msg("修改成功！");
            });
        });

        //批量删除
        $("#getAll").click(function () {
            var checkStatus = table.checkStatus('userList')
                    ,data = checkStatus.data;
            if(data.length==0){
                layer.msg("请选择要删除的数据");
            }else{
                var ids=new Array();
                $.each(data,function (index,val) {
                    ids.push(val.id);
                });
                $.post("${ctx}/user/batchDelete",{ids:ids},function () {
                    layer.alert("删除成功",{icon:6},function (index) {
                        layer.close(index);
                        table.reload('userList');

                    });

                });
            }
        });

    });
</script>
</body>

</html>