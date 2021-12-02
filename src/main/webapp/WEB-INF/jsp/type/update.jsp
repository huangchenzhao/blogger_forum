<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form type_edit" style="width:80%;" hidden>
    <div class="layui-form-item">
        <input type="hidden" name="id" class="layui-input">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">栏目名</label>
        <div class="layui-input-block">
            <input type="text" name="name" class="layui-input" lay-verify="required" placeholder="请输入栏目名">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">序号</label>
        <div class="layui-input-block">
            <input type="number" name="sort" class="layui-input" lay-verify="required|number" placeholder="请输入序号">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="updatetype">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary close_btn">取消</button>
        </div>
    </div>
</form>

<form class="layui-form type_add" style="width:80%;" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label">栏目名</label>
        <div class="layui-input-block">
            <input type="text" name="name" class="layui-input" lay-verify="required" placeholder="请输入栏目名">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">序号</label>
        <div class="layui-input-block">
            <input type="number" name="sort" class="layui-input" lay-verify="required|number" placeholder="请输入序号">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addtype">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary close_btn">取消</button>
        </div>
    </div>
</form>