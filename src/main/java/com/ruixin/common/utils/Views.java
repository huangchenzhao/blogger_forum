package com.ruixin.common.utils;


/**
 * 视图管理类
 */
public interface Views {

    //登录页
    String LOGIN="/user/login";
    String REDIRECT_LOGIN="redirect:/login";

    //信息提醒页
    String ERROR="/message/error";
    String SUCCESS="/message/success";
    String NOT_FOUNT="/message/404";

    //登录成功页
    String REDIRECT_ADMIN="redirect:/admin/index";
    String ADMIN="/admin/index";

    //主页
    String ADMIN_MAIN="/admin/main";

    //注册页
    String REGISTER="/user/register";

    //用户
    String USER_LIST="/user/list";
    String USER_ADD="/user/add";

    //新闻
    String NEWS_LIST="/news/list";
    String NEWS_ADD="/news/add";

    //日志
    String LOG_LIST="/log/list";

    //前端页面
    String INDEX="/front/index";
    String SECOND="/front/second";
    String THIRD="/front/third";
    String ABOUT="/front/about";

    //系统管理
    String SYS_DATA_LIST="/data/list";
    String SYS_DATA_LIST1="/data/list1";
    String SYS_TYPE_LIST="/type/list";

    //友情链接
    String LINK_LIST="/link/list";
    String LINK_ADD="/link/add";

    //用来测试的页面
    String TEXT_PAGE="/front/test";

    String AUDIO="/message/AudioSuccess";

    String FOCUS_LIST="/focus/list";

    String FOCUS_PAGE="/focus/first";

    String FACE_REGISTER="/face/register";

    String FACE_LOGIN="/face/facelogin";
}
