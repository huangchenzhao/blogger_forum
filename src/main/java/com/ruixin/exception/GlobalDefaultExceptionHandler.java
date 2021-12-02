package com.ruixin.exception;

import com.ruixin.common.utils.Views;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruixin on 2017/5/12.
 * Description:全局异常捕获
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public String defaultErrorHandler(HttpServletRequest req, Exception e)
    {
        e.printStackTrace();
        req.setAttribute("msg","o(╥﹏╥)o 程序出现了点小问题呦！");
        return Views.ERROR;
    }
}
