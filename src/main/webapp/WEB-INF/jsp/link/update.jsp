<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form link_edit" style="width:80%;" hidden>
    <div class="layui-form-item">
        <input type="hidden" name="id" class="layui-input">
    </div>
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
            <button class="layui-btn" lay-submit="" lay-filter="updateLinks">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary close_btn">取消</button>
        </div>
    </div>
</form>