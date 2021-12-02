package com.ruixin.controller;

import com.alibaba.fastjson.JSON;
import com.database.data.DisplayFocus;
import com.database.data.Focus;
import com.database.mapper.IFocusMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-29 1:25
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/display")
public class DisplayFocusController {
    @Resource
    private IFocusMapper iFocusMapper;

    @RequestMapping("/search")
    @ResponseBody
    public String findFocus(){
        List<DisplayFocus> displayFocusList=iFocusMapper.queryAll(FocusController.user_focus);
        String jsonString = JSON.toJSONString(displayFocusList);
        String focuss="{\"code\":\"0\",\"msg\":\"ok\",\"count\":100,\"data\":"+jsonString+"}";
        System.out.println("-----"+focuss);
        return focuss;
    }
}
