package com.ruixin.controller;

import com.ruixin.common.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 测试邮件发送
 */
@RestController
public class TestMailController {

    @Autowired
    private MailUtil mailUtil;

    @GetMapping("/testMail1")
    public String simpleMail() throws Exception {
        String[] revicer={"3128341058@qq.com","1299077789@qq.com"};
        mailUtil.sendSimpleMail(revicer,"这是一个测试邮件1","这是一个测试邮件！");
        return "success";
    }

    @GetMapping("/testMail2")
    public String sendHtmlMail() throws Exception {
        String[] revicer={"3128341058@qq.com","1299077789@qq.com"};
        mailUtil.sendHtmlMail(revicer,"<h1>这是一个测试邮件2</h1>","<h1 style='color:red;'>这是一个测试邮件！</h1>");
        return "success";
    }

    @GetMapping("/testMail3")
    public String sendInlineMail() throws Exception {
        String[] revicer={"3128341058@qq.com","1299077789@qq.com"};
        File file=new File("C:\\Users\\Administrator\\IdeaProjects\\hblog\\src\\main\\webapp\\static\\images\\photo.jpg");
        mailUtil.sendInlineMail(revicer,"<h1>这是一个测试邮件3</h1>","<h1 style='color:red;'>这是一个测试邮件！<img src='cid:photo'/></h1>",file,"photo");
        return "success";
    }

    @GetMapping("/testMail4")
    public String sendAttachmentsMail() throws Exception {
        String[] revicer={"3128341058@qq.com","1299077789@qq.com"};
        File file=new File("C:\\Users\\Administrator\\IdeaProjects\\hblog\\src\\main\\webapp\\static\\images\\photo.jpg");
        mailUtil.sendAttachmentsMail(revicer,"<h1>这是一个测试邮件4</h1>","<h1 style='color:red;'>这是一个测试邮件!</h1>",file,"photo");
        return "success";
    }

}
