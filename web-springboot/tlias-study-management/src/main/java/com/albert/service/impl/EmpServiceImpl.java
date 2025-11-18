package com.albert.service.impl;

import com.albert.mapper.EmpMapper;
import com.albert.pojo.Emp;
import com.albert.pojo.PageResult;
import com.albert.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.call the count method
        Long total = empMapper.count();

        //2.calculate the start index
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.call the list method


        //4.return the page result
        return new PageResult<Emp>(total, rows);
    }
}
