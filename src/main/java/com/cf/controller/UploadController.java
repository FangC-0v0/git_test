package com.cf.controller;

import com.cf.pojo.Result;
import com.cf.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //本地存储
   /* @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传,用户姓名：{},年龄{},图片{}",username,age,image);
        //获取原始文件名
        String filename = image.getOriginalFilename();

        //构造唯一文件名，uuid(唯一通用识别码)
        int index = filename.lastIndexOf(".");
        String exname = filename.substring(index);
        String newFileName = UUID.randomUUID().toString()+exname;
        log.info("新的文件名:{}",newFileName);

        //将文件存储在服务器磁盘目录下F:\code\javaweb\image
        image.transferTo(new File("F:\\code\\javaweb\\image\\" +newFileName));

        return Result.success();
    }*/

    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件名:{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件url:{}",url);
        return  Result.success("url");
    }



}
