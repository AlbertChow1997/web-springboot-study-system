package com.albert.mapper;

import com.albert.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * Query all the departments
     * @return
     */
//    @Select("select id, dept_name, create_time, update_time from department order by update_time desc")

    List<Dept> findAll();
}
