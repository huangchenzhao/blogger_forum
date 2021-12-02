package com.ruixin.controller;

import com.alibaba.fastjson.JSON;
import com.database.data.Focus;
import com.database.mapper.IFocusMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-27 20:10
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/up")
public class AddFocusController {
    @Resource
    private IFocusMapper iFocusMapper;
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadData(@RequestParam("articleId") String articleId, @RequestParam("userName") String userName){
        Focus focus=new Focus(userName, new Integer(Integer.parseInt(articleId)));
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if(iFocusMapper.query(focus)!=null){
                return null;
            }else {
                iFocusMapper.add(focus);
                hashMap.put("articleId",Integer.parseInt(articleId));
                hashMap.put("userName",userName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject(hashMap).toString();
    }
}
