package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @ClassName CommonController
 * @Description TODO
 * @Author aql
 * @Date 2025/2/5 17:41
 * @Version 1.0
 **/


@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    /**@Description: TODO methods 文件上传和下载
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file) {
        log.info(file.toString());
        return R.success();
    }
}
