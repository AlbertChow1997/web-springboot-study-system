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

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("Delete student: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("Modify student: {}", student);
        studentService.update(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("Get student information by id: {}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    @PutMapping("/violation/{id}/{score}")
    public Result addViolation(@PathVariable Integer id, @PathVariable Short score){
        log.info("Add violation to student: {}, score: {}", id, score);
        studentService.addViolation(id, score);
        return Result.success();
    }

}
