package com.albert.mapper;

import com.albert.pojo.Emp;
import com.albert.pojo.EmpExpr;
import com.albert.pojo.EmpQueryParam;
import com.albert.pojo.PageResult;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * query total count of employees
     */

    List<Emp> list(EmpQueryParam param);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    //statistics of employees' position
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();


    //PageResult page(Integer page, Integer pageSize);
}
