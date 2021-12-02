package com.ruixin.controller;

import com.ruixin.bean.Menu;
import com.ruixin.common.utils.Views;
import com.ruixin.service.MenuService;
import com.ruixin.util.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台控制管理
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MenuService menuService;

    /**
     *  跳转到后台页
     */
    @RequiresPermissions("base:admin:views")
    @GetMapping("/index")
    public String index(){
        return Views.ADMIN;
    }

    /**
     * 后台主页
     */
    @RequiresPermissions("base:admin:views")
    @GetMapping("/main")
    public String main(){
        return Views.ADMIN_MAIN;
    }

    /**
     * 根据权限获取菜单
     * @return
     */
    @RequiresPermissions("base:admin:views")
    @PostMapping("/getMenu")
    @ResponseBody
    public List<Menu> getMenu(){
        return UserUtils.getMenuinfoList();
    }
}
