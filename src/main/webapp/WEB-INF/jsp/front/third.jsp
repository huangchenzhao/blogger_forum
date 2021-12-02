<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
    <title>${news.title}</title>
    <link rel="stylesheet" href="${ctxStatic}/css/style.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/top.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/top.js"></script>
    <script type="text/javascript" src="/static/plugins/syntaxhighlighter_3.0.83/scripts/shCore.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/plugins/syntaxhighlighter_3.0.83/styles/shCoreDefault.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/jquery.1.4.2.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${ctxStatic}/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${ctxStatic}/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/global.css">
    <script th:inline="javascript">
        $(document).ready(function () {
            document.getElementById("guanzhu").addEventListener("click", function () {
                var my1=$("#hehehe").val()
                var my2=$("#hahaha").val()
                console.log(my1)
                console.log(my2)
                var data = {articleId:my2,userName:my1}
                if(my1==""){
                    alert("登陆后才能关注哦");
                }else {
                    console.log(data);
                    $.ajax({
                        url: "/up/upload",
                        type: "POST",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            if(data==null){
                                alert("已经关注过该文章啦！")
                            }else{
                                alert("关注成功,快去关注页面看看吧!")
                            }
                        }
                    });
                }
            });
        });
    </script>
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
</head>
<body>
<div class="bodycontent">
    <jsp:include page="header.jsp"/>
    <section class="blockGroup">
        <c:if test="${!empty news }">
            <h2 class="s_title">${news.title}</h2>
            <div class="views">
                <time>发布于:<fmt:formatDate value="${news.createDate}" pattern="yyyy-MM-dd"/></time>
                --${news.read}次检阅--作者：${news.createBy.username}
            </div>
            <div style="text-align:center">
                <button id="guanzhu"style="cursor: pointer; width: 50px;
                           height: 30px; color: white;
                           background-color:#1a7dde;
                           border: 0px; border-radius: 7px;
                           margin-top: 10px;">关注
                    <input type="hidden" value="${news.id}" id="hahaha"/>
                    <input type="hidden" value="${fns:getUser().getUsername()}" id="hehehe"/>
                </button>
            </div>
        </c:if>
        <c:if test="${empty news}">
            <article class="single">
                <div id="text">
                    暂无相关内容！
                </div>
            </article>
        </c:if>
    </section>

    <div class="layui-container">
        <div >
            <div >
                <c:if test="${!empty news }">
                    <div id="text">${news.content}</div>
                </c:if>
                <c:if test="${empty news}">
                    <pre>暂无相关内容！</pre>
                </c:if>

            </div>

        </div>
    </div>
    <section class="blockGroup">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
            <legend>评论</legend>
        </fieldset>
        <c:if test="${!empty commentPage.data}">
            <c:forEach items="${commentPage.data}" var="comment">
                <ul class="jieda" id="jieda">
                    <li data-id="111" class="jieda-daan">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="/static/images/topbg.jpg">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>${comment.id}</cite>
                                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                                    <i class="layui-badge fly-badge-vip">VIP3</i>
                                </a>
                            </div>

                            <div class="detail-hits">
                                <span><fmt:formatDate value="${comment.create_Date}" pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p>${comment.content}</p>
                        </div>
                        <div class="jieda-reply">
                                <span type="reply">
                                    <i class="iconfont icon-svgmoban53"></i>
                                    回复
                                </span>
                        </div>
                        <hr>
                    </li>
                </ul>
            </c:forEach>
        </c:if>
        <c:if test="${empty commentPage.data}">
                          <span style="color: #c2c2c2;font-size: 20px">
                              &nbsp;&nbsp;&nbsp;&nbsp;
                              o(╥﹏╥)o,暂无相关评论！快来添加第一条评论吧。。。
                          </span>
            <hr>
        </c:if>
    </section>

    <article>
        <form action="${ctx}/comment/add/${news.id}" method="post">
            <section class="blockGroup">
                <c:choose>
                    <c:when test="${fns:getUser().username eq null}">
                        <span style="color: #c2c2c2;font-size: 20px">哎呀呀,登陆后才能发表评论哦~</span>
                        <br>
                        （<a href="${ctx}/login">登录</a>/<a href="${ctx}/register">注册</a>）
                        <script id="container" name="content" lay-verify="required" type="text/plain" style="width:1050px;height:50px;align-content: center"></script>
                    </c:when>
                    <c:otherwise>
                        <img src="${ctxStatic}/images/editor.png"; width="45px" ;height="45px">
                        <span style="color: #399db6;font-size: 18px">啊哈哈，快来发表您的见解吧~</span>
                        <script id="container" name="content" lay-verify="required" type="text/plain" style="width:1050px;height:50px;align-content: center"></script>
                        <button type="button" id="btn">立即提交</button>
                        <button type="reset">重置</button>
                    </c:otherwise>
                </c:choose>
            </section>
        </form>
    </article>
</div>


</div>

<%@include file="footer.jsp" %>

<script type="text/javascript">
    var str=$('pre').attr("class");
    if(str){
        str=str.substring(str.indexOf(':')+1,str.indexOf(';'));

        if(str==='html'||str==='xml'){
            str='Xml';
        }if(str==='js'){
            str='JScript'
        }else{
            str=str[0].toUpperCase()+str.substr(1,str.length);
        }
        $.getScript('${ctxStatic}/plugins/syntaxhighlighter_3.0.83/scripts/shBrush'+str+'.js');
        console.log("测试代码类型："+str);
        console.log("js动态获取路径："+'${ctxStatic}/plugins/syntaxhighlighter_3.0.83/scripts/shBrush'+str+'.js');
        SyntaxHighlighter.defaults['first-line']='001';
        SyntaxHighlighter.defaults['pad-line-numbers']=true;
        SyntaxHighlighter.defaults['auto-links'] = false; //是否可以添加自动连接  默认连接可以点击 默认为true
        SyntaxHighlighter.defaults['collapse'] = false; //默认高亮代码是否折叠 true为折叠 false为不折叠
        SyntaxHighlighter.defaults['html-script'] = false; //是否开启html的混合形式 默认为false 为关闭
        SyntaxHighlighter.defaults['toolbar']=true; //黄绿色版权问号的显示和隐藏 默认为true 为显示
        SyntaxHighlighter.all();
    }

    //添加评论
    $("#btn").click(function () {
        var ue = UE.getEditor('container').getPlainTxt();
        $.post("${ctx}/comment/add",{"container":ue,"newsid":${news.id}},function(){
            alert("添加评论成功！！")
            $("input").val("");
        })
    })
</script>
</body>