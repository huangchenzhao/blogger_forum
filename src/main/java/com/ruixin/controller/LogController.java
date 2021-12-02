package com.ruixin.controller;

import com.ruixin.bean.Log;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.Views;
import com.ruixin.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 跳转到日志管理页
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("base:log:views")
    public String list(){
        return Views.LOG_LIST;
    }

    /**
     * 搜索日志
     * @param page
     * @param log
     * @return
     */
    @PostMapping("/search")
    @ResponseBody
    @RequiresPermissions("base:log:views")
    public Page<Log> search(Page<Log> page,Log log){
        return logService.findPageList(page,log);
    }

    /**
     * 删除日志
     * @param log
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("base:log:del")
    public String delete(Log log){
        logService.delete(log);
        return "success";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/bathDelete")
    @ResponseBody
    @RequiresPermissions("base:log:del")
    public String bathDelete(@RequestParam("ids[]")String[] ids){
        logService.bathDelete(ids);
        return "success";
    }
}
