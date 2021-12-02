package com.ruixin.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruixin.bean.User;
import com.ruixin.common.entity.Page;
import com.ruixin.common.utils.MD5;
import com.ruixin.common.utils.MailUtil;
import com.ruixin.common.utils.StringUtils;
import com.ruixin.common.utils.Views;
import com.ruixin.service.RoleService;
import com.ruixin.service.UserService;
import com.ruixin.util.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailUtil mailUtil;

    @Value("${mail.href}")
    public String href;

    /**
     * 重新发送激活邮件
     * @param user
     * @return
     */
    @PostMapping("/reEmail")
    @ResponseBody
    public String reEmail(User user){
        user=userService.findByAccount(user.getUsername());
        if(user!=null&&User.STATUS_AUDIT.equals(user.getStatus())){
            String[] sender={user.getEmail()};
            mailUtil.sendHtmlMail(sender,"hblog账户激活邮件","<h1>请点击<a href='"+href+"activate/"+user.getCode()+"'>此链接</a>以激活账号</h1>");
            return "success";
        }
        return "error";
    }

    /**
     * 跳转到用户列表页
     */
    @RequiresPermissions("base:user:views")
    @GetMapping("/user/list")
    public String list(Model model){
        model.addAttribute("roleList", JSONArray.toJSON(roleService.findRole(null)));
        return Views.USER_LIST;
    }

    /**
     * 搜索用户
     * @param pages
     * @param user
     * @return
     */
    @RequiresPermissions("base:user:views")
    @PostMapping("/user/search")
    @ResponseBody
    public Page<User> search(Page<User> pages,User user){
        return userService.findPageList(pages,user);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @RequiresPermissions("base:user:del")
    @PostMapping("/user/delete")
    @ResponseBody
    public String delete(User user){
        user.setStatus("1");
        userService.save(user);
        return "success";
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequiresPermissions("base:user:edit")
    @PostMapping("/user/update")
    @ResponseBody
    public String update(User user,@RequestParam("roleIds[]") String[] roleIds){
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(MD5.md5(user.getUsername(),user.getPassword()));
            userService.save(user);
        }
        roleService.updateUserRole(user.getId(),roleIds);
        return "success";
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @RequiresPermissions("base:user:views")
    @PostMapping("/user/read")
    @ResponseBody
    public User read(@RequestParam("id") int id){
        return UserUtils.get(id);
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequiresPermissions("base:user:del")
    @PostMapping("/user/batchDelete")
    @ResponseBody
    public String batchDelete(@RequestParam("ids[]") String[] ids){
        userService.batchDelete(ids);
        return "success";
    }

    /**
     * 跳转到用户添加页
     * @param model
     * @return
     */
    @RequiresPermissions("base:user:views")
    @GetMapping("/user/add")
    public String add(Model model){
        model.addAttribute("roles",roleService.findRole(null));
        return Views.USER_ADD;
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @RequiresPermissions("base:user:views")
    @PostMapping("/user/findCount")
    @ResponseBody
    public String findByCount(@RequestParam("username") String username){
        User user=userService.findByAccount(username);
        if(user!=null){
            return "false";
        }
        return "true";
    }

    /**
     * 添加用户
     * @param user
     * @param role
     * @return
     */
    @RequiresPermissions("base:user:edit")
    @PostMapping("/user/add")
    @ResponseBody
    public String add(User user,@RequestParam("userRoles[]")String[] role){
        user.setUserPassword(user.getPassword());
        user.setPassword(MD5.md5(user.getUsername(),user.getPassword()));
        user.setStatus("0");
        user.setAvatar("/static/images/photo.jpg");
        userService.save(user);
        //添加权限
        roleService.insertUserRole(user.getId(),role);
        return "ture";
    }
}
