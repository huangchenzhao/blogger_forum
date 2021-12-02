var navs = [{
    "title": "用户管理",
    "icon": "&#xe613;",
    "spread": false,
    "children": [{
        "title": "用户列表",
        "icon": "&#xe63c;",
        "href": basePath+"/user/list"
    }, {
        "title": "用户添加",
        "icon": "&#xe63c;",
        "href": basePath+"/user/add"
    }]
}, {
    "title": "文章管理",
    "icon": "&#xe63c;",
    "spread": false,
    "children": [{
        "title": "文章列表",
        "icon": "fa-table",
        "href": basePath+"/news/list"
    }, {
        "title": "文章添加",
        "icon": "fa-navicon",
        "href": basePath+"/news/add"
    }]
},{
    "title": "链接管理",
    "icon": "&#xe641;",
    "spread": false,
    "children": [{
        "title": "链接列表",
        "icon": "fa-table",
        "href": basePath+"/link/list"
    }, {
        "title": "链接添加",
        "icon": "fa-navicon",
        "href": basePath+"/link/add"
    }]
},{
    "title": "数据管理",
    "icon": "&#xe62c;",
    "spread": false,
    "children": [{
        "title": "文章发表数据",
        "icon": "&#xe629",
        "href": basePath+"/sys/data/list"
    },{
        "title": "文章浏览数据",
        "icon": "&#xe629",
        "href": basePath+"/sys/data/list1"
    }]
},{
    "title": "系统管理",
    "icon": "fa-cogs",
    "spread": false,
    "children": [{
        "title": "前台栏目列表",
        "icon": "&#xe631;",
        "href": basePath+"/sys/type/list"
    }]
},{
    "title": "音频管理",
    "icon": "&#xe6fc;",
    "spread": false,
    "children": [{
        "title": "音频设置",
        "icon": "fa-table",
        "href": basePath+"/log/list"
    }]
},{
    "title": "关注管理",
    "icon": "&#xe628;",
    "spread": false,
    "children": [{
        "title": "关注设置",
        "icon": "fa-table",
        "href": basePath+"/focus/first"
    }]
},{
    "title": "人脸管理",
    "icon": "&#xe616;",
    "spread": false,
    "children": [{
        "title": "注册人脸",
        "icon": "fa-table",
        "href": basePath+"/face/register"
    },{
        "title": "删除人脸信息",
        "icon": "fa-table",
        "href": basePath+"/focus/list"
    }]
}];