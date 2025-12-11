package com.albert.controller;


import com.albert.pojo.Result;
import com.albert.utils.AliyunOSSOperator;
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

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        log.info("upload file parameters: file={}", file.getOriginalFilename());
        //Use OSS to store the file
        try {
            String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
            log.info("upload file to OSS success, url={}", url);
            return Result.success(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


    //Local Store plan
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) {
//        log.info("upload file parameters: name={}, age={}, file={}", name, age, file);
//
//        //Get the original filename
//        String originalFilename = file.getOriginalFilename();
//        //Get the file extension
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + extension;
//
//        try {
//            file.transferTo(new File("D:/image/" + newFileName));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return Result.success();
//    }

