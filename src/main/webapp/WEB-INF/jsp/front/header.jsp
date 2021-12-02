<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a href="/"><font style="font-size: 50px;color: #c2c2c2">HBLOG</font></a>
        <ul class="layui-nav fly-nav-user">
            <c:choose>
                <c:when test="${fns:getUser().username eq null}">
                    <li class="layui-nav-item"><a class="iconfont icon-touxiang layui-hide-xs" href="${ctx}/login"></a></li>
                    <li class="layui-nav-item"><a href="${ctx}/login">账号登录</a></li>
                    <li class="layui-nav-item"><a href="${ctx}/facelogin">人脸登录</a></li>
                    <li class="layui-nav-item"><a href="${ctx}/register">注册</a></li>
                </c:when>
                <c:otherwise>
                    <ul class="layui-nav admin-header-item">
                        <li class="layui-nav-item">
                            <a href="${ctx}/login" class="admin-header-user"><span>${fns:getUser().getUsername()}</span></a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a href="${ctx}/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul class="layui-clear">
            <li class="layui-hide-xs layui-this"><a href="${ctx}/index">首页</a></li>
            <c:forEach items="${typeList}" var="type">
                <li><a href="${ctx}/second/${type.id}">${type.name}</a></li>
            </c:forEach>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

            <!-- 用户登入后显示 -->
          <!--   <c:choose>
                <c:when test="${fns:getUser().username eq null}">

                </c:when>
                <c:otherwise>
                    <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/">${fns:getUser().username}发表的文章</a></li>
                    <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/">${fns:getUser().username}收藏的文章</a></li>
                </c:otherwise>
            </c:choose> -->

        </ul>

      <!--   <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="jie/add.html" class="layui-btn">搜索文章</a>
        </div> -->

    </div>
</div>
</header>