<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>日记</title>
    <link rel="stylesheet" href="${ctxStatic}/css/style.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/top.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/top.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.1.4.2.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/global.css">
    <style type="text/css" rel="stylesheet" >
        .archive-item {
            padding: 0 !important;
            height: 2.5em;
        }

        .archive-item p{
            display: block;
            float: left;
            line-height: 20px;
            width: 70%;

        }
        .archive-item time{
            display: block;
            line-height: 20px;
            float: right;
            padding-top: 1em;

        }
    </style>
</head>
<body>
<div class="bodycontent">
    <jsp:include page="header.jsp"/>
    <div class="layui-container">
        <ul class="fly-list">
            <c:if test="${empty page.data}">
                <span style="color: #c2c2c2;font-size: 20px"> &nbsp;&nbsp;&nbsp;&nbsp;该栏目还没有相关内容</span>
                <script type="text/javascript">
                    $(function() {
                        $("#page").empty();
                    });

                </script>
            </c:if>
            <c:forEach items="${page.data}" var="news">
                <li>
                    <a href="user/home.html" class="fly-avatar">
                        <img src="/static/images/topbg.jpg">
                    </a>
                    <h2>
                        <a class="layui-badge">动态</a>
                        <a href="${ctx}/third/${news.id}">${news.title}</a>
                    </h2>
                    <div class="fly-list-info">
                        <a href="user/home.html" link>
                            <cite>ID：${news.id}</cite>
                            <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                            <i class="layui-badge fly-badge-vip">不知道</i>
                        </a>
                        <span><fmt:formatDate value="${news.createDate}" pattern="yyyy-MM-dd"/></span>
                        <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                        <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                        <a href="${ctx}/third/${news.id}"/><span class="layui-badge fly-badge-accept layui-hide-xs">阅读全文</span></a>
                        <span class="fly-list-nums"> <i class="iconfont icon-pinglun1" title="回答"></i> 66</span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div style="text-align: center" id="page">
        <div class="laypage-main">
            <c:choose>
                <c:when test="${page.page-1 gt 0}">
                    <a href="${ctx}/second/${typeId}?page=${page.page-1}">上一页</a>
                </c:when>
                <c:otherwise>
                    <a href="javascript:void(0);" class="laypage-curr">首页</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${page.page+1 <= page.pageCount}">
                    <a href="${ctx}/second/${typeId}?page=${page.page+1}" class="laypage-next">下一页</a>
                </c:when>
                <c:otherwise>
                    <a href="javascript:void(0);" class="laypage-curr">尾页</a>
                </c:otherwise>
            </c:choose>
        </div>

    </div>

<%@include file="footer.jsp" %>