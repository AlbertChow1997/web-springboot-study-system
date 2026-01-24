package com.albert.service;

import com.albert.pojo.Emp;
import com.albert.pojo.EmpQueryParam;
import com.albert.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         LocalDate begin,
//                         LocalDate end);

    PageResult<Emp> page(EmpQueryParam param);

    void save(Emp emp);


    void delete(List<Integer> ids);
}
