<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>数据列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctxStatic}/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctxStatic}/plugins/font-awesome/css/font-awesome.min.css">
    <style>
        .layui-form-selected dl{
            z-index:10000;
        }
    </style>
</head>

<body style=" background-color: gainsboro;">

<div style="margin:0px; background-color: white; margin:0 10px;">
    <div id="container" style="min-width:400px;max-width:99%;height:580px"></div>
</div>
<script type="text/javascript" src="${ctxStatic}/js/jquery.1.4.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/hcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/hcharts/modules/exporting.js"></script>
<script>
    $(function () {
        var month=new Array();
        var data1=new Array();
        $.post("${ctx}/sys/data/getData",function (data) {
            $.each(data,function (index,val) {
                month.push(val.month);
                data1.push(val.data);
            });
            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '文章发表量'
                },
                subtitle: {
                    text: '数据来源: hblog'
                },
                xAxis: {
                    categories: month,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '文章发表量量 (篇)'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y} 篇</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        borderWidth: 0
                    }
                },
                series:  [{
                    name: '博文',
                    data: data1
                }]
            });
        });


    });
</script>
</body>

</html>