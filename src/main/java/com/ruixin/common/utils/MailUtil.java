/**
 * 
 */
package com.ruixin.common.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: MailUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author: ruixin
 * @createDate 2018年1月29日 下午5:42:59 
 * @version:1.0
 * @department:研发部 
 */
@Component
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	/**
	 * <p>Title: sendSimpleMail</p>
	 * <p>Description:发送一般的邮件 </p>
	 * @date 2018年1月29日 下午6:11:54
	 * @param recevice 接受者
	 * @param title 主题
	 * @param content 右键内容
	 * @throws Exception
	 * @return void
	 */
	public void sendSimpleMail(String[] recevice,String title,String content) throws Exception{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(recevice);
		message.setSubject(title);
		message.setText(content);
		System.out.println(message);
		mailSender.send(message);
	}
	
	/**
	 * <p>Title: sendHtmlMail</p>
	 * <p>Description: 发送带有html格式的邮件</p>
	 * @date 2018年1月29日 下午6:15:27
	 * @param recevice
	 * @param title
	 * @param content
	 * @return void
	 */
	public void sendHtmlMail(String[] recevice,String title,String content) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recevice);
            helper.setSubject(title);
            helper.setText(content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
	
	/**
	 * <p>Title: sendAttachmentsMail</p>
	 * <p>Description:发送邮件带附件 </p>
	 * @date 2018年1月29日 下午6:20:30
	 * @param recevice 
	 * @param title
	 * @param content
	 * @param files 文件
	 * @param fileMsg 文件信息
	 * @return void
	 */
	public void sendAttachmentsMail(String[] recevice,String title,String content,File files,String fileMsg) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recevice);
            helper.setSubject(title);
            helper.setText(content);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(files);
            //加入邮件
            helper.addAttachment(fileMsg, file);
        } catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
    }
	
	/**
	 * <p>Title: sendInlineMail</p>
	 * <p>Description:发送带静态资源的邮件</p>
	 * @date 2018年1月29日 下午6:23:40
	 * @param recevice
	 * @param title
	 * @param content 带静态资源的邮件内容 图片:<img src='cid:fileMsg' />  *这里的fileMsg就是指fileMsg里面的值  cid是固定值
	 * @param files
	 * @param fileMsg
	 * @return void
	 */
	 public void sendInlineMail(String[] recevice,String title,String content,File files,String fileMsg) {
	        MimeMessage message = null;
	        try {
	            message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setFrom(sender);
	            helper.setTo(recevice);
	            helper.setSubject(title);
	            //第二个参数指定发送的是HTML格式,同时cid:是固定的写法
	            helper.setText(content, true);

	            FileSystemResource file = new FileSystemResource(files);
	            helper.addInline(fileMsg,file);
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	        mailSender.send(message);
	    }
}
