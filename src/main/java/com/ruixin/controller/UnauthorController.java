package com.ruixin.controller;

import com.ruixin.common.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 未授权管理
 */
@Controller
public class UnauthorController {

    /**
     * 未授权
     */
    @GetMapping("/unauthor")
    public String unauthor(Model model){
        model.addAttribute("msg","您暂时没有这个权限呦！");
        return Views.ERROR;
    }

}
