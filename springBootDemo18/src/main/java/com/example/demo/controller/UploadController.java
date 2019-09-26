package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController
{
    // 上传文件存放的路径
    private static String UPLOADED_FOLDER = "/Users/wang/Downloads/";

    @GetMapping("/upload")
    public String index()
    {
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
    {
        if (file.isEmpty() && file.getName().equals(""))
        {
            // file.getName().equals("")的目的是解决例如一个文本文件无任何内容0KB的情况，无法上传的BUG
            redirectAttributes.addFlashAttribute("message", "请选择要上传的文件");
            return "redirect:uploadStatus";
        }
        try
        {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            // UPLOADED_FOLDER 文件本地存储地址
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "文件已成功上传 '" + file.getOriginalFilename() + "'");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus()
    {
        return "uploadStatus";
    }

    //----------------------------------------------------------------------------

    @GetMapping("/uploadMore")
    public String uploadMore()
    {
        return "uploadMore";
    }

    @PostMapping("/uploadMoreFile")
    public String moreFileUpload(@RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes)
    {
        if (files.length==0)
        {
            redirectAttributes.addFlashAttribute("message", "请选择要上传的文件");
            return "redirect:uploadStatus";
        }
        for(MultipartFile file:files)
        {
            try
            {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", "文件已全部上传完毕");
        return "redirect:/uploadStatus";
    }
}
