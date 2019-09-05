package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class TemplateEngineUtils
{
    @Autowired
    @Qualifier("templateEngineBean")
    private TemplateEngine engine;

    /**
     * 生成静态文件
     *
     * @param template 模板名称
     * @param context      数据内容
     * @param outFilePath  输出路径
     */
    public boolean process(String template, Context context, String outFilePath)
    {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(outFilePath);
            engine.process(template, context, fileWriter);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                fileWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}