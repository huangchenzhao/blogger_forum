package com.ruixin.controller;

import com.ruixin.bean.User;
import com.ruixin.service.UserService;
import com.ruixin.util.UserUtils;
import com.ruixin.common.utils.Views;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 登录管理页
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页
     */
    @GetMapping("/login")
    public String login() {
        User user = UserUtils.getPrincipal();
        if (user != null) {
            return Views.REDIRECT_ADMIN;
        }
        return Views.LOGIN;
    }

    /**
     * 登录验证
     *
     * @param user
     * @param map
     * @return
     */
    @PostMapping("/login")
    public String login(User user, ModelMap map) {
        if (user != null) {
            if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
                return Views.LOGIN;
            }
            AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), true);
            if (token == null) {
                map.put("message", "用户名或密码错误");
                return Views.LOGIN;
            }
            //记住我
            ((UsernamePasswordToken) token).setRememberMe(true);
            try {
                SecurityUtils.getSubject().login(token);
                return Views.REDIRECT_ADMIN;
            } catch (AuthenticationException e) {
                if (e instanceof UnknownAccountException) {
                    map.put("message", "用户不存在");
                } else if (e instanceof LockedAccountException) {
                    map.put("message", "账户未激活");
                    map.put("status", 2);
                    map.put("user", user);
                } else if (e instanceof DisabledAccountException) {
                    map.put("message", "账户未启用");
                }
                e.printStackTrace();
                map.put("message", "用户认证失败");
            }
            return Views.LOGIN;
        } else {
            map.put("message", "未知异常");
            return Views.LOGIN;
        }
    }

    /**
     * 安卓端登录
     *
     * @param user
     * @return
     */
    @PostMapping("/loginAndroid")
    @ResponseBody
    public User loginAndroid( User user) {

        System.out.println("安卓端登录登录进到方法");

        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), true);
        //记住我
        ((UsernamePasswordToken) token).setRememberMe(true);

        SecurityUtils.getSubject().login(token);

        //根据传进来的用户名查询用户信息
        String username = user.getUsername();

        User user1 = userService.findByAccount(username);

        System.out.println("测试安卓登录用户名信息："+user1.getEmail()+",user1:"+user1);

        return user;
    }




}
