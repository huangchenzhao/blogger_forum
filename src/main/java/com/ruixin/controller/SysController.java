package com.ruixin.controller;

import com.ruixin.bean.Type;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.Views;
import com.ruixin.service.NewsService;
import com.ruixin.service.TypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TypeService typeService;

    /**
     * 跳转到系统数据页
     * @return
     */
    @GetMapping("/data/list")
    @RequiresPermissions("base:data:views")
    public String dataList(){
        return Views.SYS_DATA_LIST;
    }

    /**
     * 跳转到系统数据页
     * @return
     */
    @GetMapping("/data/list1")
    @RequiresPermissions("base:data:views")
    public String dataList1(){
        return Views.SYS_DATA_LIST1;
    }

    /**
     * 获取数据
     */
    @PostMapping("/data/getData")
    @RequiresPermissions("base:data:views")
    @ResponseBody
    public List<Map<String,Object>> getData(){
        return newsService.getData();
    }

    /**
     * 获取数据
     */
    @PostMapping("/data/getData1")
    @RequiresPermissions("base:data:views")
    @ResponseBody
    public List<Map<String,Object>> getData1(){
        return newsService.getData1();
    }

    /**
     * 跳转到前台栏目列表页
     * @return
     */
    @GetMapping("/type/list")
    @RequiresPermissions("base:type:views")
    public String typeList(){
        return Views.SYS_TYPE_LIST;
    }

    /**
     * 查询栏目
     * @param page
     * @param type
     * @return
     */
    @PostMapping("/type/search")
    @ResponseBody
    @RequiresPermissions("base:type:views")
    public Page<Type> typeSearch(Page<Type> page,Type type){
        return typeService.findPageList(page,type);
    }

    /**
     * 删除栏目
     * @param type
     * @return
     */
    @PostMapping("/type/delete")
    @ResponseBody
    @RequiresPermissions("base:type:del")
    public String typeDelete(Type type,Model model){
        type.setStatus("1");
        typeService.save(type);
        model.addAttribute("typeList",newsService.findTypeList());
        return Views.INDEX;
    }

    /**
     * 更新栏目
     * @param type
     * @return
     */
    @PostMapping("/type/update")
    @ResponseBody
    @RequiresPermissions("base:type:edit")
    public String typeUpdate(Type type,Model model){
        typeService.save(type);
        model.addAttribute("typeList",newsService.findTypeList());
        return Views.INDEX;
    }
}
