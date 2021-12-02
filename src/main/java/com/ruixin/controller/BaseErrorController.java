package com.ruixin.controller;

import com.ruixin.common.utils.Views;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {

    @Override
    public String getErrorPath(){
        return Views.NOT_FOUNT;
    }

    @RequestMapping
    public String error() {
        return Views.NOT_FOUNT;
    }

}