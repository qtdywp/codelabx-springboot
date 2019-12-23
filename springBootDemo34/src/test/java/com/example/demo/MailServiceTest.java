package com.example.demo;

import com.example.demo.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailServiceTest
{
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail()
    {
        mailService.sendSimpleMail("123456@qq.com", "This is simple mail", " Hello, This is simple mail.");
    }

    @Test
    public void testHtmlMail()
    {
        String content = "<html><body><h1>Hello，这是一封html邮件!</h1><h3>这行字体应该比上一行要小。</h3></body></html>";
        mailService.sendHtmlMail("123456@qq.com", "This is HTML mail", content);
    }

    @Test
    public void sendAttachmentsMail()
    {
        String filePath = "/Users/wang/Downloads/notice.zip";
        mailService.sendAttachmentsMail("123456@qq.com", "This is Attachments mail", "Hello, This is Attachments mail.", filePath);
    }

    @Test
    public void sendInlineResourceMail()
    {
        String rscId = "IMG_20191007_155634_1";
        String content = "<html><body>hi~，分享我的新摄影作品：<br /><img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/wang/Downloads/IMG_20191007_155634_1.jpg";

        mailService.sendInlineResourceMail("123456@qq.com", "This is Inline Resource mail", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail()
    {
        Context context = new Context();
        context.setVariable("checkCode", "45CB0D8F_09100037_7D1056EB_A1DDC1A2");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("123456@qq.com", "This is Template mail", emailContent);
    }
}