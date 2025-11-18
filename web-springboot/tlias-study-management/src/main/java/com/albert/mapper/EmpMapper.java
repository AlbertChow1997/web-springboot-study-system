package com.albert.mapper;

import com.albert.pojo.Emp;
import com.albert.pojo.PageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * query total count of employees
     */
    public Long count();

    public List<Emp> list();

    //PageResult page(Integer page, Integer pageSize);
}
