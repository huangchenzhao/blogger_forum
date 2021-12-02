package com.ruixin.controller;

import com.ruixin.common.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yilin
 * @CreateTime 2021/7/28 21:05
 * @Version 1.0.0
 **/
@Controller
public class FaceLoginController {
    @RequestMapping("/facelogin")
    public String faceLogin(){
        return Views.FACE_LOGIN;
    }
}
