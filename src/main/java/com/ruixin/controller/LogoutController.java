package com.ruixin.controller;

import com.ruixin.bean.User;
import com.ruixin.util.UserUtils;
import com.ruixin.common.utils.Views;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class LogoutController {

    /**
     * 退出登录
     * @return
     * @throws IOException
     */
    @GetMapping("/logout")
    public String logout() throws IOException {
        User user= UserUtils.getUser();
        // 如果已经登录，则跳转到管理首页
        if(user != null){
            SecurityUtils.getSubject().logout();
        }
        return Views.REDIRECT_LOGIN;
    }

}
