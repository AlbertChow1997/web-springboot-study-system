package com.albert.service;

import com.albert.pojo.PageResult;
import com.albert.pojo.Student;
import com.albert.pojo.StudentQueryParam;
import com.github.pagehelper.page.PageParams;

import java.util.List;

public interface StudentService {

    PageResult<Student> list(StudentQueryParam params);

    void add(Student student);

    void deleteByIds(List<Integer> ids);

    void update(Student student);

    Student getInfo(Integer id);

    void addViolation(Integer id, Short score);
}
