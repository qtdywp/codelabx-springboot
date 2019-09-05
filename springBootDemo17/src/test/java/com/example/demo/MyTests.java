package com.example.demo;

import com.example.demo.util.FileUtil;
import com.example.demo.util.TemplateEngineUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTests
{
    @Autowired
    private TemplateEngineUtils templateEngineUtils;

    @Test
    public void toHtmlPageProcess()
    {
        // 创建文件夹
        String outFilePath = "/Users/wang/Downloads/springBootDemo17/";
        File destFile = new File(outFilePath);
        if(destFile.exists())
        {
            FileUtil.delFolder(outFilePath);
        }
        destFile.mkdirs();

        // 生成静态页
        Context context = new Context();
        context.setVariable("welcomeText", "Welcome to CodeLabX!");

        templateEngineUtils.process("index", context, outFilePath + "index.html");
    }
}
