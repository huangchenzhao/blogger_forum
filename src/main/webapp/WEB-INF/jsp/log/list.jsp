<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>语音设置</title>
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

<div class="content">
    <div class="input-panel">
        <form id="form1" method="post" action="${ctx}/audio">
            <dl>
                <dt>语音播报设置</dt>
                <dd>
                    <p>语速<span class="error"></span></p>
                    <select name="speed">
                        <option value="慢">慢</option>
                        <option value="适中">适中</option>
                        <option value="快">快</option>
                    </select>
                </dd>
                <dd>
                    <p>语音播报性别<span class="error"></span></p>
                    <p><input type="radio" name="gender" checked value="男" /> 男
                        <input type="radio" name="gender" value="女" style="margin-left: 60px;"/> 女
                    </p>
                </dd>
                <dd class="btn-groups">
                    <input type="submit" value="确 定" class="btn btn-primary" />
                    <input type="reset" value="重 置" class="btn btn-success" />
                </dd>
            </dl>
        </form>
    </div>
</div>
</body>

</html>