package com.albert.controller;


import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import com.albert.pojo.PageResult;
import com.albert.pojo.Result;
import com.albert.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result getClazz(ClazzQueryParam param) {
        log.info("List all clazzs,{}", param);
        PageResult<Clazz> clazzList = clazzService.page(param);

        return Result.success(clazzList);
    }
}
