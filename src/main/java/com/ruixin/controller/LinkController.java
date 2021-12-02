package com.ruixin.controller;

import com.ruixin.bean.Link;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.Views;
import com.ruixin.service.LinkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    /**
     * 跳转到友情链接列表页
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("base:link:views")
    public String list(){
        return Views.LINK_LIST;
    }

    /**
     * 友情链接搜索
     * @param page
     * @param link
     * @return
     */
    @PostMapping("/search")
    @RequiresPermissions("base:link:views")
    @ResponseBody
    public Page<Link> search(Page<Link> page,Link link){
        return linkService.findPageList(page,link);
    }

    /**
     * 删除友情链接
     * @param link
     * @return
     */
    @PostMapping("/delete")
    @RequiresPermissions("base:link:edit")
    @ResponseBody
    public String delete(Link link){
        link.setStatus("1");
        linkService.save(link);
        return "success";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/bathDelete")
    @RequiresPermissions("base:link:del")
    @ResponseBody
    public String bathDelete(@RequestParam("ids[]") String[] ids){
        linkService.bathDelete(ids);
        return "success";
    }

    /**
     * 更新链接
     */
    @RequiresPermissions("base:link:edit")
    @PostMapping("/update")
    @ResponseBody
    public String update(Link link){
        linkService.save(link);
        return "success";
    }

    /**
     * 添加链接
     * @param link
     * @return
     */
    @PostMapping("/add")
    @RequiresPermissions("base:link:edit")
    @ResponseBody
    public String add(Link link){
        linkService.save(link);
        return "success";
    }

    /**
     * 跳转到链接添加页
     */
    @GetMapping("/add")
    @RequiresPermissions("base:link:views")
    public String add(){
        return Views.LINK_ADD;
    }
}
