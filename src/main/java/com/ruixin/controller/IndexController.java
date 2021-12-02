package com.ruixin.controller;

import com.ruixin.bean.Comment;
import com.ruixin.bean.News;
import com.ruixin.bean.Type;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.Views;
import com.ruixin.service.CommentService;
import com.ruixin.service.LinkService;
import com.ruixin.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 前台管理类
 */
@Controller
public class IndexController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private CommentService commentService;

    /**
     * 跳转到首页
     * @param model
     * @param page
     * @return
     */
    @GetMapping({"/","/index"})
    public String index(Model model, Page<News> page){
        model.addAttribute("typeList",newsService.findTypeList());
        Page<News> page1=newsService.findPageList(page,new News());
        model.addAttribute("page",page1);
        model.addAttribute("links",linkService.findList(null));
        return Views.INDEX;
    }

    /**
     * 跳转到二级页面
     * @param model
     * @return
     */
    @GetMapping("/second/{typeId}")
    public String second(Model model,@PathVariable("typeId")Integer typeId,Page<News> page){
        News news=new News();
        news.setTypeId(typeId);
        model.addAttribute("typeList",newsService.findTypeList());
        model.addAttribute("typeId",typeId);
        model.addAttribute("page",newsService.findPageList(page,news));
        return Views.SECOND;
    }

    /**
     * 用来测试的页面
     * @return
     */
    @GetMapping("/font/test")
    public String test(){
        System.out.println("跳进测试的页面");
        return Views.TEXT_PAGE;
    }

    /**
     * 跳转到三级页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/third/{id}")
    public String third(Model model, @PathVariable("id")int id, Page<Comment> page){
        //加载内容
        model.addAttribute("typeList",newsService.findTypeList());
        News news=newsService.get(id);
        model.addAttribute("news",news);
        news.setRead(String.valueOf(Integer.parseInt(news.getRead())+1));
        newsService.save(news);
        //加载评论
        Page<Comment> commentPage=commentService.getFindId(page,new Comment(),id);
        model.addAttribute("commentPage",commentPage);
        return Views.THIRD;
    }

    /**
     * 关于我
     */
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("typeList",newsService.findTypeList());
        return Views.ABOUT;
    }

    /***
     * 以下是安卓端访问部分
     */

    /**
     *查找博文列表
     * @return
     */
    @ResponseBody
    public List<Type> newsList(){
        return newsService.findTypeList();
    }

}
