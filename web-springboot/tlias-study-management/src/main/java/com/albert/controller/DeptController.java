package com.albert.controller;

import com.albert.pojo.Dept;
import com.albert.pojo.Result;
import com.albert.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        System.out.println("Query all the departments");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
    * Delete department by id
    * @param deptId the id of the department to be deleted
    *   Whenever you use the @RequestParam annotation,
    *   you can use the name of the parameter to get the value of the parameter.
    *   But there must be a parameter in the request body, or it will throw an error.
    *   If the parameter is not compulsory, you can use the @RequestParam(required = false) annotation to avoid the error.
    *   The default value of the required parameter is true, which means that the parameter is compulsory.
    * @return the result of the operation
    * */

    @DeleteMapping
    public Result delete(@RequestParam("id") Integer deptId) {
        System.out.println("Delete department by id: " + deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    /*
    * Add department
    * @param dept the department to be added
    * @return the result of the operation
    *
    * @RequestBody annotation is used to bind the request body to the parameter.
    * Usually used to bind JSON parameters in the request body.
    * */
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("Add department: " + dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer deptId) {
        System.out.println("Query department by id: " + deptId);
        Dept dept = deptService.getInfo(deptId);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("Update department: " + dept);
        deptService.update(dept);
        return Result.success();
    }


}
