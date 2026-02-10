package com.albert.service;

import com.albert.pojo.Emp;
import com.albert.pojo.EmpQueryParam;
import com.albert.pojo.LoginInfo;
import com.albert.pojo.PageResult;

import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize,
//                         String name, Integer gender,
//                         LocalDate begin,
//                         LocalDate end);

    PageResult<Emp> page(EmpQueryParam param);

    void save(Emp emp);


    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
