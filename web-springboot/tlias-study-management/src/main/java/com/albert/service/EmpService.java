package com.albert.service;

import com.albert.pojo.Emp;
import com.albert.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
