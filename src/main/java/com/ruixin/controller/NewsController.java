package com.ruixin.controller;

import com.ruixin.bean.News;
import com.ruixin.bean.Role;
import com.ruixin.bean.Type;
import com.ruixin.bean.User;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.Views;
import com.ruixin.service.NewsService;
import com.ruixin.util.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * 跳转到新闻列表页
     */
    @GetMapping("/list")
    @RequiresPermissions("base:news:views")
    public String list(Model model){
        model.addAttribute("typeList",newsService.findTypeList());
        return Views.NEWS_LIST;
    }

    /**
     * 新闻搜索
     * @param pages
     * @param news
     * @return
     */
    @PostMapping("/search")
    @ResponseBody
    @RequiresPermissions("base:news:views")
    public Page<News> list(Page<News> pages, News news){
        User user= UserUtils.getUser();
        if(user!=null){
            for (Role role:user.getRoles()){
                if(Role.ALL_NEWS.equals(role.getName())){
                    return newsService.findPageList(pages,news);
                }
            }
            news.setCreateBy(user);
            return newsService.findPageList(pages,news);
        }
            return null;
    }

    /**
     * 查看新闻
     * @param news
     * @return
     */
    @PostMapping("/read")
    @ResponseBody
    @RequiresPermissions("base:news:views")
    public News read(News news){
        return newsService.get(news);
    }

    /**
     * 更新博文
     * @param news
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    @RequiresPermissions("base:news:edit")
    public String update(News news){
        newsService.save(news);
        return "success";
    }

    /**
     * 删除博文
     * @param news
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @RequiresPermissions("base:news:del")
    public String delete(News news){
        news.setStatus("1");
        newsService.save(news);
        return "success";
    }

    /**
     * 批量删除博文
     * @param ids
     * @return
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    @RequiresPermissions("base:news:del")
    public String batchDelete(@RequestParam("ids[]") String[] ids){
        newsService.batchDelete(ids);
        return "success";
    }

    /**
     * 跳转到博文添加页
     * @param model
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("base:news:views")
    public String add(Model model){
        model.addAttribute("typeList",newsService.findTypeList());
        return Views.NEWS_ADD;
    }

    /**
     * 添加文章
     * @param news
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions("base:user:edit")
    public String add(News news){
        newsService.save(news);
        return Views.NEWS_ADD;
    }
}
