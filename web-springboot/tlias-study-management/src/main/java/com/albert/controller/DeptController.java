package com.albert.controller;

import com.albert.pojo.Dept;
import com.albert.pojo.Result;
import com.albert.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping( "/depts")
    public Result list() {
        System.out.println("Query all the departments");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
}
