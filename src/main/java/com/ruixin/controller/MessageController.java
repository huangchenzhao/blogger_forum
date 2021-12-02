package com.ruixin.controller;

import com.ruixin.common.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author yilin
 * @CreateTime 2021/7/29 9:14
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/message")
public class MessageController {

    @RequestMapping("/success")
    public String success(){ return Views.SUCCESS;}

    @RequestMapping("/error")
    public String error(){ return Views.ERROR;}
}
