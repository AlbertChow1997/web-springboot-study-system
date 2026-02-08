package com.albert.controller;


import com.albert.pojo.Clazz;
import com.albert.pojo.ClazzQueryParam;
import com.albert.pojo.PageResult;
import com.albert.pojo.Result;
import com.albert.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

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

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("Add clazz: {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("Modify clazz: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Long id){
        log.info("Get clazz info: {}", id);
        Clazz clazz = clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("Delete clazz: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
