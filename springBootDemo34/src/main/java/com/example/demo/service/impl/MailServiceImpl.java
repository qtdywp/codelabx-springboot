package com.example.demo.service.impl;

import com.example.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailServiceImpl implements MailService
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    // 发送文本邮件
    @Override
    public void sendSimpleMail(String to, String subject, String content)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try
        {
            mailSender.send(message);
            logger.info("SimpleMail已发送");
        } catch (Exception e)
        {
            logger.error("SimpleMail发送异常！", e);
            throw e;
        }

    }

    // 发送html邮件
    @Override
    public void sendHtmlMail(String to, String subject, String content)
    {
        MimeMessage message = mailSender.createMimeMessage();

        try
        {
            // 通过MimeMessageHelper来为MimeMessage设置各项属性
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);  // true表示接收content type "text/html"类型，默认content type ("text/plain")

            mailSender.send(message);
            logger.info("HtmlMail已发送");
        } catch (MessagingException e)
        {
            logger.error("HtmlMail发送异常！", e);
        }
    }


    // 发送带附件的邮件
    public void sendAttachmentsMail(String to, String subject, String content, String filePath)
    {
        MimeMessage message = mailSender.createMimeMessage();

        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            logger.info("AttachmentsMail已发送");
        } catch (MessagingException e)
        {
            logger.error("AttachmentsMail发送异常！", e);
        }
    }


    // 发送正文中有静态资源（图片）的邮件
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId)
    {
        MimeMessage message = mailSender.createMimeMessage();

        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            logger.info("InlineResourceMail已发送");
        } catch (MessagingException e)
        {
            logger.error("InlineResourceMail发送异常！", e);
        }
    }
}