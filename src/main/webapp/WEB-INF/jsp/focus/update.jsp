<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<form class="layui-form news_read" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label">文章标题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input newsName" name="title" lay-verify="required" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">栏目</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="typeName" disabled>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">添加时间</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="createDate" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作者</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" name="username" disabled>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">文章内容</label>
        <div class="layui-input-block news_content">
            <script id="container" name="content" type="text/plain" style="width:700px;"></script>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-primary close_btn">关闭</button>
        </div>
    </div>
</form>

<form class="layui-form news_update" action="${ctx}/news/update" method="post" hidden>
    <div class="layui-form-item">
        <input type="text" class="layui-input" name="id" lay-verify="required" style="display: none">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">文章标题</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input newsName" name="title" lay-verify="required">
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
        <div class="layui-input-block news_content">
            <script id="container1" name="content" lay-verify="required" type="text/plain" style="width:700px;"></script>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addNews">立即提交</button>
            <button type="button" class="layui-btn layui-btn-primary close_btn">关闭</button>
        </div>
    </div>
</form>