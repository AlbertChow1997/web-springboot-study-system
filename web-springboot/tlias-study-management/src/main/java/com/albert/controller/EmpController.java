package com.albert.controller;

import com.albert.mapper.EmpMapper;
import com.albert.pojo.Emp;
import com.albert.pojo.EmpQueryParam;
import com.albert.pojo.PageResult;
import com.albert.pojo.Result;
import com.albert.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Employee Controller
 */

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam param) {
        log.info("Enquire employees: {}", param);
        PageResult<Emp> pageResult = empService.page(param);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("Save employee: {}", emp);
        empService.save(emp);
        return Result.success();
    }

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("Enquire employees: page: {}, pageSize: {}, name: {}, gender: {}, begin: {}, end: {}",
//                page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("Delete employees: {}", Arrays.toString(ids));
//        return Result.success();
//    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("Delete employees: {}", ids);
        empService.delete(ids);
        return Result.success();
    }
}
