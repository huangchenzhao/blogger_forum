<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <title>博客论坛</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/global.css">
    <script type="text/javascript" src="${ctxStatic}/js/jquery.1.4.2.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
    <script th:inline="javascript">
        $(document).ready(function () {
            document.getElementById("guanzhu").addEventListener("click", function () {
                var my1=$("#hehehe").val()
                var my2=$("#hahaha").val()
                console.log(my1)
                console.log(my2)
                var data = {articleId:my2,userName:my1}
                console.log(data);
                $.ajax({
                    url: "/up/upload",
                    type: "POST",
                    dataType: "json",
                    data: data,
                    success: function (data) {
                        console.log("上传成功");
                        console.log(data);
                    }
                });
            });
        });
    </script>
</head>
<body>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel">
                <div class="fly-panel-title fly-filter">
                    <a>New Post 最新文章</a>
                </div>
                <ul class="fly-list">
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
                                <button id="guanzhu">
                                    <cite>ID：${news.id}</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">关注</i>
                                    <input type="hidden" value="${ctx}/third/${news.id}" id="hahaha"/>
                                    <input type="hidden" value="${ctx}/third/${fns:getUser().getUsername()}" id="hehehe"/>
                                </button>
                                <span><fmt:formatDate value="${news.createDate}" pattern="yyyy-MM-dd"/></span>
                                <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> 60</span>
                                <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                                <a href="${ctx}/third/${news.id}"/><span class="layui-badge fly-badge-accept layui-hide-xs">阅读全文</span></a>
                                <span class="fly-list-nums"> <i class="iconfont icon-pinglun1" title="回答"></i> 66</span>
                            </div>

                        </li>
                    </c:forEach>



                </ul>

                <div style="text-align: center">
                    <div class="laypage-main">
                        <c:choose>
                            <c:when test="${page.page-1 gt 0}">
                                <a href="${ctx}/index?page=${page.page-1}">上一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" class="laypage-curr">首页</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${page.page+1 <= page.pageCount}">
                                <a href="${ctx}/index?page=${page.page+1}" class="laypage-next">下一页</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" class="laypage-curr">尾页</a>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>

            </div>
        </div>
        <div class="layui-col-md4">

            <div class="fly-panel">
                <h3 class="fly-panel-title">友情链接</h3>
                <ul class="fly-panel-main fly-list-static" id="link">
                    <c:forEach items="${links}" var="links" begin="0" end="19">
                        <li><a href='${links.url}' target="_blank" onload="textFunction()">${links.description}</a></li>
                    </c:forEach>
                </ul>
                <div class="fly-panel" style="margin-bottom: 0;">

                    <c:if test="${links.size()>10}">
                        <div style="text-align: center;">
                            <a class="laypage-next" id="allLink">>>>共${links.size()}条链接，展开全部链接>>></a>
                            <a class="laypage-next" id="tenLink">收起</a>
                        </div>
                    </c:if>
                    <c:if test="${links.size()==0}">
                        <div style="text-align: center;">
                            <a class="laypage-next">暂无链接</a>
                        </div>
                    </c:if>

                </div>

            </div>


        <!--     <div class="fly-panel fly-signin">
                <div class="fly-panel-title">
                    签到
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinHelp">说明</a>
                    <i class="fly-mid"></i>
                    <a href="javascript:;" class="fly-link" id="LAY_signinTop">活跃榜<span class="layui-badge-dot"></span></a>
                    <span class="fly-signin-days">已连续签到<cite>16</cite>天</span>
                </div>
                <div class="fly-panel-main fly-signin-main">
                    <button class="layui-btn layui-btn-danger" id="LAY_signin">今日签到</button>
                    <span>可获得<cite>5</cite>飞吻</span>

                    <!-- 已签到状态 -->
                    <!--
                    <button class="layui-btn layui-btn-disabled">今日已签到</button>
                    <span>获得了<cite>20</cite>飞吻</span>
                   
                </div>
            </div>

            <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
                <h3 class="fly-panel-title">回贴周榜</h3>
                <dl>
                    <dd>暂无数据...</dd>
                </dl>
            </div> -->

        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<script type="text/javascript">
    function textFunction(){
        var obj = {0:0,1:1,2:2,3:3,4:4,5:5,6:6,7:7,8:8,9:9};
        $("#tenLink").hide();
        $("#link li").css("display","none");
        $.each(obj,function (i) {
            $("#link li:eq("+obj[i]+")").show();
        })
    };
    window.onload=textFunction;

    $("#allLink").click(function () {
        $("#link li").show();
        $("#allLink").hide();
        $("#tenLink").show();
    });

    $("#tenLink").click(function(){
        textFunction();
        $("#allLink").show();
    });
</script>
</body>
</html>

