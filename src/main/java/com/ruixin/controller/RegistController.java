package com.ruixin.controller;

import com.ruixin.bean.User;
import com.ruixin.common.utils.MD5;
import com.ruixin.common.utils.Views;
import com.ruixin.service.RoleService;
import com.ruixin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import com.ruixin.common.utils.MailUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;


@Controller
public class RegistController {

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Value("${mail.href}")
	public String href;

	/**
	 * 跳转到注册页
	 * @return
	 */
	@GetMapping("/register")
	public String register(){
		return Views.REGISTER;
	}

	/**
	 * 注册
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/register")
	public String addUser(User user, Model model){
		User user1 = userService.findByAccount(user.getUsername());
		User user2 = userService.findByAccount(user.getEmail());
		if(user1==null&&user2==null){
			//设置Gravatar图片链接
			String gravatarImg = "/static/images/qqtouxiang.jpg";
			//设置激活码code
			String code = UUID.randomUUID().toString();
			user.setAvatar(gravatarImg);
			user.setCode(code);
			user.setUserPassword(user.getPassword());
			user.setPassword(MD5.md5(user.getUsername(),user.getPassword()));
			//            user.setStatus(User.STATUS_AUDIT);
			//            userService.save(user);
			//            mailUtil.sendHtmlMail(new String[]{user.getEmail()},"hblog账户激活邮件","<h1>请点击<a href='"+href+"activate/"+code+"'>此链接</a>以激活账号</h1>");
			//            model.addAttribute("msg","٩(๑❛ᴗ❛๑)۶注册成功,激活邮件已经发送到您的邮箱请及时激活!");
			user.setStatus(User.STATUS_NORMAL);
			userService.save(user);
			String[] roleIds={"1","5"};
			roleService.insertUserRole(user.getId(),roleIds);
			model.addAttribute("msg","٩(๑❛ᴗ❛๑)۶注册成功");
			return Views.SUCCESS;
		}else{
			model.addAttribute("msg","o(╥﹏╥)o用户名或邮箱存在，注册失败");
			return Views.REGISTER;
		}
	}

	/**
	 * 用户账号激活
	 * @param code
	 * @param model
	 * @return
	 */
	@GetMapping("/activate/{code}")
	public String activateUser(@PathVariable("code") String code,
			Model model){
		User user =userService.getUserByCode(code);
		if(user!=null){
			user.setStatus("0");
			user.setCode("0");
			userService.save(user);
			String[] roleIds={"1","5"};
			roleService.insertUserRole(user.getId(),roleIds);
			model.addAttribute("msg","٩(๑❛ᴗ❛๑)۶账号激活成功!");
			return Views.SUCCESS;
		}else{
			model.addAttribute("msg","o(╥﹏╥)o账号激活码不存在!");
			return Views.ERROR;
		}
	}


}
