package com.ruixin.controller;

import com.ruixin.common.utils.Views;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-27 19:15
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/focus")
public class FocusController {
    public static String user_focus=null;
    @RequestMapping("/first")
    public String list() {
        return Views.FOCUS_PAGE;
    }
    @RequestMapping("/list")
    public String showList() {
        return Views.FOCUS_LIST;
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(@RequestParam("userName") String userName) {
        System.out.println(userName);
        user_focus=userName;
        return null;
    }
}
