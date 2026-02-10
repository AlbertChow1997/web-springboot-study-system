package com.albert.service.impl;

import com.albert.mapper.StudentMapper;
import com.albert.pojo.PageResult;
import com.albert.pojo.Student;
import com.albert.pojo.StudentQueryParam;
import com.albert.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> list(StudentQueryParam params) {
        PageHelper.startPage(params.getPage(), params.getPageSize());

        List<Student> studentList = studentMapper.list(params.getName(), params.getDegree(), params.getClazzId());
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }
}
