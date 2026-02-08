package com.albert.controller;


import com.albert.pojo.PageResult;
import com.albert.pojo.Result;
import com.albert.pojo.Student;
import com.albert.pojo.StudentQueryParam;
import com.albert.service.StudentService;
import com.github.pagehelper.page.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam params) {
        PageResult<Student> pageResult = studentService.list(params);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("Add student: {}", student);
        studentService.add(student);
        return Result.success();
    }

}
