package org.my.springstart.controller;

import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.AliOSSProperties;
import org.my.springstart.entity.Result;
import org.my.springstart.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSProperties aliOSSProperties;

    @PostMapping("/upload")
    // spring 提供 MultipartFile 类 接受前端传递的 content-type application-multipart 类型的文件
    public Result upload(String username, Integer age, MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        //1.获取原始文件名, 截取后缀
        String originalFilename = file.getOriginalFilename();   //eg: 1.2.3.jpg
        log.info("原始文件名：{}", originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));     //获取后缀 .jpg
        System.out.println(aliOSSProperties);
        aliOSSProperties.setTime(LocalDateTime.now());
        System.out.println(aliOSSProperties);
        //2.调用阿里云OSS工具类，将文件上传到oss
        String url = OssUtil.upload(aliOSSProperties.getEndpoint(), aliOSSProperties.getBucket(), aliOSSProperties.getAccessKeyId(), aliOSSProperties.getAccessKeySecret(), file.getBytes(), extName);

        //3.返回图片路径
        return Result.success(url);
////        log.info("参数：{}，{}，{}", username, age, file);
//        System.out.println(file);
//             //1.获取原始文件名
//             String originalFilename = file.getOriginalFilename();   //eg: 001.002.003.jpg
//
//             //2.通过UUID生成随机字符串
//             String newFileName = UUID.randomUUID() + "_" + originalFilename;
//
//             //2.将前端上传的文件存到本地
//             file.transferTo(new File("/Users/baiyuejiao/Desktop/j-p/spring-start/src/main/resources/static/"+ newFileName));//eg: 1.2.3.jpg
//        log.info("原始文件名：{}", originalFilename);
//        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));     //获取后缀 .jpg
//
//        //2.调用阿里云OSS工具类，将文件上传到oss
//        String url = OssUtil.upload(aliOSSProperties.getEndpoint(), aliOSSProperties.getBucket(), file.getBytes(), extName);
//
//        //3.返回图片路径
//        return Result.success(url);
    }
}
