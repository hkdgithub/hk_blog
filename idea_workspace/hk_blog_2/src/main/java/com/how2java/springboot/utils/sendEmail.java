package com.how2java.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class sendEmail {
    //初始化Log4j的一个实例，让这个实例在以后的打印中，题头都带上你的类名
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    /*发送纯文本的简单邮件
    * @Param to 收件人Email地址
    * @Param from 发件人Email地址
    * @Param content 邮件主题内容
    * @Param subject 邮件主题
    * */
    public void sendSimpMail(String to ,String subject ,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try{
            sender.send(message);
            logger.info("简单邮件已经发送");
        }catch(Exception e){
            logger.error("发送邮件出现异常"+e);
        }

    }

    //发送html格式的邮件
    public void sendHtmlMail(String to ,String subject ,String content){
        MimeMessage message = sender.createMimeMessage();
        try{
            //true表示需要创建一个multpart mesage
            MimeMessageHelper helper = new MimeMessageHelper(message ,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content ,true);
            sender.send(message);
            logger.info("html邮件已发送");
        }catch (Exception e){
            logger.error("发送html邮件出现异常"+e);
        }
    }

    //发送附带的邮件
    public void sendAttachmentsMail(String to ,String subject ,String content ,String  filePath){
        MimeMessage message = sender.createMimeMessage();
        try{
            //true表式需要创建一个multpart message
            MimeMessageHelper helper = new MimeMessageHelper(message ,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content ,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            //获得文件的名字
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName ,file);
            sender.send(message);
            logger.info("带附件的邮件已经发送");
        }catch(Exception e){
            logger.error("发送带附件的邮件出现异常"+e);
        }
    }

    /*发送嵌入式静态资源（一般是图片）的邮件
    * @param content 邮件内容，需要包括一个静态资源的id，比如<img src="/>
    * @param rscPath 静态资源路径和文件名
    * @param rscId 静态资源Id
    * */

    public void sendInlineResourceMail(String to ,String subject ,String content ,String rscPath ,String rscId){
        MimeMessage message = sender.createMimeMessage();
        try{
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message ,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content ,true);
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId ,res);
            sender.send(message);
            logger.info("嵌入静态资源的邮件已经发送");
        }catch (Exception e){
            logger.error("发送嵌入式资源的邮件出现异常"+e);
        }
    }
}
